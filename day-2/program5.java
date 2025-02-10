// Write a program to construct a binary tree from level-order input, while treating -1 
// as a placeholder for missing nodes. The program reads input, constructs the tree, 
// and provides an in-order traversal to verify correctness.

// Input Format:
// ---------------
// Space separated integers, level order data (where -1 indiactes null node).

// Output Format:
// -----------------
// Print the in-order data of the tree.


// Sample Input:
// ----------------
// 1 2 3 -1 -1 4 5

// Sample Output:
// ----------------
// 2 1 4 3 5

// Explanation:
// --------------
//     1
//    / \
//   2   3
//      / \
//     4   5


// Sample Input:
// ----------------
// 1 2 3 4 5 6 7

// Sample Output:
// ----------------
// 4 2 5 1 6 3 7

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4  5 6  7
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