package com.jayway.changeless.sequences;

import com.jayway.changeless.utilities.Guard;

final class PartitionSequence<T> extends LazySequence<Sequence<T>> {
	private final int n;
	private final Sequence<T> sequence;

	private PartitionSequence(Sequence<T> sequence, int n) {
		this.sequence = sequence;
		this.n = n;
	}

	public static <T> Sequence<Sequence<T>> create(Sequence<T> sequence, int n) {
		Guard.nonNegative(n, "n");
		if (sequence.isEmpty()) {
			return Sequences.empty();
		}
		
		return new PartitionSequence<T>(sequence, n);
	}

	@Override
	public Sequence<Sequence<T>> createSequence() {
		Sequence<T> first = sequence.take(n);
		return Sequences.append(first, sequence.skip(n).partition(n));
	}
}
