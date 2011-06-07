package com.jayway.changeless.sequences;

/**
 * A support class that can be extends in order to easily create lazy 
 * sequences.
 * 
 * @param <T> The type of the elements in this sequence.
 */
public abstract class LazySequence<T> extends SequenceSupport<T> {
	private Sequence<T> cachedValue;
	
	/**
	 * <p>
	 * Implement this method to create a lazy sequence.
	 * </p>
	 * <p>
	 * How to implement this method is best shown by an example.<br>
	 * Let's implement the lazy sequence of natural numbers. 
	 * </p>
	 * <p>
	 * <code>
	 * <pre>
	 * class NaturalNumbers extends LazySequence&lt;Integer&gt; {
	 *   private final int head;
	 * 	 
	 *   public NaturalNumbers() {
	 *     this(1);
	 *   }
	 *     
	 *   private NaturalNumbers(int head) {
	 *     this.head = head;
	 *   }
	 *   
	 *   public Sequence&lt;Integer&gt; createSequence() {
	 *     Sequence&lt;Integer&gt; rest = new NaturalNumbers(head+1);
	 *     return rest.add(head);
	 *   }
	 * }
	 * </pre>
	 * </code>
	 * </p>
	 * <p>
	 * As you can see the above definition of the natural numbers is just 
	 * a recursive definition. This is a very common pattern for creating 
	 * new lazy sequences. 
	 * </p>
	 * @return returns the lazy sequence.
	 */
	public abstract Sequence<T> createSequence();
	
	@Override
	public T first() {
		return getCachedValue().first();
	}

	@Override
	public Sequence<T> rest() {
		return getCachedValue().rest();
	}

	@Override
	public boolean isEmpty() {
		return getCachedValue().isEmpty();
	}

	private synchronized Sequence<T> getCachedValue() {
		if (cachedValue == null) {
			cachedValue = createSequence();
		}	
		return cachedValue;
	}
}
