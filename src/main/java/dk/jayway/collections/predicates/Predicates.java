package dk.jayway.collections.predicates;

public final class Predicates {
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
}
