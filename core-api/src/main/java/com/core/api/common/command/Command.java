package com.core.api.common.command;

public class Command {

	@FunctionalInterface
	public interface Executor {
		Object exec(Command command);
	}

	public final String name;
	public final String args;

	public Command(String name, String args) {
		this.name = name;
		this.args = args;
	}
}
