package org.fscotto.asd.adt.stack;

import org.fscotto.asd.adt.Stack;
import org.fscotto.asd.adt.list.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {
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
	public void push(E element) {
		list.addFirst(element);
	}

	@Override
	public E top() {
		return list.first();
	}

	@Override
	public E pop() {
		return list.removeFirst();
	}
	
}
