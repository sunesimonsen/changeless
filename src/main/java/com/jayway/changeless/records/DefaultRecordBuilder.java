package com.jayway.changeless.records;

import java.lang.reflect.Proxy;
import java.util.HashMap;

class DefaultRecordBuilder<T extends Record<?>> implements RecordBuilder<T> {
	private final Class<? extends T> clazz;
	private final HashMap<String, Class<?>> types;
	private ClassLoader classLoader;
	private Class<?>[] interfaces;
	private RecordValidator validator = new DefaultRecordValidator();

	private DefaultRecordBuilder(Class<? extends T> clazz) {
		this.clazz = clazz;
		types = validator.validateRecord(clazz);
		classLoader = clazz.getClassLoader();
		interfaces = new Class[] { clazz };
	}

	@SuppressWarnings("unchecked")
	public T create() {
		return (T) Proxy.newProxyInstance(classLoader, interfaces,
				new RecordInceptor(clazz, types));
	}
	
	public static <T extends Record<?>> RecordBuilder<T> create(Class<T> clazz) {
		return new DefaultRecordBuilder<T>(clazz);
	}
}
