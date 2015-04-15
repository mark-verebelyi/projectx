package zzz.projectx.domain.event.listener;

import zzz.projectx.api.eda.listener.EventListener;
import zzz.projectx.application.event.TestEvent1;

public class TestEventListener1 implements EventListener<TestEvent1> {

	@Override
	public void onEvent(final TestEvent1 event) {
		System.out.println("TestEventListener1.onEvent()");
	}

	@Override
	public Class<TestEvent1> eventType() {
		return TestEvent1.class;
	}

}
