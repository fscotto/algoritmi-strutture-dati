package org.fscotto.asd.adt.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.fscotto.asd.adt.PositionalList;
import org.fscotto.asd.adt.Position;

/** Implementazione di lista posizionale mediante lista doppiamente concatenata. */
public class LinkedPositionalList<E> implements PositionalList<E> {

	private static class Node<E> implements Position<E> {
		private E element;		// riferimento all'elemento memorizzato in questo nodo
		private Node<E> prev;	// riferimento al nodo precedente nella lista
		private Node<E> next;	// riferimento al nodo seguente nella lista

		public Node(E element, Node<E> prev, Node<E> next) {
			this.element = element;
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

		public void setElement(E element) {
			this.element = element;
		}

		@Override
		public E getElement() {
			if(next == null) throw new IllegalStateException("Position no longer valid");
			return element;
		}
		
	}

	private Node<E> header;		// sentinella iniziale
	private Node<E> trailer;	// sentinella finale
	private int size;			// numero di elementi presenti nella lista
	
	public LinkedPositionalList() {
		header = new Node<>(null, null, null);		// crea la sentinella iniziale
		trailer = new Node<>(null, header, null);	// trailer è preceduto da header
		header.setNext(trailer);
	}
	
	/**
	 * Verifica se la posizione è valida e la restituisce
	 * sotto forma di nodo.
	 * 
	 * @param position
	 * @return
	 * @throws IllegalArgumentException
	 */
	private Node<E> validate(Position<E> position) throws IllegalArgumentException {
		if(!(position instanceof Node)) throw new IllegalArgumentException("Invalid p");
		Node<E> node = (Node<E>) position; // cast sicuro
		if(node.getNext() == null) throw new IllegalArgumentException("p is no longer in the list");		
		return node;
	}
	
	/**
	 * Restituisce il nodo sotto forma di Position (o null, se è una sentinella).
	 * @param node
	 * @return
	 */
	private Position<E> position(Node<E> node) {
		return (node != header && node != trailer) ? node : null;
	}
	
	/**
	 * Restituisce il numero di elementi presenti
	 * nella lista concatenata.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Restituisce true se e solo se la lista concatenata è vuota.
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Restituisce la prima Position della lista (o null, se la lista è vuota).
	 */
	@Override
	public Position<E> first() {
		return position(header.getNext());
	}

	/**
	 * Restituisce l'ultima Position della lista(o null, se la lista è vuota).
	 */
	@Override
	public Position<E> last() {
		return position(trailer.getPrev());
	}

	/**
	 * Restituisce la Position che precede p (o null, se p è la prima).
	 */
	@Override
	public Position<E> before(Position<E> position) {
		Node<E> node = validate(position);
		return position(node.getPrev());
	}

	/**
	 * Restituisce la Posizione che segue p (o null, se p è l'ultima).
	 */
	@Override
	public Position<E> after(Position<E> position) {
		Node<E> node = validate(position);
		return position(node.getNext());
	}
	
	/**
	 * Aggiunge l'elemento e alla lista concatenata
	 * tra i due nodi dati.
	 * 
	 * @param element
	 * @param pred
	 * @param succ
	 * @return
	 */
	private Position<E> addBeetween(E element, Node<E> pred, Node<E> succ) {
		Node<E> newest = new Node<>(element, pred, succ);
		pred.setNext(newest);
		succ.setPrev(newest);
		++size;
		return newest;
	}

	/**
	 * Inserisce l'elemento e all'inizio della lista;
	 * ne restituisce la posizione.
	 */
	@Override
	public Position<E> addFirst(E element) {
		return addBeetween(element, header, header.getNext());
	}

	/**
	 * Inserisce l'elemento e alla fine della lista;
	 * ne restituisce la posizione.
	 */
	@Override
	public Position<E> addLast(E element) {
		return addBeetween(element, trailer.getPrev(), trailer);
	}
	
	/**
	 * Inserisce l'elemento e prima della Position p;
	 * ne restituisce la posizione.
	 */
	@Override
	public Position<E> addBefore(Position<E> position, E element) {
		Node<E> node = validate(position);
		return addBeetween(element, node.getPrev(), node);
	}

	/**
	 * Inserisce l'elemento e dopo la Position p;
	 * ne restituisce la posizione.
	 */
	@Override
	public Position<E> addAfter(Position<E> position, E element) {
		Node<E> node = validate(position);
		return addBeetween(element, node, node.getNext());
	}

	/**
	 * Sostituisce l'elemento nella Position p;
	 * restituisce l'elemento sostituito.
	 */
	@Override
	public E set(Position<E> position, E element) {
		Node<E> node = validate(position);
		E answer = node.getElement();
		node.setElement(element);
		return answer;
	}

	/**
	 * Elimina e restituisce l'elemento nella Position p (poi p non è più valida).
	 */
	@Override
	public E remove(Position<E> position) {
		Node<E> node = validate(position);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		--size;
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return answer;
	}
	
	private class PositionIterator implements Iterator<Position<E>> {
		private Position<E> cursor = first();		// prossima posizione da restituire
		private Position<E> recent = null;			// posizione restituita più recente
		
		/**
		 * Verifica se l'iteratore ha altro da restituire.
		 */
		@Override
		public boolean hasNext() {
			return cursor != null;
		}

		/**
		 * Restituisce la prossima posizione dell'iteratore.
		 */
		@Override
		public Position<E> next() throws NoSuchElementException {
			if(cursor == null) throw new NoSuchElementException("nothing left");
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}
		
		/**
		 * Elimina l'elemento restituito dalla più recente invocazione di next.
		 */
		@Override
		public void remove() throws IllegalStateException {
			if(recent == null) throw new IllegalArgumentException("nothing to remove");
			LinkedPositionalList.this.remove(recent);	// rimuova l'elemento della lista
			recent = null;	// non si può invocare di nuovo remove prima di next
		}
		
	}
	
	private class PositionIterable implements Iterable<Position<E>> {

		@Override
		public Iterator<Position<E>> iterator() {
			return new PositionIterator();
		}
		
	}

	/**
	 * Restituisce una rappresentazione iterabile delle posizioni dela lista.
	 */
	@Override
	public Iterable<Position<E>> positions() {
		return new PositionIterable();	// crea un nuovo esemplare della classe interna
	}
	
	/**
	 * Adatta l'iteratore restituito da positions() perchè restituisca elementi.
	 */
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> it = new PositionIterator();
		
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
	 * Restituisce un iteratore degli elementi memorizzati nella lista.
	 */
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

}
