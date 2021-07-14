import practice.All;

import java.util.*;
import java.util.LinkedList;

public class All1 {
    /**
     * check valid no
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        boolean isNum=false;
        boolean isE=false;
        boolean isPoint=false;
        for( int i=0;i<s.length();i++){
            if(s.charAt(i)>='0' && s.charAt(i)<='9')
                isNum=true;
            else if(s.charAt(i)=='.'){
                if(isPoint  || isE)
                    return false;
                isPoint=true;
            }else if(s.charAt(i)=='e' || s.charAt(i)=='E'){
                if(!isNum ||isE)
                    return false;
                isE=true;
                isNum=false;
            }else if(s.charAt(i)=='+' ||s.charAt(i)=='-'){
                if(i !=0  && s.charAt(i-1) != 'e' )
                    return false;

            }else
                return false;
        }
        return isNum;



    }
    public boolean isNumber1(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen)
                    return false;
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen)
                    return false;
                numberSeen = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e')
                    return false;
            } else
                return false;
        }
        return numberSeen;
    }

    // find missing no. in array 1...n
    //if we initialize an integer to nn and XOR it with every index and value, we will be left with the missing number.
    public int missingNumber(int[] nums) {
        int sum=0;
        for(int n:nums){
            sum+=n;
        }
        return  Math.abs((sum - nums.length*(nums.length+1)/2));

    }
    //bits find unique nos all other nos are twice
   /* he diff part in the bitmasking solution is not needed.
    Any way to distinguish between the two integers will do, so an easier
    way to compare is to use the fact that the numbers are distinct, hence
    one will be larger than the other:*/
    public List<Integer> singleNumber(int[] nums) {
        int bitmask=0;
        for(int n:nums){
            bitmask ^=n;
            System.out.println(Integer.toBinaryString(bitmask));

        }
        List l= new ArrayList();
        /* this is second way to find difference and then do 2nd loop, n^bitmask !=0;

        int comp=~bitmask;
        System.out.println("-> "+Integer.toBinaryString(comp));
        System.out.println("----> "+Integer.toBinaryString(-bitmask));

        int diff=bitmask & (-bitmask);
        System.out.println("xdiffxxxx-> "+Integer.toBinaryString(diff));*/

        int x = 0;
        for(int n:nums){
            System.out.println(n+"*");
            if(n> (n^bitmask) )
            {
                x ^= n;
                System.out.println(n+" -xxxxx-> "+Integer.toBinaryString(x));

            }
        }
        l.add(x);
        l.add(bitmask^x);
        return l;

    }

        /**
         * 560. Subarray Sum Equals K : return the total number of continuous subarrays whose sum equals to k.
         */
        public int subarraySum(int[] nums, int k) {
            int sum = 0;
            int count = 0;
            Map<Integer, Integer> hm = new HashMap<>();
            hm.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                count += hm.getOrDefault(sum - k, 0);
                hm.put(sum , hm.getOrDefault(sum , 0) + 1);
            }
            return count;
        }

        /**
         * 523. Continuous Subarray :Sum continuous subarray of size at least two whose elements sum up to a multiple of k
         */
        public boolean checkSubarraySum(int[] nums, int k) {
            Map<Integer, Integer> hm = new HashMap<>();//keep no and frequecies
            int sum = 0;
            hm.put(0, -1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (k != 0)
                    sum = sum % k;
                if (hm.containsKey(sum)) {
                    if (i - hm.get(sum) > 1)
                        return true;
                } else
                    hm.put(sum, i);

            }
            return false;

        }

        /**
         * 76. Minimum Window Substring
         */
        public String minWindow(String s, String t) {
            if (s == null || s.length() == 0 || t == null || t.length() == 0 || t.length() > s.length())
                return "";
            int[] ch = new int[128];
            for (char c : t.toCharArray())
                ch[c]++;
            int st = 0;
            int end = 0;
            int min = Integer.MAX_VALUE;
            int minS = 0;
            int counter = t.length();
            while (end < s.length()) {
                final char c = s.charAt(end);
                if (ch[c] > 0) counter--;
                ch[c]--;
                end++;
                while (counter == 0) {
                    if (min > end - st) {
                        min = end - st;
                        minS = st;

                    }
                    final char c1 = s.charAt(st);
                    ch[c1]++;
                    if (ch[c1] > 0) counter++;
                    st++;
                }
            }
            System.out.println("min " + min + "+ " + minS + ": " + s);

            return min == Integer.MAX_VALUE ? "" : s.substring(minS, minS + min);
        }

        public String minWindowHM(String s, String t) {
            if (s == null || s.length() == 0 || t == null || t.length() == 0)
                return "";
            Map<Character, Integer> hm = new HashMap<>();
            for (char c : t.toCharArray()) {
                hm.put(c, hm.getOrDefault(c, 0) + 1);
            }
            int counter = 0;
            int min = Integer.MAX_VALUE;
            int start = 0;
            int end = 0;
            int minStart = 0;
            while (end < s.length()) {
                char c = s.charAt(end);
                hm.put(c, hm.getOrDefault(c, 0) - 1);
                if (hm.getOrDefault(c, 0) >= 0)
                    counter++;
                end++;
                while (counter == t.length()) {
                    if (min > end - start) {
                        min = end - start;
                        minStart = start;
                    }
                    char f = s.charAt(start);
                    hm.put(f, hm.getOrDefault(f, 0) + 1);
                    if (hm.get(f) > 0)
                        counter--;
                    start++;
                }
            }
            System.out.println("min " + min + "+ " + minStart + ": " + s);
            return min == Integer.MAX_VALUE ? "" : s.substring(minStart, min + minStart);

        }

        /**
         * 209. Minimum Size Subarray Sum with target only positive nums
         */
        public int minSubArrayLen(int target, int[] nums) {
            int left = 0;
            int right = 0;
            int min = Integer.MAX_VALUE;
            int sum = 0;
            while (right < nums.length) {
                sum += nums[right];
                while (sum >= target) {
                    min = Math.min(min, right - left + 1);
                    sum = sum - nums[left++];
                }

                right++;
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }

        /**
         * 325. Maximum Size Subarray Sum Equals k, -ve and +ve number
         */
        public int maxSubArrayLen(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>(); // num and index
            int max = 0;
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (sum == k) max = i + 1;
                else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
                if (!map.containsKey(sum)) map.put(sum, i);
            }
            return max;

        }

        /**
         * merge k arrays : Time: O(n * log(k))
         * Space: O(k)
         */
        public static List<Integer> mergeSortedArrays(List<List<Integer>> arrays) {
            // Write your code here.
            List<Integer> res = new ArrayList<>();
            PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.num, b.num));
            for (int i = 0; i < arrays.size(); i++)
                pq.offer(new Item(i, 0, arrays.get(i).get(0)));
            while (!pq.isEmpty()) {
                Item min = pq.poll();
                res.add(min.num);
                if (min.numIdx >= arrays.get(min.arrIndx).size() - 1) continue;
                pq.offer(new Item(min.arrIndx, min.numIdx + 1, arrays.get(min.arrIndx).get(min.numIdx + 1)));
            }
            return res;
        }

        static class Item {
            int arrIndx;
            int numIdx;
            int num;

            Item(int arrIndx, int numIdx, int num) {
                this.arrIndx = arrIndx;
                this.numIdx = numIdx;
                this.num = num;
            }
        }
        /**
         *Combination sum, 3 sum
         * * **/

        public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
            // Write your code here.
            List<Integer[]> list = new ArrayList<>();
            Arrays.sort(array);
            helper(array,  targetSum, new ArrayList<>(),  list,  0);
            return list;
        }
        static void helper(int[]arr, int target,List<Integer> tmp, List<Integer[]> list, int idx){
            if(target<0)
                return;
            if(tmp.size()>3)
                return;
            if(target==0 && tmp.size()==3){
                //	Collections.sort(tmp);
                Integer[] r=tmp.toArray(new Integer[3]);
                list.add(r);
                return;
            }
            for(int i=idx;i<arr.length;i++){
                tmp.add(arr[i]);
                helper(arr,  target-arr[i], tmp,  list,  i+1);
                tmp.remove(tmp.size()-1);
            }
        }

        public int threeSumSmaller(int[] nums, int target) {
            Arrays.sort(nums);
            int sum = 0;
            for (int i = 0; i < nums.length - 2; i++) {
                sum += twoSumSmaller(nums, i + 1, target - nums[i]);
            }
            return sum;
        }

        private int twoSumSmaller(int[] nums, int startIndex, int target) {
            int sum = 0;
            int left = startIndex;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target) {
                    sum += right - left;
                    left++;
                } else {
                    right--;
                }
            }
            return sum;
        }
        public int threeSum1Closest(int[] nums, int target) {
            int diff=Integer.MAX_VALUE;
            Arrays.sort(nums);
            for(int i=0;i<nums.length;i++){
                int lo=i+1;
                int j=nums.length-1;
                while(lo<j){
                    int s=nums[i]+nums[lo]+nums[j];
                    if(Math.abs(target-s)< Math.abs(diff)){
                        diff=target-s;
                        if(s<target) lo++;
                        else if (s>target) j--;
                    }
                }
            }
            return target-diff;
        }

        /**
         * O(n) space: o(1)
         */
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> ans= new ArrayList<>();
            for(int n:nums)
                nums[Math.abs(n)-1]*= -1;
            for(int n:nums){
                if(nums[Math.abs(n)-1] >0){
                    ans.add(Math.abs(n));
                    nums[Math.abs(n) - 1] *= -1;// so we've already taken no and don't add duplicate in ans
                }
            }

            return ans;
        }
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> res= new ArrayList<>();
            for(int i=0;i<nums.length;i++){
                if(nums[Math.abs(nums[i])-1] >0)
                    nums[Math.abs(nums[i])-1] *=-1;
            }
            for (int i = 1; i <= nums.length; i++) {

                if (nums[i - 1] > 0) {
                    res.add(i);
                }
            }
            return res;
        }
        public int findLengthOfShortestSubarray1(int[] arr) {
            int left = 0;
            while(left + 1 < arr.length && arr[left] <= arr[left+1]) left++;
            if(left == arr.length - 1) return 0;

            int right = arr.length - 1;
            while(right > left && arr[right-1] <= arr[right]) right--;
            int result = Math.min(arr.length - left - 1, right);

            int i = 0;
            int j = right;
            while(i <= left && j < arr.length) {
                if(arr[j] >= arr[i]) {
                    result = Math.min(result, j - i - 1);
                    i++;
                }else {
                    j++;
                }
            }
            return result;
        }
        /**
         *
         */
        public void wiggleSort(int[] nums) {
            for (int i = 0; i < nums.length - 1; i++) {
                if (((i % 2 == 0) && nums[i] > nums[i + 1])
                        || ((i % 2 == 1) && nums[i] < nums[i + 1])) {
                    //  swap(nums, i, i + 1); //todo
                }
            }
        }

        public int firstMissingPositive(int[] nums) {
            int n = nums.length;

            // 1. mark numbers (num < 0) and (num > n) with a special marker number (n+1)
            // (we can ignore those because if all number are > n then we'll simply return 1)
            for (int i = 0; i < n; i++) {
                if (nums[i] <= 0 || nums[i] > n) {
                    nums[i] = n + 1;
                }
            }
            // note: all number in the array are now positive, and on the range 1..n+1

            // 2. mark each cell appearing in the array, by converting the index for that number to negative
            for (int i = 0; i < n; i++) {
                int num = Math.abs(nums[i]);
                if (num > n) {
                    continue;
                }
                num--; // -1 for zero index based array (so the number 1 will be at pos 0)
                if (nums[num] > 0) { // prevents double negative operations
                    nums[num] = -1 * nums[num];
                }
            }

            // 3. find the first cell which isn't negative (doesn't appear in the array)
            for (int i = 0; i < n; i++) {
                if (nums[i] >= 0) {
                    return i + 1;
                }
            }

            // 4. no positive numbers were found, which means the array contains all numbers 1..n
            return n + 1;
        }
        /**
         *
         */
        public int longestRepeatingSubstringDP(String S) {
            int l = S.length();
            int[][] dp = new int[l+1][l+1];
            int res = 0;
            for (int i = 1; i <= l; i++) {
                for (int j = i + 1; j <= l; j++) {
                    if (S.charAt(i - 1) == S.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        res = Math.max(dp[i][j],res);
                    }
                }
            }
            return res;
        }

        /**
         * 1044. Longest Duplicate Substring : Rabin- Karp's Algorithm find pattern in o(1) time
         * take pattern hash it and roll it over for
         * he Rabin-Karp algorithm is better when searching for a large text that is finding multiple pattern matches, like detecting plagiarism.
         * And Boyer-Moore is better when the pattern is relatively large with a moderately sized alphabet and with a large vocabulary. And it does not work well with binary strings or very short patterns.
         * Meanwhile, KMP is good for searching inside a smaller alphabet, like in bioinformatics or searching in binary strings. And it does not run fast if the alphabet increases.
         * Time complexity : O(NlogN). O(logN) for the binary search and O(N) for Rabin-Karp algorithm. Space complexity : O(N) for hashset
         */
        public String longestDupSubstring(String S) {
            String ans = "";

            int left = 1;
            int right = S.length()-1;

            while(left <= right){
                int m = left + (right - left)/2;

                String dup = getDup(m,S);

                if(dup != null){
                    ans = dup;
                    left = m+1;
                }else{
                    right = m-1;
                }
            }

            return ans;
        }

        private String getDup(int size, String s){ //getDup() : returns a duplicate string for a given window size

            long H = hash(s.substring(0,size));

            HashSet<Long> set = new HashSet();
            set.add(H);

            long pow = 1;
            for(int i=1;i<size;i++)
                pow = (pow * 31);

            int n = s.length();
            for(int i=size;i < n;i++){
                H = nextHash(pow, H, s.charAt(i-size), s.charAt(i));
                if(!set.add(H)){
                    return s.substring(i-size+1, i+1);
                }
            }

            return null;
        }

        private long hash(String s){
            long h = 0;
            long a = 1;

            int n = s.length();
            for(int k=n;k>=1;k--){
                char ch = s.charAt(k-1);
                h += (ch - 'a' + 1) * a;
                a = (a*31);
            }

            return h;
        }

        private long nextHash(long pow,long hash,char left,char right){
            return (hash - (left - 'a' + 1) * pow) * 31 + (right - 'a' + 1);
        }
        public int compress(char[] chars) {
            int indexAns = 0, index = 0;
            while(index < chars.length){
                char currentChar = chars[index];
                int count = 0;
                while(index < chars.length && chars[index] == currentChar){
                    index++;
                    count++;
                }
                chars[indexAns++] = currentChar;
                if(count != 1)
                    for(char c : Integer.toString(count).toCharArray())
                        chars[indexAns++] = c;
            }
            return indexAns;
        }

        /**
         * rotate array, reverse main, reverse less than k, rev after k  time O(n),spce o(1)
         */
        public void rotate(int[] nums, int k) {
            k %= nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }
        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
        public int gcd(int a, int b){
            if(b>a)
                gcd(b,a);
            if(b == 0) return a;
            return gcd(b, a % b);
        }
        public static int[] slidingWindowMax(final int[] in, final int w) {
            final int[] max_left = new int[in.length];
            final int[] max_right = new int[in.length];

            max_left[0] = in[0];
            max_right[in.length - 1] = in[in.length - 1];

            for (int i = 1; i < in.length; i++) {
                max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);

                final int j = in.length - i - 1;
                max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
            }

            final int[] sliding_max = new int[in.length - w + 1];
            for (int i = 0, j = 0; i + w <= in.length; i++) {
                sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
            }

            return sliding_max;
        }
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int n: nums){
                map.put(n, map.getOrDefault(n,0)+1);
            }

            PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                    new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
            for(Map.Entry<Integer,Integer> entry: map.entrySet()){
                maxHeap.add(entry);
            }

            List<Integer> res = new ArrayList<>();
            while(res.size()<k){
                Map.Entry<Integer, Integer> entry = maxHeap.poll();
                res.add(entry.getKey());
            }
            return res;
        }
        /**********************
         * nearest coprimes in node array array elements between 1<n[i]<50 Input: nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
         * Output: [-1,0,0,1]
         Declared Global to make method signature simple and readable.
         But can be passed in method signature to get rid of Global variables
         */
        public void dfs(int[] nums, List<Integer>[] tree, int depth, int node, boolean[] visited, int[] ans, Map<Integer,int[]> map, boolean[][] coPrimeMemo) {
            if(visited[node]) return;
            visited[node] = true;
            int ancestor = -1;
            int d = Integer.MAX_VALUE;
         /*
            Find a number (candidate) that is Co-prime with current node value and also check
            if we have that candidate in out 'ancestor' map.
            If found, try to minimize the depth between them.
        */
            for(int i = 1; i < 51; i++) {
                if(coPrimeMemo[nums[node]][i] && map.containsKey(i)) {
                    if(depth - map.get(i)[0] <= d) {
                        d = depth - map.get(i)[0];
                        ancestor = map.get(i)[1];
                    }
                }
            }
            ans[node] = ancestor; // Store the closest ancestor found, If not, by default -1
            // for backtracking purpose, store old entry so that we can restore it back
            // when we come again after visiting its children.
            // If No such entry in our ancestor, we store [-1, -1] (distinguishing value)
            int[] alreadyExist = (map.containsKey(nums[node])) ? map.get(nums[node]) :  new int[]{-1,-1};
            map.put(nums[node],new int[]{depth,node});
            for(int child : tree[node]) { // Visit its child just like we do in DFS. Just skip the parent
                if(visited[child]) continue;
                dfs(nums, tree, depth+1, child, visited, ans, map, coPrimeMemo);
            }
            if(alreadyExist[0] != -1) map.put(nums[node], alreadyExist);
            else map.remove(nums[node]);

            return;
        }
// minimum height tree, v is no of nodes, then edges= v-1 time: O(V), space O(v)

        public int[] getCoprimes(int[] nums, int[][] edges) {
            boolean[][] coPrimeMemo = new boolean[51][51]; // since  nums[i] <50
            for(int i = 1; i < 51; i++) {
                for(int j = 1; j < 51; j++) {
                    if(gcd(i,j) == 1) {
                        coPrimeMemo[i][j] = true;
                        coPrimeMemo[j][i] = true;
                    }
                }
            }
            int n = nums.length;
            /* Create a graph for easy access of children */
            List<Integer>[] tree = new LinkedList[n];

            for(int i =0 ; i < tree.length; i++) tree[i] = new LinkedList<>();
            for(int edge[] : edges) {
                tree[edge[0]].add(edge[1]);
                tree[edge[1]].add(edge[0]);
            }

            int[] ans = new int[n];
            Arrays.fill(ans, -1);
            ans[0] = -1;
            Map<Integer,int[]> map = new HashMap<>(); // Each entry will represent: <value, [depth, nodeIndex]>

            boolean[] visited = new boolean[n];
            dfs(nums, tree, 0, 0, visited, ans, map, coPrimeMemo);
            return ans;
        }

        public int lengthLongestPath(String input) {
            int maxLen = 0 ,count =0 , level =1 ;
            boolean isFile = false;
            HashMap<Integer , Integer > levelLength  = new HashMap<>(); // level, length

            int i =0 ;
            while (i < input.length()){
                while(input.charAt(i) == '\t'){
                    ++level;
                    ++i;
                }
                while( i < input.length() && input.charAt(i) != '\n'){

                    if(input.charAt(i)== '.')
                        isFile = true;
                    ++count;
                    ++i;
                }
                if(isFile) {
                    levelLength.put(level , levelLength.getOrDefault(level - 1, 0) + count);
                    maxLen = Math.max(maxLen, levelLength.get(level ));
                }
                else
                    levelLength.put(level , levelLength.getOrDefault(level - 1, 0 ) + count + 1);
                count = 0 ;
                level = 1;
                isFile = false;
                ++i;
            }
            return  maxLen;
        }
        public int lengthLongestPathStack(String input) { //he root dir dir is in level 1, we push a initial value 0 into stack first indicating a not existing file with length 0
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            stack.push(0);
            int result = 0;
            for (String s : input.split("\n")) {
                int level = s.lastIndexOf('\t') + 1;
                int len = s.length() - level;
                while (stack.size() > level + 1) {  // done to bring it to subdirectory level
                    stack.pop();
                }
                if (s.contains(".")) {
                    result = Math.max(result, stack.peek() + len);
                } else {
                    stack.push(stack.peek() + len + 1);
                }
            }
            return result;
        }

        public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
            // Write your code here.
            List<Integer[]> res= new ArrayList<>();
            helper( array,  res,  new ArrayList<>(), targetSum, 0);
            return res;
        }
        public static void helper(int[] arr, List<Integer[]> res, List<Integer> tmp, int sum,int idx){
            if(sum < 0)
                return;
            if(sum ==0 && tmp.size()==4){
                res.add(tmp.toArray(new Integer[4]));
                return;
            }
            for(int i=idx;i<arr.length;i++){
                tmp.add(arr[i]);
                helper( arr,  res,  tmp,  sum-arr[i], i+1);
                tmp.remove(tmp.size()-1);

            }
        }
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> map= new HashMap<>();
            for(String word: words)
                map.put(word, map.getOrDefault(word,0)+1);
            PriorityQueue<Map.Entry<String,Integer>> q= new PriorityQueue<>((a,b)->( a.getValue()==b.getValue()?a.getKey().compareTo(b.getKey()) : b.getValue()-a.getValue()));

            for(Map.Entry<String,Integer> entry: map.entrySet()){
                q.offer(entry);
            }

            List<String> res= new ArrayList();
            while(res.size()<k)
                res.add(q.poll().getKey());
            return res;

        }
        public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
            Map<Integer, List<Integer>> map = new HashMap<>();  // Build the hierarchical tree
            Deque<int[]> queue = new LinkedList<>();
            int result = Integer.MIN_VALUE;

            for(int i = 0; i < manager.length; i++) {
                if(!map.containsKey(manager[i]))
                    map.put(manager[i], new ArrayList<>());

                map.get(manager[i]).add(i);     // Map of manager and its subordinates given by the index i
            }

            queue.offer(new int[]{headID, 0});      // head of organizer and corresponding informing time.

            while(!queue.isEmpty()) {
                int[] temp = queue.poll();
                int managerAtGivenLevel = temp[0];
                int cumulativeTime = temp[1];

                result = Math.max(result, cumulativeTime);

                if(map.containsKey(managerAtGivenLevel)) {      // if the manager has subordinates
                    List<Integer> subordinates = map.get(managerAtGivenLevel);  // get the list of subordinates

                    for(int i : subordinates) {
                        queue.offer(new int[]{i, informTime[managerAtGivenLevel] + cumulativeTime});    // add the subordinates as manager and the time taken to inform each of these subordinates
                    }
                }
            }
            return result;
        }
        static void spiralFill(int sRow,int sCol, int eRow, int eCol, int[][] arr, List<Integer> res){
            if(sRow >eRow || sCol >eCol)
                return;
            for(int i=sCol;i<=eCol;i++)
                res.add(arr[sRow][i]);
            for(int i=sRow+1;i<=eRow;i++)
                res.add(arr[i][eCol]);
            for(int i=eCol-1;i>=sCol;i--){
                if(sRow==eRow) break;
                res.add(arr[eRow][i]);
            }
            for(int i=eRow-1;i>=sRow+1;i--){
                if(sCol==eCol) break;
                res.add(arr[i][sCol]);
            }
            spiralFill(sRow+1, sCol+1, eRow-1, eCol-1,  arr,  res);
        }
        public static String shortenPath(String path) {
            String[] dirs=path.split("/");
            Stack<String> stack= new Stack<>();
            for(String dir: dirs){
                if(dir.equals(".") || dir.isEmpty()){
                    continue;
                }else if(dir.equals("..")){

                    if(stack.isEmpty() || stack.peek() == "..")
                        stack.push("..");
                    else
                        stack.pop();


                }else
                    stack.push(dir);
            }
            StringBuilder sb = new StringBuilder();
            while(!stack.isEmpty()){
                sb.insert(0,"/"+stack.pop());
            }

            //sb = sb.charAt(0) =='/' ? sb : sb.substring(1);
            return sb.length()>0 ? sb.toString() : "";
        }

        static int M[][] = new int[][]
                { { 1, 1, 0, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 1, 0, 0, 1, 1 },
                        { 0, 0, 0, 0, 0 },
                        { 1, 0, 1, 0, 1 } };
        static int countIslands(int[][]M){
            boolean[][] visited= new boolean[M.length][M[0].length];
            int count =0;

            for(int i=0;i<M.length;i++)
                for(int j=0;j<M[0].length;j++){
                    if(M[i][j]==1 && !visited[i][j]){
                        util(M, visited, i, j);
                        count ++;
                    }
                }

            return count;

        }
        static void util(int[][]M,boolean[][] visit,int i,int j){
            if(i<0 ||j <0 ||i >=M.length || j >=M[0].length || M[i][j] == 0 || visit[i][j]){
                return;
            }
            visit[i][j]=true;
            int[][] dir= new int[][]{{1,0},{-1,0},{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
            for(int d =0;d< dir.length;d++){
                int x =dir[d][0];
                int y=dir[d][1];
                util(M,visit,i+dir[d][0],j+dir[d][1]);

            }

        }

        class  NestedInteger{
            int i;
            List<NestedInteger> list;
            int getInteger(){
                return i;
            }
            boolean isInteger(){
                return i>=0;
            }
            List<NestedInteger> getList(){
                return list;
            }

        }
        public int depthSum(List<NestedInteger> nestedList) {
            int depth =1;
            int total=0;
            Queue<NestedInteger> q= new LinkedList<>();
            q.addAll(nestedList);
            while(!q.isEmpty()){
                int size=q.size();
                for(int i=0;i<size;i++){
                    NestedInteger ni= q.poll();
                    if(ni.isInteger())
                        total+=ni.getInteger()*depth;
                    else
                        q.addAll(ni.getList());
                }

                depth++;

            }
            return total;
        }
        public int depthSumInverse(List<NestedInteger> nestedList) {
            int unweighted = 0, weighted = 0;
            while (!nestedList.isEmpty()) {
                List<NestedInteger> nextLevel = new ArrayList<>();
                for (NestedInteger ni : nestedList) {
                    if (ni.isInteger())
                        unweighted += ni.getInteger();
                    else
                        nextLevel.addAll(ni.getList());
                }
                weighted += unweighted;
                nestedList = nextLevel;
            }
            return weighted;
        }
        public static void main(String[] args) {
            System.out.println(shortenPath("../../foo/bar/baz"));
            fourNumberSum(new int[]{7, 6, 4, -1, 1, 2},16);
            System.out.println("islands "+countIslands(M));

        }
        int [] nums;

        public void swap(int a, int b) {
            int tmp = this.nums[a];
            this.nums[a] = this.nums[b];
            this.nums[b] = tmp;
        }


        public int partition(int left, int right, int pivot_index) {
            int pivot = this.nums[pivot_index];
            // 1. move pivot to end
            swap(pivot_index, right);
            int store_index = left;

            // 2. move all smaller elements to the left
            for (int i = left; i <= right; i++) {
                if (this.nums[i] < pivot) {
                    swap(store_index, i);
                    store_index++;
                }
            }

            // 3. move pivot to its final place
            swap(store_index, right);

            return store_index;
        }

        public int quickselect(int left, int right, int k_smallest) {
    /*
    Returns the k-th/kth smallest element of list within left..right.
    */

            if (left == right) // If the list contains only one element,
                return this.nums[left];  // return that element

            // select a random pivot_index
            Random random_num = new Random();
            int pivot_index = left + random_num.nextInt(right - left);

            pivot_index = partition(left, right, pivot_index);

            // the pivot is on (N - k)th smallest position
            if (k_smallest == pivot_index)
                return this.nums[k_smallest];
                // go left side
            else if (k_smallest < pivot_index)
                return quickselect(left, pivot_index - 1, k_smallest);
            // go right side
            return quickselect(pivot_index + 1, right, k_smallest);
        }

        public int findKthLargest(int[] nums, int k) {
            this.nums = nums;
            int size = nums.length;
            // kth largest is (N - k)th smallest
            return quickselect(0, size - 1, size - k);
        }

        /**
         * time:   O(nLog(k)) space O(k)
         */
        public int findKthLargestPQ(int[] nums, int k) {
            // init heap 'the smallest element first'
            PriorityQueue<Integer> heap =
                    new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

            // keep k largest elements in the heap
            for (int n: nums) {
                heap.add(n);
                if (heap.size() > k)
                    heap.poll();
            }

            // output
            return heap.poll();
        }
     /**
     * leet code  1730  shortest path find food, find path bfs/ min moves
     */
    public int getFood(char[][] grid) {
        int x=0;
        int y=0;
        Queue<int[]> q = new LinkedList<>();
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]=='*'){
                    q.add(new int[]{i,j,1});
                    break;
                }
            }
        int[][] dirs = {{0,-1},{0,1},{1,0},{-1,0}};
        while(!q.isEmpty()){
            int[] a=q.poll();
            for(int i=0;i<dirs.length;i++){
                x=dirs[i][0]+a[0];
                y=dirs[i][1]+a[1];

                if(x>=0 && y>=0 && x<grid.length && y< grid[0].length && grid[x][y] !='X'){

                    if(grid[x][y]=='#'){
                        return a[2];
                    }
                    if(grid[x][y]=='O'){
                        grid[x][y]='X';
                        q.add(new int[]{x,y,a[2]+1});
                    }

                }
            }
        }

        return -1;
    }
    /**
     * space is O(n), time O(logn)
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set= new HashSet();
        for(int n:nums)
            set.add(n);
        int max=0;
        for(int m:nums)
            if(!set.contains(m-1)){
                int n=m+1;
                while(set.contains(n))
                    n++;
                max=Math.max(max,n-m);
            }
        return max;


    }
    
        //pruning
        if (res == grid.length - 1 +  grid[0].length - 1) return;

        //find result
        if (x == grid.length - 1 && y == grid[0].length - 1) {
            res = Math.min(res, step);
            return;
        }

        //dfs
        if (x >= 0 && y >= 0 && k >= 0 && x < grid.length && y < grid[0].length && grid[x][y] != 2) {
            //obstacles
            if (grid[x][y] == 1) {
                grid[x][y] = 2;
                dfs(grid, x + 1, y, k - 1, step + 1);
                dfs(grid, x, y + 1, k - 1, step + 1);
                dfs(grid, x - 1, y, k - 1, step + 1);
                dfs(grid, x, y - 1, k - 1, step + 1);
                //backtrack
                grid[x][y] = 1;

                //not obstacles
            } else if(grid[x][y] == 0) {
                grid[x][y] = 2;
                dfs( grid, x + 1, y, k, step + 1);
                dfs( grid, x, y + 1, k, step + 1);
                dfs( grid, x - 1, y, k, step + 1);
                dfs( grid, x, y - 1, k, step + 1);
                //backtrack
                grid[x][y] = 0;

            }
        }
    }
    /** leetcode 1293 shortest pth
     *
     */
    public int shortestPath(int[][] grid, int k) {
        boolean[][][] state= new boolean[grid.length][grid[0].length][k+1];
        Queue<int[]> q= new LinkedList();
        q.offer(new int[]{0,0,0});// add starting pos in q
        int[][] dirs={{0,-1},{0,1},{1,0},{-1,0}};
        int steps=0;
        while(!q.isEmpty()){
            int size=q.size();//check for curr size and save else we will not get right size as we add more elems
            for(int i=0;i<size;i++){
                int[] a=q.poll();

                if(a[0]==grid.length-1 && a[1]==grid[0].length-1){// if dest.{
                    System.out.print("Here");
                    return steps;
                }
                for(int j=0;j<dirs.length;j++){
                    int x=a[0]+dirs[j][0];
                    int y=a[1]+dirs[j][1];
                    int blockerCount=a[2];
                    if(x>=0 &&x<grid.length && y>=0 && y<grid[0].length){//if corordinates are out of grid
                        if(grid[x][y]==1 )//check for obstacle and add count, we can't go more than that
                            blockerCount++;
                        if(blockerCount<=k && !state[x][y][blockerCount]){
                            System.out.println("blocker "+blockerCount+"- i,y "+x+"y"+y);
                            state[x][y][blockerCount] = true;// mark visited
                            q.offer(new int[]{x, y, blockerCount});// add to queue
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }
    /**
     * minumu moves/ shortest path
     */
    public static int minMoves(int n, int startRow, int startCol, int endRow, int endCol) {
        // Write your code here
        // x and y direction, where a knight can move
        int dx[] = { -2, -1, 1, 2, -2, -1, 1, 2 };
        int dy[] = { -1, -2, -2, -1, 1, 2, 2, 1 };

        // queue for storing states of knight in board
        Queue<Box> q = new LinkedList<>();

        // push starting position of knight with 0 distance
        q.add(new Box(startRow, startCol, 0));
        Box box;
        int x, y;
        boolean visit[][] = new boolean[n + 1][n + 1];

        // make all cell unvisited
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                visit[i][j] = false;

        // visit starting state
        visit[startRow][startCol] = true;

        // loop untill we have one element in queue
        while (!q.isEmpty()) {
            box = q.poll();
            q.remove(0);

            // if current cell is equal to target cell,
            // return its distance
            if (box.x_axis == endRow && box.y_axis == endCol)
                return box.distance;

            // loop for all reachable states
            for (int i = 0; i < 8; i++) {
                x = box.x_axis + dx[i];
                y = box.x_axis + dy[i];

                // If reachable state is not yet visited and
                // inside board, push that state into queue
                if(!(x >= 0 && x < n && y >= 0 && y < n)) continue;
                if (  !visit[x][y]) {
                    visit[x][y] = true;
                    q.add(new Box(x, y, box.distance + 1));
                }
            }
        }
        return Integer.MAX_VALUE;

    }
    static class Box {
        int x_axis, y_axis;
        int distance;

        public Box(int x, int y, int dis)
        {
            this.x_axis = x;
            this.x_axis = y;
            this.distance = dis;
        }
    }
    public int minKnightMoves(int x, int y) {
        int[][] dx={{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}};
        boolean[][] visited=new boolean[x+1][y+1];// 1is visited
        Queue<int[]> q= new LinkedList<>();
        for (int i = 1; i < x; i++)
            for (int j = 1; j < y; j++)
                visited[i][j] = false;
        q.offer(new int[]{0,0,0});
        visited[0][0]=true;
        while(!q.isEmpty()){
            int[] curr=q.poll();
            System.out.println("Poll : "+Arrays.toString(curr));

            if(curr[0]==x && curr[1]==y)
                return curr[2];
            for(int i=0;i<dx.length;i++){
                int cx=curr[0]+dx[i][0];
                int cy=curr[1]+dx[i][1];
                if(!(cx>=0 && cx<=x && cy >= 0 && cy<=y )) continue;
                if(! visited[cx][cy]){
                    q.offer(new int[]{cx,cy,curr[2]+1});
                    System.out.println(cx+" : "+cy);
                    visited[cx][cy]=true;
                }

            }
        }
        return Integer.MAX_VALUE;
  }
    /**
     * leetcode 162
     */
    public int findPeakElement(int[] nums) {
        int left=0;
        int right=nums.length-1;
        int index=0;
        while(left<right){
            int mid=(left+right)/2;
            if(nums[mid]<nums[mid+1])
                left=mid+1;
            else right =mid;
        }
        return left;
    }
    /**
     * leet code coung substring Input: "aaa"-op -6, time complexity =space =O(n^2)
     */
    public int countSubstrings(String s) {
        boolean[][] dp= new boolean[s.length()+1][s.length()+1];
        int count=0;
        for(int i=0;i<s.length();i++){
            dp[i][i]=true;
            count++;
        }
        System.out.println("c- "+count);
        for(int i=0;i<s.length()-1;i++){
            dp[i][i+1]=(s.charAt(i)==s.charAt(i+1));
            if(dp[i][i+1])
                count++;
        }
        System.out.println("c- "+count);

        for(int len =3;len<=s.length();len++)
            for(int i=0, j=i+len-1;j<s.length();i++,j++){
                dp[i][j]=(s.charAt(i)==s.charAt(j)&& dp[i+1][j-1]);
                count += (dp[i][j]==true)?1:0;
            }
        return count;
    }
    /** level1 not full regex
     * 44 wild card matching
     *
     */
        public boolean isMatch(String s, String p) {
            boolean[][] dp= new boolean[s.length()+1][p.length()+1];

            dp[0][0]=true;
            if(p.charAt(0)== '*')
                dp[0][1]=true;

            for(int i=1;i<=s.length();i++){
                for(int j=1;j<=p.length();j++){
                    if(s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='.'){
                        dp[i][j]=dp[i-1][j-1];
                    }else if(p.charAt(j-1)=='*'){
                        dp[i][j]=dp[i-1][j] ||dp[i][j-1];
                    }

                }
            }
            return dp[s.length()][p.length()];
        }

    static String alphabetBoardPath(String target) {
        String[] board = new String []{"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};
        StringBuilder sb = new StringBuilder();
        if(target== null)
            return "";
        String ans=null;
        int i=0;
        int xs=0;
        int xy=0;
        int x=0;
        int y=0;
        while(i<target.length()){
            char c= target.charAt(i);
            for(int p=0;p<board.length;p++){
                System.out.println("board[p].indexOf(c)"+board[p].indexOf(c));
                if(board[p].indexOf(c) != -1){
                    xs=p;
                    xy=board[p].indexOf(c);
                    break;
                }
            }

            if(xs >x){
                while(xs >x){
                    x++;
                    sb.append('D');

                }
            }else{
                while(xs <x){
                    x--;
                    sb.append('U');
                }}
            if(xy>y){
                while(xy >y){
                    y++;
                    sb.append('R');
                }
            }else{
                while(xy<y){
                    y--;
                    sb.append('L');
                }
            }
            sb.append('!');
            i++;

        }
        return sb.toString();

    }
    public String addBinary(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        BigInteger zero = new BigInteger("0", 2);
        BigInteger carry, answer;
        while (y.compareTo(zero) != 0) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        return x.toString(2);
    }
    public int getSum(int a, int b) {
        int x=Math.abs(a);
        int y=Math.abs(b);
        if (x < y) return getSum(b, a);
        int sign= a <0?-1:1;
        int sum=0;
        int carry=0;
        if(a*b>=0){
            while(y != 0){
                sum=x^y;
                carry=(x&y)<<1;
                x=sum;
                y=carry;
            }
        }
        else{
            while(y != 0){
                sum=x^y;
                carry=((~x)&y)<<1;
                x=sum;
                y=carry;

            }}
        return x*sign;

    }
    // O(1)  O(1)
    public int getSum1(int a, int b) {
        while(b !=0){
            int sum =a^b;
            int carry=a&b<<1;
            a=sum;
            b=carry;
        }
        return a;

    }
    public String reverseWords(String s) {
        int left=0;
        int right=s.length()-1;
        while(s.charAt(left)== ' ')
            left++;
        while(s.charAt(right)== ' ')
            right--;
        Deque<String> q= new ArrayDeque();
        StringBuilder words= new StringBuilder();
        while(left<=right){
            char c=s.charAt(left);
            if(words.length() !=0 && c ==' '){
                q.offerFirst(words.toString());
                words= new StringBuilder();
            }else if(c != ' '){
                words.append(c);
            }
            left++;
        }
        q.offerFirst(words.toString());

        return String.join(" ",q);

    }
    public int compress(char[] chars) {
        int idx=0;
        int pos=0;
        while(idx<chars.length){
            int count=0;
            char cuu=chars[idx];
            while(idx<chars.length && chars[idx]==cuu){
                count++;
                idx++;
            }
            chars[pos++]=cuu;
            if(count !=1)
                for(char c: Integer.toString(count).toCharArray()){
                    chars[pos++]=c;
                }
        }
        return pos;
    }
    public String minRemoveToMakeValid(String s) {
        int open=0;
        int close=0;
        StringBuffer sb= new StringBuffer();
        for(int i=0;i<s.length();i++)
            if(s.charAt(i)==')')
                close++;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)==')'){
                if(open>0){
                    sb.append(s.charAt(i));
                    open--;
                }
                else
                    close--;
            }else if(s.charAt(i)=='('){
                open++;

                if(close>0){
                    sb.append(s.charAt(i));
                    close--;
                }

            } else
                sb.append(s.charAt(i));
        }
        return sb.toString();



    }

    /**
     * leet code 359. Logger Rate Limiter
     * @param timestamp
     * @param message
     * @return
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        HashMap<String, Integer> hm = new HashMap<>();

        for(Map.Entry<String,Integer> entry:(hm.entrySet())){
            if(timestamp -entry.getValue() >=10)
                hm.remove(entry.getKey());
        }
        if(hm.containsKey(message) && (timestamp- hm.get(message)<10))
            return false;
        hm.put(message,timestamp);
        return true;


    }
    //power fo 2 n&n-1 ==0 or x&(-x)==x  hint (-x is ~x+1)
    public boolean isPowerOfTwo(int n) {
        if(n<=0) return false;
        return (n&(n-1))==0;

    }
    //how many 1 in bits
    public int hammingWeight(int n) {
        int sum=0;
        while(n!=0){
            sum++;
            n&=(n-1) ;
        }
        return sum;

    }
    //seen once or thrice
    public int singleNumber(int[] nums) {
        int seen_once=0;
        int seen_twice=0;
        for( int n:nums){
            seen_once= ~seen_twice &(seen_once^n);
            seen_twice= ~seen_once &(seen_twice^n);
        }
        return seen_once;
    }
    public int[] countBits(int num) {
        int n[]= new int[num+1];
        for ( int i=1;i<=num;i++){
            int nos=0;
            int nums=i;
            while(nums!=0){
                nos++;
                nums&=(nums-1);
            }
            n[i]=nos;
        }
        return n;
    }
    public int[] countBits1(int num) {
        int n[]= new int[num+1];
        int n1[]= new int[num+1];
        for ( int i=1;i<=num;i++){
            int nos=0;
            int nums=i;

            n[i]=n[i& (i-1)]+1;
            n1[i]=n1[i-1]+1;
        }
        System.out.print(Arrays.toString(n1));
        return n;
    }
    //reverse digits in a bit number
    public int reverseBits(int n) {
        if (n == 0) return 0;

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) result++;
            n >>= 1;
        }
        return result;
    }
    public int maxAreaOfIsland(int[][] grid) {
        boolean[][] seen = new boolean[grid.length][grid[0].length];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        int ans = 0;
        for (int r0 = 0; r0 < grid.length; r0++) {
            for (int c0 = 0; c0 < grid[0].length; c0++) {
                if (grid[r0][c0] == 1 && !seen[r0][c0]) {
                    int shape = 0;
                    Stack<int[]> stack = new Stack();
                    stack.push(new int[]{r0, c0});
                    seen[r0][c0] = true;
                    while (!stack.empty()) {
                        int[] node = stack.pop();
                        int r = node[0], c = node[1];
                        shape++;
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if (0 <= nr && nr < grid.length &&
                                    0 <= nc && nc < grid[0].length &&
                                    grid[nr][nc] == 1 && !seen[nr][nc]) {
                                stack.push(new int[]{nr, nc});
                                seen[nr][nc] = true;
                            }
                        }
                    }
                    ans = Math.max(ans, shape);
                }
            }
        }
        return ans;
    }
    /** leetcode 46 permutation
     *Input: nums = [1,2,3]  Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public List<List<Integer>> permuteSubSetI(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        permuteSubSetI(result, new ArrayList<Integer>(), nums);
        return result;

    }
    void permuteSubSetI(List<List<Integer>> list, List<Integer> tmp, int[]  nums){
        if(tmp.size()== nums.length)
            list.add(new ArrayList<>(tmp));
        else{
            for(int i = 0; i < nums.length; i++){
                if(!tmp.contains(nums[i])) {
                    tmp.add(nums[i]);
                    permuteSubSetI(list,tmp, nums);
                    tmp.remove(tmp.size()-1);

                }}
        }
    }

    /**
     * leetcode 47 duplicate permutation
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list= new ArrayList();
        Arrays.sort(nums);
        permuteUnique(nums, list,new ArrayList<Integer>(), new boolean[nums.length]);

        return list;

    }
    void permuteUnique(int[] nums,List<List<Integer>> list, List<Integer> tmp, boolean[] used  ){
        if(tmp.size()==nums.length){
            list.add(new ArrayList<>(tmp));
        }else{
            for(int i=0;i<nums.length;i++){
                if(used[i] || i>0 && nums[i-1] == nums[i] && !used[i-1]) continue;
                used[i]=true;
                tmp.add(nums[i]);
                permuteUnique(nums, list,tmp, used);
                tmp.remove(tmp.size()-1);
                used[i]=false;
            }
        }

    }
    /** letcode all subset
     *
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates remove line for noon dups
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    /**
     * return a list of all unique combinations of candidates where the chosen numbers sum to target
     * Input: candidates = [2,3,6,7], target = 7
     * Output: [[2,2,3],[7]] leetcode 39 The same number may be chosen from candidates an unlimited number of times
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> list= new ArrayList();
        Arrays.sort(candidates);
        combinationSum(list, candidates,  target,new ArrayList<Integer>(),0) ;
        return list;
    }
    void combinationSum( List<List<Integer>> list,int[] candidates, int target, List<Integer> tmp,int start) {
        if (target < 0)
            return;
        if (target == 0)
            list.add(new ArrayList(tmp));
        for (int i = start; i < candidates.length; i++) {
            tmp.add(candidates[i]);
            combinationSum(list, candidates, target - candidates[i], tmp, i);
            tmp.remove(tmp.size() - 1);

        }
    }
    /**
     *
     */
    public boolean isLongPressedName(String name, String typed) {
        int i=0;
        int j=0;
        while(i<name.length() && j<typed.length()){
            if( name.charAt(i)==typed.charAt(j))
            {
                j++;
                i++;
            }else if(j>=1 && typed.charAt(j-1)==typed.charAt(j)){
                j++;
            }else
                return false;
        }
        System.out.println("----i-"+i+"-j;"+j);

        if( i !=name.length()){
            return false;
        }else{
            while(j<typed.length()){
                if(typed.charAt(j-1) !=typed.charAt(j))
                    return false;
                j++;
            }
        }
        return true;
    }
    /**
     * 1544. Make The String Great
     */
    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i=0;
        while(i<sb.length()-1){
            if(Math.abs(sb.charAt(i)-sb.charAt(i+1))==32)
            {
                sb.deleteCharAt(i);
                sb.deleteCharAt(i);
                i=Math.max(i-1,0);
            }else
                i++;
        }
        return sb.toString();
    }
    /**
     * 40. Combination Sum II Each number in candidates may only be used once in the combination.
     * @param target
     * @return
     */
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> list= new ArrayList();
            Arrays.sort(candidates);
            combinationSum2( list, new ArrayList<Integer>(),candidates,  target,  0);
            return list;

        }
        void combinationSum2( List<List<Integer>> list,List<Integer> tmp,int[] candidates, int target, int idx){
            if(target<0)
                return;
            if(target==0)
                list.add(new ArrayList(tmp));
            for(int i=idx;i<candidates.length;i++){
                if(i>idx && candidates[i] == candidates[i-1]) continue;
                tmp.add(candidates[i]);
                combinationSum2( list, tmp,candidates,  target-candidates[i],  i+1);
                tmp.remove(tmp.size()-1);

            }
        }
/**77
 * untp no n and size k
 */  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> list= new ArrayList();
    combine(1, n,  k,  list, new ArrayList());
    return list;
}
    void combine(int st,int n, int k, List<List<Integer>> list, List<Integer> tmp){
        if(tmp.size()==k)
            list.add(new ArrayList(tmp));
        for(int i=st;i<=n;i++){
            tmp.add(i);
            combine(i+1, n,  k,  list,tmp);
            tmp.remove(tmp.size()-1);
        }

    }
    /**
     * 377. Combination Sum IV Given an integer array with all positive numbers and no duplicates,
     * find the number of possible combinations that add up to a positive integer target.
     */
    private int[] dp;
    public int combinationSum4(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }

    public int combinationSum4II(int[] nums, int target) {
        int[] dp= new int[target+1];
        dp[0]=1;
        for(int i=1;i<=target;i++){
            for(int num: nums){
                if (i - num >= 0)
                    dp[i]+=dp[i-num];
            }
        }
        return dp[target];

    }
    /**
     * string = (operand1 + operator + operand2)
     * @param str
     * @return
     */
    static String prefixToInFix(String str){
        Stack <String> stk= new Stack ();
        for(int i=str.length()-1;i>=0;i--){
            char c=str.charAt(i);
            if(Character.isLetterOrDigit(c))
                stk.push(c+"");
            else{
                String s1=stk.pop();
                String s2=stk.pop();
                stk.push("("+s1+c+s2+")");
            }
        }

        return stk.pop();
    }
    /**
     * add char to stack
     * if operator(+ / *) pop 2 top elements from stack createstring and push back.
     * @param str
     * @return
     */
    static String prefixToPostFix(String str){
        Stack<String> stk = new Stack<>();
        for (int i =str.length()-1;i>=0;i--){
            if(Character.isLetterOrDigit(str.charAt(i))){
                stk.push(str.charAt(i)+"");
            }
            else{
                String c1= stk.pop();
                String c2=stk.pop();
                stk.push(c1+c2+str.charAt(i));

            }
        }
        return stk.pop();
    }
    static int[] mapping = new int[26];
    static public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++)
            mapping[order.charAt(i) - 'a'] = i;
        for (int i = 1; i < words.length; i++)
            if (bigger(words[i - 1], words[i]))
                return false;
        return true;
    }
    static boolean bigger(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        for (int i = 0; i < n && i < m; ++i)
            if (s1.charAt(i) != s2.charAt(i))
                return mapping[s1.charAt(i) - 'a'] > mapping[s2.charAt(i) - 'a'];
        return n > m;
    }
    static String infoxToPostFix(String str){
        StringBuffer sb = new StringBuffer();
        Stack<Character> stk= new Stack<>();
        char[] arr= str.toCharArray();
        for(Character ch:arr){
            if(Character.isLetterOrDigit(ch))
                sb.append(ch);
            else if (ch == '(') {
                stk.push(ch);
            }
            else if(ch == ')'){
                while(!stk.isEmpty()  && stk.peek() != '('){
                    sb.append((stk.pop()));
                }
                stk.pop();
            }
            else {
                while (!stk.isEmpty()&& precedencde(ch)<=precedencde(stk.peek())){
                    sb.append(stk.pop());

                }
                stk.push(ch);
            }

        }
        while ((!stk.isEmpty())){
            if(stk.peek() == '(')
                return "Invalid Expression";
            sb.append(stk.pop());
        }
        return sb.toString();
    }
    static int precedencde(char ch){
        if(ch=='+' || ch=='-')
            return 1;
        if(ch=='/' || ch=='*')
            return 2;
        if(ch=='^' || ch=='-')
            return 3;
        return -1;
    }
    static String postFixToInFix(String str){
        Stack <String> stk= new Stack ();
        for(int i=0;i<str.length();i++){
            char c=str.charAt(i);
            if(Character.isLetterOrDigit(c))
                stk.push(c+"");
            else{
                String s1=stk.pop();
                String s2=stk.pop();
                stk.push("("+s2+c+s1+")");
            }
        }

        return stk.pop();
    }


    static void sortLexicographically(String strArr[])
    {
        for (int i = 0; i < strArr.length; i++) {
            for (int j = i + 1; j < strArr.length; j++) {
                if (strArr[i].compareToIgnoreCase(strArr[j])
                        > 0) {
                    String temp = strArr[i];
                    strArr[i] = strArr[j];
                    strArr[j] = temp;
                }
            }
        }
    }

    /**
     * leetcode #216
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list= new ArrayList();
        combinationSum3( k,  n,list, new ArrayList<Integer>(),0);
        return list;

    }
    void combinationSum3(int k, int n,List<List<Integer>> list,List tmp, int start){
        if(tmp.size()==k && n<0)
            return;
        else if(tmp.size()==k && n==0)
            list.add(new ArrayList(tmp));
        else {
            for(int i=start;i<n;i++){
                tmp.add(i+1);
                combinationSum3( k,  n-i-1,list, tmp,i+1);
                tmp.remove(tmp.size()-1);
            }
        }//

    }

    /**
     *make smallest num digit remove min digits
     */
    public String removeKdigits(String num, int k) {
        StringBuilder res= new StringBuilder();
        for (int i=0; i<num.length(); i++){
            char cur= num.charAt(i);
            while (k>0 && res.length()>0 && res.charAt(res.length()-1)>cur ){
                res.deleteCharAt(res.length()-1);
                k--;
            }
            if (res.length()==0 && cur=='0') continue;
            res.append(cur);
        }
        while (k-->0 && res.length()>0) res.deleteCharAt(res.length()-1);
        return res.length()==0?"0":res.toString();
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // move along the input array starting from the end
        for (int idx = n - 1; idx >= 0; --idx) {
            // set all the nines at the end of array to zeros
            if (digits[idx] == 9) {
                digits[idx] = 0;
            }
            // here we have the rightmost not-nine
            else {
                // increase this rightmost not-nine by 1
                digits[idx]++;
                // and the job is done
                return digits;
            }
        }
        // we're here because all the digits are nines
        digits = new int[n + 1];
        digits[0] = 1;
        return digits;
    }

    /**
     * leetcode 332
     * airport routes destination iteneary
     */
    Map<String,PriorityQueue<String>> hm= new HashMap();
    List<String> route= new ArrayList();
    public List<String> findItinerary(List<List<String>> tickets) {
        for(List<String> tick :tickets){
            hm.computeIfAbsent(tick.get(0),k->new PriorityQueue()).add(tick.get(1));
        }
        visit("JFK");
        return route;
    }
    void visit(String s){
        while(hm.containsKey(s) && !hm.get(s).isEmpty()){
            visit(hm.get(s).poll());

        }
        route.add(0,s);
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSumII(nums, i, res);
            }
        return res;
    }
    void twoSumII(int[] nums, int i, List<List<Integer>> res) {
        int lo = i + 1, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum < 0) {
                ++lo;
            } else if (sum > 0) {
                --hi;
            } else {
                res.add(Arrays.asList(nums[i], nums[lo++], nums[hi--]));
                while (lo < hi && nums[lo] == nums[lo - 1])
                    ++lo;
            }
        }
    }

    /**
     * leetcode 1099
     * @param nums
     * @param k
     * @return
     */
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int answer = -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < k) {
                answer = Math.max(answer, sum);
                left++;
            } else {
                right--;
            }
        }
        return answer;
    }

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            sum += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return sum;
    }

    private int twoSumSmaller(int[] nums, int startIndex, int target) {
        int sum = 0;
        int left = startIndex;
        int right = nums.length - 1;
        while (left < right) {
            if (nums[left] + nums[right] < target) {
                sum += right - left;
                left++;
            } else {
                right--;
            }
        }
        return sum;
    }
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; ++i) {
            int lo = i + 1, hi = sz - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (Math.abs(target - sum) < Math.abs(diff))
                    diff = target - sum;
                if (sum < target)
                    ++lo;
                else
                    --hi;
            }
        }
        return target - diff;
    }


 // count each character first. For each 26 characters, check if it's count is already used. If so, delete characters until you find unused count, or reach zero.
    public int minDeletions(String s) {
        int[] arr = new int[26];
        int res = 0;
        for (char ch : s.toCharArray()) {
            arr[ch - 'a']++;
        }
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            int freq = arr[i];
            while (freq > 0) {
                if (!used.contains(freq)) {
                    used.add(freq);
                    break;
                }
                freq--;
                res++;
            }
        }
        return res;
    }

    /**
     * minimum cost node weight edge graph cities node
     */
    public int minimumCost(int N, int[][] connections) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());  // start from 0
        for (int[] c : connections) {
            graph.get(c[0]).add(new int[]{c[1], c[2]});
            graph.get(c[1]).add(new int[]{c[0], c[2]});
        }

        Queue<int[]> pq = new PriorityQueue<>((a,b)-> a[1]-b[1]);  // minHeap compare with cost
        boolean[] visited = new boolean[N+1];
        pq.offer(new int[]{1,0}); // suppose there is a start 0 node with 0-1 cost is 0.
        int numberOfCitiesVisited = 0, cost = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (visited[cur[0]]) continue;

            cost += cur[1];
            visited[cur[0]] = true;
            numberOfCitiesVisited++;  // not all node may be visited.

            List<int[]> neis = graph.get(cur[0]);
            for (int[] nei : neis) {
                if (visited[nei[0]]) continue; // if already visted, it means there is a path with smaller cost that can get to it, so we do not need to access it again.
                pq.offer(nei);
            }
        }
        return numberOfCitiesVisited == N ? cost : -1;
    }

    //** acyclic graph to findall paths from 0 to end DAG graph (directed acyclic grph)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        path.add(0);
        dfsSearch(graph, 0, res, path);

        return res;
    }

    private void dfsSearch(int[][] graph, int node, List<List<Integer>> res, List<Integer> path) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for (int nextNode : graph[node]) {
            path.add(nextNode);
            dfsSearch(graph, nextNode, res, path);
            path.remove(path.size() - 1);
        }
    }

    /**
     *
     *  find max length of unqiue word for arr of words

     */
    static public int maxLength(List<String> A) {
        List<Integer> dp = new ArrayList<>();
        dp.add(0);
        int res = 0;
        for (String s : A) {
            int a = 0, dup = 0;
            for (char c : s.toCharArray()) {
                System.out.println(Integer.toBinaryString((c - 'a')));

                int x= 1 << (c - 'a');
                dup |= a & x; // is used to check whether there are some duplicate characters. If there is, some position in dup will be set
                              // as 1, which will make dup larger than 0. Then we do not need to consider this word definitely.
                a |= x; //is to set the position to 1 if there exists some character in the current word. (if the character is 'a', then set position 0 as 1)
                System.out.println(Integer.toBinaryString(dup));
                System.out.println(Integer.toBinaryString(a));
            }
            if (dup > 0) continue;
            for (int i = dp.size() - 1; i >= 0; --i) {
                if ((dp.get(i) & a) > 0) continue;
                dp.add(dp.get(i) | a);
                res = Math.max(res, Integer.bitCount(dp.get(i) | a));
            }
        }
        return res;
    }

    private int result = 0;

    /**
     *
     */
    public int maxLengthUNIQUECHARS(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }

        dfs(arr, "", 0);

        return result;
    }

    private void dfs(List<String> arr, String path, int idx) {
        boolean isUniqueChar = isUniqueChars(path);

        if (isUniqueChar) {
            result = Math.max(path.length(), result);
        }

        if (idx == arr.size() || !isUniqueChar) {
            return;
        }

        for (int i = idx; i < arr.size(); i++) {
            dfs(arr, path + arr.get(i), i + 1);
        }
    }

    private boolean isUniqueChars(String s) {
        Set<Character> set = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }
        return true;
    }

    /**
     *whether the course queries[i][0] is a prerequisite of the course queries[i][1] or not.
      *to take course 0 you have first to take course 1, which is expressed as a pair: [1,0]
     */
    //
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        boolean[][] connected = new boolean[n][n];
        for (int[] p : prerequisites)
            connected[p[0]][p[1]] = true; // p[0] -> p[1]
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    connected[i][j] = connected[i][j] || connected[i][k] && connected[k][j];
        List<Boolean> ans = new ArrayList<>();
        for (int[] q : queries)
            ans.add(connected[q[0]][q[1]]);
        return ans;
    }

    //polenton

    /**
     * grid1 = [
     * 	['c', 'c', 'c', 't', 'i', 'b'],
     * 	['c', 'c', 'a', 't', 'n', 'i'],
     * 	['a', 'c', 'n', 'n', 't', 't'],
     * 	['t', 'c', 's', 'i', 'p', 't'],
     * 	['a', 'o', 'o', 'o', 'a', 'a'],
     * 	['o', 'a', 'a', 'a', 'o', 'o'],
     * 	['k', 'a', 'i', 'c', 'k', 'i'],
     * ]
     * word1 = "catnip"
     * word2 = "cccc"
     * word3 = "s"
     * word4 = "bit"
     * word5 = "aoi"
     * word6 = "ki"
     * word7 = "aaa"
     * word8 = "ooo"
     * find_word_location(grid1, word1) => [ (0, 2), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
     */

    static List<List<Integer>> locateWordPosition(char[][] arr, String s){
        if(arr == null || arr.length==0 || s== null ||s.length()==0)
            return new ArrayList<>();
        List<List<Integer>> res= new ArrayList<>();
        int n=arr.length;
        int m=arr[0].length;
        char[] c=s.toCharArray();
        int k=s.length()-1;
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                if(k>=0 && arr[i][j]==c[k]){
                    if(k>0 && (arr[i-1][j]==c[k-1] || arr[i][j-1]==c[k-1])){
                        List<Integer> tmp= new ArrayList<>();
                        tmp.add(i);
                        tmp.add(j);
                        res.add(new ArrayList<>(tmp));
                        k--;
                    }
                    else if(k<=0){
                        List<Integer> tmp= new ArrayList<>();
                        tmp.add(i);
                        tmp.add(j);
                        res.add(new ArrayList<>(tmp));
                        k--;

                    }

                }
            }

        }
        return res;

    }
    //str cta, arr= "ajsdjcta,sadda,sad"
    static String findscrambledWords(String[] arr, String str){

        if(arr== null || arr.length==0 ||str== null)
            return null;
        String res=null;
        for(int i=0;i<arr.length;i++){
            String s=arr[i];
            HashMap<Character,Integer> hm= new HashMap<>();
            for(char c:arr[i].toCharArray()){
                hm.put(c,hm.getOrDefault(c,0)+1);
            }
            int st=0;
            int end=0;
            int count=hm.size();

            while(end<str.length()){
                char ch=str.charAt(end);
                if(hm.containsKey(ch)){
                    hm.put(ch,hm.getOrDefault(ch,0)-1);
                    if(hm.get(ch)==0)
                        count--;
                }
                end++;
                if(count == 0){
                    res =s;
                    return res;

                }
            }
        }
        return res;
    }
 /**
     * LeetCode 122
     * print max profit of stock can buy and sell as many as you want
     */
    public int maxProfit(int[] prices) {
        int i = 0;
        int val = prices[0];
        int peak = prices[0];
        int profit = 0;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1])
                i++;
            val = prices[i];
            while (i < prices.length - 1 && prices[i] <= prices[i + 1])
                i++;
            peak = prices[i];
            profit = profit + (peak - val);

        }
        return profit;

    }

    /**
     * Leetcode 121 print max profit of stock can buy and sell
     */
    public int maxProfitI(int[] prices) {
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min)
                min = prices[i];
            int profit = prices[i] - min;
            if (profit > maxProfit)
                maxProfit = profit;
        }
        return maxProfit;

    }
    static public int maxProfitForkTransactions(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;

        //if k >= n/2, then you can make maximum number of transactions.
        if (k >=  n/2) {
            int maxPro = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i-1])
                    maxPro += prices[i] - prices[i-1];
            }
            return maxPro;
        }

        int[][] dp = new int[k+1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i-1][0] - prices[0]; //max profit where you already bought the stock on day jj with at most i transactions.
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j-1],  prices[j] + localMax);
                localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
            }
        }
        return dp[k][n-1];
    }
    public int maxProfit2Days(int[] prices) {
        int n=prices.length;
        int[][] dp = new int[3][n];
        int localMax = Integer.MIN_VALUE;
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j < n; j++) {
                localMax = Math.max(localMax, dp[i-1][j-1] - prices[j-1]);
                dp[i][j] = Math.max(dp[i][j-1], localMax + prices[j]);
            }
            localMax = Integer.MIN_VALUE;
        }
        return dp[2][n-1];

    }
    }

