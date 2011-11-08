package com.jayway.changeless.sequences;

import com.jayway.changeless.sets.Set;
import com.jayway.changeless.sets.Sets;

public class DistinctSequence<T> extends SequenceSupport<T> {
	private final Sequence<T> sequence;
	private final Set<T> used;

	private DistinctSequence(Sequence<T> sequence, Set<T> used) {
		this.sequence = sequence;
		this.used = used;
	}
	
	public static <T> Sequence<T> create(Sequence<T> sequence) {
		Set<T> used = Sets.empty();
		return create(sequence, used);
	}
	
	public static <T> Sequence<T> create(Sequence<T> sequence, Set<T> used) {
		sequence = sequence.skipWhile(used);
		
		if (sequence.isEmpty()) {
			return sequence;
		}
		
		
		return new DistinctSequence<T>(sequence, used);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Sequence<T> rest() {
		Set<T> u = used.add(first());
		return create(sequence, u);
	}

	@Override
	public T first() {
		return sequence.first();
	}
}
