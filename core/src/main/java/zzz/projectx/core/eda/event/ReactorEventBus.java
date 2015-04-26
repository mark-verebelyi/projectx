package zzz.projectx.core.eda.event;

import static com.google.common.base.Preconditions.checkArgument;
import reactor.core.Reactor;
import reactor.event.Event;
import zzz.projectx.api.eda.bus.EventBus;

final class ReactorEventBus implements EventBus {

	private final Reactor reactor;

	public ReactorEventBus(final Reactor reactor) {
		checkArgument(reactor != null, "reactor can not be null");
		this.reactor = reactor;
	}

	@Override
	public void publish(final zzz.projectx.api.eda.Event event) {
		checkArgument(event != null, "Can't publish null event to EventBus");
		reactor.notify(eventSelector(event), convertToReactorEvent(event));
	}

	private static String eventSelector(final zzz.projectx.api.eda.Event event) {
		return event.getClass().getName();
	}

	private static Event<?> convertToReactorEvent(final zzz.projectx.api.eda.Event event) {
		return Event.wrap(event);
	}
}