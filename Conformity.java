import java.util.HashSet;
import java.util.HashMap;
import java.io.*;
import java.util.StringTokenizer;

class Conformity {
    static class FastIO extends PrintWriter
    {
        BufferedReader br;
        StringTokenizer st;

        public FastIO()
        {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
    public static void main(String[] args) {
        FastIO f = new FastIO();
        int n = f.nextInt();
        int Max = 1;
        int freq = 0;
        HashMap<HashSet<Integer>, Integer> hmap = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            HashSet<Integer> hashSet = new HashSet<>(5);
            for (int j = 0; j < 5; j++) {
                hashSet.add(f.nextInt());
            }
            if (hmap.containsKey(hashSet)) {
                int k = hmap.get(hashSet);
                k++;
                if (k == Max) {
                    freq++;
                } else if (k > Max) {
                    Max = k;
                    freq = 1;
                }
                hmap.remove(hashSet);
                hmap.put(hashSet, k);
            } else {
                hmap.put(hashSet, 1);
            }
        }
        if (Max == 1) {
            f.println(n);
        } else {
            f.println(Max*freq);
        }
        f.close();
    }
}