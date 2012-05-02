package com.jayway.changeless.stacks;


import java.util.EmptyStackException;
import java.util.Iterator;

import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.utilities.Guard;

final class SequenceStack<T> implements Stack<T> {
	private final Sequence<T> data;

	private SequenceStack(Sequence<T> data) {
		Guard.notNull(data, "data");
		this.data = data;
	}
	
	private SequenceStack() {
		data = Sequences.empty();
	}
	
	public static <T> Stack<T> create(Sequence<T> data) {
		return new SequenceStack<T>(data);
	}
	
	public static <T> Stack<T> create() {
		return new SequenceStack<T>();
	}

	@Override
	public int size() {
		return data.size();
	}
	
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public Stack<T> push(T element) {
		return create(data.add(element));
	}

	@Override
	public Stack<T> pop() {
		ensureNotEmpty();
		return create(data.rest());
	}

	@Override
	public T peek() {
		ensureNotEmpty();
		return data.first();
	}

	private void ensureNotEmpty() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
	}
	
	@Override
	public String toString() {
		return data.toString();
	}

	@Override
	public int hashCode() {
		return data.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Stack other = (Stack) obj;
		return sequence().equals(other.sequence());
	}

	@Override
	public Sequence<T> sequence() {
		return data.sequence();
	}

	@Override
	public Iterator<T> iterator() {
		return data.iterator();
	}
}
