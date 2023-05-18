import java.util.*;
import java.io.*;

public class Seventeen {
    private static String jet;
    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Seventeen.txt"));
        StringBuilder jetbuilder = new StringBuilder();
        while(scan.ready()){jetbuilder.append(scan.readLine());}
        jet = jetbuilder.toString();
        scan.close();

        partOne();
        partTwo();
    }

    private static void partOne(){
        int count = 0;
        double peak = 0;
        Set<List<Double>> stopped = new HashSet<List<Double>>();
        for(int i = 0; i < 2022; i++) {
            Rock rock = new Rock(i, peak);
            while(true){
                int delta = jet.charAt(count++ % jet.length()) == '>' ? 1 : -1;
                rock.shift(delta,stopped);
                if(!rock.down(stopped)){
                    stopped.addAll(rock.pieces);
                    if(peak < rock.high +1){peak = rock.high + 1;}
                    break;
                }
            }
        }

        System.out.println((int) peak);
    }

    private static void partTwo(){
        //maps the state to the pair of peak and rock number
        Map<List<Object>, List<Double>> state2peakrock = new HashMap<List<Object>,List<Double>>();

        int count = 0;
        double peak = 0;
        Set<List<Double>> stopped = new HashSet<List<Double>>();
        double i = 0;
        double max = 1000000000000.0;

        //for the skip
        double extra = 0;

        while(i < max){
            Rock rock = new Rock(i, peak);

            //makes the state: first the rock type, then the rock formation 50 rows below, then the pointer in the jets string
            List<Object> state = new ArrayList<Object>();
            state.add(i % 5); //rock type

            Set<List<Double>> lower = new HashSet<List<Double>>(); //rocks below the current rock
            for (double y = peak; y >= peak - 50 && y >=0; y--) {
                for (double x = 0; x < 7; x++){
                    List<Double> coord = new ArrayList<Double>();
                    coord.add(x); coord.add(y);
                    if (stopped.contains(coord)){
                        coord.set(1,y-peak);
                        lower.add(coord);
                    }
                }
            }
            state.add(lower);
            state.add(count); //pointer in the jets string

            if(i > 2022){
                if (state2peakrock.containsKey(state)){
                    double di = i - state2peakrock.get(state).get(1);
                    double dy = peak - state2peakrock.get(state).get(0);
                    double loop = Math.floor((max - i)/di);
                    extra += loop*dy;
                    i = di*loop + i;
                }
            }
            else{
                List<Double> peak_rock = new ArrayList<Double>();
                peak_rock.add(peak); //peak
                peak_rock.add(i); //number of rocks dropped: i.e. rock number
                state2peakrock.put(state, peak_rock);
            }
        

            while(true){
                int delta = jet.charAt(count) == '>' ? 1 : -1;
                count = ++count % jet.length();
                rock.shift(delta,stopped);
                if(!rock.down(stopped)){
                    stopped.addAll(rock.pieces);
                    if(peak < rock.high +1){peak = rock.high + 1;}
                    break;
                }
            }

            i++;
        }
        StringBuilder out = new StringBuilder(((Double) (extra + peak)).toString());
        out.delete(1,2); out.delete(out.length() - 3,out.length()); //super janky way to not print in scientific notation
        System.out.println(out);
    }

    private static class Rock{
        public List<List<Double>> pieces = new ArrayList<List<Double>>();
        public double high;

        public void add(List<Double> rockpart){pieces.add(rockpart);}

        Rock(double i, double highesty){
            if (i %5 == 0){
                add(Arrays.asList((double) 2, (double) highesty + 3));
                add(Arrays.asList((double) 3, (double) highesty + 3));
                add(Arrays.asList((double) 4, (double) highesty + 3));
                add(Arrays.asList((double) 5, (double) highesty + 3));

                high = highesty + 3;
            }
            else if(i % 5 == 1){
                add(Arrays.asList((double) 3,(double) highesty + 3));
                add(Arrays.asList((double) 3,(double) highesty + 4));
                add(Arrays.asList((double) 2,(double) highesty + 4));
                add(Arrays.asList((double) 4,(double) highesty + 4));
                add(Arrays.asList((double) 3,(double) highesty + 5));

                high = highesty + 5;
            }
            else if(i % 5 == 2){
                add(Arrays.asList((double) 2,(double) highesty + 3));
                add(Arrays.asList((double) 3,(double) highesty + 3));
                add(Arrays.asList((double) 4,(double) highesty + 3));
                add(Arrays.asList((double) 4,(double) highesty + 4));
                add(Arrays.asList((double) 4,(double) highesty + 5));

                high = highesty + 5;
            }
            else if(i % 5 == 3){
                add(Arrays.asList((double) 2,(double) highesty+3));
                add(Arrays.asList((double) 2,(double) highesty+4));
                add(Arrays.asList((double) 2,(double) highesty+5));
                add(Arrays.asList((double) 2,(double) highesty+6));

                high = highesty + 6;
            }
            else if(i % 5 == 4){
                add(Arrays.asList((double) 2,(double) highesty+3));
                add(Arrays.asList((double) 3,(double) highesty+3));
                add(Arrays.asList((double) 2,(double) highesty+4));
                add(Arrays.asList((double) 3,(double) highesty+4));

                high = highesty + 4;
            }
        }

        public boolean shift(double delta, Set<List<Double>> stopped){
            for (List<Double> p: pieces){
                if (p.get(0)+delta < 0 || p.get(0) + delta > 6){return false;}
                p.set(0,p.get(0) + delta);
                if(stopped.contains(p)){
                    p.set(0,p.get(0) - delta);
                    return false;
                }
                p.set(0,p.get(0) - delta);
            }

            for(List<Double> p: pieces){
                p.set(0,p.get(0)+delta);
            }
            return true;
        }

        public boolean down(Set<List<Double>> stopped){
            for (List<Double> p: pieces){
                if (p.get(1)-1 < 0){return false;}
                p.set(1,p.get(1)-1);
                if(stopped.contains(p)){
                    p.set(1,p.get(1) + 1);
                    return false;
                }
                p.set(1,p.get(1) + 1);
            }

            for(List<Double> p: pieces){
                p.set(1,p.get(1)-1);
            }
            high--;
            return true;
        }
    }
}


