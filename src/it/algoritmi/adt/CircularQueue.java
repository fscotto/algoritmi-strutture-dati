package it.algoritmi.adt;

public interface CircularQueue<E> extends Queue<E> {

	/**
	 * Sposta alla fine della coda il suo elemento iniziale.
	 * Se la coda è vuota non fa niente.
	 */
	void rotate();
	
}
