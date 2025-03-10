/*
"Emphatic Pronunciation" of a given word is where we take the word and
replicate some of the letter to emphasize their impact.

Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
We define emphatic pronunciation of a word, which is derived by replicating 
a group (or single) of letters in the original word. 

So that the replicated group is atleast 3 characters or more and 
greater than or equal to size of original group. 
For example Good -> Goood is an emphatic pronunciation,
but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
In the question you are given the "Emphatic pronunciation" word, 
you have to findout how many words can legal result in the 
"emphatic pronunciation" word.

Input Format:
-------------
Line-1 -> A String contains a single word, Emphatic Pronunciation word
Line-2 -> Space seperated word/s

Output Format:
--------------
Print an integer as your result

Sample Input-1:
---------------
goood
good goodd

Sample Output-1:
----------------
1

Sample Input-2:
---------------

3
.
Sample Output-2:
----------------
2

 */
import java.util.*;
class solution{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String emp = sc.nextLine();
        String strs[] = sc.nextLine().split(" ");
        StringBuilder empabbr = new StringBuilder();
        for( int i = 0 ; i< emp.length() ;i++){
            char c = emp.charAt(i);
            int count = 1 ;
            while( i+1< emp.length() && c == emp.charAt(i+1)){
                c = emp.charAt(++i);
                count++;
            }
            empabbr.append(emp.charAt(i));
            empabbr.append(count);
        }
        String empstr = empabbr.toString();
        int ans  = 0 ;
        for( String s : strs){
            StringBuilder abbr = new StringBuilder();
            for( int i = 0 ; i< s.length() ;i++){
                char c = s.charAt(i);
                int count = 1 ;
                while( i+1< s.length() && c == s.charAt(i+1)){
                    c = s.charAt(++i);
                    count++;
                }
                abbr.append(s.charAt(i));
                abbr.append(count);
            }
            String sabbr = abbr.toString();
            System.out.println(empstr + " "+sabbr);
            int i = 0 ;
            int j = 0 ;

            while (i + 1 < n && word.charAt(i + 1) == c) {
                i++;
                count++;
            }


            boolean flag = true;
            while( i < empstr.length() && j < sabbr.length()){
                System.out.print(i+" ");
                char c1 = empstr.charAt(i);
                char c2 = sabbr.charAt(j);
                if(c1!=c2){
                    flag = false;
                    break;
                }
                i++;
                j++;
                int n1 = empstr.charAt(i) - '0';
                int n2 = sabbr.charAt(j) - '0';
                if (n1 < 3 || n1 < n2) {
                    flag = false;
                    break;
                }
                i++;
                j++;

            }
            if(flag || (i==j)) ans++;
        }
        System.out.print(ans);
    }
}