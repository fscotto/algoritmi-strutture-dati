package org.fscotto.asd.adt;

/**
 * Una raccolta di oggetti che vengono inseriti e eliminati secondo il principio
 * last-in first-out. Anche se ha uno scopo simile, questa interfaccia � diversa da
 * java.util.Stack
 * 
 * @author fscotto
 *
 */
public interface Stack<E> {

	/**
	 * Restituisce il numero di elementi presenti nella pila.
	 * @return il numero di elementi presenti nella pila.
	 */
	int size();
	
	/**
	 * Verifica se la pila � vuota.
	 * @return true se e solo se la pila � vuota
	 */
	boolean isEmpty();
	
	/**
	 * Inserisce un elemento in cima alla pila.
	 * @param e l'elemento da inserire
	 */
	void push(E e);
	
	
	/**
	 * Restituisce l'elemento in cima alla pila, senza eliminarlo.
	 * @return l'elemento in cima alla pila (o null se la pila � vuota)
	 */
	E top();
	
	/**
	 * Elimina e restituisce l'elemento che si trova in cima alla pila.
	 * @return l'elemento eliminato (o null se la pila � vuota)
	 */
	E pop();

}
