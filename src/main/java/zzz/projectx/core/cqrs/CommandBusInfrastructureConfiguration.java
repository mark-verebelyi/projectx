package zzz.projectx.core.cqrs;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.gateway.GatewayProxyFactoryBean;
import org.springframework.integration.handler.ServiceActivatingHandler;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.SubscribableChannel;

import zzz.projectx.api.cqrs.command.bus.CommandBus;
import zzz.projectx.api.cqrs.command.handler.CommandHandler;

@Configuration
public class CommandBusInfrastructureConfiguration {

	private final ComponentNameTemplate componentNameTemplate = new CommandHandlerNameTemplate();

	@Autowired
	private List<CommandHandler<?>> commandHandlers;

	@Autowired
	private ConfigurableApplicationContext applicationContext;

	@Bean
	public CommandBus commandBus() throws Exception {
		final GatewayProxyFactoryBean commandBus = new GatewayProxyFactoryBean(CommandBus.class);
		commandBus.setBeanFactory(this.applicationContext.getBeanFactory());
		commandBus.setDefaultRequestChannel(commandBusChannel());
		return (CommandBus) commandBus.getObject();
	}

	@Bean
	public DirectChannel commandBusChannel() {
		return new DirectChannel();
	}

	@Bean
	public PayloadTypeRouter commandBusRouter() {
		final PayloadTypeRouter router = new PayloadTypeRouter();
		for (final CommandHandler<?> commandHandler : this.commandHandlers) {
			final String key = getKey(commandHandler);
			final String channelName = getChannelName(commandHandler);
			router.setChannelMapping(key, channelName);
		}
		return router;
	}

	private String getKey(final CommandHandler<?> commandHandler) {
		return commandHandler.commandType().getName();
	}

	private String getChannelName(final CommandHandler<?> commandHandler) {
		return this.componentNameTemplate.nameOfRequestChannelForComponent(commandHandler.getClass().getName());
	}

	@PostConstruct
	public void wireIntegration() {
		commandBusChannel().subscribe(commandBusRouter());
		for (final CommandHandler<?> commandHandler : this.commandHandlers) {
			final SubscribableChannel requestChannel = getChannel(commandHandler);
			final ServiceActivatingHandler serviceActivator = getServiceActivator(commandHandler);
			requestChannel.subscribe(serviceActivator);
		}
	}

	private ServiceActivatingHandler getServiceActivator(final CommandHandler<?> commandHandler) {
		return this.applicationContext.getBean(getServiceActivatorName(commandHandler), ServiceActivatingHandler.class);
	}

	private String getServiceActivatorName(final CommandHandler<?> commandHandler) {
		return this.componentNameTemplate.nameOfServiceActivatorForComponent(commandHandler.getClass().getName());
	}

	private SubscribableChannel getChannel(final CommandHandler<?> commandHandler) {
		return this.applicationContext.getBean(getChannelName(commandHandler), SubscribableChannel.class);
	}

}
