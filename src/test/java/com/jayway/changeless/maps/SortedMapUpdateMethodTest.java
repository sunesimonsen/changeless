package com.jayway.changeless.maps;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.optionals.Optional;

public class SortedMapUpdateMethodTest {
	@Test
	public void replacingNonExistingValueWithNewValue() throws Exception {
		Map<Integer, String> map = SortedMaps.of(1,"One", 2, "Two", 3, "Three");
		Map<Integer, String> expected = Maps.of(1,"One", 2, "Two", 3, "Three", 5, "None");
		Map<Integer, String> actual = map.update(5, addDot);
		assertEquals("Expected maps to be equals", expected, actual);
	}
	
	@Test
	public void replacingExistingValueWithNewValue() throws Exception {
		Map<Integer, String> map = SortedMaps.of(1,"One", 2, "Two", 3, "Three");
		Map<Integer, String> expected = Maps.of(1,"One.", 2, "Two", 3, "Three");
		Map<Integer, String> actual = map.update(1, addDot);
		assertEquals("Expected maps to be equals", expected, actual);
	}
	
	public Fn<Optional<String>, String> addDot = new Fn<Optional<String>, String>() {
		@Override
		public String apply(Optional<String> input) {
			if (input.hasNoValue()) {
				return "None";
			}
			return input.getValue() +".";
		}
	};
}
