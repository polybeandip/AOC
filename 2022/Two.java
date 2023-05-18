import java.io.*;

public class Two {
   public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Two.txt"));
        partOne(scan);
        scan = new BufferedReader(new FileReader("Two.txt"));
        partTwo(scan);
   }

   private static void partOne(BufferedReader scan) throws IOException{
        int score =0;
        while(scan.ready()){
            String[] input = scan.readLine().split(" ");
            String op = input[0], me = input[1];
            
            if (me.equals("X")){
                score +=1;
                if(op.equals("A")){
                    score += 3;
                }
                else if(op.equals("B")){
                    score += 0;
                }
                else if(op.equals("C")){
                    score += 6;
                }
                else{
                    System.out.println("fuck " + op);
                }
            }
           else if(me.equals("Y")){
                score +=2;
                if(op.equals("A")){
                    score += 6;
                }
                else if(op.equals("B")){
                    score += 3;
                }
                else if(op.equals("C")){
                    score += 0;
                }
                else{
                    System.out.println("fuck " + op);
                }
            }
            else if(me.equals("Z")){
                score +=3;
                if(op.equals("A")){
                    score += 0;
                }
                else if(op.equals("B")){
                    score += 6;
                }
                else if(op.equals("C")){
                    score += 3;
                }
                else{
                    System.out.println("fuck " + op);
                }
            }
            else{
                System.out.println("fuck");
            }
        }

        System.out.println(score);
   }

   private static void partTwo(BufferedReader scan) throws IOException{
        int score =0;
        while(scan.ready()){
            String[] input = scan.readLine().split(" ");
            String op = input[0], me = input[1];
            
            if (me.equals("X")){
                score +=0;
                if(op.equals("A")){
                    score += 3;
                }
                else if(op.equals("B")){
                    score += 1;
                }
                else if(op.equals("C")){
                    score += 2;
                }
                else{
                    System.out.println("fuck " + op);
                }
            }
           else if(me.equals("Y")){
                score +=3;
                if(op.equals("A")){
                    score += 1;
                }
                else if(op.equals("B")){
                    score += 2;
                }
                else if(op.equals("C")){
                    score += 3;
                }
                else{
                    System.out.println("fuck " + op);
                }
            }
            else if(me.equals("Z")){
                score +=6;
                if(op.equals("A")){
                    score += 2;
                }
                else if(op.equals("B")){
                    score += 3;
                }
                else if(op.equals("C")){
                    score += 1;
                }
                else{
                    System.out.println("fuck " + op);
                }
            }
            else{
                System.out.println("fuck");
            }
        }

        System.out.println(score);
   }

}
