package com.jayway.collections.immutable.sequences;

import com.jayway.collections.utilities.Guard;


public final class IntegerSequenceBuilder {

	private final int from;
	private int step = 1;

	public IntegerSequenceBuilder(int from) {
		this.from = from;
	}
	
	public IntegerSequenceBuilder step(int step) {
		Guard.nonNegative(step, "step");
		this.step = step;
		return this;
	}
	
	public Sequence<Integer> upward() {
		return new IntegerSequence(from, step);
	}
	
	public Sequence<Integer> downward() {
		return new IntegerSequence(from, -step);
	}
}
