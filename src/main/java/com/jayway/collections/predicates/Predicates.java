package com.jayway.collections.predicates;

public final class Predicates {
	private Predicates() {}
	
	public static <T> Predicate<T> not(final Predicate<T> predicate) {
		return new Predicate<T>() {
			@Override
			public boolean apply(T input) {
				return !predicate.apply(input);
			}
		};
	}

	public static <T> Predicate<Object> notNull() {
		return new Predicate<Object>() {
			@Override
			public boolean apply(Object input) {
				return input != null;
			}
		};
	}
	
	public static Predicate<Integer> oddPredicate = new Predicate<Integer>() {
		@Override
		public boolean apply(Integer input) {
			return input % 2 == 1;
		}
	};
	
	public static Predicate<Integer> evenPredicate = new Predicate<Integer>() {
		@Override
		public boolean apply(Integer input) {
			return input % 2 == 0;
		}
	};
}
