package com.jayway.changeless.stacks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jayway.changeless.stacks.Stack;
import com.jayway.changeless.stacks.Stacks;


public class StackTest {
	private Stack<Integer> subject = Stacks.of(3,1,65,3,1);
	
	@Test
	public void has_size_one_after_an_element_is_pushed() throws Exception {
		Stack<Integer> actual = subject.push(13);
		assertEquals("Expected the size of the stack to grow by one", subject.size() + 1, actual.size());
	}
	
	@Test
	public void size_is_not_change_by_a_push_followed_by_a_pop() throws Exception {
		Stack<Integer> actual = subject.push(13).pop();
		assertEquals("Expected the size to be unchanged", subject.size(), actual.size());
	}
	
	@Test
	public void peek_returns_the_top_element() throws Exception {
		assertEquals("Expected top element", Integer.valueOf(3), subject.peek());
	}
	
	@Test
	public void to_string_on_stack_is_readable() throws Exception {
		String actual = subject.toString();
		String expected = "(3,1,65,3,1)";
		assertEquals("Expected to string on stack to be readable", expected, actual);
	}
	
	@Test
	public void pop_returns_a_stack_without_the_top_element() throws Exception {
		Stack<Integer> actual = subject.pop();
		Stack<Integer> expected = Stacks.of(1,65,3,1);
		assertEquals("Expected stack after pop", expected, actual);
	}
}
