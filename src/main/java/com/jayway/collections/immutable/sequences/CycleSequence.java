package com.jayway.collections.immutable.sequences;

final class CycleSequence<T> extends LazySequence<T> {
	private final Sequence<T> current;
	private final Sequence<T> original;

	private CycleSequence(Sequence<T> original, Sequence<T> current) {
		this.original = original;
		this.current = current;
	}

	public static <T> Sequence<T> create(Sequence<T> sequence) {
		if (sequence.isEmpty()) {
			return sequence;
		}
		return new CycleSequence<T>(sequence,sequence);
	}
	
	@Override
	public Sequence<T> createSequence() {
		T first = current.first();
		Sequence<T> rest = current.rest();
		if (rest.isEmpty()) {
			rest = original;
		}
		return Sequences.append(first, new CycleSequence<T>(original, rest));
	}
}
