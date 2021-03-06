package com.jayway.changeless.sequences;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import com.jayway.changeless.predicates.integers.EvenPredicate;
import com.jayway.changeless.functions.Functions;

public class InfiniteSequenceTest {
    private Sequence<Integer> subject = Sequences.from(-10).upward();

	@Test
    public void filter_should_return_a_filtered_sequence() throws Exception {
		Sequence<Integer> actual = subject.filter(new EvenPredicate()).take(5);
		Sequence<Integer> expected = Sequences.of(-10,-8,-6,-4,-2);
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test
	public void transform_should_return_a_transformed_sequence() throws Exception {
		Sequence<String> actual = subject.transform(Functions.toStringFunction).take(3);
		Sequence<String> expected = Sequences.of("-10", "-9", "-8");
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test
	public void get_should_return_the_specified_element() throws Exception {
        int actual = subject.get(3);
        int expected = -7;
		assertEquals("Expected sequences to be equals",expected, actual);
	}
	
	@Test
    public void partition_should_return_a_partitioned_sequence() throws Exception {
		Sequence<Sequence<Integer>> actual = subject.partition(3).take(4);
		@SuppressWarnings("unchecked")
		Sequence<Sequence<Integer>> expected = 
			Sequences.of(Sequences.of(-10,-9,-8),
                         Sequences.of(-7,-6,-5),
                         Sequences.of(-4,-3,-2),
                         Sequences.of(-1,0,1));
		
		assertEquals("Expected sequences to be equal", expected, actual);
	}
	
	@Test
	public void insertAt_should_return_sequence_with_elements_inserted() throws Exception {
		Sequence<Integer> actual = subject.insertAt(5, 0, 0).take(10);
		Sequence<Integer> expected = Sequences.of(-10,-9,-8,-7,-6,0,0,-5,-4,-3);
		assertEquals("Expected sequences to be  equal", expected, actual);
	}
}