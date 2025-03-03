public class tem {

    public static void main(String[] args) {
        String s = "nn99qbttxpse";
        int n = s.length();

        int ans = 0;
        int count = 1;
        int i = 0;
        for (i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
            } else {
                if (count > 1) {
                    ans += sum(count);
                    count = 1;
                } else {
                    ans++;
                }
            }
            System.out.println("i: " + i + " ans: " + ans + " count: " + count);
        }
        if(count > 1) {
            ans += sum(count);
            count = 1;
        }else {
            ans++;
        }

        System.out.println(ans);
    }

    public static int sum(int n) {
        return (n * (n + 1)) / 2;
    }
}