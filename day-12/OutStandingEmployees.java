/*
Imagine a company where each employee has a performance score, and 
the organizational chart is structured as a binary tree with the CEO at the top. 
An employee is considered "outstanding" if, along the chain of command from the 
CEO down to that employee, no one has a performance score higher than that 
employee's score. Your task is to determine the total number of outstanding 
employees in the company.

Example 1:
Input: 3 1 4 3 -1 1 5
Output: 4

Chart formed:
         3
        / \
       1   4
      /   / \
     3   1   5

Explanation:
- The CEO (score 3) is automatically outstanding.
- The employee with score 4, whose chain is [3,4], is outstanding because 4 
  is higher than 3.
- The employee with score 5, following the path [3,4,5], is outstanding as 5 
  is the highest so far.
- The subordinate with score 3, along the path [3,1,3], is outstanding because 
  none of the managers in that chain have a score exceeding 3.

Example 2:
Input: 3 3 -1 4 2
Output: 3

Chart formed:
       3
      / 
     3
    / \
   4   2

Explanation:
- The CEO (score 3) is outstanding.
- The subordinate with score 3 (chain: [3,3]) is outstanding.
- The employee with score 2 (chain: [3,3,2]) is not outstanding because the 
  managers have higher scores.

 */

import java.util.*;
class TreeNode{
    TreeNode right;
    TreeNode left;
    int data;
    TreeNode(int data) {
        this.data = data;
    }
}
class OutStandingEmployees{
    static int count;
//  static int max ;
    public static void find(TreeNode root,int max){
        if(root == null ) return;
        if(root.data >= max){
            count++;
            max = Math.max(root.data,max);
        }
        find(root.left,max);
        find(root.right,max);
    }
    
    public static TreeNode buildTree(int[] nodes){
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int idx = 1;
        while (!q.isEmpty()){
            TreeNode n = q.poll();
            if (idx < nodes.length && nodes[idx] != -1) {
                n.left = new TreeNode(nodes[idx]);
                q.add(n.left);
            }
            idx++;
            if (idx < nodes.length && nodes[idx] != -1) {
                n.right = new TreeNode(nodes[idx]);
                q.add(n.right);
            }
            idx++;
        }
        return root;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String nodesString[] = input.split("\\s+");
        int[] nodes = new int[nodesString.length];
        for (int i = 0; i < nodesString.length; i++) {
            nodes[i] = Integer.parseInt(nodesString[i]);
        }
        TreeNode root = buildTree(nodes);
        count= 0;
        find(root,root.data);
        System.out.print(count);
    }
}