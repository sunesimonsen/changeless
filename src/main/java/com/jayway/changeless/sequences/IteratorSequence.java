package com.jayway.changeless.sequences;

import java.util.Iterator;

import com.jayway.changeless.utilities.Guard;


final class IteratorSequence<T> extends LazySequence<T> {

	private final Iterator<T> source;

	public IteratorSequence(Iterator<T> source) {
		Guard.notNull(source, "source");
		this.source = source;
	}
	
	@Override
	public Sequence<T> createSequence() {
		if (!source.hasNext()) {
			return Sequences.empty();
		}
		T first = source.next();
		Sequence<T> rest = new IteratorSequence<T>(source);
		return Sequences.append(first, rest);
	}
	
	@Override
	public boolean isEmpty() {
		return !source.hasNext();
	}
}
