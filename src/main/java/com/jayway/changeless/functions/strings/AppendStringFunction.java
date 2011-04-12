package com.jayway.changeless.functions.strings;

import com.jayway.changeless.functions.Fn2;

public class AppendStringFunction implements Fn2<StringBuilder, Object, StringBuilder> {
	@Override
	public StringBuilder apply(StringBuilder result, Object input) {
		return result.append(input);
	}
}
