package com.jayway.changeless.sequences;

import com.jayway.changeless.utilities.Guard;


final class DefaultSequence<T> extends SequenceSupport<T> {
	private final T first;
	private final Sequence<T> rest;
	
	private DefaultSequence(T first, Sequence<T> rest) {
		Guard.notNull(first,"first");
		Guard.notNull(rest, "rest");
		this.first = first;
		this.rest = rest;
	}
	
	@Override
	public final T first() {
		return first;
	}

	@Override
	public final Sequence<T> rest() {
		return rest;	
	}
	
	public static <T> Sequence<T> create(T first, Sequence<T> rest) {
		return new DefaultSequence<T>(first, rest);
	}
	
	@Override
	public final boolean isEmpty() {
		return false;
	}
}
