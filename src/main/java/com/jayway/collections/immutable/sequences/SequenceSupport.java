package com.jayway.collections.immutable.sequences;

import java.util.Iterator;

import com.jayway.collections.functions.Fn;
import com.jayway.collections.functions.Fn2;
import com.jayway.collections.functions.Functions;
import com.jayway.collections.immutable.intervals.Intervals;
import com.jayway.collections.immutable.maps.Map;
import com.jayway.collections.immutable.maps.Maps;
import com.jayway.collections.predicates.Predicate;
import com.jayway.collections.predicates.Predicates;
import com.jayway.collections.utilities.Guard;


public abstract class SequenceSupport<T> implements Sequence<T> {
	private static final int MAXIMUM_ELEMENTS_IN_TO_STRING = 20;
	
	@Override
	public abstract Sequence<T> rest();
	
	@Override
	public Sequence<T> add(T... elements) {
		Sequence<T> result = this;
		for (int i = elements.length-1; 0 <= i; i--) {
			Guard.notNull(elements[i], "element[%d]", i);
			result = Sequences.append(elements[i], result);
		}
		return result;
	}
	
	@Override
	public Sequence<T> add(Iterable<T> elements) {
		Sequence<T> result = this;
		for (T element : this) {
			Guard.notNull(element, "element");
			result = Sequences.append(element, result);
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
			.interpose(",").reduce(stringBuilder, Functions.appendStringFunction);
	
		if (tailSequence.isEmpty()) {
			stringBuilder.append(")");	
		} else {
			stringBuilder.append(",...");
		}
		
		return stringBuilder.toString();
	}
	
	@Override
	public Sequence<T> interpose(T separator) {
		return new InterposeSequence<T>(this, separator);
	}
	
	@Override
	public Sequence<T> skip(int n) {
		return SkipSequence.create(this, n);
	}
	
	@Override
	public <R> Sequence<R> transform(Fn<? super T, ? extends R> function) {
		return TransformedSequence.create(this, function);
	}
	
	@Override
	public Sequence<T> filter(Predicate<? super T> predicate) {
		return FilterSequence.create(this, predicate);
	}
	
	@SuppressWarnings("unchecked")
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
		
		if (isEmpty() && other.isEmpty()) {
			return true;
		} 
		
		if (isEmpty() != other.isEmpty()) {
			return false;
		}
		
		return first().equals(other.first()) && 
			rest().equals(other.rest());
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
		if (isEmpty()) {
			return 0;
		}
		final int prime = 31;
		return prime * rest().hashCode() + first().hashCode();
	}
	
	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} 
		return 1 + rest().size();	
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
	public Sequence<T> reverse() {
		Sequence<T> result = Sequences.empty();
		for (T value : this) {
			result = Sequences.append(value, result);
		}
		return result;
	}

	@Override
	public boolean all(Predicate<T> predicate) {
		return non(Predicates.not(predicate));
	}

	@Override
	public boolean any(Predicate<T> predicate) {
		for (T element : this) {
			if (predicate.apply(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean non(Predicate<T> predicate) {
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
	public Sequence<T> remove(T element) {
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
}
