package com.jayway.changeless.predicates;

public final class Predicates {
	private Predicates() { /* Static class */ }
	
	public static <T> Predicate<T> not(final Predicate<T> predicate) {
		return new Predicate<T>() {
			@Override
			public boolean apply(T input) {
				return !predicate.apply(input);
			}
		};
	}

	public static Predicate<Object> notNull= new Predicate<Object>() {
			@Override
			public boolean apply(Object input) {
				return input != null;
			}
		};
	
	public static Predicate<Object> equalsPredicate(final Object element) {
		return new Predicate<Object>() {
			@Override
			public boolean apply(Object input) {
				return element.equals(input);
			}
		};
	}
}
