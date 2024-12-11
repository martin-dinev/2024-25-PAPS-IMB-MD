import aud8_1.SLLTree;
import aud8_1.Tree;
import aud8_2.BNode;
import aud8_2.BTree;

import java.util.Scanner;
import java.util.Stack;


class MainAud8 {
    public static void main(String[] args) {
        Tree<String> tree = new SLLTree<>();

        tree.makeRoot("Archy");
        Tree.Node<String> archy = tree.root();

        tree.addChild(archy, "Joe");
        tree.addChild(archy, "Colin");
        Tree.Node<String> frank = tree.addChild(archy, "Frank");
        Tree.Node<String> george = tree.addChild(archy, "George");
        tree.addChild(george, "Fred");
        Tree.Node<String> ann = tree.addChild(frank, "Ann");
        tree.addChild(frank, "Maggie");
        Tree.Node<String> david = tree.addChild(frank, "David");
        tree.addChild(ann, "Jon");
        tree.addChild(ann, "Emma");
        Tree.Node<String> jeff = tree.addChild(david, "Jeff");
        tree.addChild(david, "Susie");

        System.out.println(jeff.getElement());
        System.out.println(tree.parent(jeff).getElement());
        System.out.println("Frank has " + tree.childCount(frank) + " children");


        for (Tree.Node<String> dete : tree.childrenNodes(archy)) {
            System.out.print(dete.getElement() + " ");
        }

        System.out.println();
        pecati_drvo(tree);

        System.out.println("Stepenot e " + stepenNaDrvo(tree));

    }

    private static <E> int stepenNaDrvo(Tree<E> tree) {
        return stepenNaDrvoRekurzivno(tree.root(), tree);
    }

    private static <E> int stepenNaDrvoRekurzivno(Tree.Node<E> node, Tree<E> tree) {
        int rez = tree.childCount(node);
        for (Tree.Node<E> child : tree.childrenNodes(node)) {
            int child_rez = stepenNaDrvoRekurzivno(child, tree);
            rez = Math.max(rez, child_rez);
        }
        return rez;
    }

    private static <E> void pecati_drvo(Tree<E> tree) {
        pecati_rekurzivno(tree.root(), tree, 0);
    }

    private static <E> void pecati_rekurzivno(Tree.Node<E> node, Tree<E> tree, int nivo) {
        for (int i = 0; i < nivo; i++)
            System.out.print("    ");
        System.out.println(node.getElement());
        for (Tree.Node<E> child : tree.childrenNodes(node)) {
            pecati_rekurzivno(child, tree, nivo + 1);
        }
    }
}

class Hello {
    public static void main(String[] args) {
        BTree<Integer> drvo = new BTree<>(15);
        BNode<Integer> root = drvo.root;
        BNode<Integer> n6 = drvo.addChild(root, BNode.LEFT, 6);
        BNode<Integer> n18 = drvo.addChild(root, BNode.RIGHT, 18);
        BNode<Integer> n17 = drvo.addChild(n18, BNode.LEFT, 17);
        BNode<Integer> n20 = drvo.addChild(n18, BNode.RIGHT, 20);
        BNode<Integer> n3 = drvo.addChild(n6, BNode.LEFT, 3);
        BNode<Integer> n7 = drvo.addChild(n6, BNode.RIGHT, 7);
        BNode<Integer> n13 = drvo.addChild(n7, BNode.RIGHT, 13);
        BNode<Integer> n9 = drvo.addChild(n13, BNode.LEFT, 9);
        BNode<Integer> n2 = drvo.addChild(n3, BNode.LEFT, 2);
        BNode<Integer> n4 = drvo.addChild(n3, BNode.RIGHT, 4);

        drvo.inorder();
        pecatiInorder(drvo);

        System.out.println("Brojot na vnatreshni jazli e " + br_vnatresni(drvo.root));
        System.out.println("Brojot na listovi jazli e " + br_listovi(drvo.root));
        System.out.println("Max dlabocina " + max_dlabocina(drvo.root));

    }

    private static int max_dlabocina(BNode<Integer> n) {
        if (n != null) {
            int left_rez = br_vnatresni(n.left);
            int right_rez = br_vnatresni(n.right);
            return Math.max(left_rez, right_rez) + 1;
        } else {
            return 0;
        }
    }

    private static int br_vnatresni(BNode<Integer> n) {
        if (n != null) {
            int left_rez = br_vnatresni(n.left);
            int momentalen = n.left != null || n.right != null ? 1 : 0;
            int right_rez = br_vnatresni(n.right);
            return left_rez + momentalen + right_rez;
        } else {
            return 0;
        }
    }

    private static int br_listovi(BNode<Integer> n) {
        if (n != null) {
            int left_rez = br_vnatresni(n.left);
            int momentalen = n.left != null || n.right != null ? 0 : 1;
            int right_rez = br_vnatresni(n.right);
            return left_rez + momentalen + right_rez;
        } else {
            return 0;
        }
    }

    static class Helper<E> {
        public BNode<E> node;
        public int status;

        public Helper(BNode<E> node, int status) {
            this.node = node;
            this.status = status;
        }
    }

    private static <E> void pecatiInorder(BTree<E> drvo) {
        Stack<Helper<E>> stack = new Stack<>();
        BNode<E> node = drvo.root;
        stack.push(new Helper<>(node, 0));
        while (!stack.isEmpty()) {
            while (node.left != null) {
                stack.push(new Helper<>(node.left, 0));
                node = node.left;
            }
            Helper<E> pop = stack.pop();
            BNode<E> prnode = pop.node;
            System.out.print(prnode.info + " ");

            if (prnode.right != null) {
                stack.push(new Helper<>(prnode.right, 0));
                node = prnode.right;
            }

        }
        System.out.println();
    }
}

class FXT {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String X = sc.next();
            char[] chars = X.toCharArray();
            int T = sc.nextInt(), momentalna_razlika = 0;
            for (int i = 0; i < X.length() && momentalna_razlika < T; i++) {
                if (chars[i] == 'a') continue;
                else {
                    chars[i] = 'a';
                    momentalna_razlika++;
                }
            }
            for (int i = X.length() - 1; i >= 0 && momentalna_razlika < T; i--) {
                if (X.charAt(i) == 'a') {
                    chars[i] = 'b';
                    momentalna_razlika++;
                } else {
                    continue;
                }
            }
            String s = new String(chars);
            System.out.println(s);

        }
    }
}
/*
"""
baa
aaabb
aaaaa
ayzabcaaabbbxyz
aaaabcaaabbbxyz
aaaaaaaaabbbxyz
aaaaaaaaaaabxyz
aaaaaaaaaaaaayz
aaaaaaaaaaaaaaa
aaaaaaabbaaaaaa
aaabaabbbaaaaaa
aabbaa
aaaacc
"""*/
