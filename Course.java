import java.util.*;
import java.util.LinkedList;

public class Course {
    /**
     * leetcode 207
     * dedlock detection
     * can all courses be completed
     * [0,1] means 0 is dependent on 1 ir 1 should be completed before 0
     */
    static boolean courseComplete(int numOFCourses, int[][] pre){
        HashSet<Integer> visited = new HashSet<>();
        //Hash map is created to find which course is dependent om key course
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        int[] visit = new int[numOFCourses];
        for(int[] i:pre){
            if(hm.containsKey(i[1]))
               hm.get(i[1]).add(i[0]);
else{
    hm.put(i[1],new ArrayList<>(Arrays.asList(i[0])));
            }
        }
        for(int i=0;i<numOFCourses;i++){
            if(!dfs(visit,i,hm))
                return false;
        }
        return true;
    }
    static boolean dfs(int[] visited, int src,HashMap<Integer, List<Integer>> hm){
        if(visited[src]==-1)
            return false;
        if(visited[src]==1)
        return true;
        visited[src] =-1;
        if(hm.containsKey(src))
            for(int j:hm.get(src))
                return dfs(visited,j,hm);

         visited[src]=1;
         return true;
    }
     static boolean bfs(int num ,int[][] prerequisites) {
         //counter for no of prerequisite. ie how many pre req for a course
         int[] counter = new int[num];
         for (int i = 0; i < prerequisites.length; i++)
             counter[prerequisites[i][0]]++;

         //store courses that have no prerequisites
         Queue<Integer> queue = new LinkedList<>();
         for (int c = 0; c < num; c++){
             if (counter[c] == 0)
                 queue.add(c);
             }
         // courses with no pre req
         int numNoPre = queue.size();
         while(!queue.isEmpty()) {
                int top = queue.remove();
                //for all dependent courses in pre
                for (int i = 0; i < prerequisites.length; i++) {
                    //If top is equal to prereq,
                    int val=prerequisites[i][1];
                    if (val == top) {
                        counter[prerequisites[i][0]]--;
                        //if all pre req are removed for that
                        if (counter[prerequisites[i][0]] == 0) {
                            numNoPre++;
                            queue.add(prerequisites[i][0]);
                        }
                    }
                }
            }
         return numNoPre==num;
    }

    public static void main(String[] args) {
        System.out.println("Course comple "+courseComplete(2,new int[][]{{1,0}}));
        System.out.println("Course comple "+courseComplete(2,new int[][]{{1,0},{0,1}}));
        System.out.println("Course complebfs  "+bfs(4,new int[][]{{1,0},{2,0},{3,1},{3,2}}));
        System.out.println("Course complebfs "+bfs(2,new int[][]{{1,0}}));
        System.out.println("Course comple bfs "+bfs(2,new int[][]{{1,0},{0,1}}));
        System.out.println("cycleDetectionCanFinish- "+cycleDetectionCanFinish(new int[][]{{0,1},{1,2},{2,3}},    1));
        System.out.println("Course comple bfs "+bfs(2,new int[][]{{1,0},{0,1}}));
    }

    /**
     * using dfs
     * O(v+e)
     * @param courses
     * @param num
     * @return
     */
    static boolean cycleDetectionCanFinish(int[][] courses, int num){
        int[] visited = new int[6];
        //0= unvisited, 1=processed, 2=processing
        for(int i=0;i<num;i++){
            if(visited[i] ==0)
                if(isCyclic(courses,visited,i))
                    return false;
        }
        return true;
    }
    static boolean isCyclic(int[][] courses,int[] visited ,int curr){

        if(visited[curr]==2)
            return true;
        visited[curr]=2;
        for(int i=0;i<courses[curr].length;i++){
            if(visited[courses[curr][i]] != 1){
                if(isCyclic(courses,visited,courses[curr][i]))
                    return true;
            }
        }
        visited[curr]=1;
        return false;
    }
    /**
     * Leetcode 210
     * Given the total number of courses numCourses and a list
     * of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
     * kahn's algo using bfs
     * O(v+e)
     */
    static void printCourses(int[][] courses,int numcourse){

    }

}
