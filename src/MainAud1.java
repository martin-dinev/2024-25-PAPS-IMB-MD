import aud1.Array;
import aud1.SLL;
import aud1.SLLNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainAud1 {
    public static void main(String[] args) {
        System.out.println("Hello");
        int[] broevi = new int[12];
        int[] tmp = new int[24];
        for (int i = 0; i < broevi.length; i++) {
            tmp[i] = broevi[i];
        }
        broevi = tmp;
    }
}

class Aud1Zadaca1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        Array<Integer> array1 = getIntegerArray(n, input);
        Array<Integer> array2 = getIntegerArray(n, input);

        ArrayList<Integer> list1, list2;
        list1 = copyArrayToList(array1);
        list2 = copyArrayToList(array2);


        System.out.println(array1);
        System.out.println(array2);
        brisi_isti(array1, array2);
        System.out.println("Po povik na funkcijata");
        System.out.println(array1);
        System.out.println(array2);

        System.out.println(list1);
        System.out.println(list2);
        brisi_isti(list1, list2);
        System.out.println("Po povik na funkcijata");
        System.out.println(list1);
        System.out.println(list2);

        List<Integer> list = new LinkedList<>();
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(6);
        System.out.println(list);

    }

    private static <E> void brisi_isti(ArrayList<E> list1, ArrayList<E> list2) {
        for (int i = 0; i < list1.size(); ) {
            E e1 = list1.get(i);
            E e2 = list2.get(i);
            if (e1.equals(e2)) {
                list1.remove(i);
                list2.remove(i);
            } else {
                i++;
            }
        }
    }

    private static ArrayList<Integer> copyArrayToList(Array<Integer> array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.getSize(); i++) {
            list.add(array.get(i));
        }
        return list;
    }

    private static <E> void brisi_isti(Array<E> array1, Array<E> array2) {
        for (int i = 0; i < array1.getSize(); ) {
            E e1 = array1.get(i);
            E e2 = array2.get(i);
            if (e1.equals(e2)) {
                array1.delete(i);
                array2.delete(i);
            } else {
                i++;
            }
        }
    }

    private static Array<Integer> getIntegerArray(int n, Scanner input) {
        Array<Integer> array = new Array<>(n);
        for (int i = 0; i < n; i++) {
            array.insertLast(input.nextInt());
        }
        return array;
    }
}
/*
6
1 2 3 4 5 6
7 2 3 7 7 6

* */

class Aud1Zadaca2 {
    public static void main(String[] args) {
        SLL<Integer> lista = new SLL<>();
        popolni_lista(lista);
        System.out.println(lista);
        SLLNode<Integer> first = lista.getFirst();
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(i + ": " + first.getElement() + "\t");
            first = first.getSucc();
        }
        System.out.println();
        int broj_parni = izbroj_parni(lista);
        System.out.println("Broj parni e: " + broj_parni);

        SLL<Integer> izbriseni = izbriseni_parni(lista);
        System.out.println(izbriseni);
        izbrisi_parni(lista);
        System.out.println(lista);

        lista = new SLL<>();
        popolni_lista(lista);
        System.out.println(lista);
        izbrisi_parni_v2(lista);
        System.out.println(lista);
    }

    private static SLL<Integer> izbriseni_parni(SLL<Integer> lst) {
        SLL<Integer> rez = new SLL<>();
        SLLNode<Integer> current = lst.getFirst();
        while (current != null) {
            Integer element = current.getElement();
            if (element % 2 != 0)
                rez.insertLast(element);
            current = current.getSucc();
        }
        return rez;
    }

    private static void izbrisi_parni(SLL<Integer> lst) {
        SLL<Integer> rez = new SLL<>();
        SLLNode<Integer> current = lst.getFirst();
        while (current != null) {
            Integer element = current.getElement();
            if (element % 2 != 0)
                rez.insertLast(element);
            current = current.getSucc();
        }
        lst.deleteList();
        current = rez.getFirst();
        while (current != null) {
            lst.insertLast(current.getElement());
            current = current.getSucc();
        }
    }

    private static void izbrisi_parni_v2(SLL<Integer> lst) {
        SLLNode<Integer> current = lst.getFirst();
        while (current != null) {
            Integer element = current.getElement();
            if (element % 2 == 0)
                lst.delete(current);
            current = current.getSucc();
        }
    }


    private static int izbroj_parni(SLL<Integer> lst) {
        SLLNode<Integer> current = lst.getFirst();
        int broj_parni = 0;
        while (current != null) {
            if (current.getElement() % 2 == 0)
                broj_parni++;
            current = current.getSucc();
        }
        return broj_parni;
    }

    private static void popolni_lista(SLL<Integer> lista) {
        lista.insertLast(4);
        lista.insertLast(5);
        lista.insertLast(8);
        lista.insertLast(2);
        lista.insertLast(3);
        lista.insertLast(1);
        lista.insertLast(0);
        lista.insertLast(8);
        lista.insertLast(9);
    }
}

class Aud1Zadaca3 {
    public static void main(String[] args) {
        SLL<Integer> lista = new SLL<>();
        popolni_lista(lista);
        System.out.println(lista);

        SLL<Integer> prevrtena = prevrtena_lista(lista);
        System.out.println(prevrtena);
        prevrti_lista(lista);
        System.out.println(lista);
    }

    private static void prevrti_lista(SLL<Integer> lista) {
        SLLNode<Integer> current = lista.getFirst();
        SLLNode<Integer> pred = null;
        while (current != null) {
            SLLNode<Integer> next = current.getSucc();
            current.setSucc(pred);
            pred = current;
            current = next;
        }
        lista.setFirst(pred);
    }

    private static SLL<Integer> prevrtena_lista(SLL<Integer> lista) {
        SLL<Integer> rez = new SLL<>();
        SLLNode<Integer> current = lista.getFirst();
        while (current != null) {
            rez.insertFirst(current.getElement());
            current = current.getSucc();
        }
        return rez;
    }

    private static void popolni_lista(SLL<Integer> lista) {
        lista.insertLast(4);
        lista.insertLast(5);
        lista.insertLast(8);
        lista.insertLast(2);
        lista.insertLast(3);
        lista.insertLast(1);
        lista.insertLast(0);
        lista.insertLast(8);
        lista.insertLast(9);
    }
}

class Aud1Zadaca4{
    public static void main(String[] args) {
        SLL<Integer> l1 = lista1();
        SLL<Integer> l2 = lista2();
        System.out.println(l1);
        System.out.println(l2);
        SLL<Integer> spoeno = spoj(l1,l2);
        System.out.println(spoeno);
    }

    private static <E extends Comparable<E>>SLL<E> spoj(SLL<E> l1, SLL<E> l2) {
        SLL<E> rez = new SLL<>();
        SLLNode<E> c1 = l1.getFirst();
        SLLNode<E> c2 = l2.getFirst();
        while(c1!= null || c2!= null){
            boolean add_from_first;
            if(c2==null||(c1 != null && c1.getElement().compareTo(c2.getElement()) > 0)){
                add_from_first = true;
            }else{
                add_from_first = false;
            }
            if(add_from_first){
                rez.insertLast(c1.getElement());
                c1 = c1.getSucc();
            }else{
                rez.insertLast(c2.getElement());
                c2 = c2.getSucc();
            }
        }

        return rez;
    }

    static SLL<Integer> lista1(){
        SLL<Integer> l = new SLL<>();
        l.insertLast(9);
        l.insertLast(7);
        l.insertLast(5);
        l.insertLast(3);
        l.insertLast(1);
        return l;
    }
    static SLL<Integer> lista2(){
        SLL<Integer> l = new SLL<>();
        l.insertLast(8);
        l.insertLast(5);
        l.insertLast(4);
        return l;
    }
}

class Aud1Zadaca5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name;
        SLL<String> lista = new SLL<>();
        do{
            name = input.nextLine();
            if(!name.equals("KRAJ")){
                Character c = name.charAt(0);
                boolean valid = Character.isUpperCase(c);
                if(valid){
                    lista.insertFirst(name);
                }
            }
        }while(!name.equals("KRAJ"));
        // print all names
        SLLNode<String> current = lista.getFirst();
        while(current != null){
            System.out.println(current.getElement());
            current = current.getSucc();
        }
    }
}