package dk.jayway.collections.predicates;

public class EvenPredicate implements Predicate<Integer> {

	@Override
	public boolean apply(Integer input) {
		return input.intValue() % 2 == 0;
	}

}
