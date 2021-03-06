package com.jayway.changeless.sequences;


final class InterposeSequence<T> extends LazySequence<T> {

	private final SequenceSupport<T> source;
	private final T separator;

	public InterposeSequence(SequenceSupport<T> source, T separator) {
		this.source = source;
		this.separator = separator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Sequence<T> createSequence() {
		if (source.isEmpty()) {
			return source;
		}
		
		Sequence<T> rest = source.rest();
		if (rest.isEmpty()) {
			return source;
		}
		
		T first = source.first();
		return rest.interpose(separator).add(first, separator);
	}
}
