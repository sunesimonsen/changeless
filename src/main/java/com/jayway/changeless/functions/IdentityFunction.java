package com.jayway.changeless.functions;

public class IdentityFunction<T> implements Fn<T, T> {
	@Override
	public T apply(T input) {
		return input;
	}
}
