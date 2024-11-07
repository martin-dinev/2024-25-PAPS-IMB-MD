import aud1.SLL;
import aud1.SLLNode;
import aud2.DLL;
import aud2.DLLNode;

import java.util.*;

public class MainAud4 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

class CookiesAndWishes {
    //    7 6
//    5 9 4 3 1 6 8
//    9 8 3 4 3 2

    static class Par implements Comparable<Par> {
        int prv;
        int second;

        @Override
        public int compareTo(Par o) {
            if (prv < o.prv) return -1;
            if (prv > o.prv) return 1;
            if (second < o.second) return -1;
            if (second > o.second) return 1;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        int m = cin.nextInt();
        int[] wishes = new int[n];
        for (int i = 0; i < n; i++) {
            wishes[i] = cin.nextInt();
        }
        int[] cookies = new int[m];
        for (int i = 0; i < m; i++) {
            cookies[i] = cin.nextInt();
        }
        Arrays.sort(wishes);
        Arrays.sort(cookies);

//        Par[] parovi = new Par[10];
//        Arrays.sort(parovi);
        int br = 0, rez = 0;
        for (int cookie : cookies) {
            if (cookie >= wishes[br]) {
                rez++;
                br++;
                if (br > n) break;
            }
        }
        System.out.println(rez);
    }
}

class ClosedInterval implements Comparable<ClosedInterval> {
    int left;
    int right;

    public ClosedInterval(int left, int right) {
        this.left = left;
        this.right = right;
    }

    boolean point_in(int a) {
        return left <= a && a <= right;
    }



    @Override
    public String toString() {
        return "{" + left + " " + right + "}";
    }


    @Override
    public int compareTo(ClosedInterval o) {
        if (left < o.left) return -1;
        if (left > o.left) return 1;
        if (right < o.right) return -1;
        if (right > o.right) return 1;
        return 0;
    }
}
class ClosingIntervals {
    static boolean se_secat(ClosedInterval a, ClosedInterval b) {
        return a.point_in(b.left) ||
                a.point_in(b.right) ||
                b.point_in(a.left) ||
                b.point_in(a.right);
    }
    static ClosedInterval spoj(ClosedInterval a, ClosedInterval b) {
        if (!se_secat(a, b)) throw new RuntimeException("Ova ne treba da se sluchi");
        return new ClosedInterval(
                Math.min(a.left, b.left),
                Math.max(a.right, b.right)
        );
    }


    public static void main2(String[] args) { // brute force
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SLL<ClosedInterval> l = new SLL<>();
        for (int i = 0; i < n; i++) {
            l.insertLast(new ClosedInterval(scanner.nextInt(), scanner.nextInt()));
        }
        SLLNode<ClosedInterval> curr = l.getFirst();
        System.out.println(l);
        while (curr != null) {
            SLLNode<ClosedInterval> next = curr.getSucc();
            while (next != null) {
                if (se_secat(curr.getElement(), next.getElement())) {
                    curr.setElement(spoj(next.getElement(), curr.getElement()));
                    l.delete(next);
                    System.out.println(l);
                }
                next = next.getSucc();
            }
            curr = curr.getSucc();
            System.out.println("Sledno");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<ClosedInterval> l = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            l.add(new ClosedInterval(scanner.nextInt(), scanner.nextInt()));
        }
        l.sort(Comparator.naturalOrder());
        List<ClosedInterval> rez = new ArrayList<>();
        rez.add(l.getFirst());
        for (int i = 1; i < n; i++) {
            ClosedInterval curr = l.get(i);
            if(se_secat(curr, rez.getLast())){
                rez.set(rez.size()-1, spoj(curr, rez.getLast()));
            }else{
                rez.add(curr);
            }
        }
        for (ClosedInterval cl:rez){
            System.out.print(cl + "  ");
        }
        System.out.println();


//            for (int i = 0; i < n; i++) {
//                ClosedInterval curr = l.get(i);
////            while(curr!=null){
//                SLLNode<ClosedInterval> next = curr.;
//                while (next != null) {
//                    if (se_secat(curr.getElement(), next.getElement())) {
//                        curr.setElement(spoj(next.getElement(), curr.getElement()));
//                        l.delete(next);
//                        System.out.println(l);
//                    }
//                    next = next.getSucc();
//                }
//                curr = curr.getSucc();
//                System.out.println("Sledno");
//            }
    }


}

class PowerButBetter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int n = sc.nextInt();
        long rez = pow_but_mod(x,n);
        System.out.println(rez);
    }

    private static long pow_but_mod(long x, long n) {
        if(n==1)return x;
        long mod = 1000000007;
        long pola = pow_but_mod(x, n/2);
        long rez = pola * pola % mod;
        if(n%2==1)rez = rez * x % mod;
        return rez;

//        long rez = 1;
//        for (int i = 0; i < n; i++) {
//            rez *= x;
//            rez %= mod;
//        }
//        return rez;
    }

}