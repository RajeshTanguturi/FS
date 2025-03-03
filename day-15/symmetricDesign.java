/*
Imagine you are an artisan tasked with assembling a decorative mosaic from a 
collection of uniquely colored tiles. Each tile is marked with a character, 
and your challenge is to rearrange these tiles to create a design that mirrors 
itself perfectly from left to right. 
Your goal is to determine whether the available tiles can be arranged to form 
such a symmetric pattern. Print true if a symmetric design is possible,
and false otherwise.


Input format:
A string representing the characters on the tiles.

Output format:
Print a boolean value

Example 1:
input: work
output: false

Example 2:
input: ivicc
output: true

Constraints:
1 <= string.length <= 5000
tile characters are all lowercase English letters.

 */

import java.util.Scanner;

public class symmetricDesign {
    private static boolean canBeAPalindrome(String s) {
        if( s == null || s == "") return true;
        int arr[] = new int[26];
        for (char c : s.toCharArray()) {
            arr[c-'a']++;
        }
        int oddcount = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] % 2 == 1) oddcount++; 
        }
        return oddcount == 1 || oddcount == 0;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(canBeAPalindrome(s));
    }
        
}