package com.jayway.changeless.maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jayway.changeless.optionals.Optional;

public class SortedMapsTests {
	@Test
	public void putCreatesANewMap() throws Exception {
		Map<Integer, String> map = SortedMaps.empty();
		Map<Integer, String> modifiedMap = map.put(1, "One");
		modifiedMap = modifiedMap.put(2, "Two");
		modifiedMap = modifiedMap.put(3, "Three");
		
		assertNotSame("Expected put to return a new map",
				map, modifiedMap);
	}
	
	@Test
	public void insertedIntegersCanBeFound() throws Exception {
		Map<Integer, Integer> map = SortedMaps.empty();
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
		Map<String, Integer> map = SortedMaps.empty();
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
		Map<String, Integer> map = SortedMaps.empty();
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
	public void insertAtTheEndOfBucket() throws Exception {
		Map<Integer, Integer> map = SortedMaps.empty();
		Map<Integer, Integer> actual = map.put(0, 0).put(32, 32);
		Map<Integer, Integer> expected = Maps.of(0,0,32,32);
		assertEquals("Expected elements to be equal", expected, actual);
	}
	
	@Test
	public void toStringOnEmptyMap() throws Exception {
		Map<Integer,String> map = SortedMaps.empty();
		assertEquals("Expected string representation", "()", map.toString());
	}
	
	@Test
	public void toStringOnMap() throws Exception {
		Map<Integer,String> map = SortedMaps.of(42,"42",41,"41",40,"40");
		String actual = map.toString();
		String expected = "(<40,40>,<41,41>,<42,42>)";
		assertEquals("Expected toString output", expected, actual);
	}
	
	@Test
	public void toSequenceAndBack() throws Exception {
		Map<Integer,String> map = SortedMaps.of(42,"42",41,"41",40,"40");
		Map<Integer, String> actual = Maps.copyOf(map.sequence());
		assertEquals("Expected elements to be equal", map, actual);
	}
	
	@Test
	public void containsSuccess() throws Exception {
		Map<Integer,String> map = SortedMaps.of(42,"42",41,"41",40,"40");
		assertTrue("Expected map to contain value", map.contains(41));
	}
	
	@Test
	public void containsFailure() throws Exception {
		Map<Integer,String> map = SortedMaps.of(42,"42",41,"41",40,"40");
		assertFalse("Expected map not to contain value", map.contains(39));
	}
	
	@Test
	public void mapPredicateSuccess() throws Exception {
		Map<Integer,String> map = SortedMaps.of(42,"42",41,"41",40,"40");
		assertTrue("Expected map to contain value", map.matches(41));
	}
	
	@Test
	public void mapPredicateFailure() throws Exception {
		Map<Integer,String> map = SortedMaps.of(42,"42",41,"41",40,"40");
		assertFalse("Expected map not to contain value", map.matches(39));
	}
	
	@Test
	public void mapFunctionOnExistingKey() throws Exception {
		Map<Integer,String> map = SortedMaps.of(42,"42",41,"41",40,"40");
		Optional<String> expected = Optional.valueOf("41");
		Optional<String> actual = map.apply(41);
		assertEquals("Expected values to be equals", expected, actual);
	}
	
	@Test
	public void mapFunctionOnNonExistingKey() throws Exception {
		Map<Integer,String> map = SortedMaps.of(42,"42",41,"41",40,"40");
		Optional<String> expected = Optional.none();
		Optional<String> actual = map.apply(39);
		assertEquals("Expected values to be equals", expected, actual);
	}
	
	@Test
	public void isEmptyOnEmptyMap() throws Exception {
		Map<Integer,String> map = SortedMaps.empty();
		assertTrue("Expected map to be empty", map.isEmpty());
	}
	
	@Test
	public void isEmptyOnNonEmptyMap() throws Exception {
		Map<Integer,String> map = SortedMaps.of(42,"42",41,"41",40,"40");
		assertFalse("Expected map to be non-empty", map.isEmpty());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createMapThrowsOnNullKey() {
		SortedMaps.of(null, 1);
	}


	@Test(expected=IllegalArgumentException.class)
	public void createMapThrowsOnNullValue() {
		SortedMaps.of(1, null);
	}

	@Test
	public void getNonExistingKeyReturnsDefault() {
		Map<Integer, String> map = SortedMaps.of(2, "TWO");
		assertEquals("Expected default value", map.get(1, "ONE"), "ONE");
	}
	
	@Test
	public void mergeUpdatesMultipleValues() {
		Map<Integer,String> orig = SortedMaps.of(1, "1", 2, "2", 3, "3");
		Map<Integer,String> updates = SortedMaps.of(2, "TWO", 3, "THREE", 4, "FOUR");
		Map<Integer,String> expectedResult =
				Maps.of(1, "1", 2, "TWO", 3, "THREE", 4, "FOUR");
		assertEquals("Expected merged map",
				expectedResult, orig.merge(updates));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void updatesMapToMergeCannotBeNull() {
		SortedMaps.empty().merge(null);
	}
	
	@Test
	public void mergeExampleTest() {
		assertTrue("The example in the merge javadoc is wrong",
				SortedMaps.of(1,"1", 2,"2")
					.merge(SortedMaps.of(2,"TWO", 3,"3"))
					.equals(Maps.of(1,"1", 2,"TWO", 3,"3")));
	}
}
