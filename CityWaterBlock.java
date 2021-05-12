package practice;

import java.util.LinkedList;
import java.util.Queue;

public class CityWaterBlock {
    //DriverCode Cities:4
    public static void main(String[] args) {
        int cities = 4;
        LinkedList<Integer>[] adj = new LinkedList[cities+1];
        for (int i = 0; i < adj.length; i++)
            adj[i] = new LinkedList<>();

        int []v = new int[cities + 1];

        // Adjacency list denoting road
        // between city u and v
        adj[1].add(2);
        adj[2].add(1);
        adj[2].add(3);
        adj[3].add(2);
        adj[3].add(4);
        adj[4].add(3);

        // Array for storing whether ith
        // city is blocked or not
        v[1] = 0;
        v[2] = 1;
        v[3] = 1;
        v[4] = 0;

        System.out.print(BFS(adj,v,cities));

    }
    static int BFS(LinkedList<Integer>[] adj, int[] blockers,int cities){
        boolean[] visited = new boolean[cities+1];
        int max=1;
        for(int i=1;i<=cities;i++){
            if(blockers[i] ==0 && !visited[i]){
                max= Math.max(util(adj,blockers,cities,visited,i),max);
            }
        }
        return max;
    }
    static int util(LinkedList<Integer>[] adj, int[] blockers,int cities,boolean[] visited,int src){
        visited[src]=true;
        Queue<Integer> q= new LinkedList<>();

        q.offer(src);
        int count=1;
        while(!q.isEmpty()){
            int p=q.poll();
            for(int i=0;i <adj[p].size();i++){
                if(!visited[adj[p].get(i)] && blockers[adj[p].get(i)]==0){
                    q.offer(adj[p].get(i));
                    visited[adj[p].get(i)]=true;
                    count++;

                }
                else if(!visited[adj[p].get(i)] && blockers[adj[p].get(i)]==1)
                    count++;
            }
           // q.remove();
        }
        return count;
    }

}
