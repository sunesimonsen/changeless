package com.jayway.changeless;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.changeless.Optional;

public class OptionalTests {
	@Test(expected=NullPointerException.class)
	public void valueOfThrowOnNull() throws Exception {
		Optional.valueOf(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void getValueOnNonThrows() throws Exception {
		Optional.none().getValue();
	}
	
	@Test
	public void toStringOnNone() throws Exception {
		String actual = Optional.none().toString();
		String expected = "NONE";
		assertEquals("Expected toString on none", expected, actual);
	}
	
	@Test
	public void toStringOnGetValue() throws Exception {
		String actual = Optional.valueOf("Test").toString();
		String expected = "Test";
		assertEquals("Expected toString on optional value", expected, actual);
	}
}
