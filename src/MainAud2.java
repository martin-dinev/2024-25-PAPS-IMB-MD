//import aud2.DLL;
//import aud2.DLLNode;

import java.util.Scanner;

public class MainAud2 {
    public static void main(String[] args) {
        DLL<String> dll = new DLL<>();
        dll.insertLast("Ime1");
        dll.insertLast("Ime2");
        dll.insertLast("Ime3");
        dll.insertLast("Ime4");
        DLLNode<String> curr = dll.getFirst();
        while (curr != null) {
            DLLNode<String> move_to = curr.getSucc();
            if(curr.element.endsWith("3")){
                dll.insertAfter("Target", curr);
            }
            curr = move_to;
        }
    }
}

class ZadacaBrisi {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        DLL<Integer> dll1 = new DLL<>();
        int n = cin.nextInt();
        for (int i = 0; i < n; i++) {
            dll1.insertLast(cin.nextInt());
        }
        DLL<Integer> dll2 = new DLL<>();
        int m = cin.nextInt();
        for (int i = 0; i < m; i++) {
            dll2.insertLast(cin.nextInt());
        }
        brisi_podlisti(dll1, dll2);
        pecati_lista(dll1);
//        pecati_lista(dll2);
    }

    private static void brisi_podlisti(DLL<Integer> dll1, DLL<Integer> dll2) {
        int n = dll1.getSize();
        int m = dll2.getSize();
        DLLNode<Integer> curr = dll1.getFirst();
        while(curr!=null){
            boolean brisi = dali_treba(curr,dll2);
            if(brisi){
                for (int i = 0; i < m; i++) {
                    dll1.delete(curr);
                    curr = curr.getSucc();
                }
            }else {
                curr = curr.getSucc();
            }
            System.out.print("");
        }

    }

    private static boolean dali_treba(DLLNode<Integer> curr, DLL<Integer> dll2) {
        DLLNode<Integer> vtor = dll2.getFirst();
        while(curr!=null || vtor!=null){
            if(curr == null) return false;
            if(vtor == null) return true;
            if(!curr.getElement().equals(vtor.getElement()))
                return false;
            curr = curr.getSucc();
            vtor = vtor.getSucc();
        }
        return true;

    }
    private static void pecati_lista(DLL<Integer> dll) {
        DLLNode<Integer> curr = dll.getFirst();
        while(curr != null){
            System.out.print(curr.getElement() + " ");
            curr = curr.getSucc();
        }
        System.out.println();
    }

}
class ZadacaPodeli{
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        DLL<Integer> dll = new DLL<>();
        int n = cin.nextInt();
        for (int i = 0; i < n; i++) {
            dll.insertLast(cin.nextInt());
        }
//        System.out.println(dll);
        pecati_lista(dll);
        DLL<Integer> rez1 = new DLL<>();
        DLL<Integer> rez2 = new DLL<>();

        podeli_lista(dll,rez1,rez2);
        pecati_lista(rez1);
        pecati_lista(rez2);
    }

    private static void podeli_lista(DLL<Integer> dll, DLL<Integer> rez1, DLL<Integer> rez2) {
        float average = 0;
        DLLNode<Integer> curr = dll.getFirst();
        while(curr != null){
            average = average + curr.getElement();
            curr = curr.getSucc();
        }
        average = average / dll.getSize();
        curr = dll.getLast();
        while(curr != null){
            if(curr.getElement()<=average){
                rez1.insertLast(curr.getElement());
            }else{
                rez2.insertLast(curr.getElement());
            }
            curr = curr.getPred();
        }
    }

    private static void pecati_lista(DLL<Integer> dll) {
        DLLNode<Integer> curr = dll.getFirst();
        while(curr != null){
            System.out.print(curr.getElement() + " ");
            curr = curr.getSucc();
        }
        System.out.println();
    }
}

class ZadacaPalindrom{
    public static void main(String[] args) {
        DLL<Integer> dll = new DLL<>();
        dll.insertLast(1);
        dll.insertLast(2);
        dll.insertLast(3);
        dll.insertLast(4);
        dll.insertLast(3);
        dll.insertLast(2);
        dll.insertLast(1);
        System.out.println(dll);
        if(dali_e_palindrom(dll))
            System.out.println("Listata e palindrom");
        else
            System.out.println("Listata ne e palindrom");
    }

    private static boolean dali_e_palindrom(DLL<Integer> dll) {
        DLLNode<Integer> left = dll.getFirst();
        DLLNode<Integer> right = dll.getLast();
//        while(left != right && left.getPred() != right){
//        while(left != null && right != null){
        for (int i = 0; i < dll.getSize() / 2; i++) {
            if(!left.getElement().equals(right.getElement())){
                return false;
            }
            left = left.getSucc();
            right = right.getPred();
        }
        return true;
    }
}

class ZadacaMirror{
    public static void main(String[] args) {
        DLL<String> dll = new DLL<>();
        dll.insertLast("Ime1");
        dll.insertLast("Ime2");
        dll.insertLast("Ime3");
        dll.insertLast("Ime4");
        System.out.println(dll);
        prevrti_lista(dll);
        System.out.println(dll);
        System.out.println(prevrtena_lista(dll));

//        System.out.println(dll.getFirst().getPred());
    }

    private static void prevrti_lista(DLL<String> dll) {
        DLLNode<String> curr = dll.getFirst();
        while(curr != null){
            DLLNode<String> old_pred = curr.getPred();
            DLLNode<String> old_succ = curr.getSucc();
            curr.setPred(old_succ);
            curr.setSucc(old_pred);
            curr = old_succ;
        }
        DLLNode<String> old_first = dll.getFirst();
        DLLNode<String> old_last = dll.getLast();
        dll.setFirst(old_last);
        dll.setLast(old_first);
    }
    private static DLL<String> prevrtena_lista(DLL<String> dll) {
        DLL<String> res = new DLL<>();
        DLLNode<String> curr = dll.getLast();
        while(curr != null){
            res.insertLast(curr.getElement());
            curr = curr.getPred();
        }
        return res;
    }


}

class DLL<E> {
    private DLLNode<E> first, last;

    public void setFirst(DLLNode<E> first) {
        this.first = first;
    }

    public void setLast(DLLNode<E> last) {
        this.last = last;
    }

    public DLL() {
        // Construct an empty SLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.pred = ins;
        first = ins;
    }

    public void insertLast(E o) {
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<E>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            if (first != null) first.pred = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.pred;
                last.succ = null;
                return tmp.element;
            }
        } else
            return null;
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;
        return node.element;

    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> tmp = first;
            ret += tmp.toString();
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public String toStringR() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> tmp = last;
            ret += tmp.toString();
            while (tmp.pred != null) {
                tmp = tmp.pred;
                ret += "<->" + tmp.toString();
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {

        return last;
    }

    public void mirror() {

        DLLNode<E> tmp = null;
        DLLNode<E> current = first;
        last = first;
        while(current!=null) {
            tmp = current.pred;
            current.pred = current.succ;
            current.succ = tmp;
            current = current.pred;
        }

        if(tmp!=null && tmp.pred!=null) {
            first=tmp.pred;
        }
    }
}

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;
    public DLLNode(E elem, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = elem;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }

    public E getElement() {
        return element;
    }

    public DLLNode<E> getPred() {
        return pred;
    }

    public DLLNode<E> getSucc() {
        return succ;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setPred(DLLNode<E> pred) {
        this.pred = pred;
    }

    public void setSucc(DLLNode<E> succ) {
        this.succ = succ;
    }
}


