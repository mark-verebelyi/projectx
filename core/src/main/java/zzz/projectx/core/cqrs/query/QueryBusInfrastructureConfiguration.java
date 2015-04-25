package zzz.projectx.core.cqrs.query;

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

import zzz.projectx.api.cqrs.query.bus.QueryBus;
import zzz.projectx.api.cqrs.query.handler.QueryHandler;
import zzz.projectx.core.AbstractInfrastructureConfiguration;

@Configuration
class QueryBusInfrastructureConfiguration extends AbstractInfrastructureConfiguration<QueryHandler<?, ?>> {

	@Autowired(required = false)
	private List<QueryHandler<?, ?>> queryHandlers;

	public QueryBusInfrastructureConfiguration() {
		super(new QueryHandlerNameTemplate());
	}

	@Override
	@Bean(name = "queryBusChannel")
	public SubscribableChannel componentChannel() {
		return new DirectChannel();
	}

	@Override
	@Bean(name = "queryBusRouter")
	public PayloadTypeRouter componentRouter() {
		final PayloadTypeRouter queryBusRouter = new PayloadTypeRouter();
		for (final QueryHandler<?, ?> queryHandler : components()) {
			final String key = getKey(queryHandler);
			final String channelName = getChannelName(queryHandler);
			queryBusRouter.setChannelMapping(key, channelName);
		}
		return queryBusRouter;
	}

	@Override
	protected List<QueryHandler<?, ?>> components() {
		return queryHandlers != null ? queryHandlers : new ArrayList<>();
	}

	@Bean
	public QueryBus queryBus(final ApplicationContext applicationContext) throws Exception {
		final GatewayProxyFactoryBean queryBus = new GatewayProxyFactoryBean(QueryBus.class);
		queryBus.setBeanFactory(applicationContext);
		queryBus.setDefaultRequestChannel(componentChannel());
		return (QueryBus) queryBus.getObject();
	}

	private String getKey(final QueryHandler<?, ?> queryHandler) {
		return queryHandler.queryType().getName();
	}

}
