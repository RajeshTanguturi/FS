/*
Construct Tree from the given Level-order and In-order.
Compute the Depth and Sum of the Deepest nodes in the Binary tree

Input Format:
-------------
An integer N representing the number of nodes.
A space-separated list of N integers representing the in-order traversal.
A space-separated list of N integers representing the level-order traversal.

Output Format:
--------------
Print two values:
->The maximum number of levels.
->The sum of all node values at the deepest level.

Example:
-------------

Input:
11
7 8 4 2 5 9 11 10 1 6 3
1 2 3 4 5 6 7 9 8 10 11

Output:
6 11

Explanation:
The binary tree structure:
           1
         /   \
       2       3
      / \     /
     4   5   6
    /     \   
   7       9
    \       \
     8      10
            /
          11
Maximum Depth: 6
nodes at the Deepest Level (6): 11
Sum of nodes at Level 6: 11
*/
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
    public static PriorityQueue<Integer> buildPQ(int start, int end,int[] inorder,Map<Integer,Integer> levelorderMap){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            int idx1 = levelorderMap.get(a);
            int idx2 = levelorderMap.get(b);
            return idx1 - idx2;
        });
        for (int i = start; i <= end; i++) {
            pq.offer(inorder[i]);
        }
        return pq;
    } 
    public static Node buildTree( int[] inorder,Map<Integer,Integer> inorderMap,Map<Integer,Integer> levelorderMap , int sidx, int eidx,int curEle){
        Node root = new Node(curEle);
        int rootIndex = inorderMap.get(curEle);
        PriorityQueue<Integer> leftSubTreeElements = buildPQ(sidx,rootIndex-1,inorder,levelorderMap);
        PriorityQueue<Integer> rightSubTreeElements = buildPQ(rootIndex+1,eidx,inorder,levelorderMap);

        if(leftSubTreeElements.isEmpty()){
            root.left = null;
        }else{

            int leftchildEle = leftSubTreeElements.poll();
            root.left = buildTree( inorder, inorderMap,levelorderMap, sidx, rootIndex-1,leftchildEle);
        }
        if (rightSubTreeElements.isEmpty()) {
            root.right = null;
        }else{
            int rightchildEle = rightSubTreeElements.poll(); 
            root.right = buildTree( inorder, inorderMap,levelorderMap, rootIndex+1, eidx,rightchildEle);
        }
        return root;
    }
    public static void inorder (Node root){
        if( root == null) return;
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }
    public static void preorder(Node root,int[] ans,int level){
        if( root == null){
            return;
        }
        if(level > ans[0]){
            ans[0] = level;
            ans[1]=root.val;
        }
        else if( level == ans[0]){
            ans[1] +=root.val;
        }
        preorder(root.left, ans, level+1);
        preorder(root.right, ans, level+1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int inorder[] = new int[n];
        int levelorder[] = new int[n];
        Map<Integer,Integer> inorderMap = new HashMap<>();
        Map<Integer,Integer> levelorderMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            inorder[i] = sc.nextInt();
            inorderMap.put(inorder[i],i);
        }
        for (int i = 0; i < n; i++){
            levelorder[i] = sc.nextInt();
            levelorderMap.put(levelorder[i], i);
        }
        
        Node root = buildTree(inorder,inorderMap,levelorderMap,0,n-1,levelorder[0]);
        int a[] = {-1,0};

        preorder(root,a,1);


    }
}
