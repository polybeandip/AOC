import java.io.*;
import java.lang.Math;

class Six {
  public static void main(String[] args) throws IOException {
    String file = "six.txt";
    if (args.length >= 1 && args[0].equals("-t"))
      file = "test.txt";

    BufferedReader scan = new BufferedReader(new FileReader(file));

    String[] timestr = scan.readLine().split(":\s*")[1].split("\s+");
    String[] distancestr = scan.readLine().split(":\s*")[1].split("\s+");

    scan.close();

    double T = Double.valueOf(String.join("", timestr));
    double D = Double.valueOf(String.join("", distancestr));
    int part2 = beatRecord(T, D);

    int part1 = 1;
    for (int i = 0; i < timestr.length; i++) {
      T = Double.valueOf(timestr[i]);
      D = Double.valueOf(distancestr[i]);
      part1 *= beatRecord(T, D);
    }

    System.out.println(part1);
    System.out.println(part2);
  }

  private static int beatRecord (double T, double D) {
      double t0 = Math.floor(0.5 * (T - Math.sqrt(T*T - 4*D))) + 1;
      double t1 = Math.ceil(0.5 * (T + Math.sqrt(T*T - 4*D))) - 1;

      if (t0 < 0) t0 = 0;
      if (t1 > T) t1 = T;

      return (int) (t1 - t0 + 1);
  }
}