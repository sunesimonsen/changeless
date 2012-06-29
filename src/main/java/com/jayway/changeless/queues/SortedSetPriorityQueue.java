package com.jayway.changeless.queues;

import java.util.NoSuchElementException;

import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sets.SortedSet;
import com.jayway.changeless.sets.SortedSets;

final class SortedSetPriorityQueue<T extends Comparable<T>> extends QueueSupport<T> {

	private final SortedSet<T> elements;

	private SortedSetPriorityQueue(SortedSet<T> elements) {
		this.elements = elements;
	}
	
	private static <T extends Comparable<T>> Queue<T> create(SortedSet<T> elements) {
		return new SortedSetPriorityQueue<T>(elements);
	}

	public static <T extends Comparable<T>> Queue<T> empty() {
		SortedSet<T> elements = SortedSets.empty();
		return new SortedSetPriorityQueue<T>(elements);
	}

	@Override
	public Sequence<T> sequence() {
		return elements.sequence();
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public Integer size() {
		return elements.size();
	}

	@Override
	public Queue<T> add(T element) {
		return create(elements.add(element));
	}

	@Override
	public Queue<T> remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't remove element from empty queue");
		}
		return create(elements.removeMax());
	}

	@Override
	public T peek() {
		return elements.max();
	}
}
