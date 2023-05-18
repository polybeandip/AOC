import java.util.*;
import java.io.*;

public class Fourteen {
   public static void main(String[] args) throws IOException{
    partOne("Fourteen.txt");
    partTwo("Fourteen.txt");
   }

   private static void partOne(String file) throws IOException{
    BufferedReader scan = new BufferedReader(new FileReader(file));
    Set<List<Integer>> rock = new HashSet<List<Integer>>();
    int maxy = 0;
    while(scan.ready()){
        String[] coords = scan.readLine().split("->");
        for (int i = 0; i < coords.length-1; i++){
            String[] str1 = coords[i].trim().split(",");
            int[] coords1= new int[2];
            coords1[0] = Integer.valueOf(str1[0]);
            coords1[1] = Integer.valueOf(str1[1]);

            String[] str2 = coords[i+1].trim().split(",");
            int[] coords2 = new int[2];
            coords2[0] = Integer.valueOf(str2[0]);
            coords2[1] = Integer.valueOf(str2[1]);

            if (maxy < coords1[1]){maxy = coords1[1];}
            else if (maxy < coords2[1]){maxy = coords2[1];}

            if (coords1[0] == coords2[0]){
                for (int j = coords1[1]; j <= coords2[1]; j++){
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(coords1[0]); path.add(j);
                    rock.add(path);
                }
                for (int j = coords2[1]; j <= coords1[1]; j++){
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(coords1[0]); path.add(j);
                    rock.add(path);
                }
            }
            else if (coords1[1] == coords2[1]){
                for (int j = coords1[0]; j <= coords2[0]; j++){
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(j); path.add(coords1[1]);
                    rock.add(path);
                }
                for (int j = coords2[0]; j <= coords1[0]; j++){
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(j); path.add(coords1[1]);
                    rock.add(path);
                }
            }
        }
    }
    int[] source = new int[2];
    source[0] = 500; source[1]= 0;
    scan.close();
    System.out.println(drop(rock, source,maxy));
   }

   private static void partTwo(String file) throws IOException{
    BufferedReader scan = new BufferedReader(new FileReader(file));
    Set<List<Integer>> rock = new HashSet<List<Integer>>();
    int maxy = 0;
    while(scan.ready()){
        String[] coords = scan.readLine().split("->");
        for (int i = 0; i < coords.length-1; i++){
            String[] str1 = coords[i].trim().split(",");
            int[] coords1= new int[2];
            coords1[0] = Integer.valueOf(str1[0]);
            coords1[1] = Integer.valueOf(str1[1]);

            String[] str2 = coords[i+1].trim().split(",");
            int[] coords2 = new int[2];
            coords2[0] = Integer.valueOf(str2[0]);
            coords2[1] = Integer.valueOf(str2[1]);

            if (maxy < coords1[1]){maxy = coords1[1];}
            else if (maxy < coords2[1]){maxy = coords2[1];}

            if (coords1[0] == coords2[0]){
                for (int j = coords1[1]; j <= coords2[1]; j++){
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(coords1[0]); path.add(j);
                    rock.add(path);
                }
                for (int j = coords2[1]; j <= coords1[1]; j++){
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(coords1[0]); path.add(j);
                    rock.add(path);
                }
            }
            else if (coords1[1] == coords2[1]){
                for (int j = coords1[0]; j <= coords2[0]; j++){
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(j); path.add(coords1[1]);
                    rock.add(path);
                }
                for (int j = coords2[0]; j <= coords1[0]; j++){
                    List<Integer> path = new ArrayList<Integer>();
                    path.add(j); path.add(coords1[1]);
                    rock.add(path);
                }
            }
        }
    }
    int[] source = new int[2];
    source[0] = 500; source[1]= 0;
    scan.close();
    System.out.println(dropv2(rock, source,maxy));
   }

   private static int dropv2(Set<List<Integer>> rock, int[] source, int maxy){ 
    Set<List<Integer>> sand = new HashSet<List<Integer>>();
    int count = 0;
    List<Integer> last = new ArrayList<Integer>();
    last.add(source[0]); last.add(source[1]);
    while(true){
        List<Integer> particle = new ArrayList<Integer>();
        particle.add(source[0]);
        particle.add(source[1]);
        while(true){
            if (particle.get(1) == maxy+1){sand.add(particle); break;}
            List<Integer> down = new ArrayList<Integer>(Arrays.asList(particle.get(0),particle.get(1)+1));
            List<Integer> left = new ArrayList<Integer>(Arrays.asList(particle.get(0)-1,particle.get(1)+1));
            List<Integer> right = new ArrayList<Integer>(Arrays.asList(particle.get(0)+1,particle.get(1)+1));

            if (!sand.contains(down) && !rock.contains(down)){particle = down; continue;}
            if (!sand.contains(left) && !rock.contains(left)){particle = left; continue;}
            if (!sand.contains(right) && !rock.contains(right)){particle = right; continue;}
            sand.add(particle);
            break;
        }
        count++;
        if (sand.contains(last)){break;}
    }

    return count;
   }

   private static int drop(Set<List<Integer>> rock, int[] source, int maxy){ 
    Set<List<Integer>> sand = new HashSet<List<Integer>>();
    int count = 0;
    outer:
    while(true){
        List<Integer> particle = new ArrayList<Integer>();
        particle.add(source[0]);
        particle.add(source[1]);
        while(true){
            if(particle.get(1) > maxy){break outer;}
            List<Integer> down = new ArrayList<Integer>(Arrays.asList(particle.get(0),particle.get(1)+1));
            List<Integer> left = new ArrayList<Integer>(Arrays.asList(particle.get(0)-1,particle.get(1)+1));
            List<Integer> right = new ArrayList<Integer>(Arrays.asList(particle.get(0)+1,particle.get(1)+1));

            if (!sand.contains(down) && !rock.contains(down)){particle = down; continue;}
            if (!sand.contains(left) && !rock.contains(left)){particle = left; continue;}
            if (!sand.contains(right) && !rock.contains(right)){particle = right; continue;}
            sand.add(particle);
            break;
        }
        count++;
    }

    return count;
   }
}
