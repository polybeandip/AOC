import java.io.*;
import java.util.*;

class Five {
  public static void main(String[] args) throws IOException {
    String file = "five.txt";
    if (args.length >= 1 && args[0].equals("-t"))
      file = "test.txt";

    BufferedReader scan = new BufferedReader(new FileReader(file));

    String[] seedline = scan.readLine().split(": ")[1].split(" ");
    long[] seeds = new long[seedline.length];
    List<List<Long>> seedsrange = new ArrayList<List<Long>>();
    for (int i = 0; i + 1 < seeds.length; i += 2) {
      seeds[i] = Long.valueOf(seedline[i]);
      seeds[i+1] = Long.valueOf(seedline[i+1]);
      seedsrange.add(Arrays.asList(seeds[i], seeds[i] + seeds[i+1] -1));
    }

    scan.readLine();

    while (scan.ready()) {
      scan.readLine();
      String line;
      boolean[] hit = new boolean[seeds.length];
      List<List<Long>> pushed = new ArrayList<List<Long>>();

      while((line = scan.readLine()) != null && line.length() > 0) {
        String[] linesplit = line.split(" ");
        long[] longsplit = new long[3];
        for (int i = 0; i < 3; i++) longsplit[i] = Long.valueOf(linesplit[i]);

        for (int i = 0; i < seeds.length; i++) {
          boolean range = 
            longsplit[1] <= seeds[i] && seeds[i] < longsplit[1] + longsplit[2];
          if (!hit[i] && range) {
            seeds[i] = longsplit[0] + (seeds[i] - longsplit[1]);
            hit[i] = true;
          }
        }
        
        List<List<Long>> next = new ArrayList<List<Long>>();
        for (List<Long> seedrange : seedsrange) {
          long a = seedrange.get(0), b = seedrange.get(1); 
          long c = longsplit[1], d = longsplit[1] + longsplit[2] - 1;
          if(a <= c && c <= b && b <= d) {
            pushed.add(Arrays.asList(longsplit[0], b - c + longsplit[0]));
            if (a < c) next.add(Arrays.asList(a, c-1));
          }
          else if (c <= a && b <= d)
            pushed.add(Arrays.asList(a - c + longsplit[0], b - c + longsplit[0]));
          else if (a <= c && d <= b) {
            pushed.add(Arrays.asList(longsplit[0], d - c + longsplit[0]));
            if (a < c) next.add(Arrays.asList(a, c - 1));
            if (d < b) next.add(Arrays.asList(d + 1, b));
          }
          else if (c <= a && a <= d && d <= b) {
            pushed.add(Arrays.asList(a - c + longsplit[0], d - c + longsplit[0]));
            if (d < b) next.add(Arrays.asList(d + 1, b));
          }
          else next.add(seedrange);
        }
        
        seedsrange = new ArrayList<List<Long>>(next);
      }

      seedsrange.addAll(pushed);
    }

    scan.close();

    long part1 = 0;
    for (int i = 0; i < seeds.length; i++)
      if (i == 0 || part1 > seeds[i]) part1 = seeds[i];

    long part2 = 0;
    for (int i = 0; i < seedsrange.size(); i++) {
      List<Long> seedrange = seedsrange.get(i);
      if (i == 0 || part2 > seedrange.get(0)) part2 = seedrange.get(0);
    }

    System.out.println(part1);
    System.out.println(part2);
  }
}