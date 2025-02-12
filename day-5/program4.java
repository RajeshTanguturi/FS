// not done 
// Construct Tree from the given Level-order and In-order.
// Compute the Depth and Sum of the Deepest nodes in the Binary tree

// Input Format:
// -------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the level-order traversal.

// Output Format:
// --------------
// Print two values:
// ->The maximum number of levels.
// ->The sum of all node values at the deepest level.

// Example:
// -------------
// Input:
// 11
// 7 8 4 2 5 9 11 10 1 6 3
// 1 2 3 4 5 6 7 9 8 10 11

// Output:
// 6 11

// Explanation:
// The binary tree structure:
//            1
//          /   \
//        2       3
//       / \     /
//      4   5   6
//     /     \   
//    7       9
//     \       \
//      8      10
//             /
//           11
// Maximum Depth: 6
// nodes at the Deepest Level (6): 11
// Sum of nodes at Level 6: 11

import java.util.*;

class Node{
    Node left;
    Node right;
    int val;
    Node(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
public class program4 {
    public static Node rec(int[] preorder, int[] inorder,Map<Integer,Integer> inorderIndex , int sidx, int eidx, int preIdx[]){
        if(sidx > eidx) return null;
        Node root = new Node(preorder[preIdx[0]++]);
        int rootIndex = inorderIndex.get(root.val);
        root.left = rec(preorder, inorder, inorderIndex, sidx, rootIndex-1,preIdx);
        root.right = rec(preorder, inorder, inorderIndex, rootIndex+1, eidx,preIdx);
        return root;
    }
    public static List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        if(root==null) return res;
        que.add(root);
        int level = 1;
        while(!que.isEmpty()){
            List<Integer> sub = new ArrayList<>();
            int size = que.size();
            for(int i=0;i<size;i++){
                Node curr = que.poll();
                sub.add(curr.val);
                if(curr.left!=null) que.add(curr.left);
                if(curr.right!=null) que.add(curr.right);
            }
            if(level % 2 == 0){
                Collections.reverse(sub);
                res.add(sub);
            }else{
                res.add(sub);
            }
            level++;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int inorder[] = new int[n];
        int preorder[] = new int[n];
        int preIdx[] = new int[]{0};
        Map<Integer,Integer> inorderIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inorder[i] = sc.nextInt();
            inorderIndex.put(inorder[i],i);
        }
        for (int i = 0; i < n; i++){
            preorder[i] = sc.nextInt();
        }
        int l = sc.nextInt();
        int r = sc.nextInt();
        
        Node root = rec(preorder,inorder,inorderIndex,0,n-1,preIdx);
        List<List<Integer>> ansl = levelOrder(root);
        ArrayList<Integer> res = new ArrayList<>();
        // System.out.println(ansl);
        for( int i = l ; i<=r ; i++){
            res.addAll(ansl.get(i-1));
        }
        System.out.println(res);
    }
}
