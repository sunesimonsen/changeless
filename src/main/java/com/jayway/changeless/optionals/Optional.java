package com.jayway.changeless.optionals;

/**
 * Represents an optional value that can either be none or have a value.
 * @param <V> the type of this optional value.
 */
public final class Optional<V> {
	private V value;
	
	private Optional(V value) {
		this.value = value;
	}
	
	/**
	 * Returns true if this optional has a value; false otherwise.
	 * @return true if this optional has a value; false otherwise.
	 */
	public boolean hasValue() {
		return value != null;
	}
	
	/**
	 * Returns true if this optional has no value; false otherwise.
	 * @return true if this optional has no value; false otherwise.
	 */
	public boolean hasNoValue() {
		return !hasValue();
	}
	
	/**
	 * Returns the value of this optional.
	 * @return the value of this optional.
	 * @throws NullPointerException if this optional has no value.
	 */
	public V getValue() {
		if (!hasValue()) {
			throw new NullPointerException("The optional has no value.");
		}
		return value;
	}
	
	/**
	 * Returns the value of this optional or the given default value if this 
	 * optional has no value.
	 * @param <T> The type of this optional value.
	 * @param defaultValue the default value that will be returned is this optional has no value.
	 * @return the value of this optional or the given default value if this 
	 * optional has no value.
	 */
	public <T extends V> V getValueOrDefault(T defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value;
	}
	
	/**
	 * Returns an optional with no value.
	 * @param <T> the type of the optional value to be created.
	 * @return an optional with no value.
	 */
	public static <T> Optional<T> none() {
		return new Optional<T>(null);
	}
	
	/**
	 * Returns an optional of the given value.
	 * @param <T> the type of the optional value to be created. 
	 * @param value the value to return an optional for.
	 * @return an optional of the given value.
	 * @throws NullPointerException if the given value is null.
	 */
	public static <T> Optional<T> valueOf(T value) {
		if (value == null) {
			throw new NullPointerException("An optional can't be creates from null.");
		}
		return new Optional<T>(value);
	}
	
	@Override
	public String toString() {
		return hasValue() ? getValue().toString() : "NONE";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Optional<V> other = (Optional<V>) obj;
		return equals(other);
	}
	
	/**
	 * A strongly typed version of {@link Optional#equals(Object)}.
	 * @param other the optional with which to compare.  
	 * @return true if this optional is equals to the given optional value.
	 */
	public boolean equals(Optional<V> other) {
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
