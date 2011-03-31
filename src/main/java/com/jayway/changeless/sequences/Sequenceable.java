package com.jayway.changeless.sequences;

public interface Sequenceable<T> extends Iterable<T> {
	Sequence<T> sequence();
}
