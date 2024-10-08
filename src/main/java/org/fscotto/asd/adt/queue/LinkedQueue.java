package org.fscotto.asd.adt.queue;

import org.fscotto.asd.adt.Queue;
import org.fscotto.asd.adt.list.SinglyLinkedList;

public class LinkedQueue<E> implements Queue<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<>();
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void enqueue(E element) {
		list.addLast(element);
	}

	@Override
	public E dequeue() {
		return list.removeFirst();
	}

	@Override
	public E first() {
		return list.first();
	}

}
