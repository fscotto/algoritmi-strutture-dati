package it.algoritmi.adt.list;

import it.algoritmi.adt.Position;
import it.algoritmi.adt.PositionalList;

/** Gestisce una lista di preferiti usando l'euristica move-to-front */
public class FavoritesListMTF<E> extends FavoritesList<E> {

	/**
	 * Sposta all'inizio della lista l'oggetto in posizione position
	 * a cui si è acceduto.
	 */
	protected void moveUp(Position<Item<E>> position) {
		if(position != list.first()) list.addFirst(list.remove(position)); // elimina e reinserisce all'inizio
	}
	
	/**
	 * Restituisce un contenitore iterabile con i k elementi
	 * aventi più accessi.
	 */
	@Override
	public Iterable<E> getFavorites(int k) throws IllegalArgumentException {
		if(k < 0 || k > size()) throw new IllegalArgumentException();
		
		// iniziamo facendo una copia della lista originale
		PositionalList<Item<E>> tmp = new LinkedPositionalList<>();
		for(Item<E> item : list) tmp.addLast(item);
		
		// cerchiamo ed eliminiamo ripetutamente l'elemento con il conteggio massimo
		PositionalList<E> result = new LinkedPositionalList<>();
		for(int j = 0; j < k; ++j) {
			Position<Item<E>> highPos = tmp.first();
			Position<Item<E>> walk = tmp.after(highPos);
			while(walk != null) {
				if(count(walk) > count(highPos))
					highPos = walk;
				walk = tmp.after(walk);
			}
			// abbiamo trovato l'elemento con il conteggio massimo
			result.addLast(value(highPos));
			tmp.remove(highPos);
		}
		return result;
	}
	
}
