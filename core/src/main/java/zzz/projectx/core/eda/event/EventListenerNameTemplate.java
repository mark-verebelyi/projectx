package zzz.projectx.core.eda.event;

import zzz.projectx.core.AbstractComponentNameTemplate;

final class EventListenerNameTemplate extends AbstractComponentNameTemplate {

	private static final String NAME_PATTERN = "eventListener-%s";
	private static final String REQUEST_CHANNEL_PATTERN = "eventListener-requestChannel-%s";
	private static final String SERVICE_ACTIVATOR_PATTERN = "eventListener-serviceActivator-%s";

	public EventListenerNameTemplate() {
		super(NAME_PATTERN, REQUEST_CHANNEL_PATTERN, SERVICE_ACTIVATOR_PATTERN);
	}

}
