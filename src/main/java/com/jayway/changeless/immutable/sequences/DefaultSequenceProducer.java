package com.jayway.changeless.immutable.sequences;

import com.jayway.changeless.Optional;
import com.jayway.changeless.functions.Fn;


public final class DefaultSequenceProducer<T> extends LazySequence<T> {

	private final T first;
	private final Fn<? super T, ? extends Optional<T>> producer;

	public DefaultSequenceProducer(T current, Fn<? super T, ? extends Optional<T>> producer) {
		this.first = current;
		this.producer = producer;
	}

	public static <T> Sequence<T> create(T current, Fn<? super T, ? extends Optional<T>> producer) {
		return new DefaultSequenceProducer<T>(current, producer);
	}

	@Override
	public Sequence<T> createSequence() {
		Optional<T> nextElement = producer.apply(first);
		Sequence<T> rest;
		if (nextElement.hasValue()) {
			rest = DefaultSequenceProducer.create(nextElement.getValue(), producer);
		} else {
			rest = Sequences.empty();
		}
		return Sequences.append(first, rest);
	}
}
