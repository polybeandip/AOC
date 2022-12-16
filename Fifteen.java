import java.util.*;
import java.io.*;

public class Fifteen {
    private static String file;
    public static void main(String[] args) throws IOException{
        file = "Fifteen.txt";
        partOne(10);
        partTwo(4000000);
    }

    private static void partOne(int y) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        Set<Integer> nothing = new HashSet<Integer>();
        while(scan.ready()){
            String[] line = scan.readLine().split(" ");
            int sensorx = Integer.valueOf(line[2].split("[=,\\,]")[1]);
            int sensory = Integer.valueOf(line[3].split("[=,:]")[1]);
            int beaconx = Integer.valueOf(line[8].split("[=,\\,]")[1]);
            int beacony = Integer.valueOf(line[9].split("[=]")[1]);

            int distx =  sensorx < beaconx ? beaconx - sensorx : sensorx - beaconx;
            int disty =  sensory < beacony ? beacony - sensory : sensory - beacony;
            int dist = distx + disty;
            
            disty = sensory < y ? y - sensory : sensory - y;

            if (disty > dist){continue;}
          
            int shift = dist - disty -1;
            int left = -shift + sensorx;
            int right = shift + sensorx +1;

            for (int i = left; i <= right; i++){
                nothing.add(i);
            }
        }

        scan.close();
        System.out.println(nothing.size());
    }


    //maffs
    private static void partTwo(int max) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        List<int[]> sensors = new ArrayList<int[]>();
        while(scan.ready()){
            String[] line = scan.readLine().split(" ");
            int sensorx = Integer.valueOf(line[2].split("[=,\\,]")[1]);
            int sensory = Integer.valueOf(line[3].split("[=,:]")[1]);
            int beaconx = Integer.valueOf(line[8].split("[=,\\,]")[1]);
            int beacony = Integer.valueOf(line[9].split("[=]")[1]);

            int distx =  sensorx < beaconx ? beaconx - sensorx : sensorx - beaconx;
            int disty =  sensory < beacony ? beacony - sensory : sensory - beacony;
            int dist = distx + disty;

            int[] data = new int[3];
            data[0]= sensorx;data[1]=sensory;data[2] = dist;
            sensors.add(data);
        }
        scan.close();

        Set<List<Integer>> potential = new HashSet<List<Integer>>();

        for (int[] first : sensors){
            for (int[] second : sensors){
                int a = first[1] - first[0] + first[2] +1;
                int b = first[1] - first[0] - first[2] -1;
                int c = second[1] + second[0] + second[2] +1;
                int d = second[1] + second[0] - second[2] -1;

                if ((a +c) % 2 == 0){
                    List<Integer> coords =new ArrayList<Integer>();
                    coords.add((c-a)/2);
                    coords.add((a+c)/2);
                    potential.add(coords);
                }

                if ((a +d) % 2 == 0){
                    List<Integer> coords =new ArrayList<Integer>();
                    coords.add((d-a)/2);
                    coords.add((a+d)/2);
                    potential.add(coords);
                }

                if ((b +d) % 2 == 0){
                    List<Integer> coords =new ArrayList<Integer>();
                    coords.add((d-b)/2);
                    coords.add((b+d)/2);
                    potential.add(coords);
                }

                if ((b +c) % 2 == 0){
                    List<Integer> coords =new ArrayList<Integer>();
                    coords.add((c-b)/2);
                    coords.add((b+c)/2);
                    potential.add(coords);
                }
            }
        }

        for (List<Integer> coords : potential){
            int x= coords.get(0);
            int y = coords.get(1);
            if (x <0 ||  x > max){continue;}
            if (y <0 ||  y > max){continue;}

            boolean flag = true;
            for (int[] sensor: sensors){
                    int xdist = sensor[0] < x ? x - sensor[0] : sensor[0] - x;
                    int ydist = sensor[1] < y ? y - sensor[1] : sensor[1] - y;
                    if (xdist + ydist <= sensor[2]){flag = false;}
            }

            if(flag){;double ans = ((double) x)*4000000 + y; System.out.println(ans); return;}
        }
    }

}
