package com.core.api.common.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class CommandManager {
	
	private static final Logger log = LoggerFactory.getLogger(CommandManager.class);

	private static HashMap<String, Command.Executor> executors = new HashMap<>();

	public static synchronized void register(String command, Command.Executor executor) {
		if (executors.containsKey(command)) {
			throw new RuntimeException(command + ":命令重复注册");

		}
		executors.put(command, executor);
	}

	public static synchronized void remove(String command) {
		executors.remove(command);
	}

	public static synchronized void clear() {
		executors.clear();
	}

	public static Object command(Command command) {
		log.info("Exec command: " + command.name);
		Command.Executor executor = executors.get(command.name);
		if (executor != null) {
			return executor.exec(command);
		} else {
			throw new RuntimeException("Not command name!");
		}
	}
}
