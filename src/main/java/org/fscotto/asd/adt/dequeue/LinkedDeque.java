package org.fscotto.asd.adt.dequeue;

import org.fscotto.asd.adt.Deque;
import org.fscotto.asd.adt.list.DoublyLinkedList;

public class LinkedDeque<E> implements Deque<E> {
	private DoublyLinkedList<E> l = new DoublyLinkedList<>();

	@Override
	public int size() {
		return l.size();
	}

	@Override
	public boolean isEmpty() {
		return l.isEmpty();
	}
	
	@Override
	public E first() {
		return l.first();
	}
	
	@Override
	public E last() {
		return l.last();
	}
	
	@Override
	public void addFirst(E element) {
		l.addFirst(element);
	}
	
	@Override
	public void addLast(E element) {
		l.addLast(element);
	}
	
	@Override
	public E removeFirst() {
		return l.removeFirst();
	}
	
	@Override
	public E removeLast() {
		return l.removeLast();
	}

}
