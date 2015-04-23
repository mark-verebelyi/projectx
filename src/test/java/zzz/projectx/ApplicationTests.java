package zzz.projectx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import zzz.projectx.api.cqrs.command.bus.CommandBus;
import zzz.projectx.api.cqrs.query.bus.QueryBus;
import zzz.projectx.api.eda.bus.EventBus;
import zzz.projectx.application.event.TestEvent1;
import zzz.projectx.application.event.TestEvent2;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApplicationTests {

	@Autowired
	private CommandBus commandBus;

	@Autowired
	private QueryBus queryBus;

	@Autowired
	private EventBus eventBus;

	@Test
	public void contextLoads() {
		eventBus.publish(new TestEvent1());
		eventBus.publish(new TestEvent2());
	}

}
