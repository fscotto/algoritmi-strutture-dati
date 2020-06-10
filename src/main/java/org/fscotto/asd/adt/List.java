package org.fscotto.asd.adt;

/** Una versione semplificata dell'interfaccia java.util.List. */
public interface List<E> extends Iterable<E> {

	/** Restituisce il numero di elementi presenti nella lista. */
	int size();
	
	/** Restituisce true se e sole se la lista è vuota. */
	boolean isEmpty();
	
	/** Restituisce l'elemento corristondente all'indice i, senza eliminarlo. */
	E get(int i);
	
	/** Sostituisce con e l'elemento di indice i; restituisce l'elemento sostituito. */
	E set(int i, E e);
	
	/** Inserisce e come elemento di indice i, spostando gli elementi successivi. */
	void add(int i, E e);
	
	/** Rimuove e restituisce l'elemento di indice i, spostando i successivi. */
	E remove(int i);
	
}
