package zzz.projectx.api.cqrs.command;

import static com.google.common.base.Preconditions.checkArgument;

public class CommandException extends RuntimeException {

	private static final long serialVersionUID = 4983184759595932496L;

	private final Command command;

	public CommandException(final Command command, final Throwable cause) {
		super(cause);
		checkArgument(command != null, "command can not be null");
		this.command = command;
	}

	public Command getCommand() {
		return this.command;
	}

}
