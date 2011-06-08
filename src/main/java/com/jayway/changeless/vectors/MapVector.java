package com.jayway.changeless.vectors;

import com.jayway.changeless.maps.Map;
import com.jayway.changeless.maps.Maps;

final class MapVector<T> extends VectorSupport<T> {
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
	
	public static <T> MapVector<T> copyOf(Iterable<T> elements) {
		Map<Integer, T> data = Maps.empty();
		int i = 0;
		for (T element : elements) {
			data = data.put(i, element);
			i++;
		}
		return new MapVector<T>(data, i);
	}

	@Override
	public int size() {
		return size;
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
		EnsureValidIndex(index);
		return data.get(index).getValue();
	}

	@Override
	public Vector<T> set(int index, T element) {
		EnsureValidIndex(index);
		return new MapVector<T>(data.put(index, element), size());
	}
}
