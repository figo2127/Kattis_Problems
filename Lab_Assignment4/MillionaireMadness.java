import java.io.*;

import java.lang.reflect.Array;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.StringTokenizer;

class MillionaireMadness {
    static int max = 1000000000;
    static int min = 0;
    static int[][] map;
    static boolean[][] visited;
    static int ladderLength;

    static int M;
    static int N;

    public static void main(String[] args) {
        FastIO fio = new FastIO();

        M = fio.nextInt();
        N = fio.nextInt();
        map = new int[M][N];
        visited = new boolean[M][N];


        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = fio.nextInt();
            }
        }
        //binary search for the minimum ladder height
        while(max > min) {
            ladderLength = (max + min)/2;
            //reset visited array for next iteration of dfs
            for (int a = 0; a < M; a++) {
                Arrays.fill(visited[a], false);
            }
            boolean validPath = dfs(0,0);

            if (validPath) { //if dfs returns true that means there is a path with the given ladder height
                max = ladderLength;
            } else { // if dfs returns false means impossible to reach with height of current ladder, needs increase
                min = ladderLength + 1;
            }


        }
        fio.println(min);

        fio.close();
    }

    static boolean dfs(int x, int y) {
        //reached end
        if (x == map.length - 1 && y == map[0].length - 1) {
            return true;
        }
        if (visited[x][y]) {
            return false;
        }
        visited[x][y] = true;
        boolean validPath = false;
        if (x > 0 && map[x-1][y] - map[x][y] <= ladderLength) {
            validPath = validPath || dfs(x-1, y);
        }

        if (x < map.length-1 && map[x+1][y] - map[x][y] <= ladderLength) {
            validPath = validPath || dfs(x+1, y);
        }

        if (y > 0 && map[x][y-1] - map[x][y] <= ladderLength) {
            validPath = validPath || dfs(x, y-1);
        }

        if (y < map[0].length-1 && map[x][y+1] - map[x][y] <= ladderLength) {
            validPath = validPath || dfs(x, y+1);
        }
        return validPath;
    }

//    static class Edge {
//        public int w;
//        public int u;
//        public int v;
//
//        public Edge(int w, int u, int v) {
//            this.w = w;
//            this.u = u;
//            this.v = v;
//        }
//    }
//
//    static int getEdgeWeight(int r1, int c1, int r2, int c2) {
//        int h1 = map[r1][c1];
//        int h2 = map[r2][c2];
//        return Math.max(h2 - h1, 0);
//    }

//    static void search(int x, int y, int currlen) {
//        if (x < 0 || x >= M || y < 0 || y >= N || visited[x][y]) {
//            return;
//        }
//        visited[x][y] = true;
//        if (x == M-1 && y == N-1) {
//            if (currlen < shortest) {
//                shortest = currlen;
//            }
//            return;
//        }
//        else {
//            if (x > 0 && !visited[x - 1][y]) {
//                if (currlen + arr[x][y] < arr[x-1][y]) {
//                    search(x-1, y, arr[x-1][y] - arr[x][y]);
//                }
//                else {
//                    search(x-1, y, currlen);
//                }
//            }if (x >= M && !visited[x + 1][y]) {
//                if (currlen + arr[x][y] < arr[x+1][y]) {
//                    search(x+1, y, arr[x-1][y] - arr[x][y]);
//                }
//                else {
//                    search(x+1, y, currlen);
//                }
//            }if (!visited[x][y - 1]) {
//                if (currlen + arr[x][y] < arr[x][y - 1]) {
//                    search(x, y-1, arr[x][y - 1] - arr[x][y]);
//                }
//                else {
//                    search(x, y-1, currlen);
//                }
//            }if (!visited[x][y + 1]) {
//                if (currlen + arr[x][y + 1] < arr[x][y + 1]) {
//                    search(x, y+1, arr[x][y + 1] - arr[x][y]);
//                }
//                else {
//                    search(x, y + 1, currlen);
//                }
//            }
//        }
//
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