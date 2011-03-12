package com.jayway.collections.functions;

public final class Functions {
	private Functions() {}
	
	public static Fn<Object, String> toStringFunction = new Fn<Object, String>(){
		@Override
		public String apply(Object input) {
			return input.toString();
		}
	};
	
	public static Fn2<Integer, Integer, Integer> plusFunction = 
		new Fn2<Integer, Integer, Integer>() {
		@Override
		public Integer apply(Integer input, Integer result) {
			return input + result;
		}
	};
	
	public static Fn2<StringBuilder, Object, StringBuilder> appendStringFunction =
		new Fn2<StringBuilder, Object, StringBuilder>() {
			@Override
			public StringBuilder apply(StringBuilder result, Object input) {
				return result.append(input);
			}
		};
}
