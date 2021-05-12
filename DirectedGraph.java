package practice;

import java.util.*;

public class DirectedGraph {
    static class Graph {
        private int V;   // No. of vertices
        private LinkedList<Integer> adj[]; //Adjacency List

        //Constructor
        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList();
        }

        //Function to add an edge into the graph
        void addEdge(int v, int w) {
            adj[v].add(w);
        }
    }
    public static void main(String args[])
    {
        // Create a graph given in the above diagram
        Graph g = new Graph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("Following are strongly connected components "+
                "in given graph ");
        printSCC(g);
        System.out.println("Topological sort");
        Graph g1 = new Graph(6);
        g1.addEdge(5, 2);
        g1.addEdge(5, 0);
        g1.addEdge(4, 0);
        g1.addEdge(4, 1);
        g1.addEdge(2, 3);
        g1.addEdge(3, 1);
        topologicalSort(g1);
        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
       // graph2.addEdge(1, 2);
      graph2.addEdge(2, 0);
        graph2.addEdge(2, 3);
     //   graph2.addEdge(3, 3);

        if(isCyclic(graph2))
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't "
                    + "contain cycle");

        System.out.println("Is cyclic BFS "+isCyclicBFS(graph2));

    }
    /**
     * Kosaraju algo to print strongly connected algo
     */
   static void printSCC(Graph g){
        Stack<Integer> stack= new Stack<>();
        boolean[] visited= new boolean[g.V];
        for(int i=0;i<g.V;i++){
            if (visited[i] == false){
                fillOrder(i,visited,stack,g); // Fill vertices in stack according to their finishing times
            }
        }
        Graph gr = getTranspose( g);// Create a reversed graph
        Arrays.fill(visited,false);
        while(!stack.isEmpty()){
            int v = stack.pop();
            if (visited[v] == false)
            {
                DFSUtil(v, visited,gr);
                System.out.println(" ");
            }
        }

    }
    static Graph getTranspose(Graph g){
        Graph gr= new Graph(g.V);
        for (int v = 0; v < g.V; v++)
        {
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i =g.adj[v].listIterator();
            while(i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }

     static void fillOrder(int indx,boolean[] visited,Stack<Integer> stack,Graph g){
        visited[indx] =true;
        for(int i=0;i<g.adj[indx].size();i++){
            if(!visited[g.adj[indx].get(i)]){
                fillOrder(g.adj[indx].get(i),visited,stack,g);
            }
        }
        stack.push(new Integer(indx));
    }
    static void DFSUtil(int v,boolean visited[],Graph g)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        int n;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i =g.adj[v].iterator();
        while (i.hasNext())
        {
            n = i.next();
            if (!visited[n])
                DFSUtil(n,visited,g);
        }
    }
    static void topologicalSort(Graph g){ //time complexity O(V+E) space O(V)
       Stack<Integer> stack = new Stack<>();
       boolean[] visit = new boolean[g.V];
       for(int i=0;i<g.V;i++){
           if(!visit[i]){
               topologicalSort(g,visit,stack,i);
           }
       }
       while(!stack.isEmpty())
           System.out.print(stack.pop() +" ");
        System.out.println(" ");

    }
    static void topologicalSort(Graph g,boolean[] visit, Stack<Integer> stack, int i){
       visit[i] = true;
       for(int n : g.adj[i]){
           if(!visit[n])
               topologicalSort(g,visit,stack,n);
       }
       stack.add(i);
    }
    static boolean isCyclic(Graph g){
       boolean[] visited = new boolean[g.V];
       boolean[] resStack = new boolean[g.V];
       for(int i=0;i<g.V;i++){
           if(isCyclic(g,visited,resStack,i))
               return true;
       }
       return false;

    }
    static boolean isCyclic(Graph g, boolean[] visit, boolean[] recStack, int n){
        if(recStack[n])
            return true;
        if(visit[n])
            return false;
        visit[n] = true;
        recStack[n] = true;
        List<Integer> l=g.adj[n];
        for(int i: l){
            if(isCyclic(g,visit,recStack,i))
                return true;
        }
        recStack[n] =false;
        return false;
    }
    static boolean isCyclicBFS(Graph g){ // uses Kahn's topoligical sort algo
       int[] inDegree= new int[g.V];
       for(int i=0;i<g.V;i++){
           for(int n:g.adj[i])
               inDegree[n]++; //Traverse adjacency lists to fill indegrees of// vertices. This step takes O(V+E) time
       }
       int count=0; // count of visited vertices
       Queue<Integer>q= new LinkedList<>();
       // Create a vector to store result (A topological ordering of the vertices)
        Vector<Integer> top_order = new Vector<>();
       for(int i=0;i<inDegree.length;i++)
           if(inDegree[i]==0)
               q.offer(i);
        while(!q.isEmpty()){
            int u=q.poll();
          //  top_order.add(u);
            for(int v: g.adj[u]){
                 if(--inDegree[v]==0)
                     q.offer(v);
            }
            count++;

        }
        System.out.println("count "+count);
        if(count !=g.V)
            return true;
        return false;
    }
}
