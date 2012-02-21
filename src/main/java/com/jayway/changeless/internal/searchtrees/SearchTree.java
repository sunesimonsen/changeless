package com.jayway.changeless.internal.searchtrees;

import java.util.Iterator;

import com.jayway.changeless.optionals.Optional;
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
 * and extended with the delete method from Matthew Might article:
 * http://matt.might.net/articles/red-black-delete/
 * 
 * @param <T> The element type. 
 */
public interface SearchTree<T extends Comparable<T>> extends Sequenceable<T> {
	boolean contains(T element);
	boolean isEmpty();
	Optional<T> find(T queue);
	SearchTree<T> add(T element);
	SearchTree<T> remove(T element);
}

interface Node<T extends Comparable<T>> extends SearchTree<T> {
	TreeNode<T> insertInTree(T element);
	Node<T> removeInTree(T element);
	Color getColor();
	boolean isRed();
	boolean isBlack();
	Tuple<Integer, Integer> numberOfNodes();
	Tuple<Integer, Integer> numberOfBlackNodes();
	void ensureRedNodesHasBlackChildren();
	void ensureInvariant();
	Node<T> colorBlack();
	Node<T> colorRed();
	Node<T> balance();
	Node<T> removeMax();
	T max();
	Node<T> bubble();
	Node<T> darken();
	Node<T> lighten();
	Node<T> lightenChildren();
	boolean isDoubleBlack();
	boolean isNegativeBlack();
}

abstract class NodeSupport<T extends Comparable<T>> implements Node<T> {
	public SearchTree<T> add(T element) {
		Guard.notNull(element, "element");
		return insertInTree(element).colorBlack();
	}
	
	public SearchTree<T> remove(T element) {
		Guard.notNull(element, "element");
		return removeInTree(element).colorBlack();
	}
	
	public void ensureInvariant() {
		Tuple<Integer, Integer> numberOfBlackNodes = numberOfBlackNodes();
		if (numberOfBlackNodes.getFirst() != numberOfBlackNodes.getSecond()) {
			String message = String.format("Invariant violation - Every path from "+
					"the root to an empty node contains the same number of black nodes. "+
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
	
	public boolean isDoubleBlack() {
		return getColor() == Color.DOUBLE_BLACK;
	}
	
	@Override
	public boolean isNegativeBlack() {
		return getColor() == Color.NEGATIVE_BLACK;
	}
	
	@Override
	public Iterator<T> iterator() {
		return sequence().iterator();
	}
	
	@Override
	public String toString() {
		return sequence().toString();
	}
	
	public boolean contains(T element) {
		return find(element).hasValue();
	}
}

class TreeNode<T extends Comparable<T>> extends NodeSupport<T> {
	private final Color color;

	private final Node<T> left;
	private final T element;
	private final Node<T> right;
	
	private TreeNode(Color color, Node<T> left, T element, Node<T> right) {
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
	
	public static <T extends Comparable<T>> TreeNode<T> create(
			Color color, Node<T> left, T element, Node<T> right) {
		return new TreeNode<T>(color, left, element, right);
	}
	
	public TreeNode<T> colorRed() {
		return create(Color.RED, left, element, right);
	}
	
	public TreeNode<T> colorBlack() {
		return create(Color.BLACK, left, element, right);
	}
	
	@Override
	public TreeNode<T> insertInTree(T element) {
		if (Comparables.lessThan(element, this.element)) {
			return create(color, left.insertInTree(element), this.element, right).balance();
		} 
		
		if (Comparables.greaterThan(element, this.element)) {
			return create(color, left, this.element, right.insertInTree(element)).balance();
		}
		
		return create(color, left, element, right);
	}

	@Override
	public TreeNode<T> balance() {
		TreeNode<T> balancedTree = null;

		balancedTree = matchLeftRight(this);
		if (balancedTree != null) return balancedTree;

		balancedTree = matchLeftLeft(this);
		if (balancedTree != null) return balancedTree;
		
		balancedTree = matchRightRight(this);
		if (balancedTree != null) return balancedTree;
		
		balancedTree = matchRightLeft(this);
		if (balancedTree != null) return balancedTree;

		if (isDoubleBlack()) {
			balancedTree = matchLeftNegativeBlack(this);
			if (balancedTree != null) return balancedTree;
					
			balancedTree = matchRightNegativeBlack(this);
			if (balancedTree != null) return balancedTree;
		}
		
		return this;
	}

	private TreeNode<T> matchLeftNegativeBlack(TreeNode<T> z) {
		TreeNode<T> x = z.getNegativeBlackLeftChild();
		if (x != null) {
			TreeNode<T> y = (TreeNode<T>) x.getRight();
			Node<T> a = x.getLeft();
			Node<T> b = y.getLeft();
			Node<T> c = y.getRight();
			Node<T> d = z.getRight();
			
			return create(Color.BLACK,
					create(Color.BLACK, a.colorRed(), x.getElement(), b).balance(), 
					y.getElement(),
					create(Color.BLACK, c, z.getElement(), d));
 		}
		return null;
	}
	
	private TreeNode<T> matchRightNegativeBlack(TreeNode<T> x) {
		TreeNode<T> z = x.getNegativeBlackRightChild();
		if (z != null) {
			TreeNode<T> y = (TreeNode<T>) z.getLeft();
			Node<T> a = x.getLeft();
			Node<T> b = y.getLeft();
			Node<T> c = y.getRight();
			Node<T> d = z.getRight();
			
			return create(Color.BLACK,
					create(Color.BLACK, a, x.getElement(), b), 
					y.getElement(),
					create(Color.BLACK, c, z.getElement(), d.colorRed()).balance());
 		}
		return null;
	}
	
	private TreeNode<T> getNegativeBlackRightChild() {
		return right.isNegativeBlack() ? (TreeNode<T>) right : null;
	}

	private TreeNode<T> getNegativeBlackLeftChild() {
		return left.isNegativeBlack() ? (TreeNode<T>) left : null; 
	}

	private static <T extends Comparable<T>> TreeNode<T> matchLeftRight(TreeNode<T> z) {
		TreeNode<T> x = z.getRedLeftChild();
		if (x != null) {
			TreeNode<T> y = x.getRedRightChild();
			if (y != null) {
				Node<T> a = x.getLeft();
				Node<T> b = y.getLeft();
				Node<T> c = y.getRight();
				Node<T> d = z.getRight();
				
				return createBalancedTree(x, y.setColor(z.getColor()), z, a, b, c, d);
			}
		}
		return null;
	}
	
	private static <T extends Comparable<T>> TreeNode<T> matchLeftLeft(TreeNode<T> z) {
		TreeNode<T> y = z.getRedLeftChild();
		if (y != null) {
			TreeNode<T> x = y.getRedLeftChild();
			if (x != null) {
				Node<T> a = x.getLeft();
				Node<T> b = x.getRight();
				Node<T> c = y.getRight();
				Node<T> d = z.getRight();
				
				return createBalancedTree(x, y.setColor(z.getColor()), z, a, b, c, d);
			}
		}
		return null;
	}
	
	private static <T extends Comparable<T>> TreeNode<T> matchRightRight(TreeNode<T> x) {
		TreeNode<T> y = x.getRedRightChild();
		if (y != null) {
			TreeNode<T> z = y.getRedRightChild();
			if (z != null) {
				Node<T> a = x.getLeft();
				Node<T> b = y.getLeft();
				Node<T> c = z.getLeft();
				Node<T> d = z.getRight();
				
				return createBalancedTree(x, y.setColor(x.getColor()), z, a, b, c, d);
			}
		}
		return null;
	}	
	
	private static <T extends Comparable<T>> TreeNode<T> matchRightLeft(TreeNode<T> x) {
		TreeNode<T> z = x.getRedRightChild();
		if (z != null) {
			TreeNode<T> y = z.getRedLeftChild();
			if (y != null) {
				Node<T> a = x.getLeft();
				Node<T> b = y.getLeft();
				Node<T> c = y.getRight();
				Node<T> d = z.getRight();
				
				return createBalancedTree(x, y.setColor(x.getColor()), z, a, b, c, d);
			}
		}
		return null;
	}
	
	private TreeNode<T> getRedLeftChild() {
		return left.isRed() ? (TreeNode<T>) left : null;
	}
	
	private TreeNode<T> getRedRightChild() {
		return right.isRed() ? (TreeNode<T>) right : null;
	}

	private static <T extends Comparable<T>> TreeNode<T> createBalancedTree(
			TreeNode<T> x, TreeNode<T> y, TreeNode<T> z, 
			Node<T> a, Node<T> b, Node<T> c, Node<T> d) {
		
		return createSubTree(x.colorBlack(), y.lighten(), z.colorBlack(), a, b, c, d);
	}
	
	private static <T extends Comparable<T>> TreeNode<T> createSubTree(
			TreeNode<T> x, TreeNode<T> y, TreeNode<T> z, 
			Node<T> a, Node<T> b, Node<T> c, Node<T> d) {
		return create(y.getColor(), 
					create(x.getColor(), a, x.getElement(), b), 
					y.getElement(), 
					create(z.getColor(), c, z.getElement(), d));
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
	
	public Node<T> removeInTree(T element) {
		if (Comparables.lessThan(element, this.element)) {
			return create(color, left.removeInTree(element), this.element, right).bubble();
		} 
		
		if (Comparables.greaterThan(element, this.element)) {
			return create(color, left, this.element, right.removeInTree(element)).bubble();
		}

		// This is the element we would like to remove
		
		if (left.isEmpty() && right.isEmpty()) {
			return isBlack() ? left.darken() : left;
		} else if (left.isEmpty()) {
			return right.colorBlack();
		} else if (right.isEmpty()) {
			return left.colorBlack();
		} 
		
		return create(color, 
				left.removeMax(),
				left.max(),
				right).bubble();	
	}

	@Override
	public Node<T> removeMax() {
		if (isMax()) {
			if (left.isEmpty()) {
				if (isRed()) {
					return right;
				} else {
					return right.darken();
				}
			} 
			return left.colorBlack();
			
		}  
		return create(color, left, element, right.removeMax()).bubble();
	}
	
	@Override
	public Node<T> bubble() {
		if (left.isDoubleBlack() || right.isDoubleBlack()) {
			return lightenChildren().darken().balance();
		}
		
		return this;
	}

	private boolean isMax() {
		return right.isEmpty();
	}

	@Override
	public T max() {
		if (isMax()) {
			return element;
		}
		return right.max();
	}

	@Override
	public Node<T> lightenChildren() {
		return create(color, left.lighten(), element, right.lighten());
	}

	@Override
	public TreeNode<T> darken() {
		return setColor(getColor().darken());
	}
	
	@Override
	public TreeNode<T> lighten() {
		return setColor(getColor().lighten());
	}
	
	public TreeNode<T> setColor(Color color) {
		return create(color, left, element, right);
	}

	@Override
	public Optional<T> find(T query) {
		if (Comparables.lessThan(query, element)) {
			return left.find(query);
		} 
		
		if (Comparables.greaterThan(query, element)) {
			return right.find(query);
		}
		
		return Optional.valueOf(element);
	}
}

class LeafNode<T extends Comparable<T>> extends NodeSupport<T> {
	protected LeafNode() {
		
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}

	public static  <T extends Comparable<T>> Node<T> create() {
		return new LeafNode<T>();
	}

	@Override
	public TreeNode<T> insertInTree(T element) {
		return TreeNode.create(Color.RED, this, element, this);
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
	public Node<T> colorRed() {
		throw new IllegalStateException("Can't color a leaf red");
	}

	@Override
	public Node<T> balance() {
		return this;
	}
	
	@Override
	public Node<T> removeMax() {
		return this;
	}

	@Override
	public T max() {
		throw new IllegalStateException("Empty tree has no max element");
	}

	@Override
	public Node<T> bubble() {
		return this;
	}

	@Override
	public Node<T> lightenChildren() {
		return this;
	}

	@Override
	public Node<T> removeInTree(T element) {
		return this;
	}

	@Override
	public Node<T> darken() {
		return new DoubleBlackLeafNode<T>();
	}

	@Override
	public Node<T> lighten() {
		throw new IllegalStateException("Empty tree can't be lighten");
	}

	@Override
	public Optional<T> find(T queue) {
		return Optional.none();
	}
}

final class DoubleBlackLeafNode<T extends Comparable<T>> extends LeafNode<T> {
	@Override
	public Color getColor() {
		return Color.DOUBLE_BLACK;
	}

	@Override
	public Node<T> colorBlack() {
		return LeafNode.create();
	}
	
	@Override
	public Node<T> lighten() {
		return LeafNode.create();
	}
}

final class TreeSequenece<T extends Comparable<T>> extends LazySequence<T> {

	private final TreeNode<T> tree;

	private TreeSequenece(TreeNode<T> tree) {
		this.tree = tree;
	}
	
	public static <T extends Comparable<T>> Sequence<T> create(TreeNode<T> tree) {
		return new TreeSequenece<T>(tree);
	}
	
	@Override
	public Sequence<T> createSequence() {
		Sequence<T> x = tree.getLeft().sequence(); 
		T y = tree.getElement();
		Sequence<T> z = tree.getRight().sequence();
		
		return x.append(z.add(y));
	}	
}
