package practice;

import java.util.PriorityQueue;
import java.util.Stack;

public class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    /**
     *82. Remove Duplicates from Sorted List II Input: head = [1,2,3,3,4,4,5]
     * Output: [1,2,5]
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode sentinal= new ListNode(0,head);
        ListNode prev=sentinal;
        while(head != null){
            if(head.next != null && head.next.val==head.val){
                while(head.next != null && head.next.val==head.val){
                    head= head.next;
                }
                prev.next=head.next;

            } else{
                prev=head;
            }
            head=head.next;
        }
        return sentinal.next;

    }

    /**
     * reverse
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast=head;
        ListNode slow=head;
        while(fast.next != null && fast.next.next != null){
            fast=fast.next.next;
            slow=slow.next;
        }
        System.out.println("*"+slow.val);
        if (fast != null) // odd nodes: let right half smaller
            System.out.println("f-"+fast.val);
        ListNode rev=reverse(slow.next);
        ListNode first=head;
        boolean result=true;
        while(result && rev != null){
            System.out.println("f- "+rev.val);

            if(head.val != rev.val)
                result=false;
            rev=rev.next;
            head=head.next;
        }
        return result;
    }
    ListNode reverse(ListNode head){
        ListNode curr=head;
        ListNode prev=null;

        while(curr != null){
            ListNode tmp=curr.next;
            curr.next =prev;
            prev=curr;
            curr=tmp;
        }
        return prev;
    }
    // space o(n) time o(n)
    public boolean isPalindrome1(ListNode head) {
        Stack<Integer> stack= new Stack<>();
        ListNode fast=head;
        ListNode slow=head;
        while(fast != null && fast.next != null){
            fast=fast.next.next;
            stack.add(slow.val);
            slow=slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        while(slow != null){
            if(stack.pop() != slow.val)
                return false;
            slow=slow.next;
        }
        return true;
    }
    public ListNode detectCycle(ListNode head) {
        if(head ==null)
            return head;
        ListNode slow=head;
        ListNode  fast=head;
        while(fast != null && fast.next != null){
            slow=slow.next;
            fast=fast.next.next;
            if(fast==slow)
                break;
        }
        if(fast==null || fast.next == null)
            return null;
        ListNode n=head;
        while(n !=slow){
            slow=slow.next;n=n.next;
        }
        return n;
    }
    public boolean hasCycle(ListNode head) {
        if(head==null)
            return false;
        ListNode slow=head;
        ListNode fast= head.next;
        while(fast != null && fast.next != null){
            if(fast==slow)
                return true;
            fast=fast.next.next;
            slow=slow.next;
        }
        return false;

    }
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy=new ListNode(0,head);
        ListNode curr=dummy;
        ListNode prev=dummy;
        while(curr != null){
            if(curr.val==val){
                prev.next=curr.next;
            }
            else

                prev =curr;
            curr=curr.next;
        }
        return dummy.next;
    }
    /**
     *
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum=0;
        int carry=0;
        ListNode sentinal= new ListNode(0);
        ListNode curr=sentinal;
        while(l1 != null || l2 != null){
            int tmp=0;
            tmp=l1 != null ?l1.val+tmp:tmp;
            tmp=l2 != null ?l2.val+tmp:tmp;
            tmp=tmp+carry;
            carry=tmp/10;
            l1 =l1 != null ?l1.next:null;
            l2 =l2 != null ?l2.next:null;
            ListNode n= new ListNode(tmp%10);
            curr.next=n;
            curr=curr.next;
        }
        if(carry != 0)
            curr.next= new ListNode(carry);
        return sentinal.next;
    }
    /**
     *List  has at atmost k nodes, so the operation cost O(n log(K)) where n is the total number of nodes in all the lists
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null || lists.length ==0)
            return null;
        PriorityQueue<ListNode> q= new PriorityQueue<ListNode>(lists.length, (a, b)-> a.val-b.val);
        for(ListNode list:lists)
            if(list != null )
                q.offer(list);
        ListNode sentinel= new ListNode(0);
        ListNode curr= sentinel;
        while(!q.isEmpty()){
            curr.next=q.poll();
            curr=curr.next;
            ListNode tmp=curr.next;
            if(tmp != null)
                q.add(tmp);
        }
        return sentinel.next;

    }

    /**
     * Time O(Nlogk) space O(1)- \text{k}k is the number of linked lists. n is the total number of nodes in two lists.
     */
    public ListNode mergeKListsMergeAndConquer(ListNode[] lists) {
        if(lists ==null || lists.length==0)
            return null;
        int interval=1;
        while(interval<lists.length){
            for(int i=0;i<lists.length-interval;i=i+interval*2){
                lists[i]=merge(lists[i],lists[i+interval]);
            }
            interval=interval*2;
        }
        return lists[0];
    }
    private ListNode merge(ListNode n1, ListNode n2){
        ListNode sentinel = new ListNode(0);
        ListNode pre=sentinel;
        while(n1 != null && n2 != null){
            if(n1.val<n2.val){
                pre.next=n1;
                n1=n1.next;
            }else{
                pre.next=n2;
                n2=n2.next;
            }
            pre=pre.next;
        }
        pre.next= n1==null?n2:n1;
        return sentinel.next;

    }
    public ListNode reverseKGroup(ListNode head, int k) {
        //1. test weather we have more then k node left, if less then k node left we just return head
        ListNode node = head;
        int count = 0;
        while (count < k) {
            if(node == null)return head;
            node = node.next;
            count++;
        }
        // 2.reverse k node at current level
        ListNode pre = reverseKGroup(node, k); //pre node point to the the answer of sub-problem
        while (count > 0) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            count = count - 1;
        }
        return pre;
    }
    }
