import java.io.*;

class Three {
  private static char[][] map;
  private static int width = 0;
  private static int height = 1;

  public static void main(String[] args) throws IOException {
    String file = "three.txt";
    if (args.length >= 1 && args[0].equals("-t"))
      file = "test.txt";

    partOne(file);
  }

  private static void partOne(String file) throws IOException {
    BufferedReader scan = new BufferedReader(new FileReader(file));
    width = scan.readLine().length();
    while (scan.ready()) {height++; scan.readLine();}
    scan.close();

    map = new char[height][width];
    scan = new BufferedReader(new FileReader(file));
    for (int i = 0; i < height; i++) {
      String line = scan.readLine();
      for (int j = 0; j < width; j++) {
        map[i][j] = line.charAt(j);
      }
    }
    scan.close();

    int sum = 0;
    for (int i = 0; i < height; i++) {
      StringBuilder num = new StringBuilder();
      boolean part = false;
      boolean inside = false;
      for (int j = 0; j < width; j++) {
        char c = map[i][j];
        if ((c - '0') <= 9 && (c -'0') >= 0) {
          num.append(c);
          part = part || is_part(i,j);
          inside = true;
        }
        else if (inside) {
          int b = Integer.valueOf(num.toString());
          //System.out.println(b);
          if (part) sum += b;
          inside = false;
          part = false;
          num = new StringBuilder();
        }
      }
    }

    System.out.println(sum);
  }
  
  private static boolean is_symbol(int i, int j) {
    if (i < 0 || i >= height || j < 0 || j >= width) return false;

    int ndist = map[i][j] - '0';
    return (ndist < 0 || ndist > 9) && map[i][j] != '.';
  }

  private static boolean is_part(int i, int j) {
    for (int dx = -1; dx < 2; dx++) {
      for (int dy = -1; dy < 2; dy++) {
        if (dx == 0 && dy == 0) continue;
        if (is_symbol(i+dx, j + dy)) return true;
      }
    }
    return false;
  }
}