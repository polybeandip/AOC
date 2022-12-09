import java.util.*;
import java.io.*;

//the indentation is cursed, sorry
public class Nine {
    private static Set<List<Integer>> visited;
    private static String file;
   public static void main(String[] args) throws IOException{
    file = "Nine.txt";
    partOne();
    //the argument is the number of tails
    partTwo(9);
   }

   private static void partOne() throws IOException{
        visited = new HashSet<List<Integer>>();
        BufferedReader scan = new BufferedReader(new FileReader(file));
        int headi = 0, headj = 0;
        int taili = 0, tailj =0;

        while(scan.ready()){
            String[] instructions = scan.readLine().split(" ");
            String dir = instructions[0];
            int dist = Integer.valueOf(instructions[1]);

            for (int i = 0; i < dist; i++){
                switch(dir){
                    case "R": headi += 1; break;
                    case "L": headi -= 1; break;
                    case "U": headj += 1; break;
                    case "D": headj -= 1; break;
                }
                if (taili == headi || tailj == headj){
                    if (headi > taili +1){taili += 1;}
                    if (headi < taili -1){taili -= 1;}
                    if (headj > tailj +1){tailj += 1;}
                    if (headj < tailj -1){tailj -= 1;}
                }
                else{
                    if (headi > taili +1){taili += 1; if(headj < tailj){tailj -=1;}else{tailj +=1;}}
                    else if (headi < taili -1){taili -= 1; if(headj < tailj){tailj -=1;}else{tailj +=1;}}
                    else if (headj > tailj +1){tailj += 1; if(headi < taili){taili -=1;}else{taili +=1;}}
                    else if (headj < tailj -1){tailj -= 1; if(headi < taili){taili -=1;}else{taili +=1;}}
                }

                visited.add(Arrays.asList(taili,tailj));
            }
        }
        scan.close();
        System.out.println(visited.size());
   }

   private static void partTwo(int knots) throws IOException{
    visited = new HashSet<List<Integer>>();
    BufferedReader scan = new BufferedReader(new FileReader(file));
    int headi = 0, headj = 0;
    int[] tailiarr = new int[knots];
    int[] tailjarr = new int[knots];
    while(scan.ready()){
        String[] instructions = scan.readLine().split(" ");
        String dir = instructions[0];
        int dist = Integer.valueOf(instructions[1]);

        for (int i = 0; i < dist; i++){
            switch(dir){
                case "R": headi += 1; break;
                case "L": headi -= 1; break;
                case "U": headj += 1; break;
                case "D": headj -= 1; break;
            }
            for (int counter =0; counter < knots; counter++){
                if (counter == 0){
                    if (tailiarr[0]== headi || tailjarr[0] == headj){
                        if (headi > tailiarr[0] +1){tailiarr[0]+= 1;}
                        if (headi < tailiarr[0] -1){tailiarr[0]-= 1;}
                        if (headj > tailjarr[0] +1){tailjarr[0] += 1;}
                        if (headj < tailjarr[0]-1){tailjarr[0] -= 1;}
                    }
                    else{
                        if (headi > tailiarr[0]+1){tailiarr[0] += 1; if(headj < tailjarr[0]){tailjarr[0]-=1;}else{tailjarr[0]+=1;}}
                        else if (headi < tailiarr[0] -1){tailiarr[0]-= 1; if(headj < tailjarr[0]){tailjarr[0]-=1;}else{tailjarr[0]+=1;}}
                        else if (headj > tailjarr[0]+1){tailjarr[0]+= 1; if(headi < tailiarr[0]){tailiarr[0]-=1;}else{tailiarr[0]+=1;}}
                        else if (headj < tailjarr[0] -1){tailjarr[0] -= 1; if(headi < tailiarr[0]){tailiarr[0] -=1;}else{tailiarr[0]+=1;}}
                    }
                }
                else{
                    if (tailiarr[counter]== tailiarr[counter-1] || tailjarr[counter] == tailjarr[counter-1]){
                        if (tailiarr[counter-1] > tailiarr[counter] +1){tailiarr[counter]+= 1;}
                        if (tailiarr[counter-1] < tailiarr[counter] -1){tailiarr[counter]-= 1;}
                        if (tailjarr[counter-1] > tailjarr[counter] +1){tailjarr[counter] += 1;}
                        if (tailjarr[counter-1] < tailjarr[counter]-1){tailjarr[counter] -= 1;}
                    }
                    else{
                        if (tailiarr[counter -1] > tailiarr[counter]+1){tailiarr[counter] += 1; if(tailjarr[counter-1] < tailjarr[counter]){tailjarr[counter]-=1;}else{tailjarr[counter]+=1;}}
                        else if (tailiarr[counter -1] < tailiarr[counter] -1){tailiarr[counter]-= 1; if(tailjarr[counter-1] < tailjarr[counter]){tailjarr[counter]-=1;}else{tailjarr[counter]+=1;}}
                        else if (tailjarr[counter -1] > tailjarr[counter]+1){tailjarr[counter]+= 1; if(tailiarr[counter-1] < tailiarr[counter]){tailiarr[counter]-=1;}else{tailiarr[counter]+=1;}}
                        else if (tailjarr[counter-1] < tailjarr[counter] -1){tailjarr[counter] -= 1; if(tailiarr[counter-1] < tailiarr[counter]){tailiarr[counter] -=1;}else{tailiarr[counter]+=1;}}
                    }
                }
            }

            visited.add(Arrays.asList(tailiarr[knots-1],tailjarr[knots -1]));
        }
    }
    scan.close();
    System.out.println(visited.size());
   }
}
