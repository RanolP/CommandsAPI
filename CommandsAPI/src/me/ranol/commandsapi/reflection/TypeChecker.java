package me.ranol.commandsapi.reflection;

public class TypeChecker {
	public static boolean isNumberClass(Class<?> clazz) {
		return clazz.isAssignableFrom(Byte.class)
				|| clazz.isAssignableFrom(Short.class)
				|| clazz.isAssignableFrom(Integer.class)
				|| clazz.isAssignableFrom(Long.class)
				|| clazz.isAssignableFrom(byte.class)
				|| clazz.isAssignableFrom(short.class)
				|| clazz.isAssignableFrom(int.class)
				|| clazz.isAssignableFrom(long.class);
	}

	public static boolean isDoubleClass(Class<?> clazz) {
		return isNumberClass(clazz) || clazz.isAssignableFrom(Double.class)
				|| clazz.isAssignableFrom(Float.class)
				|| clazz.isAssignableFrom(double.class)
				|| clazz.isAssignableFrom(float.class);
	}

	public static boolean isMatch(Class<?> clazz, Class<?> clazz2) {
		if (isNumberClass(clazz))
			return isNumberClass(clazz2);
		if (isDoubleClass(clazz))
			return isDoubleClass(clazz2);
		return clazz.isAssignableFrom(clazz2);
	}
}
