package com.jayway.changeless.queues;

public final class PriorityQueues {
	private PriorityQueues() { /* static class */ }

	/**
	 * Creates an empty priority queue.
	 * @return an empty priority queue.
	 */
	public static <T extends Comparable<T>> Queue<T> empty() {
		return SortedSetPriorityQueue.empty();
	}

}
