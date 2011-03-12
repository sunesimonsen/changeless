package dk.jayway.collections.immutable.sets;

import dk.jayway.collections.immutable.sequences.Sequenceable;

public interface Set<T> extends Sequenceable<T> {
	Set<T> add(T value);
	boolean Contains(T value);
	int size();
	Set<T> remove(T value);
	Set<T> remove(Set<T> values);
	boolean isEmpty();
	Set<T> intersection(Set<T> set);
	Set<T> union(Set<T> set);
}

