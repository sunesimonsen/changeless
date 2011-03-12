package com.jayway.collections.functions;

public interface Fn<TInput,TReturn> {
	TReturn apply(TInput input);
}
