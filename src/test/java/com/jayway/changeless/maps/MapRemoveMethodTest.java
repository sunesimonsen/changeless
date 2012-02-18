package com.jayway.changeless.maps;

import static org.junit.Assert.*;

import org.junit.Test;

public class MapRemoveMethodTest {
	@Test
	public void removeUnexistingKey() throws Exception {
		Map<Integer, String> map = Maps.of(1,"One", 2, "Two", 3, "Three");
		Map<Integer, String> expected = map;
		Map<Integer, String> actual = map.remove(4);
		assertEquals("Expected maps to be equals", expected, actual);
	}
	
	@Test
	public void removeExistingKey() throws Exception {
		Map<Integer, String> map = Maps.of(1,"One", 2, "Two", 3, "Three");
		Map<Integer, String> expected = Maps.of(1,"One", 3, "Three");
		Map<Integer, String> actual = map.remove(2);
		assertEquals("Expected maps to be equals", expected, actual);
	}
	
	@Test
	public void removeAllKeys() throws Exception {
		Map<Integer, String> map = Maps.of(1,"One", 2, "Two", 3, "Three");
		Map<Integer, String> expected = Maps.empty();
		Map<Integer, String> actual = map.remove(1,2,3);
		assertEquals("Expected maps to be equals", expected, actual);
	}
}
