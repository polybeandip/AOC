import java.io.*;
import java.util.*;
import java.util.concurrent.*;

//this way is better since we can handle any number of buckets.
public class FivePrime {
    private static Map<Integer,MyStack<Character>> stacks = new HashMap<Integer,MyStack<Character>>();
    private static int charCount;
    private static boolean flag = false;
    public static void main(String[] args) throws Exception{
        try{
        if (args[0].equals("display")){flag = true;}}
        catch(ArrayIndexOutOfBoundsException e){}
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
                if(flag) {state(toStack);
                TimeUnit.SECONDS.sleep(1);}
            }
        }

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
        charCount = 0;
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
                    charCount++;
                }else{
                    MyStack<Character> stack = new MyStack<Character>();
                    stack.add(0,c);
                    stacks.put(key, stack);
                    charCount++;
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
                if(flag) {state(toStack);
                TimeUnit.SECONDS.sleep(1);}
            }
        }

        int buckets = stacks.size();

        for (int i = 1; i <= buckets; i++){
            System.out.print(stacks.get(i).peak());
        }
        System.out.print("\n");
    }

    private static void state(MyStack yellow){
        int n = stacks.size();
        for (int current =30; current >= 0; current--){
            for (int i = 1; i <= n; i++){
                MyStack<Character> stack = stacks.get(i);
                try{
                    char c =stack.get(current);
                    if (current == stack.size() -1 && stack == yellow){System.out.print("\033[0;33m" + c + " " + "\033[0m");}
                    else if (current == stack.size()-1){
                        System.out.print("\033[0;31m" + c + " " + "\033[0m");
                    }else{System.out.print(c + " ");}
                }catch(Exception e){
                    System.out.print("  ");
                }
            }

            System.out.println();
        }
    }
}



class MyStack<T> extends LinkedList<T>{

    public T pop(){
        T t = get(size() -1);
        remove(size() -1);
        return t;
    }

    public T peak(){
        return get(size() -1);
    }

}
