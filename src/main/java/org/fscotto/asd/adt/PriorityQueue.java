package org.fscotto.asd.adt;

/**
 * Interfaccia per l' ADT coda prioritaria.
 * 
 * @author fabio
 *
 * @param <K>
 * @param <V>
 */
public interface PriorityQueue<K extends Comparable<K>, V> {

	int size();
	
	boolean isEmpty();
	
	Entry<K, V> insert(K key, V value);
	
	Entry<K, V> min();
	
	Entry<K, V> removeMin();
	
}
