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

	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}

	@Override
	public int size() {
		return data.size();
	}

	public Vector<T> add(T element) {
		return new MapVector<T>(data.put(size, element),size+1);
	}
	
	@Override
	public Vector<T> add(T... elements) {
		Vector<T> result = this;
		for (T element : elements) {
			result = result.add(element);
		}
		return result;
	}

	@Override
	public T get(int index) {
		Guard.in(index, zero.open(size), "index");
		return data.get(index).getValue();
	}
}
