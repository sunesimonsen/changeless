package com.jayway.changeless.functions;

public interface Fn<T,R> {
	R apply(T input);
}
