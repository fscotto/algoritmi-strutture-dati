package org.fscotto.asd.demo;

import org.fscotto.asd.adt.queue.LinkedCircularQueue;
import org.fscotto.asd.adt.CircularQueue;

public class Josephus {
	/** Determina il vincitore del problema di Josephus usando una coda circolare. */
	public static <E> E josephus(CircularQueue<E> queue, int k) {
		if(queue.isEmpty()) {
			return null;
		}
		while(queue.size() > 1) {
			for (int i = 0; i < k-1; ++i) {
				queue.rotate();
			}
			E e = queue.dequeue();
			System.out.println("    " + e + " is out");
		}
		return queue.dequeue();
	}
	
	/** Costruisce una coda circolare a partire da un array di oggetti. */
	public static <E> CircularQueue<E> buildQueue(E a[]) {
		CircularQueue<E> queue = new LinkedCircularQueue<>();
		for(int i = 0; i < a.length; ++i) {
			queue.enqueue(a[i]);
		}
		return queue;
	}
	
	public static void main(String[] args) {
		String[] a1 = {"Alice", "Bob", "Cindy", "Doug", "Ed", "Fred"};
		String[] a2 = {"Gene", "Hope", "Irene", "Jack", "Kim", "Lance"};
		String[] a3 = {"Mike", "Roberto"};
		System.out.println("First winner is " + josephus(buildQueue(a1), 3));
		System.out.println("Second winner is " + josephus(buildQueue(a2), 10));
		System.out.println("Third winner is " + josephus(buildQueue(a3), 7));
	}

}
