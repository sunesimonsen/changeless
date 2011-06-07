package com.jayway.changeless.records;

/**
 * A builder capable of building record instances of a given record type.
 * @param <T> the type of the records that this builder can create.
 */
public interface RecordBuilder<T extends Record> {
	/**
	 * Creates a new {@link Record} instance of type T. 
	 * @return a new {@link Record} instance of type T.
	 * @throws RecordValidationException if the given class is not a valid {@link Record} interface. 
	 * see {@link Record} for more information. 
	 */
	public T create();
}
