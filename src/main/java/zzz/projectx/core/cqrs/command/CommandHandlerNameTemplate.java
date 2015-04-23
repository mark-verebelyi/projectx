package zzz.projectx.core.cqrs.command;

import zzz.projectx.core.AbstractComponentNameTemplate;

class CommandHandlerNameTemplate extends AbstractComponentNameTemplate {

	private static final String NAME_PATTERN = "commandHandler-%s";
	private static final String REQUEST_CHANNEL_PATTERN = "commandHandler-requestChannel-%s";
	private static final String SERVICE_ACTIVATOR_PATTERN = "commandHandler-serviceActivator-%s";

	public CommandHandlerNameTemplate() {
		super(NAME_PATTERN, REQUEST_CHANNEL_PATTERN, SERVICE_ACTIVATOR_PATTERN);
	}

}
