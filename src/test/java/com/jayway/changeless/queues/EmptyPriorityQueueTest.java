package com.jayway.changeless.queues;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.NoSuchElementException;

import org.junit.Test;

import com.jayway.changeless.optionals.Optional;

public class EmptyPriorityQueueTest {
	private Queue<String> subject = PriorityQueues.empty(); 
	
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
	
	@Test
	public void poll_returns_none() throws Exception {
		Optional<String> expected = Optional.none();
		assertThat(subject.poll(), is(expected));
	}
}
