/*
There are N cities, and M routes[], each route is a path between two cities.
routes[i] = [city1, city2], there is a travel route between city1 and city2.
Each city is numbered from 0 to N-1.
 
There are one or more Regions formed among N cities. 
A Region is formed in such way that you can travel between any two cities 
in the region that are connected directly and indirectly.
 
Your task is to findout the number of regions formed between N cities. 
 
Input Format:
-------------
Line-1: Two space separated integers N and M, number of cities and routes
Next M lines: Two space separated integers city1, city2.
 
Output Format:
--------------
Print an integer, number of regions formed.
 
 
Sample Input-1:
---------------
5 4
0 1
0 2
1 2
3 4
 
Sample Output-1:
----------------
2
 
 
Sample Input-2:
---------------
5 6
0 1
0 2
2 3
1 2
1 4
2 4
 
Sample Output-2:
----------------
1
 
Note: Look HINT for explanation.

 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class cityRegions {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt(), edges = sc.nextInt();
        int parent[] = new int[nodes];
        for( int i = 0 ; i< nodes ; i++) parent[i] = i;
        DSU dsu = new DSU(parent);
        for( int i = 0 ; i< edges ; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            dsu.union(x, y);
        }
        HashSet<Integer> set = new HashSet<>();
        for( int i = 0 ;i < nodes ;i++){
            set.add(parent[i]);
        }
        System.out.println(set.size());
    }
}
class DSU{
    int rank[];
    int parent[];
    DSU(int parent[],int rank[]){
        this.rank  = rank;
        this.parent = parent;
    }
    DSU(int parent[]){
        this.parent = parent;
    }
    public int find(int x){
        if( parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if( rootX == rootY){
            return;
        }
        if( rootX < rootY){
            parent[y] = rootX;
        }else{
            parent[x] = rootY;
        }
    }
}