package com.jayway.changeless.records;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import com.jayway.changeless.functions.Fn2;
import com.jayway.changeless.maps.Map;
import com.jayway.changeless.records.Record;
import com.jayway.changeless.records.RecordBuilder;
import com.jayway.changeless.records.Records;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.sets.Set;
import com.jayway.changeless.sets.Sets;
import com.jayway.changeless.utilities.Comparables;

interface Person extends Record {
	Person name(String name);
	String name();
	Address address();
	Person address(Address address);
}

interface Address extends Record {
	Address street(String street);
	String street();
	Address houseNumber(int houseNumber);
	int houseNumber();
}

public class RecordsTests {
	@Test
	public void createRecord() throws Exception {
		RecordBuilder<Person> personBuilder = Records.builder(Person.class);
		
		Person p1 = personBuilder.create();
		
		Address a1 = Records.of(Address.class);
		a1 = a1.street("foo").houseNumber(15);
		
		p1 = p1.address(a1);
		assertEquals("foo", p1.address().street());
		assertEquals(15, p1.address().houseNumber());
		
		Person p2 = personBuilder.create();
		
		Address a2 = Records.of(Address.class);
		a2 = a2.street("foo").houseNumber(15);
		
		p2 = p2.address(a2);
		
		assertEquals(p1.hashCode(), p2.hashCode());
		assertEquals(p1.toString(), p2.toString());
		assertEquals(p1, p2);
		
		Person p3 = personBuilder.create();
		p3 = p3.address(a2.street("bar"));
		assertFalse("not equals", p1.equals(p3));
	}
	
	interface Planet extends Record {
		String name();
		Planet name(String name);
		double mass();
		Planet mass(double mass);
	}
	
	@Test
	public void creatingAPlanetRecord() {
		RecordBuilder<Planet> pb = Records.builder(Planet.class);
		Planet earth = pb.create().name("Earth").mass(1.00);
		Planet jupiter = pb.create().name("Jupiter").mass(11.209);
		Planet neptune = pb.create().name("Neptune").mass(3.883);
		
		Sequence<Planet> planets = Sequences.of(earth, jupiter, neptune);
		
		Fn2<Planet, Planet, Planet> maxMass = new Fn2<Planet, Planet, Planet>() {
			@Override
			public Planet apply(Planet result, Planet planet) {
				return Comparables.greaterThan(planet.mass(), result.mass()) 
					? planet : result;
			}
		};
		
		Planet largestMass = planets.reduce(maxMass);
		
		assertEquals("Planet name", "Jupiter", largestMass.name());
	}
	
	@Test(expected=IllegalStateException.class)
	public void throwsOnRetrievingUndefinedField() {
		Address address = Records.of(Address.class);
		address.street();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void throwsOnSettingFieldToNull() {
		Address address = Records.of(Address.class);
		address.street(null);
	}
	
	@Test
	public void dataCanBeRetrievedFromRecord() {
		Person person = Records.of(Person.class);
		Address address = Records.of(Address.class);
		address = address.houseNumber(12).street("JayStreet");
		person = person.name("Jane").address(address);
		
		Map<String, Object> data = person.getData();
		
		assertEquals("Jane", data.get("name").getValue());
		assertEquals(address, data.get("address").getValue());
	}
}
