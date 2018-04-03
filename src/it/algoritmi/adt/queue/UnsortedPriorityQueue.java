package it.algoritmi.adt.queue;

import java.util.Comparator;

import it.algoritmi.adt.Entry;
import it.algoritmi.adt.Position;
import it.algoritmi.adt.PositionalList;
import it.algoritmi.adt.list.LinkedPositionalList;

/** Un'implementazione di coda prioritaria mediante una lista non ordinata. */
public class UnsortedPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

	/** Contenitore principale delle voci della coda prioritaria */
	private PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();
	
	/** Crea una coda prioritaria vuota che usa l'ordine naturale tra le chiavi. */
	public UnsortedPriorityQueue() {
		super();
	}
	
	/** Crea una coda prioritaria vuota che usa il comparatore fornito. */
	public UnsortedPriorityQueue(Comparator<K> comparator) {
		super(comparator);
	}

	/** Restituisce la Position di una delle voci aventi chiave minima. */
	private Position<Entry<K, V>> findMin() {
		Position<Entry<K, V>> small = list.first();
		for(Position<Entry<K, V>> walk : list.positions())
			if(compare(walk.getElement(), small.getElement()) < 0)
				small = walk;
		return small;
	}

	/** Restituisce il numero di voci presenti nella coda prioritaria. */
	@Override
	public int size() {
		return list.size();
	}

	/** Inserisce una coppia chiave-valore e restituisce la voce creata. */
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key); // metodo ausiliario di verifica (può lanciare eccezione)
		Entry<K, V> newest = new PQEntry<>(key, value);
		list.addLast(newest);
		return newest;
	}

	/** Restituisce una delle voci aventi chiave minima (senza rimuoverla). */
	@Override
	public Entry<K, V> min() {
		if(list.isEmpty()) return null;
		return findMin().getElement();
	}

	/** Elimina e restituisce una delle voci aventi chiave minima. */
	@Override
	public Entry<K, V> removeMin() {
		if(list.isEmpty()) return null;
		return list.remove(findMin());
	}

}
