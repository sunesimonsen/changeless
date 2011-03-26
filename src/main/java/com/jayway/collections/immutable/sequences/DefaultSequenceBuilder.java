package com.jayway.collections.immutable.sequences;

import com.jayway.collections.Optional;
import com.jayway.collections.functions.Fn;


public final class DefaultSequenceBuilder<T> {
	private T current;
	
	public DefaultSequenceBuilder(T from) {
		current = from;
	}

	public static <T> DefaultSequenceBuilder<T> from(T from) {
		return new DefaultSequenceBuilder<T>(from);
	}
	
	public Sequence<T> producer(Fn<? super T, ? extends Optional<T>> producer) {
		return DefaultSequenceProducer.create(current, producer);
	}
}