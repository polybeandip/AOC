
import java.io.*;

public class Four {

    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Four.txt"));
        partOne(scan);
        scan = new BufferedReader(new FileReader("Four.txt"));
        partTwo(scan);
    }

    private static void partOne(BufferedReader scan) throws IOException {
        int count=0;
        while(scan.ready()){
            String[] line = scan.readLine().split(",");
            String[] first = line[0].split("-");
            String[] second = line[1].split("-");
            
            if (Integer.valueOf(first[0])<= Integer.valueOf(second[0])){
                if(Integer.valueOf(first[1]) >= Integer.valueOf(second[1])){
                    count++;
                    continue;
                }
            }
            if (Integer.valueOf(second[0])<= Integer.valueOf(first[0])){
                if(Integer.valueOf(second[1]) >= Integer.valueOf(first[1])){
                    count++;
                    continue;
                }
            }

        }

        System.out.println(count);
   }

   private static void partTwo(BufferedReader scan) throws IOException{
        int count=0;
        while(scan.ready()){
            String[] line = scan.readLine().split(",");
            String[] first = line[0].split("-");
            String[] second = line[1].split("-");
            
            if (Integer.valueOf(first[1])< Integer.valueOf(second[0])){
                continue;
            }
            else if (Integer.valueOf(second[1])< Integer.valueOf(first[0])){
                continue;
            }
            else{
                count++;
            }

        }

        System.out.println(count);
   }
}
