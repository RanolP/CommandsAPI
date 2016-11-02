package me.ranol.commandsapi.api.completables;

import java.util.ArrayList;
import java.util.List;

import me.ranol.commandsapi.api.CommandArguments;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class CompPlayer implements Completable {

	@Override
	public List<String> complete(CommandSender s, CommandArguments args) {
		List<String> players = new ArrayList<>();
		Bukkit.getOnlinePlayers().forEach((p) -> players.add(p.getName()));
		return players;
	}

}
