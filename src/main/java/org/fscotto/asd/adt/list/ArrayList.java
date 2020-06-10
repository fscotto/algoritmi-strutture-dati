package org.fscotto.asd.adt.list;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.fscotto.asd.adt.List;

public class ArrayList<E> implements List<E> {
	// variabili di esemplare
	public static final int CAPACITY = 16;	// capacità predefinita dell'array
	
	private E[] data;						// array generico per memorizzare gli elementi
	private int size;						// numero di elementi nella lista
	
	/**
	 * Costruttore di una lista standard
	 */
	public ArrayList() {
		this(CAPACITY);
	}
	
	/**
	 * Costruisce una lista con capacità data
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		data = (E[]) new Object[capacity];
		size = 0;
	}
	
	/**
	 * Restituisce il numero di elementi presenti nella lista.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Restituisce true se e solo se la lista è vuota.
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Restituisce l'elemento corrispondente all'indice i,
	 * senza eliminarlo.
	 */
	@Override
	public E get(int i) {
		checkIndex(i, size);
		return data[i];
	}

	/**
	 * Sostituisce con e l'elemento di indice i;
	 * restituisce l'elemento sostituito.
	 */
	@Override
	public E set(int i, E e) {
		checkIndex(i, size);
		E tmp = data[i];
		data[i] = e;
		return tmp;
	}

	/**
	 * Inserisce e come elemento di indice i, 
	 * spostando gli elementi successivi.
	 */
	@Override
	public void add(int i, E e) {
		checkIndex(i, size + 1);
		if(size == data.length) resize(2 * data.length);
		for(int k = size - 1; k >= i; --k) data[k + 1] = data[k];
		data[i] = e;
		++size;
	}

	/**
	 * Elimina e restituisce l'elemento con l'indice i,
	 * spostando i successivi.
	 */
	@Override
	public E remove(int i) {
		checkIndex(i, size);
		E tmp = data[i];
		for(int k = i; k <= size-1; ++k) data[k] = data[k + 1];
		data[size - 1] = null;
		--size;
		return tmp;
	}
	
	/**
	 * Controlla se l'indice data appartiene all'intervallo [0, n - 1]
	 * @param i
	 * @param n
	 * @throws IndexOutOfBoundsException
	 */
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		if(i < 0 || i >= n) throw new IndexOutOfBoundsException("Illegal index: " + i);
	}

	/**
	 * Ridimensiona l'array interno in modo che abbia capacità >= size.
	 */
	@SuppressWarnings("unchecked")
	protected void resize(int capacity) {
		E[] tmp = (E[]) new Object[capacity];
		for(int k = 0; k < size; ++k) tmp[k] = data[k];
		data = tmp;
	}

	private class ArrayIterator implements Iterator<E> {
		private int j = 0;					// indice del prossimo elemento da restituire
		private boolean removable = false;	// si può invocare remove ora?
		
		/**
		 * Verifica se l'iteratore ha ancora oggetti da restituire
		 * @return true se e solo se ci sono ancora oggetti da restituire
		 */
		@Override
		public boolean hasNext() {
			return j < size();
		}

		/**
		 * Restituisce l'oggetto successivo presente nell'iteratore.
		 * 
		 * @return l'oggetto successivo
		 * @throws NoSuchElementException
		 */
		@Override
		public E next() throws NoSuchElementException {
			if(j == size()) throw new NoSuchElementException("No next element");
			removable = true; // questo elemento potrà poi essere rimosso
			return data[j++]; // post-incremente di j, così è pronto per il futuro
		}
		
		/**
		 * Elimina l'elemento restituito dalla più recente invocazione di next.
		 * @throws IllegalStateException se next non è mai stato invocato
		 * @throws IllegalStateException se remove è già stato invocato dopo next
		 */
		@Override
		public void remove() throws IllegalStateException {
			if(!removable) throw new IllegalStateException("nothing to remove");
			ArrayList.this.remove(j-1);
			--j;
			removable = false;
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}
	
}
