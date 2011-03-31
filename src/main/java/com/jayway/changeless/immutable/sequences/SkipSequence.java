package com.jayway.changeless.immutable.sequences;


final class SkipSequence<T> extends LazySequence<T>{

	private final Sequence<T> sequence;
	private final int n;

	private SkipSequence(Sequence<T> sequence, int n) {
		this.sequence = sequence;
		this.n = n;
	}
	
	public static <R> Sequence<R> create(Sequence<R> sequence, int n) {
		if (n <= 0 || sequence.isEmpty()) {
			return sequence;
		} 

		return new SkipSequence<R>(sequence, n);
	}
	
	@Override
	public Sequence<T> createSequence() {
		return SkipSequence.create(sequence.rest(), n-1);
	}
}
