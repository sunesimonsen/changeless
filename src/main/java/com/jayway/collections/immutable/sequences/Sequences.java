package com.jayway.collections.immutable.sequences;




public final class Sequences {
	private Sequences() { }
	
	public static <T> Sequence<T> of(T... elements) {
		return ArraySequence.of(elements);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Sequence<T> of(T e1) {
		return ArraySequence.<T>of(e1);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Sequence<T> of(T e1, T e2) {
		return ArraySequence.<T>of(e1,e2);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Sequence<T> of(T e1, T e2, T e3) {
		return ArraySequence.<T>of(e1,e2,e3);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Sequence<T> of(T e1, T e2, T e3, T e4) {
		return ArraySequence.<T>of(e1,e2,e3, e4);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Sequence<T> of(T e1, T e2, T e3, T e4, T e5) {
		return ArraySequence.<T>of(e1, e2, e3, e4, e5);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Sequence<T> of(T e1, T e2, T e3, T e4, T e5, T e6) {
		return ArraySequence.<T>of(e1, e2, e3, e4, e5, e6);
	}
	
	public static <T> Sequence<T> empty() {
		return DefaultSequence.empty();
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
}
