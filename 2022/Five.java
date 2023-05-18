import java.io.*;
import java.util.*;

public class Five {
    private static Stack<Character> one = new Stack<Character>();
    private static Stack<Character> two = new Stack<Character>();
    private static Stack<Character> three = new Stack<Character>();
    private static Stack<Character> four = new Stack<Character>();
    private static Stack<Character> five = new Stack<Character>();
    private static Stack<Character> six = new Stack<Character>();
    private static Stack<Character> seven = new Stack<Character>();
    private static Stack<Character> eight = new Stack<Character>();
    private static Stack<Character> nine = new Stack<Character>();

    public static void main(String[] args) throws IOException{
        partOne();
        partTwo();
    }

    private static void partOne() throws IOException{
        //initalizing
        BufferedReader scan = new BufferedReader(new FileReader("Five.txt"));
        String[] lines = new String[9];
        for (int i =0; i< 8; i++){
            lines[i] = scan.readLine();
        }

        for (int i = 0; i < 8; i++){
            String input =lines[7- i];
            if (input.charAt(1)!= ' '){one.push(input.charAt(1));}
            if (input.charAt(5)!= ' '){two.push(input.charAt(5));}
            if (input.charAt(9)!= ' '){three.push(input.charAt(9));}
            if (input.charAt(13)!= ' '){four.push(input.charAt(13));}
            if (input.charAt(17)!= ' '){five.push(input.charAt(17));}
            if (input.charAt(21)!= ' '){six.push(input.charAt(21));}
            if (input.charAt(25)!= ' '){seven.push(input.charAt(25));}
            if (input.charAt(29)!= ' '){eight.push(input.charAt(29));}
            if (input.charAt(33)!= ' '){nine.push(input.charAt(33));}
        }


        scan.readLine();
        scan.readLine();
        
        while(scan.ready()){
            String[] command = scan.readLine().split("[A-Z, a-z]+");
            int howmany = Integer.valueOf(command[1]);
            int from = Integer.valueOf(command[2]);
            int to = Integer.valueOf(command[3]);

            Stack<Character> fromStack = null;
            Stack<Character> toStack = null;
            switch(from){
                case 1: fromStack = one;      break;
                case 2: fromStack = two;      break;
                case 3: fromStack = three;    break;
                case 4: fromStack = four;     break;
                case 5: fromStack = five;     break;
                case 6: fromStack = six;      break;
                case 7: fromStack = seven;    break;
                case 8: fromStack = eight;    break;
                case 9: fromStack = nine;     break;
            }

            switch(to){
                case 1: toStack = one;      break;
                case 2: toStack = two;      break;
                case 3: toStack = three;    break;
                case 4: toStack = four;     break;
                case 5: toStack = five;     break;
                case 6: toStack = six;      break;
                case 7: toStack = seven;    break;
                case 8: toStack = eight;    break;
                case 9: toStack = nine;     break;
            }

            for (int i = 0; i < howmany; i++){
                toStack.push(fromStack.pop());
            }
        }

        scan.close();

        System.out.print(one.pop());
        System.out.print(two.pop());
        System.out.print(three.pop());
        System.out.print(four.pop());
        System.out.print(five.pop());
        System.out.print(six.pop());
        System.out.print(seven.pop());
        System.out.print(eight.pop());
        System.out.println(nine.pop());

    }

    private static void partTwo() throws IOException{
        //initalizing
        BufferedReader scan = new BufferedReader(new FileReader("Five.txt"));
        String[] lines = new String[9];
        for (int i =0; i< 8; i++){
            lines[i] = scan.readLine();
        }

        for (int i = 0; i < 8; i++){
            String input =lines[7- i];
            if (input.charAt(1)!= ' '){one.push(input.charAt(1));}
            if (input.charAt(5)!= ' '){two.push(input.charAt(5));}
            if (input.charAt(9)!= ' '){three.push(input.charAt(9));}
            if (input.charAt(13)!= ' '){four.push(input.charAt(13));}
            if (input.charAt(17)!= ' '){five.push(input.charAt(17));}
            if (input.charAt(21)!= ' '){six.push(input.charAt(21));}
            if (input.charAt(25)!= ' '){seven.push(input.charAt(25));}
            if (input.charAt(29)!= ' '){eight.push(input.charAt(29));}
            if (input.charAt(33)!= ' '){nine.push(input.charAt(33));}
        }

        scan.readLine();
        scan.readLine();

        while(scan.ready()){
            String[] command = scan.readLine().split("[A-Z, a-z]+");
            int howmany = Integer.valueOf(command[1]);
            int from = Integer.valueOf(command[2]);
            int to = Integer.valueOf(command[3]);

            Stack<Character> fromStack = null;
            Stack<Character> toStack = null;
            switch(from){
                case 1: fromStack = one;      break;
                case 2: fromStack = two;      break;
                case 3: fromStack = three;    break;
                case 4: fromStack = four;     break;
                case 5: fromStack = five;     break;
                case 6: fromStack = six;      break;
                case 7: fromStack = seven;    break;
                case 8: fromStack = eight;    break;
                case 9: fromStack = nine;     break;
            }

            switch(to){
                case 1: toStack = one;      break;
                case 2: toStack = two;      break;
                case 3: toStack = three;    break;
                case 4: toStack = four;     break;
                case 5: toStack = five;     break;
                case 6: toStack = six;      break;
                case 7: toStack = seven;    break;
                case 8: toStack = eight;    break;
                case 9: toStack = nine;     break;
            }

            char[] move = new char[howmany];
            for (int i = 0; i < howmany; i++){
                move[i] = fromStack.pop();
            }

            for (int i = 0; i < howmany; i++){
                toStack.push(move[howmany -1 -i]);
            }
        }

        scan.close();

        System.out.print(one.pop());
        System.out.print(two.pop());
        System.out.print(three.pop());
        System.out.print(four.pop());
        System.out.print(five.pop());
        System.out.print(six.pop());
        System.out.print(seven.pop());
        System.out.print(eight.pop());
        System.out.println(nine.pop());

    }
}
