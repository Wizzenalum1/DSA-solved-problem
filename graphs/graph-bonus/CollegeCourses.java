import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.PlainDocument;

import jdk.tools.jlink.resources.plugins;


/*############################################################################
  						   CollegeCourses
    In a college there are a total of "N" courses, labelled from "0" to "N-1". 
    You have to return whether it is possible to finish all the courses, if 
    there are some prerequisites given.
    The prerequisites are such that to pick course 1, you have to take course 
    0 first, which is represented as a pair (1 , 0).

					completed false;
  #############################################################################*/ 


public class CollegeCourses {
    private static boolean isSubGraphCyclic(int[][] adjMatrix,int current, boolean[] visited, boolean[] localVisited) {
        // System.out.println(current+" ");
        if(localVisited[current]){
            for (int i = 0; i < localVisited.length; i++) {
                if(adjMatrix[current][i]==1 && localVisited[i]) return true;
            }
        }
        visited[current] = true;
        localVisited[current] = true;
        for (int i = 0; i < localVisited.length; i++) {
            // System.out.println("r1 "+current+" "+i);
            if(adjMatrix[current][i]!=0 ){
                // System.out.println("calling  "+current+" "+i);
                if(isSubGraphCyclic(adjMatrix, i, visited, localVisited)) return true;
            }
            // System.out.println("r2 "+current+" "+i);

        }
        return false;
    }
    private static boolean isCyclePossible(int[][] adjMatrix, boolean[] visited) {
        boolean[] localVisited = new boolean[adjMatrix.length];
        for (int[]  b : adjMatrix) {
        //  System.out.println(Arrays.toString(b));   
        }
        for (int i = 0; i < visited.length; i++) {
        
            if(!visited[i]){
                // System.out.println("start subgraph   "+i);
                if(isSubGraphCyclic(adjMatrix,i,visited,localVisited)) return true;
            }
        }
        return false;
    }
// with adjList
    private static boolean isSubGraphCyclic(ArrayList<ArrayList<Integer>> adjList,int current, boolean[] visited, boolean[] localVisited) {
        // System.out.println(current+" ");
        ArrayList<Integer> list = adjList.get(current);
        if(list==null) return false;
        if(localVisited[current]){
            for (int i = 0; i < list.size(); i++) {
                if(localVisited[list.get(i)]) return true;
            }
        }
        visited[current] = true;
        localVisited[current] = true;
        for (int i = 0; i < list.size(); i++) {
            if(isSubGraphCyclic(adjList, list.get(i), visited, localVisited));
        }
        return false;
    }
    private static boolean isCyclePossible(ArrayList<ArrayList<Integer>> adjList, boolean[] visited) {
        boolean[] localVisited = new boolean[adjList.size()];
        // printAdjList(adjList);
        for (int i = 0; i < visited.length; i++) {
        
            if(!visited[i]){
                // System.out.println("start subgraph   "+i);
                if(isSubGraphCyclic(adjList,i,visited,localVisited)) return true;
            }
        }
        return false;
    }


    public static void printAdjList(ArrayList<ArrayList<Integer>> adjList) {
        // print array list for debuging
        for (int i = 0; i < adjList.size(); i++) {
            ArrayList<Integer> list = adjList.get(i);
            if(list!=null){
                for (int j = 0; j < list.size(); j++) {
                    System.out.print(j+"-"+list.get(j)+" ");
                }
                System.out.println();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        // FileReader r = new FileReader("/home/dipak/Bit_by_bit/DSA.learn/careercamp/graphs/graph-bonus/StringCircle.txt");
		InputStreamReader r = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(r);
		// int n = Integer.parseInt(br.readLine().trim());
		// int e = Integer.parseInt(br.readLine().trim());
        int n = 4;
        int e = 3;
        // String[] courseDependency = br.readLine().trim().split(" ");
        String[] courseDependency = {"0", "1", "1", "2", "2", "0"};
        // String[] courseDependency = {"0", "1", "1", "2", "0" ,"2"};
    // // using adjcency matrix but java heap space error giveing
        // int[][] adjMatrix = new int[n][n];
        // int courese = 0, prerequisites = 0; 
        // for (int i = 0; i < e; i++) { // this is directed graph
        //     courese = Integer.parseInt(courseDependency[2*i]);
        //     prerequisites = Integer.parseInt(courseDependency[2*i+1]);
        //     adjMatrix[courese][prerequisites] = 1;
        // }
        // if(isCyclePossible(adjMatrix,new boolean[n])){ // if their is any cycle then course cant be completed
        //     System.out.println("0");
        // }else System.out.println("1");
    // adjacency list instead of adjmatrix.
        ArrayList<ArrayList<Integer>> adjList =new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(null);
        }

        int courese = 0, prerequisites = 0; 
        for (int i = 0; i < e; i++) { // this is directed graph
            courese = Integer.parseInt(courseDependency[2*i]);
            prerequisites = Integer.parseInt(courseDependency[2*i+1]);
            if(adjList.get(courese)==null) adjList.set(courese,new ArrayList<Integer>());
            adjList.get(courese).add(prerequisites);
        }
        printAdjList(adjList);
        if(isCyclePossible(adjList,new boolean[n])){ // if their is any cycle then course cant be completed
            System.out.println("0");
        }else System.out.println("1");
        

    }

  
}