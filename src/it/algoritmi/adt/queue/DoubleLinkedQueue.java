package it.algoritmi.adt.queue;

import it.algoritmi.adt.Deque;
import it.algoritmi.adt.Queue;
import it.algoritmi.adt.dequeue.LinkedDeque;

public class DoubleLinkedQueue<E> implements Queue<E> {
	private Deque<E> d = new LinkedDeque<>();
	
	@Override
	public int size() {
		return d.size();
	}

	@Override
	public boolean isEmpty() {
		return d.isEmpty();
	}

	@Override
	public void enqueue(E element) {
		d.addFirst(element);
	}

	@Override
	public E dequeue() {
		return d.removeLast();
	}

	@Override
	public E first() {
		return d.first();
	}

}
