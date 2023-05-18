import java.util.*;
import java.io.*;

public class Twentyone {

    private static Map<String, Monkey> monkeys1 = new HashMap<String, Monkey>();
    private static Map<String, Monkey> monkeys2 = new HashMap<String, Monkey>();
    public static void main(String[] args) throws IOException{
        String file = "Twentyone.txt";
        BufferedReader scan = new BufferedReader(new FileReader(file));
        while(scan.ready()){
            String[] split = scan.readLine().split(" ");
            if(split.length == 2){
                long val = Long.valueOf(split[1].trim());
                String name = split[0].trim();
                monkeys1.put(name, new Monkey(name,val));
                monkeys2.put(name, new Monkey(name,val));
            }
            else{
                String name = split[0].trim();
                String left = split[1].trim() + ":";
                String op = split[2].trim();
                String right = split[3].trim() + ":";

                Monkey m = new Monkey(name, left, right, op);
                monkeys1.put(name, m);
                monkeys2.put(name, m);
            }
        }
        scan.close();

        for(Map.Entry<String,Monkey> entry: monkeys2.entrySet()){
            Monkey m = entry.getValue();
            if(!m.combine){continue;}
            m.left = monkeys2.get(m.lname);
            m.right = monkeys2.get(m.rname);

            m.left.parent = m;
            m.right.parent = m;
        }

        scan = new BufferedReader(new FileReader(file));

        scan.close();

        partOne();
        partTwo();

    }

    private static void partOne(){
        System.out.println(monkeys1.get("root:").val());
    }

    private static void partTwo(){
        Monkey current = monkeys2.get("humn:");
        Monkey root = monkeys2.get("root:");
        Set<String> humnPath = new HashSet<String>();
        while(current.parent != root){
            humnPath.add(current.name);
            current = current.parent;
        }
        humnPath.add(current.name);


        Monkey other = root.left;
        if(current.name.equals(other.name)){other = root.right;}

        long val = other.val();
        while(!current.name.equals("humn:")){
            if(humnPath.contains(current.left.name)){
                String op = current.op;
                if(op.equals("*")){val = val/current.right.val();}
                else if(op.equals("+")){val = val - current.right.val();}
                else if(op.equals("-")){val = val + current.right.val();}
                else{val = val*current.right.val();} 

                current = current.left;
            }
            else{
                String op = current.op;
                if(op.equals("*")){val = val/current.left.val();}
                else if(op.equals("+")){val = val - current.left.val();}
                else if(op.equals("-")){val = current.left.val() - val;}
                else{val = current.left.val()/val;} 

                current = current.right;
            }
        }

        System.out.println(val);
    }

    private static class Monkey{
        String name;
        long val;

        boolean combine;
        String op;
        public Monkey left;
        public String lname;
        public Monkey right;
        public String rname;

        public Monkey parent;

        public Monkey(String name, long val){this.name = name; this.val = val; combine = false;}
        public Monkey(String name, String left, String right, String op){
            this.name = name;
            this.lname = left; this.rname = right;
            //this.left = monkeys.get(left); this.right = monkeys.get(right); 
            this.op = op; this.combine = true;
        }

        public long val(){
            if(!combine){return val;}

            if(op.equals("+")){val = left.val() + right.val();}
            if(op.equals("*")){val =  left.val() * right.val();}
            if(op.equals("/")){val = left.val()/right.val();}
            if (op.equals("-")){val = left.val() - right.val();}

            combine = false;
            return val;
        }

        public String toString(){return name;}
    }

}
