public class MinStack {
    Node top;
    private class Node{
        int t;
        Node next;
        int min;
        Node(int elem){
            this.t=elem;
            this.min=min;
        }
    }

    void push(int t){
        Node newN=new Node(t);

        if(top==null){
            newN.t=t;
            newN.min=t;
            top=newN;
            return;
        }
        newN.min =Math.min(top.min,t);
        newN.next=top;
        top=newN;

    }
    int pop(){
        if(top ==null){
            System.out.println("Stack is empty");
            return -1;
        }
        int item=top.t;
        top=top.next;
        return item;
    }
    public String toString(){
        StringBuffer sb= new StringBuffer();
        Node current =top;
        while(current != null){
            sb.append("Item: "+current.t+ " min: "+current.min+" : ");
            current=current.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MinStack min= new MinStack();
        min.push(4);
        min.push(9);
        System.out.println("Stack :"+min.toString());
        min.push(2);
        System.out.println("Stack :"+min.toString());
min.pop();
        System.out.println("Stack :"+min.toString());

    }
}
