package org.fscotto.asd.adt;

/** Un albero binario */
public interface BinaryTree<E> extends Tree<E> {

	/** Restituisce la posizione del figlio sinistro di position (o null se non esiste). */
	Position<E> left(Position<E> position);
	
	/** Restituisce la posizione del figlio destro di position (o null se non esiste). */
	Position<E> right(Position<E> position);
	
	/** Restituisce la posizione del fratello di position (o null se non esiste). */
	Position<E> sibling(Position<E> position);
		
}
