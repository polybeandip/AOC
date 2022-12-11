import java.io.*;
import java.util.*;

public class Eleven{
    static String file;
    static Map<Integer, Monkey> monkey = new HashMap<Integer, Monkey>();
    public static void main(String[] args) throws IOException{
        file = "Eleven.txt";
        //replace with 20 for part one
        partOneAndTwo(10000);
    }

    private static void partOneAndTwo(int rounds) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        int mnum = 0;
        while(scan.ready()){
            scan.readLine();
            String[] itemLine = scan.readLine().split("[\\,:]");
            String[] operationLine = scan.readLine().split(" ");
            String[] testLine = scan.readLine().split(" ");
            String[] trueLine = scan.readLine().split(" ");
            String[] falseLine = scan.readLine().split(" ");

            String op = operationLine[operationLine.length -1];
            int opnum;
            if (op.equals("old")){opnum = -1;}
            else{opnum = Integer.valueOf(op);}

            Monkey m = new Monkey(mnum, operationLine[operationLine.length -2], opnum, Integer.valueOf(testLine[testLine.length -1]), Integer.valueOf(trueLine[trueLine.length -1]), Integer.valueOf(falseLine[falseLine.length -1]));
            monkey.put(mnum,m);

            int last = -1;
            for (int i = itemLine.length -1; i >= 0; i--){
                try{
                    Integer.valueOf(itemLine[i].trim());
                }catch(Exception e){
                    last = i;
                    break;
                }
            }
            for (int i = last +1; i < itemLine.length; i++){
                m.add(Integer.valueOf(itemLine[i].trim()));
            }

            scan.readLine();
            mnum++;
        }

        scan.close();

        int[] inspection = new int[mnum];
        for (int counter = 0; counter < rounds; counter++){
            for (int i = 0; i < mnum; i++){
                Monkey m = monkey.get(i);
                inspection[i] += m.test();
            }
        }

        long max = 0;
        long max2 = 0;

        for (int i = 0; i < mnum; i++){
            if (max < inspection[i]){
                max2 = max;
                max = inspection[i];
            }
            else if(max2 < inspection[i]){max2 = inspection[i];}
        }
        System.out.println(max*max2);
        
    }

    private static class Monkey{
        int name;
        String operation;
        int opnum;
        int test;
        int mtrue;
        int mfalse;
        Queue<Long> items = new LinkedList<Long>();
        static int mod =1;

        Monkey(int name, String operation, int opnum, int test, int mtrue, int mfalse){
            this.name = name; this.operation = operation; this.opnum = opnum; this.test = test; this.mtrue = mtrue; this.mfalse = mfalse;
            mod *= test;
        }

        public void add(long item){items.add(item);}

        public long operation(long item){
            if (opnum == -1){return item * item;}
            else if (operation.equals("+")){return item + opnum;}
            else if (operation.equals("*")){return item * opnum;}
            throw new RuntimeException();
        }

        public int test(){
            int count = 0;
            while(items.size() != 0){
                count++;
                long item = items.poll();
                //the part one answer follows by replacing % mod with /3
                //item = operation(item) /3;
                item = operation(item) % mod;
                if (item % test == 0){
                    monkey.get(mtrue).add(item);
                }
                else{
                    monkey.get(mfalse).add(item);
                }
            }

            return count;
        }

    }
}
