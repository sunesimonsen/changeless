package com.jayway.changeless.records;

import static org.junit.Assert.assertEquals;

import com.jayway.changeless.functions.Fn2;
import com.jayway.changeless.records.RecordsTests.Planet;
import com.jayway.changeless.sets.Set;
import com.jayway.changeless.sets.Sets;
import com.jayway.changeless.utilities.Comparables;

/**
 * <p>
 * An utility class for working with {@link Record} instances.
 * </p>
 * <p>
 * Below is an example of what is possible.
 * <code>
 * <pre>
 * interface Planet extends Record {
 *   String name();
 *   Planet name(String name);
 *   double mass();
 *   Planet mass(double mass);
 * }
 * 
 * RecordBuilder<Planet> pb = Records.builder(Planet.class);
 * Planet earth   = pb.create().name("Earth").mass(1.00);
 * Planet jupiter = pb.create().name("Jupiter").mass(11.209);
 * Planet neptune = pb.create().name("Neptune").mass(3.883);
 * 		
 * Sequence&lt;Planet&gt; planets = Sequences.of(earth, jupiter, neptune);
 * Planet largestMass = planets.reduce(new Fn2&lt;Planet, Planet, Planet&gt;() {
 *   public Planet apply(Planet result, Planet planet) {
 *     return Comparables.greaterThan(planet.mass(), result.mass()) 
 *                       ? planet : result;
 *   }
 * });
 * 
 * assertEquals("Planet name", "Jupiter", largestMass.name());
 * </pre>
 * </code>
 * </p>
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
