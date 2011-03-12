package com.jayway.collections.immutable.vectors;

public final class Vectors {
	private Vectors(){}
	
	@SuppressWarnings("unchecked")
	public static <T> Vector<T> empty() {
		return ArrayVector.of();
	}

	public static <T> Vector<T> of(T... elements) {
		return ArrayVector.of(elements);
	}

}
