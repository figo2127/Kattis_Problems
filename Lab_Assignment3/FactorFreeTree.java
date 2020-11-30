import java.io.*;
import java.util.*;


class FactorFreeTree {
//    ▶ The root of the tree will be an ancestor of all vertices and
//    must therefore be coprime to all other values.

    static int N = 1000035;
    static int inputSize = N * 10;
    static int num;
    static int max;
    static int[] arr = new int[N];
    static boolean[] visited = new boolean[inputSize];
    static int[] f = new int[inputSize];
    static int[] prime = new int[inputSize]; //stores prime numbers
    static int[] pred = new int[inputSize];
    static int[] pre = new int[N];
    static int[] nxt = new int[N];
    static int[] output = new int[N];

//    The solution: simply start scanning from both sides simultaneously!
//    Intuitively, this is faster because:
//            ▶ If the root is near one of the ends of the sequence, we split
//    into unequal subproblems, but we can do so quickly.
//            ▶ If the root is near the middle, it takes a long time to find, but
//    we cut into to roughly equisized subproblems.
//    This algorithm can be proven to run in O(n log n) time

    static boolean canBeRoot(int i, int left, int right) {
        return (pre[i] < left && right < nxt[i]);
    }

//        Function Solve (l, r) is
//          for i from l to r do
//              if i can be root of [l,r] then
//                  return Solve (l, i − 1) and Solve (i + 1, r)
//              end
//          end
//          return l ≥ r
//        end

    static boolean Solve(int left, int right, int node){
        if(left >= right)  {
            if (left == right) {
                output[left] = node;
            }
            return true;
        }
        int l = left;
        int r = right;

        while (l <= r) {
            //start doing from both sides simultaneously
            if(canBeRoot(l, left, right)){
                output[l] = node;
                return Solve(left, l-1, l) && Solve(l+1, right, l);
            }
            if(canBeRoot(r, left, right)){
                output[r] = node;
                return Solve(left, r-1, r) && Solve(r+1, right, r);
            }
            l++;
            r--;
        }
        return false;
    }

    //sieve of eratosthenes
    static void makePrime(int ceil) {
        //first 2 confirmed to be false we don't care since they are init to zero
        for(int i = 2; i <= ceil; i++){
            if(!visited[i]){ //used to save operations
                num++;
                prime[num] = i;
                f[i]=i;
            }
            int temp;
            for(int j = 1; (j <= num) && (i * prime[j] <= ceil); j++){
                temp = i * prime[j];
                visited[temp] = true;
                f[temp] = prime[j];

                if(i % prime[j] == 0) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        FastIO fio = new FastIO();

        int n = fio.nextInt();

        for (int i = 1; i <= n; i++) {
            arr[i] = fio.nextInt();
            max = Math.max(max, arr[i]);
        }

        //obtain the prime numbers and indicate them in the array
        makePrime(max);

        for (int i = 1; i <= n; i++) {
            int tmp = arr[i];
            int left = 0;
            while((tmp ^ 1)!= 0) { //xor, tmp only = 0 when tmp = 1
                int g = f[tmp];
                left = Math.max(left, pred[g]);
                pred[g] = i;

                while(g!= 0 && tmp % g == 0) {
                    tmp /= g;
                }

            }
            pre[i] = left;
        }

        for (int i = 1; i <= max; i++) {
            pred[i] = n + 1;
        }

        for (int i = n; i >= 1; i--) {
            int tmp = arr[i];
            int right = n + 1;
            while((tmp ^ 1) != 0) { //equivalent to tmp != 1
                int g = f[tmp];
                right = Math.min(right, pred[g]);
                pred[g] = i;

                while(g != 0 && tmp % g == 0) {
                    tmp /= g;
                }


            }
            nxt[i] = right;
        }

        if (Solve(1, n, 0)) {
            for (int i = 1; i <= n; i++) {
                fio.print(output[i] + " ");
            }
        }
        else {
            fio.println("impossible");
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