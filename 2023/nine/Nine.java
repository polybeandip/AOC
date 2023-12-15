import java.io.*;
import java.util.*;

class Nine {
  public static void main(String[] args) throws IOException {
    String file = "nine.txt";
    if (args.length >= 1 && args[0].equals("-t")) file = "test.txt";
    BufferedReader scan = new BufferedReader(new FileReader(file));

    int part1 = 0;
    int part2 = 0;
    while (scan.ready()) {
      String[] split = scan.readLine().split(" ");
      List<Integer> seq = new ArrayList<Integer>();
      List<Integer> seqrev = new ArrayList<Integer>();
      for (String s : split) {
        int i = Integer.valueOf(s);
        seqrev.add(i); seq.add(i);
      }
      Collections.reverse(seqrev);
      part1 += next(seq);
      part2 += next(seqrev);
    }

    scan.close();

    System.out.println(part1);
    System.out.println(part2);
  }

  //requires: [seq.size()] >= 1
  private static int next(List<Integer> seq) {
    boolean check = true;
    List<Integer> out = new ArrayList<Integer>();
    for (int i = 1; i < seq.size(); i++) {
      int val = seq.get(i) - seq.get(i-1);
      out.add(val);
      check = check && (val == 0);
    }

    int delta = out.get(0);
    if (!check) delta = next(out);

    return seq.get(seq.size() - 1) + delta;
  }
}