package zzz.projectx.core.cqrs.query;

import zzz.projectx.core.AbstractComponentNameTemplate;

final class QueryHandlerNameTemplate extends AbstractComponentNameTemplate {

	private static final String NAME_PATTERN = "queryHandler-%s";
	private static final String REQUEST_CHANNEL_PATTERN = "queryHandler-requestChannel-%s";
	private static final String SERVICE_ACTIVATOR_PATTERN = "queryHandler-serviceActivator-%s";

	public QueryHandlerNameTemplate() {
		super(NAME_PATTERN, REQUEST_CHANNEL_PATTERN, SERVICE_ACTIVATOR_PATTERN);
	}

}
