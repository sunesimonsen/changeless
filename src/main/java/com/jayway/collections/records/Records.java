package com.jayway.collections.records;

public final class Records {
	public static <T extends Record> T of(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		return builder(clazz).create();
	}
	
	public static <T extends Record> RecordBuilder<T> builder(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		return DefaultRecordBuilder.create(clazz);
	}
}
