package me.ranol.commandsapi.api;

import org.bukkit.command.CommandSender;

@FunctionalInterface
public interface AbstractArgument {
	public boolean exec(CommandSender s, CommandArguments args);
}
