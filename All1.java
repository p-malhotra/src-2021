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
                hm.put(sum - k, hm.getOrDefault(sum - k, 0) + 1);
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
    }

