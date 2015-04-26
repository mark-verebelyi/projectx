package zzz.projectx.core.eda.event;

import java.util.Set;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import zzz.projectx.api.eda.listener.EventListener;
import zzz.projectx.core.ComponentRegistrarUtils;

@Configuration
class EventListenerRegistrar implements ImportBeanDefinitionRegistrar {

	private static final Class<?> COMPONENT_TYPE = EventListener.class;
	private static final Class<?> ENABLING_ANNOTATION_TYPE = EnableEventBus.class;

	@Override
	public void registerBeanDefinitions(final AnnotationMetadata metadata, final BeanDefinitionRegistry registry) {
		final Set<BeanDefinition> components = findComponents(metadata);
		for (final BeanDefinition component : components) {
			final String beanName = BeanDefinitionReaderUtils.generateBeanName(component, registry);
			registry.registerBeanDefinition(beanName, component);
		}
	}

	private Set<BeanDefinition> findComponents(final AnnotationMetadata metadata) {
		return ComponentRegistrarUtils.findComponents(COMPONENT_TYPE, ENABLING_ANNOTATION_TYPE, metadata);
	}

}
