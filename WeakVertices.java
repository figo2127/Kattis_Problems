import java.io.*;
import java.util.StringTokenizer;

public class WeakVertices {
    public static void main(String[] args) {
//        int V = 0; //total number of vertices

        FastIO fio = new FastIO();
        while(true) {
            int n = fio.nextInt();
            if (n == -1) {
                break;
            }
            int[][] adjMatrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int cur = fio.nextInt();
                    if (cur == 1) {
                        adjMatrix[i][j] = 1;
                    }
                    else {
                        adjMatrix[i][j] = 0;
                    }
                }
            }

            boolean firstToPrint = true;

            for (int i = 0; i < n; i++) {
                boolean triangle = false;
                for (int j = 0; j < n; j++) {
                    if (adjMatrix[i][j] == 1) { //we found an edge, now search for edges that can form triangle
                        for (int k = j; k < n; k++) {
                            if (adjMatrix[i][k] == 1 && adjMatrix[j][k] == 1) {
                                triangle = true;
                                break;
                            }
                        }
                    }
                    if (triangle) {
                        break;
                    }
                }

                //end of a row, we print the weak vertices here
                if (!triangle) {
                    //i also denotes the vertex number here
                    fio.print(i + " ");
                }
            }
            fio.println();
        }
        fio.close();



//        ArrayList<ArrayList<Integer>> adjList = new
//                ArrayList<ArrayList<Integer>>();

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

//class Vertex {
//    public int v1;
//    public int v2;
//
//    Vertex(int v1, int v2) {
//        this.v1 = v1;
//        this.v2 = v2;
//    }
//}
class Edge {
    public int w; //weight
    public int u; //the vertex which the edge is pointing from
    public int v; //the vertex which the edge is pointing to

    public Edge(int w, int u, int v) {
        this.w = w;
        this.u = u;
        this.v = v;
    }
}