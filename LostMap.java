import java.io.*;
import java.util.*;

class LostMap {
    static int n;
    static int[][] adjmatrix;

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        n = fio.nextInt();
        adjmatrix = new int[n][n];
        ArrayList<Edge> edgelist = new ArrayList<>();
        int numEdges = 0;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                int input = fio.nextInt();
                adjmatrix[x][y] = input;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) { //take half of the matrix
                int w = adjmatrix[i][j];
                edgelist.add(new Edge(i + 1, j + 1, w));
                numEdges++;
            }
        }
        Collections.sort(edgelist);
        UnionFind UF = new UnionFind(n + 1); // all V are disjoint sets at the beginning

        int mst_cost = 0;
        for (int a = 0; a < numEdges; a++) { // process all edges, O(E)
            Edge e = edgelist.get(a);
            int u = e.src, v = e.dest, w = e.weight; // note that we have re-ordered the integer triple
            if (!UF.isSameSet(u, v)) { // if no cycle
                mst_cost += w; // add weight w of e to MST
//                System.out.println("Adding edge: " + e + ", MST cost now = " + mst_cost);
                fio.println(u + " " + v);
                UF.unionSet(u, v); // link these two vertices
            }
            else {//cycle found!!!
//                System.out.println("Ignoring edge: " + e + ", MST cost now = " + mst_cost);
//                fio.println(u + " " + v);

            }
        }
        fio.close();

//        System.out.printf("Final MST cost %d\n", mst_cost);

    }
    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        // Comparator function used for
        // sorting edgesbased on their weight, then their number
        public int compareTo(Edge e) {
            if (!(this.weight == (e.weight))) {
                return this.weight - e.weight;
            } else if (!(this.src == (e.src))) {
                return this.src - e.src;
            } else {
                return this.dest - e.dest;
            }
        }
        public String toString() { return this.src + " " + this.dest  + " " + this.weight; }

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

    // Union-Find Disjoint Sets Library, using both path compression and union by rank heuristics
    static class UnionFind {
        public int[] p;
        public int[] rank;
        public int[] setSize;
        public int numSets;

        public UnionFind(int N) {
            p = new int[N];
            rank = new int[N];
            setSize = new int[N];
            numSets = N;
            for (int i = 0; i < N; i++) {
                p[i] = i;
                rank[i] = 0;
                setSize[i] = 1;
            }
        }

        public int findSet(int i) {
            if (p[i] == i) return i;
            else {
                p[i] = findSet(p[i]);
                return p[i];
            }
        }

        public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

        public void unionSet(int i, int j) {
            if (!isSameSet(i, j)) {
                numSets--;
                int x = findSet(i), y = findSet(j);
                // rank is used to keep the tree short
                if (rank[x] > rank[y]) {
                    p[y] = x;
                    setSize[x] = setSize[x] + setSize[y];
                }
                else {
                    p[x] = y;
                    setSize[y] = setSize[x] + setSize[y];
                    if (rank[x] == rank[y])
                        rank[y] = rank[y]+1;
                }
            }
        }

        public int numDisjointSets() { return numSets; }

        public int sizeOfSet(int i) { return setSize[findSet(i)]; }
    }
}

//public class KruskalDemo {
//    @SuppressWarnings("unchecked")
//    public static void main(String[] args) throws Exception {
//    /*
//    // Sample graph shown in lecture
//    5 7
//    1 2 4
//    1 3 4
//    2 3 2
//    1 4 6
//    3 4 8
//    1 5 6
//    4 5 9
//    */
//
//        // THE FIRST PART, DEMO OF USING EDGE LIST DATA STRUCTURE + SORTING EDGES
//        Scanner sc = new Scanner(System.in);
//        int V = sc.nextInt(), E = sc.nextInt();
//
//        // another graph data structure: EdgeList
//        ArrayList < IntegerTriple > EdgeList = new ArrayList < IntegerTriple >();
//        for (int i = 0; i < E; i++) { // simply populate this EdgeList
//            // we decrease index by 1 to change input to 0-based indexing
//            int u = sc.nextInt() - 1, v = sc.nextInt() - 1, w = sc.nextInt();
//            EdgeList.add(new IntegerTriple(w, u, v)); // we store this information as (w, u, v)
//        }
//
//        System.out.println("BEFORE SORTING");
//        for (int i = 0; i < E; i++)
//            System.out.println(EdgeList.get(i));
//        System.out.println("==============");
//
//        // sort by edge weight O(E log E)
//        Collections.sort(EdgeList);
//
//        System.out.println("AFTER  SORTING");
//        for (int i = 0; i < E; i++)
//            System.out.println(EdgeList.get(i));
//        System.out.println("==============");
//
//
//
//        // THE SECOND PART, THE MAIN LOOP OF KRUSKAL'S ALGORITHM
//        UnionFind UF = new UnionFind(V); // all V are disjoint sets at the beginning
//        int i, mst_cost = 0;
//        for (i = 0; i < E; i++) { // process all edges, O(E)
//            IntegerTriple e = EdgeList.get(i);
//            int u = e.second(), v = e.third(), w = e.first(); // note that we have re-ordered the integer triple
//            if (!UF.isSameSet(u, v)) { // if no cycle
//                mst_cost += w; // add weight w of e to MST
//                System.out.println("Adding   edge: " + e + ", MST cost now = " + mst_cost);
//                UF.unionSet(u, v); // link these two vertices
//            }
//            else
//                System.out.println("Ignoring edge: " + e + ", MST cost now = " + mst_cost);
//        }
//
//        System.out.printf("Final MST cost %d\n", mst_cost);
//    }
//}
//
//
//
//class IntegerTriple implements Comparable<IntegerTriple> {
//    public Integer _first, _second, _third;
//
//    public IntegerTriple(Integer f, Integer s, Integer t) {
//        _first = f;
//        _second = s;
//        _third = t;
//    }
//
//    public int compareTo(IntegerTriple o) {
//        if (!this.first().equals(o.first()))
//            return this.first() - o.first();
//        else if (!this.second().equals(o.second()))
//            return this.second() - o.second();
//        else
//            return this.third() - o.third();
//    }
//
//    Integer first() { return _first; }
//    Integer second() { return _second; }
//    Integer third() { return _third; }
//
//    public String toString() { return first() + " " + second() + " " + third(); }
//}



