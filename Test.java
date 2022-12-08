import java.util.*;

class Test{
    public static void main(String[] args){

        System.out.println(Arrays.toString("$ cd dbnsfp".split(" ")));
        System.out.println("[B] [Q] [R]     [D] [T]".matches("[A-Z, \\[, \\], ]+"));
        System.out.println(Arrays.toString("move 5 from 4 to 5".split("[a-z, A-Z]+")));
    }
}