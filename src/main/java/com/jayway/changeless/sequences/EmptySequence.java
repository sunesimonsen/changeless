package com.jayway.changeless.sequences;

final class EmptySequence<T> extends SequenceSupport<T> {

	private EmptySequence() {  /* Static class */ }

	@Override
	public T first() {
		throw new IllegalStateException("You can't call first() on an empty sequence");
	}
	
	@Override
	public Sequence<T> rest() {
		throw new IllegalStateException("You can't call rest() on an empty sequence");
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	public static <T> Sequence<T> create() {
		return new EmptySequence<T>();
	}

}
