package me.ranol.commandsapi;

import me.ranol.commandsapi.api.AbstractCommand;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandsAPI extends JavaPlugin {

	@Override
	public void onLoad() {
	}

	@Override
	public void onEnable() {
		registerCommands(this, new ExampleCommand());

	}

	@Override
	public void onDisable() {
	}

	public static void registerCommands(Plugin plugin, AbstractCommand cmd) {
		cmd.construct(plugin);
	}
}
