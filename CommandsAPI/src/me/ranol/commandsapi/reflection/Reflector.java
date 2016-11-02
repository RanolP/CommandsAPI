package me.ranol.commandsapi.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.google.common.util.concurrent.UncheckedExecutionException;

public class Reflector {

	private Reflector() {
	}

	public static FieldObject getField(Class<?> obj, String fieldName) {
		try {
			FieldObject fobj = new FieldObject(obj.getDeclaredField(fieldName));
			return fobj;
		} catch (NoSuchFieldException e) {
			return null;
		} catch (Exception e) {
			throw new UncheckedExecutionException(e);
		}
	}

	public static MethodObject getMethod(Class<?> obj, String methodName,
			Class<?>... classes) {
		try {
			MethodObject fobj = new MethodObject(obj.getDeclaredMethod(
					methodName, classes));
			return fobj;
		} catch (NoSuchMethodException e) {
			return null;
		} catch (Exception e) {
			throw new UncheckedExecutionException(e);
		}
	}

	public static <T extends Annotation> AnnotationObject getAnnotation(
			Object obj, Class<T> annotation) {
		try {
			Annotation anno = obj.getClass().getAnnotation(annotation);
			return new AnnotationObject(anno);
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			throw new UncheckedExecutionException(e);
		}
	}

	public static <T extends Annotation> AnnotationObject getAnnotation(
			MethodObject method, Class<T> annotation) {
		try {
			Annotation anno = method.getAnnotation(annotation);
			if (anno == null)
				return null;
			return new AnnotationObject(anno);
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			throw new UncheckedExecutionException(e);
		}
	}

	public static <T> T newInstance(Class<T> clazz, ClassMap... args) {
		try {
			Class<?>[] classes = new Class<?>[args.length];
			Object[] objects = new Object[args.length];
			for (int i = 0; i < args.length; i++) {
				classes[i] = args[i].getHandleClass();
				objects[i] = args[i].getHandle();
			}
			Constructor<T> constructor = clazz.getDeclaredConstructor(classes);
			constructor.setAccessible(true);
			return constructor.newInstance(objects);
		} catch (NoSuchMethodException e) {
			return null;
		} catch (Exception e) {
			throw new UncheckedExecutionException(e);
		}
	}

	public static Class<?>[] getClasses(Object... args) {
		if (args.length <= 0)
			return new Class<?>[0];
		List<Class<?>> classes = new ArrayList<>();
		for (Object o : args)
			classes.add(o.getClass());
		return classes.toArray(new Class<?>[0]);
	}

	public static class ClassMap {
		private Object object;
		private Class<?> clazz;

		public <T extends Object> ClassMap(T obj, Class<T> clazz) {
			this.object = obj;
			this.clazz = clazz;
		}

		public <T extends Object> ClassMap(Class<T> clazz, T obj) {
			this.object = obj;
			this.clazz = clazz;
		}

		public <T extends Object> ClassMap(T obj) {
			this.object = obj;
			this.clazz = obj.getClass();
		}

		public Object getHandle() {
			return object;
		}

		public Class<?> getHandleClass() {
			return clazz;
		}
	}
}
