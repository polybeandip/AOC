import java.util.*;
import java.io.*;

public class Six {
    public static void main(String[] args) throws IOException {
         partOne();
         partTwo();
    }

    private static void partOne() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Six.txt"));
        String stream = "";
        while(scan.ready()){
            stream += scan.readLine();
        }

        scan.close();

        char[] streamchars = stream.toCharArray();
        for (int i = 3; i < streamchars.length; i++){
            Set<Character> stuff = new HashSet<Character>();
            stuff.add(streamchars[i]);
            stuff.add(streamchars[i-1]);
            stuff.add(streamchars[i-2]);
            stuff.add(streamchars[i-3]);
            
            if (stuff.size() == 4){System.out.println(i+1); return;}
        }
    }

    private static void partTwo() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Six.txt"));
        String stream = "";
        while(scan.ready()){
            stream += scan.readLine();
        }

        scan.close();

        char[] streamchars = stream.toCharArray();
        for (int i = 13; i < streamchars.length; i++){
            Set<Character> stuff = new HashSet<Character>();
            for (int j = 0; j < 14; j++){
                if (stuff.contains(streamchars[i-j])){break;}
                stuff.add(streamchars[i-j]);
            }

            if (stuff.size() == 14){System.out.println(i+1); return;}
        }

        scan.close();
    }
}
