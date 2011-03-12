package dk.jayway.collections.functions;


class PlusFunction implements Fn2<Integer, Integer, Integer> {
	@Override
	public Integer apply(Integer input, Integer result) {
		return input + result;
	}
}
