package com.jayway.changeless.records;

import java.util.HashMap;

/**
 * A validator capable of validating record classes. 
 */
interface RecordValidator {
	/**
	 * Validates the given record class. 
	 * @param clazz the class to validate.
	 * @return A map of properties to their data type.
	 * @throws RecordValidationException if the given record class is not valid.
	 */
	HashMap<String, Class<?>> validateRecord(Class<?> clazz);
}
