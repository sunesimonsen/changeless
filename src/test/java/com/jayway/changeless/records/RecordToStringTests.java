package com.jayway.changeless.records;

import org.junit.Test;
import static org.junit.Assert.*;

public class RecordToStringTests {
	private interface Person extends Record {
		String name();
		Person name(String name);
		int age();
		Person age(int age);
	}
	
	@Test
	public void toStringIncludesAllFields() {
		Person person = Records.of(Person.class).name("John").age(34);
		assertContains(person.toString(), "<name,John>");
		assertContains(person.toString(), "<age,34>");
	}

	private void assertContains(String text, String expected) {
		String message = String.format("expected text '%s' to contain '%s'", 
				text, expected);
		assertTrue(message , text.contains(expected));
	}
}
