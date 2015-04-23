package zzz.projectx.core;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.SubscribableChannel;

public abstract class AbstractInfrastructureConfiguration<T> {

	@Autowired
	private ApplicationContext applicationContext;

	private final ComponentNameTemplate componentNameTemplate;

	public AbstractInfrastructureConfiguration(final ComponentNameTemplate componentNameTemplate) {
		checkArgument(componentNameTemplate != null, "componentNameTemplate can not be null");
		this.componentNameTemplate = componentNameTemplate;
	}

	public abstract SubscribableChannel componentChannel();

	public abstract PayloadTypeRouter componentRouter();

	protected abstract List<T> components();

	@PostConstruct
	public final void wireIntegration() {
		componentChannel().subscribe(componentRouter());
		for (final T component : components()) {
			final SubscribableChannel requestChannel = getChannel(component);
			final ServiceActivatingHandler serviceActivator = getServiceActivator(component);
			requestChannel.subscribe(serviceActivator);
		}
	}

	private SubscribableChannel getChannel(final T component) {
		return applicationContext.getBean(getChannelName(component), SubscribableChannel.class);
	}

	protected final String getChannelName(final T component) {
		return componentNameTemplate.nameOfRequestChannelForComponent(component.getClass().getName());
	}

	private ServiceActivatingHandler getServiceActivator(final T component) {
		return applicationContext.getBean(getServiceActivatorName(component), ServiceActivatingHandler.class);
	}

	private String getServiceActivatorName(final T component) {
		return componentNameTemplate.nameOfServiceActivatorForComponent(component.getClass().getName());
	}

}
