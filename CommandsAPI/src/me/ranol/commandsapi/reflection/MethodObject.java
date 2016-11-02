package me.ranol.commandsapi.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.google.common.util.concurrent.UncheckedExecutionException;

public class MethodObject {
	private Method handle;

	public MethodObject(Method real) {
		handle = real;
		System.out.println(real);
	}

	public Object Invoke(Object instance, Object... args) {
		try {
			return handle.invoke(instance, args);
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
