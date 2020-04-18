package org.fscotto.asd.adt.tree;

import java.util.ArrayList;
import java.util.List;

import org.fscotto.asd.adt.BinaryTree;
import org.fscotto.asd.adt.Position;

/** Una classe di base astratta che implementain parte l'interfaccia BinaryTree */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

	/** 
	 * Restituisce la posizione del fratello di position (o null se non esiste).
	 * @param position il nodo che vuoi ispezionare
	 * @return la posizione del fratello di posistion o null se non esiste
	 */
	@Override
	public Position<E> sibling(Position<E> position) {
		Position<E> parent = parent(position);
		if(parent == null) return null;	// position è la radice
		if(position == left(position))	// position è il figlio sinistro
			return right(position);		// può essere null
		else							// position è il figlio destro
			return left(parent);		// può essere null
	}

	/**
	 * Restituisce il numero di figli della Position position
	 * @param position
	 * @return restituisce il numero di figli della Position position
	 */
	@Override
	public int numChildren(Position<E> position) {
		int count = 0;
		if(left(position) != null) ++count;
		if(right(position) != null) ++count;
		return count;
	}
	
	/**
	 * Restituisce un contenitore iterabile delle posizione dei figli di position
	 * @param position
	 * @return restituisce un contenitore iterabile delle posizione dei figli di position
	 */
	@Override
	public Iterable<Position<E>> children(Position<E> position) {
		List<Position<E>> snapshot = new ArrayList<>(2);	// capacità massima
		if(left(position) != null) snapshot.add(left(position));
		if(right(position) != null) snapshot.add(right(position));
		return snapshot;
	}
	
	/**
	 * Aggiunge a snapshot le posizioni del sottoalbero avente radice position.
	 * @param position
	 * @param snapshot
	 */
	private void inorderSubtree(Position<E> position, List<Position<E>> snapshot) {
		if(left(position) != null) inorderSubtree(left(position), snapshot);
		snapshot.add(position);
		if(right(position) != null) inorderSubtree(right(position), snapshot);
	}
	
	/**
	 * Restituisce una lista delle posizioni dell'albero, in ordine simmetrico
	 * @return lista delle posizioni in ordine simmetrico
	 */
	protected Iterable<Position<E>> inorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty()) inorderSubtree(root(), snapshot);
		return snapshot;
	}
	
	
	/**
	 * Restituisce una lista delle posizioni dell'albero
	 * @return lista delle posizioni.
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return inorder();
	}

}
