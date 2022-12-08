import java.io.*;
import java.util.*;

public class Seven {
    public static void main(String[] args) throws Exception{
        partOne();
        partTwo();
    }

    private static void partOne() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Seven.txt"));
        Dir current = new Dir(null, "/");
        Dir outer = current;
        List<Dir> dirs = new ArrayList<Dir>();
        dirs.add(current);

        while(scan.ready()){
            String line = scan.readLine();
            if(line.startsWith("$ cd ..")){
                current = current.above;
            }
            else if(line.startsWith("$ cd /")){
                current = outer;
            }
            else if(line.startsWith("$ cd")){
                String[] split = line.split(" ");
                for (Dir e: current.sub){
                    if(e.name.equals(split[2])){current =e;}
                }
            }
            else if (line.startsWith("$ ls")){
                while(scan.ready() && !(line = scan.readLine()).startsWith("$")){
                    scan.mark(100);
                    String[] contents = line.split(" ");
                    if(contents[0].equals("dir")){
                        Dir ballz = new Dir(current, contents[1]);
                        current.sub.add(ballz);
                        dirs.add(ballz);
                    }
                    else{current.add(Integer.valueOf(contents[0]));}
                }
                scan.reset();
            }
        }

        System.out.println(total(dirs));
    }

    private static void partTwo() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Seven.txt"));
        Dir current = new Dir(null, "/");
        Dir outer = current;
        List<Dir> dirs = new ArrayList<Dir>();
        dirs.add(current);

        while(scan.ready()){
            String line = scan.readLine();
            if(line.startsWith("$ cd ..")){
                current = current.above;
            }
            else if(line.startsWith("$ cd /")){
                current = outer;
            }
            else if(line.startsWith("$ cd")){
                String[] split = line.split(" ");
                for (Dir e: current.sub){
                    if(e.name.equals(split[2])){current =e;}
                }
            }
            else if (line.startsWith("$ ls")){
                while(scan.ready() && !(line = scan.readLine()).startsWith("$")){
                    scan.mark(100);
                    String[] contents = line.split(" ");
                    if(contents[0].equals("dir")){
                        Dir ballz = new Dir(current, contents[1]);
                        current.sub.add(ballz);
                        dirs.add(ballz);
                    }
                    else{current.add(Integer.valueOf(contents[0]));}
                }
                scan.reset();
            }
        }

        int need = 30000000 - (70000000 - size(outer));
        int min = 70000000;
        for(Dir e : dirs){
            int size = size(e);
            if (size > need && size < min){
                min = size;
            }
        }

        System.out.println(min);

    }

    private static int size(Dir current){
        int temp = current.files;

        for (Dir e: current.sub){
            temp += size(e);
        }

        return temp;
    }

    private static int total(List<Dir> list){
        int sum= 0;
        for (Dir e: list){
            int size = size(e);
            if (size <= 100000){sum+= size;}
        }
        return sum;
    }
}

class Dir{
    String name;
    public List<Dir> sub = new ArrayList<Dir>();
    public Dir above;
    public int files;

    public Dir(Dir above, String name){
        this.name = name;
        this.above = above;
    }

    public void add(int file){
        files += file;
    }

    public String toString(){return name;}
}

