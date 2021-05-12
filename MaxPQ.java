public class MaxPQ {
    private int[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue

    public MaxPQ(int initCapacity) {
        pq =  new int[initCapacity + 1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }
    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    public int max(){
        return pq[1];

    }
    /**
     * Adds a new key to this priority queue.
     *
     * @param  x the new key to add to this priority queue
     */
    public void insert(int x) {
        pq[++n]=x;
        swim(n);
    }

    public void delMax(){
        //exchange top elem with last elem
        int tmp= pq[1];

        pq[1]=  pq[n];
        pq[n]=tmp;
        n--;
        sink(1);
        pq[n+1]=-1;
    }
     /* Helper functions to restore the heap invariant.
    ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && (pq[k/2]<pq[k])) {
            int tmp=pq[k/2];
            pq[k/2]=pq[k];
            pq[k]=tmp;
            k = k/2;
        }
    }

    public static void main(String[] args) {
        MaxPQ pq= new MaxPQ(10);
        pq.insert(6);
        pq.insert(4);
        pq.insert(10);
        pq.insert(2);
        System.out.println("dscds"+ pq.max());
        pq.delMax();
        System.out.println("dscds"+ pq.max());
        pq.delMax();
        System.out.println("dscds"+ pq.max());



    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && (pq[j]<pq[j+1])) j++;
            if ((pq[j]<pq[k]))
                break;
            int tmp=pq[k];
            pq[k]=pq[j];
            pq[j]=tmp;
            k = j;
        }
    }
}
