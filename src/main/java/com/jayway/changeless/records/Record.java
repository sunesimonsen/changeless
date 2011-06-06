package com.jayway.changeless.records;

import com.jayway.changeless.maps.Map;

/**
 * A record is an immutable collection of unordered labeled fields defined by a
 * record interface. Each field has an extractor method and a mutator method.
 * The extractor method can be used to extract the value of the field in the
 * record and the mutator is used to create a copy of the record where the field
 * is change to a new value. For a Person record with a name field of type
 * String the extractor and mutator must have the following signature:
 * <code>
 * interface Person { 
 *   String name(); // Extractor for the name field Person
 *   name(String name); // Mutator for the name field 
 * }
 * </code>
 */
public interface Record {
	/**
	 * Returns the data of this record as a map from the field name to the values 
	 * of the fields.  
	 * @return the data of this record as a map.
	 */
	Map<String, Object> getData();
}
