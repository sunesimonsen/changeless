package com.jayway.collections.utilities;

public final class Guard {
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

	public static void greaterThanOne(int argument, String argumentName) {
		if (0 < argument) {
			String message = String.format("Argument '%s' must be greater than one.", argumentName);
			throw new IllegalArgumentException(message);
		}	
	}
}