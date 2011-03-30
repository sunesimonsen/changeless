package com.jayway.collections.records;

import java.lang.reflect.Proxy;

class DefaultRecordBuilder<T extends Record> implements RecordBuilder<T> {
	private final Class<T> clazz;
	private ClassLoader classLoader;
	private Class<?>[] interfaces;

	private DefaultRecordBuilder(Class<T> clazz) {
		this.clazz = clazz;
		classLoader = clazz.getClassLoader();
		interfaces = new Class[] { clazz };
	}

	@SuppressWarnings("unchecked")
	public T create() throws InstantiationException, IllegalAccessException {
		return (T) Proxy.newProxyInstance(classLoader, interfaces,
                new RecordInceptor<T>(clazz));
	}
	
	public static <T extends Record> RecordBuilder<T> create(Class<T> clazz) {
		return new DefaultRecordBuilder<T>(clazz);
	}
}
