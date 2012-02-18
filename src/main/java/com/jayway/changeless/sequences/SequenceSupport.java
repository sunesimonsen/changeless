package com.jayway.changeless.sequences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.functions.Fn2;
import com.jayway.changeless.functions.Functions;
import com.jayway.changeless.functions.strings.AppendStringFunction;
import com.jayway.changeless.intervals.Intervals;
import com.jayway.changeless.maps.Map;
import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.maps.UpdateFunction;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.predicates.Predicate;
import com.jayway.changeless.predicates.Predicates;
import com.jayway.changeless.tuples.Tuple;
import com.jayway.changeless.utilities.Guard;


/**
 * A support class for implementing sequences. The only method you 
 * have to implement is {@link Sequence#first()} and {@link Sequence#rest()}
 * @param <T> the type of the elements in this sequence.
 */
public abstract class SequenceSupport<T> implements Sequence<T> {
	private static final int MAXIMUM_ELEMENTS_IN_TO_STRING = 20;
	
	@Override
	public abstract Sequence<T> rest();
	
	@Override
	public abstract T first();
	
	@Override
	public Sequence<T> add(T element) {
		return Sequences.add(element, this);
	}
	
	@Override
	public Sequence<T> add(T... elements) {
		Sequence<T> result = this;
		for (int i = elements.length-1; 0 <= i; i--) {
			Guard.notNull(elements[i], "element[%d]", i);
			result = Sequences.add(elements[i], result);
		}
		return result;
	}
	
	
	@Override
	public Sequence<T> add(Iterable<? extends T> elements) {
		Sequence<T> result = this;
		for (T element : this) {
			Guard.notNull(element, "element");
			result = Sequences.add(element, result);
		}
		return result;
	}
	
	@Override
	public String toString() {
		Sequence<T> sequence = this.take(MAXIMUM_ELEMENTS_IN_TO_STRING);
		Sequence<T> tailSequence = this.skip(MAXIMUM_ELEMENTS_IN_TO_STRING);
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(");
		
		stringBuilder = sequence.transform(Functions.toStringFunction)
			.interpose(",").reduce(stringBuilder, new AppendStringFunction());
	
		if (tailSequence.isEmpty()) {
			stringBuilder.append(")");	
		} else {
			stringBuilder.append(",...");
		}
		
		return stringBuilder.toString();
	}
	
	@Override
	public <I extends T>  Sequence<T> interpose(I separator) {
		return new InterposeSequence<T>(this, separator);
	}
	
	@Override
	public Sequence<T> skip(int n) {
		if (n <= 0) {
			return this;
		} 
		
		Sequence<T> result = this;
		for (int i = 0; i < n && !result.isEmpty(); i++) {
			result = result.rest();
		}
		
		return result;
	}
	
	@Override
	public Sequence<T> skipWhile(Predicate<T> predicate) {
		Sequence<T> result = this;
		while (!result.isEmpty() && predicate.matches(result.first())) {
			result = result.rest();
		}
		return result;
	}
	
	@Override
	public <R> Sequence<R> transform(Fn<? super T, ? extends R> function) {
		return TransformedSequence.create(this, function);
	}
	
	@Override
	public <R> Sequence<R> transformIndexed(Fn2<Integer, ? super T,? extends R> function) {
		return IndexedTransformedSequence.create(this, function);
	}
	
	
	@Override
	public Sequence<T> filter(Predicate<? super T> predicate) {
		return FilterSequence.create(this, predicate);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;	
		}
		if (obj == null) {
			return false;
		}
		
		if (!(obj instanceof Sequenceable)) {
			return false;
		}
		
		Sequence other = ((Sequenceable) obj).sequence();
		
		Sequence h1 = this;
		Sequence h2 = other;
		
		while (!h1.isEmpty()) {
			if (h2.isEmpty()) {
				return false;
			}
			
			if (!h1.first().equals(h2.first())) {
				return false;
			}
			
			h1 = h1.rest();
			h2 = h2.rest();
		}
		
		// now h1.isEmpty() 
		return h2.isEmpty();
	}
	
	@Override
	public Map<T, Integer> frequencies() {
		return reduce(Maps.<T, Integer>empty(), frequencyFunction);
	}
	
	private Fn2<Map<T, Integer>, T, Map<T, Integer>> frequencyFunction = 
		new Fn2<Map<T, Integer>, T, Map<T, Integer>>() {
		@Override
		public Map<T, Integer> apply(Map<T, Integer> result, T input) {
			Integer frequency = result.get(input, 0);
			return result.put(input, frequency + 1);
		}
	};
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int hashCode = 0; 
		for (T element : this) {
			hashCode = prime * hashCode + element.hashCode();
		}
		return hashCode;
	}
	
	@Override
	public int size() {
		int size = 0;
		Sequence<T> head = this;
		while(!head.isEmpty()) {
			head = head.rest();
			size++;
		}
		return size;
	}
	
	@Override
	public boolean isSize(int size) {
		Guard.nonNegative(size, "size");
		Sequence<T> head = this;
		for (int i = 0; i < size; i++) {
			if (head.isEmpty()) {
				return false;
			}
			head = head.rest();
		}
		return head.isEmpty();
	}

	@Override
	public Sequence<T> sequence() {
		return this;
	}

	@Override
	public Sequence<T> append(Sequenceable<T> sequenceable) {
		return AppendedSequence.create(this, sequenceable.sequence());
	}

	@Override
	public <R> R reduce(R start, Fn2<? super R, ? super T,R> function) {
		if (isEmpty()) {
			return start;
		}
		
		R result = start;
		for (T value : this) {
			result = function.apply(result, value);
		}
		
		return result;
	}
	
	@Override
	public T reduce(Fn2<? super T, ? super T, T> function) {
		if (isEmpty()) {
			String message = "Reduce without a start value does not work on empty sequences.";
			throw new IllegalArgumentException(message);
		}
		return reduce(first(), function);
	}

	@Override
	public Sequence<T> reverse() {
		Sequence<T> result = Sequences.empty();
		for (T value : this) {
			result = Sequences.add(value, result);
		}
		return result;
	}

	@Override
	public boolean all(Predicate<? super T> predicate) {
		return non(Predicates.not(predicate));
	}

	@Override
	public boolean any(Predicate<? super T> predicate) {
		for (T element : this) {
			if (predicate.matches(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean non(Predicate<? super T> predicate) {
		return !any(predicate);
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public Iterator<T> iterator() {
		return DefaultSequenceIterator.of(this);
	}

	@Override
	public T get(int index) {
		Guard.nonNegative(index, "index");
		Sequence<T> sequence = skip(index);
		if (sequence.isEmpty()) {
			Guard.in(index, Intervals.zero.open(size()), "index");
		}
		return sequence.first();
	}
	
	@Override
	public Sequence<T> take(int n) {
		return TakeSequence.create(this, n);
	}
	
	@Override
	public Sequence<T> takeWhile(Predicate<T> predicate) {
		return TakeWhileSequence.create(this, predicate);
	}

	@Override
	public Sequence<T> remove(Object element) {
		return remove(Predicates.equalsPredicate(element));
	}

	@Override
	public Sequence<T> remove(Predicate<? super T> predicate) {
		return filter(Predicates.not(predicate));
	}

	@Override
	public Sequence<Sequence<T>> partition(int n) {
		return PartitionSequence.create(this, n);
	}
	
	@Override
	public Sequence<T> cycle() {
		return CycleSequence.create(this);
	}

	@Override
	public <TOther> Sequence<Tuple<T, TOther>> zip(Sequence<TOther> sequence) {
		return ZipSequence.create(this, sequence);
	}

	@Override
	public Sequence<T> insertAt(int index, T... elements) {
		return take(index).append(Sequences.of(elements)).append(skip(index));
	}
	
	@Override
	public Sequence<T> filterOnIndex(Predicate<? super Integer> predicate) {
		return IndexFilterSequence.create(this, predicate);
	}
	
	@Override
	public Sequence<T> withRest(Sequence<T> rest) {
		return Sequences.add(first(), rest);
	}
	
	@Override
	public Optional<T> find(Predicate<? super T> predicate) {
		for (T element : this) {
			if (predicate.matches(element)) {
				return Optional.valueOf(element);
			}
		}
		return Optional.none();
	}
	
	@Override
	public Sequence<T> distinct() {
		return DistinctSequence.create(this);
	}
	
	@Override
	public <I extends Comparable<I>> Sequence<T> sortBy(final Fn<? super T, I> selector) {
		Comparator<T> comperator = new Comparator<T>() {
			@Override
			public int compare(T arg0, T arg1) {
				return selector.apply(arg0).compareTo(selector.apply(arg1));
			}
			
		};
		
		ArrayList<T> sortBuffer = new ArrayList<T>();
		for (T e : this) {
			sortBuffer.add(e);
		}
		Collections.sort(sortBuffer, comperator);
		
		return Sequences.copyOf(sortBuffer);
	}
	
	public <K> Map<K,Sequence<T>> groupBy(Fn<T, K> selector) {
		Map<K, Sequence<T>> grouping = Maps.empty();
		for (T element : this) {
			K key = selector.apply(element);
			grouping = grouping.update(key, new AddToSequenceFunction<T>(element));
		}
		return grouping;
	}
	
	@Override
	public Sequence<T> shuffle() {
		ArrayList<T> shuffleBuffer = new ArrayList<T>();
		for (T element : this) {
			shuffleBuffer.add(element);
		}
		
		Collections.shuffle(shuffleBuffer);
		
		return Sequences.copyOf(shuffleBuffer);
	}
	
	private static class AddToSequenceFunction<V> implements UpdateFunction<Sequence<V>> {
		private final V element;
		public AddToSequenceFunction(V element) {
			this.element = element;
		}
		
		@Override
		public Sequence<V> apply(Optional<Sequence<V>> value) {
			if (value.hasValue()) {
				return value.getValue().add(element);
			} else {
				return Sequences.of(element);
			}
		}
	 }
}
