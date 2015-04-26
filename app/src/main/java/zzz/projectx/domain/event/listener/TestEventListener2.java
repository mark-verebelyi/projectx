package zzz.projectx.domain.event.listener;

import zzz.projectx.api.eda.listener.EventListener;
import zzz.projectx.application.event.TestEvent2;

public class TestEventListener2 implements EventListener<TestEvent2> {

	@Override
	public void onEvent(final TestEvent2 event) {
		System.out.println(Thread.currentThread() + " - TestEventListener2.onEvent()");
	}

	@Override
	public Class<TestEvent2> eventType() {
		return TestEvent2.class;
	}

}
