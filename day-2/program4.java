// You are developing an application for a garden management system where each tree 
// in the garden is represented as a binary tree structure. The system needs to 
// allow users to plant new trees in a systematic way, ensuring that each tree is 
// filled level by level.

// A gardener wants to:
//  - Plant trees based on user input.
//  - Ensure trees grow in a balanced way by filling nodes level by level.
//  - Inspect the garden layout by performing an in-order traversal, which helps 
//    analyze the natural arrangement of trees.

// Your task is to implement a program that:
//     - Accepts a list of N tree species (as integers).
//     - Builds a binary tree using level-order insertion.
//     - Displays the in-order traversal of the tree.

// Input Format:
// -------------
// - An integer N representing the number of tree plants.
// - A space-separated list of N integers representing tree species.

// Output Format:
// --------------
// A list of integers, in-order traversal of tree.


// Sample Input:
// -------------
// 7
// 1 2 3 4 5 6 7

// Sample Output:
// --------------
// 4 2 5 1 6 3 7


// Explanation:
// ------------
// The tree looks like this:

//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// The in order is : 4 2 5 1 6 3 7


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
public class program5 {
    public static void inorder(Node root){
        if(root == null) return;

        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String nodes[] = input.split("\\s+");
        // System.out.println(Arrays.toString(nodes));
        // System.out.println(!nodes[0].equals("-1"));
        Node root = buildTree(nodes);
        inorder(root);
        
        sc.close();
    }
}
