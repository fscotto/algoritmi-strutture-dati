package it.algoritmi.adt.tree;

import java.util.ArrayList;
import java.util.List;

import it.algoritmi.adt.Position;
import it.algoritmi.adt.Queue;
import it.algoritmi.adt.Tree;
import it.algoritmi.adt.queue.LinkedQueue;

/** Una classe di base astratta che implementa in parte l'interfaccia Tree. */
public abstract class AbstractTree<E> implements Tree<E> {

	@Override
	public boolean isInternal(Position<E> position) throws IllegalArgumentException {
		return numChildren(position) > 0;
	}

	@Override
	public boolean isExternal(Position<E> position) throws IllegalArgumentException {
		return numChildren(position) == 0;
	}

	@Override
	public boolean isRoot(Position<E> position) throws IllegalArgumentException {
		return position == root();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/** Restituisce la profondità della Position position. */
	public int depth(Position<E> position) {
		class A {
			int depth(Position<E> position, int acc) {
				return isRoot(position) ? 0 : depth(parent(position), ++acc);
			}
		}
		return (new A()).depth(position, 0);
	}
	
	/** 
	 * Restituisce l'altezza dell'albero.
	 * @return altezza dell'albero 
	 */
	@Deprecated
	public int height() {
		int h = 0;
		for(Position<E> position : positions())
			if(isExternal(position))
				h = Math.max(h, depth(position));
		return h;
	}
	
	/** 
	 * Restituisce l'altezza del sottoalbero avente 
	 * radice nelle Position position.
	 * @param position
	 * @return altezza dell'albero
	 */
	public int height(Position<E> position) {
		int h = 0;
		for(Position<E> c : children(position))
			h = Math.max(h, 1 + height(c));
		return h;
	}
	
	/**
	 * Aggiunge a snapshot le posizioni del sottoalbero avente radice position.
	 * @param position
	 * @param snapshot
	 */
	private void preorderSubtree(Position<E> position, List<Position<E>> snapshot) {
		snapshot.add(position);	// in pre-ordine, aggiungiamo position prima dei sottoalberi
		for(Position<E> c : children(position))
			preorderSubtree(c, snapshot);
	}
	
	/**
	 * Restituisce una lista delle posizioni dell'albero, in pre-ordine.
	 * @return lista delle posizioni in pre-ordine
	 */
	protected Iterable<Position<E>> preorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty()) preorderSubtree(root(), snapshot);	// riempe ricorsivamente snapshot
		return snapshot;
	}

	/**
	 * Aggiunge a snapshot le posizioni del sottoalbero avente radice position.
	 * @param position
	 * @param snapshot
	 */
	private void postorderSubtree(Position<E> position, List<Position<E>> snapshot) {
		for(Position<E> c : children(position))
			postorderSubtree(c, snapshot);
		snapshot.add(position);
	}
	
	/**
	 * Restituisce una lista delle posizioni dell'albero, in post-ordine
	 * @return lista delle posizioni in post-ordine
	 */
	protected Iterable<Position<E>> postorder() {
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty()) postorderSubtree(root(), snapshot);
		return snapshot;
	}
	
	/**
	 * Restituisce una lista delle posizioni dell'albero, attraversato in ampiezza.
	 * @return lista delle posizioni in ampiezza
	 */
	protected Iterable<Position<E>> breadthFirst() {
		List<Position<E>> snapshot = new ArrayList<>();
		if(!isEmpty()) {
			Queue<Position<E>> fringe = new LinkedQueue<>();
			fringe.enqueue(root());							// inizia con la radice
			while(!fringe.isEmpty()) {
				Position<E> position = fringe.dequeue(); 	// estrae dall'inizio della coda
				snapshot.add(position);						// aggiunge questa posizione
				for(Position<E> c : children(position))
					fringe.enqueue(c);						// aggiunge i figli in fondo alla coda
			}
		}
		return snapshot;
	}
	
	/**
	 * Restituisce una lista delle posizioni dell'albero
	 * @return lista delle posizioni.
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return preorder();
	}

}
