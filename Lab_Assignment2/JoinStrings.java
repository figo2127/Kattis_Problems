import java.io.*;
import java.util.*;

class JoinStrings {
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

        int n = f.nextInt(); //4

        String[] str = new String[n + 1]; //create an empty String array of 5 elements
        int[] indexArrOne = new int[n + 1]; //create an empty int array of 5 elements
        int[] indexArrTwo = new int[n + 1]; //create an empty int array of 5 elements

        for (int i = 1; i <= n; i++) {
            str[i] = f.next(); //get all the strings and assign to arr[1] onwards
            //cute
            //cat
            //kattis
            //is
        }

        int i1 = 1;
        int i2 = 0;

        for (int i = 1; i <= n - 1; i++) {
            i1 = f.nextInt(); //3 4 3
            i2 = f.nextInt(); //2 1 4

            int lastIdx = indexArrTwo[i1]; //if indexArrTwo[idx] got nothing then 0 is returned.
//            System.out.println(lastIdx);

            if (lastIdx == 0) {
                lastIdx = i1; //assigned 3

                while (indexArrOne[lastIdx] != 0) {
                    lastIdx = indexArrOne[i1];
                }
            }

            indexArrOne[lastIdx] = i2; //indexArrOne[3] = 2
            indexArrTwo[i1] = indexArrTwo[i2]; //indexArrTwo[3] = indexArrTwo[2] ie. swapped

            if (indexArrTwo[i1] == 0) {
                indexArrTwo[i1] = i2; //assign 2 to indexArrTwo[3]
            }
        }
        // print out strings in right order part!
        while (indexArrOne[i1] != 0) {
            f.print(str[i1]);
            i1 = indexArrOne[i1];
        }
        //print out last string
        f.print(str[i1]);

        f.close();
    }
}