import java.io.*;
import java.util.*;

//god I fucking hate parsing
public class Thirteen{
    static private String file;
    public static void main(String[] args) throws IOException{
       file = "Thirteen.txt";
       partOne();
       partTwo();
    }

    private static void partOne() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        int count = 1;
        int sum = 0;
        while(scan.ready()){
            if(compare(parse(scan.readLine()),parse(scan.readLine())) == 1){sum += count;}
            scan.readLine();
            count++;
        }

        System.out.println(sum);
        scan.close();
    }

    private static void partTwo() throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader(file));
        List<List<Object>> packets = new ArrayList<List<Object>>();
        while(scan.ready()){
            packets.add(parse(scan.readLine()));
            packets.add(parse(scan.readLine()));
            scan.readLine();
        }
        List<Object> div1 = parse("[[2]]");
        List<Object> div2 = parse("[[6]]");
        packets.add(div1);
        packets.add(div2);

        Collections.sort(packets, new Comparator<List<Object>>(){
            public int compare(List<Object> left, List<Object> right){
                return -Thirteen.compare(left, right);
            }
        });

        int prod = 1;
        for (int i = 0; i < packets.size(); i++){
            List<Object> current = packets.get(i);
            if (div1 == current || div2 == current){prod *= (i+1);}
        }

        System.out.println(prod);
        scan.close();
    }

    //1 for left < right, -1 for left > right, and 0 for unsure
    private static int compare(List<Object> left, List<Object> right){
        for (int i = 0; i < left.size(); i++){
            boolean lflag;
            boolean rflag; 
            int intl = -2;
            int intr = -2;
            List<Object> listl = null;
            List<Object> listr = null;
            try {
                intl = (int) left.get(i);
                lflag = true;
            }catch(ClassCastException e){
                listl = (List<Object>) left.get(i);
                lflag = false;
            }

            try {
                intr = (int) right.get(i);
                rflag = true;
            }catch(ClassCastException e){
                listr = (List<Object>) right.get(i);
                rflag = false;
            }catch(IndexOutOfBoundsException e){return -1;}
            int compare = -2;
            if (lflag && rflag){compare = compare(intl, intr);}
            if ((!lflag) && rflag){compare = compare(listl, intr);}
            if ((lflag) && (!rflag)){compare = compare(intl, listr);}
            if ((!lflag) && (!rflag)){compare = compare(listl, listr);}
            if (compare != 0){return compare;}
        }
        
        if (left.size() < right.size()){return 1;}
        return 0;
    }

    private static int compare(int left, int right){
        if (left == right){return 0;}
        return left < right ? 1: -1;
    }

    private static int compare(int left, List<Object> right){
        List<Object> leftarr = new ArrayList<Object>();
        leftarr.add(left);

        return compare(leftarr, right);
    }

    private static int compare(List<Object> left, int right){
        List<Object> rightarr = new ArrayList<Object>();
        rightarr.add(right);

        return compare(left, rightarr);
    }

    private static List<Object> parse(String s){
        List<Object> out = new ArrayList<Object>();

        for (int i = 1; i < s.length(); i++){
            if(s.charAt(i) == ']' || s.charAt(i) == ','){continue;}
            else if(s.charAt(i) == '['){
                int j = i+1;
                int count = 0;
                while(s.charAt(j) != ']' || count != 0){
                    if(s.charAt(j) == '[') {count++;}
                    if(s.charAt(j) == ']'){count--;}
                    j++;
                 }
                out.add(parse(s.substring(i,j+1)));
                i = j;
            }
            else{
                int j = i;
                while(s.charAt(j) != ',' && s.charAt(j) != ']' && s.charAt(j) != '['){j++;}
                out.add(Integer.valueOf(s.substring(i,j)));
                i=j;
            }
        }

        return out;
    }
}