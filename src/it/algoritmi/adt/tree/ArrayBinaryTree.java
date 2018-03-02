package it.algoritmi.adt.tree;

import java.util.Iterator;

import it.algoritmi.adt.Position;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
	
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
	
	public static final int CAPACITY = 16;
	
	private Node<E>[] data;
	private int size;
	
	public ArrayBinaryTree() {
		this(CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayBinaryTree(int capacity) {
		data = (Node<E>[]) new Object[capacity];
		size = 0;
	}
	
	@Override
	public Position<E> left(Position<E> position) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> right(Position<E> position) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> root() {
		return data[0];
	}

	@Override
	public Position<E> parent(Position<E> position) throws IllegalArgumentException {
		if(isRoot(position)) return null;
		int index = depth(position);
		
		return null;
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

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
