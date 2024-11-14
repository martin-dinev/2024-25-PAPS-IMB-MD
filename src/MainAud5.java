import aud5.*;

import java.util.Deque;
import java.util.LinkedList;


public class MainAud5 {
    public static void main(String[] args) {
        String input = "(– b + sqrt[b^2 {}– 4ac]) / 2a";
        if(check_valid(input))
            System.out.println("Zagradite se vo red");
        else
            System.out.println("Zagradite ne se vo red");

    }

    private static boolean check_valid(String str) {
        Stack<Character> st = new LinkedStack<>();
        for (int i = 0; i < str.length(); i++) {
            Character cur = str.charAt(i);
            if (cur == '(' || cur == '{' || cur == '[') {
                st.push(cur);
            } else if (cur == ')' || cur == '}' || cur == ']') {
                if (st.isEmpty()) return false;
                Character open = st.pop();
                if (!is_matching(open, cur)) return false;
            }
        }
        return st.isEmpty();
    }

    private static boolean is_matching(Character open, Character close) {
        return open == '(' && close == ')'
                || open == '{' && close == '}'
                || open == '[' && close == ']';
    }
}


class Student implements Comparable<Student>{
    int index;
    int vreme;

    public Student(int index, int vreme) {
        this.index = index;
        this.vreme = vreme;
    }

    @Override
    public String toString() {
        return "{" + index + " " +vreme + '}';
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(vreme, o.vreme);
    }
}


class ZadacaAud5{

    public static void main(String[] args) {
        Queue<Student> queue = new LinkedQueue<>();
        queue.enqueue(new Student(197515, 50));
        queue.enqueue(new Student(195615, 20));
        queue.enqueue(new Student(196135, 15));
        queue.enqueue(new Student(196215, 30));
        queue.enqueue(new Student(112315, 35));
        queue.enqueue(new Student(193235, 10));
        queue.enqueue(new Student(112415, 30));
        queue.enqueue(new Student(234535, 25));
        System.out.println(queue);

        opsluzhiStudenti(queue, 180);

        System.out.println(queue);
        if (queue.isEmpty()){
            System.out.println("Site se opsluzheni");
        }else {
            System.out.println("Prviot neopsluzhen e " + queue.peek());
            System.out.println("Brojot na neopsluzheni e " + queue.size());
        }



        PriorityQueue<Student> pq = new PriorityQueue<>();
        pq.enqueue(new Student(197515, 50));
        pq.enqueue(new Student(195615, 20));
        pq.enqueue(new Student(196135, 15));
        pq.enqueue(new Student(196215, 30));
        pq.enqueue(new Student(112315, 35));
        pq.enqueue(new Student(193235, 10));
        pq.enqueue(new Student(112415, 30));
        pq.enqueue(new Student(234535, 25));
        System.out.println(pq);

        opsluzhiStudenti(pq, 180);

        System.out.println(pq);
        if (pq.isEmpty()){
            System.out.println("Site se opsluzheni");
        }else {
            System.out.println("Prviot neopsluzhen e " + pq.peek());
            System.out.println("Brojot na neopsluzheni e " + pq.size());
        }

    }

    private static void opsluzhiStudenti(Queue<Student> queue, int vreme) {
        while(!queue.isEmpty() && queue.peek().vreme <= vreme){
            vreme -= queue.peek().vreme;
            System.out.print(queue.peek()+ " ");
            queue.dequeue();
        }
        System.out.println();
    }
}


class ZadacaLanska{
    public static void main(String[] args) {

    }
}