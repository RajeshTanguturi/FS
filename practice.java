import java.util.*;

public class practice {

    public static void main(String[] args) {
        List<StringBuilder> ans;
        int n = 4;
        int l = 10;
        int count = 0 ;
        boolean increasing = true;
        for( int i = 0 ; i < l ; i++ ){
            System.out.print(count + " ");
            if(increasing){
                count++;
            }else{
                count--;
            }
            if( count == n - 1){
                increasing = false;
            }
            if( count  == 0 ){
                increasing = true;
            }
        }
    }
}