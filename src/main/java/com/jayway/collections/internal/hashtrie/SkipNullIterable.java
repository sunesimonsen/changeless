package com.jayway.collections.internal.hashtrie;

import java.util.Iterator;

import com.jayway.collections.immutable.sequences.LazySequence;
import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.Sequences;
import com.jayway.collections.utilities.Guard;

class SkipNullIterable<T> extends LazySequence<T> {

	private final Iterator<T> source;

	public SkipNullIterable(Iterator<T> source) {
		Guard.notNull(source, "source");
		this.source = source;
	}
	
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
