package me.ranol.commandsapi.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.google.common.util.concurrent.UncheckedExecutionException;

public class FieldObject {
	private Field handle;

	public FieldObject(Field real) {
		handle = real;
	}

	public Object get(Object instance) {
		try {
			handle.setAccessible(true);
			return handle.get(instance);
		} catch (Exception e) {
			throw new UncheckedExecutionException(e);
		}
	}

	public void set(Object instance, Object value) {
		try {
			handle.setAccessible(true);
			handle.set(instance, value);
		} catch (Exception e) {
			throw new UncheckedExecutionException(e);
		}
	}

	public <T extends Annotation> T getAnnotation(Class<T> anno) {
		try {
			return handle.getDeclaredAnnotation(anno);
		} catch (Exception e) {
			throw new UncheckedExecutionException(e);
		}
	}
}
