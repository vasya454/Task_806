import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/*
Ну не гномы, а наказание какое-то! Подумала Белоснежка, в очередной раз пытаясь уложить гномов спать.
Одного уложишь, другой уже проснулся! И так всю ночь. У Белоснежки n гномов, и все они очень разные.
Она знает, что для того, чтобы уложить спать i-го гнома нужно ai минут, и после этого он будет спать
ровно bi минут. Помогите Белоснежке узнать, может ли она получить хотя бы минутку отдыха,
когда все гномы будут спать, и если да, то в каком порядке для этого нужно укладывать гномов спать.

Например, пусть есть всего два гнома, a1=1, b1=10, a2=10, b2=20. Если Белоснежка сначала начнет
укладывать первого гнома, то потом ей потребуется целых 10 минут, чтобы уложить второго,
а за это время проснется первый. Если же она начнет со второго гнома, то затем она успеет уложить
первого и получит целых 10 минут отдыха.

Входные данные
Первая строка входного файла INPUT.TXT содержит число n (1 ≤ n ≤ 10^5), вторая строка
содержит числа a1, a2, . . . an, третья - числа b1, b2, . . . bn (1 ≤ ai, bi ≤ 10^9).

Выходные данные
В выходной файл OUTPUT.TXT выведите n чисел – порядок, в котором нужно укладывать гномов спать.
Если Белоснежке отдохнуть не удастся, выведите число −1.

Примеры
№	INPUT.TXT	OUTPUT.TXT
1	2
    1 10
    10 20       2 1
2	2
    10 10
    10 10       -1
*/
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
            gnomes.sort(Comparator.comparing(o -> o.a));
            Collections.reverse(gnomes);
            gnomes.sort(Comparator.comparing(o -> o.a));
            Collections.reverse(gnomes);
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
            System.out.println(out_str);
            FileWriter fileWriter = new FileWriter("OUTPUT.txt");
            fileWriter.write(out_str);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something wrong");
        }
    }
}

class Gnome {
    public Gnome(int index) {
        this.a = 0;
        this.b = 0;
        this.index = index;
    }

    public Integer getA() {
        return a;
    }

    public Integer getB() {
        return b;
    }

    public Integer a, b;
    public int index;
}