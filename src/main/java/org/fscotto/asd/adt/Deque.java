package org.fscotto.asd.adt;

/**
 * Interfaccia che definisce una coda doppia: un contenitore di elementi con
 * inserimenti e rimozioni ai due estremi. Semplificata rispetto a java.util.Dequeue.
 */
public interface Deque<E> {

	/** Restituisce il numero di elementi presenti nella coda doppia. */
	int size();
	
	/** Verifica se la coda doppia � vuota. */
	boolean isEmpty();
	
	/** Restituisce il primo elemento, senza toglierlo (null se la coda � vuota). */
	E first();
	
	/** Restituisce l'ultimo elemento, senza toglierlo (null se la coda � vuota). */
	E last();
	
	/** Inserisce un elemento all'inizio della coda doppia. */
	void addFirst(E e);
	
	/** Inserisce un elemento alla fine della coda doppia. */
	void addLast(E e);
	
	/** Elimina e restituisce il primo elemento della coda (null se � vuota). */
	E removeFirst();
	
	/** Elimina e restituisce l'ultimo elemento della coda (null se � vuota). */
	E removeLast();
	
}
