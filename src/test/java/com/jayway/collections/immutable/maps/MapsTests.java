package com.jayway.collections.immutable.maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MapsTests {
	@Test
	public void putCreatesANewMap() throws Exception {
		Map<Integer, String> map = Maps.empty();
		Map<Integer, String> modifiedMap = map.put(1, "One");
		modifiedMap = modifiedMap.put(2, "Two");
		modifiedMap = modifiedMap.put(3, "Three");
		
		assertNotSame("Expected put to return a new map",
				map, modifiedMap);
	}
	
	@Test
	public void insertedIntegersCanBeFound() throws Exception {
		Map<Integer, Integer> map = Maps.empty();
		int numberOfElements = 10000;
		for (int i = 0; i < numberOfElements; i++) {
			map = map.put(i, i);
		}
		
		for (int i = 0; i < numberOfElements; i++) {
			Integer expected = i;
			Integer actual = map.get(i).getValue();
			assertEquals("Expected elements to be equal", expected, actual);
		}
	}
	
	@Test
	public void insertedStringsCanBeFound() throws Exception {
		Map<String, Integer> map = Maps.empty();
		int numberOfElements = 10000;
		for (int i = 0; i < numberOfElements; i++) {
			map = map.put(String.valueOf(i), i);
		}
		
		for (int i = 0; i < numberOfElements; i++) {
			Integer expected = i;
			Integer actual = map.get(String.valueOf(i)).getValue();
			assertEquals("Expected elements to be equal", expected, actual);
		}
	}
	@Test
	public void replacedStringCanBeFound() throws Exception {
		Map<String, Integer> map = Maps.empty();
		int numberOfElements = 2;
		for (int i = 0; i < numberOfElements; i++) {
			map = map.put(String.valueOf(i), i);
		}
		
		for (int i = 0; i < numberOfElements; i++) {
			map = map.put(String.valueOf(i), i+1);
		}
		
		for (int i = 0; i < numberOfElements; i++) {
			Integer expected = i + 1;
			Integer actual = map.get(String.valueOf(i)).getValue();
			assertEquals("Expected elements to be equal", expected, actual);
		}
	}
	
	@Test
	public void toStringOnEmptyMap() throws Exception {
		Map<Integer,String> map = Maps.empty();
		assertEquals("Expected string representation", "()", map.toString());
	}
	
	@Test
	public void toStringOnMap() throws Exception {
		Map<Integer,String> map = Maps.of(42,"42",41,"41",40,"40");
		String actual = map.toString();
		assertTrue("Expected toString output to contain", actual.contains("<42,42>"));
		assertTrue("Expected toString output to contain", actual.contains("<41,41>"));
		assertTrue("Expected toString output to contain", actual.contains("<40,40>"));
	}
}
