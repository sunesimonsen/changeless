package dk.jayway.collections.immutable.sequences;

import dk.jayway.test.EqualsAndHashcodeTestSupport;

public class SequenceEqualsAndHashcodeWithEmptySequenceTest extends EqualsAndHashcodeTestSupport<Sequence<Integer>> {

	@Override
	protected Sequence<Integer> createFirstInstance() {
		return Sequences.of();
	}

	@Override
	protected Sequence<Integer> createSecondInstance() {
		return Sequences.of(40,41,42);
	}

}
