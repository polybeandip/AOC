import java.io.*;

public class One{

    private static void partOne() throws IOException{
        BufferedReader read = new BufferedReader(new FileReader("One.txt"));
        int max = 0;
        int count = max;

        while(read.ready()){
            String line = read.readLine();
            if(line.isEmpty() || line.contains(" ")){
                if(count > max){max = count; count = 0;}
                count = 0;
            }
            else{
                count += Integer.valueOf(line);
            }
        }

        read.close();
        System.out.println(max);
    }

    private static void partTwo() throws IOException {
        BufferedReader read = new BufferedReader(new FileReader("One.txt"));
        int first = 0;
        int second = 0;
        int third= 0;
        int count = first;

        while(read.ready()){
            String line = read.readLine();
            if(line.isEmpty() || line.contains(" ")){
                if(count > first){third = second; second = first; first = count; count = 0;}
                else if(count > second){third = second; second = count; count =0;}
                else if (count > third){third = count; count = 0;}
                else{count = 0;}
            }
            else{
                count += Integer.valueOf(line);
            }
        }

        read.close();

        System.out.println(first + second + third);
    }

    public static void main(String[] args) throws IOException{
        partOne();
        partTwo();
    }
}