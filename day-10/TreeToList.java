/*
You are a gardener designing a beautiful floral pathway in a vast botanical 
garden. The garden is currently overgrown with plants, trees, and bushes 
arranged in a complex branching structure, much like a binary tree. Your task 
is to carefully prune and rearrange the plants to form a single-file walking 
path that visitors can follow effortlessly.

To accomplish this, you must fl
atten the garden’s layout into a linear sequence 
while following these rules:
    1. The garden path should maintain the same PlantNode structure, 
       where the right branch connects to the next plant in the sequence, 
       and the left branch is always trimmed (set to null).
    2. The plants in the final garden path should follow the same arrangement 
       as a pre-order traversal of the original garden layout.
Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print the list.


Sample Input:
-------------
1 2 5 3 4 -1 6

Sample Output:
--------------
1 2 3 4 5 6


Explanation:
------------
input structure:
       1
      / \
     2   5
    / \    \
   3   4    6
   
output structure:
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

 */


 import java.util.*;

 class Node{ 
     Node left;
     Node right;
     String val;
     Node(String val)
     {
         this.val = val;
         this.left = null;
         this.right = null;
     }
 }
 
 class solution{
     
     public static void preorder(Node root){
         if(root == null) return;
         System.out.print(root.val+" ");
         preorder(root.left);
         preorder(root.right);
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
     public static Node solve(Node root){
         if( root == null ) return null;
         Node r = solve(root.right);
         Node l = solve(root.left);
         // System.out.println ("root "+ root.val);
         if( l == null && r == null ){
             return root;
         }
         if( l == null && r != null ){
             return root;
         }
         if( l != null && r == null ){
             root.right = l; 
             root.left = null;
             return root;
         }
         // System.out.println("root "+ root.val + " l "+l.val+" r "+r.val);
         root.right = l;
         root.left= null;
         Node temp = l;
         while(temp.right != null ){
             // System.out.print("in while "+temp.val+"  ");
             temp = temp.right;
         }
         // System.out.println();
         temp.right = r;
         return root;
     }
     public static void main(String args[]){
         Scanner sc = new Scanner(System.in);
         String input = sc.nextLine();
         String nodes[] = input.split("\\s+");
         Node root = buildTree(nodes);
         solve(root);
         preorder(root);
     }
 }