package org.fscotto.asd.adt;

public interface AdaptablePriorityQueue<K extends Comparable<K>, V> extends PriorityQueue<K, V> {

	public void remove(Entry<K, V> entry);
	
	public void replaceKey(Entry<K, V> entry, K key);
	
	public void replaceValue(Entry<K, V> entry, V value);
	
}
