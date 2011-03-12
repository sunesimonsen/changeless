package dk.jayway.collections.immutable.sequences;

import dk.jayway.collections.Optional;
import dk.jayway.collections.functions.Fn;

public class DefaultStreamBuilder<T> {
	private T current;
	
	public DefaultStreamBuilder(T from) {
		current = from;
	}

	public static <T> DefaultStreamBuilder<T> from(T from) {
		return new DefaultStreamBuilder<T>(from);
	}
	
	public Sequence<T> producer(Fn<? super T, ? extends Optional<T>> producer) {
		return DefaultStreamProducer.create(current, producer);
	}
}