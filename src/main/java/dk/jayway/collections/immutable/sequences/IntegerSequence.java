package dk.jayway.collections.immutable.sequences;



class IntegerSequence extends SequenceSupport<Integer> {

	private final int from;
	private final int step;

	IntegerSequence(int from, int step) {
		this.from = from;
		this.step = step;
	}

	@Override
	public Integer first() {
		return from;
	}

	@Override
	public Sequence<Integer> rest() {
		return new IntegerSequence(from + step, step);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
