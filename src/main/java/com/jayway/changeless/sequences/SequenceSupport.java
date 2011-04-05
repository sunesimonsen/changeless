package com.jayway.changeless.sequences;

import java.util.Iterator;

import com.jayway.changeless.functions.Fn;
import com.jayway.changeless.functions.Fn2;
import com.jayway.changeless.functions.Functions;
import com.jayway.changeless.intervals.Intervals;
import com.jayway.changeless.maps.Map;
import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.predicates.Predicate;
import com.jayway.changeless.predicates.Predicates;
import com.jayway.changeless.tuples.Tuple;
import com.jayway.changeless.utilities.Guard;


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
	public Sequence<T> add(Iterable<? extends T> elements) {
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
	public boolean all(Predicate<? super T> predicate) {
		return non(Predicates.not(predicate));
	}

	@Override
	public boolean any(Predicate<? super T> predicate) {
		for (T element : this) {
			if (predicate.apply(element)) {
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
}
