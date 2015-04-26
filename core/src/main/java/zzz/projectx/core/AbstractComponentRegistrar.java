package zzz.projectx.core;

import static com.google.common.base.Preconditions.checkArgument;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.handler.ServiceActivatingHandler;

public abstract class AbstractComponentRegistrar implements ImportBeanDefinitionRegistrar {

	private final ComponentNameTemplate componentNameTemplate;

	protected AbstractComponentRegistrar(final ComponentNameTemplate componentNameTemplate) {
		checkArgument(componentNameTemplate != null, "componentNameTemplate can not be null");
		this.componentNameTemplate = componentNameTemplate;
	}

	@Override
	public final void registerBeanDefinitions(final AnnotationMetadata importingClassMetadata, final BeanDefinitionRegistry registry) {
		final Set<BeanDefinition> components = findComponents(importingClassMetadata);
		for (final BeanDefinition component : components) {
			registerComponent(registry, component);
			registerRequestChannel(registry, component);
			registerServiceActivator(registry, component);
		}
	}

	private Set<BeanDefinition> findComponents(final AnnotationMetadata importingClassMetadata) {
		return ComponentRegistrarUtils.findComponents(componentType(), enablingAnnotationType(), importingClassMetadata);
	}

	private void registerServiceActivator(final BeanDefinitionRegistry registry, final BeanDefinition component) {
		final String name = componentNameTemplate.nameOfServiceActivatorForComponent(component);
		registry.registerBeanDefinition(name, createServiceActivator(component));
	}

	private BeanDefinition createServiceActivator(final BeanDefinition component) {
		final BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ServiceActivatingHandler.class);
		builder.addConstructorArgReference(componentNameTemplate.nameOfComponent(component));
		return builder.getBeanDefinition();
	}

	private void registerRequestChannel(final BeanDefinitionRegistry registry, final BeanDefinition component) {
		final String name = componentNameTemplate.nameOfRequestChannelForComponent(component);
		registry.registerBeanDefinition(name, createRequestChannel());
	}

	private BeanDefinition createRequestChannel() {
		final BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(requestChannelType());
		return builder.getBeanDefinition();
	}

	private void registerComponent(final BeanDefinitionRegistry registry, final BeanDefinition component) {
		final String name = componentNameTemplate.nameOfComponent(component);
		registry.registerBeanDefinition(name, component);
	}

	protected abstract Class<?> componentType();

	protected abstract Class<? extends Annotation> enablingAnnotationType();

	protected Class<?> requestChannelType() {
		return DirectChannel.class;
	}

}
