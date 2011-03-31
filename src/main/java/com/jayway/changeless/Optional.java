package com.jayway.changeless;

public final class Optional<V> {
	private V value;
	
	private Optional(V value) {
		this.value = value;
	}
	
	public boolean hasValue() {
		return value != null;
	}
	
	public V getValue() {
		if (!hasValue()) {
			throw new NullPointerException("The optional has no value.");
		}
		return value;
	}
	
	public <T extends V> V getValueOrDefault(T defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return value;
	}
	
	public static <T> Optional<T> none() {
		return new Optional<T>(null);
	}
	
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
	
	public boolean equals(Optional<V> other) {
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public boolean hasNoValue() {
		return !hasValue();
	}
}
