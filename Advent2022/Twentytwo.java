import java.io.*;
import java.util.*;

public class Twentytwo {
    private static char[][] plane;
    private static char[][][] cube;
    private static String motion = "";
    private static int start_col;

    private static int row_total;
    private static int col_total;

    public static void main(String[] args) throws IOException{
        String file = "Twentytwo.txt";
        parse1(file);
        System.out.println(motion());

        parse3("Test.txt");
        System.out.println(cube(4));
    }

    private static void parse1(String file) throws IOException{
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

    private static void parse3(String file) throws IOException{
        /*
         *      0
         *  5 3 4
         *      2 1
         * 
         */


        cube = new char[6][4][4]; //face, row, col
        BufferedReader scan = new BufferedReader(new FileReader(file));
        int count = 0;
        while(scan.ready()){
           String line = scan.readLine();
            if(count < 4){
                for (int i = 0; i < 4; i++){
                    cube[0][count][i] = line.charAt(i + 8);
                }
            }
            else if(count < 8){
                for(int i = 0; i < 4; i++){
                    cube[3][count - 4][i] = line.charAt(i+ 4);
                    cube[5][count - 4][i] = line.charAt(i);
                    cube[4][count - 4][i] = line.charAt(i+ 8);
                }
            }else if(count < 12){
                for(int i = 0; i < 4; i++){
                    cube[2][count - 8][i] = line.charAt(i + 8);
                    cube[1][count - 8][i] = line.charAt(i+12);
                }
            }
            else{
                motion = scan.readLine();
            }
            count++;
        }

        scan.close();
    }

    private static void parse2(String file) throws IOException{
        cube = new char[6][50][50]; //face, row, col
        BufferedReader scan = new BufferedReader(new FileReader(file));
        int count = 0;
        while(scan.ready()){
           String line = scan.readLine();
            if(count < 50){
                for (int i = 0; i < 50; i++){
                    cube[0][count][i] = line.charAt(i + 50);
                    cube[1][count][i] = line.charAt(i + 100);
                }
            }
            else if(count < 100){
                for(int i = 0; i < 50; i++){
                    cube[4][count - 50][i] = line.charAt(i+ 50);
                }
            }else if(count < 150){
                for(int i = 0; i < 50; i++){
                    cube[3][count - 100][i] = line.charAt(i);
                    cube[2][count - 100][i] = line.charAt(i+50);
                }
            }
            else if(count < 200){
                for(int i = 0; i < 50; i++){
                    cube[5][count - 150][i] = line.charAt(i);
                }

            }
            count++;
        }

        scan.close();
    }

    private static int[] moveFace(int nrow, int ncol, int face, int side){
        //new row(0), new col(1), new face(2), new direction (i.e. new drow(4) and dcol(5))
        int[] out = new int[6];
        side--;
        if(nrow == -1){//up
            if(face == 0){
                out[2] = 5; out[0] = ncol; out[1] = 0;
                out[4] = 0; out[5] = 1; //face 5 and right
            }
            else if(face == 1){
                out[2] = 5; out[0] = side; out[1] = ncol;
                out[4] = -1; out[5] = 0; //face 5 and upward
            }
            else if(face == 2){
               out[2] = 4; out[0] = side; out[1] = ncol;
               out[4] = -1; out[5] = 0; //face 4 and upward
            }
            else if(face == 3){
                out[2] = 4; out[0] = ncol; out[1] = 0;
                out[4] = 0; out[5] = 1; //face 4 and right
            }
            else if(face == 4){
                out[2] = 0; out[0] = side; out[1] = ncol;
                out[4] = -1; out[5] = 0; //face 0 and upward
            }
            else if(face == 5){
                out[2] = 3; out[0] = side; out[1] = ncol;
                out[4] = -1; out[5] = 0; //face 3 and upward
            }
        }
        else if(nrow == side+1){//down
            if(face == 0){
                out[2] = 4; out[0] = 0; out[1] = ncol;
                out[4] = 1; out[5] = 0; //face 4 and downward
            }
            else if(face == 1){
                out[2] = 4; out[0] = ncol; out[1] =side;
                out[4] = 0; out[5] = -1; //face 4 and left
            }
            else if(face == 2){
                out[2] = 5; out[0] = ncol; out[1] =side;
                out[4] = 0; out[5] = -1; //face 5 and left
            }
            else if(face == 3){
                out[2] = 5; out[0] = 0; out[1] = ncol;
                out[4] = 1; out[5] = 0; //face 5 and downward 
            }
            else if(face == 4){
                out[2] = 2; out[0] = 0; out[1] = ncol;
                out[4] = 1; out[5] = 0; //face 2 and downward
            }
            else if(face == 5){
                out[2] = 1; out[0] = 0; out[1] = ncol;
                out[4] = 1; out[5] = 0; //face 1 and downward
            }
        }
        else if(ncol == -1){//left
            if(face == 0){
               out[2] = 3; out[0] = side - nrow; out[1] = 0;
               out[4] = 0; out[5] = 1; //face 3 and right
            }
            else if(face == 1){
                out[2] = 1; out[0] = nrow; out[1] = side;
                out[4] = 0; out[5] = -1; //face 0 and left
            }
            else if(face == 2){
                out[2] = 3; out[0] = nrow; out[1] = side;
                out[4] = 0; out[5] = -1; //face 3 and left
            }
            else if(face == 3){
               out[2] = 0; out[0] = side - nrow; out[1] = 0;
               out[4] = 0; out[5] = 1; //face 0 and right
            }
            else if(face == 4){
                out[2] = 3;  out[0] = 0; out[1] = nrow;
                out[4] = 1; out[5] = 0; //face 3 and downward
            }
            else if(face == 5){
                out[2] = 0;  out[0] = 0; out[1] = nrow;
                out[4] = 1; out[5] = 0; //face 1 and downward
            }
        }
        else if(ncol == side+1){//right
            if(face == 0){
                out[2] = 1; out[0] = nrow; out[1] = 0;
                out[4] = 0; out[5] = 1;
            }
            else if(face == 1){
                out[2] = 2; out[0] = side - nrow; out[1] = side;
                out[4] = 0; out[5] = -1;
            }
            else if(face == 2){
                out[2] = 1; out[0] = side - nrow; out[1] = side;
                out[4] = 0; out[5] = -1;
            }
            else if(face == 3){
                out[2] = 2; out[0] = nrow; out[1] = 0;
                out[4] = 0; out[5] = 1;
            }
            else if(face == 4){
                out[2] = 1; out[0] = side; out[1] = nrow;
                out[4] = -1; out[5] = 0;

            }else if(face == 5){
                out[2] = 2; out[0] = side; out[1] = nrow;
                out[4] = -1; out[5] = 0;
            }
        }

        return out;
    }

    private static int cube(int side){
        int counter = 0;
        int row = 0, col = 0, face = 0;
        int drow = 0, dcol = 1;

        while(counter < motion.length()){

            String far_str = "";
            while(counter < motion.length() && motion.charAt(counter) != 'R' && motion.charAt(counter) != 'L'){
                far_str += motion.charAt(counter);
                counter++;
            }

            int far = Integer.valueOf(far_str);

            while(far > 0){
                System.out.println("row: " + row + " col: " +col + " face: " + face);
                int nrow = row + drow;
                int ncol = col + dcol;
                if(nrow >= 0 && nrow < side && ncol >= 0 && ncol < side){
                    if(cube[face][nrow][ncol] != '#'){row = nrow; col = ncol; far--;}
                    else{break;}
                }
                else{
                    int[] out = moveFace(nrow, ncol, face,side);
                    System.out.println("out: " + Arrays.toString(out));
                    nrow = out[0]; ncol = out[1]; int nface= out[2];
                    if(cube[nface][nrow][ncol] != '#'){row = nrow; col = ncol; drow = out[4]; dcol = out[5]; face = nface; far--;}
                    else{break;}
                }
            }

            if(counter < motion.length()){
                //turn
                if(motion.charAt(counter) == 'R'){
                    if(drow == 1 && dcol == 0){drow = 0; dcol = -1;}
                    else if(drow == 0 && dcol == 1){drow = 1; dcol = 0;}
                    else if(drow == -1 && dcol == 0){drow = 0; dcol = 1;}
                    else if(drow == 0 && dcol == -1){drow = -1; dcol = 0;}
                }
                else{
                    if(drow == 1 && dcol == 0){drow = 0; dcol = 1;}
                    else if(drow == 0 && dcol == 1){drow = -1; dcol = 0;}
                    else if(drow == -1 && dcol == 0){drow = 0; dcol = -1;}
                    else if(drow == 0 && dcol == -1){drow = 1; dcol = 0;}
                }
            }

            counter++;
        }

        //convert to points on the real grid
        if(face == 0){col+= 50;}
        else if(face == 1){col+= 100;}
        else if(face == 2){row += 100; col += 50;}
        else if(face == 3){row += 100;}
        else if(face == 4){col += 50; row += 50;}
        else if(face == 5){row += 150;}

        //find direction
        int dir = 0;
        if(drow == 0 && dcol == 1){dir = 0;}
        else if(drow == 1 && dcol == 0){dir =1;}
        else if(drow == 0 && dcol == -1){dir = 2;}
        else if(drow == -1 && dcol == 0){dir = 3;}


        return 1000*(row +1) + 4*(col +1) + (dir);
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
    
}
