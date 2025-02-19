
/*
Balbir Singh is working with Binary Trees.
The elements of the tree is given in the level order format.
Balbir has a task to split the tree into two parts by removing only one edge
in the tree, such that the product of sums of both the splitted-trees should be maximum.

You will be given the root of the binary tree.
Your task is to help the Balbir Singh to split the binary tree as specified.
print the product value, as the product may be large, print the (product % 1000000007)
	
NOTE: 
Please do consider the node with data as '-1' as null in the given trees.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6

Sample Output-1:
----------------
110

Explanation:
------------
if you split the tree by removing edge between 1 and 4, 
then the sums of two trees are 11 and 10. So, the max product is 110.


Sample Input-2:
---------------
3 2 4 3 2 -1 6

Sample Output-2:
----------------
100


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

    public static int sum(TreeNode root) {
        if (root == null)
            return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    static int res = Integer.MIN_VALUE;

    public static void max(TreeNode root) {
        if (root == null)
            return;

        if (root.left == null && root.right == null && root != null)
            return;

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        int fullSum = root.val + leftSum + rightSum;

        res = Math.max(res, Math.max((fullSum - leftSum) * leftSum, (fullSum - rightSum) * rightSum));
    }

    static int totalSum = 0;
    static int tSum = 0;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String nodesString[] = input.split("\\s+");
        int[] nodes = new int[nodesString.length];
        for (int i = 0; i < nodesString.length; i++) {
            nodes[i] = Integer.parseInt(nodesString[i]);
        }
        TreeNode root = buildTree(nodes);
        max(root);
        System.out.print(res);

    }
}
