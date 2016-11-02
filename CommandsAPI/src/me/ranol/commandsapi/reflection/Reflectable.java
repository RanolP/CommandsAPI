package me.ranol.commandsapi.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Reflectable {
	private HashMap<Class<?>, Object> reflectMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	protected <T, U extends Annotation> T reflectAnnotation(
			Class<T> resultClass, Class<U> annoClass) {
		U anno = getClass().getDeclaredAnnotation(annoClass);
		if (anno == null)
			return null;
		Method m;
		try {
			if ((m = annoClass.getMethod("value")) != null) {
				T value = (T) m.invoke(anno);
				reflectMap.put(annoClass, value);
				return value;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
