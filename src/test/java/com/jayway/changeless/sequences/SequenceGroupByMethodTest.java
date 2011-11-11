package com.jayway.changeless.sequences;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jayway.changeless.maps.Map;
import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.records.RecordBuilder;
import com.jayway.changeless.records.Records;
import com.jayway.changeless.stubs.GetAgeFunction;
import com.jayway.changeless.stubs.Person;

public class SequenceGroupByMethodTest {
	RecordBuilder<Person> builder = Records.builder(Person.class);
	
	@Test
	public void onEmptySequence() throws Exception {
		Sequence<Person> sequence = Sequences.empty();
		Map<Integer,Sequence<Person>> expected = Maps.empty();
		Map<Integer,Sequence<Person>> actual = sequence.groupBy(age);
		assertEquals("Expected maps to be equal", expected, actual);
	}
	
	@Test
	public void onNonEmptySequence() throws Exception {
		Sequence<Person> sequence = Sequences.of(
				p("Foo", 56), p("Bar", 26), p("Baz",26),p("Qux",35),p("Quux",35));
		
		Map<Integer,Sequence<Person>> expected = Maps.of(
				26, Sequences.of(p("Baz",26),p("Bar",26)), 
				35, Sequences.of(p("Quux",35),p("Qux",35)), 
				56, Sequences.of(p("Foo",56)));
		Map<Integer,Sequence<Person>> actual = sequence.groupBy(age);
		assertEquals("Expected maps to be equal", expected, actual);
	}
	
	private Person p(String name, int age) {
		return builder.create().name(name).age(age);
	}
	
	private GetAgeFunction age = new GetAgeFunction();
}
