package it.algoritmi.adt.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.algoritmi.adt.Position;

/** Implementazione concreta di albero binario usando nodi concatenati. */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
	
	//----------------- classe Node annidata -------------------------
	protected static class Node<E> implements Position<E> {
		private E element;
		private Node<E> parent;
		private Node<E> left;
		private Node<E> right;
		
		public Node(E element, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
			this.element = element;
			this.parent = above;
			this.left = leftChild;
			this.right = rightChild;
		}

		@Override
		public E getElement() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public void setLeft(Node<E> left) {
			this.left = left;
		}

		public Node<E> getRight() {
			return right;
		}

		public void setRight(Node<E> right) {
			this.right = right;
		}
	}
	//----------------- fine della classe Node annidata -------------------

	/** Metodo-fabbrica che crea un nuovo nodo memorizzandovi l'elemento element */
	protected Node<E> createNode(E element, Node<E> parent, Node<E> left, Node<E> right) {
		return new Node<>(element, parent, left, right);
	}
	
	protected Node<E> root;
	private int size;
	
	/** Verifica la validità della posizione e la restituisce sotto forma di nodo. */
	protected Node<E> validate(Position<E> position) throws IllegalArgumentException {
		if(!(position instanceof Node)) throw new IllegalArgumentException("Not valid position type");
		Node<E> node = (Node<E>) position;
		if(node.getParent() == node) throw new IllegalArgumentException("position is no longer in the tree");
		return node;
	}
	
	/**
	 * Restituisce la Position del figlio sinistro di position
	 * (o null se non c'è).
	 * @param position
	 * @return figlio sinistro o null se non c'è
	 */
	@Override
	public Position<E> left(Position<E> position) throws IllegalArgumentException {
		Node<E> node = validate(position);
		return node.getLeft();
	}

	/**
	 * Restituisce la Position del figlio destro di position
	 * (o null se non c'è).
	 * @param position
	 * @return figlio destro o null se non c'è
	 */
	@Override
	public Position<E> right(Position<E> position) throws IllegalArgumentException {
		Node<E> node = validate(position);
		return node.getRight();
	}

	/**
	 * Restituisce la Position radice dell'albero (o null se l'albero è vuoto).
	 * @return radice dell'albero o null se è vuoto
	 */
	@Override
	public Position<E> root() {
		return root;
	}

	/**
	 * Restituisce la Position del genitore di position 
	 * (o null se position è la radice).
	 * @param position
	 * @return posizione di position o null se è radice
	 */
	@Override
	public Position<E> parent(Position<E> position) throws IllegalArgumentException {
		Node<E> node = validate(position);
		return node.getParent();
	}

	/**
	 * Restituisce il numero di nodi presenti
	 * nell'albero.
	 * @return numero di nodi dell'albero
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Pone e nelle radice di un albero vuoto e 
	 * restituisce la sua nuova Position
	 */
	public Position<E> addRoot(E element) throws IllegalArgumentException {
		if(!isEmpty()) throw new IllegalStateException("Tree is not empty");
		root = createNode(element, null, null, null);
		this.size = 1;
		return root;
	}
	
	/**
	 * Crea il figlio sinistro di position con l'elemento element e;
	 * ne restituisce la posizione.
	 * @param position
	 * @param element
	 * @return la posizione del figlio sinistro creato.
	 * @throws IllegalArgumentException
	 */
	public Position<E> addLeft(Position<E> position, E element) throws IllegalArgumentException {
		Node<E> parent = validate(position);
		if(parent.getLeft() != null) throw new IllegalArgumentException("position already has a left child");
		Node<E> child = createNode(element, parent, null, null);
		parent.setLeft(child);
		++this.size;
		return child;
	}
	
	/**
	 * Crea il figlio destro di position con l'elemento element e;
	 * ne resituisce la posizione.
	 * @param position
	 * @param element
	 * @return la posizione del figlio destro creato.
	 * @throws IllegalArgumentException
	 */
	public Position<E> addRight(Position<E> position, E element) throws IllegalArgumentException {
		Node<E> parent = validate(position);
		if(parent.getRight() != null) throw new IllegalArgumentException("position already has a right child");
		Node<E> child = createNode(element, parent, null, null);
		parent.setRight(child);
		++this.size;
		return child;
	}
	
	/**
	 * Sostituisce con element l'elemento in position 
	 * e restituisce l'elemento sostituito.
	 * @param position
	 * @param element
	 * @return l'elemento sostituito
	 * @throws IllegalArgumentException
	 */
	public E set(Position<E> position, E element) throws IllegalArgumentException {
		Node<E> node = validate(position);
		E tmp = node.getElement();
		node.setElement(element);
		return tmp;
	}
	
	/**
	 * Collega gli alberi t1 e t2 come sottoalberi
	 * sinistro e destro di position, foglia.
	 * @param position
	 * @param t1
	 * @param t2
	 * @throws IllegalArgumentException
	 */
	public void attach(Position<E> position, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
		Node<E> node = validate(position);
		if(isInternal(position)) throw new IllegalArgumentException("position must be a leaf");
		size += t1.size() + t2.size();
		if(!t1.isEmpty()) { // collega t1 come sottoalbero sinistro di node
			t1.root.setParent(node);
			node.setLeft(t1.root);
			t2.root = null;
			t2.size = 0;
		}
		if(!t2.isEmpty()) {
			t2.root.setParent(node);
			node.setRight(t2.root);
			t1.root = null;
			t1.size = 0;
		}
	}
	
	/**
	 * Elimina il nodo in posizione position e
	 * lo sostituisce con il figlo, se c'è.
	 * @param position
	 * @return elemento eliminato
	 * @throws IllegalArgumentException
	 */
	public E remove(Position<E> position) throws IllegalArgumentException {
		Node<E> node = validate(position);
		if(numChildren(position) == 2) throw new IllegalArgumentException("position has two children");
		Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
		if(child != null) child.setParent(node.getParent()); // il nonno di child ne diventa genitore
		if(node == root) 
			root = child;	// child diventa la radice
		else {
			Node<E> parent = node.getParent();
			if(node == parent.getLeft()) parent.setLeft(child);
			else parent.setRight(child);
		}
		--size;
		E tmp = node.getElement();
		node.setElement(null);
		node.setLeft(null);
		node.setParent(node);
		return tmp;
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
	 * Restituisce una lista delle posizioni dell'albero
	 * @return lista delle posizioni.
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return preorder();
	}

	//------------------ classe ElementIterator annidata ----------
	/** Adatta l'iterazione prodotta da positions() per restituire elementi. */
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> it = positions().iterator();
		
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public E next() {
			return it.next().getElement();
		}
		
		@Override
		public void remove() {
			it.remove();
		}
	}
	
	/**
	 * Restituisce un iteratore degli elementi
	 * memorizzati nell'albero.
	 * @return iteratore alla struttura
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

}
