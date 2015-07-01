import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;


public class Solutions {

	/**
	 * Definition for singly-linked list.
	  */
	public static class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	  }
	 
	/**
	 * Definition for an interval.
	 */
	public class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
	 }
	
	/**
	 * Definition for binary tree with next pointer.
	 */
	public class TreeLinkNode {
	     int val;
	     TreeLinkNode left, right, next;
	     TreeLinkNode(int x) { val = x; }
	}
	
	/**
	 * Yelp Place Bomb
	 */
	int[][] placeBombs(int[][] board, int numBombs) {
	    // place bombs on board
	    // return board with bombs
	    
	    if(board == null || board.length == 0 || board[0].length == 0){
	        return board;
	    }
	    
	    int rows = board.length;
	    int cols = board[0].length;
	    
	    int[] bombs = new int[rows*cols];
	    
	    for(int i = 0; i < rows*cols; i++){
	    	bombs[i] = i;
	    }

	    Collections.shuffle(Arrays.asList(bombs));

	    for(int i = 0; i < numBombs; i++){
	    	setBoard(board, bombs[i], rows, cols);
	    }
	        
	    return board;
	}

	void setBoard(int[][] board, int num, int rows, int cols){
	    int row = num/cols;
	    int col = num%cols;
	    
	    board[row][col] = -1;
	}
	
	/**
	 * Uber Palindrome
	 */
	public static boolean palindrome(String s){
	    if(s == null){
	      return false;
	    }
	    if(s.length() == 0){
	      return true;
	    }
	    
	    int singleNum = 0;
	    
	    char[] chars = s.toCharArray();
	    
	    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	    
	    for(int i = 0; i < s.length(); i++){
	      if(map.containsKey(chars[i])){
	        map.put(chars[i], map.get(chars[i])+1);
	      }else{
	        map.put(chars[i], 1);
	      }
	    }
	    
	    for(Map.Entry<Character,Integer> entry : map.entrySet()){
	        if(entry.getValue() % 2 == 1){
	          singleNum++;
	        }
	    }
	    
	    if(singleNum <= 1){
	      return true;
	    }
	    return false;
	  }
	
	/**
	 * Definition for binary tree
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	
	/**
	 * Merge Two Sorted Lists Total Accepted: 47545 Total Submissions: 143600 My Submissions Question Solution 
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(0);
        
        ListNode dummy = newHead;
        
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                newHead.next = l1;
                newHead = newHead.next;
                l1 = l1.next;
            }else{
                newHead.next = l2;
                newHead = newHead.next;
                l2 = l2.next;
            }
        }
        
        if(l1 == null){
            newHead.next = l2;
        }
        
        if(l2 == null){
            newHead.next = l1;
        }
        
        return dummy.next;
    }
	
	/**
	 * Swap Nodes in Pairs Total Accepted: 40440 Total Submissions: 124207 My Submissions Question Solution 
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
	 */
	public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        while(head.next != null && head.next.next != null){
            ListNode latterP = head.next.next.next;
            head.next = swap(head.next);
            head.next.next.next = latterP;
            head = head.next.next;
        }
        
        return dummy.next;
        
    }
    
    public ListNode swap(ListNode p){
        ListNode newHead = p.next;
        newHead.next = p;
        p.next = null;
        
        return newHead;
    }
	
/**
 * Remove Duplicates from Sorted Array Total Accepted: 50968 Total Submissions: 161464 My Submissions Question Solution 
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
 */
	
	public int removeDuplicates(int[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        
        if(A.length == 1){
            return 1;
        }
        
        int size = 0;
        
        for(int i = 0; i < A.length; i++){
            if(A[i] != A[size]){
                size++;
                A[size] = A[i];
            }
        }
        
        return size+1;
    }
	
	/**
	 * Remove Duplicates from Sorted Array II Total Accepted: 34841 Total Submissions: 113423 My Submissions Question Solution 
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
	 */
	public int removeDuplicates2(int[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        
        if(A.length == 1){
            return 1;
        }
        
        int size = 0;
        int counter = 1;
        for(int i = 1; i < A.length; i++){
            if(A[i] != A[size]){
                counter = 1;
                size++;
                A[size] = A[i];
                continue;
            }
            counter++;
            if(counter <= 2){
                size++;
                A[size] = A[i];
            }
        }
        
        return size+1;
    }
	
    /**
     * Remove Duplicates from Sorted List Total Accepted: 36774 Total Submissions: 106441 My Submissions Question Solution 
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.	
     * @param args
     * @throws IOException
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode dummy = head;
        while(head!=null && head.next!=null){
            if(head.val == head.next.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        
        return dummy;
    }
    
    /**
     * Remove Duplicates from Sorted List II Total Accepted: 35147 Total Submissions: 140986 My Submissions Question Solution 
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        while(head.next != null && head.next.next != null){
            if(head.next.val == head.next.next.val){
                int val = head.next.val;
                head.next = head.next.next.next;
                while(head.next != null && head.next.val == val){
                    head.next = head.next.next;
                }
            }else{
                head = head.next;
            }
        }
        
        return dummy.next;
    }
	
	/**
	 * Dungeon Game Total Accepted: 6084 Total Submissions: 35264 My Submissions Question Solution 
The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

-2 (K)	-3	3
-5	-10	1
10	30	-5 (P)

Notes:

The knight's health has no upper bound.
Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
	 */
	public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon[0] == null){
            return 0;
        }
        
        int rows = dungeon.length;
        int cols = dungeon[0].length;
        
        dungeon[rows-1][cols-1] = dungeon[rows-1][cols-1] > 0? 1 : 1-dungeon[rows-1][cols-1];

        for(int i = rows - 2; i >= 0; i--){
            dungeon[i][cols-1] = dungeon[i+1][cols-1] - dungeon[i][cols-1] > 0? dungeon[i+1][cols-1] - dungeon[i][cols-1] : 1;
        }
        
        for(int j = cols - 2; j >= 0; j--){
            dungeon[rows-1][j] = dungeon[rows-1][j+1] - dungeon[rows-1][j] > 0? dungeon[rows-1][j+1] - dungeon[rows-1][j] : 1;
        }
        
        for(int i = rows - 2; i >= 0; i--){
            for(int j = cols - 2; j >= 0; j--){
                if( Math.min(dungeon[i+1][j], dungeon[i][j+1]) - dungeon[i][j] > 0){
                    dungeon[i][j] = Math.min(dungeon[i+1][j], dungeon[i][j+1]) - dungeon[i][j];
                }else{
                    dungeon[i][j] = 1;
                }
            }
            
        }
        
        return dungeon[0][0];
        
    }
	
	/**
	 * Binary Search Tree Iterator Total Accepted: 10998 Total Submissions: 37967 My Submissions Question Solution 
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

Credits:
Special thanks to @ts for adding this problem and creating all test cases.
	 */
	
	public class BSTIterator {

	    List<Integer> list = new ArrayList<Integer>();  
	    int index = 0;

	    public BSTIterator(TreeNode root) {
	        helper(root);
	    }
	    
	    public void helper(TreeNode root){
	        if(root == null){
	            return;
	        }
	        
	        helper(root.left);
	        list.add(root.val);
	        helper(root.right);
	    }

	    /** @return whether we have a next smallest number */
	    public boolean hasNext() {
	        if(index < list.size()){
	            return true;
	        }else{
	            return false;
	        }
	    }

	    /** @return the next smallest number */
	    public int next() {
	        return list.get(index++);
	    }
	}

	/**
	 * Your BSTIterator will be called like this:
	 * BSTIterator i = new BSTIterator(root);
	 * while (i.hasNext()) v[f()] = i.next();
	 */
	
	/**
	 * Same Tree Total Accepted: 51298 Total Submissions: 121757 My Submissions Question Solution 
Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == q){
            return true;
        }
        
        if(p == null || q == null){
            return false;
        }
        
        if(p.val != q.val){
            return false;
        }
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
	
	 /**
     * Validate Binary Search Tree Total Accepted: 22081 Total Submissions: 85259 My Submissions
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
1
/ \
2   3
/
4
\
 5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
     * @param args
     * @throws IOException
     */
    /**
     * Definition for binary tree
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    public class Solution {
        int lastNodeVal = Integer.MIN_VALUE;
        
        public boolean isValidBST(TreeNode root) {
            if(root  == null){
                return true;
            }
            if(!isValidBST(root.left)){
                return false;
            }
            if(lastNodeVal >= root.val){
                return false;
            }
            lastNodeVal = root.val;
            if(!isValidBST(root.right)){
                return false;
            }
            return true;
        }
    }
    
    /**
     * Recover Binary Search Tree Total Accepted: 27713 Total Submissions: 114329 My Submissions Question Solution 
Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
     */
    
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode last = new TreeNode(Integer.MIN_VALUE);
    
    public void traverse(TreeNode root){
    	if(root == null){
    		return;
    	}
    	
    	traverse(root.left);
    	
    	if(first == null && last.val >= root.val){
    		first = last;
    	}
    	
    	if(first != null && last.val >= root.val){
    		second = root;
    	}
    	
    	last = root;
    	
    	traverse(root.right);
    }
    
    public void recoverTree(TreeNode root){
    	traverse(root);
    	
    	int temp = first.val;
    	first.val = second.val;
    	second.val = temp;
    }
	
	/**
	 * Populating Next Right Pointers in Each Node Total Accepted: 42226 Total Submissions: 117065 My Submissions Question Solution 
Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
	 */
	
	
	
	public void connect(TreeLinkNode root) {
        if(root == null){
            return;
        }
        
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.offer(root);
        
        while(!queue.isEmpty()){
        	
            int size = queue.size();
            
            for(int i = 0; i < size; i++){
                TreeLinkNode node = queue.poll();
                TreeLinkNode lastNode = node;
                
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
                if(i == size - 1){
                    lastNode.next = null;
                }else{
                    lastNode.next = queue.peek();
                }
            }
        }
        
        return;
    }
	
	
	/**
	 * Roman to Integer Total Accepted: 29431 Total Submissions: 84727 My Submissions Question Solution 
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
	 */
	public int romanToInt(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int result = map.get(s.charAt(s.length()-1));
        
        for(int i = s.length() - 2; i >= 0; i--){
            if(map.get(s.charAt(i)) >= map.get(s.charAt(i+1))){
                result += map.get(s.charAt(i));
            }else{
                result -= map.get(s.charAt(i));
            }
        }
        
        return result;
    }
	
	/**
	 * Integer to Roman Total Accepted: 25386 Total Submissions: 73606 My Submissions Question Solution 
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
	 */
	public String intToRoman(int num) {
        String result = "";
        if(num == 0){
            return result;
        }
        
        int[] nums = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] romans = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        
        for(int i = nums.length - 1; i >= 0; i--){
            int times = num/nums[i];
            num = num % nums[i];
            
            for(int j = 0; j < times; j++){
                result += romans[i];
            }
        }
        
        return result;
    }	
	
	/**
	 * Find Peak Element Total Accepted: 16631 Total Submissions: 51508 My Submissions Question Solution 
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

click to show spoilers.

Note:
Your solution should be in logarithmic complexity.
	 */
	public int findPeakElement(int[] num) {
        if(num == null || num.length <= 1){
            return 0;
        }
        
        if(num[0] > num[1]){
            return 0;
        }
        
        if(num[num.length - 1] > num[num.length - 2]){
            return num.length - 1;
        }
        
        int left = 1;
        int right = num.length - 2;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(num[mid] > num[mid + 1] && num[mid] > num[mid - 1]){
                return mid;
            }else if(num[mid] < num[mid + 1]){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        
        return -1;
    }
	
	/**
	 * Min Stack Total Accepted: 20078 Total Submissions: 124784 My Submissions Question Solution 
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
	 */
	List<Integer> stack = new ArrayList<Integer>();
    List<Integer> minStack = new ArrayList<Integer>();
    
    public void push(int x) {
        stack.add(x);
        if(minStack.size() == 0 || minStack.get(minStack.size()-1)>=x){
            minStack.add(x);
        }
    }

    public void pop() {
        if(stack.size() != 0){
            int cur = stack.get(stack.size()-1);
            stack.remove(stack.size()-1);
            if(minStack.size()!=0 && minStack.get(minStack.size()-1) == cur){
                minStack.remove(minStack.size()-1);
            }
        }
    }

    public int top() {
        if(stack.size() != 0){
            return stack.get(stack.size()-1);
        }else{
            return -1;
        }
    }

    public int getMin() {
        if(minStack.size() != 0){
            return minStack.get(minStack.size()-1);
        }else{
            return -1;
        }
    }
	
	/**
	 * Reverse Linked List II Total Accepted: 32290 Total Submissions: 123040 My Submissions Question Solution 
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
	 * @param a
	 * @param b
	 * @return
	 */
//	public static ListNode reverseBetween(ListNode head, int m, int n) {
//        if(head == null || head.next == null || m >= n){
//            return head;
//        }
//        
//        ListNode dummy = new ListNode(0);        
//        dummy.next = head;
//        
//        ListNode left = dummy;
//        ListNode right = dummy;
//        for(int i = 1; i < m; i++){
//            left = left.next;
//        }
//        for(int i = 0; i <= n; i++){
//            right = right.next;
//        }
//        //{3,5}, 1, 2
//        
//        ListNode middle = reverse(left.next, m, n);
//        
//        left.next = middle;
//        while(middle.next != null){
//            middle = middle.next;
//        }
//        
//        middle.next = right;
//        
//        return dummy.next;
//        
//    }
//	
//	public static ListNode reverse(ListNode head, int m, int n){
//        ListNode newhead = null;
//        int length = n - m;
//        while(head != null && length >= 0){
//            ListNode temp = head.next;
//            head.next = newhead;
//            newhead = head;
//            head = temp;
//            length--;
//        }
//        
//        return newhead;
//    }
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m >= n){
            return head;
        }
        int reverseLength = n - m;
        ListNode dummy = new ListNode(0);
        
        dummy.next = head;
        
        ListNode pointer = dummy;
        while(m > 1){
            pointer = pointer.next;
            m--;
        }
        //{3,5}1,2
        pointer.next = reverse(pointer, reverseLength);
        
        return dummy.next;
    }
    
    public static ListNode reverse(ListNode head, int len){
        ListNode newHead = null;
        
        while(len >= 0){
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
            len--;
        }
        
        ListNode p = newHead;
        while(p.next != null){
            p = p.next;
        }
        
        p.next = head;
        
        return newHead;
    }
	
	public static int addBinaries(int a, int b){
		while(b != 0){
			int carry = a & b; // Carry is AND of two bits
			a = a ^ b; // SUM is XOR of two bits
			b = carry << 1; // shifts carry to 1 bit to calculate sum;
		}
		return a;
	}
	/**
	 * Add Binary Total Accepted: 33380 Total Submissions: 133227 My Submissions Question Solution 
Given two binary strings, return their sum (also a binary string).

For example,
a = "11"
b = "1"
Return "100".
	 */
	public String addBinary(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        
        int maxLen = Math.max(lenA, lenB);
        String result = "";
        int carry = 0;
        for(int i = 1; i <= maxLen; i++){
            int p, q;
            
            if(i <= lenA){
                p = a.charAt(lenA-i) - '0';
            }else{
                p = 0;
            }   
            if( i <= lenB){
                q = b.charAt(lenB-i) - '0';
            }else{
                q = 0;
            }
            
            int cur = (p + q + carry)%2;
            carry = (p + q + carry)/2;
            
            result = cur+result;
        }
        
        if(carry == 1){
            result = 1 + result;
        }
        
        return result;
    }
	
	
	/**
	 * Next Permutation Total Accepted: 28016 Total Submissions: 111382 My Submissions Question Solution 
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

“这道题是给定一个数组和一个排列，求下一个排列。算法上其实没有什么特别的地方，主要的问题是经常不是一见到这个题就能马上理清思路。下面我们用一个例子来说明，比如排列是(2,3,6,5,4,1)，求下一个排列的基本步骤是这样：
1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;
2) 接下来分两种情况：
    (1) 如果上面的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，其实把所有数字反转过来即可(比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
    (2) 否则，如果p存在，从p开始往后找，找找找，找到第一个比他小的数，然后两个调换位置，比如例子中的4。调换位置后得到(2,4,6,5,3,1)。最后把p之后的所有数字倒序，比如例子中得到(2,4,1,3,5,6), 这个即是要求的下一个排列。
以上方法中，最坏情况需要扫描数组三次，所以时间复杂度是O(3*n)=O(n)，空间复杂度是O(1)。代码如下：”
	 */
	public void nextPermutation(int[] num) {
        if(num == null || num.length <= 1){
            return;
        }
        
        for(int i = num.length - 1; i > 0; i--){
            if(num[i-1] < num[i]){
                i--;
                int j = i + 1;
                for(; j < num.length; j++){
                    if(num[j] <= num[i]){
                        break;                 
                    }
                }
                j--;
                        
                swap(num, i, j);
                reverse(num, i+1, num.length - 1);
                return;    
                   
            }
        }
        
        reverse(num, 0, num.length - 1);
    }
    
    public void swap(int[] num, int i, int j){
        int temp = num[j];
        num[j] = num[i];
        num[i] = temp;
    }
    
    public void reverse(int[] num, int start, int end){
        for(int i = start, j = end; i < j; i++, j--){
            swap(num, i, j);
        }
    }
	
	
	/**
	 * Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".
	 * @param s
	 * @return
	 */
	public static String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuffer result = new StringBuffer();
        for(int i = 1; i<=words.length; i++){
            result.append(words[words.length-i]);
            if(i!=words.length){
                result.append(" ");
            }
        }
        return result.toString();
    }
	 public static String reverseWords2(String s) {
		    s = " 1";
		    s.trim();
	        String[] stringSplit = s.split(" ");
	        StringBuffer returnString = new StringBuffer();
	        for(int i = stringSplit.length-1; i >=0 ; i --){
	            returnString.append(stringSplit[i]);
	            if(i!=0){
	                returnString.append(" ");
	            }
	        }
	        
	        return returnString.toString();
	    }
	
/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * @param tokens
 * @return
 */
	public static int evalRPN(String[] tokens) {
		int returnValue = 0;
		Stack<String> stack = new Stack<String>();
		String operands = "+-*/";
		for(String token:tokens){
			if(!operands.contains(token)){
				stack.push(token);
			}else{
				int a = Integer.parseInt(stack.pop());
				int b = Integer.parseInt(stack.pop());
				
				int index = operands.indexOf(token);
				switch(index){
					case 0: 
						stack.push((b+a)+"");
						break;
					case 1:
						stack.push((b-a)+"");
						break;
					case 2:
						stack.push((b*a)+"");
						break;
					case 3:
						stack.push((b/a)+"");
				}
			}
		}
		
		returnValue = Integer.parseInt(stack.pop());
		
		return returnValue;
    }
	
	public static int evalRPN2(String[] tokens) {
        if(tokens == null || tokens.length == 0){
            return -1;
        }
        String operands = "+-*/";
        Stack<Integer> stack = new Stack<Integer>();
        
        for(String token : tokens){
            if(operands.contains(token)){
                int num1 = stack.pop();
                int num2 = stack.pop();
                int index = operands.indexOf(token);
                switch(index){
                    case 0:
                        stack.push(num2+num1);
                    case 1:
                        stack.push(num2-num1);
                    case 2:
                        stack.push(num2*num1);
                    case 3:
                        stack.push(num2/num1);
                }
                
            }else{
                stack.push(Integer.parseInt(token));
            }
        }
        
        return stack.pop();
        
    }
	/**
	 * Single Number Total Accepted: 31666 Total Submissions: 69144 My Submissions
Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 * @param A
	 * @return
	 */
	public static int singleNumber(int[] A) {
        Map<Integer, Integer> numberMap = new HashMap<Integer,Integer>();
        //int returnNum;
        for(int i = 0; i < A.length ; i++){
            if(!numberMap.containsKey(A[i])){
               numberMap.put(A[i],1);
            }else{
            	numberMap.put(A[i], numberMap.get(A[i])+1);
            }
        }
        for(Map.Entry<Integer,Integer> entry : numberMap.entrySet()){
        	if(entry.getValue() == 1){
        		return entry.getKey();
        	}
        }
        return -1;
    }
	
	public static int singleNumber2(int[] A){
		int x=0;
		 
        for(int a: A){
            x = x ^ a;
        }
 
        return x;
	}
	
	 public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else{
            return 1 + Math.max(maxDepth(root.left),maxDepth(root.right));
        }
	}
	 
	 public static int reverse(int x) {
	        String result ="";
	        if(x == 0){
	            return x;
	        }
	        
	        Boolean minus = false;
	        if(x<0){
	            x = -x;
	            minus = true;
	        }
	        
	        int tmp = x%10;
	        if(minus){
	            result = "-"+tmp;
	        }else{
	            result = tmp+"";
	        }
	        int rest = x/10;
	        while(rest>=1){
	            tmp = rest%10;
	            result += tmp+"";
	            rest = rest/10;
	        }
	        
	        return Integer.parseInt(result);
		 
//		 	StringBuffer result = new StringBuffer();
//	        boolean minus = false;
//	        if(x < 0){
//	            minus = true;
//	            x = -x;
//	            result.append("-");
//	        }
//	        
//	        if(x == 0){
//	            return 0;
//	        }
//	        
//	        while(x > 0){
//	            int tmp = x%10;
//	            result.append(tmp+"");
//	            x = x/10;
//	        }
//	        
//	        return Integer.parseInt(result.toString());
	    }
	
	 
	 /**
	  * Sort Colors Total Accepted: 20855 Total Submissions: 65256 My Submissions
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
	  * @param A
	  */
	 public void sortColors(int[] A) {
	        if(A == null || A.length <= 1){
	            return;
	        }
	        
	        int pl = 0;
	        int pr = A.length - 1;
	        
	        int i = 0;
	        
	        while(i <= pr){
	            if(A[i] == 0){
	                swap2(A, i, pl);
	                pl++;
	                i++;
	            }else if(A[i] == 1){
	                i++;
	            }else{
	                swap2(A, i, pr);
	                pr--;
	            }
	        }
	        
	        return;
	        
	    }
	    
	    public void swap2(int[] A, int i, int j){
	        int temp = A[j];
	        A[j] = A[i];
	        A[i] = temp;
	    }
	 
	 /**Plus One Total Accepted: 17341 Total Submissions: 55050 My Submissions
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.


	  * 
	  * @param digits
	  * @return
	  */
	 public static int[] plusOne(int[] digits) {
	        boolean carryFlag = false;
	        int length = digits.length;
	        int plusBonus = 1;
	        for(int i = length-1; i >= 0; i--){
	            if(i == 0 && carryFlag == true && digits[i]+plusBonus>9){
	                digits[i] = (digits[i]+plusBonus)%10;
	            	int[] newArray = new int[length+1];
	                for(int j = 0; j<newArray.length; j++){
	                    if(j == 0){
	                        newArray[j] = 1;
	                    }else{
	                        newArray[j] = digits[j-1];
	                    }
	                }
	                return newArray;
	            }else{
	                if(i == length-1||carryFlag){
	                	if(digits[i]+plusBonus>9){
	                		digits[i] = (digits[i]+plusBonus)%10;
	                		carryFlag = true;
	                	}else{
	                		digits[i] += plusBonus;
	                		carryFlag = false;
	                	}
	                }
	            }
	        }
	        return digits;
	    }
	 
//	 public boolean hasCycle(ListNode head) {
//	        //Use two pointers, a one step/time, b two steps/time, if there is a circle, they will meet
//	        ListNode oneStepPointer = head;
//	        ListNode twoStepPointer = head;
//	        
//	        while(twoStepPoiner!=null){
//	            oneStepPointer = onStepPointer.next;
//	            twoStepPointer = (twoStepPointer.next.next == null)?twoStepPointer.next:twoStepPointer.next.next；
//	            
//	            if(oneStepPointer == twoStepPointer){
//	                return true;
//	            }
//	        }
//	        
//	        return false;
//	    }
	 
	 public static List<Integer> test(){
		 List<Integer> list1 = new ArrayList<Integer>();
		 list1.add(1);
		 list1.add(2);
		 list1.add(3);
		 list1.add(4);
		 return list1;
	 }
	 
	 public static int climbStairsRecur(int n){
		 if(n==1) return 1;
		 if(n==2) return 2;
		 
		 return climbStairsRecur(n-1) + climbStairsRecur(n-2);
	 }
	 
	 public static int climbStairsRecur2(int n){
		 if(n < 3){
			 return n;
		 }
		 
		 int[] steps = new int[n+1];
		 steps[0] = 1;
		 steps[1] = 1;
		 
		 for(int i = 2; i <= n; i++){
			 steps[i] = steps[i-2] + steps[i-1];
		 }
		 
		 return steps[n];
	 }
	 //int[] A = {-2,2,3,4,-1,2,1,-5,4};
	 public static int maxSubArray(int[] A) {
	        int sum = 0;
	        int maxSum = Integer.MIN_VALUE;
	        for(int i = 0; i < A.length; i++) {
	            sum += A[i];
	            maxSum = Math.max(maxSum, sum);
	            // re-set sum when < 0 (no need to keep neg value)
	            if(sum < 0) sum = 0;
	        }
	        return maxSum;
	    }
	 
	 
	 //ListNode reverse
	 private ListNode reverse(ListNode head) {
	        ListNode newHead = null;
	        while (head != null) {
	            ListNode temp = head.next;
	            head.next = newHead;
	            newHead = head;
	            head = temp;
	        }
	        return newHead;
	    }
	 
	 /**
	  * Generate Parentheses Total Accepted: 18568 Total Submissions: 59142 My Submissions
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
	  * @param n
	  * @return
	  */
	 	public static List<String> generateParenthesis(int n) {
	        List<String> result = new ArrayList<String>();
	        
	        addParentheses(result,0,0,n,"");
	        return result;
	    }
	    
	    public static void addParentheses(List<String> result, int left, int right, int n, String parentheses){

	        if(left<right){
	            return;
	        }
	        
	        if(left == n && right == n){
	            result.add(parentheses);
	            return;
	        }
	        
	        if(left==n){
	            addParentheses(result, left, right+1, n, parentheses+")");
	            return;
	        }
	        addParentheses(result,left+1,right,n,parentheses+"(");
	        addParentheses(result,left,right+1,n,parentheses+")");
	        
	    }
	    
	    /**
	     * Convert Sorted Array to Binary Search Tree Total Accepted: 18955 Total Submissions: 58092 My Submissions
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
	     */
	    
	    /**
	     * Definition for binary tree
	     * public class TreeNode {
	     *     int val;
	     *     TreeNode left;
	     *     TreeNode right;
	     *     TreeNode(int x) { val = x; }
	     * }
	     */
	    public TreeNode sortedArrayToBST(int[] num) {
		       if(num==null || num.length ==0){
		           return null;
		       }
		       return sort(num,0,num.length-1);
	    }
	    
	    public TreeNode sort(int[] num, int start, int end){
	        if(start > end){
	            return null;
	        }
	        TreeNode node = new TreeNode(num[(start+end)/2]);
	        node.left = sort(num, start, (start+end)/2-1);
	        node.right = sort(num,(start+end)/2+1, end);
	        
	        return node;
	        
	    }
	    
	    /**
	     * Permutations Total Accepted: 21762 Total Submissions: 69549 My Submissions
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	     */
	    
	    public static List<List<Integer>> permute(int[] num) {
	        if(num == null || num.length == 0){
	            return null;
	        }
	        List<List<Integer>> res = new ArrayList<List<Integer>>();
	        ArrayList<Integer> list = new ArrayList<Integer>();
	        doit(res, list, num);
	        
	        return res;
	    }
	    
	    public static void doit(List<List<Integer>> res, ArrayList<Integer> list, int[] num){
	        if(list.size() == num.length){
	            res.add(new ArrayList(list));
	            return;
	        }
	        
	        for(int i = 0; i < num.length; i++){
	            if(list.contains(num[i])){
	                continue;
	            }
	            list.add(num[i]);
	            doit(res,list,num);
	            list.remove(list.size()-1);
	        }
	    }
	    
	    /**
	     * Minimum Path Sum Total Accepted: 15823 Total Submissions: 50730 My Submissions
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
	     */
	    public int minPathSum(int[][] grid) {
	        if(grid == null || grid.length == 0 || grid[0].length == 0){
	            return 0;
	        }
	        
	        int m = grid.length;
	        int n = grid[0].length;
	        
	        int[][] sum = new int[m][n];
	        sum[0][0] = grid[0][0];
	        
	        for(int i = 1; i < m; i++){
	            sum[i][0] = sum[i-1][0] + grid[i][0];
	        }
	        for(int j = 1; j < n; j++){
	            sum[0][j] = sum[0][j-1] + grid[0][j];
	        }
	        
	        for(int i = 1; i<m;i++){
	            for(int j=1;j<n;j++){
	                sum[i][j] = Math.min(sum[i-1][j],sum[i][j-1]) + grid[i][j];
	            }
	        }
	        
	        return sum[m-1][n-1];
	    }
	    //No extra space
	    public int minPathSum2(int[][] grid) {
	        if(grid == null || grid.length == 0 || grid[0].length == 0){
	            return 0;
	        }
	        
	        int rows = grid.length;
	        int cols = grid[0].length;
	        
	        for(int i = 1; i < rows; i++){
	            grid[i][0] = grid[i-1][0] + grid[i][0];
	        }
	        
	        for(int j = 1; j < cols; j++){
	            grid[0][j] = grid[0][j-1] + grid[0][j];
	        }
	        
	        for(int i = 1; i < rows; i++){
	            for(int j = 1; j < cols; j++){
	                grid[i][j] = Math.min(grid[i-1][j], grid[i][j-1]) + grid[i][j];
	            }
	        }
	        
	        return grid[rows-1][cols-1];
	    }
	    
	    /**
	     * Insertion Sort List Total Accepted: 19765 Total Submissions: 78265 My Submissions
Sort a linked list using insertion sort.
	     */
	    
	    public ListNode insertionSortList(ListNode head){
	    	if(head==null || head.next == null){
	    		return head;
	    	}
	    	
	    	ListNode dummy = new ListNode(0);
	    	while(head!=null){
	    		ListNode node = dummy;
	    		while(node.next != null && node.next.val < head.val){	
	    			node = node.next;
	    		}
	    		ListNode tmp = head.next;
	    		head.next = node.next;
	    		node.next = head;
	    		head = tmp;
	    	}
	    	
	    	return dummy.next;
	    }
	    
	    /**
	     * Search a 2D Matrix Total Accepted: 18028 Total Submissions: 57811 My Submissions
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true.
	     */
	    public static boolean searchMatrix(int[][] matrix, int target) {
	    	if(matrix == null || matrix.length == 0||matrix[0].length == 0){
	    		return false;
	    	}
	    	int rows = matrix.length;
	    	int cols = matrix[0].length;
	    	
	    	int start = 0;
	    	int end = rows*cols - 1;
	    	
	    	while(start<=end){
	    		int middle = (start+end)/2;
	    		int val = matrix[middle/cols][middle%cols];
	    		
	    		if(val==target){
	    			return true;
	    		}else if(val>target){
	    			end = middle - 1;
	    		}else{
	    			start = middle + 1;
	    		}
	    	}
	    	
	    	return false;
	    }
	    
	    /**
	     * Container With Most Water Total Accepted: 15277 Total Submissions: 49083 My Submissions
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
	     */
	    
	    public static int maxArea(int[] height) {
	        if(height == null || height.length < 2){
	        	return 0;
	        }
	        int max = 0;
	        int left = 0;
	        int right = height.length -1;
	        
	        while(left<right){
	        	max = Math.max(max,(right-left)*Math.min(height[left],height[right]));
	        	
	        	if(height[left]>=height[right]){
	        		right--;
	        	}else{
	        		left++;
	        	}
	        	
	        }
	        
	        return max;
	        
	    }
	    
	    /**
	     * Path Sum Total Accepted: 20421 Total Submissions: 66760 My Submissions
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
	     */
	    
	    public boolean hasPathSum(TreeNode root, int sum) {
	        if(root == null){
	            return false;
	        }
	        if(root.left == null && root.right == null && root.val == sum){
	            return true;
	        }
	        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
	    }
	    
	    /**
	     * Path Sum II Total Accepted: 34331 Total Submissions: 128433 My Submissions Question Solution 
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
	     * @param root
	     * @param sum
	     * @return
	     */
	    public List<List<Integer>> pathSum(TreeNode root, int sum) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if(root == null){
	            return result;
	        }
	        
	        List<Integer> list = new ArrayList<Integer>();
	        
	        helper(result, list, root, sum);
	        
	        return result;
	    }
	    
	    public void helper(List<List<Integer>> result, List<Integer> list, TreeNode root, int sum){
	        if(root == null){
	            return;
	        }
	        sum = sum - root.val;
	        
	        if(root.left == null && root.right == null){
	            if(sum == 0){
	                list.add(root.val);
	                result.add(new ArrayList<Integer>(list));
	                list.remove(list.size() - 1);
	            }
	            return;
	        }
	        
	        list.add(root.val);
	        helper(result, list, root.left, sum);
	        helper(result, list, root.right, sum);
	        list.remove(list.size() - 1);
	    }
	    /**
	     * Set Matrix Zeroes Total Accepted: 15860 Total Submissions: 51401 My Submissions
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:
Did you use extra space?
A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
	     */
	    public static void setZeroes(int[][] matrix) {
	        if(matrix == null || matrix.length == 0||matrix[0].length == 0){
	            return;
	        }
	        
	        int rows = matrix.length;
	        int cols = matrix[0].length;
	        
	        HashMap<Integer, Integer> numMap = new HashMap<Integer,Integer>();
	        
	        for(int i = 0; i<rows; i++){
	            for(int j = 0; j<cols; j++){
	                if(matrix[i][j] == 0){
	                    numMap.put(i,j);
	                }
	            }
	        }
	        for(Map.Entry<Integer,Integer> entry:numMap.entrySet()){
	            zeros(entry.getKey(),entry.getValue(), matrix, rows, cols);
	        }
	        System.out.println(1);
	    }
	    
	    public static void zeros(int m, int n, int[][] matrix, int rows, int cols){
	        for(int i=0; i<cols; i++){
	            matrix[m][i] = 0;
	        }
	        for(int i=0; i<rows; i++){
	            matrix[i][n] = 0;
	        }
	        return;
	    }
	    
	    
	    
	    /**
	     * Best Time to Buy and Sell Stock Total Accepted: 21737 Total Submissions: 70130 My Submissions
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
	     */
	    
	    public static int maxProfit(int[] prices){
	    	int profit = 0;
	    	int min = Integer.MAX_VALUE;
	    	
	    	for(int i : prices){
	    		min = Math.min(i,min);
	    		profit = Math.max(profit, i-min);
	    	}
	    	
	    	return profit;
	    }
	    /**
	     * Best Time to Buy and Sell Stock II Total Accepted: 21565 Total Submissions: 58591 My Submissions
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	     */
	    
	    public static int maxProfit2(int[] prices){
	    	if(prices == null || prices.length == 0){
	    		return 0;
	    	}
	    	int profit = 0;
	    	
	    	for(int i = 0; i < prices.length -1; i++){
	    		int diff = prices[i+1] - prices[i];
	    		if(diff > 0){
	    			profit += diff;
	    		}
	    	}	    	
	    	return profit;
	    }
	    
	    /**
	     * Best Time to Buy and Sell Stock III Total Accepted: 26680 Total Submissions: 114342 My Submissions Question Solution 
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	     */
	    public int maxProfit3(int[] prices) {
	        if(prices == null || prices.length <= 0){
	            return 0;
	        }
	        
	        int len = prices.length;
	        int[] left = new int[len];
	        int[] right = new int[len];
	        
	        //DP from left to right
	        left[0] = 0;
	        int min = prices[0];
	        int maxProfit = Integer.MIN_VALUE;
	        for(int i = 1; i < len; i++){
	            min = Math.min(min, prices[i]);
	            maxProfit = Math.max(maxProfit, prices[i] - min);
	            left[i] = maxProfit;
	        }
	        
	        //DP from right to left
	        right[len-1] = 0;
	        int max = prices[len-1];
	        maxProfit = Integer.MIN_VALUE;
	        for(int i = len - 2; i>=0; i--){
	            max = Math.max(max, prices[i]);
	            maxProfit = Math.max(maxProfit, max - prices[i]);
	            right[i] = maxProfit;
	        }
	        
	        int result = 0;
	        for(int i = 0; i < len; i++){
	            result = Math.max(left[i] + right[i], result);        
	        }
	        
	        return result;
	    }
	    
	    /**
	     * Combinations Total Accepted: 18531 Total Submissions: 61132 My Submissions
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

For example,
If n = 4 and k = 2, a solution is:

[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
	     */
	    
	    public List<List<Integer>> combine(int n, int k){
	    	if(k <= 0||n<k){
	    		return null;
	    	}
	    	
	    	List<List<Integer>> result = new ArrayList<List<Integer>>();
	    	List<Integer> list = new ArrayList<Integer>();
	    	
	    	helper(result,list,n,k,1);
	    	return result;
	    }
	    
	    public void helper(List<List<Integer>> result, List<Integer> list, int n, int k, int start){
	    	if(list.size()==k){
	    		result.add(new ArrayList(list));
	    		return;
	    	}
	    	
	    	for(int i = start; i<=n;i++){
	    		list.add(i);
	    		helper(result, list, n,k,i+1);
	    		list.remove(list.size()-1);
	    	}
	    }
	    /**
	     * Combination Sum Total Accepted: 17593 Total Submissions: 66186 My Submissions
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

The same repeated number may be chosen from C unlimited number of times.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 2,3,6,7 and target 7, 
A solution set is: 
[7] 
[2, 2, 3]
	     * @param args
	     * @throws IOException
	     */
	    
	    public List<List<Integer>> combinationSum(int[] candidates, int target){
	    	if(candidates == null || candidates.length == 0){
	    		return null;
	    	}
	    	
	    	List<List<Integer>> res = new ArrayList<List<Integer>>();
	    	List<Integer> list = new ArrayList<Integer>();
	    	
	    	helper(res,list,candidates, target, 0);
	    	
	    	return res;
	    	
	    }
	    
	    public void helper(List<List<Integer>> res, List<Integer> list, int[] candi, int target, int start){
	    	int sum = 0;
	    	if(list!=null){
	    		for(Integer item : list){
	    			sum += item;
	    		}
	    	}
	    	if(sum >= target){
	    		if(sum == target){
	    			res.add(new ArrayList(list));
	    		}
	    		return;
	    	}
	    	
	    	for(int i = start; i < candi.length; i++){
	    		if(sum+candi[i]>target){
	    			continue;
	    		}
	    		list.add(candi[i]);
	    		helper(res,list,candi,target,i);
	    		list.remove(list.size()-1);
	    	}
	    }
	    /**
	     * Combination Sum II Total Accepted: 26747 Total Submissions: 107099 My Submissions Question Solution 
Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6]
	     */
	    public List<List<Integer>> combinationSum2(int[] num, int target) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        
	        if(num == null || num.length == 0){
	            return result;
	        }
	        Arrays.sort(num);
	        List<Integer> list = new ArrayList<Integer>();
	        int[] visited = new int[num.length];
	        
	        helper(num, target, result, list, 0, visited);
	        
	        return result;
	    }
	    
	    public void helper(int[] num, int target, List<List<Integer>> result, List<Integer> list, int start, int[] visited){
	        int sum = 0;
	        for(int number : list){
	            sum += number;
	        }
	        if(sum == target){
	            result.add(new ArrayList(list));
	            return;
	        }else if(sum > target){
	            return;
	        }
	        
	        for(int i = start; i < num.length; i++){
	            if(visited[i] == 1 || i > 0 && visited[i-1] == 0 && num[i-1] == num[i]){
	                continue;
	            }
	            visited[i] = 1;
	            list.add(num[i]);
	            helper(num, target, result, list, i+1, visited);
	            list.remove(list.size()-1);
	            visited[i] = 0;
	        }
	    }
 
	    /**
	     * Sum Root to Leaf Numbers Total Accepted: 20027 Total Submissions: 67210 My Submissions
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.
	     * @param args
	     * @throws IOException
	     */
	    
	    public static int sumNumbers(TreeNode root){
	    	int sum = 0;
	    	
	    	return sum(root, sum);
	    }
	    
	    public static int sum(TreeNode root, int prev){
	    	if(root == null){
	    		return 0;
	    	}
	    	
	    	int sum = root.val + prev*10;
	    	if(root.left == null && root.right == null){
	    		return sum;
	    	}
	    	
	    	return sum(root.left, sum) + sum(root.right, sum);
	    }
	    
	    /**
	     * Minimum Depth of Binary Tree Total Accepted: 21310 Total Submissions: 72020 My Submissions
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
	     * @param args
	     * @throws IOException
	     */
	    
	    public static int minDepth(TreeNode root){
	    	if(root == null){
	    		return 0;
	    	}
	    	return min(root);
	    }
	    
	    public static int min(TreeNode root){
	    	if(root == null){
	    		return Integer.MAX_VALUE;
	    	}
	    	
	    	if(root.left == null && root.right == null){
	    		return 1;
	    	}
	    	
	    	return Math.min(min(root.left), min(root.right))+1;
	    }

	    
	    public int minDepth2(TreeNode root) {
	        if(root == null){
	            return 0;
	        }
	        
	        Queue<TreeNode> queue = new LinkedList<TreeNode>();
	        queue.offer(root);
	        int depth = 0;
	        
	        while(!queue.isEmpty()){
	            int size = queue.size();
	            depth += 1;
	            for(int i = 0; i < size; i++){
	                TreeNode current = queue.poll();
	                if(current.left == null && current.right==null){
	                    return depth;
	                }
	                if(current.left!=null){
	                    queue.offer(current.left);
	                }
	                if(current.right != null){
	                    queue.offer(current.right);
	                }
	            }
	        }
	        
	        return depth;
	    }
	    
	    /**
	     * Remove Nth Node From End of List Total Accepted: 19997 Total Submissions: 66938 My Submissions
Given a linked list, remove the nth node from the end of list and return its head.

For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
	     * @param args
	     * @throws IOException
	     */
	    public ListNode removeNthFromEnd(ListNode head, int n) {
	    	if(head == null || head.next == null){
	    		return null;
	    	}
	    	ListNode dummy = new ListNode(0);
	    	dummy.next = head;
	    	ListNode preDelete = dummy;
	    	
	    	for(int i = 0; i<n;i++){
	    		head = head.next;
	    	}
	    	
	    	while(head!=null){
	    		head = head.next;
	    		preDelete = preDelete.next;
	    	}
	    	
	    	preDelete.next = preDelete.next.next;
	    	
	    	return dummy.next;
	    	
	    }
	    
	    /**
	     * Sort List Total Accepted: 20156 Total Submissions: 98402 My Submissions
Sort a linked list in O(n log n) time using constant space complexity.
	     * @param args
	     * @throws IOException
	     */
	    public ListNode sortList(ListNode head){
	    	if(head == null || head.next == null){
	    		return head;
	    	}
	    	
	    	ListNode mid = findMid(head);
	    	ListNode right = sortList(mid.next);
	    	mid.next = null;
	    	ListNode left = sortList(head);
	    	
	    	return merge(left,right);
	    }
	    
	    public ListNode findMid(ListNode head){
	    	ListNode slow = head;
	    	ListNode fast = head.next;
	    	
	    	while(fast != null && fast.next != null){
	    		slow = slow.next;
	    		fast = fast.next.next;
	    	}
	    	
	    	return slow;
	    }
	    
	    public ListNode merge(ListNode head1, ListNode head2){
	    	ListNode dummy = new ListNode(0);
	    	ListNode tail = dummy;
	    	
	    	while(head1 != null && head2 != null){
	    		if(head1.val < head2.val){
	    			tail.next = head1;
	    			head1 = head1.next;
	    		}else{
	    			tail.next = head2;
	    			head2 = head2.next;
	    		}
	    		
	    		tail = tail.next;
	    	}
	    	
	    	if(head1!=null){
	    		tail.next = head1;
	    	}else{
	    		tail.next = head2;
	    	}
	    	
	    	return dummy.next;
	    }
	    /**
	     * Binary Tree Level Order Traversal Total Accepted: 20928 Total Submissions: 67889 My Submissions
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
	     * @param args
	     * @throws IOException
	     */
	    public List<List<Integer>> leverOrder(TreeNode root){
	    	List<List<Integer>> result = new ArrayList<List<Integer>>();
	    	if(root == null){
	    		return result;
	    	}
	    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
	    	
	    	queue.offer(root);
	    	
	    	while(!queue.isEmpty()){
	    		List<Integer> levelList = new ArrayList<Integer>();
	    		int size = queue.size();
	    		for(int i = 0; i < size; i++){
	    			TreeNode head = queue.poll();
	    			levelList.add(head.val);
	    			if(head.left != null){
	    				queue.offer(head.left);
	    			}
	    			if(head.right != null){
	    				queue.offer(head.right);
	    			}
	    		}
	    		result.add(new ArrayList<Integer>(levelList));
	    	}
	    	return result;
	    }
	    
	    /**
	     * Palindrome Number Total Accepted: 20593 Total Submissions: 70923 My Submissions
Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
	     * @param args
	     * @throws IOException
	     */
	    public boolean isPalindrome(int x){
	    	if(x < 0){
	    		return false;
	    	}
	    	
	    	return x == reverseNum(x);
	    }
	    
	    public int reverseNum(int x){
	    	int result = 0;
	    	
	    	while(x>0){
	    		result = result * 10 + x % 10;
	    		x = x / 10;
	    	}
	    	
	    	return result;
	    }
	    
	    /**
	     * Valid Palindrome Total Accepted: 39993 Total Submissions: 181986 My Submissions Question Solution 
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
	     */
	    public boolean isPalindrome(String s) {
	        if(s == null || s.length() == 0 || s.length() == 1){
	            return true;
	        }
	        
	        char[] chars = s.toCharArray();
	        
	        int start = 0;
	        int end = chars.length-1;
	        
	        while(start < end){
	            while(!isChar(chars[start])){
	                start += 1;
	                if(start >= chars.length){
	                    break;
	                }
	            }
	            while(!isChar(chars[end])){
	                end -= 1;
	                if(end < 0){
	                    break;
	                }
	            }
	            
	            if(start < end && Character.toLowerCase(chars[start]) != Character.toLowerCase(chars[end])){
	                return false;
	            }
	            start++;
	            end--;
	        }
	        
	        return true;
	    }
	    
	    public boolean isChar(char c){
	        if(Character.isLetter(c) || Character.isDigit(c)){
	            return true;
	        }
	        return false;
	    }
	   
	    /**
	     * Flatten Binary Tree to Linked List Total Accepted: 21467 Total Submissions: 76219 My Submissions
Given a binary tree, flatten it to a linked list in-place.

For example,
Given
	
         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
	     * @param args
	     * @throws IOException
	     */
	    public TreeNode lastNode = null;
	    public void flatten(TreeNode root) {
	    	if(root == null){
	    		return;
	    	}
	    	
	    	if(lastNode != null){
	    		lastNode.left = null;
	    		lastNode.right = root;
	    	}
	    	lastNode = root;
	    	TreeNode right = root.right;
	    	flatten(root.left);
	    	flatten(right);
	    	
	    }
	    
	    /**
	     * Longest Consecutive Sequence Total Accepted: 19443 Total Submissions: 69221 My Submissions
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
	     * @param args
	     * @throws IOException
	     */
	    public int longestConsecutive (int[] num){
	    	if(num == null || num.length == 0){
	    		return 0;
	    	}
	    	
	    	HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
	    	int maxLength = 1;
	    	for(int i : num){
	    		map.put(num[i], 0);
	    	}
	    	
	    	for(int i : num){
	    		if(map.get(i) == 1){
	    			continue;
	    		}
	    		int tmpLength = 1;
	    		map.put(i, 1);
	    		int j = i;
	    		while(map.containsKey(j+1)){
	    			map.put(j+1, 1);
	    			j++;
	    			tmpLength ++;
	    		}
	    		j = i;
	    		while(map.containsKey(j-1)){
	    			map.put(j-1,1);
	    			j--;
	    			tmpLength++;
	    		}
	    		maxLength = Math.max(maxLength, tmpLength);
	    	}
	    	
	    	return maxLength;
	    }
	    /**
	     * Search for a Range Total Accepted: 18461 Total Submissions: 67221 My Submissions
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
	     * @param args
	     * @throws IOException
	     */
	    public int[] searchRange(int[] A, int target) {
	    	int[] bound = new int[2];
	    	bound[0] = -1;
	    	bound[1] = -1;
	    	if(A == null || A.length == 0){
	    		return bound;
	    	}
	    	
	    	int start = 0;
	    	int end = A.length - 1;
	    	while(start + 1 < end){
	    		int mid = (start + end)/2;
	    		if(A[mid] >= target){
	    			end = mid;
	    		}else{
	    			start = mid;
	    		}
	    	}
	    	if(A[start] == target){
	    		bound[0] = start;
	    	}else if(A[end] == target){
	    		bound[0] = end;
	    	}else{
	    		return bound;
	    	}
	    	
	    	
	    	start = 0;
	    	end = A.length - 1;
	    	while(start + 1 < end){
	    		int mid = (start + end)/2;
	    		if(A[mid] <= target){
	    			start = mid;
	    		}else{
	    			end = mid;
	    		}
	    	}
	    	if(A[end] == target){
	    		bound[1] = end;
	    	}else if(A[start] == target){
	    		bound[1] = start;
	    	}else{
	    		return bound;
	    	}
	    	
	    	return bound;
	    }
	    
	    /**
	     * Count and Say Total Accepted: 14707 Total Submissions: 53867 My Submissions
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
	     * @param args
	     * @throws IOException
	     */
	    public String countAndSay(int n) {
	    	String oldString = "1";
	    	
	    	while(--n>0){
	    		char[] chars = oldString.toCharArray();
	    		StringBuffer sb = new StringBuffer();
	    		for(int i = 0; i < chars.length; i++){
	    			int count = 1;
	    			while((i+1) < chars.length && chars[i] == chars[i+1]){
	    				count++;
	    				i++;
	    			}
	    			sb.append(count);
	    			sb.append(chars[i]);
	    		}
	    		oldString = sb.toString();
	    	}
	    	
	    	return oldString;
	    }
	    /**
	     * Jump Game Total Accepted: 18846 Total Submissions: 69266 My Submissions
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.
	     * @param args
	     * @throws IOException
	     */
	    public boolean canJump(int[] A) {
	    	if(A == null || A.length == 0){
	    		return false;
	    	}
	    	
	    	boolean[] can = new boolean[A.length];
	    	can[0] =true;
	    	for(int i = 1; i < A.length; i++){
	    		for(int j = 0; j < i; j++){
	    			if(can[j] == true && j + A[j] >= i){
	    				can[i] = true;
	    				break;
	    			}
	    		}
	    	}
	    	
	    	return can[A.length-1];
	    }
	    
	   /**
	    * Longest Common Prefix Total Accepted: 17506 Total Submissions: 64471 My Submissions
Write a function to find the longest common prefix string amongst an array of strings.
	    * @param args
	    * @throws IOException
	    */
	    public static String longestCommonPrefix(String[] strs) {
	        String commonString = "";
	        
	        if(strs == null || strs.length ==0){
	            return commonString;
	        }
	        
	        commonString = strs[0];
	        
	        for(String item : strs){
	            if(!item.equals(commonString)){
	                StringBuffer sb = new StringBuffer();
	                
	                char[] charsCommon = commonString.toCharArray();
	                char[] charsItem = item.toCharArray();
	                
	                for(int i = 0; i < Math.min(charsCommon.length, charsItem.length); i++){
	                    if(charsCommon[i] == charsItem[i]){
	                        sb.append(charsCommon[i]);
	                    }else{
	                    	break;
	                    }
	                }
	                commonString = sb.toString();
	            }
	        }
	        return commonString;
	    }
	    /**
	     * Triangle. Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
	     * @param args
	     * @throws IOException
	     */
	    public int minimumTotal(List<List<Integer>> triangle) {
	        if(triangle == null || triangle.size() == 0){
	            return 0;
	        }
	        
	        int size = triangle.size();
	        
	        int[][] nums = new int[size][size];
	        
	        for(int j = 0; j < size; j++){
	            nums[size-1][j] = triangle.get(size-1).get(j);
	        }
	        
	        for(int i = size - 2; i >= 0; i--){
	            for(int j = 0; j <= i; j++){
	                nums[i][j] = Math.min(nums[i+1][j], nums[i+1][j+1]) +  + triangle.get(i).get(j);
	            }
	        }
	        
	        return nums[0][0];
	     }
	    
	    /**
	     * Merge Intervals Total Accepted: 27545 Total Submissions: 126790 My Submissions Question Solution 
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
	     */
	    public List<Interval> merge2(List<Interval> intervals) {
	        if(intervals == null || intervals.size() < 2){
	            return intervals;
	        }
	        
	        List<Interval> result = new ArrayList<Interval>();
	        Collections.sort(intervals, new IntervalComparator());
	        
	        Interval last = intervals.get(0);
	        
	        for(int i = 1; i < intervals.size(); i++){
	            Interval current = intervals.get(i);
	            if(current.end <= last.end){
	                continue;
	            }else if(current.start <= last.end){
	                last.end = current.end;
	            }else if(current.start > last.end){
	                result.add(last);
	                last = current;
	            }
	        }
	        result.add(last);
	        
	        return result;
	        
	    }
	    
	    public class IntervalComparator implements Comparator<Interval>{
	        public int compare(Interval a, Interval b){
	            return a.start - b.start;
	        }
	    }
	    
	    /**
	     * Insert Interval Total Accepted: 15252 Total Submissions: 73758 My Submissions
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
	     * @param args
	     * @throws IOException
	     */
	    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
	        List<Interval> result = new ArrayList<Interval>();
	        
	        if(intervals == null || intervals.size() == 0){
	            result.add(newInterval);
	            return result;
	        }
	        //int j = 0;
	        int addPoint = 0;
	        for(; addPoint < intervals.size(); addPoint++){
	            if(intervals.get(addPoint).start >= newInterval.start){
	                break;
	            }
	        }
	        
	        intervals.add(addPoint, newInterval);
	        
	        int startPoint = 0;
	        if(addPoint > 0){
	            startPoint = addPoint - 1;
	        }
	        
	        for(int i = 0; i < startPoint; i++){
	            result.add(intervals.get(i));
	        }
	        
	        Interval last = intervals.get(startPoint);
	        for(int i = startPoint + 1; i < intervals.size(); i++){
	            Interval cur = intervals.get(i);
	            if(cur.end <= last.end){
	                continue;
	            }else if(cur.start <= last.end){
	                last.end = cur.end;
	            }else{
	                result.add(last);
	                last = cur;
	            }
	        }
	        
	        result.add(last);
	        
	        return result;
	       
	    }
	    
	    
	    /**
	     * Partition List Total Accepted: 16965 Total Submissions: 63199 My Submissions
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
	     * @param args
	     * @throws IOException
	     */
	    
	    /**
	     * Binary Tree Zigzag Level Order Traversal Total Accepted: 15266 Total Submissions: 57150 My Submissions
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
	     * @param args
	     * @throws IOException
	     */
	    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        
	        if(root == null){
	            return result;
	        }
	        
	        Queue<TreeNode> queue = new LinkedList<TreeNode>();
	        queue.offer(root);
	        int i = 1;
	        while(queue.peek()!=null){
	            List<Integer> levelList = new ArrayList<Integer>();
	            Stack<Integer> stack = new Stack<Integer>();
	            int size = queue.size();
	            for(int j = 0; j < size; j++){
	                TreeNode current = queue.poll();
	                if(current.left != null){
	                    queue.offer(current.left);
	                }
	                if(current.right != null){
	                    queue.offer(current.right);
	                }
	                if(i % 2 == 0){
	                    stack.push(current.val);
	                }else{
	                    levelList.add(current.val);
	                }    
	            }
	            if(i % 2 == 0){
	                while(!stack.empty()){
	                    levelList.add(stack.pop());
	                }
	            }
	            result.add(new ArrayList(levelList));
	            i++;
	        }
	        
	        return result;
	    }
	    
	    /**
	     * Letter Combinations of a Phone Number Total Accepted: 16208 Total Submissions: 61600 My Submissions
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	     * @param args
	     * @throws IOException
	     */
	    
	    /**
	     * N-Queens Total Accepted: 14262 Total Submissions: 54918 My Submissions
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
	     * @param args
	     * @throws IOException
	     */
	    
	    
	    /**
	     * Palindrome Partitioning Total Accepted: 18348 Total Submissions: 70720 My Submissions
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]
	     * @param args
	     * @throws IOException
	     */
	    public List<List<String>> partition(String s) {
	        List<List<String>> result = new ArrayList<List<String>>();
	        
	        if(s == null || s.length() == 0){
	            return result;
	        }
	        
	        List<String> list = new ArrayList<String>();
	        
	        helper(result, list, s, 0);
	        
	        return result;
	    }
	    
	    public void helper(List<List<String>> result, List<String> list, String s, int start){
	        int totalLength = 0;
	        for(String item : list){
	            totalLength += item.length();
	        }
	        if(totalLength == s.length()){
	            result.add(new ArrayList(list));
	            return;
	        }
	        
	        for(int i = start; i < s.length(); i++){
	            if(isPalindrome2(s.substring(start, i+1))){
	                list.add(s.substring(start,i+1));
	                helper(result, list, s, i + 1);
	                list.remove(list.size()-1);
	            }
	        }
	    }
	    
	    public boolean isPalindrome2(String s){
	        if(s == null || s.length() == 0){
	            return false;
	        }
	        char[] chars = s.toCharArray();
	        int start = 0;
	        int end = chars.length - 1;
	        
	        while(start < end){
	            if(chars[start] != chars[end]){
	                return false;
	            }
	            start++;
	            end--;
	        }
	        
	        return true;
	    }
	    /**
	     * Pow(x, n) Total Accepted: 21876 Total Submissions: 84414 My Submissions
Implement pow(x, n).
	     * @param args
	     * @throws IOException
	     */
	    
	    /**
	     * Edit Distance Total Accepted: 14655 Total Submissions: 57915 My Submissions
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
	     * @param args
	     * @throws IOException
	     */
	    
	    /**
	     * Convert Sorted List to Binary Search Tree Total Accepted: 18661 Total Submissions: 68225 My Submissions
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
	     * @param args
	     * @throws IOException
	     */
	    
	    /**
	     * Linked List Cycle Total Accepted: 51165 Total Submissions: 140824 My Submissions Question Solution 
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
	     */
	    public boolean hasCycle(ListNode head) {
	        if(head == null){
	            return false;
	        }
	        
	        ListNode slow = head;
	        ListNode fast = head;
	        
	        while(fast != null && fast.next != null){
	            slow = slow.next;
	            fast = fast.next.next;
	            if(slow == fast){
	                return true;
	            }
	        }
	        
	        return false;
	    }
	    
	    /**
	     * Linked List Cycle II Total Accepted: 21942 Total Submissions: 70875 My Submissions
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?
	     * @param args
	     * @throws IOException
	     */
	    public ListNode detectCycle(ListNode head) {
	        if(head == null){
		        return head;
	        }
	        
	        ListNode slow = head;
	        ListNode fast = head;
	        
	        while(fast != null && fast.next != null){
	            slow = slow.next;
	            fast = fast.next.next;
	            if(slow == fast){
	                while(head != slow){
	                    head = head.next;
	                    slow = slow.next;
	                }
	                return head;
	            }
	        }
	        
	        return null;
	    }
	    
	    /**
	     * N-Queens II Total Accepted: 12920 Total Submissions: 38785 My Submissions
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

	     * @param args
	     * @throws IOException
	     */
	    static int sum = 0;
	    public static int totalNQueens(int n) {
	        if(n < 1){
	            return 0;
	        }
	        List<Integer> list = new ArrayList<Integer>();
	        helperNqueen2(n, list);
	        return sum;
	    }
	    
	    public static void helperNqueen2(int n, List<Integer> list){
	        if(list.size() == n){
	            sum++;
	            return;
	        }
	        
	        for(int i = 0; i < n ; i++){
	            if(!isValidNqueen2(list,i)){
	                continue;
	            }
	            list.add(i);
	            helperNqueen2(n, list);
	            list.remove(list.size() - 1);
	        }
	    }
	    
	    public static boolean isValidNqueen2(List<Integer> list, int col){
	        int size = list.size();
	        for(int i = 0; i < size; i++){
	            if(list.get(i) == col){
	                return false;
	            }
	            if(i - list.get(i) == size - col){
	                return false;
	            }
	            if(i + list.get(i) == size + col){
	                return false;
	            }
	        }
	        
	        return true;
	    }
	    
/**
 * Remove Duplicates from Sorted Array II Total Accepted: 19090 Total Submissions: 62203 My Submissions
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
 * @param args
 * @throws IOException
 */
	    
	    /**
	     * Anagrams Total Accepted: 15404 Total Submissions: 64689 My Submissions
Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.
	     * @param args
	     * @throws IOException
	     */
	    public List<String> anagrams1(String[] strs) {
	        List<String> result = new ArrayList<String>();
		    	if(strs == null || strs.length == 0){
		    		return result;
		    	}
		    	
		    	HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		    	
		    	for(int i = 0; i < strs.length; i++){
		    		String curSorted = sortStr1(strs[i]);
		    		if(!map.containsKey(curSorted)){
		    			map.put(curSorted, new ArrayList<String>());
		    		}
	    			List<String> arrayList = map.get(curSorted);
	    			arrayList.add(strs[i]);
	    			map.put(curSorted, arrayList);	
		    	}
		    	
		    	for(List<String> list : map.values()){
		    		if(list.size() > 1){
		    			result.addAll(list);
		    		}
		    	}
		    	
		    	return result;
	    }
	    
	    public String sortStr1(String str){
	    	char[] chars = str.toCharArray();
	    	
	    	Arrays.sort(chars);
	    	return new String(chars);
	    }
	    
	    /**
	     * Subsets Total Accepted: 21151 Total Submissions: 75821 My Submissions
Given a set of distinct integers, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
	     * @param args
	     * @throws IOException
	     */
	    public List<List<Integer>> subsets(int[] s) {
	    	List<List<Integer>> result = new ArrayList<List<Integer>>();
	    	if(s == null || s.length == 0){
	    		return result;
	    	}
	    	
	    	List<Integer> list = new ArrayList<Integer>();
	    	Arrays.sort(s);
	    	helper(result, list, s, 0);
	    	return result;
	    }
	    
	    public void helper(List<List<Integer>> result, List<Integer> list, int[] s, int start){
	    	result.add(new ArrayList<Integer>(list));
	    	
	    	for(int i = start; i < s.length; i++){
	    		list.add(s[i]);
	    		helper(result, list, s, i+1);
	    		list.remove(list.size() - 1);
	    	}
	    }
	    /**
	     * Subsets II Total Accepted: 17142 Total Submissions: 63375 My Submissions
Given a collection of integers that might contain duplicates, S, return all possible subsets.

Note:
Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If S = [1,2,2], a solution is:

[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
	     * @param args
	     * @throws IOException
	     */
	    
	    public static List<List<Integer>> subsetsWithDup(int[] num) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if(num == null || num.length == 0){
	            return result;
	        }
	        
	        List<Integer> list = new ArrayList<Integer>();
	        Arrays.sort(num);
	        helperSubSetII(result, list, num, 0);
	        
	        return result;
	    }
	    
	    public static void helperSubSetII(List<List<Integer>> result, List<Integer> list, int[] num, int start){
	        if(!result.contains(list)){
	            result.add(new ArrayList(list));
	            if(list != null && list.size() != 0){
	                return;
	            }
	        }
	        
	        for(int i = start; i < num.length; i++){
	            list.add(num[i]);
	            helperSubSetII(result, list, num, i + 1);
	            list.remove(list.size() - 1);
	        }
	    }
	    /**
	     * First Missing Positive Total Accepted: 17105 Total Submissions: 75836 My Submissions
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.
	     * @param args
	     * @throws IOException
	     */
	    
	    public static int firstMissingPositive(int[] A) {
	    	if(A == null && A.length == 0){
	            return -1;
	        }
	        
	        for(int i = 0; i < A.length; i++){
	            if(A[i] > 0 && A[i] < A.length && A[i] != i+1 && A[i] != A[A[i]-1]){
	                int temp = A[A[i]-1];
	                A[A[i]-1] = A[i];
	                A[i] = temp;
	                i--;
	            }
	        }
	        
	        for(int i = 0; i < A.length; i++){
	            if(A[i] != i+1){
	                return i+1;
	            }
	        }
	        
	        return A.length + 1;

	    }
	    
	    
	    /**
	     * Jump Game II Total Accepted: 17030 Total Submissions: 69013 My Submissions
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
	     * @param args
	     * @throws IOException
	     */
	    
	    /**
	     * Copy List with Random Pointer Total Accepted: 19618 Total Submissions: 84091 My Submissions
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
	     * @param args
	     * @throws IOException
	     */
	    
	    /**
	     * Multiply Strings Total Accepted: 13433 Total Submissions: 65416 My Submissions
Given two numbers represented as strings, return multiplication of the numbers as a string.

Note: The numbers can be arbitrarily large and are non-negative.
	     * @param args
	     * @throws IOException
	     */
	    
	    
	    
	    /**
	     * Valid Palindrome Total Accepted: 20244 Total Submissions: 88222 My Submissions
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
	     * @param args
	     * @throws IOException
	     */
	    
	    /**
	     * Candy Total Accepted: 18644 Total Submissions: 97979 My Submissions
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
	     * @param args
	     * @throws IOException
	     */
	    public static int candy(int[] ratings) {
	        int sum = 0;
	        
	        if(ratings == null || ratings.length == 0){
	            return 0;
	        }
	        
	        if(ratings.length == 1){
	            return 1;
	        }
	        
	        int[] count = new int[ratings.length];
	        
	        Arrays.fill(count, 1);
	        
	        for(int i = 1; i < ratings.length; i++){
	            if(ratings[i] > ratings[i-1]){
	                count[i] = count[i-1] + 1;
	            }
	        }
	        
	        for(int i = ratings.length - 1; i > 0; i--){
	            sum += count[i];
	            if(ratings[i-1] > ratings[i] && count[i-1] <= count[i]){
	                count[i-1] = count[i] + 1;
	            }
	        }
	        
	        sum += count[0];
	        
	        return sum;
	    }
	    /**
	     * Word Ladder Total Accepted: 18513 Total Submissions: 101058 My Submissions
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
	     * @param args
	     * @throws IOException
	     */
	    
	    public static int ladderLength(String start, String end, Set<String> dict) {
	        if(dict == null || dict.size() == 0){
	            return 0;
	        }
	        
	        Queue<String> queue = new LinkedList<String>();
	        queue.offer(start);
	        dict.remove(start);
	        int length = 1;
	        while(!queue.isEmpty()){
	            int size = queue.size();
	            
	            for(int i = 0; i < size; i++){
	                String current = queue.poll();
	                for(int j = 0; j < current.length(); j++){
	                    for(char c = 'a'; c <= 'z'; c++){
	                        if(current.charAt(j) == c){
	                            continue;
	                        }
	                        String temp = replace(current, j, c);
	                        if(dict.contains(temp)){
	                            if(end == temp || end.equals(temp)){
	                                return length+1;
	                            }
	                            queue.offer(temp);
	                            dict.remove(temp);
	                        }
	                        
	                    }
	                }
	            }
	            length ++;
	        }
	        return 0;
	    }
	    
	    public static String replace(String current, int index, char c){
	        char[] newC = current.toCharArray();
	        newC[index] = c;
	        return new String(newC);
	    }
	    
	    /**
	     * Pascal's Triangle Total Accepted: 19610 Total Submissions: 62098 My Submissions
Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
	     * @param args
	     * @throws IOException
	     */
	    public List<List<Integer>> generate(int numRows) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if(numRows < 1){
	            return result;
	        }
	        ArrayList list = new ArrayList();
	        list.add(1);
	        result.add(new ArrayList(list));
	        for(int i = 1; i < numRows; i++){
	            list = new ArrayList();
	            for(int j = 0; j <= i; j++){
	                if(j == 0){
	                    list.add(1);
	                }
	                else if(j >= result.get(i-1).size()){
	                    list.add(1);
	                }else{
	                    list.add(result.get(i-1).get(j-1) + result.get(i-1).get(j));
	                }
	            }
	            result.add(new ArrayList(list));
	        }
	        
	        return result;
	    }
	    /**
	     * 4Sum Total Accepted: 14890 Total Submissions: 69106 My Submissions
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:
Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
The solution set must not contain duplicate quadruplets.
    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

    A solution set is:
    (-1,  0, 0, 1)
    (-2, -1, 1, 2)
    (-2,  0, 0, 2)
	     * @param args
	     * @throws IOException
	     */
	    public List<List<Integer>> fourSum(int[] num, int target) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if(num == null || num.length == 0){
	            return result;
	        }
	        Arrays.sort(num);
	        for(int i = 0; i < num.length -3; i++){
	            if(i != 0 && num[i] == num[i-1]){
	                continue;
	            }
	            for(int j = i+1; j < num.length - 2; j++){
	                if(j != i+1 && num[j] == num[j-1]){
	                    continue;
	                }
	                
	                int left = j + 1;
	                int right = num.length - 1;
	                while(left < right){
	                    int sum = num[i] + num[j] + num[left] + num[right];
	                    if(sum < target){
	                        left++;
	                    }else if(sum > target){
	                        right--;
	                    }else{
	                        List<Integer> temp = new ArrayList<Integer>();
	                        temp.add(num[i]);
	                        temp.add(num[j]);
	                        temp.add(num[left]);
	                        temp.add(num[right]);
	                        
	                        result.add(temp);
	                        left++;
	                        right--;
	                        while(left < right && num[left] == num[left-1]){
	                            left++;
	                        }
	                        while(left < right && num[right] == num[right+1]){
	                            right--;
	                        }
	                    }
	                }
	                
	            }
	        }
	        return result;
	    }
	    /**
	     * Word Search Total Accepted: 15488 Total Submissions: 77855 My Submissions
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ["ABCE"],
  ["SFCS"],
  ["ADEE"]
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
	     * @param args
	     * @throws IOException
	     */
	    public boolean exist(char[][] board, String word) {
	        if(board == null || board.length == 0){
	            return false;
	        }
	        if(word == null || word.length() == 0){
	            return true;
	        }
	        
	        int rows = board.length;
	        int cols = board[0].length;
	        
	        for(int i = 0; i < rows; i++){
	            for(int j = 0; j < cols; j++){
	                if(board[i][j] == word.charAt(0)){
	                    boolean result = helper(board, i, j, word, 0);
	                    if(result){
	                        return true;
	                    }
	                }
	            }
	        }
	        return false;
	    }
	    
	    public boolean helper(char[][] board, int i, int j, String word, int count){
	        if(count == word.length()){
	            return true;
	        }
	        if(i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j] != word.charAt(count)){
	            return false;
	        }
	        
	        board[i][j] = '#';
	        
	        boolean rst = helper(board, i-1, j, word, count+1) || helper(board, i+1, j, word, count+1) ||helper(board, i, j-1, word, count+1) ||helper(board, i, j+1, word, count+1);
	        
	        board[i][j] = word.charAt(count);
	        
	        return rst;
	    }
	    
	    /**
	     * Simplify Path Total Accepted: 12523 Total Submissions: 62752 My Submissions
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
	     * @param args
	     * @throws IOException
	     */
	    public String simplifyPath(String path) {
	        String result = "";
	        if(path == null || path.length() == 0){
	            return result;
	        }
	        String[] sig = path.split("/");
	        Stack<String> stack = new Stack<String>();
	        
	        for(String item : sig){
	            if(item.length() == 0 || item.equals(".")){
	                continue;
	            }else if(item.equals("..")){
	                if(stack.empty()){
	                    continue;
	                }
	                stack.pop();
	            }else{
	                stack.push(item);
	            }
	            
	        }
	        
	        while(!stack.empty()){
	            result = stack.pop() + "/" + result;
	        }
	        if(result.length()>1){
	            result = result.substring(0, result.length()-1);
	        }
	        result = "/"+result;
	        return result;
	    }
	    /**
	     * 3Sum Closest Total Accepted: 16811 Total Submissions: 61984 My Submissions
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	     * @param args
	     * @throws IOException
	     */
	    
	    public int threeSumClosest(int[] num, int target) {
	        if(num == null || num.length < 3){
		            return -1;
	        }
	        
	        int sumClosest = Integer.MAX_VALUE/2;
	        Arrays.sort(num);
	        for(int i = 0; i < num.length - 2; i++){
	            if(i != 0 && num[i] == num[i-1]){
	                continue;
	            }
	            
	            int left = i+1;
	            int right = num.length - 1;
	            while(left<right){
	                int sum = num[i] + num[left] + num[right];
	                sumClosest = (Math.abs(sum - target)<Math.abs(sumClosest - target))?sum:sumClosest;
	                if(sum > target){
	                	right--;
	                }else if(sum < target){
	                	left++;
	                }else{
	                    return sumClosest;
	                }
	            }
	        }
	        return sumClosest;
	    }
	    /**
	     * 3Sum Total Accepted: 31562 Total Submissions: 189563 My Submissions Question Solution 
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
	     */
	    
	    public List<List<Integer>> threeSum(int[] num) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if(num == null || num.length < 3){
	            return result;
	        }
	        Arrays.sort(num);
	        for(int i = 0; i < num.length - 2; i++){
	            if(i != 0 && num[i] == num[i-1]){
	                continue;
	            }
	            int left = i + 1;
	            int right = num.length - 1;
	            
	            while(left < right){
	                int sum = num[i] + num[left] + num[right];
	                if(sum == 0){
	                    List<Integer> list = new ArrayList<Integer>();
	                    list.add(num[i]);
	                    list.add(num[left]);
	                    list.add(num[right]);
	                    result.add(list);
	                    do{
	                        left++;
	                    }while(left+1 < right && num[left] == num[left-1]);
	                    do{
	                        right--;
	                    }while(right - 1 > left && num[right] == num[right+1]);
	                    
	                }else if(sum < 0){
	                    //do{
	                        left++;
	                    //}while(left+1 < right && num[left+1] == num[left]);
	                }else{
	                    //do{
	                        right--;
	                    //}while(right - 1 > left && num[right-1] == num[right]);
	                }
	            }   
	        }
	            
	        return result;
	    }
	    
	    /**
	     * Surrounded Regions Total Accepted: 14032 Total Submissions: 98816 My Submissions
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
	     */
	    
	    public static int rows;
	    public static int cols;
	    
	    public static Queue<Integer> queue;
	    
	    public static char[][] board;
	    
	    public static void solve(char[][] board) {
	        queue = new LinkedList<Integer>();
	        if(board == null || board.length == 0 || board[0].length == 0){
	            return;
	        }
	        rows = board.length;
	        cols = board[0].length;
	        
	        for(int i = 0; i < rows; i++){
	            enqueue(i,0);
	            enqueue(i,cols-1);
	        }
	        
	        for(int i = 1; i < cols-1; i++){
	            enqueue(0,i);
	            enqueue(rows-1,i);
	        }
	        
	        while(!queue.isEmpty()){
	            int cur = queue.poll();
	            int row = cur/cols;
	            int col = cur%cols;
	            if(board[row][col]=='0')
	            board[row][col] = 'D';
	            
	            enqueue(row+1,col);
	            enqueue(row-1,col);
	            enqueue(row,col+1);
	            enqueue(row,col-1);
	        }
	        
	        for(int i = 0; i < rows; i++){
	            for(int j = 0; j < cols; j++){
	                if(board[i][j] == 'D'){
	                    board[i][j] = '0';
	                }else if(board[i][j] == '0'){
	                    board[i][j] = 'X';
	                }
	            }
	        }
	        queue = null;
	        board = null;
	        rows = 0;
	        cols = 0;
	    }
	    
	    public static void enqueue(int m, int n){
	        if(m>=0 && n>=0 && m<rows && n<cols && board[m][n] == '0'){
	            queue.offer(m*cols + n);
	        }
	    }
	    
	    
	    /**
	     * Unique Binary Search Trees Total Accepted: 25214 Total Submissions: 68964 My Submissions
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
	     * @param args
	     * @throws IOException
	     */

	    public int numTrees(int n) {
	        if(n < 2){
	            return n;
	        }
	        
	        int[] count = new int[n+1];
	        
	        count[0] = 1;
	        count[1] = 1;
	        
	        for(int i = 2; i <= n; i++){
	            for(int j = 0; j < i; j++){
	                count[i] += count[j] * count[i-1-j];
	            }
	        }
	        
	        return count[n];
	    }
	    /**
	     * Pascal's Triangle II Total Accepted: 17839 Total Submissions: 58377 My Submissions
Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
	     * @param args
	     * @throws IOException
	     */
	    public List<Integer> getRow(int rowIndex) {
	        List<Integer> result = new ArrayList<Integer>();
	        if(rowIndex < 0){
	            return result;
	        }
	        
	        result.add(1);
	        
	        for(int i = 1; i <= rowIndex; i++){
	            List<Integer> tmp = new ArrayList<Integer>();
	            tmp.add(1);
	            tmp.add(1);
	            for(int j = 1; j < i; j++){
	                tmp.add(j, result.get(j-1)+result.get(j));
	            }
	            result = tmp;
	        }
	        return result;
	    }
	    
	    /**
	     * Word Break Total Accepted: 24315 Total Submissions: 114556 My Submissions
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
	     * @param args
	     * @throws IOException
	     */
	    public boolean wordBreak(String s, Set<String> dict) {
	        if(s == null || s.length() == 0){
	            return false;
	        }
	        
	        boolean[] canBreak = new boolean[s.length()+1];
	        canBreak[0] = true;
	        
	        for(int i = 1; i <= s.length(); i++){
	            canBreak[i] = false;
	            for(int j = 0; j <= i; j++){
	                if(!canBreak[j]){
	                    continue;
	                }
	                if(dict.contains(s.substring(j,i))){
	                    canBreak[i] = true;
	                    break;
	                }
	            }
	        }
	        
	        return canBreak[s.length()];
	    }
	    
	    /**
	     * Word Break II Total Accepted: 16537 Total Submissions: 100359 My Submissions
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
	     * @param args
	     * @throws IOException
	     */
	    public List<String> wordBreakII(String s, Set<String> dict) {
	        Map<String, List<String>> memo = new HashMap<String, List<String>>();
	        
	        return helper(s, dict, memo);
	    }
	    
	    public List<String> helper(String s, Set<String> dict, Map<String, List<String>> memo){
	        if(memo.containsKey(s)){
	            return memo.get(s);
	        }
	        List<String> result = new ArrayList<String>();

	        if(s == null || s.length() == 0){
	            return result;
	        }
	        
	        for(int i = 1; i <= s.length(); i++){
	            String prefix = s.substring(0,i);
	            if(dict.contains(prefix)){
	                if(i == s.length()){
	                    result.add(s);
	                }else{
	                    String subString = s.substring(i);
	                    List<String> temp = helper(subString, dict,memo);
	                    if(temp!=null && temp.size() != 0){
	                        for(String item : temp){
	                            String toAdd = prefix + " " + item;
	                            result.add(toAdd);
	                        }
	                    }
	                }
	            }
	        }
	        memo.put(s, result);
	        return result;
	    }  
	    
	    /**http://www.programcreek.com/2013/10/top-10-questions-for-java-regular-expression/
	     * How to extract numbers from a string?
List<Integer> numbers = new LinkedList<Integer>();
Pattern p = Pattern.compile("\\d+");
Matcher m = p.matcher(str); 
while (m.find()) {
  numbers.add(Integer.parseInt(m.group()));
}
	     * @param args
	     * @throws IOException
	     */
	    
	    
	    /**
	     * Maximum Product Subarray Total Accepted: 2958 Total Submissions: 16948 My Submissions
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
	     * @param args
	     * @throws IOException
	     */
	    public int maxProduct(int[] A) {
	        int maximum = A[0];
	        int maxHere = A[0];
	        int minHere = A[0];
	        
	        for(int i = 1; i < A.length; i++){
	            int maxLocal = maxHere;
	            maxHere = Math.max(Math.max(maxHere*A[i], A[i]), minHere*A[i]);
	            minHere = Math.min(Math.min(maxLocal*A[i], A[i]), minHere*A[i]);
	            maximum = Math.max(maxHere, maximum);
	        }
	        
	        return maximum;
	    }
	    
	    /**
	     * Binary Tree Maximum Path Sum Total Accepted: 20285 Total Submissions: 101152 My Submissions
Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
	     * @param args
	     * @throws IOException
	     */
	    public int maxPathSum(TreeNode root) {
	        ResultType result = helper(root);
	        return result.maxPath;
	    }
	    
	    public ResultType helper(TreeNode root){
	        if(root == null){
	            return new ResultType(0, Integer.MIN_VALUE);
	        }
	        
	        //Divide
	        ResultType left = helper(root.left);
	        ResultType right = helper(root.right);
	        
	        //Conquer
	        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
	        singlePath = Math.max(singlePath, 0);
	        
	        int maxPath = Math.max(left.maxPath, right.maxPath);
	        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
	        
	        return new ResultType(singlePath, maxPath);
	    }
	    
	    public class ResultType{
	        int singlePath;
	        int maxPath;
	        ResultType(int singlePath, int maxPath){
	            this.singlePath = singlePath;
	            this.maxPath = maxPath;
	        }
	    }
	    
	    
	    /**
	     * Trapping Rain Water Total Accepted: 16978 Total Submissions: 58985 My Submissions
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
	     * @param args
	     * @throws IOException
	     */
	    public int trap(int[] A) {
	        int result = 0;
	        if(A == null || A.length == 0){
	            return result;
	        }
	        int maxIndex = -1;
	        int maxValue = 0;
	        int prev = 0;
	        //find the maxIndex
	        for(int i = 0; i < A.length; i++){
	            if(A[i] >= maxValue){
	                maxValue = A[i];
	                maxIndex = i;
	            }
	        }
	        
	        //For left
	        for(int i = 0; i < maxIndex; i++){
	            if(A[i] > prev){
	                result += (maxIndex - i) * (A[i] - prev);
	                prev = A[i];
	            }
	            result -= A[i];
	        }
	        
	        //For right
	        prev = 0;
	        for(int i = A.length - 1; i > maxIndex; i--){
	            if(A[i] > prev){
	                result += (i - maxIndex) * (A[i] - prev);
	                prev = A[i];
	            }
	            result -= A[i];
	        }
	        
	        return result;
	    }
	    
	    
	    /**
	     * Construct Binary Tree from Inorder and Postorder Traversal Total Accepted: 15804 Total Submissions: 59543 My Submissions
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
	     * @param args
	     * @throws IOException
	     */
	    public TreeNode buildTree(int[] inorder, int[] postorder) {
	        if(inorder == null || postorder == null || inorder.length != postorder.length){
	            return null;
	        }
	        
	        return myBuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length-1);
	    }
	    
	    public TreeNode myBuildTree(int[] inorder, int instart, int inend, int[] postorder, int poststart, int postend){
	        if(instart > inend){
	            return null;
	        }
	        
	        TreeNode root = new TreeNode(postorder[postend]);
	        int position = findPosition(inorder, postorder[postend]);
	        
	        root.left = myBuildTree(inorder, instart, position - 1, postorder, poststart, poststart + position - instart -1);
	        root.right = myBuildTree(inorder, position + 1, inend, postorder, poststart + position - instart, postend -1);
	        
	        return root;
	    }
	    
	    public int findPosition(int[] inorder, int value){
	        for(int i = 0; i < inorder.length; i++){
	            if(inorder[i] == value){
	                return i;
	            }
	        }
	        return -1;
	    }
	    
	    /**
	     * Construct Binary Tree from Preorder and Inorder Traversal Total Accepted: 16447 Total Submissions: 61830 My Submissions
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
	     * @param args
	     * @throws IOException
	     */
	    public TreeNode buildTree2(int[] preorder, int[] inorder) {
	        if(preorder == null || inorder == null || preorder.length != inorder.length){
	            return null;
	        }
	        
	        return myBuildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length -1 );
	    }
	    
	    public TreeNode myBuildTree2(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend){
	        if(instart > inend){
	            return null;
	        }
	        
	        TreeNode root = new TreeNode(preorder[prestart]);
	        
	        int position = findPosition(preorder[prestart], inorder);
	        
	        root.left = myBuildTree(preorder, prestart + 1, prestart + position - instart, inorder, instart, position - 1);
	        root.right = myBuildTree(preorder, preend + position - inend + 1, preend, inorder, position + 1, inend);
	        
	        return root;
	    }
	    
	    public int findPosition(int value, int[] inorder){
	        for(int i = 0; i < inorder.length; i++){
	            if(inorder[i] == value){
	                return i;
	            }
	        }
	        
	        return -1;
	    }
	    
	    /**
	     * Balanced Binary Tree Total Accepted: 26255 Total Submissions: 80100 My Submissions
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
	     * @param args
	     * @throws IOException
	     */
	    public boolean isBalanced(TreeNode root) {
	        return maxDepth2(root) != -1;
	    }
	    
	    public int maxDepth2(TreeNode root){
	        if(root == null){
	            return 0;
	        }
	        
	        int left = maxDepth2(root.left);
	        int right = maxDepth2(root.right);
	        
	        if(left == -1 || right == -1 || Math.abs(left - right) > 1){
	            return -1;
	        }
	        
	        return Math.max(left, right) + 1;
	    }
	    /**
	     * Merge Intervals Total Accepted: 17087 Total Submissions: 81963 My Submissions
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
	     * @param args
	     * @throws IOException
	     */
	    /**
	     * Definition for an interval.
	     * public class Interval {
	     *     int start;
	     *     int end;
	     *     Interval() { start = 0; end = 0; }
	     *     Interval(int s, int e) { start = s; end = e; }
	     * }
	     */
	    
	    public List<Interval> merge(List<Interval> intervals) {
	        if(intervals == null || intervals.size() <= 1){
	            return intervals;
	        }
	        
	        Collections.sort(intervals, new IntervalComparator2());
	        
	        Interval last = intervals.get(0);
	        List<Interval> result = new ArrayList<Interval>();
	        
	        for(int i = 1; i < intervals.size(); i++){
	            Interval cur = intervals.get(i);
	            if(cur.end <= last.end){
	                continue;
	            }
	            if(last.end >= cur.start){
	                last.end = cur.end;
	            }else{
	                result.add(last);
	                last = cur;
	            }
	        }
	        
	        result.add(last);
	        return result;
	    }
	    
	    private class IntervalComparator2 implements Comparator<Interval>{
	        public int compare(Interval a, Interval b){
	            return a.start - b.start;
	        }
	    }
	    
	    /**
	     * Valid Sudoku Total Accepted: 15083 Total Submissions: 54100 My Submissions
Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.


A partially filled sudoku which is valid.

Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
	     * @param args
	     * @throws IOException
	     */
	    public boolean isValidSudoku(char[][] board) {
	        if(board == null || board[0] == null || board.length != board[0].length){
	            return false;
	        }
	        
	        boolean[] visited = new boolean[9];
	        
	        //rows
	        for(int i = 0; i < 9; i++){
	            Arrays.fill(visited, false);
	            for(int j = 0; j < 9; j++){
	                if(!validate(visited, board[i][j])){
	                    return false;
	                }
	            }
	        }
	        
	        //cols
	        for(int j = 0; j < 9; j++){
	            Arrays.fill(visited, false);
	            for(int i = 0; i < 9; i++){
	                if(!validate(visited, board[i][j])){
	                    return false;
	                }
	            }
	        }
	        
	        //submatrix
	        for(int i = 0; i < 9; i += 3){
	            for(int j = 0; j < 9; j += 3){
	                Arrays.fill(visited, false);
	                for(int m = 0; m < 9; m++){
	                    if(!validate(visited, board[i + m/3][j + m%3])){
	                        return false;
	                    }
	                }
	            }
	        }
	        
	        return true;
	    }
	    
	    public boolean validate(boolean[] visited, char c){
	        if(c == '.'){
	            return true;
	        }
	        
	        int num = c - '0';
	        if(visited[num - 1]){
	            return false;
	        }
	        visited[num - 1] = true;
	        return true;
	    }
	    
	    /**
	     * Max Points on a Line Total Accepted: 21962 Total Submissions: 197934 My Submissions
Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
	     * @param args
	     * @throws IOException
	     */
	    
	     static class Point {
	          int x;
	          int y;
	          Point() { x = 0; y = 0; }
	          Point(int a, int b) { x = a; y = b; }
	     }
	     
	    
	    public static int maxPoints(Point[] points) {
	        if(points == null || points.length == 0){
	            return 0;
	        }
	        
	        int max = 1;
	        Map<Double, Integer> map = new HashMap<Double, Integer>();
	        
	        for(int i = 0; i < points.length; i++){
	            map.clear();
	            map.put((double)Integer.MIN_VALUE, 1);
	            int dup = 0;
	            
	            for(int j = i+1; j < points.length; j++){
	                if(points[i].x == points[j].x && points[i].y == points[j].y){
	                    dup++;
	                    continue;
	                }
	                
	                if((points[j].x - points[i].x) == 0){
	                    map.put((double)Integer.MIN_VALUE,map.get((double)Integer.MIN_VALUE)+1);
	                }else{
	                    double slope = 0.0 + (double)(points[j].y - points[i].y)/(double)(points[j].x - points[i].x);
	                    
	                    if(map.containsKey(slope)){
	                        map.put(slope,map.get(slope)+1);
	                    }else{
	                        map.put(slope, 2);
	                    }
	                }
	                
	            }
	            
	            for(int m : map.values()){
	                if(m + dup > max){
	                    max = m + dup;
	                }
	            }
	        }
	        
	        return max;
	    }
	    
	    /**
	     * estore IP Addresses Total Accepted: 16452 Total Submissions: 80138 My Submissions
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
	     * @param args
	     * @throws IOException
	     */
	    public List<String> restoreIpAddresses(String s) {
	        List<String> result = new ArrayList<String>();
	        
	        if(s == null || s.length() < 4){
	            return result;
	        }
	        List<String> list = new ArrayList<String>();
	        
	        helper(result, list, 0, s);
	        
	        return result;
	    }
	    
	    public void helper(List<String> result, List<String> list, int start, String s){
	        if(list.size() == 4){
	            if(start != s.length()){
	                return;
	            }
	            
	            StringBuffer sb = new StringBuffer();
	            for(String i : list){
	                sb.append(i);
	                sb.append(".");
	            }
	            String temp = sb.toString();
	            result.add(temp.substring(0, temp.length() - 1));
	            return;
	        }
	        
	        for(int i = start; i < s.length() && i <= start+3; i ++){
	            String toAdd = s.substring(start, i + 1);
	            if(isValid(toAdd)){
	                list.add(toAdd);
	                helper(result, list, i + 1, s);
	                list.remove(list.size() - 1);
	            }
	        }
	    }
	    
	    public boolean isValid(String string){
	        if(string.length() > 1 && string.charAt(0) == '0'){
	            return false;
	        }
	        if(Integer.parseInt(string)>255 || Integer.parseInt(string) < 0){
	            return false;
	        }
	        return true;
	    }
	    
	    /**
	     * Rotate Image Total Accepted: 22924 Total Submissions: 72843 My Submissions Question Solution 
You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
	     * @param args
	     * @throws IOException
	     */
	    public void rotate(int[][] matrix) {
	        int n = matrix.length;
	        
	        for(int i = 0; i<n/2; i++){
	            for(int j = 0; j<Math.ceil(((double)n)/2.);j++){
	                int tmp = matrix[i][j];
	                matrix[i][j] = matrix[n-1-j][i];
	                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
	                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
	                matrix[j][n-1-i] = tmp;
	            }
	        }
	    }
	    
	    /**
	     * Create Hashtable
	     * @param args
	     * @throws IOException
	     */
	    static class myHashEntry{
	    	private String key;
	    	private String value;

	    	myHashEntry(String key, String value){
	    		this.key = key;
	    		this.value = value;
	    	}

	    	public String getKey(){
	    		return this.key;
	    	}

	    	public String getValue(){
	    		return this.value;
	    	}
	    }

	    static class myHashMap{
	    	public static int tableSize = 128;

	    	myHashEntry[] hashTable;

	    	myHashMap(){
	    		hashTable = new myHashEntry[tableSize];
	    		for(int i = 0; i < tableSize; i++){
	    			hashTable[i] = null;
	    		}
	    	}

	    	public int getHash(String key,int plus){
	    		if(key == null || key.length() == 0){
	    			return -1;
	    		}
	    		char[] chars = key.toCharArray();
	    		int value = 0;
	    		for(char c : chars){
	    			value += c;
	    		}
	    		return (value+plus)%tableSize;
	    	}

	    	public boolean put(String key, String value){
	    		int plus = 0;
	    		int hash = getHash(key,plus);
	    		if(hash==-1){
	    			return false;
	    		}
	    		while(hashTable[hash]!=null){
	    			hash = getHash(key, ++plus);
	    		}
	    		hashTable[hash] = new myHashEntry(key,value);
	    		return true;
	    	}
	    	
	    	public String get(String key){
	    		int plus = 0;
	    		int hash = getHash(key,plus);
	    		if(hash==-1){
	    			return "ERROR";
	    		}
	    		while(hashTable[hash]!=null && !hashTable[hash].getKey().equals(key)){
	    			hash = getHash(key, ++plus);
	    		}
	    		if(hashTable[hash] == null){
	    			return null;
	    		}else{
	    			return hashTable[hash].getValue();
	    		}
	    	}

	    }
	    
	    /**
	     * Anagrams Total Accepted: 21609 Total Submissions: 90484 My Submissions Question Solution 
Given an array of strings, return all groups of strings that are anagrams.

Note: All inputs will be in lower-case.
	     * @param strs
	     * @return
	     */
	    public List<String> anagrams(String[] strs) {
	        List<String> result = new ArrayList<String>();
	        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
	        
	        if(strs == null || strs.length == 0){
	            return result;
	        }
	        
	        for(String str : strs){
	            int[] count = new int[26];
	            
	            for(int i = 0; i < str.length(); i++){
	                count[str.charAt(i)-'a']++;
	            }
	            
	            int hash = getHashanagrams(count);
	            if(!map.containsKey(hash)){
	                map.put(hash, new ArrayList<String>());
	            }
	            
	            map.get(hash).add(str);
	        }
	        
	        for(List<String> item : map.values()){
	            if(item.size() > 1){
	                result.addAll(item);
	            }
	        }
	        return result;
	    }
	    
	    public int getHashanagrams(int[] count){
	        int a = 378551;
	        int b = 63689;
	        
	        int hash = 0;
	        
	        for(int c : count){
	            hash = hash * a + c;
	            a = a * b;
	        }
	        
	        return hash;
	    }
	    
	    //Another Method
	    public List<String> anagrams2(String[] strs){
	    	List<String> result = new ArrayList<String>();
	    	if(strs == null || strs.length == 0){
	    		return result;
	    	}
	    	
	    	HashMap<String, List<String>> map = new HashMap<String, List<String>>();
	    	
	    	for(int i = 0; i < strs.length; i++){
	    		String curSorted = sortStr(strs[i]);
	    		if(!map.containsKey(curSorted)){
	    			map.put(curSorted, new ArrayList<String>());
	    		}
    			List<String> arrayList = map.get(curSorted);
    			arrayList.add(strs[i]);
    			map.put(curSorted, arrayList);	
	    	}
	    	
	    	for(List<String> list : map.values()){
	    		if(list.size() > 1){
	    			result.addAll(list);
	    		}
	    	}
	    	
	    	return result;
	    }
	    
	    public String sortStr(String str){
	    	char[] chars = str.toCharArray();
	    	
	    	Arrays.sort(chars);
	    	return new String(chars);
	    }
	    
	    /**
	     * Search Insert Position Total Accepted: 34928 Total Submissions: 99618 My Submissions Question Solution 
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
	     * @param args
	     * @throws IOException
	     */
	    public int searchInsert(int[] A, int target) {
	        if(A == null || A.length == 0){
	            return -1;
	        }
	        
	        if(A.length == 1){
	            if(target > A[0]){
	                return 1;
	            }else{
	                return 0;
	            }
	        }
	        
	        int start = 0;
	        int end = A.length - 1;
	        
	        while(start + 1 < end){
	            int mid = start + (end - start)/2;
	            if(A[mid] >= target){
	                end = mid;
	            }else{
	                start = mid;
	            }
	        }
	        
	        if(A[start] >= target){
	            return start;
	        }else if(A[end] < target){
	            return end + 1;
	        }else{
	            return end;
	        }
	    }
	    
	   /**
	    * Median of Two Sorted Arrays Total Accepted: 28629 Total Submissions: 162257 My Submissions Question Solution 
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).	
	    * @param args
	    * @throws IOException
	    */
	    public double findMedianSortedArrays(int A[], int B[]) {
	        int len = A.length + B.length;
	        if (len % 2 == 0) {
	            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0 ;
	        } else {
	            return findKth(A, 0, B, 0, len / 2 + 1);
	        }
	    }
	    
	    // find kth number of two sorted array
	    public static int findKth(int[] A, int A_start, int[] B, int B_start, int k){		
			if(A_start >= A.length) 
				return B[B_start + k - 1];
			if(B_start >= B.length)
				return A[A_start + k - 1];

			if (k == 1)
				return Math.min(A[A_start], B[B_start]);
			
			int A_key = A_start + k / 2 - 1 < A.length
			            ? A[A_start + k / 2 - 1]
			            : Integer.MAX_VALUE;
			int B_key = B_start + k / 2 - 1 < B.length
			            ? B[B_start + k / 2 - 1]
			            : Integer.MAX_VALUE; 
			
			if (A_key < B_key) {
				return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
			} else {
				return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
			}
		}
	    
	    /**
	     * One editing distance
	     * @param args
	     * @throws IOException
	     */
	    public static boolean isOneEditDistance(String s, String t){
	    	if(s == null || t == null){
	    		return false;
	    	}
	    	int m = s.length();
	    	int n = t.length();
	    	
	    	if(m > n){
	    		isOneEditDistance(t,s);
	    	}
	    	
	    	if(n - m > 1){
	    		return false;
	    	}
	    	
	    	int shift = n - m;
	    	int i = 0;
	    	
	    	for(; i < m; i++){
	    		if(s.charAt(i) != t.charAt(i)){
	    			break;
	    		}
	    	}
	    	if(i == m && shift == 1){
	    		return true;
	    	}
	    	if(shift == 0){
	    		i++;
	    	}
	    	for(;i < m; i++){
	    		if(s.charAt(i) != t.charAt(i+shift)){
	    			return false;
	    		}
	    	}
	    	return true;
	    }
	    
	    //OneEditDistance upgraded. Plus output
	    static boolean isDelete = false;
        public static String solution(String S, String T) {
            // write your code in Java SE 8
            if(S == null || T == null){
                return "IMPOSSIBLE";
            }
            
            int m = S.length();
            int n = T.length();
            int flag = -1;
            
            if(m > n){
                isDelete = true;
                solution(T, S);
            }
            
            if(n - m > 1){
                return "IMPOSSIBLE";
            }
            
            int shift = n - m;
            int i = 0;
            
            for(; i < m; i++){
                if(S.charAt(i) != T.charAt(i)){
                    break;
                }
            }
            if(i == m && shift == 1){
                if(isDelete){
                    return "DELETE "+T.charAt(T.length()-1);
                }else{
                    return "INSERT "+T.charAt(T.length()-1);
                }
            }
            flag = i;
            if(shift == 0){
                i++;
            }
            for(; i < m; i++){
                if(S.charAt(i) != T.charAt(i+shift)){
                    if(shift == 0 && S.charAt(flag) == T.charAt(i) && S.charAt(i) == T.charAt(flag)){
                        return "SWAP "+S.charAt(flag)+" "+S.charAt(i);
                    }
                    
                    
                    return "IMPOSSIBLE";
                }
            }
            
            if(shift == 1){
                if(isDelete){
                    return "DELETE "+T.charAt(flag);
                }else{
                    return "INSERT "+T.charAt(flag);
                }
            }else{
                return "IMPOSSIBLE";
            }    
        }
	    /**
	     * Compare Version Numbers Total Accepted: 7285 Total Submissions: 49164 My Submissions Question Solution 
Compare two version numbers version1 and version1.
If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

You may assume that the version strings are non-empty and contain only digits and the . character.
The . character does not represent a decimal point and is used to separate number sequences.
For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

Here is an example of version numbers ordering:

0.1 < 1.1 < 1.2 < 13.37
	     * @param args
	     * @throws IOException
	     */
	    public int compareVersion(String version1, String version2) {
	        if(version1.equals(version2))  
	        return 0;  
	          
	        int fversion1 , fversion2;  
	        String sversion1,sversion2;  
	        if(version1.contains(".")){  
	            int pos = version1.indexOf(".");  
	            fversion1 = Integer.valueOf(version1.substring(0,pos));  
	            sversion1 = version1.substring(pos+1,version1.length());  
	        }  
	        else {  
	            fversion1 = Integer.valueOf(version1);  
	            sversion1 = "0";  
	        }  
	          
	        if(version2.contains(".")){  
	            int pos = version2.indexOf(".");  
	            fversion2 = Integer.valueOf(version2.substring(0,pos));  
	            sversion2 = version2.substring(pos+1,version2.length());  
	        }  
	        else {  
	            fversion2 = Integer.valueOf(version2);  
	            sversion2 = "0";  
	        }  
	          
	        if(fversion1 > fversion2)  
	            return 1;  
	        else if(fversion1 < fversion2)  
	            return -1;  
	        else return compareVersion(sversion1, sversion2); 
	    }
	    
	    /**
	     * Excel Sheet Column Number Total Accepted: 7446 Total Submissions: 18999 My Submissions Question Solution 
Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
	     * @param args
	     * @throws IOException
	     */
	    public static int titleToNumber(String s) {
	        if(s == null || s.length() == 0){
	            return 0;
	        }
	        
	        int result = 0;
	        return cal(s, result);
	        
//	        return result;
	    }
	    
	    public static int cal(String s, int result){
	        int length = s.length();
	        
	        char c = s.charAt(0);
	        int num = c - 'A' + 1;
	        if(length == 1){
	            result += num;
	        }else{
	            result += num * Math.pow(26,length-1);
	        }
	        if(length > 1){
	            return cal(s.substring(1,length), result);
	        }else{
	            return result;
	        }
	    }
	    
	    /**
	     * Largest Number Total Accepted: 6225 Total Submissions: 42033 My Submissions Question Solution 
Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
	     * @param args
	     * @throws IOException
	     */
	    public static String largestNumber(int[] num) {
	        StringBuffer sb = new StringBuffer();
	        if(num == null || num.length == 0){
	            return "";
	        }
	        
	        int length = num.length;
	        while(length > 0){
	            int levelmaxP = 0;
	            int levelmax = num[0]/((num[0]+"").length()*10);
	            if(length > 1){
	                for(int i = 1; i < length; i++){
	                    String current = num[i] + "";
	                    int currentmax = num[i]/(current.length()*10);
	                    
	                    if(currentmax > levelmax){
	                        levelmaxP = i;
	                        levelmax = currentmax;
	                    }
	                }
	            }
	            
	            sb.append(num[levelmaxP]+"");
	            num[levelmaxP] = num[--length];
	        }
	        
	        return sb.toString();
	    }
	    
	    /**
	     * Two Sum Total Accepted: 60529 Total Submissions: 333771 My Submissions Question Solution 
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
	     * @param args
	     * @throws IOException
	     */
	    
	    public static int[] twosum(int[] numbers, int target){
	    	int[] result = new int[2];
	    	if(numbers == null || numbers.length < 2){
	    		return result;
	    	}
	    	
	    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	    	
	    	for(int i = 0; i < numbers.length; i++){
	    		if(map.containsKey(target - numbers[i])){
	    			result[0] = map.get(target - numbers[i]) + 1;
	    			result[1] = i + 1;
	    			break;
	    		}else{
	    			map.put(numbers[i], i);
	    		}
	    	}
	    	
	    	return result;
	    }
	    
	    /**
	     * Add Two Numbers Total Accepted: 40697 Total Submissions: 179701 My Submissions Question Solution 
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
	     * @param args
	     * @throws IOException
	     */
	    
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
	    	ListNode result = new ListNode(0);
	    	ListNode dummy = result;
	    	
	    	int carry = 0;
	    	
	    	while(l1 != null || l2 != null){
	    		if(l1 != null){
	    			carry += l1.val;
	    			l1 = l1.next;
	    		}
	    		if(l2 != null){
	    			carry += l2.val;
	    			l2 = l2.next;
	    		}
	    		
	    		result.next = new ListNode(carry%10);
	    		result = result.next;
	    		carry = carry / 10;
	    	}
	    	
	    	if(carry == 1){
	    		result.next = new ListNode(1);
	    	}
	    	
	    	return dummy.next;
	    	
	    }
	    
	    /**
	     * Longest Substring Without Repeating Characters Total Accepted: 42967 Total Submissions: 196044 My Submissions Question Solution 
Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
	     * @param args
	     * @throws IOException
	     */
	    
	    public static int lengthOfLongestSubstring(String s){
	    	int result = 0;
	    	if(s == null || s.length() == 0){
	    		return result;
	    	}
	    	
	    	char[] chars = s.toCharArray();
	    	boolean[] exits = new boolean[256];
	    	int i = 0;
	    	int j = 0;
	    	
	    	while(j < chars.length){
	    	    if(exits[chars[j]]){
	    	        result = Math.max(result, j - i);
	    	        while(chars[i] != chars[j]){
	    	            exits[chars[i]] = false;
	    	            i++;
	    	        }
	    	        i++;
	    	    }else{
	    	        exits[chars[j]] = true;
	    	    }
	    	    j++;
	    	}
	    	
	    	result = Math.max(result, chars.length - i);
	    	
	    	return result;
	    }
	    
//	    Write code in a language of your choice to:
//	    	Match answers to appropriate questions.
//	    	Assume IDs are unique, questions/answers array has up to 1 million q/a
//	    	Print each question and matching answers in the following format:
//
//	    	(Question ID) Question Text, User
//	    		(Answer ID) Answer Text, User
//	    		...
//
//	    	#Question ID,User,Time,Question Text,Answer IDs
//	    	questions = ['6433,bill,1337366219,"How do I iterate over a list in python?","15203, 112"',
//	    		       '3453,bill,1337366209,"How do I iterate over a list in go?","987"',
//	    			'64633,bill,1337566219,"How do I iterate over a list in java?","145, 177"']
//
//	    	#Answer ID,Question ID,User,Time,Answer Text
//	    	answers = ['15203,6433,tom,1337367859,"Like this..."',
//	    		    '112,6433,sam,1337364759,"Another way..."',
//	    		    '1112,64633,bob,1347367759,"Another way..."',
//	    	    '145,3453,jane,1237367759,"Another way..."']
//


	    	public void solution(String[] questions, String[] answers){
	    		if(questions == null || questions.length == 0 || answers == null || answers.length == 0){
	    			return;
	    		}
	    		HashMap<String, List<String>> qMap = new HashMap<String, List<String>>();
	    		HashMap<String, List<String>> aMap = new HashMap<String, List<String>>();

	    		String[] questionStringArray = null;
	    		String[] questionStringArray2 = null;
	    		for(String questionString : questions){
	    			questionStringArray = questionString.split(",");
	    			questionStringArray2 = questionString.split("'");
	    			String key = questionStringArray[0];
	    			List<String> stringList = new ArrayList<String>();
	    			//Order: User, QuestionText, AnswerId
	    			stringList.add(questionStringArray[1]);
	    			stringList.add(questionStringArray2[1]);
	    			stringList.add(questionStringArray2[3]);
	    			qMap.put(key,stringList);
	    		}
	    		for(String answerString : answers){
	    			String[] answerStringArray = answerString.split(",");
	    			String key = answerStringArray[0];
	    			List<String> stringList = new ArrayList<String>();
	    			//Order:QuestionId, User, Answer Text
	    			stringList.add(questionStringArray[1]);
	    			stringList.add(questionStringArray[2]);
	    			stringList.add(questionStringArray[4].substring(1,questionStringArray[4].length() - 1));

	    			aMap.put(key,stringList);
	    		}
	    		for(Map.Entry<String, List<String>> entry : qMap.entrySet()){
	    			System.out.print("(");
	    			System.out.print(entry.getKey());
	    			System.out.print(")");
	    			System.out.print(entry.getValue().get(1) + ", ");
	    			System.out.print(entry.getValue().get(0));
	    			
	    			String[] answerIds = entry.getValue().get(2).split(",");
	    			for(String answerId : answerIds){
	    				if(aMap.containsKey(answerId)){
	    					List<String> answerInfo = aMap.get(answerId);
	    					if(answerInfo.get(0).equals(entry.getKey())){
	    						System.out.print("\n");
	    						System.out.print("(");
	    						System.out.print(answerId);
	    						System.out.print(")");
	    						System.out.print(answerInfo.get(2) + ", ");
	    						System.out.print(answerInfo.get(1));
	    					}
	    				}
	    			}
	    		}
	    	}
	    	
	    	
	        
	        //Find the equilibrium coordinate
	        public static int solution3(int[][] A) {
	            // write your code in Java SE 8
	            int rows = A.length;
	            int cols = A[0].length;
	            int sumAll = 0;
	            int result = 0;
	            
	            int[] rowSum = new int[rows];
	            int[] colSum = new int[cols];
	            
	            for(int i = 0; i < rows; i++){
	                for(int j = 0; j < cols; j++){
	                    rowSum[i] += A[i][j];
	                    sumAll += A[i][j];
	                }
	            }    
	            
	            for(int j = 0; j < cols; j++){
	                for(int i = 0; i < rows; i++){
	                    colSum[j] += A[i][j];
	                }
	            }
	            
	            int checkRowSum = 0;
	            for(int i = 0; i < rows; i++){
	                if(checkRowSum == (sumAll - rowSum[i])/2){
	                    int checkColSum = 0;
	                    for(int j = 0; j < cols; j++){
	                        if(checkColSum == (sumAll - colSum[j])/2){
	                            result++;
	                        }
	                        checkColSum += colSum[j];
	                    }
	                }
	                checkRowSum += rowSum[i];
	            }
	            
	            return result;
	        }
		//////Main=---------------------------------
		public static void main(String[] args) throws IOException{
			//System.out.println(titleToNumber("AA"));
			
		//		String[] tokens = new String[] { "2", "1", "+", "3", "*" };
		//		System.out.println(evalRPN(tokens));
			//String s = "the sky is blue";
			//int[] A = {1,2};
			String[] A = {"4","-2","/","2","-3","-","-"};
			ListNode head = new ListNode(3);
			ListNode head2 = new ListNode(5);
			head.next = head2;
			head2.next = null;
//			solution("form", "from");
			int[][] B= {{2,7,5},{3,1,1},{2,1,-7},{0,2,1},{1,6,8}};
			//solution3(B) ;
			int[] C = {2,0,0};
			//sortColors(C);
			reverseBetween(head, 1,2);
			//evalRPN2(A);
			//int[] A = {1,1,1,2,2,2,0,0,0,2,2,1,1};
			//int[] plusOne = {9,9,9,9,9,9,9};
			//int[] A = {1,2,3};
			//permute(A);
		//		int a = -123457;
			//sortedArrayToBST(A);
			//System.out.println(generateParenthesis(8));
			//test();
			//int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		//		int target = 11;
			//int[][] A = {{3,5,5,6,9,1,4,5,0,5},{2,7,9,5,9,5,4,9,6,8},{6,0,7,8,1,0,1,6,8,1},{7,2,6,5,8,5,6,5,0,6},{2,3,3,1,0,4,6,5,3,5},{5,9,7,3,8,8,5,1,4,3},{2,4,7,9,9,8,4,7,3,7},{3,5,2,8,8,2,2,4,9,8}};
			//setZeroes(A);
			//String[] strs = {"aca","cba"};
			//longestCommonPrefix(strs);
			
//			char[][] chars = new char[3][3];
//			chars[0] = "XXX".toCharArray();
//			chars[1] = "X0X".toCharArray();
//			chars[2] = "XXX".toCharArray();
//			solve(chars);
//			
//			Point a = new Point(2,3);
//			Point b = new Point(3,3);
//			Point c = new Point(-5,3);
//			Point[] collect = new Point[]{a,b,c};
//			maxPoints(collect);
			
//			if(isOneEditDistance("abc","abd")){
//				System.out.println("true");
//			}else{
//				System.out.println("false");
//			}
			
		}
}