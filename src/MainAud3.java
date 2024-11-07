//import aud2.DLL;
//import aud2.DLLNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MainAud3 {
    public static void main(String[] args) {

    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "{" +
                x +
                "," + y +
                '}';
    }
}

class Aud3Zadaca1 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int x = cin.nextInt();
            int y = cin.nextInt();
            points.add(new Point(x, y));
        }
        Double rez = null;
        Point par = null;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double rast = najdi_rast(points.get(i), points.get(j));
                if (rez == null || rast < rez) {
                    rez = rast;
                    par = new Point(i, j);
                }
            }
        }
        System.out.println("Min rast e " + rez);
        System.out.println("Pomegju " + points.get(par.x) + " " + points.get(par.y));
    }

    private static double najdi_rast(Point a, Point b) {
        double x_rast = a.x - b.x;
        double y_rast = a.y - b.y;
        return Math.sqrt(x_rast * x_rast + y_rast * y_rast);
    }
}

class Aud3Zadaca2 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        long rez = 0;
        for (int red1 = 0; red1 < n; red1++) {
            System.out.println("red1 " + red1);
            for (int kol1 = 0; kol1 < n; kol1++) {
                System.out.println("                   kol1 " + kol1);
                rez += n*n;
                rez -= n;
                rez -= n;
                rez -= n;
                rez -= n;
                rez += 3;
//                for (int red2 = 0; red2 < n; red2++) {
//                    for (int kol2 = 0; kol2 < n; kol2++) {
//                        if (!se_napagjaat(red1, kol1, red2, kol2)) {
//                            rez = rez + 4;
//                        }
//                    }
//                }
            }
        }
        System.out.println("Ima " + rez + " parovi kralici koi ne se napagjaat");
    }

    private static boolean se_napagjaat(int red1, int kol1, int red2, int kol2) {
        if (red1 == red2) return true;
        if (kol1 == kol2) return true;
        int rast_x = Math.abs(kol1 - kol2);
        int rast_y = Math.abs(red1 - red2);
        if (rast_y == rast_x) return true;
        return false;
    }

}
class Aud3Zadaca3 {
    /*
    6
    1000 50 10 5 2 1
    4223
    5
    8 5 10 1 2
    13
    */
    static int min_rez = Integer.MAX_VALUE;
    static void rekurzija(int ind, List<Integer> coins, int remaining, int rez){
        if(ind == coins.size()){
            if(rez<min_rez && remaining == 0)
                min_rez = rez;
            return;
        }
        int curr = coins.get(ind);
        int fits = remaining / curr;
        for (int i = 0; i <= fits; i++) {
            rekurzija(ind+1, coins, remaining - i*curr, rez + i);
        }
    }
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        List<Integer> pari = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            pari.add(cin.nextInt());
        }
        pari.sort(Comparator.reverseOrder());
        int target = cin.nextInt(), rez = 0;
        for (int i = 0, remaining = target; i < n; i++) {
            int curr = pari.get(i);
            int fits = remaining / curr;
            rez += fits;
            remaining -= curr * fits;
            System.out.println("Kje iskoristam "+curr + " " + fits+ " pati, " + remaining);
        }
        System.out.println("Vkupno iskoristiv " + rez + " parichki");
        rekurzija(0, pari, target, 0);
        System.out.println("Optimalno mozheshe "+ min_rez + " parichki");
    }


}
class Aud3Zadaca4 {
    static class Objekt{
        int vrednost;
        int tezina;

        public Objekt(int vrednost, int tezina) {
            this.vrednost = vrednost;
            this.tezina = tezina;
        }

    }
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        List<Objekt> objekti = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            objekti.add(new Objekt(cin.nextInt(),cin.nextInt()));
        }
        objekti.sort(Comparator.comparingDouble(
                objekt ->  (double)-objekt.vrednost /objekt.tezina
        ));
        int kapacitet = cin.nextInt(), rez = 0;
        for (int i = 0, remaining = kapacitet; i < n; i++) {
            Objekt curr = objekti.get(i);
            if(remaining<curr.tezina)continue;
            rez += curr.vrednost;
            remaining -= curr.tezina;
            System.out.println("Kje dobijam "+ curr.vrednost + " " + curr.tezina + " prostor, " + remaining);
        }
    }


}

/*
# Објект Вредност Тежина
1 Облека 200 10                     20
2 Книги 300 20                      15
3 Монопол, карти… 5 0.5             10
4 CD player, MP3 player… 80 5       16
           0      20
200 10     200    10
80 5       280     5
300 20     355     0
30 3       355     0

           0      20
200 10     200    10
80 5       280     5
300 20     280     5
30 3       310     2

           0      20
300 20     300    0
200 10
80 5
30 3

 */


