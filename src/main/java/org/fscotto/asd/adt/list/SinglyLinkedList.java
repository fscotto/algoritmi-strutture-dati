package org.fscotto.asd.adt.list;

public class SinglyLinkedList<E> implements Cloneable {
	private Node<E> head = null;
	private Node<E> tail = null;
	private int size = 0;
	
	private static class Node<E> {
		private E element;
		private Node<E> next;
		
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		
		public E getElement() {
			return element;
		}
		
		public Node<E> getNext() {
			return next;
		}
		
		public void setNext(Node<E> n) {
			next = n;
		}
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public E first() {
		if(isEmpty()) {
			return null;
		}
		return head.getElement();
	}
	
	public E last() {
		if (isEmpty()) {
			return null;
		}
		return tail.getElement();
	}
	
	public void addFirst(E e) {
		head = new Node<>(e, head);
		if(size == 0) {
			tail = head;
		}
		size++;
	}
	
	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null);
		if(isEmpty()) {
			head = newest;
		} else {
			tail.setNext(newest);
		}
		tail = newest;
		size++;
	}
	
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		}
		E answer = head.getElement();
		head = head.getNext();
		size--;
		if (size == 0) {
			tail = null;
		}
		return answer;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinglyLinkedList other = (SinglyLinkedList) obj;
		if (size != other.size)
			return false;
		Node walkA = head;
		Node walkB = other.head;
		while(walkA != null) {
			if (!walkA.getElement().equals(walkB.getElement()))
				return false;
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();
		if (size > 0) {
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext();
			Node<E> otherTail = other.head;
			while(walk != null) {
				Node<E> newest = new Node<>(walk.getElement(), null);
				otherTail.setNext(newest);
				otherTail = newest;
				walk = walk.getNext();
			}
		}
		return other;
	}
	
}
