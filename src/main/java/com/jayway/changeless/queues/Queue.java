package com.jayway.changeless.queues;

import java.util.NoSuchElementException;

import com.jayway.changeless.sequences.Sequenceable;

/**
 * <p>
 * An immutable collection designed for holding elements prior to processing.
 * You add elements to the queue and the implementation decides the order the
 * elements will be removed.
 * </p>
 * <p>
 * As this an immutable collection a new version of the data structure will be
 * returned for every operation the changes the data structure.
 * </p>
 * 
 * @param <T>
 *            the type of the elements in this queue.
 */
public interface Queue<T> extends Sequenceable<T> {
	/**
	 * Returns true if this queue is empty.
	 * 
	 * @return true if this queue is empty.
	 */
	public boolean isEmpty();

	/**
	 * Returns the number of elements in this queue.
	 * 
	 * @return the size of this queue.
	 */
	public Integer size();

	/**
	 * Returns a new queue with all the elements of this queue in addition to
	 * the given element.
	 * 
	 * @param element
	 *            the element to be added to this queue.
	 * @return the queue with the given element added.
	 */
	public Queue<T> add(T element);

	/**
	 * Returns a new queue with all the elements of this queue except the
	 * element at the front of the queue.
	 * 
	 * @return the queue with the front element removed.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public Queue<T> remove();

	/**
	 * Returns the element at the front of this queue.
	 * @return the element at the front of this queue.
	 * @throws NoSuchElementException if the queue is empty.
	 */
	public T peek();
}
