package com.jayway.changeless.sequences;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.functions.Fn2;
import com.jayway.changeless.functions.Functions;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.tuples.Tuple;
import com.jayway.changeless.tuples.Tuples;
import com.jayway.changeless.utilities.Comparables;



/**
 * An utility class for working with {@link Sequence}'s.
 */
public final class Sequences {
	private Sequences() { /* Static class */ }
	
	/**
	 * Creates a new sequence consisting of the given element appended 
	 * to the front of the sequence.
	 * @param <T> the type of the elements in the sequence.
	 * @param element the first element of the sequence to create.
	 * @param sequence the tail of the sequence to create.
	 * @return the created sequence.
	 */
	public static <T> Sequence<T> add(T element, Sequence<T> sequence) {
		return DefaultSequence.create(element, sequence);
	}
	
	/**
	 * Create a new sequence containing the given element.
	 * @param <T> the type of the elements in the sequence.
	 * @param element the element of the sequence to create.
	 * @return a new sequence containing the given element.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Sequence<T> of(T element) {
		return ArraySequence.of(element);
	}
	
	/**
	 * Create a new sequence consisting of the given elements.
	 * @param <T> the type of the elements in the sequence.
	 * @param elements the elements of the sequence to create.
	 * @return a new sequence consisting of the given elements.
	 */
	public static <T> Sequence<T> of(T... elements) {
		return ArraySequence.of(elements);
	}
	
	/**
	 * Creates an empty sequence.
	 * @param <T> the type of the elements in the sequence.
	 * @return an empty sequence.
	 */
	public static <T> Sequence<T> empty() {
		return EmptySequence.create();
	}
	
	/**
	 * Creates a new sequence containing the elements of given {@link Iterable}.
	 * @param <T> the type of the elements in the sequence.
	 * @param elements the elements of the sequence to create.
	 * @return a new sequence consisting of the given elements.
	 */
	public static <T> Sequence<T> copyOf(Iterable<T> elements) {
		return ArraySequence.copyOf(elements);
	}
	
	/**
	 * Creates a new sequence containing the elements of given array.
	 * @param <T> the type of the elements in the sequence.
	 * @param elements the elements of the sequence to create.
	 * @return a new sequence consisting of the given elements.
	 */
	public static <T> Sequence<T> copyOf(T[] elements) {
		return ArraySequence.copyOf(elements);
	}
	
	/**
	 * Given a collection of sequences this function creates a lazy sequence
	 * containing the elements of all the sequences. 
	 * @param <T> the type of the elements in the sequence.
	 * @param sequences the sequences to be appended.
	 * @return the created sequence.
	 */
	public static <T> Sequence<T> appended(Iterable<? extends Sequenceable<T>> sequences) {
		Sequence<T> appendedSequence = empty();
		for (Sequenceable<T> sequenceable : sequences) {
			appendedSequence = appendedSequence.append(sequenceable);
		}
		return appendedSequence;
	}
	
	/**
	 * <p>
	 * Create an infinite integer sequence starting from the specified value.
	 * </p>
	 * <p>
	 * Example:
	 * <code>
	 * <pre>
	 * Sequence&lt;Integer&gt; sequence = Sequences.from(2).step(3).upward().take(5);
	 * Sequence&lt;Integer&gt; expected = Sequences.of(2,5,8,11,14);
	 * assertEquals(expected, sequence);
	 * </pre>
	 * </code>
	 * </p>
	 * @param from the starting point of the sequence.
	 * @return the produced sequence.
	 */
	public static IntegerSequenceBuilder from(int from) {
		return new IntegerSequenceBuilder(from);
	}
	
	/**
	 * <p>
	 * Create a lazy sequence using the given starting point and a producer function.
	 * </p>
	 * <p>
	 * Given the starting point <i>s</i> and the producer function <i>f</i> the sequence if produces 
	 * the following way.
	 * </p>
	 * <p>
	 * <code>
	 * [s, f(start), ff(start), fff(start), ffff(start), ...]
	 * </code>
	 * </p>
	 * <p>
	 * If the producer function returns an {@link Optional#none()} value the sequence stops.
	 * </p>
	 * @param <T> the type of the elements in the sequence.
	 * @param start the starting point of the sequence.
	 * @param producer the producer function used to produce the sequence.
	 * @return the created sequence.
	 */
	public static <T> Sequence<T> produce(T start, Fn<? super T, ? extends Optional<T>> producer) {
		return DefaultSequenceProducer.create(start, producer);
	}
	
	/**
	 * Returns the smallest value of the given sequence.
	 * @param <T> the type of the elements in the sequence.
	 * @param sequence the sequence.
	 * @return the smallest value of the given sequence.
	 */
	public static <T extends Comparable<T>> T min(Sequence<T> sequence) {
		if (sequence.isEmpty()) {
			throw new IllegalArgumentException("min only work on non-empty sequences");
		}
		Fn2<T, T, T> minFunction = Comparables.minFunction();
		return sequence.reduce(minFunction);
	}
	
	/**
	 * Returns the largest value of the given sequence.
	 * @param <T> the type of the elements in the sequence.
	 * @param sequence the sequence.
	 * @return the largest value of the given sequence.
	 * @throws IllegalArgumentException if the sequence is empty.
	 */
	public static <T extends Comparable<T>> T max(Sequence<T> sequence) {
		if (sequence.isEmpty()) {
			throw new IllegalArgumentException("max only work on non-empty sequences");
		}
		Fn2<T, T, T> maxFunction = Comparables.maxFunction();
		return sequence.reduce(maxFunction);
	}
	
	/**
	 * Given a sequence of {@link Tuple}'s this function splits the tuples into 
	 * two sequences.
	 * @param <T1> the type of the first component of the tuples.
	 * @param <T2> the type of the second component of the tuples.
	 * @param sequence the sequence to unzip.
	 * @return the given tuples split into two sequences.
	 */
	public static <T1,T2> Tuple<Sequence<T1>,Sequence<T2>> unzip(Sequence<Tuple<T1,T2>> sequence) {
		Sequence<T1> s1 = sequence.transform(Tuples.<T1,T2>firstFunction());
		Sequence<T2> s2 = sequence.transform(Tuples.<T1,T2>secondFunction());
		return Tuples.of(s1, s2);
	}

	/**
	 * Returns a new sorted sequence based on the given sequence.
	 * @param sequence the sequence which elements should be sorted.
	 * @return a new sorted sequence based on the given sequence.
	 */
	public static <T extends Comparable<T>> Sequence<T> sort(Sequence<T> sequence) {
		Fn<T, T> selector = Functions.identity();
		return sequence.sortBy(selector);
	}
}
