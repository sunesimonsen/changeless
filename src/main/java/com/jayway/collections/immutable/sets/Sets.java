package com.jayway.collections.immutable.sets;



public final class Sets {
	private Sets() {}
	
	public static <T> Set<T> empty() {
		return ImmutableHashSet.empty();
	}
	
	public static <T> Set<T> of(Iterable<T> iterable) {
		return ImmutableHashSet.of(iterable);
	}

	public static <T> Set<T> of(T... elements) {
		return ImmutableHashSet.of(elements);
	}
}
