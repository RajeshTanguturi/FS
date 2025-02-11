import java.util.*;
class solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = sc.nextInt();
        int mRatings[] = new int[n];
        Set<Integer> rRate = new HashSet<>();
        for(int i = 0 ; i< n ;i++){
            mRatings[i] = sc.nextInt();
        }
        for( int i = 0 ; i< m ; i++){
            rRate.add(sc.nextInt());
        }
        Set<Integer> s = new HashSet<>();
        int currSum = 0;
        int maxSum = 0;
        int start = 0;
        for(int i = 0 ; i<n ; i++){
            if(rRate.contains(mRatings[i])){
                start=i+1;
                s.clear();
                currSum = 0;
                continue;
                
            }
            // this also works 
            // if(s.contains(mRatings[i])){
            //     while(mRatings[start]!=mRatings[i]){
            //         s.remove(mRatings[start]);
            //         currSum-=mRatings[start];
            //         start++;
            //     }
            //     s.remove(mRatings[start]);
            //     currSum-=mRatings[start];
            //     start++;
            // }
            while(s.contains(mRatings[i])){
                s.remove(mRatings[start]);
                currSum-=mRatings[start];
                start++;
            }
            
            currSum += mRatings[i];
            s.add(mRatings[i]);
            if(s.size()==k){
                maxSum  = Math.max(maxSum,currSum);
                currSum-= mRatings[start];
                s.remove(mRatings[start]);
                start++;
            }
        }
        System.out.println(maxSum == 0 ? -1 : maxSum);
        // System.out.println(-1);
    }
}