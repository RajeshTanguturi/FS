public class fenwickTree {
    private int[] bit; // Binary Indexed Tree array
    private int n; 
    public fenwickTree(int size){
        this.n = size;
        this.bit = new int[n+1]; // 1-based index
    }

    public void update( int idx, int val){
        while (idx <=n ) {
            bit[idx] += val;
            idx += (idx & -idx ); // move to parent index
        }
    }
    public int query( int idx){
        int sum = 0;
        while (idx > 0) {
            sum+=bit[idx];
            idx -= ( idx & -idx );
        }
        return sum;
    }
    public int rangeSum( int left, int right){
        return  query(right) - query(left - 1);
    }

    public static void main(String[] args) {
        fenwickTree ft = new fenwickTree(10);
        ft.update(1, 5);
        ft.update(2, 3);
        ft.update(3, 7);
        ft.update(4, 9);
        
        System.out.println(ft.query(3)); // Output: 15 (5 + 3 + 7)
        System.out.println(ft.rangeSum(2, 4)); // Output: 19 (3 + 7 + 9)
    }
    
}