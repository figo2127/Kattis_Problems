import java.util.*;
import java.io.*;

class Quest {
    int E;
    int G;
//    int count;

    public Quest(int E, int G) {
        this.E = E;
        this.G = G;
//        this.count = 1;
    }
    public boolean equals(Quest q2) {
        return this.E == q2.E && this.G == q2.G;
    }
}

class QuestCount {
    public int count;

    public QuestCount() {
        this.count = 1;
    }
}



public class KattisQuest {
    public static void main(String[] args) {
        Comparator<Quest> com = new Comparator<Quest>() {
            @Override
            public int compare(Quest o1, Quest o2) {
                if (o1.E != o2.E) {
                    return o1.E - o2.E;
                }
                return o1.G - o2.G;
            }
        };
        TreeMap<Quest, QuestCount> questList = new TreeMap<>(com);
        FastIO fio = new FastIO();

        int n = fio.nextInt();

        //processing
        for (int i = 0; i < n; i++) {
            String instruction = fio.nextLine();
            String[] ins = instruction.split(" ");

            if (ins[0].equals("add")) {
                int E = Integer.parseInt(ins[1]);
                int G = Integer.parseInt(ins[2]);
                Quest q = new Quest(E, G);
                add(q, questList);

            }
            else {
                int X = Integer.parseInt(ins[1]);
                fio.println(query(X, questList));
            }
        }

//        for (Quest q: questList) {
//            System.out.println(questList.values());
//        }
        fio.close();


    }

    public static void add(Quest curr, TreeMap<Quest, QuestCount> quests) {

        if (!quests.containsKey(curr)) {
            quests.put(curr, new QuestCount());
        }
        else {
            QuestCount currCount = quests.get(curr);
            currCount.count++;
        }
    }

    public static long query(int energyLeft, TreeMap<Quest, QuestCount> quests) {
        boolean foundQuest = true;
        long result = 0;

        while (energyLeft != 0 && foundQuest) {
            foundQuest = false;
            Quest currQuest = quests.floorKey(new Quest(energyLeft, 10000000));

            if (currQuest != null) {
//                int currQuestVal = currQuest.count;
                QuestCount currCount = quests.get(currQuest);
                foundQuest = true;
                energyLeft -= currQuest.E;
                result += currQuest.G;

                currCount.count--;
//                System.out.println(quests.get(currQuest).count);
//                System.out.println(currQuest.E);
//                System.out.println(currQuest.G);

                if (currCount.count == 0) {
                    quests.remove(currQuest);
                }
            }
        }

        return result;
    }





    static class FastIO extends PrintWriter {
        BufferedReader br;
        StringTokenizer st;

        public FastIO() {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}

