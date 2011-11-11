package com.jayway.changeless.functions;

import com.jayway.changeless.functions.objects.ToStringFunction;
import com.jayway.changeless.predicates.Predicate;

/**
 * A class containing convenient functions for working with function-objects.
 * @param <T>
 */
public final class Functions<T> {
	private Functions() { /* Static class */ }
	
	/**
	 * The function that returns the result of calling toString on it's input argument.
	 */
	public static Fn<Object, String> toStringFunction = new ToStringFunction();
	
	/**
	 * <p>
	 * Returns a new function that is the composition of the given functions.
	 * </p>
	 * <p>
	 * (f o g) x = f(g(x))
	 * </p>
	 * @param <A> the input type of function g.
	 * @param <B> the output type of function g.
	 * @param <C> the input type of function f.
	 * @param <D> the output type of function f.
	 * @param f a function to be composed with g.
	 * @param g a function to be composed with f.
	 * @return a function that is the composition of f and g.
	 */
	public static <C,D,A,B extends C> Fn<A,D> compose(final Fn<C,D> f, final Fn<A,B> g) {
		return new Fn<A,D>() {
			@Override
			public D apply(A input) {
				return f.apply(g.apply(input));
			}
		};
	}
	
	/**
	 * <p>
	 * Returns a new function that is the composition of the given functions.
	 * </p>
	 * <p>
	 * (f o g) x = f(g(x))
	 * </p>
	 * @param <A> the input type of function g.
	 * @param <B> the output type of function g.
	 * @param <C> the input type of function f.
	 * @param <D> the output type of function f.
	 * @param f a function to be composed with g.
	 * @param g a function to be composed with f.
	 * @return a function that is the composition of f and g.
	 */
	public static <C,D,A,B extends C> Fn2<A,A,D> compose(final Fn2<C,C,D> f, final Fn<A,B> g) {
		return new Fn2<A,A,D>() {
			@Override
			public D apply(A x, A y) {
				return f.apply(g.apply(x), g.apply(y));
			}
		};
	}

	/**
	 * Create a new function corresponding to the given predicate.
	 * @param predicate the predicate.
	 * @return a new function corresponding to the given predicate.
	 */
	public static <T> Fn<T, Boolean> from(final Predicate<T> predicate) {
		return new Fn<T, Boolean>() {
			@Override
			public Boolean apply(T input) {
				return predicate.matches(input);
			}
		};
	}
	
	public static <T> Fn<T, T> identity() {
		return new IdentityFunction<T>();
	};
}
