import java.io.*;
import java.util.*;

class Seven {
  public static void main(String[] args) throws IOException {
    String file = "seven.txt";
    if (args.length >= 1 && args[0].equals("-t"))
      file = "test.txt";

    BufferedReader scan = new BufferedReader(new FileReader(file));
    
    List<Hand> hands = new ArrayList<Hand>();
    List<Hand> jokers = new ArrayList<Hand>();
    while (scan.ready()) {
      String line = scan.readLine();
      hands.add(new Hand(line));
      jokers.add(new Hand(line, true));
    }

    scan.close();
    Collections.sort(hands);
    Collections.sort(jokers);

    int part1 = 0;
    int part2 = 0;
    for (int i = 1; i <= hands.size(); i++) {
      part1 += hands.get(i-1).bid * i;
      part2 += jokers.get(i-1).bid * i;
    }

    System.out.println(part1);
    System.out.println(part2);
  }

  private static class Hand implements Comparable<Hand> {
    private String weight = "23456789TJQKA";
    public int type;
    public String cards;
    public int bid;
    private boolean joker = false;

    Hand(String s) {
      String[] split = s.split(" ");
      this.cards = split[0];
      this.bid = Integer.valueOf(split[1]);
      this.type = type(cards);
    }

    Hand(String s, boolean joker) {
      this(s);
      this.joker = joker;
      if (joker) weight = "J23456789TQKA";
      this.type = type(cards);
    }

    public int compareTo(Hand h) {
      if (type != h.type) return type - h.type;

      for (int i = 0; i< cards.length(); i++) {
        int c = weight.indexOf(cards.charAt(i));
        int ch = weight.indexOf(h.cards.charAt(i));
        if (c != ch) return c - ch;
      }

      return 0;
    }

    private int type(String s) {
      Map<Character, Integer> counts = new HashMap<Character, Integer>();
      for (char c : s.toCharArray()) {
        if (!counts.containsKey(c))
          counts.put(c, 0);
        counts.put(c, 1 + counts.get(c));
      }

      Set<Character> keys = counts.keySet();

      if (keys.contains('J') && joker) {
        int max = 0;
        for (char k : weight.toCharArray()) {
          if (k == 'J') continue;
          int t = type(s.replaceAll("J","" + k));
          if (max < t) max = t;
        }

        return max;
      }

      int type = 1;

      if (keys.size() == 1) type = 7; 
      else if (keys.size() == 2) {
        for (char k : keys) {
          if (counts.get(k) == 4) type = 6;
          if (counts.get(k) == 3) type = 5;
        }
      }
      else if (keys.size() == 3) {
        for (char k : keys) {
          if (counts.get(k) == 3) type = 4;
          if (counts.get(k) == 2) type = 3;
        }
      }
      else if (keys.size() == 4) type = 2;

      return type;
    }

    public String toString() {return cards;}
  }
}