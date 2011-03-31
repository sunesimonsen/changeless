package com.jayway.changesless.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;


/**
 * A base class for testing {@link Iterable} types.
 */
public abstract class IterableTestSupport<T> {
	/**
	 * Creates an {@link Iterable} with one or more elements, 
	 * preferable 5.
	 * @return the created {@link Iterable}.
	 */
	protected abstract Iterable<T> createIterableWithOneOrMoreElements();
	
	private Iterator<T> iterator;
	private Iterable<T> iterable;
	
	@Before
	public void baseSetup() {
		iterable = createIterableWithOneOrMoreElements();
		iterator = iterable.iterator();
	}
	
	/**
	 * Tests that hasNext() on non-empty iterator returns true
	 */
	@Test
	public void nonEmptyIterableHasNextElement() throws Exception {
		assertTrue("hasNext() on non-empty iterator is expected to return true", 
				iterator.hasNext());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void callingNextOnAnIteratorWithNoMoreElements() throws Exception {
		while (iterator.hasNext()) {
			iterator.next();
		}
		iterator.next();
	}
	
	/**
	 * Tests that calling remove() before next() either throws an
	 * {@link UnsupportedOperationException} or an {@link IllegalArgumentException}.
	 */
	@Test
	public void removeBeforeCallingNext() throws Exception {
		try {
			iterator.remove();
			fail();
		} catch (UnsupportedOperationException e) {
		} catch (IllegalStateException e) {
		}
	}
	
	/**
	 * Tests that calling remove() two time after next() either throws an
	 * {@link UnsupportedOperationException} or an {@link IllegalArgumentException}.
	 */
	@Test
	public void doubleRemoveAfterCallingNext() throws Exception {
		try {
			iterator.next();
			iterator.remove();
			iterator.remove();
			fail();
		} catch (UnsupportedOperationException e) {
		} catch (IllegalStateException e) {
		}
	}
	
	/**
	 * Tests that calling remove() after next() either throws an
	 * {@link UnsupportedOperationException} or removed the element
	 * next returned.
	 */
	@Test
	public void removeAfterCallingNext() throws Exception {
		try {
			Sequence<T> beforeRemove = Sequences.copyOf(iterable);
			
			T removedElement = iterator.next();
			iterator.remove();

			Sequence<T> afterRemove = Sequences.copyOf(iterable);
			assertEquals("Element was not removed from iterable, iterable should be decreased it size by one", 
					beforeRemove.size() - 1, afterRemove.size());
			for (int i = 0; i < afterRemove.size(); i++) {
				assertSame("The iterable was corrupted after remove, the rest of the iterable should not be changed", 
						beforeRemove.get(i + 1), afterRemove.get(i));
			}
			assertSame("Element returned by next() should be the element removed from the iterable", 
					beforeRemove.get(0), removedElement);
		} catch (UnsupportedOperationException e) {
			
		}
	}
}
