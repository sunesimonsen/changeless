package dk.jayway.collections.immutable.sequences;

import dk.jayway.collections.Optional;
import dk.jayway.collections.functions.Fn;

public class DefaultSequenceBuilder<T> {
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