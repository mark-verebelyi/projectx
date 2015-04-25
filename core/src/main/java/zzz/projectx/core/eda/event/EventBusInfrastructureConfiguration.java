package zzz.projectx.core.eda.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.gateway.GatewayProxyFactoryBean;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.SubscribableChannel;

import zzz.projectx.api.eda.bus.EventBus;
import zzz.projectx.api.eda.listener.EventListener;
import zzz.projectx.core.AbstractInfrastructureConfiguration;

@Configuration
class EventBusInfrastructureConfiguration extends AbstractInfrastructureConfiguration<EventListener<?>> {

	@Autowired(required = false)
	private List<EventListener<?>> eventListeners;

	public EventBusInfrastructureConfiguration() {
		super(new EventListenerNameTemplate());
	}

	@Override
	@Bean(name = "eventBusChannel")
	public SubscribableChannel componentChannel() {
		return new DirectChannel();
	}

	@Override
	@Bean(name = "eventBusRouter")
	public PayloadTypeRouter componentRouter() {
		final PayloadTypeRouter queryBusRouter = new PayloadTypeRouter();
		for (final EventListener<?> eventListener : components()) {
			final String key = getKey(eventListener);
			final String channelName = getChannelName(eventListener);
			queryBusRouter.setChannelMapping(key, channelName);
		}
		return queryBusRouter;
	}

	private String getKey(final EventListener<?> eventListener) {
		return eventListener.eventType().getName();
	}

	@Bean
	public EventBus eventBus(final ApplicationContext applicationContext) throws Exception {
		final GatewayProxyFactoryBean eventBus = new GatewayProxyFactoryBean(EventBus.class);
		eventBus.setBeanFactory(applicationContext);
		eventBus.setDefaultRequestChannel(componentChannel());
		return (EventBus) eventBus.getObject();
	}

	@Override
	protected List<EventListener<?>> components() {
		return eventListeners != null ? eventListeners : new ArrayList<>();
	}

}
