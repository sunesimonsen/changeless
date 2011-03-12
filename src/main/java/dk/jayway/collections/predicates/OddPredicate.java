package dk.jayway.collections.predicates;

public class OddPredicate implements Predicate<Integer> {

	@Override
	public boolean apply(Integer source) {
		return source % 2 == 1;
	}

}
