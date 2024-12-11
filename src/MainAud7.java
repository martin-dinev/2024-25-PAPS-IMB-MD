import aud5.*;
import aud7.CBHT;
import aud7.MapEntry;
import aud7.OBHT;
import aud7.SLLNode;
import java.util.Map.Entry;

import java.util.*;

public class MainAud7 {
    public static void main(String[] args) {
        int [] brojaci = new int[100];
        HashMap<Long, Integer> mapa = new HashMap<>();
        CBHT<Long, Integer> cbht = new CBHT<>(100);
        OBHT<Long, Integer> obht = new OBHT<>(100);

        Long x = 123456788765L;

//        brojaci[x.intValue()] ++;

        Integer v = mapa.get(x);
        mapa.put(x, v+1);


        SLLNode<MapEntry<Long, Integer>> node = cbht.search(x);
        v = node.element.value;
        cbht.insert(x, v+1);

        int index = obht.search(x);
        MapEntry<Long, Integer> bucket = obht.getBucket(index);
        v = bucket.value;
        obht.insert(x, v+1);
    }
}

class EdgeCase{
    public static void main(String[] args) {
        int [] brojaci = new int[100];
        HashMap<Long, Integer> mapa = new HashMap<>();
        CBHT<Long, Integer> cbht = new CBHT<>(100);
        OBHT<Long, Integer> obht = new OBHT<>(100);

        Long x = 123456788765L;

//        brojaci[x.intValue()] ++;

        if(mapa.get(x) != null) {
            Integer v = mapa.get(x);
            mapa.put(x, v + 1);
            mapa.remove(x);
        }else{
            mapa.put(x, 1);
        }


        if(cbht.search(x) != null) {
            SLLNode<MapEntry<Long, Integer>> node = cbht.search(x);
            Integer v = node.element.value;
            cbht.insert(x, v + 1);
            cbht.delete(x);
        }else{
            cbht.insert(x, 1);
        }

        if(obht.search(x) != -1) {
            int index = obht.search(x);
            MapEntry<Long, Integer> bucket = obht.getBucket(index);
            Integer v = bucket.value;
            obht.insert(x, v + 1);
            obht.delete(x);
        }else{
            obht.insert(x, 1);
        }
    }
}


class ChemicalElement{
    String code;

    public ChemicalElement(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ChemicalElement{" +
                "code='" + code + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof ChemicalElement){
            ChemicalElement other = (ChemicalElement)obj;
            return other.code.equals(this.code);
        }
            return false;
    }
}

class CBHTTest {
    public static void main(String[] args) {
        CBHT<ChemicalElement, Integer> table1 = new CBHT<ChemicalElement, Integer>(5);
        System.out.println(table1);
        ChemicalElement kluc1 = new ChemicalElement("He");
        table1.insert(kluc1, 1);
        System.out.println(table1);

        ChemicalElement kluc2 = new ChemicalElement("He");

        System.out.println("Dali se isti " + (kluc1 == kluc2) + " " +  kluc1.equals(kluc2) + " " + kluc1.hashCode() + " " + kluc2.hashCode());

        SLLNode<MapEntry<ChemicalElement, Integer>> h = table1.search(kluc2);
        Integer value = h.element.value;
        System.out.println("Vrednosta e " + value);

        table1.insert(kluc2, 123);
        System.out.println(table1);

        h = table1.search(kluc2);
        value = h.element.value;
        System.out.println("Vrednosta e " + value);



    }
}


class Prva{
    public static void main(String[] args) {
        CBHT<Integer, Integer> cbht = new CBHT<>(23);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            sc.nextInt();
            sc.nextByte();
            int mesec = sc.nextInt();
            sc.nextByte();
            sc.nextInt();

            int vrednost;
            SLLNode<MapEntry<Integer, Integer>> node = cbht.search(mesec);
            if(node != null) vrednost = node.element.value;
            else vrednost = 0;
            cbht.insert(mesec, vrednost + 1);
        }
        int baranje = sc.nextInt();
        int vrednost;
        SLLNode<MapEntry<Integer, Integer>> node = cbht.search(baranje);
        if(node != null) vrednost = node.element.value;
        else vrednost = 0;
        System.out.println("Brojot na rodendeni vo toj mesec e " + vrednost);
    }
}
class PrvaKopija{
    public static void main(String[] args) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            sc.nextInt();
            sc.nextByte();
            int mesec = sc.nextInt();
            sc.nextByte();
            sc.nextInt();

            int vrednost = hm.getOrDefault(mesec, 0);
            hm.put(mesec, vrednost+1);
        }
        int baranje = sc.nextInt();
        int vrednost = hm.getOrDefault(baranje, 0);
        System.out.println("Brojot na rodendeni vo toj mesec e " + vrednost);

        // ako sakame da ispechatime se
        for (Entry<Integer, Integer> entry : hm.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
class Ponuda{
    String datum, mesto, cas;
    int vrednost;

    public Ponuda(String datum, String cas, String mesto,  int vrednost) {
        this.datum = datum;
        this.mesto = mesto;
        this.cas = cas;
        this.vrednost = vrednost;
    }
}
class Vtora{
    public static void main(String[] args) {
        HashMap<String, Ponuda> hm = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            Ponuda ponuda = new Ponuda(sc.next(), sc.next(), sc.next(), sc.nextInt());

            Ponuda postoecka = hm.get(ponuda.datum);
            if(postoecka == null) hm.put(ponuda.datum, ponuda);
            else{
                if(ponuda.vrednost > postoecka.vrednost)
                    hm.put(ponuda.datum, ponuda);
            }
        }
        String datum = sc.next();
        Ponuda najdobra = hm.get(datum);
        if(najdobra == null) System.out.println("nema ponuda za toj datum");
        else
            System.out.println("Najd pon vo toj dat e " + najdobra.vrednost);
    }
}