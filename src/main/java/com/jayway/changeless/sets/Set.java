package com.jayway.changeless.sets;

import com.jayway.changeless.sequences.Sequenceable;

public interface Set<T> extends Sequenceable<T> {
	Set<T> add(T value);
	boolean contains(T value);
	int size();
	Set<T> remove(T value);
	Set<T> remove(Set<? extends T> values);
	boolean isEmpty();
	Set<T> intersection(Set<? extends T> set);
	Set<T> union(Set<? extends T> set);
}

