package zzz.projectx.core.eda.event;

import java.lang.annotation.Annotation;

import org.springframework.context.annotation.Configuration;

import zzz.projectx.api.eda.listener.EventListener;
import zzz.projectx.core.AbstractComponentRegistrar;

@Configuration
class EventListenerRegistrar extends AbstractComponentRegistrar {

	protected EventListenerRegistrar() {
		super(new EventListenerNameTemplate());
	}

	@Override
	protected Class<?> componentType() {
		return EventListener.class;
	}

	@Override
	protected Class<? extends Annotation> enablingAnnotationType() {
		return EnableEventBus.class;
	}

}
