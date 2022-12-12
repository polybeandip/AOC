import java.io.*;
import java.util.*;

public class Twelve{
    static String file = "Twelve.txt";
    static int[] back;
    public static void main(String[] args) throws IOException{
        partOne();
        partTwo();
    }

    private static void partOne() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        StringBuilder buildInput = new StringBuilder();
        buildInput.append(scan.readLine());
        int x = buildInput.length();
        int y = 1;
        while(scan.ready()){y++; buildInput.append(scan.readLine());}
        String input = buildInput.toString();
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        int start = 0;
        int end = 0;

        //parse
        for(int i = 0; i < x*y; i++){
            char c = input.charAt(i);
            if (c == 'S'){start = i; c='a';}
            if (c == 'E'){end = i; c='z';}

            adj.add(new ArrayList<Integer>());
            try{
                char top = input.charAt(i-x);
                if (top == 'E'){top = 'z';}
                if (top - c < 2){adj.get(i).add(i-x);}
            }catch(Exception e){}
            try{
                char bot = input.charAt(i+x);
                if (bot == 'E'){bot = 'z';}
                if (bot - c < 2){adj.get(i).add(i+x);}
            }catch(Exception e){}
            if (i % x != 0){
                char left = input.charAt(i-1);
                if (left == 'E'){left = 'z';}
                if (left - c < 2){adj.get(i).add(i-1);}
            }
            if ((i +1) % x != 0){
                char right = input.charAt(i+1);
                if (right == 'E'){right = 'z';}
                if (right - c < 2){adj.get(i).add(i+1);}
            }
        }
        System.out.println(BFS(start, end, adj));

        /*
        List<Integer> path = new ArrayList<Integer>();
        int p = end;
        while(p != start){path.add(p); p = back[p];}
        path.add(start);

        for (int i = path.size() -1; i >= 0; i--){
            System.out.print(path.get(i) + " ");
        }
        System.out.println(); 
        */
    }

    private static void partTwo() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        StringBuilder buildInput = new StringBuilder();
        buildInput.append(scan.readLine());
        int x = buildInput.length();
        int y = 1;
        while(scan.ready()){y++; buildInput.append(scan.readLine());}
        String input = buildInput.toString();
        List<List<Integer>> adj = new ArrayList<List<Integer>>();
        int start = 0;
        int end = 0;

        //parse
        for(int i = 0; i < x*y; i++){
            char c = input.charAt(i);
            if (c == 'S'){start = i; c='a';}
            if (c == 'E'){end = i; c='z';}

            adj.add(new ArrayList<Integer>());
            try{
                char top = input.charAt(i-x);
                if (top == 'E'){top = 'z';}
                if (top - c < 2){adj.get(i).add(i-x);}
            }catch(Exception e){}
            try{
                char bot = input.charAt(i+x);
                if (bot == 'E'){bot = 'z';}
                if (bot - c < 2){adj.get(i).add(i+x);}
            }catch(Exception e){}
            if (i % x != 0){
                char left = input.charAt(i-1);
                if (left == 'E'){left = 'z';}
                if (left - c < 2){adj.get(i).add(i-1);}
            }
            if ((i +1) % x != 0){
                char right = input.charAt(i+1);
                if (right == 'E'){right = 'z';}
                if (right - c < 2){adj.get(i).add(i+1);}
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < x*y; i++){
            if (input.charAt(i) == 'a' || input.charAt(i) == 'S'){
                int bfs = BFS(i,end,adj);
                if (min > bfs){min = bfs;}
            }
        }
        System.out.println(min);
    }
    

    private static int BFS(int start, int end, List<List<Integer>> adj){
        Queue<Integer> outside = new LinkedList<Integer>();
        int n = adj.size();
        back = new int[n];
        boolean[] known = new boolean[n];
        boolean[] discovered = new boolean[n];
        int[] dist = new int[n];

        //init
        for (int i = 0; i < n; i++){
            if(i == start){dist[i] = 0;}
            else{dist[i] = Integer.MAX_VALUE;}
        }
        outside.add(start);
        discovered[start] = true;

        while(known[end] == false && outside.isEmpty() == false){
            int current = outside.poll();
            if (known[current] == true){continue;}
            known[current] = true;

            for(int neighbour: adj.get(current)){
                if (known[neighbour] == false && discovered[neighbour] == false){
                    dist[neighbour] = dist[current] + 1;
                    outside.add(neighbour);
                    discovered[neighbour] = true;
                    back[neighbour] = current;
                }
            }
        }

        return dist[end];
    }
}