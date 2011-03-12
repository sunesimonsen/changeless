package dk.jayway.collections.immutable.sequences;



class IntegerStream extends SequenceSupport<Integer> {

	private final int from;
	private final int step;

	IntegerStream(int from, int step) {
		this.from = from;
		this.step = step;
	}

	@Override
	public Integer first() {
		return from;
	}

	@Override
	public Sequence<Integer> rest() {
		return new IntegerStream(from + step, step);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
