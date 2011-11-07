package com.jayway.changeless.sequences;

import com.jayway.changeless.predicates.Predicate;

public class TakeWhileSequence<T> extends SequenceSupport<T> {

	private final Sequence<T> sequence;
	private final Predicate<T> predicate;

	private TakeWhileSequence(Sequence<T> sequence, Predicate<T> predicate) {
		this.sequence = sequence;
		this.predicate = predicate;
	}

	public static <T> Sequence<T> create(Sequence<T> sequence, Predicate<T> predicate) {
		if (sequence.isEmpty()) {
			return sequence;
		}
		
		if (!predicate.apply(sequence.first())) {
			return Sequences.empty();
		}
		
		return new TakeWhileSequence<T>(sequence, predicate);
	}
	
	@Override
	public Sequence<T> rest() {
		return create(sequence.rest(), predicate);
	}

	@Override
	public T first() {
		return sequence.first();
	}

}
