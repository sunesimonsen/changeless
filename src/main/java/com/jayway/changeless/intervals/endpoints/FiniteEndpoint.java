package com.jayway.changeless.intervals.endpoints;

abstract class FiniteEndpoint<T> {
	private final T value;

	public FiniteEndpoint(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
