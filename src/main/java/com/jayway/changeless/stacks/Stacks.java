package com.jayway.changeless.stacks;

import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;

public final class Stacks {
	private Stacks() {
		// static class
	}
	
	/**
	 * Creates an empty stack.
	 * @param <T> the type of the elements in the stack.
	 * @return an empty stack.
	 */
	public static <T> Stack<T> empty() {
		return SequenceStack.create();
	}

	/**
	 * Create a new stack consisting of the given elements.
	 * @param <T> the type of the elements in the stack.
	 * @param elements the elements of the stack to create.
	 * @return a new stack consisting of the given elements.
	 */
	public static <T> Stack<T> of(T... elements) {
		Sequence<T> data = Sequences.copyOf(elements);
		return SequenceStack.create(data);
	}
	
	/**
	 * Creates a new stack containing the elements of given {@link Iterable}.
	 * @param <T> the type of the elements in the stack.
	 * @param elements the elements of the stack to create.
	 * @return a new stack consisting of the given elements.
	 */
	public static <T> Stack<T> copyOf(Iterable<T> elements) {
		Sequence<T> data = Sequences.copyOf(elements);
		return SequenceStack.create(data);
	}
	
	/**
	 * Creates a new stack containing the elements of given array.
	 * @param <T> the type of the elements in the stack.
	 * @param elements the elements of the stack to create.
	 * @return a new stack consisting of the given elements.
	 */
	public static <T> Stack<T> copyOf(T[] elements) {
		Sequence<T> data = Sequences.copyOf(elements);
		return SequenceStack.create(data);
	}
	
}
