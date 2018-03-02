package it.algoritmi.adt;

/** Un albero i cui nodi possono avere un numero di figli arbitrario. */
public interface Tree<E> extends Iterable<E> {

	/** Restituisce la posizione della radice dell'albero. */
	Position<E> root();

	/** Restituisce la posizione del parent del nodo alla posizione position. */
	Position<E> parent(Position<E> position) throws IllegalArgumentException;

	/** Restituisce una struttura iterabile dei figli del nodo alla posizione position. */
	Iterable<Position<E>> children(Position<E> position) throws IllegalArgumentException;
	
	/** Restituisce il numero di figli del nodo alla posizione position. */
	int numChildren(Position<E> position) throws IllegalArgumentException;
	
	/** Restituisce true se e solo se il nodo alla posizione position non è foglia. */
	boolean isInternal(Position<E> position) throws IllegalArgumentException;
	
	/** Restituisce true se e solo se il nodo alla posizione position è foglia. */
	boolean isExternal(Position<E> position) throws IllegalArgumentException;
	
	/** Restituisce true se e solo se il nodo alla posizione position è la radice. */
	boolean isRoot(Position<E> position) throws IllegalArgumentException;
	
	/** Restituisce il numero di elementi presenti nella struttura. */
	int size();
	
	/** Restituisce true se e solo se la struttura è vuota. */
	boolean isEmpty();
	
	/** Restituisce un contenitore iterabile con tutte le posizioni dell'albero. */
	Iterable<Position<E>> positions(); 

}
