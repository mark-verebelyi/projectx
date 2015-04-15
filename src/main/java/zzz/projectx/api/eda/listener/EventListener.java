package zzz.projectx.api.eda.listener;

import zzz.projectx.api.eda.Event;

public interface EventListener<E extends Event> {

	void onEvent(E event);

	Class<E> eventType();

}
