import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

class HumanCannonballRun {
    static double inf = 10000000;
    static int n;
    static int V;

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        double startCoor[] = new double[2];
        double endCoor[] = new double[2];
        startCoor[0] = fio.nextDouble();
        startCoor[1] = fio.nextDouble();
        endCoor[0] = fio.nextDouble();
        endCoor[1] = fio.nextDouble();
        n = fio.nextInt();

        double[][] adjmatrix = new double[n+2][n+2]; //adjacency matrix
        double[][] pts = new double[n+2][2]; //double 2d array to store the points

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 2; j++) {
                pts[i][j] = fio.nextDouble();
            }
        }
        pts[0] = startCoor;
        pts[n+1] = endCoor;

        //calculate time taken to run straight
        double t = calcDist(pts[0], pts[n+1])/5;
        double inf = t + 1;

        // Initialize empty adjacency matrix.
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(adjmatrix[i], inf);
            adjmatrix[i][i] = 0;
        }

        //populate values into adjacency matrix
        for (int i = 1; i < n + 2; i++) {
            //insert time taken to run from start to everywhere else
            adjmatrix[0][i] = calcDist(pts[0], pts[i])/5;
        }
        //time taken for..
        //cannon launch + running from x to y: 2 + Math.abs(calcDist(pts[x], pts[y]) - 50)/5
        //use Math.abs to take into account cases of undershot/overshotting
        for (int i = 1; i < n + 1; i++) { //we do not have to compute travelling from the endCoor(trivial)
            for (int j = 1; j < n + 2; j++) {
                adjmatrix[i][j] = Math.min(2 + Math.abs(calcDist(pts[i], pts[j]) - 50)/5,
                                            calcDist(pts[i], pts[j])/5);
            }
        }
        //djikstra's algorithm to find sssp
        fio.println(dijkstra(adjmatrix, 0));
        fio.close();

    }



    static double dijkstra(double graph[][], int src)
    {
        V = n+2;

        double times[] = new double[V];

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            times[i] = Double.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        times[src] = 0.0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(times, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;


            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && times[u] != Integer.MAX_VALUE && times[u] + graph[u][v] < times[v]) {
                    times[v] = times[u] + graph[u][v];
                }
            }
        }
        return times[V-1];

    }

    static int minDistance(double times[], Boolean sptSet[])
    {
        // Initialize min value
        double min = Double.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && times[v] <= min) {
                min = times[v];
                min_index = v;
            }

        return min_index;
    }




    static double calcDist(double[] a, double[] b) {
        return Math.sqrt(Math.pow(a[0]-b[0], 2) + Math.pow(a[1]-b[1], 2));
    }

    static class FastIO extends PrintWriter {
        BufferedReader br;
        StringTokenizer st;

        public FastIO() {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
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
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}