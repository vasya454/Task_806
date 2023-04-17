import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("INPUT.txt"));
        int n = Integer.parseInt(scanner.nextLine());
        String tTS = scanner.nextLine();
        String sT = scanner.nextLine();
        scanner.close();
        ArrayList<Gnome> gnomes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            gnomes.add(new Gnome(i + 1));
            gnomes.get(i).a = Integer.parseInt(tTS.split(" ")[i]);
            gnomes.get(i).b = Integer.parseInt(sT.split(" ")[i]);
        }
        gnomes.sort(Gnome::compareTo);
        int firstTTS = gnomes.get(0).a;
        int firstST = gnomes.get(0).b;
        String out_str = "";
        int comeToBedTotalTime = 0;
        for (Gnome g :
                gnomes) {
            comeToBedTotalTime += g.a;
            if (comeToBedTotalTime - firstTTS < firstST) {
                out_str += g.index + " ";
            } else {
                out_str = "-1";
                break;
            }
        }
        FileWriter fileWriter = new FileWriter("OUTPUT.txt");
        fileWriter.write(out_str);
        fileWriter.close();
    }
}

class Gnome {
    public int a, b;
    public int index;

    public Gnome(int index) {
        this.index = index;
    }

    public int compareTo(Object o) {
        return (((Gnome) o).b - a) - (b - ((Gnome) o).a);
    }
}