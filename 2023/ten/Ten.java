import java.io.*;
import java.util.*;

class Ten {
  private static char[][] map;
  private static int width = 0;
  private static int height = 1; 
  private static List<List<Integer>> loop;

  public static void main(String[] args) throws IOException {
    String file = "ten.txt";
    if (args.length >= 1 && args[0].equals("-t")) file = "test.txt";

    BufferedReader scan = new BufferedReader(new FileReader(file));
    width = scan.readLine().length();
    while (scan.ready()) {height++; scan.readLine();}
    scan.close();

    scan = new BufferedReader(new FileReader(file));
    map = new char[height][width];
    int sh = 0, sw = 0;
    for (int h = 0; h < height; h++) {
      map[h] = scan.readLine().replaceAll("\\s+", "").toCharArray();
      for (int w = 0; w < map[h].length; w++)
        if (map[h][w] == 'S') {sh = h; sw = w;}
    }
    scan.close();

    lenloop(sw, sh);
    int part1 = loop.size()/2 + 1;
    System.out.println(part1);
    
    //pick's theorem + shoelace formula
    int b = loop.size(); 
    int area2 = 0;
    for (int i = 0; i < b; i++) {
      List<Integer> p1 = loop.get(i);
      List<Integer> p2 = loop.get((i+1) % b);
      area2 += p1.get(0) * p2.get(1) - p1.get(1) * p2.get(0);
    }
    int part2 = (area2 - b)/2 + 1; 
    System.out.println(part2);
  }

  private static void lenloop(int sw, int sh) {
    boolean check = 
      go(sw - 1, sh, sw, sh) || 
      go(sw + 1, sh, sw, sh) || 
      go(sw, sh - 1, sw, sh) || 
      go(sw, sh + 1, sw, sh);

    if (!check) System.out.println("Part 1 FAILED");
  }

  private static boolean go(int w, int h, int pw, int ph) {
    int sw = pw, sh = ph;
    loop = new ArrayList<List<Integer>>();
    while (w != sw || h != sh) {
      boolean wall = false;
      loop.add(Arrays.asList(ph, pw));

      //where to go next?
      int ow = w, oh = h;
      if (w < 0 || w > width -1 || h < 0 || h > height - 1) wall = true;
      else if (map[h][w] == '|' && h == ph + 1) h++;
      else if (map[h][w] == '|' && h == ph - 1) h--;
      else if (map[h][w] == '-' && w == pw + 1) w++;
      else if (map[h][w] == '-' && w == pw - 1) w--;
      else if (map[h][w] == 'L' && h == ph + 1) w++;
      else if (map[h][w] == 'L' && w == pw - 1) h--;
      else if (map[h][w] == 'J' && h == ph + 1) w--;
      else if (map[h][w] == 'J' && w == pw + 1) h--;
      else if (map[h][w] == '7' && h == ph - 1) w--;
      else if (map[h][w] == '7' && w == pw + 1) h++;
      else if (map[h][w] == 'F' && h == ph - 1) w++;
      else if (map[h][w] == 'F' && w == pw - 1) h++;
      else wall = true;

      if (!wall) {
        pw = ow; 
        ph = oh;
      }
      else return false;
    }

    return true;
  }
}