// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7


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
public class program1 {
    public static int postIdx;
    public static Node rec(int[] postorder, int[] inorder,Map<Integer,Integer> inorderIndex , int sidx, int eidx){
        if(sidx > eidx) return null;
        Node root = new Node(postorder[postIdx--]);
        int rootIndex = inorderIndex.get(root.val);
        // eidx = rootIndex - sidx -1;
        root.right = rec(postorder, inorder, inorderIndex, rootIndex+1, eidx);
        root.left = rec(postorder, inorder, inorderIndex, sidx, rootIndex-1);
        return root;
    }
    
    public static List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> que = new LinkedList<>();
        if(root==null) return res;
        que.add(root);
        while(!que.isEmpty()){
            List<Integer> sub = new ArrayList<>();
            int size = que.size();
            for(int i=0;i<size;i++){
                Node curr = que.poll();
                sub.add(curr.val);
                if(curr.left!=null) que.add(curr.left);
                if(curr.right!=null) que.add(curr.right);
            }
            res.add(sub);
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int inorder[] = new int[n];
        int postorder[] = new int[n];
        postIdx = n-1;
        Map<Integer,Integer> inorderIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            inorder[i] = sc.nextInt();
            inorderIndex.put(inorder[i],i);
        }
        for (int i = 0; i < n; i++){
            postorder[i] = sc.nextInt();
        }
        int q = sc.nextInt();
        int queries[][] = new int[q][2];
        for (int i = 0; i < queries.length; i++){
            queries[i] = new int[]{sc.nextInt(),sc.nextInt()};
        }
        Node root = rec(postorder,inorder,inorderIndex,0,n-1);
        List<List<Integer>> ansl = levelOrder(root);
        for(int i=0;i<queries.length;i++){
            ArrayList<Integer> al = new ArrayList<>();
            int st = queries[i][0];
            int end = queries[i][1];
            // System.out.println(st+" "+end);
            for(int j=st;j<=end;j++){
               al.addAll(ansl.get(j-1));
            }
            System.out.println(al);
        }
    }
}

