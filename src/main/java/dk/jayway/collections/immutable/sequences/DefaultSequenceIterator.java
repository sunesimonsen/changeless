package dk.jayway.collections.immutable.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import dk.jayway.collections.utilities.Guard;


/**
 * A default implementation of {@link Iterator} that just walks the 
 * seqeunces.
 * @param <T> The type of the elements in the sequence to iterate. 
 */
final class DefaultSequenceIterator<T> implements Iterator<T> {
	private Sequence<T> sequence;

	/**
	 * Creates a default implementation of {@link Iterator}.
	 * @param sequence The sequence to iterate over.
	 * @throws IllegalArgumentException is the given sequence is null.
	 */
	public static <T> Iterator<T> of(Sequence<T> sequence) {
		return new DefaultSequenceIterator<T>(sequence);
	}
	
	/**
	 * Creates a default implementation of {@link Iterator}.
	 * @param sequence The sequence to iterate over.
	 * @throws IllegalArgumentException is the given sequence is null.
	 */
	private DefaultSequenceIterator(Sequence<T> sequence) {
		Guard.notNull(sequence,"sequence");
		this.sequence = sequence;
	}
	
	@Override
	public boolean hasNext() {
		return !sequence.isEmpty();
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		T nextElement = sequence.first();
		sequence = sequence.rest();
		return nextElement;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Remove is not supported by this iterator.");
	}

}
