package com.jayway.changeless.sequences;

import com.jayway.changeless.functions.Fn2;

final class IndexedTransformedSequence<T, R> extends LazySequence<R> {

	private final Sequence<T> sequence;
	private final Fn2<Integer, ? super T,? extends R> function;
	private final int index;
	
	protected IndexedTransformedSequence(Sequence<T> sequence, Fn2<Integer, ? super T,? extends R> function, int index) {
		this.sequence = sequence;
		this.function = function;
		this.index = index;
	}
	
	public static <T,R> Sequence<R> create(Sequence<T> sequence,Fn2<Integer, ? super T,? extends R> function) {
		return create(sequence, function, 0);
	}
	
	private static <T,R> Sequence<R> create(Sequence<T> sequence,Fn2<Integer, ? super T,? extends R> function, int index) {
		if (sequence.isEmpty()) {
			return Sequences.empty();
		}
		
		return new IndexedTransformedSequence<T,R>(sequence, function, index);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Sequence<R> createSequence() {
		R first = function.apply(index, sequence.first());
		
		Sequence<R> rest = IndexedTransformedSequence.create(
				sequence.rest(), function, index+1);
		return Sequences.add(first, rest);
	}
}
