package it.algoritmi.adt;

/**
 * Interfaccia per una coppia chiave-valore
 * @author fabio
 *
 * @param <K>
 * @param <V>
 */
public interface Entry<K, V> {

	K getKey();
	
	V getValue();
	
}
