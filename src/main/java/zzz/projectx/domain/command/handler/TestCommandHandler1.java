package zzz.projectx.domain.command.handler;

import zzz.projectx.api.cqrs.command.handler.CommandHandler;
import zzz.projectx.application.command.TestCommand1;

public class TestCommandHandler1 implements CommandHandler<TestCommand1> {

	@Override
	public void handle(final TestCommand1 command) throws Exception {
		System.out.println("TestCommandHandler1.handle()");
	}

	@Override
	public Class<TestCommand1> commandType() {
		return TestCommand1.class;
	}

}
