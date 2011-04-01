package com.jayway.changeless.internal.hashtrie;

import java.util.Iterator;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;

final class FullNode<T> implements Node<T> {

	private final Array<Node<T>> table;
	private final int shift;

	public FullNode(int shift, Array<Node<T>> table) {
		this.shift = shift;
		this.table = table;
	}

	@Override
	public Node<T> add(int levelShift, int hash, T value) {
		int i = (hash >>> shift) & 0x1f;
		Node<T> node = table.get(i);
		Node<T> foundNode = node.add(shift + 5, hash, value);
		if (foundNode == node) {
			return this;
		} 

		Array<Node<T>> newTable = table.copy(32);
		newTable.set(i, foundNode);
		return new FullNode<T>(shift, newTable);
	}

	@Override
	public Optional<T> get(T value, int hash) {
		return table.get((hash >>> shift) & 0x01f).get(value, hash);
	}

	@Override
	public Node<T> remove(T value, int hash) {
		int i = (hash >>> shift) & 0x01f;
		int mask = 1 << i;

		Node<T> node = table.get(i).remove(value, hash);

		if (node == table.get(i)) {
			return this;
		} 
		
		Array<Node<T>> newTable = table.copy(32);

		if (node instanceof EmptyNode<?>) {
			newTable.set(i, null);
			return new BitmappedNode<T>(shift, ~0 ^ mask, newTable);
		} else {
			newTable.set(i, node);
			return new FullNode<T>(shift, newTable);
		}
	}

	@Override
	public int size() {
		int size = 0;
		for (Node<T> n : table) {
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
