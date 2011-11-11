package com.jayway.changeless.internal.hashtrie;

import java.util.Iterator;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;

final class BitmappedNode<T> implements HashTrie<T> {

	private final int bits;
	private final int shift;
	private final Array<HashTrie<T>> table;

	public BitmappedNode(int shift, int bits, Array<HashTrie<T>> table) {
		this.shift = shift;
		this.bits = bits;
		this.table = table;
	}

	@Override
	public HashTrie<T> add(int levelShift, int hash, T value) {
		int i = (hash >>> shift) & 0x1f;
		int mask = 1 << i;

		if ((bits & mask) != 0) {
			HashTrie<T> node = table.get(i).add(shift + 5, hash, value);
			if (node == table.get(i)) {
				return this;
			} 
			
			Array<HashTrie<T>> newTable = table.copy();
			newTable.set(i, node);
			return new BitmappedNode<T>(shift, bits, newTable);
		} 
		
		int tableSize = Math.max(table.size(), i + 1);
		Array<HashTrie<T>> newTable = table.copy(tableSize);
		newTable.set(i, new LeafNode<T>(hash, value));
		int newBits = bits | mask;
		if (newBits == ~0) {
			return new FullNode<T>(shift, newTable);
		} 
		
		return new BitmappedNode<T>(shift, newBits, newTable);
	}

	@Override
	public Optional<T> get(T value, int hash) {
		int i = (hash >>> shift) & 0x01f;
		int mask = 1 << i;

		if ((bits & mask) == mask) {
			return table.get(i).get(value, hash);
		} 

		return Optional.none();
	}

	@Override
	public HashTrie<T> remove(T value, int hash) {
		int i = (hash >>> shift) & 0x01f;
		int mask = 1 << i;

		if ((bits & mask) != mask) {
			return this;
		} 
			
		HashTrie<T> node = table.get(i).remove(value, hash);

		if (node == table.get(i)) {
			return this;
		} 
		
		if (node.isEmpty()) {
			if (isSize(1)) {
				return node; // reuse empty node
			} 

			int adjustedBits = bits ^ mask;
			double log = Math.log(adjustedBits) / Math.log(2);

			if (Math.floor(log) == log) { // last one
				return table.get((int) log);
			} 

			int size = table.size();
			int newSize = (i == size - 1) ? size - 1 : size;
			
			Array<HashTrie<T>> newTable = table.copy(newSize);
			
			if (i != newSize) {
				newTable.set(i, null);	
			}

			return new BitmappedNode<T>(shift, adjustedBits, newTable);
		} 

		Array<HashTrie<T>> newTable = table.copy();
		newTable.set(i, node);

		return new BitmappedNode<T>(shift, bits, newTable);
	}

	private boolean isSize(int size) {
		int s = 0;
		for (HashTrie<T> n : table) {
			s += n.size();
			if (s > size) {
				return false;
			}
		}
		return size == s;
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
	public Sequence<T> sequence() {
		return Sequences.appended(Sequences.copyOf(table));
	}

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
	
	@Override
	public String toString() {
		return sequence().toString();
	}
}
