package com.jayway.changeless.sequences;

import com.jayway.changeless.functions.Fn;

final class TransformedSequence<T, R> extends LazySequence<R> {

	private final Sequence<T> sequence;
	private final Fn<? super T, ? extends R> function;
	
	protected TransformedSequence(Sequence<T> sequence, Fn<? super T, ? extends R> function) {
		this.sequence = sequence;
		this.function = function;
	}
	
	public static <T,R> Sequence<R> create(Sequence<T> sequence, Fn<? super T, ? extends R> function) {
		if (sequence.isEmpty()) {
			return Sequences.empty();
		}
		
		return new TransformedSequence<T,R>(sequence, function);
	}
	

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Sequence<R> createSequence() {
		R first = function.apply(sequence.first());
		Sequence<R> rest = sequence.rest().transform(function);
		return Sequences.add(first, rest);
	}
}
