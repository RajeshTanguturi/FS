/*
In an Intelligence Agency, each senior officer supervises either two junior officers 
or none. The senior officer is assigned a clearance level equal to the higher clearance 
level of the two junior officers they supervise.

The clearance levels are represented as integer values in the range [1, 50], and multiple 
officers may have the same clearance level.

At the end, all officers (senior and junior) are collectively referred to as agents in the system.

You are provided with a hierarchical clearance level tree where each node represents 
an officer's clearance level. The tree structure follows these rules:
	- If a node has two children, its clearance level is the maximum of the two children's
	  clearance levels.
	- If a node has no children, it's clearance level is same as exists.
	- The value -1 indicates an empty (null) position.
Your task is to find the second highest clearance level among all agents in the agency. 
If no such level exists, return -2.

Input Format:
-------------
A single line of space separated integers, clearance levels of each individual.

Output Format:
--------------
Print an integer, second top agent based on rank.


Sample Input-1:
---------------
2 5 2 -1 -1 2 4

Sample Output-1:
----------------
4


Sample Input-2:
---------------
3 3 3 3 3

Sample Output-2:
----------------
-2

 */

 import java.util.*;


class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}


class Solution
{
    public void help(TreeNode root, int[] highest){
        if(root == null) return;
        if( highest[0] < root.val ){
            highest[1] = highest[0];
            highest[0] = root.val;
        }
        else if(highest[0] > root.val  && highest[1] < root.val){
            highest[1] = root.val;
        }
        help(root.left, highest);
        help(root.right, highest);
    }
    public int secondHighest(TreeNode root) 
    {
        // Write your code and return the second top value. 
        // (return -2 if no second highest.)
        int highest[] = new int[]{0,0};
        help(root,highest);
        return  highest[1] == 0 ? -2 : highest[1];
    }
}