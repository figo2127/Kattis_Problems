import java.io.*;
import java.util.StringTokenizer;

class Islands {
    static int r;
    static int c;
    static char[][] arr;
    static boolean[][] visited;
    static int count;

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        r = fio.nextInt();
        c = fio.nextInt();
        arr = new char[r][c];
        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            String s = fio.nextLine();
            char[] array = s.toCharArray();
            int j = 0;
            for (char character : array) {
                arr[i][j] = character;
                j++;
            }
        }
        count = 0;
        for (int a = 0; a < r; a++) {
            for (int b = 0; b < c; b++) {
                if (arr[a][b] == 'L') {
                    if (!visited[a][b]) {
                        count++;
                    }
                    floodFill(a, b);
                }
            }
        }
        fio.close();

    }
    static void floodFill(int x, int y)
    {
        // Base cases
        if (x < 0 || x >= r || y < 0 || y >= c || visited[x][y] || arr[x][y] == 'W') {
            return;
        }
        visited[x][y] = true;
        // Replace the color at (x, y)

        // Recur for north, east, south and west
        floodFill(x+1, y);
        floodFill(x-1, y);
        floodFill(x, y+1);
        floodFill(x, y-1);
    }



//    static void DFSUtil(int v,boolean visited[])
//    {
//        // Mark the current node as visited and print it
//        visited[v] = true;
//        System.out.print(v+" ");
//
//        // Recur for all the vertices adjacent to this vertex
//        Iterator<Integer> i = adj[v].listIterator();
//        while (i.hasNext())
//        {
//            int n = i.next();
//            if (!visited[n])
//                DFSUtil(n, visited);
//        }
//    }
//    // The function to do DFS traversal. It uses recursive DFSUtil()
//    static void DFS(int v)
//    {
//        // Mark all the vertices as not visited(set as
//        // false by default in java)
//        boolean visited[] = new boolean[V];
//
//        // Call the recursive helper function to print DFS traversal
//        DFSUtil(v, visited);
//    }
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