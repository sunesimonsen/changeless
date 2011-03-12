package dk.jayway.collections.immutable.sequences;

import dk.jayway.collections.Optional;
import dk.jayway.collections.functions.Fn;

public class DefaultStreamProducer<T> extends LazySequence<T> {

	private final T first;
	private final Fn<? super T, ? extends Optional<T>> producer;

	public DefaultStreamProducer(T current, Fn<? super T, ? extends Optional<T>> producer) {
		this.first = current;
		this.producer = producer;
	}

	public static <T> Sequence<T> create(T current, Fn<? super T, ? extends Optional<T>> producer) {
		return new DefaultStreamProducer<T>(current, producer);
	}

	@Override
	public Sequence<T> createStream() {
		Optional<T> nextElement = producer.apply(first);
		Sequence<T> rest;
		if (nextElement.hasValue()) {
			rest = DefaultStreamProducer.create(nextElement.getValue(), producer);
		} else {
			rest = Sequences.empty();
		}
		return DefaultSequence.create(first, rest);
	}
}
