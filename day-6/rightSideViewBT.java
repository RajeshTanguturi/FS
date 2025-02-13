/*
 Balbir Singh is working with Binary Trees.
The elements of the tree are given in level-order format.

Balbir is observing the tree from the right side, meaning he 
can only see the rightmost nodes (one node per level).

You are given the root of a binary tree. Your task is to determine 
the nodes visible from the right side and return them in top-to-bottom order.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the node values visible from the right side


Sample Input-1:
---------------
1 2 3 5 -1 -1 5

Sample Output-1:
----------------
[1, 3, 5]



Sample Input-2:
---------------
3 2 4 3 2

Sample Output-2:
----------------
[3, 4, 2]

 */


import java.util.*;

class Node{
    Node left;
    Node right;
    String val;
    Node(String val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class solution{
    public static void rightSideView(Node root,List<String> ans,int depth){
        if(root== null) return;
        if(ans.size() == depth) {
            ans.add(root.val);
        }
        rightSideView(root.right,ans,depth+1);
        rightSideView(root.left,ans,depth+1);
        
    }
    
    public static Node buildTree(String[] nodes){
        Node root = new Node(nodes[0]);
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int idx = 1;
        while(!q.isEmpty()){
            Node n = q.poll();
            if(idx < nodes.length && !nodes[idx].equals("-1")){
                n.left = new Node(nodes[idx]);
                q.offer(n.left);
            }
            idx++;
            if(idx < nodes.length && !nodes[idx].equals("-1")){
                n.right = new Node(nodes[idx]);
                q.offer(n.right);
            }
            idx++;
        }
        return root;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String nodes[] = input.split("\\s+");
        Node root = buildTree(nodes);
        List<String> ans = new ArrayList<>();
        rightSideView(root,ans,0);
        System.out.println(ans);
        
    }
}