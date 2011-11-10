package com.jayway.changeless.sequences;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.functions.objects.ToStringFunction;
import com.jayway.changeless.records.Record;
import com.jayway.changeless.records.RecordBuilder;
import com.jayway.changeless.records.Records;

public class SequenceSortByMethodTest {
	RecordBuilder<Person> builder = Records.builder(Person.class);
	
	@Test
	public void sortByOnEmptySequence() throws Exception {
		Sequence<Integer> sequence = Sequences.empty();
		Sequence<Integer> expected = Sequences.empty();
		Sequence<Integer> actual = sequence.sortBy(new ToStringFunction());
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	@Test
	public void sortPersonSequenceByName() throws Exception {
		Sequence<Person> sequence = Sequences.of(
				person("Foo"), person("Bar"), person("Bar"), person("Baz"));
		Sequence<Person> expected = Sequences.of(
				person("Bar"), person("Bar"), person("Baz"), person("Foo"));
		Sequence<Person> actual = sequence.sortBy(new GetName());
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	private Person person(String name) {
		return builder.create().name(name);
	}
}

interface Person extends Record {
	String name();
	Person name(String name);
}

class GetName implements Fn<Person, String>{

	@Override
	public String apply(Person input) {
		return input.name();
	}
	
}