package dk.jayway.collections.immutable.sequences;

import dk.jayway.collections.predicates.Predicate;

public class FilterSequence<T> extends LazySequence<T> {

	private final Sequence<T> stream;
	private final Predicate<? super T> predicate;

	public FilterSequence(Sequence<T> stream, Predicate<? super T> predicate) {
		this.stream = stream;
		this.predicate = predicate;
	}

	public static <R> Sequence<R> create(Sequence<R> stream, Predicate<? super R> predicate) {
		return new FilterSequence<R>(stream, predicate);
	}
	
	@Override
	public Sequence<T> createStream() {
		if (stream.isEmpty()) {
			return Sequences.empty();
		}
		T first = stream.first();
		Sequence<T> rest = stream.rest().filter(predicate);
		if (predicate.apply(first)) {
			return DefaultSequence.create(first, rest);
		}
		return rest;
	}
}
