package com.jayway.collections.immutable.intervals.endpoints;

public abstract class FiniteEndpoint<T> {
	private final T value;

	public FiniteEndpoint(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
