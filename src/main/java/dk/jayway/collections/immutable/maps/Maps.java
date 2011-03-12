package dk.jayway.collections.immutable.maps;

public class Maps {
	public static <K,V> Map<K, V> empty() {
		return ImmutableHashMap.empty();
	}
}
