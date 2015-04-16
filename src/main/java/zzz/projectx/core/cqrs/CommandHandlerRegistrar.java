package zzz.projectx.core.cqrs;

import java.lang.annotation.Annotation;

import org.springframework.context.annotation.Configuration;

import zzz.projectx.api.cqrs.command.handler.CommandHandler;

@Configuration
public class CommandHandlerRegistrar extends ComponentRegistrar {

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
