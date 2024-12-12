import aud9.binarysearchtree.BNode;
import aud9.binarysearchtree.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ResultWrapper<E>{
    E val;
}

public class MainAud9 {
    static Integer resultStatic = null;

    public static void main(String[] args) {
        BinarySearchTree<Integer> bstree = new BinarySearchTree<>();
        bstree.insert(56);
        bstree.insert(26);
        bstree.insert(200);
        bstree.insert(18);
        bstree.insert(28);
        bstree.insert(190);
        bstree.insert(213);
        bstree.insert(12);
        bstree.insert(24);
        bstree.insert(27);
//        bstree.printTreeWithIndent();

//        System.out.println(pathToNode(bstree.getRoot(),190));
        System.out.println("Patekata megju 27 i 190 e " + patekaString(bstree, 27, 190));
        System.out.println("Patekata megju 27 i 190 ima suma " + patekaSum(bstree, 27, 190));

        List<Integer> leaves = new ArrayList<>();
        getLeaves(bstree.getRoot(), leaves);
        System.out.println(leaves);
        Integer maxVal = null;
        for (int i = 0; i < leaves.size(); i++) {
            for (int j = i+1; j < leaves.size(); j++) {
                Integer currentVal = patekaSum(bstree, leaves.get(i), leaves.get(j));
                if(maxVal == null || maxVal < currentVal) maxVal = currentVal;
                System.out.println(""+leaves.get(i) + " "+leaves.get(j) +" " + currentVal);
            }
        }
        System.out.println("Maksimalnata od niv e " + maxVal);
        resultStatic = null;
        ResultWrapper<Integer> result = new ResultWrapper<>();
        System.out.println("Maksimalnata pateka do list nive e " + getMaxSumToAnyLeaf(bstree.getRoot(), result));
        System.out.println("Maks pat od site e " + resultStatic);
        System.out.println("Maks pat od site e " + result.val);


    }


    private static Integer getMaxSumToAnyLeaf(BNode<Integer> node, ResultWrapper<Integer> result) {
        if(node == null) return 0;
        Integer maxLeft = getMaxSumToAnyLeaf(node.left, result);
        Integer maxRight = getMaxSumToAnyLeaf(node.right, result);
        if(maxLeft != 0 && maxRight != 0){
            Integer maxPath = maxLeft + maxRight + node.info;
            if(result.val == null || maxPath > result.val) result.val = maxPath;
            if(resultStatic == null || maxPath > resultStatic) resultStatic = maxPath;
        }


        return node.info + Math.max(maxLeft,maxRight);
    }


    private static void getLeaves(BNode<Integer> node, List<Integer> result) {
        if(node == null) return;
        if(node.left == null && node.right == null){
            result.add(node.info);
            return;
        }
        getLeaves(node.left, result);
        getLeaves(node.right, result);
    }


    private static Integer patekaSum(BinarySearchTree<Integer> bstree, int smaller, int greater) {
        return sumPatekaR(bstree.getRoot(), smaller, greater);
    }

    private static String patekaString(BinarySearchTree<Integer> bstree, int smaller, int greater) {
        return stringPatekaR(bstree.getRoot(), smaller, greater);
    }


    private static Integer sumPatekaR(BNode<Integer> node, int smaller, int greater) {
        if (greater < node.info) return sumPatekaR(node.left, smaller, greater);
        if (smaller > node.info) return sumPatekaR(node.right, smaller, greater);
        Integer leftPath = sumPathToNode(node.left, smaller); // 26 28 27
        Integer rightPath = sumPathToNode(node.right, greater); // 200 190
        return leftPath + rightPath + node.info;
    }

    private static String stringPatekaR(BNode<Integer> node, int smaller, int greater) {
        if (greater < node.info) return stringPatekaR(node.left, smaller, greater);
        if (smaller > node.info) return stringPatekaR(node.right, smaller, greater);
        String leftPath = stringPathToNode(node.left, smaller); // 26 28 27
        String rightPath = stringPathToNode(node.right, greater); // 200 190
        // 27 28 26 56 200 190
        String[] strings = leftPath.split(" ");
        String rez = "";
        for (int i = strings.length - 1; i >= 0; i--) {
            rez += strings[i] + " ";
        }
        rez += node.info + " ";
        rez += rightPath;
        return rez;
    }

    private static String stringPathToNode(BNode<Integer> node, int value) {
        if (node == null) return "";
        if (node.info == value) return "" + node.info;
        String left_path = stringPathToNode(node.left, value);
        String right_path = stringPathToNode(node.right, value);
        if (left_path.isEmpty() && right_path.isEmpty()) return "";
        return node.info + " " + left_path + right_path;
    }

    private static Integer sumPathToNode(BNode<Integer> node, int value) {
        if (node == null) return 0;
        if (node.info == value) return  node.info;
        Integer left_path = sumPathToNode(node.left, value);
        Integer right_path = sumPathToNode(node.right, value);
        if (left_path == 0 && right_path == 0) return 0;
        return node.info + left_path + right_path;
    }



}

class Aud9Zadaca3{
    public static void main(String[] args) {
        Scanner sc = new Scanner("10\n56 26 18 200 28 190 213 12 24 27");
        BinarySearchTree<Integer> btree = new BinarySearchTree<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            btree.insert(sc.nextInt());

            ResultWrapper<Boolean> result = new ResultWrapper<>();
            result.val = true;
            int depth = depthR(btree.getRoot(), result);
            System.out.println("Drvoto e balansirano " + result.val);
        }
    }

    private static int depthR(BNode<Integer> node, ResultWrapper<Boolean> result) {
        if(node == null)return 0;
        int left_depth = depthR(node.left, result);
        int right_depth = depthR(node.right, result);
        if(Math.abs(left_depth - right_depth) > 1) result.val = false;
        return 1+Math.max(left_depth, right_depth);
    }
}


class Aud9Zadaca4{
    public static void main(String[] args) {
        Scanner sc = new Scanner("10\n56 26 18 200 28 190 213 12 24 27");
        BinarySearchTree<Integer> btree = new BinarySearchTree<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            btree.insert(sc.nextInt());
            if(i<2)continue;
            int kth = kthLargest(btree.getRoot(), 3);
            System.out.println(kth);
        }
    }

    private static int kthLargest(BNode<Integer> node, int k) {
        int subtreeSize = subtreeSize(node.right);
        if(subtreeSize >= k) return kthLargest(node.right, k);
        k -= subtreeSize;
        if(k == 1) return node.info;
        k --;
        return kthLargest(node.left, k);
    }

    private static int subtreeSize(BNode<Integer> node) {
        if(node==null)return 0;
        return 1+subtreeSize(node.left)+subtreeSize(node.right);
    }
}



class Aud9Zadaca5{
    public static void main(String[] args) {
        Scanner sc = new Scanner("10\n56 26 18 200 28 190 213 12 24 27");
        BinarySearchTree<Integer> btree = new BinarySearchTree<>();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            btree.insert(val);
            int prev = findPrev(btree.getRoot(),val );
            System.out.println(prev);
        }
    }

    private static int findPrev(BNode<Integer> node, int val) {
        if(node.info == val)return findMax(node.left);
        if(node.info > val)return findPrev(node.left, val);
        if(findMin(node.right) == val)return node.info;
        return findPrev(node.right, val);
    }

    private static int findMax(BNode<Integer> node) {
        while(node.right!=null) node = node.right;
        return node.info;
    }
    private static int findMin(BNode<Integer> node) {
        while(node.left!=null) node = node.left;
        return node.info;
    }

}