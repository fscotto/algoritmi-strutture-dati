package it.algoritmi.adt.tree;

import it.algoritmi.adt.Position;
import it.algoritmi.adt.Tree;

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
	
}
