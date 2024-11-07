package aud2;

public class DLLNode<E> {
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
}


