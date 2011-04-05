package com.jayway.changeless.sequences;


final class TakeSequence<T> extends SequenceSupport<T> {
	private final int n;
	private final Sequence<T> sequence;

	private TakeSequence(Sequence<T> sequence, int n) {
		this.sequence = sequence;
		this.n = n;
	}
	
	@Override
	public T first() {
		return sequence.first();
	}

	public static <T> Sequence<T> create(Sequence<T> sequence, int n) {
		if (n <= 0 || sequence.isEmpty()) {
			return Sequences.empty();
		}
		
		return new TakeSequence<T>(sequence, n);
	}
	
	@Override
	public Sequence<T> rest() {
		return TakeSequence.create(sequence.rest(), n-1);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
