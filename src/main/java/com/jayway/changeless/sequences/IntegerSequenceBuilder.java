package com.jayway.changeless.sequences;

import com.jayway.changeless.utilities.Guard;

/**
 * <p>
 * A sequence builder for building infinite integer sequences.
 * </p>
 * <p>
 * Notice this class is not thread safe.
 * </p>
 */
public final class IntegerSequenceBuilder {

	private final int from;
	private int step = 1;

	/**
	 * Creates a new integer sequence builder that is start with 
	 * the given value.
	 * @param from the starting point of the integer sequence.
	 */
	IntegerSequenceBuilder(int from) {
		this.from = from;
	}
	
	/**
	 * Specifies the step between numbers in the integer sequence 
	 * that is being build. 
	 * @param step the step between numbers in the integer sequence 
	 * that is being build.
	 * @return this sequence builder for method chaining.
	 */
	public IntegerSequenceBuilder step(int step) {
		Guard.nonNegative(step, "step");
		this.step = step;
		return this;
	}
	
	/**
	 * Produces the integer sequence with the starting point set 
	 * on this builder and upward to infinity with the specified 
	 * step between the numbers.
	 * @return the produced sequence.
	 */
	public Sequence<Integer> upward() {
		return new IntegerSequence(from, step);
	}
	
	/**
	 * Produces the integer sequence with the starting point set 
	 * on this builder and downward to negative infinity with the specified 
	 * step between the numbers.
	 * @return the produced sequence.
	 */
	public Sequence<Integer> downward() {
		return new IntegerSequence(from, -step);
	}
}
