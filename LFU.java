import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFU {
    HashMap<Integer, Integer> vals;
    HashMap<Integer, Integer> counts;
    HashMap<Integer, LinkedHashSet<Integer>> countToLRUKeys;
    int cap;
    int min = -1;
    public LFU(int capacity) {
        cap = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        countToLRUKeys = new HashMap<>();
        countToLRUKeys.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if(!vals.containsKey(key))
            return -1;
        int count = counts.get(key);
        counts.put(key, count+1);
        countToLRUKeys.get(count).remove(key); // remove key from current count (since we will inc count)
        if(count==min && countToLRUKeys.get(count).size()==0) // nothing in the current min bucket
            min++;
        if(!countToLRUKeys.containsKey(count+1))
            countToLRUKeys.put(count+1, new LinkedHashSet<>());
        countToLRUKeys.get(count+1).add(key);
        return vals.get(key);
    }

    public void set(int key, int value) {
        if(cap<=0)
            return;
        if(vals.containsKey(key)) {
            vals.put(key, value);
            get(key); // update key's count
            return;
        }
        if(vals.size() >= cap) {  // evict LRU from this min count bucket
            int evit = countToLRUKeys.get(min).iterator().next();
            countToLRUKeys.get(min).remove(evit);
            vals.remove(evit);
        }
        vals.put(key, value); // adding new key and value
        counts.put(key, 1); // adding new key and count
        min = 1;
        countToLRUKeys.get(1).add(key);
    }
}
