package com.jayway.changeless.sequences;

import com.jayway.changeless.predicates.Predicate;

final class FilterSequence<T> extends LazySequence<T> {

	private final Sequence<T> sequence;
	private final Predicate<? super T> predicate;

	public FilterSequence(Sequence<T> sequence, Predicate<? super T> predicate) {
		this.sequence = sequence;
		this.predicate = predicate;
	}

	public static <R> Sequence<R> create(Sequence<R> sequence, Predicate<? super R> predicate) {
		return new FilterSequence<R>(sequence, predicate);
	}
	
	@Override
	public Sequence<T> createSequence() {
		if (sequence.isEmpty()) {
			return Sequences.empty();
		}
		T first = sequence.first();
		Sequence<T> rest = sequence.rest().filter(predicate);
		if (predicate.apply(first)) {
			return Sequences.append(first, rest);
		}
		return rest;
	}
}
