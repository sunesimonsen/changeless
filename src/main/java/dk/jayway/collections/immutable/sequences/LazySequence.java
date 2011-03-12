package dk.jayway.collections.immutable.sequences;


public abstract class LazySequence<T> extends SequenceSupport<T> {

	private Sequence<T> cachedValue;
	
	public abstract Sequence<T> createStream();
	
	@Override
	public T first() {
		return getCachedValue().first();
	}

	@Override
	public Sequence<T> rest() {
		return getCachedValue().rest();
	}

	@Override
	public boolean isEmpty() {
		return getCachedValue().isEmpty();
	}

	public Sequence<T> getCachedValue() {
		if (cachedValue == null) {
			cachedValue = createStream();
		}
		return cachedValue;
	}
}
