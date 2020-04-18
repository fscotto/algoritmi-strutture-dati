package org.fscotto.asd.adt;

public interface Position<E> {

	/**
	 * Restituisce l'elemento memorizzato in questa posizione.
	 * 
	 * @return l'elemento memorizzato
	 * @throws IllegalStateException se la posizione non è più valida
	 */
	E getElement() throws IllegalStateException;
	
}
