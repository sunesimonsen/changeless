package com.jayway.changeless.queues;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Test;

public class EmptyQueueTest {
	private Queue<String> subject = Queues.empty(); 
	
	@Test
	public void is_empty() throws Exception {
		assertThat(subject.isEmpty(), is(true));
	}
	
	@Test
	public void has_size_zero() throws Exception {
		assertThat(subject.size(), is(0));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void remove_throws() throws Exception {
		subject.remove();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void peek_throws() throws Exception {
		subject.peek();
	}
}
