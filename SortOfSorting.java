import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.*;

class FirstTwo implements Comparable<FirstTwo> {
    char a1;
    char a2;
    String s;

    public FirstTwo(String s) {
        this.s = s;
        this.a1 = s.charAt(0);
        this.a2 = s.charAt(1);
    }

    public int compareTo(FirstTwo other) {
        if (this.a1 == (other.a1)) {
            // compare using second
            if (this.a2 == (other.a2)) {
                return 0;
            }
            else {
                return this.a2 - other.a2;
            }
        }
        else {
            return this.a1 - other.a1;
            // if this.a1 > other.a1 returns positive number
        }
    }

    @Override
    public String toString() {
        return s;
    }
}

public class SortOfSorting {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // primitive Scanner
        boolean c = true;
        boolean second = false;
        StringBuilder out = new StringBuilder(512);
        while (c) {
            ArrayList<FirstTwo> arrlist = new ArrayList<>();
            int n = Integer.parseInt(br.readLine());
            if (n == 0) {
                c = false;
                break;
            }
            if (second) {
                out.append("\n");
            }
            while (n > 0) {
                String s = br.readLine();
                arrlist.add(new FirstTwo(s));
                n--;
            }

            Collections.sort(arrlist);
            arrlist.forEach(x -> out.append(x).append("\n"));
            second = true;
        }
        System.out.print(out.toString());
    }
}