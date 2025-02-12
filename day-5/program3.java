// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)



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
public class program3 {
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
