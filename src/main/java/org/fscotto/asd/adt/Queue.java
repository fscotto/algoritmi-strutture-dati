package org.fscotto.asd.adt;

public interface Queue<E> {

	/** Restituisce il numero di elementi presenti nella coda. */
	public int size();
	
	/** Verifica se la coda � vuota. */
	public boolean isEmpty();
	
	/** Inserisce un elemento in fondo alla coda. */
	public void enqueue(E e);
	
	/** Elimina e restituisce il primo elemento della coda (null se � vuota). */
	public E dequeue();
	
	/** Restituisce il primo elemento della coda, senza toglierlo (null se � vuota). */
	public E first();
	
}
