package zzz.projectx.domain.command.handler;

import zzz.projectx.api.cqrs.command.handler.CommandHandler;
import zzz.projectx.application.command.TestCommand2;

public class TestCommandHandler2 implements CommandHandler<TestCommand2> {

	@Override
	public void handle(final TestCommand2 command) throws Exception {
		System.out.println("TestCommandHandler2.handle()");
	}

	@Override
	public Class<TestCommand2> commandType() {
		return TestCommand2.class;
	}

}
