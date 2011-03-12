package dk.jayway.collections.immutable.sequences;


class InterposeSequence<T> extends LazySequence<T> {

	private final SequenceSupport<T> source;
	private final T separator;

	public InterposeSequence(SequenceSupport<T> source, T separator) {
		this.source = source;
		this.separator = separator;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Sequence<T> createStream() {
		if (source.isEmpty()) {
			return source;
		}
		
		Sequence<T> rest = source.rest();
		if (rest.isEmpty()) {
			return source;
		}
		
		T first = source.first();
		return rest.interpose(separator).addAll(first, separator);
	}
}
