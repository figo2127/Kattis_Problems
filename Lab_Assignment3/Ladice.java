import java.io.*;
import java.util.*;
import java.util.StringTokenizer;


public class Ladice {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        StringBuilder out = new StringBuilder(512);
        int N = fio.nextInt(); //num items
        int L = fio.nextInt(); //num drawers
        UnionFind storage = new UnionFind(L);
        for (int i = 0; i < N; i++) {
            int item = fio.nextInt() - 1;
            int drawer = fio.nextInt() - 1;
            storage.unify(item, drawer);
            int initialDrawer = storage.find(item);
            if (storage.sz[initialDrawer] > storage.numItems[initialDrawer]) {
                fio.println("LADICA");
                storage.numItems[initialDrawer]++;
            } else {
                fio.println("SMECE");
            }
        }
        fio.close();
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
                catch (IOException  e) {
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



class UnionFind {
    public int size; //num of elements in UnionFind
    public int[] sz; //tracks the size of each component
    public int[] id; //if id[i] = i  then i is a root node
    public int[] numItems;
    public int numComponents;

    public UnionFind(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("size <= 0 not allowed");
        this.size = numComponents = size;
        sz = new int[size];
        id = new int[size];
        numItems = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
            sz[i] = 1; //each component is originally size 1
        }
    }

    public int find(int p) {
        //find root of the component/set
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }
        //compress path leading back to the root.
        //Path Compression.
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    //unify components/sets containing elements 'p' and 'q'
    //drawer will be the root and item will be 'beneath it'
    public void unify(int p, int q) {
        int root1 = find(p);
        int root2 = find(q);
        //if these elements are NOT of the same group!
        if (root1 != root2) {
            //merge two components tgt
            //merge smaller group into larger one
            if (sz[root1] < sz[root2]) {
                sz[root2] += sz[root1];
                numItems[root2] += numItems[root1];
                id[root1] = root2;
            } else {
                sz[root1] += sz[root2];
                numItems[root1] += numItems[root2];
                id[root2] = root1;
            }
            numComponents--;
        }
    }
}


