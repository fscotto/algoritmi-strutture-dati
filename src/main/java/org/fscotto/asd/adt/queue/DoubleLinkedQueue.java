package org.fscotto.asd.adt.queue;

import org.fscotto.asd.adt.dequeue.LinkedDeque;
import org.fscotto.asd.adt.Deque;
import org.fscotto.asd.adt.Queue;

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
