package com.jayway.changeless.internal.hashtrie;

public final class HashTries {
	public static <T> Node<T> empty() {
		return new EmptyNode<T>();
	}
}
