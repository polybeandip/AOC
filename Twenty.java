import java.io.*;
import java.util.*;

public class Twenty {
    private static Node zero1 = null;
    private static Node zero2 = null;
    private static List<Node> input1 = null;
    private static List<Node> input2 = null;
    private static int key = 811589153;
    private static int size = 0;
    public static void main(String[] args) throws IOException{
        String file = "Twenty.txt";
        BufferedReader scan = new BufferedReader(new FileReader(file));
        input1 = new ArrayList<Node>();
        Node current = null;
        Node prev = null;

        while(scan.ready()){
            size++;
            int val = Integer.valueOf(scan.readLine());
            current = new Node(val);
            current.prev = prev;
            if(input1.size() != 0){prev.next = current;}
            prev = current;
            if(val == 0){zero1 = current;}
            input1.add(current);
        }
        current.next = input1.get(0);
        input1.get(0).prev = current;
        scan.close();

        scan = new BufferedReader(new FileReader(file));
        input2 = new ArrayList<Node>();
        current = null;
        prev = null;

        while(scan.ready()){
            long val = Long.valueOf(scan.readLine());
            val *= key;
            current = new Node(val);
            current.prev = prev;
            if(input2.size() != 0){prev.next = current;}
            prev = current;
            if(val == 0){zero2 = current;}
            input2.add(current);
        }
        current.next = input2.get(0);
        input2.get(0).prev = current;
        scan.close();

        partOne();
        partTwo();
    }

    private static void partOne(){
        mix(input1);

        int sum = 0;
        Node current = zero1;
        for(int i = 0; i < 3000; i++){
            current = current.next;
            if(i==999 || i == 1999 || i == 2999){sum+= current.val;}
        }

        System.out.println(sum);
    }

    private static void partTwo(){
        for(int i = 0; i < 10; i++){mix(input2);}

        long sum = 0;
        Node current = zero2;
        for(int i = 0; i < 3000; i++){
            current = current.next;
            if(i==999 || i == 1999 || i == 2999){sum = sum + current.val;}
        }

        System.out.println(sum);
    }


    private static void mix(List<Node> input){
        for(Node current: input){
            if(current == zero1 || current == zero2){continue;}

            //remove node
            current.prev.next = current.next;
            current.next.prev = current.prev;

            //put back node
            if(current.val > 0){
                long howmany = current.val % (size -1);
                for(long i = 0; i< howmany; i++){
                    current.prev = current.next;
                    current.next = current.next.next;
                }
                current.prev.next = current;
                current.next.prev = current;
            }
            else{
                long howmany = (-current.val) % (size -1);
                for(long i = 0; i< howmany; i++){
                    current.next = current.prev;
                    current.prev = current.prev.prev;
                }
                current.prev.next = current;
                current.next.prev = current;
            }
        }
    }

    private static class Node{
        public Node prev;
        public Node next;
        public long val;

        public Node(long val){this.val = val;}
    }
}
