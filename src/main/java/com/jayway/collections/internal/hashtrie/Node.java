/**
 * This is an implementation of an immutable hash-trie is based on Daniel Spiewak
 * HashTrie implementation for Scala that is in turn a clean-room port of 
 * Rich Hickey's persistent hash trie implementation from Clojure 
 * (http://clojure.org). Originally presented as a mutable structure in a paper 
 * by Phil Bagwell. The implementation was also heavily inspired by Eugene 
 * Vigdorchik's blog post: 
 * http://groovy.dzone.com/articles/immutable-data-structures
 * 
 * @author Rich Hickey
 * @author Daniel Spiewak
 * @author Sune Simonsen
 */

package com.jayway.collections.internal.hashtrie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.jayway.collections.Optional;
import com.jayway.collections.immutable.sequences.LazySequence;
import com.jayway.collections.immutable.sequences.Sequence;
import com.jayway.collections.immutable.sequences.Sequenceable;
import com.jayway.collections.immutable.sequences.Sequences;
import com.jayway.collections.predicates.Predicate;
import com.jayway.collections.utilities.Guard;


public interface Node<T> extends Sequenceable<T> {
	int size();
	Node<T> add(int shift, int hash, T value);
	Optional<T> get(T value, int hash);
	Node<T> remove(T value, int hash);
}

class ListUtilites {
	public static <T> List<T> createListWithNullValues(int tableSize) {
		List<T> table = new ArrayList<T>(tableSize);
		for (int i = 0; i < tableSize; i++) {
			table.add(null);
		}
		return table;
	}
}

abstract class SingleNode<T> implements Node<T> {
	private final int hash;

	public SingleNode(int hash) {
		super();
		this.hash = hash;
	}

	public int getHash() {
		return hash;
	}

	protected BitmappedNode<T> bitmap(int shift, int hash, T value) {
		int shift1 = (getHash() >>> shift) & 0x1f;
		int shift2 = (hash >>> shift) & 0x1f;
		int tableSize = Math.max(shift1, shift2) + 1;
		List<Node<T>> table = ListUtilites.createListWithNullValues(tableSize);
		table.set(shift1, this);
		int bits1 = 1 << shift1;
		int bits2 = 1 << shift2;
		if (shift1 == shift2) {
			table.set(shift2, table.get(shift2).add(shift + 5, hash, value));
		} else {
			table.set(shift2, new LeafNode<T>(hash, value));
		}
		return new BitmappedNode<T>(shift, bits1 | bits2, table);
	}
}

class LeafNode<T> extends SingleNode<T> {
	private final T value;

	public LeafNode(int hash, T value) {
		super(hash);
		this.value = value;
	}

	@Override
	public Node<T> add(int shift, int hash, T value) {
		if (this.value.equals(value)) {
			return this;
		} else if (getHash() == hash) {
			Sequence<T> bucket = Sequences.of(this.value, value);
			return new CollisionNode<T>(hash, bucket);
		} else {
			return bitmap(shift, hash, value);
		}
	}

	@Override
	public Optional<T> get(T value, int hash) {
		if (this.value.equals(value)) {
			return Optional.valueOf(this.value);
		}

		return Optional.none();
	}

	@Override
	public Node<T> remove(T value, int hash) {
		if (this.value.equals(value)) {
			return new EmptyNode<T>();
		}

		return this;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public Sequence<T> sequence() {
		return Sequences.of(value);
	}
	
	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
}

class CollisionNode<T> extends SingleNode<T> {

	private final Sequence<T> bucket;

	public CollisionNode(int hash, Sequence<T> bucket) {
		super(hash);
		this.bucket = bucket;
	}

	@Override
	public Node<T> add(int shift, int hash, T value) {
		if (getHash() == hash) {

			// TODO use remove
			Sequence<T> newBucket = bucket.filter(
					new NotEqual<T>(value)).add(value);

			return new CollisionNode<T>(hash, newBucket);
		} else {
			return bitmap(shift, hash, value);
		}
	}

	@Override
	public Optional<T> get(T value, int hash) {
		for (T element : bucket) {
			if (element.equals(value)) {
				return Optional.valueOf(element);
			}
		}
		return Optional.none();
	}

	@Override
	public Node<T> remove(T value, int hash) {
		Sequence<T> newBucket = bucket.filter(
				new NotEqual<T>(value));

		int newBucketSize = newBucket.size();
		if (newBucketSize == bucket.size()) {
			return this;
		} else {
			if (newBucketSize == 1) {
				T first = newBucket.first();
				return new LeafNode<T>(hash, first);
			} else {
				return new CollisionNode<T>(hash, newBucket);
			}
		}
	}

	@Override
	public int size() {
		return bucket.size();
	}

	@Override
	public Sequence<T> sequence() {
		return bucket;
	}

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
}

class NotEqual<T> implements Predicate<T> {

	private final T value;

	public NotEqual(T value) {
		this.value = value;
	}

	@Override
	public boolean apply(T input) {
		return !value.equals(input);
	}

}

class BitmappedNode<T> implements Node<T> {

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
			} else {
				List<Node<T>> newTable = ListUtilites
						.createListWithNullValues(table.size());
				Collections.copy(newTable, table);
				newTable.set(i, node);
				return new BitmappedNode<T>(shift, bits, newTable);
			}
		} else {
			List<Node<T>> newTable = ListUtilites.createListWithNullValues(Math
					.max(table.size(), i + 1));
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

		if ((bits & mask) == mask) {
			Node<T> node = table.get(i).remove(value, hash);

			if (node == table.get(i)) {
				return this;
			} else if (node instanceof EmptyNode<?>) {
				if (size() == 1) {
					return new EmptyNode<T>();
				} else {
					int adjustedBits = bits ^ mask;
					double log = Math.log(adjustedBits) / Math.log(2);

					if (Math.floor(log) == log) { // last one
						return table.get((int) log);
					} else {
						int length = (i + 1 == table.size()) ? table.size() - 1
								: table.size();
						List<Node<T>> newTable = new ArrayList<Node<T>>(length);
						for (int j = 0; j < length; j++) {
							newTable.add(table.get(j));
						}
						
						if (i != length) {
							newTable.set(i, null);	
						}

						return new BitmappedNode<T>(shift, adjustedBits,
								newTable);
					}
				}
			} else {
				List<Node<T>> newTable = new ArrayList<Node<T>>(table.size());
				for (Node<T> n : table) {
					newTable.add(n);
				}
				newTable.set(i, node);

				return new BitmappedNode<T>(shift, bits, newTable);
			}
		} else {
			return this;
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
	public Sequence<T> sequence() {
		Iterable<Node<T>> tableWithoutNulls = new SkipNullIteratorSequence<Node<T>>(table.iterator());
		return Sequences.appended(tableWithoutNulls);
	}

	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
}

class SkipNullIteratorSequence<T> extends LazySequence<T> {

	private final Iterator<T> source;

	public SkipNullIteratorSequence(Iterator<T> source) {
		Guard.notNull(source, "source");
		this.source = source;
	}
	
	@Override
	public Sequence<T> createSequence() {
		if (!source.hasNext()) {
			return Sequences.empty();
		}
		T first = source.next();
		while (first == null && source.hasNext()) {
			first = source.next();
		}
		if (first == null) {
			return Sequences.empty();
		}
		Sequence<T> rest = new SkipNullIteratorSequence<T>(source);
		return rest.add(first);
	}
}

class FullNode<T> implements Node<T> {

	private final List<Node<T>> table;
	private final int shift;

	public FullNode(int shift, List<Node<T>> table) {
		this.shift = shift;
		this.table = table;
	}

	@Override
	public Node<T> add(int levelShift, int hash, T value) {
		int i = (hash >>> shift) & 0x1f;
		Node<T> node = table.get(i);
		Node<T> foundNode = node.add(shift + 5, hash, value);
		if (foundNode == node) { // TODO I don't think this can happen
			return this;
		} else {
			List<Node<T>> newTable = new ArrayList<Node<T>>(32);
			for (Node<T> n : table) {
				newTable.add(n);
			}
			newTable.set(i, foundNode);
			return new FullNode<T>(shift, newTable);
		}
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

		if (node == table.get(i)) { // TODO I don't think this can happen.
			return this;
		} else {
			List<Node<T>> newTable = new ArrayList<Node<T>>(32);
			for (Node<T> n : table) {
				newTable.add(n);
			}

			if (node instanceof EmptyNode<?>) {
				newTable.set(i, null);
				return new BitmappedNode<T>(shift, ~0 ^ mask, newTable);
			} else {
				newTable.set(i, node);
				return new FullNode<T>(shift, newTable);
			}
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
	public Sequence<T> sequence() {
		return Sequences.appended(table);
	}
	
	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
}
