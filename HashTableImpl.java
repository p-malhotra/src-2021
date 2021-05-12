import java.security.Key;

public class HashTableImpl<K,V> {
     static int max_capacity =10;

    Node[] arr ;
    HashTableImpl(int c){
        arr= new Node[c];

    }

    public void add(K k, V v){
        int i =hash(k);
        if(arr[i] == null){
            Node n = new Node(k,v);
            arr[i]=n;
            return;
        }


        Node head= arr[i];
        while(head.next !=null){
            if(head.key==k){
                head.value=v;
                return;
            }
            head=head.next;
        }
        Node newN= new Node(k,v);
        head.next=newN;
    }
    private int hash(K k){
        return k.hashCode()%arr.length;

    }
    public V get(K k){
        int index = hash(k);
        Node<K, V> current = arr[index];
        while(current != null){
            if(k.equals(current.key))
                return current.value;
            current=current.next;
        }
        return null;
    }

    public void toStrings(){
        for(int i=0;i<arr.length;i++){
            String s = arr[i] == null ? "" : arr[i].printForward();
            System.out.println(i + ": " + s);

        }


    }
    class Node<K,V>{
        K key;
        V value;
        Node next;

        Node(K k, V v){
            this.key=k;
            this.value=v;
        }

        public String printForward() {
            String data = "(" + key + "," + value + ")";
            if (next != null) {
                return data + "->" + next.printForward();
            } else {
                return data;
            }
        }
    }

    public static void main(String[] args) {
        HashTableImpl<Integer,String> ht= new HashTableImpl<>(3);
        ht.add(1,"One");
        ht.add(2,"Two");
        ht.add(3,"Three");
        ht.toStrings();
        System.out.println("*******");
        ht.add(6,"Six-6");
        ht.add(4,"Four");
        ht.add(5,"Five");
        ht.add(9,"Nine");
        ht.toStrings();


    }
}
