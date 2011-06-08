package com.jayway.changeless.utilities;

import com.jayway.changeless.intervals.Interval;

/**
 * A guard class for checking preconditions on method arguments.
 */
public final class Guard {
	private Guard() { /* Static class */ }
	
	/**
	 * Ensured that the given argument is not null.
	 * @param argument the argument to be checked.
	 * @param argumentName the name of the argument used in the exception message.
	 * @throws IllegalArgumentException if the given argument is null.
	 */
	public static void notNull(Object argument, String argumentName) {
		if (argument == null) {
			String message = String.format("Argument '%s' must not be null.", argumentName);
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * <p>Ensured that the given argument is not null.</p>
	 * <p>@{String#format} is used to format the argument name.</p>
	 * @param argument the argument to be checked.
	 * @param argumentNameTemplate a string template defining the argument name.
	 * @param formatArgs arguments for the argument template.
	 * @throws IllegalArgumentException if the given argument is null.
	 */
	public static void notNull(Object argument, String argumentNameTemplate, Object... formatArgs) {
		if (argument == null) {
			String argumentName = String.format(argumentNameTemplate, formatArgs);
			String message = String.format("Argument '%s' must not be null.", argumentName);
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Ensured that the given argument is not negative.
	 * @param argument the argument to be checked.
	 * @param argumentName the name of the argument used in the exception message.
	 * @throws IllegalArgumentException if the given argument is negative.
	 */
	public static void nonNegative(int argument, String argumentName) {
		if (argument < 0) {
			String message = String.format("Argument '%s' must not be negative.", argumentName);
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Ensures that the given argument is in the the specified interval.
	 * @param <T> the type of the argument.
	 * @param argument the argument to be checked.
	 * @param interval the interval that the argument should be contained by.
	 * @param argumentName the name of the argument used in the exception message.
	 * @throws IllegalArgumentException if the given argument is not contained by the 
	 * specified interval.
	 */
	public static <T extends Comparable<T>> void in(T argument, Interval<T> interval, String argumentName) {
		if (!interval.contains(argument)) {
			String message = String.format(
					"Argument '%s' with value '%s' must not be outside interval %s", 
					argumentName, argument, interval);
			throw new IllegalArgumentException(message);
		}
	}
}
