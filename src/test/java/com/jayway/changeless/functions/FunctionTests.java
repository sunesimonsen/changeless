package com.jayway.changeless.functions;

import org.junit.Test;
import static org.junit.Assert.*;


import com.jayway.changeless.predicates.integers.EvenPredicate;
import com.jayway.changeless.utilities.Comparables;

public class FunctionTests {
	@Test 
	public void functionCompositionTest() {
		Fn<Integer, String> f = Functions.compose(Functions.toStringFunction, Functions.from(new EvenPredicate()));
		assertEquals("Expected result", "false", f.apply(13));
	}
	
	@Test 
	public void function2CompositionTest() {
		Fn<Integer,Integer> square = new Fn<Integer, Integer>() {
			@Override
			public Integer apply(Integer input) {
				return input * input;
			}
		};
		
		Fn2<Integer,Integer,Integer> maxFunction = Comparables.maxFunction();
		Fn2<Integer, Integer, Integer> f = Functions.compose(maxFunction, square);
		assertEquals("Expected result", Integer.valueOf(25), f.apply(4,5));
	}
}
