package com.jayway.collections.functions;

public interface Fn<T,R> {
	R apply(T input);
}
