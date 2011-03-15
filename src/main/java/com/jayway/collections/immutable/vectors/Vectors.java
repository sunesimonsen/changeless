package com.jayway.collections.immutable.vectors;

public final class Vectors {
	private Vectors(){}
	
	@SuppressWarnings("unchecked")
	public static <T> Vector<T> empty() {
		return MapVector.of();
	}

	public static <T> Vector<T> of(T... elements) {
		return MapVector.of(elements);
	}

}
