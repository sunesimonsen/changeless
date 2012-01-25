package com.jayway.changeless.records;

import com.jayway.changeless.maps.Map;

/**
 * A record is an immutable collection of unordered labelled fields defined by a
 * record interface. Each field has an extractor method and a mutator method.
 * The extractor method can be used to extract the value of the field in the
 * record and the mutator is used to create a copy of the record where the field
 * is change to a new value. For a Person record with a name field of type
 * String the extractor and mutator must have the following signature:
 * <p>
 * <code>
 * <pre>
 * interface Person {
 *   String name(); // Extractor for the name field Person
 *   name(String name); // Mutator for the name field
 * }
 * </pre>
 * </code>
 * </p>
 */
public interface Record<T extends Record<T>> {
	/**
	 * Returns the data of this record as a map from the field name to the values 
	 * of the fields.  
	 * @return the data of this record as a map.
	 */
	Map<String, Object> getData();

	/**
	 * Merge the entries of the given map, into the backing map of this record,
	 * returning a new record with the updated entries.
	 * <p>
	 * Entries in the given updates map, where the key do not match any field
	 * in the record, will be merged over as well. This can be used to attach
	 * meta-data, or other types of hidden information, to the record.
	 * @param updates The map of field names to values.
	 * @return A new record of the same type, with the mapped fields updated
	 * to new values.
	 * @throws RecordValidationException if the updated entries contain values
	 * of types that do not match those specified by the fields of this record
	 * interface.
	 */
	T merge(Map<String, ?> updates);
}
