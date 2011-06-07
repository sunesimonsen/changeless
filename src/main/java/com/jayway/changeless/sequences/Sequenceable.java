package com.jayway.changeless.sequences;

/**
 * An interface for objects that can be turned into a sequence.
 * @param <T> the type of the elements in the sequence.
 */
public interface Sequenceable<T> extends Iterable<T> {
	/**
	 * Returns a sequence for this object.
	 * @return a sequence for this object.
	 */
	Sequence<T> sequence();
}
