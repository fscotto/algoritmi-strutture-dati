package org.fscotto.asd.adt;

/** L'interfaccia che definisce liste posizionali. */
public interface PositionalList<E> extends Iterable<E> {

	/** Restituisce il numero di elementi presenti nella lista. */
	int size();
	
	/** Restituisce true se e solo se la lista è vuota. */
	boolean isEmpty();
	
	/** Restituisce la prima Position della lista (o null se la lista è vuota). */
	Position<E> first();
	
	/** Restituisce l'ultima Position della lista (o null se la lista è vuota). */
	Position<E> last();
	
	/** Restituisce la Position che precede p (o null, se p è la prima). */
	Position<E> before(Position<E> position) throws IllegalArgumentException;

	/** Restituisce la Position che segue p (o null, se p è l'ultima). */
	Position<E> after(Position<E> position) throws IllegalArgumentException;
	
	/** Inserisce l'elemento e all'inizio della lista; ne restituisce la posizione. */
	Position<E> addFirst(E element);
	
	/** Inserisce l'elemento e alla fine della lista; ne restituisce la posizione. */
	Position<E> addLast(E element);
	
	/** Inserisce l'elemento e prima della Position p; ne restituisce la posizione. */
	Position<E> addBefore(Position<E> position, E element) throws IllegalArgumentException;
	
	/** Inserisce l'elemento e dopo la Position p; ne restituisce la posizione. */
	Position<E> addAfter(Position<E> position, E element) throws IllegalArgumentException;
	
	/** Sostituisce l'elemento nella Position p; restituisce l'elemento sostituito. */
	E set(Position<E> position, E element) throws IllegalArgumentException;
	
	/** Elimina e restituisce l'elemento nella Position p; (poi p non è più valida). */
	E remove(Position<E> position) throws IllegalArgumentException;
	
	/** Restituisce una rappresentazione iterabile delle posizioni dela lista. */
	public Iterable<Position<E>> positions();
	
}
