import java.util.Stack;

public class LinkedList {
    int data;
    Node head;

    void push(int i){
        Node newN= new Node(i);
        newN.next=head;
        head=newN;
    }

    int pop(){
        int i=-1;
        if(head!=null){
            i=head.i;
            head=head.next;
        }
        return i;
    }
    class Node{
        int i;
        Node next;
        Node(int i){
            this.i=i;
        }

    }

    @Override
    public String toString() {
        Node current =head;
        while(current != null){
            System.out.print("Data"+current.i+"->");
            current=current.next;

        }
        return "print";
    }

    /**
     * Odd Even List
     * @param head
     * @return
     */
    public Node oddEvenList(Node head) {
        if(head==null)
            return null;
        Node odd= head;
        Node even=head.next;
        Node evenH=even;
        while(even != null && even.next != null){
            odd.next=odd.next.next;
            even.next=even.next.next;
            odd=odd.next;
            even=even.next;

        }

        odd.next = evenH;

        return head;
    }
    //plusOne
    public Node plusOne(Node head) {
        Node sentinel=new  Node(0);
        Node notNine= sentinel;
        sentinel.next=head;
        while(head != null){
            if(head.i !=9){
                notNine=head;
            }
            head=head.next;
        }
        notNine.i++;
        notNine=notNine.next;
        while(notNine != null){
            notNine.i = 0;
            notNine = notNine.next;
        }
        return sentinel.i != 0 ? sentinel : sentinel.next;

    }
    //rotate right array
    public static Node rotateRight(Node head, int k) {
        if(head==null)
            return null;
        int size = 1; // since we are already at head node
        Node fast=head;
        Node slow = head;

        while(fast.next!=null){
            size++;
            fast = fast.next;
        }

        for(int i=size-k%size;i>1;i--) // i>1 because we need to put slow.next at the start.
            slow = slow.next;

        // No dummy variable.
        fast.next = head;
        head = slow.next;
        slow.next = null;

        return head;
    }
    public static void main(String[] args) {
        LinkedList ll= new LinkedList();
        ll.push(2);
        ll.push(4);
        ll.push(5);
        ll.pop();
        ll.toString();
        System.out.println("");
        ll.push(7);
        ll.push(8);
        ll.toString();
        System.out.println("-");
        ll.rotateRight(ll.head,3);
        ll.toString();
        ll.toString();


        ll.pop();
        ll.toString();

    }
    //l1 = [2,4,3], l2 = [5,6,4]
    //Output: [7,0,8] Explanation: 342 + 465 = 807.
    public Node addTwoNumbers(Node l1, Node l2) {
        Node c1 = l1;
        Node c2 = l2;
        Node sentinel = new Node(0);
        Node d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.i;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.i;
                c2 = c2.next;
            }
            d.next = new Node(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new Node(1);
        return sentinel.next;
    }

    //nput: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    //Output: 7 -> 8 -> 0 -> 7    :7243+564 =7807
    public Node addTwoNumbersUsingstack(Node l1, Node l2) {
        Stack<Node> l1Stack = new Stack<>();

        while(l1 != null) {
            l1Stack.push(l1);
            l1 = l1.next;
        }

        Stack<Node> l2Stack = new Stack<>();

        while(l2 != null) {
            l2Stack.push(l2);
            l2 = l2.next;
        }

        int carry = 0;
        Node dummy = null;

        while(!l1Stack.isEmpty() || !l2Stack.isEmpty() ||  carry != 0) {
            int f = l1Stack.isEmpty() ? 0 : l1Stack.pop().i;
            int s = l2Stack.isEmpty() ? 0 : l2Stack.pop().i;

            int t = f + s + carry;

            Node newNode = new Node(t % 10);

            newNode.next = dummy;
            dummy = newNode;
            carry = t/10;

        }

        return dummy;

    }
    //input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    //Output: 7 -> 8 -> 0 -> 7    :7243+564 =7807
    public Node addTwoNumbers1(Node l1, Node l2) {
        // find the length of both lists
        int n1 = 0, n2 = 0;
        Node curr1 = l1, curr2 = l2;
        while (curr1 != null) {
            curr1 = curr1.next;
            ++n1;
        }
        while (curr2 != null) {
            curr2 = curr2.next;
            ++n2;
        }

        // parse both lists
        // and sum the corresponding positions 
        // without taking carry into account
        // 3->3->3 + 7->7 --> 3->10->10--> 10->10->3
        curr1 = l1;
        curr2 = l2;
        Node head = null;
        while (n1 > 0 && n2 > 0) {
            int i = 0;
            if (n1 >= n2) {
                i += curr1.i;
                curr1 = curr1.next;
                --n1;
            }
            if (n1 < n2) {
                i += curr2.i;
                curr2 = curr2.next;
                --n2;
            }

            // update the result: add to front
            Node curr = new Node(i);
            curr.next = head;
            head = curr;
        }

        // take the carry into account
        // to have all elements to be less than 10
        // 10->10->3 --> 0->1->4 --> 4->1->0
        curr1 = head;
        head = null;
        int carry = 0;
        while (curr1 != null) {
            // current sum and carry
            int i = (curr1.i + carry) % 10;
            carry = (curr1.i + carry) / 10;

            // update the result: add to front
            Node curr = new Node(i);
            curr.next = head;
            head = curr;

            // move to the next elements in the list
            curr1 = curr1.next;
        }

        // add the last carry
        if (carry != 0) {
            Node curr = new Node(carry);
            curr.next = head;
            head = curr;
        }

        return head;
    }

    /**
     * reverse
     */
    public boolean isPalindrome(Node head) {
        Node fast=head;
        Node slow=head;
        while(fast.next != null && fast.next.next != null){
            fast=fast.next.next;
            slow=slow.next;
        }
        System.out.println("*"+slow.i);
        if (fast != null) // odd nodes: let right half smaller
            System.out.println("f-"+fast.i);
        Node rev=reverse(slow.next);
        Node first=head;
        boolean result=true;
        while(result && rev != null){
            System.out.println("f- "+rev.i);

            if(head.i != rev.i)
                result=false;
            rev=rev.next;
            head=head.next;
        }
        return result;
    }
    Node reverse(Node head){
        Node curr=head;
        Node prev=null;

        while(curr != null){
            Node tmp=curr.next;
            curr.next =prev;
            prev=curr;
            curr=tmp;
        }
        return prev;
    }

}
