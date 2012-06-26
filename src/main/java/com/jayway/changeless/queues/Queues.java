package com.jayway.changeless.queues;

import com.jayway.changeless.sequences.Sequences;

public class Queues {

	/**
	 * Create an empty queue
	 * @return the empty queue.
	 */
	public static <T> Queue<T> empty() {
		return FirstInFirstOutQueue.empty();
	}

	/**
	 * Creates a new queue with the given elements added to the queue in the order they are provided.
	 * @param elements the elements of the queue.
	 * @return the new queue with the given elements.
	 */
	public static <T> Queue<T> of(T... elements) {
		return FirstInFirstOutQueue.create(Sequences.of(elements));
	}

}
