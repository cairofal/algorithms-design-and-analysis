package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 24. Swap Nodes in Pairs
 * 
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
 *  
 * Your algorithm should use only constant space. You may not modify the values in the list, 
 * only nodes itself can be changed.
 * 
 * @author Hxkandwal
 *
 */
public class SwapNodesInPairs extends AbstractCustomTestRunner {
	
	private static SwapNodesInPairs _instance = new SwapNodesInPairs();		//singleton instance.
	
	private SwapNodesInPairs() {}											// private constructor
		
	public class ListNode {
		int val;
		ListNode next;
		public ListNode(int x) { val = x; }
	}

	// method 1 : manage links.
	public ListNode _swapperManagingLinks(ListNode head) {
		
		if (head == null)
			return head;
			
		int idx = 0;
	
		ListNode prevprev = null;
		ListNode prev = null;
		ListNode current = head;
		
		while (current.next != null) {
			prevprev = prev;
			prev = current;
			current = current.next;
			
			idx ++;
			
			if (idx % 2 == 1) {
				if (prevprev != null)
					prevprev.next = current;
				else 
					head = current;
				
				ListNode oldCurrent = current;
				
				prev.next = current.next;
				current = current.next = prev;
				prev = oldCurrent;
			}
		}
		
		return head;
	}
	
	// method 2 : copy values.
	public ListNode _swapperCopyingValues(ListNode head) {
		if (head == null)
			return head;
		
		int idx = 0;
	
		ListNode prev = null;
		ListNode current = head;
		
		while (current.next != null) {
			prev = current;
			current = current.next;
			idx ++;
			
			if (idx % 2 == 1) {
				int tempVal = prev.val;
				prev.val = current.val;
				current.val = tempVal;
			}
		}
		
		return head;
	}
	
	// method 3 : recursion.
	public ListNode _swapperUsingRecursion(ListNode head) {
		if (head == null || head.next == null)
			return head;
		
		ListNode nextNode = head.next;
		ListNode futureNextNode = nextNode.next;
		
		nextNode.next = head;
		head.next = (futureNextNode != null) ? _swapperUsingRecursion(futureNextNode) : futureNextNode;
		
		return nextNode;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("", "");
		_instance.runTest(null, null);
		_instance.runTest("1234", "2143");
		_instance.runTest("2143", "1234");
		_instance.runTest("123456", "214365");
		_instance.runTest("214365", "123456");
		
		System.out.println("ok!");
	}
	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		ListNode head, tail;
		head = tail =  null;
		
		String input = (String) externalVariables[0];
		
		if (input == null || input.isEmpty())
			return input;
		
		for (int idx = 0; idx < input.length(); idx ++) {
			if (head == null) {
				tail = head = new ListNode(Integer.valueOf(String.valueOf(input.charAt(idx))).intValue());
			} else {
				tail = tail.next = new ListNode(Integer.valueOf(String.valueOf(input.charAt(idx))).intValue());
			}
		}
		
		try {
			tail = (ListNode) method.invoke(_instance, new Object[] { head });
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		
		while (tail != null) {
			sb.append(tail.val);
			tail = tail.next;
		}

		return sb.toString();
	}

}