package me.ranol.commandsapi.api;

import me.ranol.commandsapi.api.restore.StringRestoreable;
import me.ranol.commandsapi.utils.Instancer;

public class RestoreInstances {
	private static Instancer<StringRestoreable<?>> instance = new Instancer<>();

	@SuppressWarnings("unchecked")
	public static <T extends StringRestoreable<?>> T getInstance(Class<T> clazz) {
		return instance.getObject((Class<StringRestoreable<?>>) clazz);
	}
}
