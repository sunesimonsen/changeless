package com.jayway.changeless.test;

import com.jayway.changeless.functions.Fn2;

public class IndexIntoString implements Fn2<Integer, Object, String> {
    public String apply(Integer x, Object y) {
        return x + ":" + y;
    }
}
	