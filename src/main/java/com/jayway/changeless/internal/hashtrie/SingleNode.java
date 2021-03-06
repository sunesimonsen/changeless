package com.jayway.changeless.internal.hashtrie;


abstract class SingleNode<T> implements HashTrie<T> {
	private final int hash;

	public SingleNode(int hash) {
		super();
		this.hash = hash;
	}

	public final int getHash() {
		return hash;
	}

	protected BitmappedNode<T> bitmap(int shift, int hash, T value) {
		int shift1 = (this.hash >>> shift) & 0x1f;
		int shift2 = (hash >>> shift) & 0x1f;
		int tableSize = Math.max(shift1, shift2) + 1;
		Array<HashTrie<T>> table = new Array<HashTrie<T>>(tableSize);
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

	@Override
	public boolean isEmpty() {
		return false;
	}
}