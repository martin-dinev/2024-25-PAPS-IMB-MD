package aud8_1;
public class SLLTreeTest {

	public static void main(String[] args) {

		Tree.Node<String> a, b, c, d;

		Tree<String> t = new SLLTree<String>();

		t.makeRoot("C:");

		a = t.addChild(t.root(), "Program files");
		b = t.addChild(a, "CodeBlocks");
		c = t.addChild(b, "codeblocks.dll");
		c = t.addChild(b, "codeblocks.exe");
		b = t.addChild(a, "Nodepad++");
		c = t.addChild(b, "langs.xml");
		d = c;
		c = t.addChild(b, "readme.txt");
		c = t.addChild(b, "notepad++.exe");
		a = t.addChild(t.root(), "Users");
		b = t.addChild(a, "Darko");
		c = t.addChild(b, "Desktop");
		c = t.addChild(b, "Downloads");
		c = t.addChild(b, "My Documents");
		c = t.addChild(b, "My Pictures");
		b = t.addChild(a, "Public");
		a = t.addChild(t.root(), "Windows");
		b = t.addChild(a, "Media");

		((SLLTree<String>)t).printTree();

		t.remove(d);
		((SLLTree<String>)t).printTree();

		System.out.println("The maximum number of children is "
				+ ((SLLTree<String>)t).countMaxChildren());
		System.out.println("Here it goes");
		for(Tree.Node<String> tn: t.childrenNodes(t.root())){
			System.out.print("Current: ");
			System.out.println(tn.getElement());
		}
	}

}
