package it.algoritmi.adt.list;

import java.util.Iterator;

import it.algoritmi.adt.Position;
import it.algoritmi.adt.PositionalList;

/** Gestisce una lista di elementi ordinati in base alla frequenza di accesso. */
public class FavoritesList<E> {

	protected static class Item<E> {
		private E value;
		private int count;
		
		public Item(E value) {
			this.value = value;
			this.count = 0;
		}
		
		public E getValue() {
			return value;
		}
		
		public int getCount() {
			return count;
		}

		public void increment() {
			++this.count;
		}
		
	}
	
	PositionalList<Item<E>> list = new LinkedPositionalList<>(); // lista di oggetti
	
	/** Abbreviazione per accedere al preferito memorizzato in posizione position. */
	protected E value(Position<Item<E>> position) {
		return position.getElement().getValue();
	}
	
	/** Abbreviazione per accedere al conteggio associato alla posizione position. */
	protected int count(Position<Item<E>> position) {
		return position.getElement().getCount();
	}
	
	/** Restituisce la Position che ha value uguale a element (o null se non trovato). */
	protected Position<Item<E>> findPosition(E element) {
		Position<Item<E>> walk = list.first();
		while(walk != null && !element.equals(value(walk)))
			walk = list.after(walk);
		return walk;
	}
	
	/** Sposta verso sinistra l'oggetto in posizione position sulla base del conteggio. */
	private void moveUp(Position<Item<E>> position) {
		int cnt = count(position);	// conteggio aggiornato dell'elemento a cui si è acceduto
		Position<Item<E>> walk = position;
		while (walk != list.first() && count(list.before(walk)) < cnt)
			walk = list.before(walk);	// trovato un conteggio minore a sinistra
		if(walk != position) list.addBefore(walk, list.remove(position)); // elimina e reinserisce l'oggetto
	}
	
	/**
	 * Restituisce il numero di elementi presenti 
	 * nella lista dei preferiti.
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Restituisce true se e solo se la lista
	 * dei preferiti è vuota.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * Accede all'elemento element (eventualmente nuovo),
	 * incrementando il suo conteggio.
	 */
	public void access(E element) {
		Position<Item<E>> position = findPosition(element);	// cerca un elemento esistente
		if(position == null) position = list.addLast(new Item<E>(element));	// se nuovo, aggiunge alla fine
		position.getElement().increment();	// in ogni caso, incrementa il conteggio
		moveUp(position);	// sposta a sinistra, se serve
	}
	
	/**
	 * Elimina dalla lista dei preferiti l'elemento
	 * uguale a element (se c'è).
	 */
	public void remove(E element) {
		Position<Item<E>> position = findPosition(element);	// cerca un elemento esitente
		if (position != null) list.remove(position);
	}
	
	/**
	 * Restituisce un contenitore iterabile con i k elementi
	 * aventi più accessi.
	 */
	public Iterable<E> getFavorites(int k) throws IllegalArgumentException {
		if(k < 0 || k > size()) throw new IllegalArgumentException("Invalid k");
		PositionalList<E> result = new LinkedPositionalList<>();
		Iterator<Item<E>> iter = list.iterator();
		for(int j = 0; j < k; ++j) result.addLast(iter.next().getValue());
		return result;
	}
	
}
