package it.algoritmi.adt.queue;

import it.algoritmi.adt.CircularQueue;
import it.algoritmi.adt.list.CircularyLinkedList;

public class LinkedCircularQueue<E> extends LinkedQueue<E> implements CircularQueue<E> {
	private CircularyLinkedList<E> list = new CircularyLinkedList<>();
	
	@Override
	public void rotate() {
		list.rotate();
	}

}
