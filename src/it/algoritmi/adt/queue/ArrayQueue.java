package it.algoritmi.adt.queue;

import it.algoritmi.adt.Queue;

public class ArrayQueue<E> implements Queue<E> {
	public static final int CAPACITY = 1000;
	
	private E[] data;
	private int size;
	private int f;
	
	public ArrayQueue() {
		this(CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(int capacity) {
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
	public void enqueue(E element) throws IllegalStateException {
		if (size() == data.length) {
			throw new IllegalStateException("Queue is full");
		}
		int avail = (f + size) % data.length;
		data[avail] = element;
		++size;
	}

	@Override
	public E dequeue() {
		if(isEmpty()) {
			return null;
		}
		E answer = data[f];
		data[f] = null;
		f = (f + 1) % data.length;
		--size;
		return answer;
	}

	@Override
	public E first() {
		if(isEmpty()) {
			return null;
		}
		return data[f];
	}

}
