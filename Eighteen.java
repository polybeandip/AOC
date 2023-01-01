import java.util.*;
import java.io.*;

public class Eighteen{
    private static Set<List<Integer>> input = new HashSet<List<Integer>>();

    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Eighteen.txt"));
        while(scan.ready()){
            List<Integer> cube = new ArrayList<Integer>();
            String[] split = scan.readLine().split(",");
            for (String val: split){
                int coord = Integer.valueOf(val);
                cube.add(coord);
            }
            input.add(cube);
        }
        scan.close();
       
        System.out.println(partOne(input));
        System.out.println(partTwo(input));
    }

    private static int partOne(Set<List<Integer>> cubes){
        int sides = 0;
        for(List<Integer> cube: cubes){
            for (int i = 0; i <3; i++){
                int coord = cube.get(i);
                cube.set(i,coord+1);
                if(!cubes.contains(cube)){sides++;}
                cube.set(i,coord -1);
                if(!cubes.contains(cube)){sides++;}
                cube.set(i,coord);
            }
        }

        return sides;
    }

    private static boolean fill(List<Integer> start){
        
        Queue<List<Integer>> fillable = new LinkedList<List<Integer>>();
        fillable.add(start);
        Set<List<Integer>> filled = new HashSet<List<Integer>>();

        while(!fillable.isEmpty()){
            List<Integer> cube = fillable.poll();
            if(input.contains(cube) || filled.contains(cube)){continue;}
            filled.add(cube);
            for (int j = 0; j < 3; j++){
                int coord = cube.get(j);
                List<Integer> dir1 = new ArrayList<Integer>(cube);
                List<Integer> dir2 = new ArrayList<Integer>(cube);
                dir1.set(j, coord -1);
                dir2.set(j, coord +1);
                fillable.add(dir1); fillable.add(dir2);
            }
            if (filled.size() > 10000){return true;}
        }

        return false;
    }


    private static int partTwo(Set<List<Integer>> cubes){
        int sides = 0;
        for (List<Integer> lava : cubes){
            List<Integer> cube = new ArrayList<Integer>(lava);
            for (int i = 0; i < 3; i++){
                int coord = cube.get(i);
                cube.set(i,coord-1);
                if(fill(cube)){sides++;}

                cube.set(i,coord+1);
                if(fill(cube)){sides++;}

                cube.set(i,coord);
            }
        }

        return sides;       
    }
}