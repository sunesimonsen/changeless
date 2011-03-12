package dk.jayway.collections.immutable.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import dk.jayway.collections.utilities.Guard;


/**
 * A default implementation of {@link Iterator} that just walks the 
 * stream.
 * @param <T> The type of the elements in the stream to iterate. 
 */
final class DefaultSequenceIterator<T> implements Iterator<T> {
	private Sequence<T> stream;

	/**
	 * Creates a default implementation of {@link Iterator}.
	 * @param sequence The sequence to iterate over.
	 * @throws IllegalArgumentException is the given sequence is null.
	 */
	public static <T> Iterator<T> of(Sequence<T> stream) {
		return new DefaultSequenceIterator<T>(stream);
	}
	
	/**
	 * Creates a default implementation of {@link Iterator}.
	 * @param sequence The sequence to iterate over.
	 * @throws IllegalArgumentException is the given sequence is null.
	 */
	private DefaultSequenceIterator(Sequence<T> stream) {
		Guard.notNull(stream,"stream");
		this.stream = stream;
	}
	
	@Override
	public boolean hasNext() {
		return !stream.isEmpty();
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T nextElement = stream.first();
		stream = stream.rest();
		return nextElement;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Remove is not supported by this iterator.");
	}

}
