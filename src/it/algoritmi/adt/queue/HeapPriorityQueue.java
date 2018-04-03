package it.algoritmi.adt.queue;

import java.util.ArrayList;
import java.util.Comparator;

import it.algoritmi.adt.Entry;

public class HeapPriorityQueue<K extends Comparable<K>, V> extends AbstractPriorityQueue<K, V> {

	/** Contenitore principale delle voci della coda prioritaria. */
	protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

	/** Crea una coda prioritaria vuota che usa l'ordine naturale tra le chiavi. */
	public HeapPriorityQueue() {
		super();
	}

	/** Crea una coda prioritaria vuota che usa il comparatore fornito. */
	public HeapPriorityQueue(Comparator<K> comparator) {
		this.comparator = comparator;
	}

	/** Crea una coda prioritaria contenente le coppie chiave-valore fornite. */
	public HeapPriorityQueue(K[] keys, V[] values) {
		super();
		for (int j = 0; j < Math.min(keys.length, values.length); j++)
			heap.add(new PQEntry<K, V>(keys[j], values[j]));
		heapify();
	}
	
	protected int parent(int j) {
		return (j - 1) / 2;
	}

	protected int left(int j) {
		return 2 * j + 1;
	}

	protected int right(int j) {
		return 2 * j + 2;
	}

	protected boolean hasLeft(int j) {
		return left(j) < heap.size();
	}

	protected boolean hasRight(int j) {
		return right(j) < heap.size();
	}

	/** Effettua la costruzione bottom-up dello heap in un tempo lineare. */
	protected void heapify() {
		int startIndex = parent(size() - 1); // inizia dal genitore dell'ultima entità
		for (int j = startIndex; j >= 0; j--)
			downheap(j);
	}
	
	/** Scambia tra loro nella lista le voci di indice i e j. */
	protected void swap(int i, int j) {
		Entry<K, V> tmp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, tmp);
	}

	/** Sposta in alto la voce di indice j, se necessario per l'ordinamento */
	protected void upheap(int j) {
		while (j > 0) {
			int p = parent(j);
			if (compare(heap.get(j), heap.get(p)) >= 0)
				break;
			swap(j, p);
			j = p;
		}
	}

	/** Sposta in alto la voce di indice j, se necessario per l'ordinamento. */
	protected void downheap(int j) {
		while (hasLeft(j)) { // prosegue verso il basso (o fino a break)
			int leftIndex = left(j);
			int smallChildIndex = leftIndex; // il destro può essere minore
			if (hasRight(j)) {
				int rightIndex = right(j);
				if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
					smallChildIndex = rightIndex; // il figlio destro è minore
			}
			if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
				break; // ordinamento corretto
			swap(j, smallChildIndex);
			j = smallChildIndex; // prosegue dalla posizione del figlio prescelto
		}
	}

	/** Restituisce il numero di voci presenti nella coda prioritaria. */
	@Override
	public int size() {
		return heap.size();
	}

	/** Inserisce una coppia chiave-valore e restituisce la voce creata. */
	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key); // metodo ausiliario di verifica (può lanciare eccezione)
		Entry<K, V> newest = new PQEntry<>(key, value);
		heap.add(newest);			// aggiunge alla fine della lista
		upheap(heap.size() - 1);	// esegue up-heap per la voce appena aggiunta
		return newest;
	}

	/** Restituisce una della voci aventi chiave minima (senza rimuoverla). */
	@Override
	public Entry<K, V> min() {
		if (heap.isEmpty()) return null;
		return heap.get(0);
	}

	/** Elimina e restituisce una delle voci aventi chiave minima. */
	@Override
	public Entry<K, V> removeMin() {
		if (heap.isEmpty()) return null;
		Entry<K, V> answer = heap.get(0);
		swap(0, heap.size() - 1);		// sposta l'elemento minimo alla fine
		heap.remove(heap.size() - 1);	// e lo rimuove dall lista
		downheap(0);					// quindi sistema la radice con down-heap
		return answer;
	}

}
