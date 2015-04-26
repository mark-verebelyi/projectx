package zzz.projectx.core.eda.event;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.Reactor;
import reactor.event.dispatch.Dispatcher;
import reactor.event.dispatch.RingBufferDispatcher;
import reactor.event.selector.Selector;
import reactor.event.selector.Selectors;
import reactor.function.Consumer;
import zzz.projectx.api.eda.Event;
import zzz.projectx.api.eda.bus.EventBus;
import zzz.projectx.api.eda.listener.EventListener;

@Configuration
class EventBusInfrastructureConfiguration {

	@Autowired(required = false)
	private final List<EventListener<? extends Event>> eventListeners = new ArrayList<>();

	@Bean
	public EventBus eventBus() {
		return new ReactorEventBus(reactor());
	}

	@Bean
	public Reactor reactor() {
		return new Reactor(dispatcher());
	}

	@Bean
	public Dispatcher dispatcher() {
		return new RingBufferDispatcher("eventBusDispatcher");
	}

	@PostConstruct
	public void registerEventListenersToReactor() {
		final Reactor reactor = reactor();
		for (final EventListener<? extends Event> eventListener : eventListeners) {
			registerEventListenerToReactor(reactor, eventListener);
		}
	}

	private void registerEventListenerToReactor(final Reactor reactor, final EventListener<? extends Event> eventListener) {
		final Selector selector = getSelector(eventListener);
		@SuppressWarnings("unchecked")
		final EventListener<Event> rawEventListener = (EventListener<Event>) eventListener;
		final Class<Event> eventType = rawEventListener.eventType();
		reactor.on(selector, new Consumer<reactor.event.Event<?>>() {
			@Override
			public void accept(final reactor.event.Event<?> reactorEvent) {
				final Event event = extractEvent(reactorEvent, eventType);
				rawEventListener.onEvent(event);
			}
		});
	}

	private static Event extractEvent(final reactor.event.Event<?> reactorEvent, final Class<Event> eventType) {
		final Object reactorEventData = reactorEvent.getData();
		checkState(eventType.isInstance(reactorEventData), "Event must be an instance of '%s'; but it was '%s'", eventType.getName(),
				reactorEventData.getClass().getName());
		return eventType.cast(reactorEventData);
	}

	private static Selector getSelector(final EventListener<?> eventListener) {
		return Selectors.object(eventListener.eventType().getName());
	}

}
