package me.ranol.commandsapi;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandListener implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender s, Command c, String l,
			String[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}
