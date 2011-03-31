package com.jayway.changeless.records;

public interface RecordBuilder<T extends Record> {
	public T create() throws InstantiationException, IllegalAccessException;
}
