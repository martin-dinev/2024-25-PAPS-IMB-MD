package aud8_2;
public class Problem9 {
    public static void main(String[] args) {
        BTree<String> tree = new BTree<String>();
        tree.makeRoot("Parent");
        BNode<String> root = tree.root;
        BNode<String> left = tree.addChild(root, BNode.LEFT, "Left");
        BNode<String> right = tree.addChild(root, BNode.RIGHT, "Right");
        BNode<String> left_left = tree.addChild(left, BNode.LEFT, "Left_Left");
        BNode<String> left_right = tree.addChild(left, BNode.RIGHT, "Left_Right");
        BNode<String> right_left = tree.addChild(right, BNode.LEFT, "Right_Left");
        BNode<String> right_right = tree.addChild(right, BNode.RIGHT, "Right_Right");

        System.out.println("Path to root: " + pathToRoot(left_right));
        System.out.println("Path to root: " + pathToRoot(right_left));
        System.out.println("Path to root: " + pathToRooot(tree.root,left_right));
        System.out.println("Path to root: " + pathToRooot(tree.root,right_left));
    }

    // public BNode<E> parent; // add a parent field in BNode
    // public BNode(E info) { // change the constructor in BNode::BNode(E)
    //     this.parent = null;
    //     ...
    // public BNode(E info, BNode<E> parent) { // create a new constructor
    //     this.parent = parent;
    //     ...
    // BNode<E> tmp = new BNode<E>(elem, node); // change in BTree::addChild (first line)
    static String pathToRoot(BNode<String> node){
        String path = "";
        while(node != null){
            path = "<" + node.info + "> " + path;
            node = node.parent;
        }
        return path;
    }

    public static String pathToRooot(BNode<String> n, BNode<String> b) {
        if (n != null) {
            String now = "<" + n.info + "> ";
            if(n==b) return now;

            String left_rez = pathToRooot(n.left, b);
            String right_rez = pathToRooot(n.right, b);
            String momentalen = !left_rez.isEmpty() || !right_rez.isEmpty() ? now + left_rez + right_rez :"";
            return momentalen;
        }else{
            return "";
        }
    }
}


