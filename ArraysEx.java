import java.util.*;

public class ArraysEx {
    // find all subsets
    //[1,2,3] op [[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
    List<List<Integer>> subsets(int[] num){
        List<List<Integer>> response= new ArrayList<>();
        //  Arrays.sort(num);
        backtracking( response,  new ArrayList<>(), num, 0);

        return response;

    }

    void backtracking(List<List<Integer>> resp, List<Integer> tmp,int[] num, int idx) {
        resp.add(new ArrayList(tmp));
        for (int i = idx; i < num.length; i++) {
            if (i > idx && num[i - 1] == num[i]) continue;

            tmp.add(num[i]);
            backtracking(resp, tmp, num, i + 1);
            tmp.remove(tmp.size() - 1);

        }
    }

    //permutation for
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>>  list= new ArrayList();
        backtracking1(list,new ArrayList(),nums,0 );
        return list;

    }
    void backtracking1(List<List<Integer>> res, List<Integer> tmp, int[] nums, int s){
        if(tmp.size()==nums.length){
            res.add(new ArrayList(tmp));
        }else{
            for(int i=s;i<nums.length;i++){
                if(tmp.contains(nums[i])) continue; // element already exists, skip

                tmp.add(nums[i]);
                backtracking(res,tmp,nums,s);
                tmp.remove(tmp.size()-1);
            }
        }
    }
//permutation with duplicate elements
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList();
        backtracking(res, new ArrayList(),nums, new boolean[nums.length] );
        return res;



    }
    void backtracking(List<List<Integer>> res, List<Integer> tmp,int[] nums, boolean[] used){
        if(tmp.size()==nums.length){
            res.add(new ArrayList(tmp));
        }else{
            for( int i=0;i<nums.length;i++){
                if((i>0 && nums[i]==nums[i-1]) && !used[i - 1] || used[i]) continue;
                tmp.add(nums[i]);
                used[i]=true;
                backtracking(res, tmp,nums,  used);
                tmp.remove(tmp.size()-1);
                used[i]=false;
            }

        }
    }
    // find sum/target by choosing no in array unlimited no of times
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> list= new ArrayList();
        Arrays.sort(candidates);
        combinationSum(list, candidates,  target,new ArrayList<Integer>(),0) ;
        return list;
    }
    void combinationSum( List<List<Integer>> list,int[] candidates, int target,  List<Integer> tmp,int st) {
        if(target<0)
            return;
        else if(target==0)
            list.add(new ArrayList(tmp));
        else {for(int i=st;i<candidates.length;i++){
            tmp.add(candidates[i]);
            combinationSum(list, candidates, target-candidates[i],tmp ,i);
            tmp.remove(tmp.size()-1);

        }
        }
    }

    //combination sum for no duplicate
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList();
        Arrays.sort(candidates);
        combinationSum2(candidates,  target, list,new ArrayList(), 0 );
        return list;

    }

    void combinationSum2(int[] candidates, int target,List<List<Integer>> list,List<Integer> tmp, int idx ){
        if(target<0)
            return;
        else if(target==0)
            list.add(new ArrayList(tmp));
        else{
            for( int i=idx;i<candidates.length;i++){
                if(i > idx && candidates[i] == candidates[i-1]) continue;
                tmp.add(candidates[i]);
                combinationSum2(candidates,  target-candidates[i], list,tmp, i+1);
                tmp.remove(tmp.size()-1);
            }
        }

    }
    // combination sum for su n and elements in subset k using only 1-9
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combination(ans, new ArrayList<Integer>(), k, 1, n);
        return ans;
    }

    private void combination(List<List<Integer>> ans, List<Integer> comb, int k,  int start, int n) {
        if (comb.size() == k && n == 0) {
            List<Integer> li = new ArrayList<Integer>(comb);
            ans.add(li);
            return;
        }
        for (int i = start; i <= 9; i++) {
            comb.add(i);
            combination(ans, comb, k, i + 1, n - i);
            comb.remove(comb.size() - 1);
        }
    }
    //search target/num/element in rotated array
    public int search(int[] nums, int target) {
        int start=0;
        int end=nums.length-1;
        while(start<=end){
            int mid = start + (end - start) / 2;
            System.out.println("mid "+mid);

            if(nums[mid]==target)
                return mid;
            else if ( nums[mid ]>=nums[start]){
                if(target>=nums[start] && target< nums[mid]) end=mid-1;
                else
                    start=mid+1;
            }else{
                if( target<= nums[end] && target>nums[mid])
                    start=mid+1;
                else
                    end=mid-1;

            }
        }
        return -1;
    }
    // is array circular
    public boolean circularArrayLoop(int[] nums) {
        if(nums == null || nums.length==1)
            return false;
        for(int i=0;i<nums.length;i++){
            if (nums[i] == 0) continue;
            int slow = i, fast = getIndex(slow,nums);

            while(nums[fast]*nums[i]>0 && nums[i]*nums[getIndex(i,nums)] >0){
                if(slow==fast){
                    if (slow == getIndex(slow, nums))
                        break;
                    return true;
                }
                slow= getIndex(slow,nums);
                fast=getIndex(getIndex(fast,nums),nums);

            }
            slow = i;
            int sgn = nums[i];
            while (sgn * nums[slow] > 0) {
                int tmp = getIndex( slow,nums);
                nums[slow] = 0;
                slow = tmp;
            }
        }
        return false;
    }

    int getIndex(int i,int[] nums){
        int n= nums.length;
        return i+nums[i] >= 0? (i+nums[i])%n : n+((i+nums[i])%n);
    }

    //1481. Least Number of Unique Integers after K Removals

    /**
     * Count the occurrences of each number using HashMap;
     * Using TreeMap to count each occurrence;
     * Poll out currently least frequent elemnets, and check if reaching k, deduct the correponding unique count remaining
     */
    static public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int a : arr)
            count.put(a, 1 + count.getOrDefault(a, 0));
        int remaining = count.size();
        TreeMap<Integer, Integer> occurrenceCount = new TreeMap<>();
        for (int v : count.values())
            occurrenceCount.put(v, 1 + occurrenceCount.getOrDefault(v, 0));
        while (k > 0) {
            Map.Entry<Integer, Integer> entry = occurrenceCount.pollFirstEntry();
            if (k - entry.getKey() * entry.getValue() >= 0) {
                k -= entry.getKey() * entry.getValue();
                remaining -= entry.getValue();
            }else {
                return remaining - k / entry.getKey();
            }
        }
        return remaining;
    }
// input array [13, 7, 6, 12} ans : {-1,12,12,-1}
    void nextGreaterElement(int[] arr){
        //create a stack
        Stack<Integer> stk = new Stack();
        int[] res= new int[arr.length];

        for(int i=arr.length-1;i>=0;i--){

            res[i]=stk.isEmpty()?-1:stk.peek();
            stk.push(arr[i]);

        }

    }
    public static void main(String[] args){
        findLeastNumOfUniqueInts(new int[]{4,3,1,1,3,3,2},3);
    }
}
