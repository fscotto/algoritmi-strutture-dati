package org.fscotto.asd.adt.list;

public class DoublyLinkedList<E> {
	private Node<E> header;
	private Node<E> trailer;
	private int size = 0;
	
	private static class Node<E> {
		private E element;
		private Node<E> prev;
		private Node<E> next;
		
		private Node(E e, Node<E> prev, Node<E> next) {
			element = e;
			this.prev = prev;
			this.next = next;
		}

		public Node<E> getPrev() {
			return prev;
		}

		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public E getElement() {
			return element;
		}
		
	}

	public DoublyLinkedList() {
		header = new Node<>(null, null, null);
		trailer = new Node<>(null, header, null);
		header.setNext(trailer);
	}
	
	/**
	 * Restituisce il numero di elementi presenti nella lista
	 */
	public int size() {
		return size;
	}

	/** Restituisce true se e solo se la lista � vuota */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/** Restituisce il primo elemento della lista, senza eliminarlo. */
	public E first() {
		if(isEmpty()) {
			return null;
		}
		return header.getNext().getElement();
	}
	
	/** Restituisce l'ultimo elemento della lista, senza eliminarlo. */
	public E last() {
		if(isEmpty()) {
			return null;
		}
		return trailer.getPrev().getElement();
	}
	
	/** Aggiunge un elemento all'inizio della lista. */
	public void addFirst(E e) {
		addBetween(e, header, header.getNext());
	}
	
	/** Aggiunge un elemento alla fine della lista. */
	public void addLast(E e) {
		addBetween(e, trailer.getPrev(), trailer);
	}
	
	/** Elimina e restituisce il primo elemento della lista. */
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		return remove(header.getNext());
	}
	
	/** Elimina e restituisce l'ultimo elemento della lista. */
	public E removeLast() {
		if (isEmpty()) {
			return null;
		}
		return remove(trailer.getPrev());
	}

	/** Elimina dall lista il nodo indicato e restituisce il suo elemento. */
	private E remove(Node<E> node) {
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		--size;
		return node.getElement();
	}

	/** Aggiunge l'elemento e alla lista, tra i due nodi dati. */
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		// crea un nuovo nodo e lo collega alla lista
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		++size;
	}

}
