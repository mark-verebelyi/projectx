package zzz.projectx.core.cqrs.query;

import java.lang.annotation.Annotation;

import org.springframework.context.annotation.Configuration;

import zzz.projectx.api.cqrs.query.handler.QueryHandler;
import zzz.projectx.core.AbstractComponentRegistrar;

@Configuration
class QueryHandlerRegistrar extends AbstractComponentRegistrar {

	protected QueryHandlerRegistrar() {
		super(new QueryHandlerNameTemplate());
	}

	@Override
	protected Class<?> componentType() {
		return QueryHandler.class;
	}

	@Override
	protected Class<? extends Annotation> enablingAnnotationType() {
		return EnableQueryBus.class;
	}

}
