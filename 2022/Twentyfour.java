import java.io.*;
import java.util.*;

public class Twentyfour {
    private static char default_char = (new char[1])[0];

    public static void main(String[] args) throws IOException {
        BufferedReader scan = new BufferedReader(new FileReader("test.txt"));
        int max_x = scan.readLine().length();
        int max_y = 1;
        while (scan.ready()) {
            scan.readLine();
            max_y++;
        }   
        scan.close();
        int t = max_x < max_y ? max_y : max_x;

        scan = new BufferedReader(new FileReader("test.txt"));
        //x, y, t
        char[][][] bad_t = new char[t][max_x][max_y];
        
        int y = 0; 
        while(scan.ready()){
            char[] line = scan.readLine().toCharArray();
            int x = 0;
            for (char c : line){
                if (c != '#' && c != '.'){
                    bad_t[0][x][y] = c;
                }
                x++;
            }
            y++;
        }


        for(int time = 1; time < t; time++){
            for(int x = 0; x < max_x; x++){
                for(y = 0; y < max_y; y++){
                    if(bad_t[0][x][y] == default_char){
                        
                    }
                }
            }
        }

        
    }
}
