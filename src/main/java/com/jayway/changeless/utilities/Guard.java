package com.jayway.changeless.utilities;

import com.jayway.changeless.intervals.Interval;

/**
 * A guard class for checking preconditions on method arguments.
 */
public final class Guard {
	private Guard() { /* Static class */ }
	
	public static void notNull(Object argument, String argumentName) {
		if (argument == null) {
			String message = String.format("Argument '%s' must not be null.", argumentName);
			throw new IllegalArgumentException(message);
		}
	}
	
	public static void notNull(Object argument, String argumentNameTemplate, Object... formatArgs) {
		if (argument == null) {
			String argumentName = String.format(argumentNameTemplate, formatArgs);
			String message = String.format("Argument '%s' must not be null.", argumentName);
			throw new IllegalArgumentException(message);
		}
	}

	public static void nonNegative(int argument, String argumentName) {
		if (argument < 0) {
			String message = String.format("Argument '%s' must not be negative.", argumentName);
			throw new IllegalArgumentException(message);
		}
	}

	public static <T extends Comparable<T>> void in(T value, Interval<T> interval, String argumentName) {
		if (!interval.contains(value)) {
			String message = String.format(
					"Argument '%s' with value '%s' must not be outside interval %s", 
					argumentName, value, interval);
			throw new IllegalArgumentException(message);
		}
	}
}
