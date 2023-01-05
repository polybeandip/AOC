import java.io.*;
import java.util.*;

public class Nineteen {
    private static int ore_cost;
    private static int clay_cost;
    private static int obi_cost;
    private static int obi_cost_clay;
    private static int geo_cost;
    private static int geo_cost_obi;
    private static int ore_max;

    private static Map<List<Integer>, Integer> state2geodes;

    public static void main(String[] args) throws IOException{
        BufferedReader scan = new BufferedReader(new FileReader("Nineteen.txt"));
        int score1 = 0;
        int score2 = 1;
        int count = 1;
        while(scan.ready()){
            String[] split = scan.readLine().split(" ");
            ore_cost = Integer.valueOf(split[6]); //in ore
            clay_cost = Integer.valueOf(split[12]); //in ore
            obi_cost = Integer.valueOf(split[18]); //in ore
            obi_cost_clay = Integer.valueOf(split[21]); //in clay
            geo_cost = Integer.valueOf(split[27]); //in ore
            geo_cost_obi = Integer.valueOf(split[30]); //in obi

            Integer[] ores = new Integer[]{ore_cost, clay_cost, obi_cost, geo_cost};
            ore_max = 0;
            for (int o: ores){if(ore_max < o){ore_max = o;}}

            state2geodes = new HashMap<List<Integer>, Integer>();
            int mine = mine(1, 0, 0, 0,0,0, 24);
            if(count < 4){
                int mine2 = mine(1, 0, 0, 0,0,0, 32);
                score2*= mine2;
            }
            score1 += mine*(count++);

            
        }
        scan.close();
        System.out.println(score1);
        System.out.println(score2);
    }

    /* 
    How many more geodes can I make with [time] amount of time remaining. I have [ore_robo], [clay_robo], [obi_robo] numbers of robots 
    for their associated resources and [ore], [clay], [obi] amount of resources.
    We assume that the geode robot immediately gives out ([time] -1) geodes upon its construction: i.e. we compute the amount of geodes it
    produces over its entire life cycle and add that to our count of geodes as soon as the bot is constructed
    */
    private static int mine(int ore_robo, int clay_robo, int obi_robo, int ore, int clay, int obi, int time){
        if(time == 0){return 0;}

        //if you already earn, in a second, more of a given resource than you can possibly spend in a second, then throw away the extra robots
        if(ore_robo > ore_max){ore_robo = ore_max;}
        if(clay_robo > obi_cost_clay){clay_robo = obi_cost_clay;}
        if(obi_robo > geo_cost_obi){obi_robo = geo_cost_obi;}
       
        //if you have more resources than you can possibly spend, then throw away the extra bits
        if (ore > (ore_max - ore_robo)*time + ore_robo){ore = (ore_max - ore_robo)*time + ore_robo;}
        if (clay > (obi_cost_clay - clay_robo)*time + clay_robo){clay = (obi_cost_clay - clay_robo)*time + clay_robo;}
        if (obi > (geo_cost_obi - obi_robo)*time + obi_robo){obi = (geo_cost_obi-obi_robo)*time + obi_robo;}

        List<Integer> state = new ArrayList<Integer>();
        state.add(ore_robo);
        state.add(clay_robo);
        state.add(obi_robo);
        state.add(ore);
        state.add(clay);
        state.add(obi);
        state.add(time);

        if(state2geodes.containsKey(state)){return state2geodes.get(state);}

        int ans = 0;
        //do nothing
        if(ore < ore_max || clay < obi_cost_clay || obi < geo_cost_obi){
            int maybe = mine(ore_robo, clay_robo, obi_robo, ore + ore_robo, clay +clay_robo, obi + obi_robo, time-1);
            if(ans < maybe){ans = maybe;}
        }

        //build a robot
        //ore
        if(ore >= ore_cost){
            int maybe = mine(ore_robo +1, clay_robo, obi_robo, ore - ore_cost + ore_robo, clay + clay_robo, obi + obi_robo, time -1);
            if(ans < maybe){ans = maybe;}
        }
        //clay
        if(ore >= clay_cost){
            int maybe = mine(ore_robo, clay_robo+1, obi_robo, ore -clay_cost + ore_robo, clay + clay_robo, obi + obi_robo, time-1);
            if(ans < maybe){ans = maybe;}
        }
        //obi
        if(ore >= obi_cost && clay >= obi_cost_clay){
            int maybe = mine(ore_robo, clay_robo, obi_robo+1, ore - obi_cost + ore_robo, clay - obi_cost_clay + clay_robo, obi + obi_robo, time-1);
            if(ans < maybe){ans = maybe;}
        }
        //geo
        if(ore >= geo_cost && obi >= geo_cost_obi){
            int maybe = (time-1) + mine(ore_robo, clay_robo, obi_robo, ore-geo_cost + ore_robo, clay + clay_robo, obi-geo_cost_obi + obi_robo, time-1);
            if(ans < maybe){ans = maybe;}
        }

        state2geodes.put(state, ans);
        return ans;
    }
}
