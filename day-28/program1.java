/*
Imagine you're the chief engineer aboard a futuristic spaceship. The ship is 
powered by N series of fuel cells arranged in a row, where each fuel cell holds
a specific amount of energy measured in megajoules. Your job is to manage these 
fuel cells by performing two main operations:

Option 1: Calculate the total energy available in a consecutive block of fuel 
          cells between indices start and end (inclusive).
Option 2: Update the energy level with given 'newEnergy' of a specific 'index' 
          fuel cell when it's refilled.

Input Format:
-------------
Line-1: Two integers N and Q, where N is the number of fuel cells and Q is the number of operations.
Line-2: N space separated integers.
next Q lines: Three integers option, start/index and end/newEnergy.

Output Format:
--------------
An integer result, for every totalEnergy.


Example 1:
-----------
Input:
8 5
1 2 13 4 25 16 17 8
1 2 6		//totalEnerge
1 0 7		//totalEnerge
2 2 18		//recharge
2 4 17		//recharge
1 2 7		//totalEnerge

Output:
75
86
80


Example 2:
----------
Input:
16 1
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
1 0 15

Output:
136


Constraints

- 1 <= N <= 3*10^4  
- -100 <= fuelCells[i] <= 100  
- 0 <= index < fuelCells.length  
- -100 <= newEnergy <= 100  
- 0 <= start <= end < fuelCells.length  
- At most 3*10^4 calls will be made to recharge and totalEnergy.

*/

import java.util.*;

public class program1{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        fenwickTree ft = new fenwickTree(n);
        int[] fuel = new int[n+1];
        for(int i = 1 ; i <=n ; i++ ){
            fuel[i] = sc.nextInt();
            ft.update(i, fuel[i]);
        }
        List<Integer> res = new ArrayList<>();
        for( int i = 0 ; i < q ; i++){
            int option  = sc.nextInt();
            if(option == 1){
                int start = sc.nextInt();
                int end = sc.nextInt();
                res.add(ft.rangeQuery(start+1, end+1));
            }else if(option == 2 ){
                int index = sc.nextInt();
                int newEnergy = sc.nextInt();
                int delta = newEnergy - fuel[index+1];
                ft.update(index+1, delta);
                fuel[index+1] = newEnergy;
            }
        }
        for(int i = 0 ; i < res.size() ; i++){
            System.out.println(res.get(i));
        }
        
    } 
}
class fenwickTree{
    private int[] tree;
    fenwickTree(int size){
        tree = new int[size+1];
    }
    public void update(int index, int add){
        while(index < tree.length ){
            tree[index] += add ;
            index += index & -index;
        }
    }
    public int query(int index){
        int sum = 0;
        while(index > 0  ){
            sum+=tree[index];
            index -= index & -index;
        }
        return sum;
    }
    public int rangeQuery( int start, int end){
        return query(end) - query( start -1 );
    }
}