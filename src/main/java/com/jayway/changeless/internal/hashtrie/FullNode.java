package com.jayway.changeless.internal.hashtrie;

import java.util.Iterator;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;

final class FullNode<T> implements HashTrie<T> {

	private final Array<HashTrie<T>> table;
	private final int shift;

	public FullNode(int shift, Array<HashTrie<T>> table) {
		this.shift = shift;
		this.table = table;
	}

	@Override
	public HashTrie<T> add(int levelShift, int hash, T value) {
		int i = (hash >>> shift) & 0x1f;
		HashTrie<T> node = table.get(i);
		HashTrie<T> foundNode = node.add(shift + 5, hash, value);
		if (foundNode == node) {
			return this;
		} 

		Array<HashTrie<T>> newTable = table.copy(32);
		newTable.set(i, foundNode);
		return new FullNode<T>(shift, newTable);
	}

	@Override
	public Optional<T> get(T value, int hash) {
		return table.get((hash >>> shift) & 0x01f).get(value, hash);
	}

	@Override
	public HashTrie<T> remove(T value, int hash) {
		int i = (hash >>> shift) & 0x01f;
		int mask = 1 << i;

		HashTrie<T> node = table.get(i).remove(value, hash);

		if (node == table.get(i)) {
			return this;
		} 
		
		Array<HashTrie<T>> newTable = table.copy(32);

		if (node instanceof EmptyNode<?>) {
			newTable.set(i, null);
			return new BitmappedNode<T>(shift, ~0 ^ mask, newTable);
		} 

		newTable.set(i, node);
		return new FullNode<T>(shift, newTable);
	}

	@Override
	public int size() {
		int size = 0;
		for (HashTrie<T> n : table) {
			size += n.size();
		}
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public String toString() {
		return Sequences.copyOf(table).toString();
	}
	
	@Override
	public Sequence<T> sequence() {
		return Sequences.appended(table);
	}
	
	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
}
