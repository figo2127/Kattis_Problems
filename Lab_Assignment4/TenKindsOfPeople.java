import java.io.*;
import java.util.*;

class TenKindsOfPeople {
    static int r;
    static int c;
    static int[][] arr; //the map
    static int[][] visited;
    static boolean travelled;

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        r = fio.nextInt();
        c = fio.nextInt();
        arr = new int[r][c];
        visited = new int[r][c];

        for (int[] coordinatesRow : visited) {
            Arrays.fill(coordinatesRow, -1);
        }
        //read the map
        for (int a = 0; a < r; a++) {
            String input = fio.next();

            for (int b = 0; b < c; b++) {
                arr[a][b] = Integer.parseInt(String.valueOf(input.charAt(b)));
            }
        }

        int n = fio.nextInt();

        for (int a = 0; a < n; a++) {
            travelled = false;
            int r1 = fio.nextInt() - 1;
            int c1 = fio.nextInt() - 1;
            int r2 = fio.nextInt() - 1;
            int c2 = fio.nextInt() - 1;
            if (visited[r1][c1] != - 1) { //some previous queries have been here before
                travelled = true;
                if (visited[r1][c1] == visited[r2][c2]) { //if both got same number means these 2 places have been tested to be connectible by some query before
                    if (arr[r1][c1] == 0) {
                        fio.println("binary");
                    } else {
                        fio.println("decimal");
                    }
                } else {
                    fio.println("neither");
                }
            }
            if (!travelled) {
                bfs(a, r1, c1);
                if (arr[r1][c1] == 1 && visited[r2][c2] == a) {
                    fio.println("decimal");
                } else if (arr[r1][c1] == 0 && visited[r2][c2] == a) {
                    fio.println("binary");
                } else {
                    fio.println("neither");
                }
            }
        }

        fio.close();
    }

    static class Pair {
        public int a;
        public int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    static void bfs(int footprint, int r, int c) {
        ArrayDeque<Pair> pq = new ArrayDeque<Pair>();

        visited[r][c] = footprint;
        pq.offer(new Pair(r, c));

        while (!pq.isEmpty()) {
            Pair coordinates = pq.poll();

            if (coordinates.a != 0 && (arr[coordinates.a - 1][coordinates.b] == arr[coordinates.a][coordinates.b] && visited[coordinates.a - 1][coordinates.b] == -1)) {
                visited[coordinates.a - 1][coordinates.b] = footprint;
                pq.offer(new Pair(coordinates.a - 1, coordinates.b));
            }

            if (coordinates.b != 0 && (arr[coordinates.a][coordinates.b - 1] == arr[coordinates.a][coordinates.b] && visited[coordinates.a][coordinates.b - 1] == -1)) {
                visited[coordinates.a][coordinates.b - 1] = footprint;
                pq.offer(new Pair(coordinates.a, coordinates.b - 1));
            }

            if (coordinates.a != arr.length - 1 && (arr[coordinates.a + 1][coordinates.b] == arr[coordinates.a][coordinates.b] && visited[coordinates.a + 1][coordinates.b] == -1)) {
                visited[coordinates.a + 1][coordinates.b] = footprint;
                pq.offer(new Pair(coordinates.a + 1, coordinates.b));
            }

            if (coordinates.b != arr[0].length - 1 && (arr[coordinates.a][coordinates.b + 1] == arr[coordinates.a][coordinates.b] && visited[coordinates.a][coordinates.b + 1] == -1)) {
                visited[coordinates.a][coordinates.b + 1] = footprint;
                pq.offer(new Pair(coordinates.a, coordinates.b + 1));
            }
        }
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