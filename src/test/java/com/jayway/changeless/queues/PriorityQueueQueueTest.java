package com.jayway.changeless.queues;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.sequences.Sequence;
import com.jayway.changeless.sequences.Sequences;

public class PriorityQueueQueueTest {
	private Queue<Integer> subject = PriorityQueues.of(0,1,2).add(3).add(4); 
	
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
	public void is_sequencable() throws Exception {
		assertThat(subject.sequence(), is(Sequences.of(0,1,2,3,4)));
	}
	
	@Test
	public void poll_returns_first_element() throws Exception {
		assertThat(subject.poll(), is(Optional.valueOf(4)));
	}
	
	@Test
	public void peek_returns_first_element() throws Exception {
		assertThat(subject.peek(), is(4));
	}
	
	@Test
	public void can_be_created_from_a_sequence() throws Exception {
		Sequence<Integer> elements = Sequences.of(0,1,2,3,4);
		assertThat(subject, is(Queues.of(elements)));
	}
	
	@Test
	public void to_string_returns_sequence_string_representation() throws Exception {
		assertThat(subject.toString(), is(Sequences.of(0,1,2,3,4).toString()));
	}
}