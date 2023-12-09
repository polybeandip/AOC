import java.io.*;
import java.util.*;

class Eight {
  public static void main(String[] args) throws IOException {
    String file = "eight.txt";
    if (args.length >= 1 && args[0].equals("-t"))
      file = "test.txt";

    BufferedReader scan = new BufferedReader(new FileReader(file));
    Map<String, String[]> map = new HashMap<String, String[]>();
    List<String> starts = new ArrayList<String>();

    String instruction = scan.readLine();
    scan.readLine();

    while (scan.ready()) {
      String[] line = scan.readLine().split(" = ");
      String[] options = line[1].split(", ");
      String[] value = {options[0].substring(1), options[1].substring(0, options[1].length() -1)};
      map.put(line[0], value);
      
      if (line[0].charAt(2) == 'A') starts.add(line[0]);
    }

    scan.close();

    int part1 = 0;
    String spot = "AAA";
    while (!spot.equals("ZZZ")) {
      int lr = instruction.charAt(part1 % instruction.length()) == 'L' ? 0 : 1;
      spot = map.get(spot)[lr];
      part1++;
    }
    System.out.println(part1);

    //assumes distance from A-node -> Z-node = distance from Z-node to Z-node
    List<Integer> dist = new ArrayList<Integer>();
    for (String s : starts) {
      spot = s;
      int count;
      for (count = 0; spot.charAt(2) != 'Z'; count++) {
        int lr = instruction.charAt(count % instruction.length()) == 'L' ? 0 : 1;
        spot = map.get(spot)[lr];
      }
      dist.add(count);
    }
    long part2 = lcm(dist, 0);
    System.out.println(part2);
  }

  private static long gcd(long x, long y) {
    if (y == 0) return x;
    return gcd(y, x % y);
  }

  private static long lcm (long x, long y) {return (x * y) / gcd(x,y);}

  private static long lcm(List<Integer> lst, int start) {
    if (lst.size() - start == 2) 
      return lcm(lst.get(start), lst.get(start + 1));
    return lcm(lst.get(start), lcm(lst, start + 1));
  }
}