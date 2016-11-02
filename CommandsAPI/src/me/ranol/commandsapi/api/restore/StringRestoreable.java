package me.ranol.commandsapi.api.restore;

@FunctionalInterface
public interface StringRestoreable<T> {
	public T restore(String s);
}
