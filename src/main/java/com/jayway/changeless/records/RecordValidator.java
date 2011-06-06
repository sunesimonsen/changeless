package com.jayway.changeless.records;

public interface RecordValidator {
	/**
	 * Validates the given record class. 
	 * @param clazz the class to validate.
	 * @throws RecordValidationException if the given record class is not valid.
	 */
	void validateRecord(Class<? extends Record> clazz);
}
