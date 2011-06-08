package com.jayway.changeless.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.jayway.changeless.utilities.Guard;


/**
 * A default implementation of {@link Iterator} that just walks the 
 * sequences.
 * @param <T> The type of the elements in the sequence to iterate. 
 */
final class DefaultSequenceIterator<T> implements Iterator<T> {
	private Sequence<T> sequence;

	/**
	 * Creates a default implementation of {@link Iterator}.
	 * @param <T> the element type of the sequence.
	 * @param sequence The sequence to iterate over.
	 * @return the iterator for the given sequence.
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
