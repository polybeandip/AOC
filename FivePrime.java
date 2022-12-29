import java.io.*;
import java.util.*;

//this way is better since we can handle any number of buckets.
public class FivePrime {
    private static Map<Integer,MyStack<Character>> stacks = new HashMap<Integer,MyStack<Character>>();
    public static void main(String[] args) throws Exception{
        partOne();
        partTwo();
    }

    private static void partOne() throws Exception{
        BufferedReader scan = new BufferedReader(new FileReader("Five.txt"));
        String line;
        while((line = scan.readLine()).matches("[A-Z, \\[, \\], ]+")){
            int count = 1;
            while(true){
                if (count > line.length()){break;}
                char c = line.charAt(count);
                if (c == ' '){count+=4; continue;}
                int key = (count -1)/4 +1;
                if (stacks.containsKey(key)){
                    MyStack<Character> stack = stacks.get(key);
                    stack.add(0,c);
                }else{
                    MyStack<Character> stack = new MyStack<Character>();
                    stack.add(0,c);
                    stacks.put(key, stack);
                }

                count+= 4;
            }
        }
        
        scan.readLine();

        while(scan.ready()){
            String[] input = scan.readLine().split("[a-z, A-Z]+");
            int howmany = Integer.valueOf(input[1]);
            int from = Integer.valueOf(input[2]);
            int to = Integer.valueOf(input[3]);

            MyStack<Character> fromStack = stacks.get(from);
            MyStack<Character> toStack = stacks.get(to);

            for (int i = 0; i < howmany; i++){
                toStack.add(fromStack.pop());
            }
        }

        scan.close();

        int buckets = stacks.size();

        for (int i = 1; i <= buckets; i++){
            System.out.print(stacks.get(i).pop());
        }
        System.out.print("\n");

        stacks = new HashMap<Integer,MyStack<Character>>();
    }

    private static void partTwo() throws Exception{
        BufferedReader scan = new BufferedReader(new FileReader("Five.txt"));
        String line; 
        while((line = scan.readLine()).matches("[A-Z, \\[, \\], ]+")){
            int count = 1;
            while(true){
                if (count > line.length()){break;}
                char c = line.charAt(count);
                if (c == ' '){count+=4; continue;}
                int key = (count -1)/4 +1;
                if (stacks.containsKey(key)){
                    MyStack<Character> stack = stacks.get(key);
                    stack.add(0,c);
                }else{
                    MyStack<Character> stack = new MyStack<Character>();
                    stack.add(0,c);
                    stacks.put(key, stack);
                }

                count+= 4;
            }
        }

        scan.readLine();

        while(scan.ready()){
            String[] input = scan.readLine().split("[a-z, A-Z]+");
            int howmany = Integer.valueOf(input[1]);
            int from = Integer.valueOf(input[2]);
            int to = Integer.valueOf(input[3]);

            MyStack<Character> fromStack = stacks.get(from);
            MyStack<Character> toStack = stacks.get(to);

            char[] chars = new char[howmany];
            for (int i = 0; i < howmany; i++){
                chars[i] = fromStack.pop();
            }

            for (int i = 0; i < howmany; i++){
                toStack.add(chars[howmany -1  -i]);
            }
        }

        scan.close();

        int buckets = stacks.size();

        for (int i = 1; i <= buckets; i++){
            System.out.print(stacks.get(i).peak());
        }
        System.out.print("\n");
    }

    private static class MyStack<T> extends LinkedList<T>{
        public T pop(){
            T t = get(size() -1);
            remove(size() -1);
            return t;
        }

        public T peak(){
            return get(size() -1);
        }
    }
}



