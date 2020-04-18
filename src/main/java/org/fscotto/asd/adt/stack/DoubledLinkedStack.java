package org.fscotto.asd.adt.stack;

import org.fscotto.asd.adt.dequeue.LinkedDeque;
import org.fscotto.asd.adt.Deque;
import org.fscotto.asd.adt.Stack;

public class DoubledLinkedStack<E> implements Stack<E> {
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
	public void push(E element) {
		d.addFirst(element);
	}

	@Override
	public E top() {
		return d.first();
	}

	@Override
	public E pop() {
		return d.removeFirst();
	}

}
