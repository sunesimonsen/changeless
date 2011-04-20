package com.jayway.changeless.functions;


public final class Functions {
	private Functions() { /* Static class */ }
	
	public static Fn<Object, String> toStringFunction = new Fn<Object, String>(){
		@Override
		public String apply(Object input) {
			return input.toString();
		}
	};
}
