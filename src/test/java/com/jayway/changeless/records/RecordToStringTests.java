package com.jayway.changeless.records;

import org.junit.Test;

import com.jayway.changeless.stubs.Person;

import static org.junit.Assert.*;

public class RecordToStringTests {
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
