package com.jayway.changeless.vectors;


public final class Vectors {
	private Vectors() { /* Static class */ }
	
	@SuppressWarnings("unchecked")
	public static <T> Vector<T> empty() {
		return MapVector.of();
	}

	public static <T> Vector<T> of(T... elements) {
		return MapVector.of(elements);
	}

	public static <T> Vector<T> copyOf(Iterable<T> elements) {
		return MapVector.copyOf(elements);
	}
}
