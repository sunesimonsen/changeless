package com.jayway.changeless.sequences;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import com.jayway.changeless.predicates.integers.EvenPredicate;

public class FilterOnIndexTests {
	@Test
	public void emptySequenceFiltersToAnEmptySequence() throws Exception {
		Sequence<Integer> emptySequence = Sequences.empty();
		assertThat(emptySequence.filterOnIndex(new EvenPredicate()), is(emptySequence));
	}
	
	@Test
	public void filteringWithEvenPredicateKeepsEvenIndexes() throws Exception {
		Sequence<String> sequence = Sequences.of("0","1","2","3","4","5","6");
		Sequence<String> evenIndexesSequence = Sequences.of("0","2","4","6");
		assertThat(sequence.filterOnIndex(new EvenPredicate()), is(evenIndexesSequence));
	}
}
