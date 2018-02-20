package it.algoritmi.adt.stack;

import it.algoritmi.adt.Deque;
import it.algoritmi.adt.Stack;
import it.algoritmi.adt.dequeue.LinkedDeque;

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
