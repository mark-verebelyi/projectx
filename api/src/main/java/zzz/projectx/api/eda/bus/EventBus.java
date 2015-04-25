package zzz.projectx.api.eda.bus;

import zzz.projectx.api.eda.Event;

public interface EventBus {

	void publish(Event event);

}
