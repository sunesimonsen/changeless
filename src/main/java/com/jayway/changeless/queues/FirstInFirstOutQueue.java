package com.jayway.changeless.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;

public final class FirstInFirstOutQueue<T> implements Queue<T> {
	private final Sequence<T> in;
	private final Sequence<T> out;

	private FirstInFirstOutQueue() {
		this.in = Sequences.empty();
		this.out = Sequences.empty();
	}
	
	private FirstInFirstOutQueue(Sequence<T> in, Sequence<T> out) {
		this.in = in;
		this.out = out;
	}

	public static <T> Queue<T> empty() {
		return new FirstInFirstOutQueue<T>();
	}
	
	static <T> Queue<T> create(Sequence<T> in, Sequence<T> out) {
		if (out.isEmpty()) {
			Sequence<T> empty = Sequences.empty();
			return new FirstInFirstOutQueue<T>(empty, in.reverse());
		}
		return new FirstInFirstOutQueue<T>(in, out);
	}
	
	static <T> Queue<T> create(Sequence<T> elements) {
		Sequence<T> empty = Sequences.empty();
		return create(empty, elements);
	}

	@Override
	public boolean isEmpty() {
		return in.isEmpty() && out.isEmpty();
	}

	@Override
	public Integer size() {
		return in.size() + out.size();
	}

	@Override
	public Queue<T> add(T element) {
		return FirstInFirstOutQueue.create(in.add(element), out);
	}

	@Override
	public Queue<T> remove() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't remove elements from an empty queue");
		}
		
		return create(in, out.rest());
	}

	@Override
	public T peek() {
		if (isEmpty()) {
			throw new NoSuchElementException("Can't peek on an empty queue");
		}
		
		return out.first();
	}

	@Override
	public Sequence<T> sequence() {
		return out.append(in.reverse());
	}

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}

	@Override
	public Optional<T> poll() {
		if (isEmpty()) {
			return Optional.none();
		}
		return Optional.valueOf(out.first());
	}

	@Override
	public int hashCode() {
		return sequence().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return sequence().equals(obj);
	}
	
	@Override
	public String toString() {
		return sequence().toString();
	}
}
