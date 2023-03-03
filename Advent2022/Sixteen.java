import java.util.*;
import java.io.*;

public class Sixteen {
    private static Valve first = null;

    //only for the memoization
    private static int totalNodes = 0;
    private static int totalFlowNodeSets = 1;
    private static int totalTime = 30 + 1; //1 more than the total amount of time we have
    private static int[] scores;

    public static void main(String[] args) throws IOException{
        String file ="Sixteen.txt";
        parse(file);
        partOne();
        partTwo();
    }

    private static void partOne(){
        scores = new int[3*totalNodes*totalFlowNodeSets*totalTime];
        Arrays.fill(scores,-1);
        System.out.println(release(first, 30, new ArrayList<Valve>(), 0));
    }

    private static void partTwo(){
        List<Valve> on = new ArrayList<Valve>();
        scores = new int[6*totalNodes*totalFlowNodeSets*totalTime];
        Arrays.fill(scores,-1);
        System.out.println(release(first, 26, on, 1));
    }

    //In [time] seconds, how many points can player [player] score, from Valve [current], given that the valves [On] are on?
    private static int release(Valve current, int time, List<Valve> on, int player){
        if(time == 0){
            if(player == 1){return release(first, 26, on, 0);}
            else{return 0;}
        }

        int key = 0;
        for (Valve isOn: on){
            key += isOn.flowCount;
        }
        key = 2*current.count*totalTime*totalFlowNodeSets + time*2*totalFlowNodeSets + 2*key + player;
        if(scores[key] != -1){return scores[key];} 

        int maxScore = 0; 
        for (Valve n: current.neighbours){
            int maybe = release(n, time -1, on,player);
            if(maxScore < maybe){maxScore = maybe;}
        }

        if(!on.contains(current)){
            List<Valve> mayhaps = new ArrayList<Valve>(on);
            mayhaps.add(current);
            int maybe = (time -1)*current.flow + release(current, time -1,mayhaps, player);
            if(maxScore < maybe){maxScore = maybe;
            }
        }

        scores[key] = maxScore;
        return maxScore;
    }


    private static void parse(String file) throws IOException{
        Map<String, Valve> storage = new HashMap<String,Valve>();
        BufferedReader scan = new BufferedReader(new FileReader(file));
        while(scan.ready()){
            String[] breakup = scan.readLine().split("[\\,, ,=,;]");
            int flow = Integer.valueOf(breakup[5]);
            Valve v = new Valve(breakup[1], flow);
            storage.put(breakup[1],v);
            if(breakup[1].equals("AA")){first = v;}
            totalNodes++;
            v.count = totalNodes-1;
            if(flow > 0){totalFlowNodeSets*=2;v.flowCount = totalFlowNodeSets/2;}
        }
        scan.close();

        scan = new BufferedReader(new FileReader(file));
        while(scan.ready()){
            String[] breakup = scan.readLine().split("[\\,, ,=,;]");
            Valve current = storage.get(breakup[1]);
            for(int i = breakup.length -1; i >=0; i--){
                if(breakup[i].equals("")){continue;}
                if(breakup[i].equals("valves") || breakup[i].equals("valve")){break;}
                Valve n = storage.get(breakup[i]);
                current.add(n);
            }
        }
        scan.close();
    }

    private static class Valve{
        public String name;
        public List<Valve> neighbours;
        public int flow;

        //only for making the key
        public int flowCount;
        public int count;

        Valve(String name, int flow){
            this.name = name; 
            this.flow = flow; 
            neighbours = new ArrayList<Valve>();

        }

        public String toString(){return name;}
        public boolean add(Valve v){return neighbours.add(v);}
    }
}

