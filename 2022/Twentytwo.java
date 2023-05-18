import java.io.*;
import java.util.*;

public class Twentytwo {
    private static char[][] plane;
    private static String motion = "";
    private static int start_col;

    private static int row_total;
    private static int col_total;

    public static void main(String[] args) throws IOException{
        String file = "Twentytwo.txt";
        parse(file);
        System.out.println(motion());

        //parttwo not done still
    }

    private static void parse(String file) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        row_total = 0; col_total = 0;
        while(scan.ready()){
            String line = scan.readLine();
            if(col_total < line.length()){col_total = line.length();}
            if(line.isEmpty()){break;}
            row_total++;
        }
        plane = new char[row_total][col_total];
        scan.close();

        scan = new BufferedReader(new FileReader(file));
        boolean check = true;
        for(int row = 0; row < row_total; row++){
            int col = 0;
            String line = scan.readLine();
            for(char c: line.toCharArray()){
                plane[row][col]= c;
                if(check && c == '.'){start_col = col;}
                col++;
            }
            while (col < col_total){plane[row][col] = ' '; col++;}
        }

        scan.readLine();
        while(scan.ready()){motion += scan.readLine();}
        scan.close();
    }

    private static int motion(){
        int counter = 0, row = 0, col = start_col, dir = 0;

        while(counter < motion.length()){
            String far_str = "";
            while(counter < motion.length() && motion.charAt(counter) != 'R' && motion.charAt(counter) != 'L'){
                far_str += motion.charAt(counter);
                counter++;
            }

            int far = Integer.valueOf(far_str);
            
            if(dir == 0){
                while(far > 0){
                    if(col == col_total -1 || plane[row][col+1] == ' '){
                        int find = 0;
                        while(plane[row][find] == ' '){find++;}
                        
                        if(plane[row][find] == '#'){break;}
                        else{col = find; far--;}
                    }
                    else{
                        if(plane[row][col+1] == '#'){break;}
                        else{col++; far--;}
                    }
                }
            }
            else if(dir == 1){
                while(far > 0){
                    if(row == row_total -1 || plane[row +1][col] == ' '){
                        int find = 0;
                        while(plane[find][col] == ' '){find++;}

                        if(plane[find][col] == '#'){break;}
                        else{row = find; far--;}
                    }
                    else{
                        if(plane[row+1][col] == '#'){break;}
                        else{row++; far--;}
                    }
                }
            }
            else if(dir == 2){
                while(far > 0){
                    if(col == 0 || plane[row][col-1] == ' '){
                        int find = plane[row].length -1;
                        while(plane[row][find] == ' '){find--;}
                        
                        if(plane[row][find] == '#'){break;}
                        else{col = find; far--;}
                    }
                    else{
                        if(plane[row][col-1] == '#'){break;}
                        else{col--; far--;}
                    }
                }
            }
            else if(dir == 3){
                while(far > 0){
                    if(row == 0 || plane[row-1][col] == ' '){
                        int find = plane.length-1;
                        while(plane[find][col] == ' '){find--;}

                        if(plane[find][col] == '#'){break;}
                        else{row = find; far--;}
                    }
                    else{
                        if(plane[row-1][col] == '#'){break;}
                        else{row--; far--;}
                    }
                }
            }

            if(counter < motion.length()){
                if(motion.charAt(counter) == 'R'){dir += 1;}
                else{dir -= 1;}
                dir = dir %4;
                if(dir < 0){dir +=4;}
                counter++;
            }
        }

        return 1000*(row +1) + 4*(col+1) + dir;
    }

    private static int cube(){
        int counter = 0, row = 0, col = start_col, dir = 0;

        while(counter < motion.length()){
            String far_str = "";
            while(counter < motion.length() && motion.charAt(counter) != 'R' && motion.charAt(counter) != 'L'){
                far_str += motion.charAt(counter);
                counter++;
            }

            int far = Integer.valueOf(far_str);

            int drow = 0; int dcol = 0;
            if (dir == 0){drow = 0; dcol = 1;}
            else if (dir == 1){drow = 1; dcol = 0;}
            else if (dir == 2){drow = 0; dcol = -1;}
            else if (dir == 3){drow = -1; dcol = 0;}

            while(far > 0){
                
            }

            if(counter < motion.length()){
                if(motion.charAt(counter) == 'R'){dir += 1;}
                else{dir -= 1;}
                dir = dir %4;
                if(dir < 0){dir +=4;}
                counter++;
            }
        }

        return 1000*(row +1) + 4*(col+1) + dir;
    }
    
}
