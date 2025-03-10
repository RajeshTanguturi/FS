/*
AlphaCipher is a string formed from another string by rearranging its letters

You are given a string S.
Your task is to check, can any one of the AlphaCipher is a palindrome or not.

Input Format:
-------------
A string S

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
carrace

Sample Output-1:
----------------
true


Sample Input-2:
---------------
code

Sample Output-2:
----------------
false

 */

 import java.util.*;
class solution{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int x = 0;
        for( char c : s.toCharArray()){
            x = x^(c-'a');
        }
        if(s.length() % 2 == 0 && x != 0 ) System.out.print(false);
        else if( s.length() %2 == 1 && x == 0) System.out.print(false);
        else System.out.print(true);
    }
}