package com.jayway.changeless.queues;

import com.jayway.changeless.sequences.Sequenceable;

/**
 * An utility class for working with priority queues.
 * The priority queues created by this class will 
 * prioritize largest elements compared to smaller 
 * according to the {@link Comparable} interface.
 */
public final class PriorityQueues {
	private PriorityQueues() { /* static class */ }

	/**
	 * Creates an empty priority queue.
	 * @return an empty priority queue.
	 */
	public static <T extends Comparable<T>> Queue<T> empty() {
		return SortedSetPriorityQueue.empty();
	}

	/**
	 * Creates a priority queue with the given elements.
	 * @param elements the elements of the queue.
	 * @return a priority queue with the given elements.
	 */
	public static <T extends Comparable<T>> Queue<T> of(T... elements) {
		Queue<T> result = empty();
		for (T element : elements) {
			result = result.add(element);
		}
		return result;
	}
	
	/**
	 * Creates a priority queue with the given elements.
	 * @param elements the elements of the queue.
	 * @return a priority queue with the given elements.
	 */
	public static <T extends Comparable<T>> Queue<T> of(Sequenceable<T> elements) {
		Queue<T> result = empty();
		for (T element : elements) {
			result = result.add(element);
		}
		return result;
	}

}
