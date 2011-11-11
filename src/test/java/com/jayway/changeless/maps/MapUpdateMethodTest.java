package com.jayway.changeless.maps;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.optionals.Optional;

public class MapUpdateMethodTest {
	@Test
	public void replacingNonExistingValueWithNone() throws Exception {
		Map<Integer, String> map = Maps.of(1,"One", 2, "Two", 3, "Three");
		Map<Integer, String> expected = map;
		Map<Integer, String> actual = map.update(5, toNone);
		assertEquals("Expected maps to be equals", expected, actual);
	}
	
	@Test
	public void replacingNonExistingValueWithNewValue() throws Exception {
		Map<Integer, String> map = Maps.of(1,"One", 2, "Two", 3, "Three");
		Map<Integer, String> expected = Maps.of(1,"One", 2, "Two", 3, "Three", 5, "None");
		Map<Integer, String> actual = map.update(5, addDot);
		assertEquals("Expected maps to be equals", expected, actual);
	}
	
	@Test
	public void replacingExistingValueWithNone() throws Exception {
		Map<Integer, String> map = Maps.of(1,"One", 2, "Two", 3, "Three");
		Map<Integer, String> expected = Maps.of(1,"One", 2, "Two");
		Map<Integer, String> actual = map.update(3, toNone);
		assertEquals("Expected maps to be equals", expected, actual);
	}
	
	@Test
	public void replacingExistingValueWithNewValue() throws Exception {
		Map<Integer, String> map = Maps.of(1,"One", 2, "Two", 3, "Three");
		Map<Integer, String> expected = Maps.of(1,"One.", 2, "Two", 3, "Three");
		Map<Integer, String> actual = map.update(1, addDot);
		assertEquals("Expected maps to be equals", expected, actual);
	}
	
	public Fn<Optional<String>, Optional<String>> toNone = new Fn<Optional<String>, Optional<String>>() {
		@Override
		public Optional<String> apply(Optional<String> input) {
			return Optional.none();
		}
	};
	
	public Fn<Optional<String>, Optional<String>> addDot = new Fn<Optional<String>, Optional<String>>() {
		@Override
		public Optional<String> apply(Optional<String> input) {
			if (input.hasNoValue()) {
				return Optional.valueOf("None");
			}
			return Optional.valueOf(input.getValue() +".");
		}
	};
}
