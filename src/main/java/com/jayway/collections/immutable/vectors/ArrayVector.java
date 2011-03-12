package com.jayway.collections.immutable.vectors;

import com.jayway.collections.immutable.maps.Map;
import com.jayway.collections.immutable.maps.Maps;

public class ArrayVector<T> extends VectorSupport<T> {
	private final Map<Integer, T> data;

	private ArrayVector(Map<Integer, T> data) {
		this.data = data;
	}
	
	public static <T> ArrayVector<T> of(T... elements) {
		Map<Integer, T> data = Maps.empty();
		for (int i = 0; i < elements.length; i++) {
			data = data.put(i, elements[i]);
		}
		return new ArrayVector<T>(data);
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public int size() {
		return data.size();
	}
}
