import java.util.*;
public class temp {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(1);
        pq.add(3);
        pq.add(-1);
        pq.remove(-1);
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());

    }
}
