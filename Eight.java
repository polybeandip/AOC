import java.util.*;
import java.io.*;

public class Eight{
    private static String file;
    public static void main(String[] args) throws IOException{
        file = "Eight.txt";
        partOne();
        partTwo();
    }

    private static void partOne() throws IOException{
        boolean[][] seen;
        BufferedReader scan = new BufferedReader(new FileReader(file));
        int x = scan.readLine().length();
        int y = 1;
        while(scan.ready()){
            scan.readLine();
            y++;
        }

        scan = new BufferedReader(new FileReader(file));
 
        seen = new boolean[x][y];
        int counter = -1;
        while (scan.ready()){
            String line = scan.readLine();
            counter++;
            int height = -1;
            for (int i = 0; i < x; i++){
                int tree = line.charAt(i) - 48;
                if (tree > height){
                    seen[i][counter]= true;
                    height = tree;

                }
            }
            
            height = -1;
            for (int i = x-1; i >= 0; i--){
                int tree = line.charAt(i) - 48;
                if (tree > height){
                    seen[i][counter]= true;
                    height = tree;
                }
            }
        }

        scan = new BufferedReader(new FileReader(file));

        for (int count = 0; count < x; count++){
            scan = new BufferedReader(new FileReader(file));
            StringBuilder b = new StringBuilder();
            while(scan.ready()){
                b.append(scan.readLine().charAt(count));
            }

            String line = b.toString();
            int height = -1;
            for (int i = 0; i < y; i++){
                int tree = line.charAt(i) - 48;
                if (tree > height){
                    seen[count][i]= true;
                    height = tree;
                }
            }
            
            height = -1;
            for (int i = y-1; i >= 0; i--){
                int tree = line.charAt(i) - 48;
                if (tree > height){
                    seen[count][i]= true;
                    height = tree;
                }
            }
        }

        int see = 0;
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if (seen[i][j]){see++;}
            }
        }

        System.out.println(see);
    }

    private static void partTwo() throws IOException{
        int[][] seen;
        BufferedReader scan = new BufferedReader(new FileReader(file));
        int x = scan.readLine().length();
        int y = 1;
        while(scan.ready()){
            scan.readLine();
            y++;
        }

        scan = new BufferedReader(new FileReader(file));
 
        seen = new int[x][y];
        for (int i = 0; i < x; i++){
            Arrays.fill(seen[i], 1);
        }
        
        int counter = -1;
        while (scan.ready()){
            String line = scan.readLine();
            counter++;
            int height = -1;
            for (int i = 0; i < x; i++){
                int tree = line.charAt(i) - 48;
                int score = 0;
                for (int j = i-1; j >= 0; j--){
                    score +=1;
                    if (tree <= ((line.charAt(j)) - 48)){break;}
                }

                seen[i][counter] *= score;
            }
            
            height = -1;
            for (int i = x-1; i >= 0; i--){
                int tree = line.charAt(i) - 48;
                int score = 0;
                for (int j = i+1; j < x; j++){
                    score +=1;
                    if (tree <= (line.charAt(j)) - 48){break;}
                }

                seen[i][counter] *= score;
            }
        }

        scan = new BufferedReader(new FileReader(file));

        for (int count = 0; count < x; count++){
            scan = new BufferedReader(new FileReader(file));
            StringBuilder b = new StringBuilder();
            while(scan.ready()){
                b.append(scan.readLine().charAt(count));
            }

            String line = b.toString();
            int height = -1;
            for (int i = 0; i < y; i++){
                int tree = line.charAt(i) - 48;
                int score = 0;
                for (int j = i-1; j >=0 ; j--){
                    score +=1;
                    if (tree <= (line.charAt(j)) - 48){break;}
                }

                seen[count][i] *= score;
            }
            
            height = -1;
            for (int i = y-1; i >= 0; i--){
                int tree = line.charAt(i) - 48;
                int score = 0;
                for (int j = i+1; j < y; j++){
                    score +=1;
                    if (tree <= (line.charAt(j)) - 48){break;}
                }

                seen[count][i] *= score;
            }
        }

        int max = 0;
        int imax =0;
        int jmax = 0;
        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++){
                if (seen[i][j] > max){max = seen[i][j]; imax = i; jmax = j;}
            }
        }

        System.out.println(max);
    }

    //another case of the idea being simple but Im bad at programming :(
}