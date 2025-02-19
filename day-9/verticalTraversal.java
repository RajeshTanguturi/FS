/*
Imagine you are a librarian organizing books on vertical shelves in a grand 
library. The books are currently scattered across a tree-like structure, where 
each book (node) has a position determined by its shelf number (column) and row 
number (level).

Your task is to arrange the books on shelves so that:
1. Books are placed column by column from left to right.
2. Within the same column, books are arranged from top to bottom (i.e., by row).
3. If multiple books belong to the same shelf and row, they should be arranged 
from left to right, just as they appear in the original scattered arrangement.

Sample Input:
-------------
3 9 20 -1 -1 15 7

Sample Output:
--------------
[[9],[3,15],[20],[7]]

Explanation:
------------
         3
       /   \
      9     20
          /    \
         15     7

Shelf 1: [9]
Shelf 2: [3, 15]
Shelf 3: [20]
Shelf 4: [7]


Sample Input-2:
---------------
3 9 8 4 0 1 7

Sample Output-2:
----------------
[[4],[9],[3,0,1],[8],[7]]

Explanation:
------------

          3
       /     \
      9       8
    /   \   /   \
   4     0 1     7

Shelf 1: [4]
Shelf 2: [9]
Shelf 3: [3, 0, 1]
Shelf 4: [8]
Shelf 5: [7]

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