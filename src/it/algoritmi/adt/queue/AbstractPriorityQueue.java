package it.algoritmi.adt.queue;

import java.util.Comparator;

import it.algoritmi.adt.Entry;
import it.algoritmi.adt.PriorityQueue;

public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	//--------------------------- classe PQEntry annidata --------------------------------
	protected static class PQEntry<K extends Comparable<K>, V> implements Entry<K, V> {
		private K key;
		private V value;
		
		public PQEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
	}
	//-------- fine della classe annidata ------------------------

	/** Il comparatore che definisce l'ordine tra le chiavi della coda prioritaria */
	private Comparator<K> comparator;
	
	/** Crea una coda prioritaria vuota che usa il comparatore fornito. */
	protected AbstractPriorityQueue(Comparator<K> comparator) {
		this.comparator = comparator;
	}
	
	/** Crea una coda prioritaria vuota che usa l'ordine naturale tra le chiavi */
	protected AbstractPriorityQueue() {
		this((o1, o2) -> o1.compareTo(o2));
	}

	/** Metodo per confrontare due entità in base alle loro chiavi. */
	protected int compare(Entry<K, V> a, Entry<K, V> b) {
		return comparator.compare(a.getKey(), b.getKey());
	}
	
	/** Determina se la chiave è valida. */
	protected boolean checkKey(K key) {
		try {
			return (comparator.compare(key, key)) == 0;
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}
	
	/** Verifica se la coda prioritaria è vuota. */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

}
