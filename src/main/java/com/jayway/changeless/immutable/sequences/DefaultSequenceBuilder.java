package com.jayway.changeless.immutable.sequences;

import com.jayway.changeless.Optional;
import com.jayway.changeless.functions.Fn;


public final class DefaultSequenceBuilder<T> {
	private T current;
	
	public DefaultSequenceBuilder(T from) {
		current = from;
	}

	public static <T> DefaultSequenceBuilder<T> from(T from) {
		return new DefaultSequenceBuilder<T>(from);
	}
	
	public Sequence<T> produce(Fn<? super T, ? extends Optional<T>> producer) {
		return DefaultSequenceProducer.create(current, producer);
	}
}