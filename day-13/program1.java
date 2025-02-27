/*
Imagine you are navigating a maze where each path you take has a section with a 
code. The maze is structured as a series of interconnected rooms, 
represented as a tree structure. Each room in the maze has a code (integral value)
associated with it, and you are trying to check if a given sequence of code 
matches a valid path from the entrance to an exit. 

You are provide with the maze structure, where you have to build the maze and then,
you are provided with a series of space seperated digits, representing a journey 
starting from the entrance and passing through the rooms along the way. 
The task is to verify whether the path corresponding to this array of codes 
exists in the maze.

Example 1:
Input:
0 1 0 0 1 0 -1 -1 1 0 0         //maze structure
0 1 0 1                         //path to verify

Output: true

Explanation:
               0
             /   \
            1     0
           / \    /
          0   1  0
           \  / \
            1 0  0

The given path 0 → 1 → 0 → 1 is a valid path in the maze. 
Other valid sequences in the maze include 0 → 1 → 1 → 0 and 0 → 0 → 0.


Example 2:
Input:
1 2 3
1 2 3

output: false

Explanation:
The proposed path 1 → 2 → 3 does not exist in the maze, 
so it cannot be a valid path.

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
class solution{
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
    public static boolean  bt(TreeNode root,List<Integer> curr,int[] paths){
        if( root == null ) return false;
        curr.add(root.data);
        if(root.left == null && root.right == null){
            if(curr.size() != paths.length ){ 
                curr.remove(curr.size()-1);
                return false;
            }
            else {
                boolean flag = true;
                for( int i = 0; i< paths.length ; i++){
                    if(curr.get(i) != paths[i]){
                        flag = false;
                        break;
                    }
                }
                curr.remove(curr.size()-1);
                return flag;
            }
        }
        boolean left = bt(root.left,curr,paths);
        boolean right = bt(root.right, curr,paths);
        curr.remove(curr.size() - 1 );
        return left || right;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String path = sc.nextLine();
        String pathString[] = path.split("\\s+");
        String nodesString[] = input.split("\\s+");
        int[] paths = new int[pathString.length];
        int[] nodes = new int[nodesString.length];
        for (int i = 0; i < nodesString.length; i++) {
            nodes[i] = Integer.parseInt(nodesString[i]);
        }
        for (int i = 0; i < pathString.length; i++) {
            paths[i] = Integer.parseInt(pathString[i]);
        }
        TreeNode root = buildTree(nodes);
        // List<List<Integer>> ans = new ArrayList<>();
        System.out.println(bt(root,new ArrayList<>(),paths));
        // System.out.println(
        // System.out.print(path);
    

}

}