import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.lang.StringBuilder;

class Runner {
    String name;
    double t1;
    double t2;

    public Runner(String[] in) {
        this.name = in[0];
        this.t1 = Double.parseDouble(in[1]);
        this.t2 = Double.parseDouble(in[2]);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Runner) obj).name.equals(this.name);
    }
}

class Team {
    Runner[] runners;

    public Team() {
        this.runners = new Runner[4]; // create an empty 4 Runner array
    }

    public double time() {
        return runners[0].t1 + runners[1].t2 + runners[2].t2 + runners[3].t2;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder(512);
        // to build the official output
        out.append(this.time()).append("\n");
        out.append(runners[0]).append("\n");
        out.append(runners[1]).append("\n");
        out.append(runners[2]).append("\n");
        out.append(runners[3]).append("\n");
        return out.toString();
    }
}

public class BestRelayTeam {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // primitive Scanner
        int n = Integer.parseInt(br.readLine());

        // best first leg timings
        PriorityQueue<Runner> t1 = new PriorityQueue<Runner>(n, (x,y) -> Double.compare(x.t1, y.t1));
        // best subsequent leg timings
        PriorityQueue<Runner> t2 = new PriorityQueue<Runner>(n, (x,y) -> Double.compare(x.t2, y.t2));

        Team[] teams = new Team[4];
        Runner[] t2runners = new Runner[4]; // create an array of empty runners

        for(int i = 0; i < 4; i++) {
            teams[i] = new Team();
        }
        for(int i = 0; i < n; i++) {
            Runner temp = new Runner(br.readLine().split(" "));
            // split the words into substrings
            // System.out.println(temp);
            // add the names into the PQ
            t1.add(temp);
            t2.add(temp);
        }
        // get the TOP FOUR FASTEST teams
        for(int i = 0; i < 4; i++) {
            // add the t1 fastest runners into the first leg runner of teams
            teams[i].runners[0] = t1.poll();
            t2runners[i] = t2.poll(); // add the timings from the other legs
            //            System.out.println(runners[i]);
        }
        // NOW CHECK FOR DUPLICATE NAMES
        for(int i = 0; i < 4; i++) {
            int j = 0;
            int k = 1;
            while(k < 4) {
                if(!teams[i].runners[0].equals(t2runners[j])) { // if bolt vs bolt, skip we can't add duplicate runners
                    teams[i].runners[k++] = t2runners[j];
                }
                j++;
            }
        }
        PriorityQueue<Team> best = new PriorityQueue<Team>(4, (x,y) -> Double.compare(x.time(), y.time()));
        for(Team t : teams) {
            best.add(t); // add all the teams into the PQ
        }
        System.out.print(best.poll()); // output the team with the best timing
        br.close(); 
    }
}