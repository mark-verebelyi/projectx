package zzz.projectx.core.cqrs.command;

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

import zzz.projectx.api.cqrs.command.bus.CommandBus;
import zzz.projectx.api.cqrs.command.handler.CommandHandler;
import zzz.projectx.core.AbstractInfrastructureConfiguration;

@Configuration
class CommandBusInfrastructureConfiguration extends AbstractInfrastructureConfiguration<CommandHandler<?>> {

	@Autowired(required = false)
	private List<CommandHandler<?>> commandHandlers;

	public CommandBusInfrastructureConfiguration() {
		super(new CommandHandlerNameTemplate());
	}

	@Override
	@Bean(name = "commandBusChannel")
	public SubscribableChannel componentChannel() {
		return new DirectChannel();
	}

	@Override
	@Bean(name = "commandBusRouter")
	public PayloadTypeRouter componentRouter() {
		final PayloadTypeRouter router = new PayloadTypeRouter();
		for (final CommandHandler<?> commandHandler : components()) {
			final String key = getKey(commandHandler);
			final String channelName = getChannelName(commandHandler);
			router.setChannelMapping(key, channelName);
		}
		return router;
	}

	@Override
	protected List<CommandHandler<?>> components() {
		return commandHandlers != null ? commandHandlers : new ArrayList<>();
	}

	@Bean
	public CommandBus commandBus(final ApplicationContext applicationContext) throws Exception {
		final GatewayProxyFactoryBean commandBus = new GatewayProxyFactoryBean(CommandBus.class);
		commandBus.setBeanFactory(applicationContext);
		commandBus.setDefaultRequestChannel(componentChannel());
		return (CommandBus) commandBus.getObject();
	}

	private String getKey(final CommandHandler<?> commandHandler) {
		return commandHandler.commandType().getName();
	}

}
