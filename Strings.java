package practice;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Strings {
    public List<Integer> findAnagrams(String s, String t) {
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;


        while(end < s.length()){
            char c = s.charAt(end);
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) counter--;
            }
            end++;

            while(counter == 0){
                char tempc = s.charAt(begin);
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);
                    if(map.get(tempc) > 0){
                        counter++;
                    }
                }
                if(end-begin == t.length()){
                    result.add(begin);
                }
                begin++;
            }

        }
        return result;
    }
    // s = "eceba"  o/p = "ece" length 3
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int start = 0, end = 0, counter = 0, len = 0;
        while(end < s.length()){
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) == 1) counter++;//new char
            end++;
            while(counter > 2){
                char cTemp = s.charAt(start);
                map.put(cTemp, map.get(cTemp) - 1);
                if(map.get(cTemp) == 0){
                    counter--;
                }
                start++;
            }
            len = Math.max(len, end-start);
        }
        return len;
    }
    //https://leetcode.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int begin = 0, end = 0, counter = 0, d = 0;

        while (end < s.length()) {
            // > 0 means repeating character
            //if(map[s.charAt(end++)]-- > 0) counter++;
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) > 1) counter++;
            end++;

            while (counter > 0) {
                //if (map[s.charAt(begin++)]-- > 1) counter--;
                char charTemp = s.charAt(begin);
                if (map.get(charTemp) > 1) counter--;
                map.put(charTemp, map.get(charTemp)-1);
                begin++;
            }
            d = Math.max(d, end - begin);
        }
        return d;
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
    public int repeatedStringMatch(String A, String B) {

        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if(sb.toString().contains(B)) return count;
        if(sb.append(A).toString().contains(B)) return ++count;
        return -1;
    }
     /**
     * LeetCode 97
     * Interleaving str
     */
    static boolean interleaving(String s1,String s2,String s3){
        return interleaving( s1,0, s2,0, s3, "");

    }
    static boolean interleaving(String s1,int l1,String s2,int l2,String s3,String res){
         if((res.equals(s3) && l1==s1.length() && l2==s2.length()))
            return true;
        boolean ans=false;
        boolean temp;
        if(l1<s1.length()){
             ans =ans | interleaving(s1,l1+1,s2,l2,s3,res+s1.charAt(l1));
        }

         if(l2<s2.length()){
            ans =ans | interleaving(s1,l1,s2,l2+1,s3,res+s2.charAt(l2));

         }
        return ans;
    }
    static boolean interleavingDP(String s1,String s2,String s3){
        if(s1.length()+s2.length() != s3.length())
            return false;
        boolean[][] m= new boolean[s1.length()+1][s2.length()+1];
        for(int i=0;i<s1.length()+1;i++)
            for(int j=0;j<s2.length()+1;j++){
                if(i==0 && j==0)
                    m[i][j]=true;
                else if(i==0){
                    if((s2.charAt(j-1)==s3.charAt(i+j-1)))
                m[i][j] =(s2.charAt(j-1)==s3.charAt(i+j-1) && m[i][j-1]);
                }
                 else if(j==0) {
                    if (s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        m[i][j] = m[i - 1][j];
                }
                 else {
                         boolean i1 = (s1.charAt(i - 1) == s3.charAt(i + j - 1));
                         boolean i2 = m[i - 1][j];
                         boolean j1 = (s2.charAt(j - 1) == s3.charAt(i + j - 1));
                         boolean j2 = m[i][j - 1];
                         m[i][j] = (i1 && i2) || (j1 && j2);
                     }                }

        return m[s1.length()][s2.length()];
    }

    /**
     * leetcoe 1143
     * abcdeh,abddfsdfh --> adh
     * @return
     */
    static int lengthlongestCommonSubSeqRe(String s1, String s2){
        return lengthlongestCommonSubSeqRe(s1,0,0,s2);
    }
    static int lengthlongestCommonSubSeqRe(String s1,int i,int j,String s2){
        if(i==s1.length() || j==s2.length())
            return 0;
        if(s1.charAt(i)==s2.charAt(j))
            return lengthlongestCommonSubSeqRe(s1,i+1,j+1,s2)+1;
           return Math.max(lengthlongestCommonSubSeqRe(s1,i+1,j,s2) ,
                    lengthlongestCommonSubSeqRe(s1,i,j+1,s2)) ;
    }

    /**
     * length and subsequence
     * @param s1
     * @param s2
     * @return
     */
    static int lengthlongestCommonSubSeqDP(String s1,String s2){
        int[][]m= new int[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++)
            for(int j=0;j<=s2.length();j++){
                if(i==0 ||j ==0)
                    m[i][j]=0;
                else{
                    if(s1.charAt(i-1) != s2.charAt(j-1))
                        m[i][j] = Math.max(m[i-1][j],m[i][j-1]);
                    else
                        m[i][j]= 1+m[i-1][j-1];
                }
            }
        // Find subsequence
        int temp=m[s1.length()][s2.length()];
            String s="";
            int i=s1.length();
            int j=s2.length();
            while(i>0 && j>0){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    s=s1.charAt(i-1)+s;
                    i--;j--;}
                else if(m[i-1][j]>m[i][j-1]){
                    i--;
                }else
                    j--;
            }
        System.out.println("Print Sub seq "+s);
return m[s1.length()][s2.length()];
    }
    static void longestCommonSubSeqRe(String s1, String s2){
        String r="";
        System.out.println("Rec-->"+longestCommonSubSeqRe(s1,0,0,s2));
    }
    static String longestCommonSubSeqRe(String s1,int i,int j,String s2){
        if(i==s1.length() || j==s2.length())
            return "";
        if(s1.charAt(i)==s2.charAt(j))
            return s1.charAt(i)+longestCommonSubSeqRe(s1,i+1,j+1,s2);

        if(((longestCommonSubSeqRe(s1,i+1,j,s2)).length())>
                ((longestCommonSubSeqRe(s1,i,j+1,s2)).length()))
            return longestCommonSubSeqRe(s1,i+1,j,s2);
        else
            return longestCommonSubSeqRe(s1,i,j+1,s2);
    }


    static int longestincSubSequence(int[] arr){
        int[] res=new int[arr.length+1];
        res[0]=1;
        for(int i=1;i<arr.length;i++){
            for(int j=0;j<=i;j++){
                if(arr[i]>arr[j] && (res[i]<res[j]+1)) {
                    res[i] =res[j] + 1;
                }
                }
            }
        int max=0;
        int index=0;
        for(int e=0;e<res.length;e++){
            if(max<res[e]){
                max=res[e];
            index=e;
            }
        }
        int mx=max;
        String s=arr[index]+"**";
        while(index>=0 && mx >0){
            if(res[index]==mx-1) {
                s = s + arr[index];
                mx=mx-1;
            }
            index=index-1;

        }
        System.out.println("-->"+s);
            return max;
    }

    static  String longestCommonsibString(String s1, String s2){
        int m[][] = new int [s1.length()+1][s2.length()+1];
        String sub="";
        int index=-1;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<=s1.length();i++)
            for(int j=0;j<=s2.length();j++){
                if(i==0 ||j==0)
                    m[i][j]=0;
                else if((s1.charAt(i-1)==s2.charAt(j-1))) {
                    m[i][j] = 1 + m[i - 1][j - 1];
                    if(m[i][j]>max) {
                        max = m[i][j];
                        index=i;

                    }
                }
            }
        sub= s1.substring(index-max,index);
        System.out.println("Length of substring "+max);
        System.out.println("Index "+(index));

        return sub;

    }
     /**
     * leetcode 438. s: "cbaebabacd" p: "abc" Output: [0, 6]
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) return new ArrayList();
        int[] pArr = new int[26];
        int[] sArr = new int[26];
        for (char c : p.toCharArray()) {
            pArr[c - 'a']++;
        }

        List<Integer> list = new ArrayList();
        for (int i = 0; i < s.length(); i++) {
            sArr[s.charAt(i) - 'a']++;

            if (i >= p.length()) {
                sArr[s.charAt(i - p.length()) - 'a']--;
            }
            if (Arrays.equals(sArr, pArr))
                list.add(i - p.length() + 1);
        }
        return list;

    }

    /**
     * LEETCODE 76
     *Minimum window  anagram, find min window which contains all the letters of t
     */
    public String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        int minStart = 0;
        if (s.length() < t.length())
            return "";
        HashMap<Character, Integer> hm = new HashMap();
        for (char c : t.toCharArray()) {
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        int start = 0;
        int end = 0;
        int counter = 0;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (hm.containsKey(c)) {
                hm.put(c, hm.getOrDefault(c, 0) - 1);
                if (hm.get(c) == 0)
                    counter++;
            }
            end++;
            while (counter == t.length()) {////if the count is equal to the length of t, then we find such window
                if (end - start < min) {
                    min = end - start;
                    minStart = start;
                }
                char st = s.charAt(start);
                //starting from the leftmost index of the window, we want to check if s[left] is in t. If so, we will remove it from the window, and increase 1 time on its counter in hashmap which means we will expect the same character later by shifting right index. At the same time, we need to reduce the size of the window due to the removal.

                if (hm.containsKey(st)) {
                    hm.put(st, hm.get(st) + 1);
                    if (hm.get(st) > 0)
                        counter--;
                }
                start++;
            }

        }
        if (min == Integer.MAX_VALUE)
            return "";
        return s.substring(minStart, minStart + min);

    }
    /**
     * LEETCODE 76
     *Minimum window  anagram, find min window which contains all the letters of t
     * using array
     */
    public String minWindow1(String s, String t) {
        int [] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        int start = 0, end = 0, minStart = 0, minLen = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] > 0) counter--;
            map[c1]--; // Decrease maps[s[end]]. If char does not exist in t, m[s[end]] will be negative.
            end++;
            while (counter == 0) {
                if (minLen > end - start) {
                    minLen = end - start;
                    minStart = start;
                }
                final char c2 = s.charAt(start);
                map[c2]++;
                if (map[c2] > 0) counter++;
                start++;
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    // at most k distinct chars
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] map = new int[256];
        int start = 0, end = 0, maxLen = Integer.MIN_VALUE, counter = 0;

        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] == 0) counter++;
            map[c1]++;
            end++;

            while (counter > k) {
                final char c2 = s.charAt(start);
                if (map[c2] == 1) counter--;
                map[c2]--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }
   // Longest Substring - at most 2 distinct characters

    public int lengthOfLongestSubstringTwoDistinctArr(String s) {
        int[] map = new int[128];
        int start = 0, end = 0, maxLen = 0, counter = 0;

        while (end < s.length()) {
            final char c1 = s.charAt(end);
            if (map[c1] == 0) counter++;
            map[c1]++;
            end++;

            while (counter > 2) {
                final char c2 = s.charAt(start);
                if (map[c2] == 1) counter--;
                map[c2]--;
                start++;
            }

            maxLen = Math.max(maxLen, end - start);
        }

        return maxLen;
    }
}
