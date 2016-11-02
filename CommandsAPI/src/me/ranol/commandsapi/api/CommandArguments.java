package me.ranol.commandsapi.api;

import java.util.LinkedList;

import me.ranol.commandsapi.api.restore.StringRestoreable;
import me.ranol.commandsapi.reflection.TypeChecker;

public class CommandArguments {
	LinkedList<Object> map = new LinkedList<>();

	public CommandArguments(Class<?>[] clazz, Object... args) {
		for (int i = 0; i < args.length; i++) {
			if (clazz.length >= i)
				map.add(args[i]);
			else
				map.add(clazz[i].cast(args[i]));
		}
	}

	public int size() {
		return map.size();
	}

	public Object[] toArray() {
		return map.toArray(new Object[size()]);
	}

	public CommandArguments(Object... args) {
		for (int i = 0; i < args.length; i++) {
			map.add(args[i]);
		}
	}

	public String getString(int index) {
		return get(index, String.class);
	}

	public byte getByte(int index) {
		return get(index, Byte.class);
	}

	public short getShort(int index) {
		return get(index, Short.class);
	}

	public int getInt(int index) {
		return get(index, Integer.class);
	}

	public long getLong(int index) {
		return get(index, Long.class);
	}

	public char getChar(int index) {
		return get(index, Character.class);
	}

	public boolean getBoolean(int index) {
		return get(index, Boolean.class);
	}

	public <U, T extends StringRestoreable<U>> U getWithString(int index, T obj) {
		return obj.restore(getString(index));
	}

	@SuppressWarnings("unchecked")
	public <T> T get(int index, Class<T> clazz) {
		if (map.size() >= index) {
			Class<?> temp = map.get(index - 1).getClass();
			if (TypeChecker.isMatch(temp, clazz))
				return (T) map.get(index - 1);
			throw new ClassCastException("index " + index + " is not "
					+ clazz.getSimpleName() + "!");
		}
		throw new IndexOutOfBoundsException("index is overed size! "
				+ map.size() + " < " + index);
	}
}
