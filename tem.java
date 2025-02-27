public class tem {
    public static void main(String[] args) {
        String p = "999000";
        int k = 0;
        while (k< p.length() && p.charAt(k) != '(') {
            k++;
        }
        Syso
        if(k+1 < p.length()){
            System.out.println("root1: "+p.substring(0, k));
            // ans.add(Integer.parseInt(p.substring(0, k)));
        }else{
            System.out.println("root2: "+p.substring(0));
            // ans.add(Integer.parseInt(p.substring(0)));
            // continue;
        }
    }
}
