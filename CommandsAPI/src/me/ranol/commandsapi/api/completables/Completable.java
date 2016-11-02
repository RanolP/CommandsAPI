package me.ranol.commandsapi.api.completables;

import java.util.List;

import me.ranol.commandsapi.api.CommandArguments;

import org.bukkit.command.CommandSender;

public interface Completable {
	public List<String> complete(CommandSender s, CommandArguments args);
}
