package NOWCODER;

public class ReciprocalNode {
    /*inner class*/
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val){
            this.val = val;
        }
    }

    public ListNode FindKthToTail(ListNode head, int k){
        if(head == null)
            return null;
        ListNode current = head;
        int length = 0;
        while (current.next != null){
            current = current.next;
            length++;
        }
        length++;
        int order = length - k + 1;
        if(order < 1 || order > length)
            return null;
        current = head;
        for(int i = 1; i < order; i++){
            current = current.next;
        }
        return current;
    }
}
