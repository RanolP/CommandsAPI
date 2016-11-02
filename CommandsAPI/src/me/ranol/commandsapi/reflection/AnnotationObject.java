package me.ranol.commandsapi.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.google.common.util.concurrent.UncheckedExecutionException;

public class AnnotationObject {
	private Annotation handle;
	private Object value;

	public AnnotationObject(Annotation real) {
		handle = real;
	}

	@SuppressWarnings("unchecked")
	public <T> T value(Class<T> clazz) {
		if (value == null)
			try {
				Method m;
				if ((m = handle.getClass().getMethod("value")) != null) {
					Object value = m.invoke(handle);
					this.value = value;
					return (T) value;
				}
			} catch (Exception e) {
				throw new UncheckedExecutionException(e);
			}
		return (T) value;
	}
}
