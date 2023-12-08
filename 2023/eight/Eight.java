import java.io.*;
import java.util.*;

class Eight {
  public static void main(String[] args) throws IOException {
    String file = "eight.txt";
    if (args.length >= 1 && args[0].equals("-t"))
      file = "test.txt";

    BufferedReader scan = new BufferedReader(new FileReader(file));
    Map<String, String[]> map = new HashMap<String, String[]>();
    List<String> current = new ArrayList<String>();

    String instruction = scan.readLine();
    scan.readLine();

    while (scan.ready()) {
      String[] line = scan.readLine().split(" = ");
      String[] options = line[1].split(", ");
      String[] value = {options[0].substring(1), options[1].substring(0, options[1].length() -1)};
      map.put(line[0], value);
      
      if (line[0].charAt(2) == 'A') current.add(line[0]);
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

    int part2 = 0; //TOO SLOW
    while (!allZ(current)) {
      for (int i = 0; i < current.size(); i++) {
        int lr = instruction.charAt(part2 % instruction.length()) == 'L' ? 0 : 1;
        current.set(i,map.get(current.get(i))[lr]);
      }
      part2++;
    }
    System.out.println(part2);
  }

  private static boolean allZ(List<String> list) {
    boolean check = true;
    for (String s : list) 
      if (s.charAt(2) != 'Z') {check = false; break;}

    return check;
  }
}