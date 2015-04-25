package zzz.projectx.api.cqrs.command.handler;

import zzz.projectx.api.cqrs.command.Command;

public interface CommandHandler<C extends Command> {

	void handle(C command) throws Exception;

	Class<C> commandType();

}
