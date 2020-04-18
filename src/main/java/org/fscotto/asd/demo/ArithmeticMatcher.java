package org.fscotto.asd.demo;

import org.fscotto.asd.adt.stack.LinkedStack;
import org.fscotto.asd.adt.Stack;

public class ArithmeticMatcher {

	public static boolean isMatched(String expression) {
		final String opening = "({[";
		final String closing = ")}]";
		Stack<Character> buffer = new LinkedStack<>();
		for(char c : expression.toCharArray()) {
			if(opening.indexOf(c) != -1) {
				buffer.push(c);
			} else if(closing.indexOf(c) != -1) {
				if(buffer.isEmpty()) {
					return false;
				}
				if(closing.indexOf(c) != opening.indexOf(buffer.pop())) {
					return false;
				}
			}
		}
		return buffer.isEmpty();
	}
	
	public static void main(String[] args) {
		String exp = "(10 + 4) * 89";
		System.out.println(isMatched(exp));
	}
}
