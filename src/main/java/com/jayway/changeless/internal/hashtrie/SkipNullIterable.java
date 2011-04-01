package com.jayway.changeless.internal.hashtrie;

import java.util.Iterator;

final class SkipNullIterable<T> implements Iterator<T> {

	private final Array<T> source;
	private int i = 0;
	public SkipNullIterable(Array<T> source) {
		this.source = source;
	}

	@Override
	public boolean hasNext() {
		while(i < source.size()) {
			
		}
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}
