package dk.jayway.collections.immutable.sequences;

import dk.jayway.collections.utilities.Guard;


public final class IntegerStreamBuilder {

	private final int from;
	private int step = 1;

	public IntegerStreamBuilder(int from) {
		this.from = from;
	}
	
	public IntegerStreamBuilder step(int step) {
		Guard.nonNegative(step, "step");
		this.step = step;
		return this;
	}
	
	public Sequence<Integer> upward() {
		return new IntegerStream(from, step);
	}
	
	public Sequence<Integer> downward() {
		return new IntegerStream(from, -step);
	}
}
