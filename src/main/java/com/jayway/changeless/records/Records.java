package com.jayway.changeless.records;

public final class Records {
	public static <T extends Record> T of(Class<T> clazz) {
		return builder(clazz).create();
	}
	
	public static <T extends Record> RecordBuilder<T> builder(Class<T> clazz) {
		return DefaultRecordBuilder.create(clazz);
	}
}
