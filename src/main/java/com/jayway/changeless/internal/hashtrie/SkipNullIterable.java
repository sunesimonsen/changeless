package com.jayway.changeless.internal.hashtrie;

import java.util.Iterator;

import com.jayway.changeless.immutable.sequences.LazySequence;
import com.jayway.changeless.immutable.sequences.Sequence;
import com.jayway.changeless.immutable.sequences.Sequences;
import com.jayway.changeless.utilities.Guard;

final class SkipNullIterable<T> extends LazySequence<T> {

	private final Iterator<T> source;

	public SkipNullIterable(Iterator<T> source) {
		Guard.notNull(source, "source");
		this.source = source;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Sequence<T> createSequence() {
		if (!source.hasNext()) {
			return Sequences.empty();
		}
		T first = source.next();
		while (first == null && source.hasNext()) {
			first = source.next();
		}
		if (first == null) {
			return Sequences.empty();
		}
		Sequence<T> rest = new SkipNullIterable<T>(source);
		return rest.add(first);
	}
}
