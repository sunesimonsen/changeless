package com.jayway.collections.immutable.sequences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

import com.jayway.collections.utilities.Guard;


final class ArraySequence<T> extends SequenceSupport<T> implements RandomAccess {

	private final int offset;
	private final List<T> elements;
	
	private ArraySequence(List<T> elements, int offset) {
		this.elements = Collections.unmodifiableList(elements);
		this.offset = offset;
	}

	public static <T> Sequence<T> of(T... elements) {
		List<T> elementList = new ArrayList<T>(elements.length);
		int i = 0;
		for (T element : elements) {
			Guard.notNull(element, "element[%s]", i);
			elementList.add(element);
			i++;
		}
		return new ArraySequence<T>(elementList, 0);
	}
	
	public static <T> Sequence<T> copyOf(Iterable<T> elements) {
		List<T> elementList = new ArrayList<T>();
		int i = 0;
		for (T element : elements) {
			Guard.notNull(element, "element[%s]", i);
			elementList.add(element);
			i++;
		}
		return new ArraySequence<T>(elementList, 0);
	}
	
	@Override
	public T first() {
		return elements.get(offset);
	}
	
	@Override
	public Sequence<T> rest() {
		return new ArraySequence<T>(elements, offset + 1);
	}

	@Override
	public boolean isEmpty() {
		return offset >= elements.size();
	}
	
	@Override
	public Iterator<T> iterator() {
		return elements.iterator();
	}

	@Override
	public T get(int index) {
		return elements.get(index);
	}

}
