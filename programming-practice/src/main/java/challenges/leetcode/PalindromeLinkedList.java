package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 234. Palindrome Linked List
 * 
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up: Could you do it in O(n) time and O(1) space?
 * 
 * @author Hxkandwal
 */
public class PalindromeLinkedList extends AbstractCustomTestRunner {
	
	public static class ListNode {
		int val;
		ListNode next;
		
		public ListNode (int x) { val = x; next = null; } 
	}
	
	public boolean isPalindrome(ListNode head) {
        return head == null || recurse (head, head) != null;
    }
    
    private ListNode recurse (ListNode node, ListNode head) {
    	if (node == null) return head;
        ListNode ret = recurse (head, node.next);
        return (ret != null && ret.val == node.val) ? (ret.next == null ? ret : ret.next) : null;
    }
    
}
