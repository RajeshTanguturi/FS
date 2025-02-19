/*
 Bubloo is working with computer networks, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) 
is uniquely identified by an integer value.

Bubloo has been assigned an important task: find the shortest communication 
path (in terms of network hops) between two specific servers in the network.

Network Structure:
------------------
The network of servers follows a binary tree topology.
Each server (node) has a unique identifier (integer).
If a server does not exist at a certain position, it is represented as '-1' (NULL).

Given the root of the network tree, and two specific server IDs (E1 & E2), 
determine the minimum number of network hops (edges) required to 
communicate between these two servers.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
4 8

Sample Output-1:
----------------
4

Explanation:
------------
The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


Sample Input-2:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
6 6

Sample Output-2:
----------------
0

Explanation:
------------
No edegs between 6 and 6.

 */


 
import java.util.*;

class TreeNode {
    TreeNode right;
    TreeNode left;
    int data;

    TreeNode(int data) {
        this.data = data;
    }
}

class solution {

    public static boolean paths(TreeNode root, List<Integer> ans, int target) {
        if (root == null)
            return false;
        ans.add(root.data);
        if (root.data == target) {
            return true;
        }
        if (paths(root.left, ans, target) || paths(root.right, ans, target)) {
            return true;
        }
        // System.out.println(root.data+ " "+ ans);
        ans.remove(ans.size() - 1);
        return false;

    }

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
        String input = sc.nextLine();
        String nodesString[] = input.split("\\s+");
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] nodes = new int[nodesString.length];
        for (int i = 0; i < nodesString.length; i++) {
            nodes[i] = Integer.parseInt(nodesString[i]);
        }
        TreeNode root = buildTree(nodes);
        List<Integer> ans1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();
        paths(root, ans1, n);
        paths(root, ans2, m);
        List<Integer> common = new ArrayList<>(ans1);
        common.retainAll(ans2);
        ans1.removeAll(common);
        ans2.removeAll(common);
        System.out.println(ans1.size() + ans2.size());
    }
}