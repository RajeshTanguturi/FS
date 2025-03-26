/*
Imagine you're an adventurer with a mystical treasure map. This map is a grid of 
ancient runes, where each cell holds a single character. Legend has a 
powerful incantation—represented as a string—is hidden within these runes. 
To unlock the treasure, you must verify if the incantation exists on the map.

The incantation is formed by linking runes that are directly next to each other 
either horizontally or vertically. Each rune on the map can only be used once in
the incantation.

Your Task:  
Given an m x n grid representing the treasure map and a string representing the 
incantation, return true if the incantation can be traced on the map; 
otherwise, return false.


Example 1:
----------
Input:  
3 4
ABCD
SFCS
ADEE
ABCCED

Output:
ABCCED can be traced

Explanation (check hint)
Treasure Map Grid:  
[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCCED" exists in map


Example 2:
----------
Input:
3 4
ABCE
SFCS
ADEE
ABCB

Output: 
ABCB cannot be traced

Explanation:
Treasure Map Grid:  

[
  ["A", "B", "C", "E"],
  ["S", "F", "C", "S"],
  ["A", "D", "E", "E"]
]

Incantation: "ABCB" does not exist in map


Constraints:

- m == the number of rows in the grid  
- n == the number of columns in the grid  
- 1 <= m, n <= 6  
- 1 <= incantation length <= 15  
- The grid and incantation consist only of uppercase and lowercase English letters.

 */

import java.util.*;



public class program3 {
    public static final int  dirs[][] = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
    
    public static boolean bt(char[][] grid, String incantation, int row , int col, int i, boolean[][] visited){

        if(i == incantation.length()) return true;

        if(row >= grid.length  || row < 0 || col >= grid[0].length || col < 0 || visited[row][col]  ) return false;

        if(grid[row][col] != incantation.charAt(i)) return false;

        visited[row][col] = true;
        //this can also done without visited array by changing the grid[row][col] = '#' this indicates visited

        for( int dir[] : dirs ){
            int currRow = dir[0]+row;
            int currCol = dir[1]+col;
            if(bt(grid, incantation, currRow, currCol, i+1,visited)) return true;
        }

        visited[row][col] = false;

        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        char [][]grid = new char[m][n];
        for( int  i = 0 ; i < m ; i++){
            String row = sc.next();
            for( int j = 0 ; j < n; j++ ){
                grid[i][j] = row.charAt(j);
            }
        }
        sc.nextLine();
        String incantation = sc.next();
        for( int  i = 0 ; i < m ; i++){
            for( int j = 0 ; j < n; j++ ){
                // System.out.println(grid[i][j]);
                if(grid[i][j] == incantation.charAt(0)){
                    if(bt(grid, incantation, i, j, 0,new boolean[m][n])){
                        System.out.println(true);
                        return;
                    }
                }
            }
        }
        System.out.println(false);
    }
}