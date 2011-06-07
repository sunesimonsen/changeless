package com.jayway.changeless.records;

/**
 * An utility class for working with {@link Record} instances.
 */
public final class Records {
	/**
	 * <p>
	 * Create a new {@link Record} of the given type.
	 * </p>
	 * <p>
	 * See {@link Record} for more information about records.
	 * </p>
	 * <p>
	 * Note that if you are going to create many instances of the same record type you should 
	 * use the {@link Records#builder(Class)} method instead as it will be much faster.
	 * </p>
	 * @param <T> the type of the record to create.
	 * @param clazz the class of the record to create.
	 * @return the created record.
	 * @throws RecordValidationException if the given class is not a valid {@link Record} interface. 
	 * see {@link Record} for more information.
	 */
	public static <T extends Record> T of(Class<T> clazz) {
		return builder(clazz).create();
	}
	
	/**
	 * Creates a new {@link RecordBuilder} capable of creating record instances of type T.
	 * @param <T> the type of the record that the build should be capable of building.
	 * @param clazz the class of the record to create.
	 * @return a new {@link RecordBuilder} capable of creating record instances of type T. 
	 */
	public static <T extends Record> RecordBuilder<T> builder(Class<T> clazz) {
		return DefaultRecordBuilder.create(clazz);
	}
}
