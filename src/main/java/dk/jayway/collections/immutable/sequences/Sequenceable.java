package dk.jayway.collections.immutable.sequences;

public interface Sequenceable<T> extends Iterable<T> {
	Sequence<T> sequence();
}
