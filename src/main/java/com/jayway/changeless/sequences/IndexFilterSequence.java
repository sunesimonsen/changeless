package com.jayway.changeless.sequences;

import com.jayway.changeless.predicates.Predicate;

final class IndexFilterSequence<T> extends LazySequence<T> {

	private final Sequence<T> sequence;
	private final Predicate<? super Integer> predicate;
	private final int currentIndex;

	public IndexFilterSequence(Sequence<T> sequence, Predicate<? super Integer> predicate) {
		this(sequence, predicate, 0);
	}
	
	public IndexFilterSequence(Sequence<T> sequence, Predicate<? super Integer> predicate, int currentIndex) {
		this.sequence = sequence;
		this.predicate = predicate;
		this.currentIndex = currentIndex;
	}

	public static <R> Sequence<R> create(Sequence<R> sequence, Predicate<? super Integer> predicate) {
		return new IndexFilterSequence<R>(sequence, predicate);
	}
	
	@Override
	public Sequence<T> createSequence() {
		if (sequence.isEmpty()) {
			return sequence;
		}
		
		Sequence<T> rest = new IndexFilterSequence<T>(sequence.rest(), predicate, currentIndex + 1);
		if (predicate.matches(currentIndex)) {
			return sequence.withRest(rest);
		}
		
		return rest;
	}
}
