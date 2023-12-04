import java.io.*;
import java.util.*;

class Three {
  private static char[][] map;
  private static int width = 0;
  private static int height = 1;
  private static Map<List<Integer>, List<Integer>> gears = new HashMap<List<Integer>, List<Integer>>();

  public static void main(String[] args) throws IOException {
    String file = "three.txt";
    if (args.length >= 1 && args[0].equals("-t")) file = "test.txt";
    BufferedReader scan = new BufferedReader(new FileReader(file));

    width = scan.readLine().length();
    while (scan.ready()) {height++; scan.readLine();}
    scan.close();

    map = new char[height][width+1];
    scan = new BufferedReader(new FileReader(file));
    for (int i = 0; i < height; i++) {
      String line = scan.readLine();
      for (int j = 0; j < width; j++) map[i][j] = line.charAt(j);
      map[i][width] = '\n';
    }
    scan.close();

    int sum = 0;
    for (int i = 0; i < height; i++) {
      StringBuilder num = new StringBuilder();
      boolean part = false;
      boolean inside = false;
      Set<List<Integer>> symbols = new HashSet<List<Integer>>();
      for (int j = 0; j <= width; j++) {
        char c = map[i][j];
        if ((c - '0') <= 9 && (c -'0') >= 0) {
          num.append(c);
          part = part || is_part(i,j, symbols);
          inside = true;
        }
        else if (inside) {
          int n = Integer.valueOf(num.toString());
          if (part) sum += n;

          for (List<Integer> coord : symbols) {
            if (!gears.containsKey(coord)) 
              gears.put(coord, new ArrayList<Integer>());
            gears.get(coord).add(n);
          }

          inside = false;
          part = false;
          num = new StringBuilder();
          symbols = new HashSet<List<Integer>>();
        }
      }
    }

    int ratio = 0;
    for (Map.Entry<List<Integer>, List<Integer>> entry : gears.entrySet()) {
      List<Integer> gear = entry.getValue();
      if (gear.size() == 2) ratio += gear.get(0) * gear.get(1);
    }

    System.out.println(sum);
    System.out.println(ratio);
  }

  private static boolean is_symbol(int i, int j) {
    if (i < 0 || i >= height || j < 0 || j >= width) return false;
    int ndist = map[i][j] - '0';
    return (ndist < 0 || ndist > 9) && map[i][j] != '.';
  }

  private static boolean is_part(int i, int j, Set<List<Integer>> symbols) {
    boolean out = false;

    for (int dx = -1; dx < 2; dx++) {
      for (int dy = -1; dy < 2; dy++) {
        if (dx == 0 && dy == 0) continue;
        if (is_symbol(i+dx, j + dy)) {
          out = true;
          if (map[i+dx][j+dy] == '*') symbols.add(Arrays.asList(i+dx, j + dy));
        }
      }
    }

    return out;
  }
}