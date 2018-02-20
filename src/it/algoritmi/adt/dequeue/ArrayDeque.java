package it.algoritmi.adt.dequeue;

import it.algoritmi.adt.Deque;

public class ArrayDeque<E> implements Deque<E> {
	private E[] data;
	private int size;
	private int f;
	
	public static final int CAPACITY = 1000;
	
	public ArrayDeque() {
		this(CAPACITY);
	}
	
	@SuppressWarnings({"all"})
	public ArrayDeque(int capacity) {
		data = (E[]) new Object[capacity];
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public E first() {
		if(isEmpty()) {
			return null;
		}
		return data[f];
	}

	@Override
	public E last() {
		if(isEmpty()) {
			return null;
		}
		return data[size-1];
	}

	@Override
	public void addFirst(E element) throws IllegalStateException {
		final int len = data.length;
		if (size() == len) {
			throw new IllegalStateException("Queue is full");
		}
		final int avail = (f - 1 + len) % len;
		data[avail] = element;
		++size;
	}

	@Override
	public void addLast(E element) throws IllegalStateException {
		final int len = data.length;
		if (size() == len) {
			throw new IllegalStateException("Queue is full");
		}
		final int avail = (f + size) % len;
		data[avail] = element;
		++size;
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

}
