package com.jayway.changeless.internal.searchtrees;

import java.util.Iterator;

import com.jayway.changeless.sequences.LazySequence;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequenceable;
import com.jayway.changeless.sequences.Sequences;
import com.jayway.changeless.tuples.Tuple;
import com.jayway.changeless.tuples.Tuples;
import com.jayway.changeless.utilities.Comparables;
import com.jayway.changeless.utilities.Guard;

/**
 * A red-black-tree based on Chris Okasaki's paper:
 * www.eecs.usma.edu/webs/people/okasaki/jfp99.ps
 * 
 * @param <T> The element type. 
 */
public interface SearchTree<T extends Comparable<T>> extends Sequenceable<T> {
	boolean contains(T element);
	boolean isEmpty();
	SearchTree<T> add(T element);
	SearchTree<T> remove(T element);
}

interface Node<T extends Comparable<T>> extends SearchTree<T> {
	ColoredNode<T> insertInTree(T element);
	Color getColor();
	boolean isRed();
	boolean isBlack();
	Tuple<Integer, Integer> numberOfNodes();
	Tuple<Integer, Integer> numberOfBlackNodes();
	void ensureRedNodesHasBlackChildren();
	void ensureInvariant();
	Node<T> colorBlack();
	Node<T> balance();
}

abstract class NodeSupport<T extends Comparable<T>> implements Node<T> {
	public SearchTree<T> add(T element) {
		Guard.notNull(element, "element");
		return insertInTree(element).colorBlack();
	}
	
	public SearchTree<T> remove(T element) {
		Guard.notNull(element, "element");
		throw new UnsupportedOperationException();
	}
	
	public void ensureInvariant() {
		Tuple<Integer, Integer> numberOfBlackNodes = numberOfBlackNodes();
		if (numberOfBlackNodes.getFirst() != numberOfBlackNodes.getSecond()) {
			String message = String.format("Invariant violation - Every path from "+
					"the root to an empty node conrains the same number of black nodes. "+
					"[%s,%s]", numberOfBlackNodes.getFirst(), numberOfBlackNodes.getSecond());
			throw new IllegalStateException(message);
		}
		ensureRedNodesHasBlackChildren();
		
		Tuple<Integer, Integer> numberOfNodes = numberOfNodes();
		if (numberOfNodes.getFirst() * 2 < numberOfNodes.getSecond()) {
			String message = String.format("Invariant violation - The longest path is no "+
					"more than twice as long as the shortest possible path"+
					"[%s,%s]", numberOfBlackNodes.getFirst(), numberOfBlackNodes.getSecond());
			throw new IllegalStateException(message);
		}
	}

	@Override
	public boolean isRed() {
		return getColor() == Color.RED;
	}

	@Override
	public boolean isBlack() {
		return getColor() == Color.BLACK;
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

class ColoredNode<T extends Comparable<T>> extends NodeSupport<T> {
	private final Color color;

	private final Node<T> left;
	private final T element;
	private final Node<T> right;
	
	private ColoredNode(Color color, Node<T> left, T element, Node<T> right) {
		this.color = color;
		this.left = left;
		this.element = element;
		this.right = right;
	}

	public Color getColor() {
		return color;
	}

	public Node<T> getLeft() {
		return left;
	}

	public T getElement() {
		return element;
	}

	public Node<T> getRight() {
		return right;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(T element) {
		if (Comparables.lessThan(element, this.element)) {
			return left.contains(element);
		} 
		
		if (Comparables.greaterThan(element, this.element)) {
			return right.contains(element);
		}
		
		return true;
	}
	
	public static <T extends Comparable<T>> ColoredNode<T> create(
			Color color, Node<T> left, T element, Node<T> right) {
		return new ColoredNode<T>(color, left, element, right);
	}
	
	public Node<T> colorBlack() {
		return create(Color.BLACK, left, element, right);
	}

	@Override
	public ColoredNode<T> insertInTree(T element) {
		if (Comparables.lessThan(element, this.element)) {
			return create(color, left.insertInTree(element), this.element, right).balance();
		} 
		
		if (Comparables.greaterThan(element, this.element)) {
			return create(color, left, this.element, right.insertInTree(element)).balance();
		}
		
		return this;
	}

	@Override
	public ColoredNode<T> balance() {
		ColoredNode<T> balancedTree = null;

		if (isRed()) {
			return this;
		}
				
		balancedTree = matchLeftRight(this);
		if (balancedTree != null) return balancedTree;

		balancedTree = matchLeftLeft(this);
		if (balancedTree != null) return balancedTree;
		
		balancedTree = matchRightRight(this);
		if (balancedTree != null) return balancedTree;
		
		balancedTree = matchRightLeft(this);
		if (balancedTree != null) return balancedTree;
		
		return this;
	}

	private static <T extends Comparable<T>> ColoredNode<T> matchLeftRight(ColoredNode<T> z) {
		ColoredNode<T> x = z.getRedLeftChild();
		if (x != null) {
			ColoredNode<T> y = x.getRedRightChild();
			if (y != null) {
				Node<T> a = x.getLeft();
				Node<T> b = y.getLeft();
				Node<T> c = y.getRight();
				Node<T> d = z.getRight();
				
				return createBalancedTree(x, y, z, a, b, c, d);
			}
		}
		return null;
	}
	
	private static <T extends Comparable<T>> ColoredNode<T> matchLeftLeft(ColoredNode<T> z) {
		ColoredNode<T> y = z.getRedLeftChild();
		if (y != null) {
			ColoredNode<T> x = y.getRedLeftChild();
			if (x != null) {
				Node<T> a = x.getLeft();
				Node<T> b = x.getRight();
				Node<T> c = y.getRight();
				Node<T> d = z.getRight();
				
				return createBalancedTree(x, y, z, a, b, c, d);
			}
		}
		return null;
	}
	
	private static <T extends Comparable<T>> ColoredNode<T> matchRightRight(ColoredNode<T> x) {
		ColoredNode<T> y = x.getRedRightChild();
		if (y != null) {
			ColoredNode<T> z = y.getRedRightChild();
			if (z != null) {
				Node<T> a = x.getLeft();
				Node<T> b = y.getLeft();
				Node<T> c = z.getLeft();
				Node<T> d = z.getRight();
				
				return createBalancedTree(x, y, z, a, b, c, d);
			}
		}
		return null;
	}	
	
	private static <T extends Comparable<T>> ColoredNode<T> matchRightLeft(ColoredNode<T> x) {
		ColoredNode<T> z = x.getRedRightChild();
		if (z != null) {
			ColoredNode<T> y = z.getRedLeftChild();
			if (y != null) {
				Node<T> a = x.getLeft();
				Node<T> b = y.getLeft();
				Node<T> c = y.getRight();
				Node<T> d = z.getRight();
				
				return createBalancedTree(x, y, z, a, b, c, d);
			}
		}
		return null;
	}
	
	private ColoredNode<T> getRedLeftChild() {
		return left.isRed() ? (ColoredNode<T>) left : null;
	}
	
	private ColoredNode<T> getRedRightChild() {
		return right.isRed() ? (ColoredNode<T>) right : null;
	}

	private static <T extends Comparable<T>> ColoredNode<T> createBalancedTree(
			ColoredNode<T> x, ColoredNode<T> y, ColoredNode<T> z, 
			Node<T> a, Node<T> b, Node<T> c, Node<T> d) {
		return create(Color.RED, 
				create(Color.BLACK, a, x.getElement(), b), 
				y.getElement(), 
				create(Color.BLACK, c, z.getElement(), d));
	}

	@Override
	public Sequence<T> sequence() {
		return TreeSequenece.create(this);
	}

	@Override
	public Tuple<Integer, Integer> numberOfBlackNodes() {
		Tuple<Integer, Integer> blacksInLeft = left.numberOfBlackNodes();
		Tuple<Integer, Integer> blacksInRight = right.numberOfBlackNodes();
		int min = Math.min(blacksInLeft.getFirst(), blacksInRight.getFirst());
		int max = Math.max(blacksInLeft.getSecond(), blacksInRight.getSecond());
		int c = isBlack() ? 1 : 0;
		return Tuples.of(c + min, c + max);
	}

	@Override
	public void ensureRedNodesHasBlackChildren() {
		if (isRed() && (left.isRed() || right.isRed())) {
			throw new IllegalStateException("Invariant violation - No red node has a red parent");
		}
		left.ensureRedNodesHasBlackChildren();
		right.ensureRedNodesHasBlackChildren();
	}

	@Override
	public Tuple<Integer, Integer> numberOfNodes() {
		Tuple<Integer, Integer> nodesInLeft = left.numberOfNodes();
		Tuple<Integer, Integer> nodesInRight = right.numberOfNodes();
		int min = Math.min(nodesInLeft.getFirst(), nodesInRight.getFirst());
		int max = Math.max(nodesInLeft.getSecond(), nodesInRight.getSecond());
		return Tuples.of(1 + min, 1 + max);
	}
}


final class EmptyNode<T extends Comparable<T>> extends NodeSupport<T> {
	private EmptyNode() {
		
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public boolean contains(T element) {
		return false;
	}

	public static  <T extends Comparable<T>> SearchTree<T> create() {
		return new EmptyNode<T>();
	}

	@Override
	public ColoredNode<T> insertInTree(T element) {
		return ColoredNode.create(Color.RED, this, element, this);
	}

	@Override
	public Color getColor() {
		return Color.BLACK;
	}

	@Override
	public Sequence<T> sequence() {
		return Sequences.empty();
	}

	@Override
	public Tuple<Integer, Integer> numberOfBlackNodes() {
		return Tuples.of(1, 1);
	}
	
	@Override
	public void ensureRedNodesHasBlackChildren() {}

	@Override
	public Tuple<Integer, Integer> numberOfNodes() {
		return Tuples.of(1, 1);
	}

	@Override
	public Node<T> colorBlack() {
		return this;
	}

	@Override
	public Node<T> balance() {
		return this;
	}
}

final class TreeSequenece<T extends Comparable<T>> extends LazySequence<T> {

	private final ColoredNode<T> tree;

	private TreeSequenece(ColoredNode<T> tree) {
		this.tree = tree;
	}
	
	public static <T extends Comparable<T>> Sequence<T> create(ColoredNode<T> tree) {
		return new TreeSequenece<T>(tree);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Sequence<T> createSequence() {
		Sequence<T> x = tree.getLeft().sequence(); 
		T y = tree.getElement();
		Sequence<T> z = tree.getRight().sequence();
		
		return x.append(z.add(y));
	}	
}
