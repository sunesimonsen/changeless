package com.jayway.changeless.queues;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.jayway.changeless.sequences.Sequences;

public class QueueTest {
	private Queue<Integer> subject = Queues.of(0,1,2).add(3).add(4); 
	
	@Test
	public void is_not_empty() throws Exception {
		assertThat(subject.isEmpty(), is(false));
	}
	
	@Test
	public void has_size_ten() throws Exception {
		assertThat(subject.size(), is(5));
	}
	
	@Test
	public void increases_size_by_one_when_adding_an_element() throws Exception {
		assertThat(subject.add(10).size(), is(subject.size() + 1));
	}
	
	@Test
	public void decreases_size_by_one_when_removing_an_element() throws Exception {
		assertThat(subject.remove().size(), is(subject.size() - 1));
	}
	
	@Test
	public void all_elements_can_be_removed() throws Exception {
		Queue<Integer> actual = subject.remove().remove().remove().remove().remove();
		assertThat(actual.isEmpty(), is(true));
	}
	
	@Test
	public void elements_can_be_removed_in_insertion_order() throws Exception {
		Queue<Integer> queue = subject;
		for (int i = 0; !queue.isEmpty(); i++) {
			assertThat(queue.peek(), is(i));
			queue = queue.remove();
		}
	}
	
	@Test
	public void is_sequencable() throws Exception {
		assertThat(subject.sequence(), is(Sequences.of(0,1,2,3,4)));
	}
}