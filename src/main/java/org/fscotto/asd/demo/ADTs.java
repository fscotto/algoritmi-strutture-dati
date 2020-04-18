package org.fscotto.asd.demo;

import org.fscotto.asd.adt.dequeue.ArrayDeque;
import org.fscotto.asd.adt.queue.LinkedQueue;
import org.fscotto.asd.adt.stack.ArrayStack;
import org.fscotto.asd.adt.Deque;
import org.fscotto.asd.adt.Queue;
import org.fscotto.asd.adt.Stack;

public class ADTs {

	public static <E> void transfer(Stack<E> s, Stack<E> t) {
		if(!s.isEmpty()) {
			for(int i = 0, len = s.size(); i < len; ++i)
				t.push(s.pop());
		}
	}
	
	public static <E> void removeAll(Stack<E> s) {
		if(s.isEmpty()) {
			return;
		}
		s.pop();
		removeAll(s);
	}
	
	public static <E> void shuffle(Deque<E> d) {
		Queue<E> q = new LinkedQueue<>();
		// TODO 
	}

	@SafeVarargs
	private static <E> Stack<E> buildStack(E... elements) {
		Stack<E> result = new ArrayStack<>();
		for(E elem : elements)
			result.push(elem);
		return result;
	}
	
	@SafeVarargs
	private static <E> Deque<E> buildDequeue(E... elements) {
		Deque<E> result = new ArrayDeque<>();
		for(E elem : elements)
			result.addLast(elem);
		return result;
	}
	
	public static void main(String[] args) {
		Stack<Integer> s1 = buildStack(1, 2, 3, 4, 5);
		Stack<Integer> s2 = buildStack(6, 7, 8, 9, 0);
		transfer(s1, s2);
		removeAll(s2);
		Deque<Integer> dk = buildDequeue(1, 2, 3, 4, 5, 6, 7, 8);
	}

}
