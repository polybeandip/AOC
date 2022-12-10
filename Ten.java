import java.util.*;
import java.io.*;

public class Ten{
    private static String file;
    public static void main(String[] args) throws IOException{
        file = "Ten.txt";
        partOne();
        partTwo();
    }

    public static void partOne() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        int cycle =1;
        int X= 1;
        int counter = 20;
        int score = 0;

        while(scan.ready()){
            String[] instruction = scan.readLine().split(" ");
            if (cycle == counter){
                score += X * counter;
                counter += 40;
            }

            if (instruction[0].equals("noop")){cycle+=1;}
            else{
                cycle += 1;
                if (cycle == counter){
                    score += X * counter;
                    counter += 40;
                }
                cycle +=1;
                X += Integer.valueOf(instruction[1]);
            }
        }

        System.out.println(score);
    }

    public static void partTwo() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        int cycle =1;
        int X= 1;
        int[][] screen = new int[40][6];

        while(scan.ready()){
            String[] instruction = scan.readLine().split(" ");
            draw(X, cycle, screen);

            if (instruction[0].equals("noop")){cycle+=1;}
            else{
                cycle += 1;
                draw(X, cycle, screen);
                cycle +=1;
                X += Integer.valueOf(instruction[1]);
            }
        }

        StringBuilder build = new StringBuilder();
        for (int j = 0; j < 6; j++){
            for (int i = 0; i< 40; i++){
                if (screen[i][j] == 1){build.append("#");}
                else {build.append(".");}
            }
            build.append("\n");
        }

        System.out.println(build.toString());

    }

    private static void draw (int X, int cycle, int[][] screen){
        cycle = cycle -1;
        int pos = cycle %40;
        if (pos == X-1 || pos == X || pos == X+1){
            screen[pos][cycle/40] = 1;
        }
    }

}