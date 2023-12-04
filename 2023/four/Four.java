import java.io.*;
import java.util.*;

public class Four {
  public static void main(String[] args) throws IOException {
    String file = "four.txt";
    if (args.length >= 1 && args[0].equals("-t")) file = "test.txt";

    int total = 0;
    BufferedReader scan = new BufferedReader(new FileReader(file)); 
    while (scan.ready()) {scan.readLine();  total++;}
    scan.close();
    
    scan = new BufferedReader(new FileReader(file)); 
    int[] counts = new int[total+1];
    Arrays.fill(counts, 1);

    int part1 = 0;
    while (scan.ready()) {
      String[] colon = scan.readLine().split(":");
      int start = 0;
      int i; 
      for (i = 0; i < colon[0].length(); i++) {
        char c = colon[0].charAt(i);
        if (c - '0' >= 0 && c - '0' <= 9 && start == 0) start = i;
        if (c == ':') break;
      }
      int card = Integer.valueOf(colon[0].substring(start, i));
      int dcard = 1;

      String[] nums = colon[1].substring(1).split(" ");
      int score = 0;
      Set<Integer> winners = new HashSet<Integer>();

      for (i = 0; ; i++) {
        if (nums[i].equals("")) continue;
        if (!nums[i].equals("|")) 
          winners.add(Integer.valueOf(nums[i]));
        else {i++; break;}
      }

      for ( ; i < nums.length; i++) {
        if (nums[i].equals("")) continue;
        if (winners.contains(Integer.valueOf(nums[i]))) {
          score = score == 0 ? 1 : 2 * score;
          if (card + dcard <= total) {
            counts[card + dcard] += counts[card];
            dcard++;
          }
        }
      }

      part1 += score;
    }

    scan.close();

    int part2 = 0;
    for (int i = 1; i <= total; i++) part2 += counts[i];

    System.out.println(part1);
    System.out.println(part2);
  }
}
