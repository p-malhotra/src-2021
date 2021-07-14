import java.util.*;
import java.util.LinkedList;

public class Course {
    /**
     * leetcode 207
     * dedlock detection
     * can all courses be completed
     * [0,1] means 0 is dependent on 1 ir 1 should be completed before 0
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        HashMap<Integer,List<Integer>> hm = new HashMap<>();
        boolean[] visit = new boolean[numCourses];
        for(int[] pre: prerequisites){
            List<Integer> list= hm.getOrDefault(pre[1], new ArrayList<Integer>());
            list.add(pre[0]);
            hm.put(pre[1],list);           
        }
        for(int i=0;i<inDegree.length; i++){
            if(! canDo(hm,visit,i))
                return false;
        }
        return true;
    }
    
    static boolean canDo(HashMap<Integer,List<Integer>> hm,boolean[] visit,int course){
        if(visit[course])
            return false;
        visit[course]=true;
        if(hm.containsKey(course)){
            for(int i : hm.get(course)){
                if(! canDo(hm,visit,i))
                    return false;
            }
        }
        visit[course]=false;
        return true;
        
    }
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
    /**
     * leetcode find course order course schedule -II coursescheduleII
     * 210 iterative BFS
     */
    static public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return null;
        // Convert graph presentation from edges to indegree of adjacent list.
        int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
        for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
            indegree[prerequisites[i][0]]++;

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0) {
                // Add the course to the order because it has no prerequisites.
                order[index++] = i;
                queue.offer(i);
            }
        // How many courses don't need prerequisites.
        while (!queue.isEmpty()) {
            int prerequisite = queue.poll(); // Already finished this prerequisite course.
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == prerequisite) {
                    indegree[prerequisites[i][0]]--;
                    if (indegree[prerequisites[i][0]] == 0) {
                        // If indegree is zero, then add the course to the order.
                        order[index++] = prerequisites[i][0];
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }
        return (index == numCourses) ? order : new int[0];
    }
     /**
     * trying to find order and no of semesters
     */
    public int[] findOrderAndSemesters(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return null;
        int[] inDegree = new int[numCourses];
        int[] order = new int[numCourses];
        for (int[] pre : prerequisites) {
            ++inDegree[pre[0]];
        }

        int semester = 0;
        Queue<Integer> q = new LinkedList();
        int index = 0;
        for (int i = 0; i < numCourses; i++)
            if (inDegree[i] == 0) {
                q.offer(i);
                order[index++] = i;
            }
        while (!q.isEmpty()) {
            int preCourse = q.poll();
            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == preCourse) {
                    --inDegree[prerequisites[i][0]];
                    if (inDegree[prerequisites[i][0]] == 0) {
                        q.offer(prerequisites[i][0]);
                        order[index++] = prerequisites[i][0];
                    }
                }
            }
            ++semester;
        }
        System.out.println("Semesters" + semester);//return se

        return index == numCourses ? order : new int[0];
    }
     public String midSemCourse(String[][] preReq){
        HashMap<String, String> hm =new HashMap<>(); // key is course, value is prereq
        for(int i=0;i<preReq.length;i++){
            hm.put(preReq[i][1],preReq[i][0]);
        }
        List<String> preReqList= new ArrayList<String>(hm.values());
        String firstC="";
        Queue<String> q= new java.util.LinkedList();
        for(String pre:preReqList){
            if(!hm.containsKey(pre)){
                q.offer(pre);
                System.out.println(pre);

            }
        }
        int courseNo=1; // no of courses is arr.size*2;
        while(!q.isEmpty()  && courseNo <preReq.length){
            String pre=q.poll();

        for(Map.Entry<String,String> entry: hm.entrySet()){
                if(entry.getValue().equals(pre)){
                    q.offer(entry.getKey());
                    courseNo++;
                    if(courseNo>=preReq.length)
                        return entry.getValue();
                }
            }}
        return null;

    }

}
