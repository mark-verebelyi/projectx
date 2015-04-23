package zzz.projectx.core.cqrs.command;

import java.lang.annotation.Annotation;

import org.springframework.context.annotation.Configuration;

import zzz.projectx.api.cqrs.command.handler.CommandHandler;
import zzz.projectx.core.AbstractComponentRegistrar;

@Configuration
class CommandHandlerRegistrar extends AbstractComponentRegistrar {

	protected CommandHandlerRegistrar() {
		super(new CommandHandlerNameTemplate());
	}

	@Override
	protected Class<?> componentType() {
		return CommandHandler.class;
	}

	@Override
	protected Class<? extends Annotation> enablingAnnotationType() {
		return EnableCommandBus.class;
	}

}
