package me.ranol.commandsapi.api.restore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ResPlayer implements StringRestoreable<Player> {

	@Override
	public Player restore(String s) {
		return Bukkit.getPlayer(s);
	}

}
