package me.ranol.commandsapi.utils;

import java.util.HashMap;

public class Instancer<T extends Object> {
	private HashMap<Class<?>, T> map = new HashMap<>();

	@SuppressWarnings("unchecked")
	public <O extends T> O getObject(Class<T> clazz) {
		try {
			if (!map.containsKey(clazz)) {
				synchronized (map) {
					clazz.newInstance();
					map.put(clazz, clazz.newInstance());
				}
			}
		} catch (Exception e) {
			return null;
		}
		return (O) map.get(clazz);
	}
}
