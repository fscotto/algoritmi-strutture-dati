package org.fscotto.asd.adt.queue;

import java.util.Comparator;

import org.fscotto.asd.adt.AdaptablePriorityQueue;
import org.fscotto.asd.adt.Entry;

public class HeapAdaptablePriorityQueue<K extends Comparable<K>, V> extends HeapPriorityQueue<K, V>
		implements AdaptablePriorityQueue<K, V> {

	// ---------------- class AdaptablePQEntry annidata ----------------
	/**
	 * Estensione della classe PQEntry per aggiungere informazioni sulla posizione.
	 */
	protected static class AdaptablePQEntry<K extends Comparable<K>, V> extends PQEntry<K, V> {

		private int index; // indice della voce nell'array che realizza lo heap

		public AdaptablePQEntry(K key, V value, int j) {
			super(key, value);
			setIndex(j);
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}
	// ---------------- fine della classe AdaptablePQEntry annidata --------

	/** Crea una coda prioritaria flessibile vuota che usa l'ordine naturale. */
	public HeapAdaptablePriorityQueue() {
		super();
	}

	/**
	 * Crea una coda prioritaria flessibile vuota che usa il comparatore fornito.
	 */
	public HeapAdaptablePriorityQueue(Comparator<K> comparator) {
		super(comparator);
	}

	/** Determina se una voce è valida, controllando la sua posizione. */
	protected AdaptablePQEntry<K, V> validate(Entry<K, V> entry) throws IllegalArgumentException {
		if (!(entry instanceof AdaptablePQEntry))
			throw new IllegalArgumentException("Invalid entry");
		AdaptablePQEntry<K, V> locator = (AdaptablePQEntry<K, V>) entry; // cast sicuro
		int j = locator.getIndex();
		if (j >= heap.size() || heap.get(j) != locator)
			throw new IllegalArgumentException("Invalid entry");
		return locator;
	}

	/** Scambia tra loro le voci delle celle i e j dell'array. */
	@Override
	protected void swap(int i, int j) {
		super.swap(i, j);
		((AdaptablePQEntry<K, V>) heap.get(i)).setIndex(i);
		((AdaptablePQEntry<K, V>) heap.get(j)).setIndex(j);
	}
	
	/** Ripristina la priorità di ordinamento spostando la voce j in altro/basso. */
	protected void bubble(int j) {
		if (j > 0 && compare(heap.get(j), heap.get(parent(j))) < 0)
			upheap(j);
		else
			downheap(j); // però può darsi che non sia necessario alcuno spostamento
	}
	
	/** Inserisce una coppia chiave-valore e restituisce la voce creata. */
	@Override
	public Entry<K, V> insert(K key, V value) {
		checkKey(key);
		Entry<K, V> newest = new AdaptablePQEntry<K, V>(key, value, heap.size());
		heap.add(newest);
		upheap(heap.size() - 1);
		return newest;
	}
	
	/** Elimina dalla coda prioritaria la voce ricevuta. */

	@Override
	public void remove(Entry<K, V> entry) {
		AdaptablePQEntry<K, V> locator = validate(entry);
		int j = locator.getIndex();
		if (j == heap.size() - 1)			// la voce si trova nell'ultima posizione
			heap.remove(heap.size() - 1);	// quindi basta eliminarla
		else {
			swap(j, heap.size() - 1);
			heap.remove(heap.size() - 1);
			bubble(j);
		}
	}

	/** Sostituisce la chiave di una voce. */
	@Override
	public void replaceKey(Entry<K, V> entry, K key) {
		AdaptablePQEntry<K, V> locator = validate(entry);
		checkKey(key);
		locator.setKey(key);
		bubble(locator.getIndex());
	}

	/** Sostituisce il valore di una voce. */
	@Override
	public void replaceValue(Entry<K, V> entry, V value) {
		AdaptablePQEntry<K, V> locator = validate(entry);
		locator.setValue(value);
	}

}
