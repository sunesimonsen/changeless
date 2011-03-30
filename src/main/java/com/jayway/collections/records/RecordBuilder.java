package com.jayway.collections.records;

public interface RecordBuilder<T extends Record> {
	public T create() throws InstantiationException, IllegalAccessException;
}
