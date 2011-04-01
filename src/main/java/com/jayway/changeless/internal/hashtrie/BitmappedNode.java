package com.jayway.changeless.internal.hashtrie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;

final class BitmappedNode<T> implements Node<T> {

	private final int bits;
	private final int shift;
	private final List<Node<T>> table;

	public BitmappedNode(int shift, int bits, List<Node<T>> table) {
		this.shift = shift;
		this.bits = bits;
		this.table = table;
	}

	@Override
	public Node<T> add(int levelShift, int hash, T value) {
		int i = (hash >>> shift) & 0x1f;
		int mask = 1 << i;

		if ((bits & mask) != 0) {
			Node<T> node = table.get(i).add(shift + 5, hash, value);
			if (node == table.get(i)) {
				return this;
			} 
			
			List<Node<T>> newTable = createEmptyTable(table.size());
			Collections.copy(newTable, table);
			newTable.set(i, node);
			return new BitmappedNode<T>(shift, bits, newTable);
		} else {
			int tableSize = Math.max(table.size(), i + 1);
			List<Node<T>> newTable = createEmptyTable(tableSize);
			Collections.copy(newTable, table);
			newTable.set(i, new LeafNode<T>(hash, value));
			int newBits = bits | mask;
			if (newBits == ~0) {
				return new FullNode<T>(shift, newTable);
			} else {
				return new BitmappedNode<T>(shift, newBits, newTable);
			}
		}
	}
	
	private List<Node<T>> createEmptyTable(int size) {
		return ListUtilites.createListWithNullValues(size);
	}

	@Override
	public Optional<T> get(T value, int hash) {
		int i = (hash >>> shift) & 0x01f;
		int mask = 1 << i;

		if ((bits & mask) == mask) {
			return table.get(i).get(value, hash);
		} else {
			return Optional.none();
		}
	}

	@Override
	public Node<T> remove(T value, int hash) {
		int i = (hash >>> shift) & 0x01f;
		int mask = 1 << i;

		if ((bits & mask) != mask) {
			return this;
		} 
			
		Node<T> node = table.get(i).remove(value, hash);

		if (node == table.get(i)) {
			return this;
		} 
		
		if (node instanceof EmptyNode<?>) {
			if (size() == 1) {
				return new EmptyNode<T>();
			} 

			int adjustedBits = bits ^ mask;
			double log = Math.log(adjustedBits) / Math.log(2);

			if (Math.floor(log) == log) { // last one
				return table.get((int) log);
			} 

			int length = (i + 1 == table.size()) ? table.size() - 1
					: table.size();
			List<Node<T>> newTable = new ArrayList<Node<T>>(length);
			for (int j = 0; j < length; j++) {
				newTable.add(table.get(j));
			}
			
			if (i != length) {
				newTable.set(i, null);	
			}

			return new BitmappedNode<T>(shift, adjustedBits, newTable);
		} else {
			List<Node<T>> newTable = new ArrayList<Node<T>>(table.size());
			for (Node<T> n : table) {
				newTable.add(n);
			}
			newTable.set(i, node);

			return new BitmappedNode<T>(shift, bits, newTable);
		}
	}

	@Override
	public int size() {
		int size = 0;
		for (Node<T> n : table) {
			if (n != null) {
				size += n.size();
			}
		}

		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Sequence<T> sequence() {
		Iterable<Node<T>> tableWithoutNulls = new SkipNullIterable<Node<T>>(table.iterator());
		return Sequences.appended(tableWithoutNulls);
	}

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
	
	@Override
	public String toString() {
		Iterable<Node<T>> iterable = new SkipNullIterable<Node<T>>(table.iterator());
		return Sequences.copyOf(iterable).toString();
	}
}
