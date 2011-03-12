package dk.jayway.collections.immutable.sequences;

import java.util.Iterator;

import dk.jayway.collections.utilities.Guard;

public class IteratorSequence<T> extends LazySequence<T> {

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
		return DefaultSequence.create(first, rest);
	}
}
