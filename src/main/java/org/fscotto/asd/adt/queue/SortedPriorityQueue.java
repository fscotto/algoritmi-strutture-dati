package org.fscotto.asd.adt.queue;

import java.util.Comparator;

import org.fscotto.asd.adt.PositionalList;
import org.fscotto.asd.adt.Entry;
import org.fscotto.asd.adt.Position;
import org.fscotto.asd.adt.list.LinkedPositionalList;

public class SortedPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

	/** Contenitore principale delle voci della coda prioritaria */
	private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();
	
	/** Crea una coda prioritaria vuota che usa l'ordine naturale tra le chiavi. */
	public SortedPriorityQueue() {
		super();
	}
	
	public SortedPriorityQueue(Comparator<K> comparator) {
		this.comparator = comparator;
	}

	@Override
	public int size() {
		return list.size();
	}
	

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key); // metodo ausiliario di verifica (può lanciare eccezione)
		Entry<K, V> newest = new PQEntry<>(key, value);
		Position<Entry<K, V>> walk = list.last();
		// procede a ritroso, cercando chiavi minori
		while (walk != null && compare(newest, walk.getElement()) < 0)
			walk = list.before(walk);
		if (walk == null)
			list.addFirst(newest);
		else
			list.addAfter(walk, newest);
		return newest;
	}
	

	@Override
	public Entry<K, V> min() {
		if(list.isEmpty()) return null;
		return list.first().getElement();
	}
	

	@Override
	public Entry<K, V> removeMin() {
		if(list.isEmpty()) return null;
		return list.remove(list.first());
	}

}
