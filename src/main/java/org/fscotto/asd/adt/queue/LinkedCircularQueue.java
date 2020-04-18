package org.fscotto.asd.adt.queue;

import org.fscotto.asd.adt.CircularQueue;
import org.fscotto.asd.adt.list.CircularyLinkedList;

public class LinkedCircularQueue<E> extends LinkedQueue<E> implements CircularQueue<E> {
	private CircularyLinkedList<E> list = new CircularyLinkedList<>();
	
	@Override
	public void rotate() {
		list.rotate();
	}

}
