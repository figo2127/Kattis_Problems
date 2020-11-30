// Lee Ze Xin, A0203252X

import java.util.*;
import java.io.*;

enum Status {
    FOLDED,
    FIST,
    PALM
}

class Pair {
    int player;
    Status s;

    Pair(int player, Status s) {
        this.player = player;
        this.s = s;
    }
}

public class CoconutSplat {
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

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        int rhyme = r.nextInt();
        int num_players = r.nextInt();
        LinkedList<Pair> group = new LinkedList<>();

        for (int i = 1; i <= num_players; i++) {
            group.add(new Pair(i, Status.FOLDED));
        }
        while (group.size() > 1) {
            for (int i = 0; i < rhyme - 1; i++) {
                group.offer(group.poll());
            }
            Pair p = group.peek();
//            index = (index + rhyme - 1) % group.size();
            if (p.s == Status.FOLDED) {
                group.remove();
                Pair newPair = new Pair(p.player, Status.FIST);
                group.addFirst(newPair);
                group.addFirst(newPair);
            }
            if (p.s == Status.FIST) {
                group.offer(new Pair(p.player, Status.PALM)); //add to back
                group.remove(); //remove the fist at front of list
            }
            if (p.s == Status.PALM) {
                group.remove();
            }
        }
        System.out.println(group.peek().player);

    }

}