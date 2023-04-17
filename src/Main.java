import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(new File("INPUT.txt"));
            int n = scanner.nextInt();
            ArrayList<Gnome> gnomes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                gnomes.add(i, new Gnome(i + 1));
                gnomes.get(i).a = scanner.nextInt();
            }
            for (int i = 0; i < n; i++) {
                gnomes.get(i).b = scanner.nextInt();
            }
            scanner.close();
            gnomes.sort(new Comparator<Gnome>() {
                @Override
                public int compare(Gnome o1, Gnome o2) {
                    return Integer.compare((o2.b - o1.a), (o1.b - o2.a));
                }
            });
            String out_str = "";
            int comeToBedTotalTime = 0;
            for (Gnome g :
                    gnomes) {
                comeToBedTotalTime += g.a;
                if (comeToBedTotalTime - gnomes.get(0).a < gnomes.get(0).b) {
                    out_str += g.index + " ";
                } else {
                    out_str = "-1";
                    break;
                }
            }
            FileWriter fileWriter = new FileWriter("OUTPUT.txt");
            fileWriter.write(out_str);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something wrong");
        }
    }
}

class Gnome {
    public int a, b;
    public int index;

    public Gnome(int index) {
        this.index = index;
    }
}