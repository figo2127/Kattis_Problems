import java.util.*;

public class TrainPassengers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean printed = false;
        int totalCapacity = sc.nextInt();
        int occupy = 0;
        int num_stations = sc.nextInt();
        for (int i = 0; i < num_stations; i++) {
            int left = sc.nextInt();
            int entered = sc.nextInt();
            int stay = sc.nextInt();
            if (left > occupy) {
                printed = true;
            }
            occupy = occupy - left + entered;
            if ((occupy != totalCapacity && stay > 0) || (occupy > totalCapacity) || (occupy < 0) || (i == num_stations - 1 && stay > 0)) {
                printed = true;
            }
        }
        if (printed) {
            System.out.println("impossible");
        }
        else if (occupy == 0 && !printed) {
            System.out.println("possible");
        }
        else if (occupy != 0) {
            System.out.println("impossible");
        }
    }
}