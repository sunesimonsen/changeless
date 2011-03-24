package com.jayway.collections.immutable.vectors;

import com.jayway.collections.immutable.intervals.Intervals;
import com.jayway.collections.immutable.intervals.StartIntervalBuilder;
import com.jayway.collections.immutable.maps.Map;
import com.jayway.collections.immutable.maps.Maps;
import com.jayway.collections.utilities.Guard;

class MapVector<T> extends VectorSupport<T> {
	private static final StartIntervalBuilder<Integer> zero = Intervals.closed(0);
	private final Map<Integer, T> data;
	private final int size;

	private MapVector(Map<Integer, T> data, int size) {
		this.data = data;
		this.size = size;
	}
	
	public static <T> MapVector<T> of(T... elements) {
		Map<Integer, T> data = Maps.empty();
		for (int i = 0; i < elements.length; i++) {
			data = data.put(i, elements[i]);
		}
		return new MapVector<T>(data, elements.length);
	}
	
	public static <T> MapVector<T> of(Iterable<T> elements) {
		Map<Integer, T> data = Maps.empty();
		int i = 0;
		for (T element : elements) {
			data = data.put(i, element);
			i++;
		}
		return new MapVector<T>(data, i);
	}

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public int size() {
		return data.size();
	}
	
	@Override
	public Vector<T> add(T... elements) {
		if (elements.length == 0) {
			return this;
		}
		
		Map<Integer, T> result = data;
		int size = data.size();
		
		for (T element : elements) {
			result = result.put(size++, element);
		}
		return new MapVector<T>(result, size);
	}

	@Override
	public T get(int index) {
		Guard.in(index, zero.open(size), "index");
		return data.get(index).getValue();
	}
}
