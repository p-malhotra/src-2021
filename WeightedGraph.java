package practice;

import java.util.Arrays;

public class WeightedGraph {
    class Edge implements Comparable<Edge>{
        int wt;
        int src;
        int dest;

        @Override
        public int compareTo(Edge o) {
            return this.wt-o.wt;
        }
    };
    class subset
    { //    // A class to represent a subset for
        int parent, rank;
    };
    int V, E; // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges

    WeightedGraph(int V, int e){
        this.V=V;
        this.E= e;
        edge= new Edge[e];
        for(int i=0;i<e;i++)
            edge[i]= new Edge();
    }
    void KruskalMST(){
        // Tnis will store the resultant MST
        Edge result[] = new Edge[V];

        // An index variable, used for result[]
        int indx = 0;

        // An index variable, used for sorted edges
        int i = 0;
        Arrays.fill(result,new Edge());

        // Step 1:  Sort all the edges in non-decreasing
        // order of their weight.  If we are not allowed to
        // change the given graph, we can create a copy of
        // array of edges
        Arrays.sort(edge);

        // Allocate memory for creating V ssubsets
        subset subsets[] = new subset[V];
        Arrays.fill(subsets,new subset());
        for (i = 0; i < V; ++i)
            subsets[i] = new subset();
      //  Arrays.fill(subsets,new subset());

        // Create V subsets with single elements
        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0; // Index used to pick next edge

        // Number of edges to be taken is equal to V-1
        while (indx < V - 1)
        {
            // Step 2: Pick the smallest edge. And increment
            // the index for next iteration
            Edge next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // If including this edge does't cause cycle,
            // include it in result and increment the index
            // of result for next edge
            if (x != y) {
                result[indx++] = next_edge;
                Union(subsets, x, y);
            }
            // Else discard the next_edge
        }

        // print the contents of result[] to display
        // the built MST
        System.out.println("Following are the edges in "
                + "the constructed MST");
        int minimumCost = 0;
        for (i = 0; i < indx; ++i)
        {
            System.out.println(result[i].src + " -- "
                    + result[i].dest
                    + " == " + result[i].wt);
            minimumCost += result[i].wt;
        }
        System.out.println("Minimum Cost Spanning Tree "
                + minimumCost);
    }

    int find(subset[] subsets,int i){
        if(subsets[i].parent !=i)
            subsets[i].parent=find(subsets,subsets[i].parent);
        return (subsets[i]).parent;

    }

    void Union(subset[] subsets,int x, int y){ // A function that does union of two setsof x and y (uses union by rank)
        int xroot= find(subsets,x);
        int yroot = find(subsets,y);
        if(subsets[xroot].rank<subsets[yroot].rank)
            subsets[xroot].parent= yroot;
        else if(subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent=xroot;
        else{ //If ranks are same, then make one as root and increment its rank by one
            subsets[yroot].parent=xroot;
            subsets[xroot].rank++;
        }
    }
    public static void main(String[] args)
    {

        /* Let us create following wted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        int V = 4; // Number of vertices in graph
        int E = 5; // Number of edges in graph
        WeightedGraph graph = new WeightedGraph(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].wt = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].wt = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].wt = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].wt = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].wt = 4;

        // Function call
        graph.KruskalMST();
    }

}
