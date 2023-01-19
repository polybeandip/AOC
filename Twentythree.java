import java.util.*;
import java.io.*;

public class Twentythree {
    private static Set<List<Integer>> input = new HashSet<List<Integer>>();
    private static int mod4 = 0;

    private static int max_x, min_x, max_y, min_y;

    public static void main(String[] args) throws IOException{
        String file = "Twentythree.txt";
         
        parse(file);
        partOne(input);
        partTwo(input);

    }

    private static void partOne(Set<List<Integer>> elves){
        for(int i = 0; i < 10; i++){
            round(elves);
        } 

        System.out.println((max_x - min_x + 1)*(max_y - min_y + 1) - elves.size());
    }

    private static void partTwo(Set<List<Integer>> elves){
        int count = 11;
        while(true){
            if(round(elves)){System.out.println(count); return;}
            count++;
        }
    }

    private static void parse(String file) throws IOException{
        BufferedReader scan = new BufferedReader( new FileReader(file));
        int y = 0;

        min_x = 0;min_y = 0;

        while(scan.ready()){
            String line = scan.readLine();
            if(max_x < line.length()-1){max_x = line.length()-1;}
            for(int i = 0; i < line.length(); i++){
                if(line.charAt(i) == '#'){
                    input.add(Arrays.asList(i,y));
                }
            }
            y++;
        }

        max_y = y-1;

        scan.close();
    }
     
    private static boolean round(Set<List<Integer>> elves){
       Map<List<Integer>,List<List<Integer>>> moves = new HashMap<List<Integer>,List<List<Integer>>>();

        //phase one
        for(List<Integer> e : elves){
            int x= e.get(0);
            int y= e.get(1);

            boolean check = true;
            for(int dx = -1; dx < 2; dx++){
                for(int dy = -1; dy < 2; dy++){
                    if(dx == 0 && dy == 0){continue;}
                    if(elves.contains(Arrays.asList(x + dx, y + dy))){check = false; break;}
                }
            }
            if(check){continue;}

            for(int i = 0; i<4; i++){
                int key = (i + mod4) % 4;
                if(key == 0){
                    if(!(elves.contains(Arrays.asList(x,y-1)) || elves.contains(Arrays.asList(x-1,y-1)) || elves.contains(Arrays.asList(x+1,y-1)))){
                        List<Integer> pos = Arrays.asList(x,y-1);
                        if(moves.containsKey(pos)){moves.get(pos).add(e);}
                        else{
                            List<List<Integer>> pos_arr = new ArrayList<List<Integer>>();
                            pos_arr.add(e);
                            moves.put(pos,pos_arr);
                        }
                        break;
                    }
                }
                else if(key == 1){
                    if(!(elves.contains(Arrays.asList(x,y+1)) || elves.contains(Arrays.asList(x+1,y+1)) || elves.contains(Arrays.asList(x-1,y+1)))){
                        List<Integer> pos = Arrays.asList(x,y+1);
                        if(moves.containsKey(pos)){moves.get(pos).add(e);}
                        else{
                            List<List<Integer>> pos_arr = new ArrayList<List<Integer>>();
                            pos_arr.add(e);
                            moves.put(pos,pos_arr); 
                        }
                        break;
                    }
                }
                else if(key == 2){
                    if(!(elves.contains(Arrays.asList(x-1,y-1)) || elves.contains(Arrays.asList(x-1,y+1)) || elves.contains(Arrays.asList(x-1,y)))){
                        List<Integer> pos = Arrays.asList(x-1,y);
                        if(moves.containsKey(pos)){moves.get(pos).add(e);}
                        else{
                            List<List<Integer>> pos_arr = new ArrayList<List<Integer>>();
                            pos_arr.add(e);
                            moves.put(pos,pos_arr); 
                        }
                        break;
                    }

                }
                else if(key == 3){
                    if(!(elves.contains(Arrays.asList(x+1,y-1)) || elves.contains(Arrays.asList(x+1,y+1)) || elves.contains(Arrays.asList(x+1,y)))){
                        List<Integer> pos = Arrays.asList(x+1,y);
                        if(moves.containsKey(pos)){moves.get(pos).add(e);}
                        else{
                            List<List<Integer>> pos_arr = new ArrayList<List<Integer>>();
                            pos_arr.add(e);
                            moves.put(pos,pos_arr); 
                        }
                        break;
                    }
                }
            }
        }

        if(moves.size() == 0){return true;}

        //phase two
        for(List<Integer> spot: moves.keySet()){
            List<List<Integer>> movers = moves.get(spot);
            if(movers.size() > 1){continue;}
            else{
                elves.add(spot);
                elves.remove(movers.get(0));

                if(spot.get(0) < min_x){min_x = spot.get(0);}
                else if (spot.get(0) > max_x){max_x = spot.get(0);}

                if(spot.get(1) < min_y){min_y = spot.get(1);}
                else if (spot.get(1) > max_y){max_y = spot.get(1);}
            }
        }

        mod4++;
        return false;
    }


}
