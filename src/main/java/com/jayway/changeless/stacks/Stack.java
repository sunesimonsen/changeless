package com.jayway.changeless.stacks;

import java.util.EmptyStackException;

import com.jayway.changeless.sequences.Sequenceable;

public interface Stack<T> extends Sequenceable<T> {
	/**
	 * Returns the number of elements on this stack.
	 * @return the number of elements on this stack.
	 */
	int size();

	/**
	 * Returns true if this stack is empty; false otherwise. 
	 * @return true if this stack is empty; false otherwise. 
	 */
	boolean isEmpty();

	/**
	 * Returns a new stack where the given element is pushed onto the top of the stack.
	 * @param element the element to be pushed onto the stack.
	 * @return a new stack where the given element is pushed onto the top of the stack.
	 */
	Stack<T> push(T element);

	/**
	 * Returns a new stack with the top removed.
	 * @return a new stack with the top removed.
	 * @throws EmptyStackException if the stack is empty.
	 */
	Stack<T> pop();

	/**
	 * Returns the top element of this stack.
	 * @return the top element of this stack.
 	 * @throws EmptyStackException if the stack is empty.
	 */
	T peek();
}
