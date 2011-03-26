package com.jayway.collections.immutable.sequences;

import com.jayway.collections.functions.Fn2;
import com.jayway.collections.functions.Functions;




public final class Sequences {
	private Sequences() { }
	
	public static <T> Sequence<T> append(T element, Sequence<T> sequence) {
		return DefaultSequence.create(element, sequence);
	}
	
	public static <T> Sequence<T> of(T... elements) {
		return ArraySequence.of(elements);
	}
	
	public static <T> Sequence<T> empty() {
		return EmptySequence.create();
	}
	
	public static <T> Sequence<T> copyOf(Iterable<T> elements) {
		return ArraySequence.copyOf(elements);
	}
	
	public static <T> Sequence<T> lazyCopyOf(Iterable<T> elements) {
		return new IteratorSequence<T>(elements.iterator());
	}
	
	public static <T> Sequence<T> appended(Iterable<? extends Sequenceable<T>> sequences) {
		Sequence<T> appendedSequence = empty();
		for (Sequenceable<T> sequenceable : sequences) {
			appendedSequence = appendedSequence.append(sequenceable);
		}
		return appendedSequence;
	}
	
	public static IntegerSequenceBuilder from(int from) {
		return new IntegerSequenceBuilder(from);
	}
	
	public static <T> DefaultSequenceBuilder<T> from(T from) {
		return DefaultSequenceBuilder.from(from);
	}
	
	public static <T extends Comparable<T>> T min(Sequence<T> sequence) {
		if (sequence.isEmpty()) {
			throw new IllegalArgumentException("min only work on non-empty sequences");
		}
		Fn2<T, T, T> minFunction = Functions.minFunction();
		return sequence.reduce(sequence.first(), minFunction);
	}

	public static <T extends Comparable<T>> T max(Sequence<T> sequence) {
		if (sequence.isEmpty()) {
			throw new IllegalArgumentException("max only work on non-empty sequences");
		}
		Fn2<T, T, T> minFunction = Functions.maxFunction();
		return sequence.reduce(sequence.first(), minFunction);
	}
}
