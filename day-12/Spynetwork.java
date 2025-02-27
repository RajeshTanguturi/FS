/*
Imagine you’re decoding a secret message that outlines the hierarchical structure 
of a covert spy network. The message is a string composed of numbers and parentheses. 
Here’s how the code works:

- The string always starts with an agent’s identification number, this is the 
  leader of the network.
- After the leader’s ID, there can be zero, one, or two segments enclosed in 
  parentheses. Each segment represents the complete structure of one subordinate 
  network.
- If two subordinate networks are present, the one enclosed in the first (leftmost) 
  pair of parentheses represents the left branch, and the second (rightmost) 
  represents the right branch.

Your mission is to reconstruct the entire spy network hierarchy based on this 
coded message.

Example 1:
Input: 4(2(3)(1))(6(5))
Output: [4, 2, 6, 3, 1, 5]

Spy network:
        4
       / \
      2   6
     / \  /
    3   1 5

Explanation:
Agent 4 is the leader.
Agent 2 (with its own subordinates 3 and 1) is the left branch.
Agent 6 (with subordinate 5) is the right branch.

Example 2:
Input: 4(2(3)(1))(6(5)(7))
Output: [4, 2, 6, 3, 1, 5, 7]

Spy network:
         4
       /   \
      2     6
     / \   / \
    3   1 5   7

Explanation:
Agent 4 leads the network.
Agent 2 with subordinates 3 and 1 forms the left branch.
Agent 6 with subordinates 5 and 7 forms the right branch.
 
 */

 import java.util.*;
public class Spynetwork {
    public static void main(String[] args) {
        // String s = "4(2(3)(1))(6(5)(7))";
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        Queue<String> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        q.add(s);
        while (!q.isEmpty()) {
            String p = q.poll();
            if( p == "") continue;
            int closingcount  = 0;
            int openingcount = 0;
            int k = 0;
            while (k< p.length() && p.charAt(k) != '(') {
                k++;
            }
            if(k+1 < p.length()){
                // System.out.println(p.substring(0, k+1));
                ans.add(Integer.parseInt(p.substring(0, k)));
            }else{
                // System.out.println(p.substring(0));
                ans.add(Integer.parseInt(p.substring(0)));
                continue;
            }
            // System.out.println("k" +k);
            int i = k+1;
            while (i< p.length() && closingcount <= openingcount) {
                if(p.charAt(i) == '(') openingcount++;
                if(p.charAt(i) == ')') closingcount++;
                i++;
            }
            // System.out.println(p.substring(k+1, i-1));
            q.add(p.substring(k+1, i-1));
            if(i == p.length()) continue;
            int j = i+1;
            closingcount  = 0;
            openingcount = 0;
            while (i< p.length() && closingcount <= openingcount) {
                if(p.charAt(i) == '(') openingcount++;
                if(p.charAt(i) == ')') closingcount++;
                i++;
            }
            // System.out.println(p.substring(j, i-1));
            q.add(p.substring(j, i-1));
        }
        // System.out.println("arr" + ans);
        System.out.println(ans);
    }

}