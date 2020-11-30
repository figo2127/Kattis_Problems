import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.*;
import java.util.*;

class Teque {
    public HashMap<Integer, Integer> front;
    public HashMap<Integer, Integer> back;
    public int firstInFront;
    public int lastInFront;
    public int firstInBack;
    public int lastInBack;

    public Teque() {
        front = new HashMap<Integer, Integer>();
        back = new HashMap<Integer, Integer>();
        firstInFront = 0;
        lastInFront = 0;
        firstInBack = 0;
        lastInBack = 0;
    }

    public void push_front(int x) {
        front.put(firstInFront, x);
        firstInFront--;
        rebalance();
    }

    public void push_middle(int x) {
        lastInFront++;
        front.put(lastInFront, x);
        rebalance();
    }

    public void push_back(int x) {
        back.put(lastInBack, x); //key = 0, value = 9
        lastInBack++; //key++, key is now 1
        rebalance();
    }

    public int get(int i) {
        int front_len = lastInFront - firstInFront;

        if (i < front_len) {
            return front.get(firstInFront + 1 + i);
        }

        i = i - front_len;

        return back.get(firstInBack + i);
    }

    //rebalancing
    public void rebalance() {
        if (front.size() < back.size()) {
            lastInFront++; //lastInFront++, lastInFront = 1
            front.put(lastInFront, back.get(firstInBack)); //key = 1, value = back.get(0) = 9;
            back.remove(firstInBack); //remove 9 from back.
            firstInBack++; //firstInBack = 1
        }

        if (front.size() > back.size() + 1) {
            firstInBack--;
            back.put(firstInBack, front.get(lastInFront));
            front.remove(lastInFront);
            lastInFront--;
        }
    }
    public static void main(String[] args) {

        Teque t = new Teque();
        FastIO io = new FastIO();
        int n = io.nextInt();

        for (int a = 0; a < n; a++) {
            String inst = io.next();
            int x = io.nextInt();

            switch (inst) {
                case "push_front":
                    t.push_front(x);
                    break;
                case "push_middle":
                    t.push_middle(x);
                    break;
                case "push_back":
                    t.push_back(x);
                    break;
                case "get":
                    io.println(t.get(x));
                    break;
                default:
            }
        }

        io.close();
    }


    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

    /**
     * Fast I/O
     * @source https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
     */
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
}