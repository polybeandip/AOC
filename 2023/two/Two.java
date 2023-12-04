import java.io.*;

class Two {
  private static int red = 12;
  private static int green = 13;
  private static int blue = 14;
  public static void main(String[] args) throws IOException {
    String file = "two.txt";
    if (args.length >= 1 && args[0].equals("-t")) file = "test.txt";
    BufferedReader scan = new BufferedReader(new FileReader(file)); 

    int part1 = 0, part2 = 0;
    while (scan.ready()) {
      String line = scan.readLine();
      String[] split = line.split(":");
      int id = Integer.valueOf(split[0].substring(5));

      boolean valid =  true;
      int max_r = 0, max_b = 0, max_g = 0;
      for (String piece : split[1].split(";")) {
        for (String p : piece.split(",")) {
          if (p.charAt(3) == 'b') {
            int b = Integer.valueOf(p.substring(1,2));
            if (valid && b > blue) valid = false;
            if (max_b < b) max_b = b;
          }
          else if (p.charAt(4) == 'b') {
            int b = Integer.valueOf(p.substring(1,3));
            if (valid && b > blue) valid = false;
            if (max_b < b) max_b = b;
          }
          else if (p.charAt(3) == 'r') {
            int r = Integer.valueOf(p.substring(1,2));
            if (valid && r > red) valid = false;
            if (max_r < r) max_r = r;
          }
          else if (p.charAt(4) == 'r' && p.charAt(6) == 'd') {
            int r = Integer.valueOf(p.substring(1,3));
            if (valid && r > red) valid = false;
            if (max_r < r) max_r = r;
          }
          else if (p.charAt(3) == 'g') {
            int g = Integer.valueOf(p.substring(1,2));
            if (valid && g > green) valid = false;
            if (max_g < g) max_g = g;
          }
          else if (p.charAt(4) == 'g') {
            int g = Integer.valueOf(p.substring(1,3));
            if (valid && g > green) valid = false;
            if (max_g < g) max_g = g;
          }
        }
      }

      int power = max_r * max_g * max_b;
      part1 += valid ? id : 0;
      part2 += power;
    }

    scan.close();

    System.out.println(part1);
    System.out.println(part2);
  }
}