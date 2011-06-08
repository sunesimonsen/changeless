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
		Sequence<T> next = sequence; 
		while(!next.isEmpty()){
			T first = next.first();
			if (predicate.apply(first)) {
				Sequence<T> rest = next.rest().filter(predicate);
				return Sequences.add(first, rest);
			}
			next = next.rest();
		}
		
		return next;
	}
}
