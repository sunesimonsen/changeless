package com.jayway.changeless.functions.integers;

import com.jayway.changeless.functions.Fn2;

public class PlusFunction implements Fn2<Integer, Integer, Integer> {
	@Override
	public Integer apply(Integer input, Integer result) {
		return input + result;
	}
}
