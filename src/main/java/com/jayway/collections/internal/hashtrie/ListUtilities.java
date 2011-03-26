package com.jayway.collections.internal.hashtrie;

import java.util.ArrayList;
import java.util.List;

final class ListUtilites {
	public static <T> List<T> createListWithNullValues(int tableSize) {
		List<T> table = new ArrayList<T>(tableSize);
		for (int i = 0; i < tableSize; i++) {
			table.add(null);
		}
		return table;
	}
}