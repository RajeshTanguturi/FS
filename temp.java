import java.util.*;
public class temp {
    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(0);
        arr.add(2);
        System.out.println(arr);
        arr.remove(Integer.valueOf(0));
        System.out.println(arr);

    }
}
