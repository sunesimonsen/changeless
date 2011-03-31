package com.jayway.changeless.immutable.sequences;

public interface Sequenceable<T> extends Iterable<T> {
	Sequence<T> sequence();
}
