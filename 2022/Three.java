import java.util.*;
import java.io.*;

public class Three {
    private static Map<Character,Integer> priority = new HashMap<Character, Integer>();
    static {
        int count =1;
        char c = 'a';
        for (int i = 0; i< 26; i++){
            priority.put(c, count++);
            c = (char) (c + 1);
        }
        c = 'A';
        for (int i = 0; i< 26; i++){
            priority.put(c, count++);
            c = (char) (c + 1);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Three.txt"));
        partOne(scan);
        scan =  new BufferedReader(new FileReader("Three.txt"));
        partTwo(scan);
    }
    
    public static void partOne(BufferedReader scan) throws IOException{
        int score = 0;
        bigwhile:
        while(scan.ready()){
            String line = scan.readLine();
            int n = line.length();
            String left = line.substring(0,n/2);
            String right = line.substring(n/2,n);

            for (char c: left.toCharArray()){
                if (right.contains(c + "")){
                   score += priority.get(c);
                   continue bigwhile;
                }
            }
        }

        System.out.println(score);
    }

    private static void partTwo(BufferedReader scan) throws IOException{
        int score = 0;
        chunguswhile:
        while(scan.ready()){
            String one = scan.readLine();
            String two = scan.readLine();
            String three = scan.readLine();

            for (char c: one.toCharArray()){
                if (two.indexOf(c) != -1){
                    if (three.indexOf(c) != -1){score += priority.get(c); continue chunguswhile;}
                }
            }
        }
        System.out.println(score);
    }
}
