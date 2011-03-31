package com.jayway.changeless.immutable.sets;



public final class Sets {
	private Sets() {}
	
	public static <T> Set<T> empty() {
		return ImmutableHashSet.empty();
	}
	
	public static <T> Set<T> copyOf(Iterable<T> iterable) {
		return ImmutableHashSet.copyOf(iterable);
	}

	public static <T> Set<T> of(T... elements) {
		return ImmutableHashSet.of(elements);
	}
}
