package com.jayway.changeless.stacks;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

import com.jayway.changeless.stacks.Stack;
import com.jayway.changeless.stacks.Stacks;


public class EmptyStackTest {
	private Stack<Integer> subject = Stacks.empty();
	
	@Test
	public void is_empty() throws Exception {
		assertTrue("Expected stack to be empty", subject.isEmpty());
	}
	
	@Test
	public void has_size_zero() throws Exception {
		assertEquals("Expected size to be zero", 0, subject.size());
	}
	
	
	
	@Test
	public void is_not_empty_after_an_element_is_pushed() throws Exception {
		Stack<Integer> actual = subject.push(13);
		assertFalse("Expected stack to be non-empty", actual.isEmpty());
	}
	
	@Test(expected=EmptyStackException.class)
	public void pop_throws() throws Exception {
		subject.pop();
	}
	
	@Test(expected=EmptyStackException.class)
	public void peek_throws() throws Exception {
		subject.peek();
	}
}
