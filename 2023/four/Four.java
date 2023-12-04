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
    int[] matches = new int[total+1];
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

      String[] nums = colon[1].substring(1).split(" ");
      boolean check = false;
      int score = 0;
      int count = 0;
      Set<Integer> winners = new HashSet<Integer>();

      for (i = 0; i < nums.length; i++) {
        if (check) {
          if (nums[i].equals("")) continue;
          int num = Integer.valueOf(nums[i]);
          if (winners.contains(num)) {
            score = score == 0 ? 1 : 2 * score;
            count++;
          }
        }
        else {
          if (!nums[i].equals("") && !nums[i].equals("|")) 
            winners.add(Integer.valueOf(nums[i]));
          if (nums[i].equals("|")) check = true;
        }
      }

      matches[card] = count;
      part1 += score;
      check = false;
    }

    scan.close();

    for (int i = 1; i <= total; i++)
      for (int j = i+1; j <= i + matches[i] && j <= total; j++) 
        counts[j] += counts[i];

    int part2 = 0;
    for (int i = 1; i <= total; i++) part2 += counts[i];

    System.out.println(part1);
    System.out.println(part2);
  }
}
