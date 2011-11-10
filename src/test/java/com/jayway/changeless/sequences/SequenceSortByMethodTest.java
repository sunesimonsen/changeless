package com.jayway.changeless.sequences;

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
		Sequence<Person> sequence = Sequences.of(p("Foo"), p("Bar"), p("Bar"), p("Baz"));
		Sequence<Person> expected = Sequences.of(p("Bar"), p("Bar"), p("Baz"), p("Foo"));
		Sequence<Person> actual = sequence.sortBy(name);
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	@Test
	public void sortByIdentity() throws Exception {
		Sequence<String> sequence = Sequences.of("Foo", "Bar", "Bar", "Baz");
		Sequence<String> expected = Sequences.of("Bar", "Bar", "Baz", "Foo");
		Sequence<String> actual = Sequences.sort(sequence);
		assertEquals("Expected sequences to be equals", expected, actual);
	}
	
	private Person p(String name) {
		return builder.create().name(name);
	}
	
	private Fn<Person, String> name = new Fn<Person, String>(){
		@Override
		public String apply(Person input) {
			return input.name();
		}
	};
}

interface Person extends Record {
	String name();
	Person name(String name);
}