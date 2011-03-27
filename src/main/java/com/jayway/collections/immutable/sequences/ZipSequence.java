package com.jayway.collections.immutable.sequences;

import com.jayway.collections.tuples.Tuple;
import com.jayway.collections.tuples.Tuples;

final class ZipSequence<T1,T2> extends LazySequence<Tuple<T1,T2>>{
	private final Sequence<T1> s1;
	private final Sequence<T2> s2;

	private ZipSequence(Sequence<T1> s1, Sequence<T2> s2) {
		this.s1 = s1;
		this.s2 = s2;
	}
	
	public static <T1,T2> Sequence<Tuple<T1, T2>> create(Sequence<T1> s1, Sequence<T2> s2) {
		if (s1.isEmpty() || s2.isEmpty()) {
			return Sequences.empty();
		}
		return new ZipSequence<T1, T2>(s1, s2);
	}

	@Override
	public Sequence<Tuple<T1, T2>> createSequence() {
		Tuple<T1,T2> first = Tuples.of(s1.first(), s2.first());
		Sequence<Tuple<T1, T2>> rest = create(s1.rest(), s2.rest());
		return Sequences.append(first, rest);
	}
}
