package zzz.projectx.api.cqrs.command.bus;

import zzz.projectx.api.cqrs.command.Command;
import zzz.projectx.api.cqrs.command.CommandException;

public interface CommandBus {

	void dispatch(Command command) throws CommandException;

}
