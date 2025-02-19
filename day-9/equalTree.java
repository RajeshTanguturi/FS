/*
Balbir Singh is working with networked systems, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) has 
a certain processing load (integer value).

Balbir has been given a critical task: split the network into two balanced 
sub-networks by removing only one connection (edge). The total processing 
load in both resulting sub-networks must be equal after the split.

Network Structure:
- The network of servers follows a binary tree structure.
- Each server is represented by an integer value, indicating its processing load.
- If a server does not exist at a particular position, it is represented as '-1' (NULL).
	
Help Balbir Singh determine if it is possible to split the network into two equal 
processing load sub-networks by removing exactly one connection.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
1 2 3 5 -1 -1 5

Sample Output-1:
----------------
true


Sample Input-2:
---------------
3 2 4 3 2 -1 7

Sample Output-2:
----------------
false

 */

 
import java.util.*;

class TreeNode {
    TreeNode right;
    TreeNode left;
    int val;

    TreeNode(int data) {
        this.val = data;
    }
}

class solution {
    public static TreeNode buildTree(int[] nodes) {
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int idx = 1;
        while (!q.isEmpty()) {
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
    public static boolean equal(TreeNode root){
        if(root == null) return true;
        int s = 0; 
        if(s == sum) return true;
        equal(root.left);
        s += root.val;
        sum -= root.val;
        equal(root.right);
        s -= root.val;
        sum +=root.val;
        return false;
    }
    static int sum = 0;
    public static int totalSum(TreeNode root){
        if( root == null){
            return 0;
        }
        sum += root.val;
        totalSum(root.left);
        totalSum(root.right);
        return sum;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        // String input = sc.nextLine();
        String input = new String("1 2 3 5 -1 -1 5");
        String nodesString[] = input.split("\\s+");
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] nodes = new int[nodesString.length];
        for (int i = 0; i < nodesString.length; i++) {
            nodes[i] = Integer.parseInt(nodesString[i]);
        }
        TreeNode root = buildTree(nodes);
        

    }
}