import java.util.*;
import java.io.*;

class Researcher implements Comparable<Researcher> {
    public int startTime = this.startTime;
    public int leaveTime = this.leaveTime;

    public Researcher(int s, int idle) {
        this.startTime = s;
        this.leaveTime = s + idle;
    }

    public int compareTo(Researcher otherResearcher) {
        return this.startTime - otherResearcher.startTime;
    }
}

public class AssigningWorkstations {

    public static void main(String args[]) {
        FastIO f = new FastIO();
        int count = 0;  //num of unlocks saved
        int n = f.nextInt(); //num of researchers
        int m = f.nextInt(); //num of idle minutes

        PriorityQueue<Researcher> R = new PriorityQueue<Researcher>();
        PriorityQueue<Integer> W = new PriorityQueue<>(); //workstations in natural ordering

        for (int i = 0; i < n; i++) {
//            int start = f.nextInt();
//            int duration = f.nextInt();
//            Integer[] arr = new Integer[2];
//            arr[0] = start;
//            arr[1] = duration;
//            R.add(arr);
            R.add(new Researcher(f.nextInt(), f.nextInt()));
        }

        for (int i = 0; i < n; i++) {
            int currResearcherTime = R.peek().startTime;

            while(!W.isEmpty()) {
                if(W.peek() + m < currResearcherTime) {
                    // if workstation + idle time happens before currResearcherTime,
                    // we can't use this workstation,
                    // so we can poll since unlocking this workstation again
                    // is no different than unlocking a different workstation.
                    W.poll();
                }
                else { //once we find a suitable peek, break
                    break;
                }
            }
            if ((!W.isEmpty()) && ( (W.peek() <= R.peek().startTime) && (R.peek().startTime <= W.peek() + m) )) {
                //can use, no need for unlocking new workstation
                count++;
                W.poll();
            }
            W.add(R.poll().leaveTime); //add reused WorkStation back OR add new Workstation
        }
        f.println(count);
        f.close();
    }




    static class FastIO extends PrintWriter {
        BufferedReader br;
        StringTokenizer st;

        public FastIO() {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}