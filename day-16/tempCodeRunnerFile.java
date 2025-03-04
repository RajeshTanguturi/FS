
 */

import java.util.Scanner;

public class cityRegions {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nodes = sc.nextInt(), edges = sc.nextInt();
        int parent[] = new int[nodes];
        for( int i = 0 ; i< nodes ; i++) parent[i] = i;
        DSU dsu = new DSU(parent);
        for( int i = 0 ; i< edges ; i++){