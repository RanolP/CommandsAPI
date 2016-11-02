package me.ranol.commandsapi.api;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.ranol.commandsapi.api.completables.Completable;
import me.ranol.commandsapi.utils.Instancer;

public class CompletionUtils extends Instancer<Completable> {
	static Instancer<Completable> instancer = new Instancer<>();

	@SuppressWarnings("unchecked")
	public static <T extends Completable> List<String> getInstance(
			Class<T> clazz, CommandSender s, CommandArguments args) {
		return instancer.getObject((Class<Completable>) clazz)
				.complete(s, args);
	}
}
