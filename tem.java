import java.util.*;

class Node{
    Node next;
    int val;
    Node(int val){
        this.val = val;
        this.next = null;
    }
}
class tem{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String ips[] = input.split("\\s+");
        System.out.println(Arrays.toString(ips));
        int arr[] = new int[ips.length];
        for( int i = 0 ; i<ips.length ; i++){
            arr[i] = Integer.parseInt(ips[i]);
        }
        Node head = new Node(arr[0]);
        Node prev = head;
        for( int i = 1 ; i<arr.length ; i++){
            Node curr = new Node(arr[i]);
            prev.next = curr;
            prev = curr;
        }
        System.out.println(
        isPalindrome(head));
        // Node temp = head;
        // while(temp!=null){
        //     System.out.println(temp.val);
        //     temp = temp.next;
        // }
        // head = reverse(head);
        // System.err.println("reverse");
        // while(head!=null){
        //     System.out.println(head.val);
        //     head = head.next;
        // }
    }
    public static void printll(Node head){
        while(head!=null){
            System.out.print(head.val +"->");
            head = head.next;
        }
    }
    public static  boolean isPalindrome(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast !=null && fast.next != null ) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if(fast!= null) slow = slow.next;
        printll(slow);
        slow = reverse(slow);
        System.out.println();
        printll(slow);

        while(slow!=null){
            if (slow.val != head.val) {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        return true;

    }
    public static Node reverse(Node head) {
        Node prev= null;
        Node curr = head;
        while (curr!=null) {
            Node nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
        return prev;
    }
}