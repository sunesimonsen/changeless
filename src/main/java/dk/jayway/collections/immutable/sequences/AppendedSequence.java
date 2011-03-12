package dk.jayway.collections.immutable.sequences;

class AppendedSequence<T> extends SequenceSupport<T> {

	private final Sequence<T> startSequence;
	private final Sequence<T> endSequence;

	private AppendedSequence(Sequence<T> startSequence,
			Sequence<T> endSequence) {
				this.startSequence = startSequence;
				this.endSequence = endSequence;
	}
	
	public static <T> Sequence<T> create(Sequence<T> startSequence,
			Sequence<T> endSequence) {
		if (startSequence.isEmpty()) {
			return endSequence;
		}
		
		return new AppendedSequence<T>(startSequence, endSequence);
	}

	@Override
	public Sequence<T> rest() {
		return create(startSequence.rest(), endSequence);
	}

	@Override
	public T first() {
		return startSequence.first();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
