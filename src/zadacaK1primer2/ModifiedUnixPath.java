package zadacaK1primer2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ModifiedUnixPath {

    public static String simplifyPath(String path) {
        //Vashiot kod odi ovde
        String[] strings = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].equals("")) {
                continue;
            } else if (strings[i].startsWith(".")) {
                String number = strings[i].substring(1);
                if (number.equals("")) continue;
                int count_pop = Integer.parseInt(number);
                for (int j = 0; j < count_pop; j++) {
                    if (!stack.isEmpty())
                        stack.pop();
                }
            } else {
                stack.push(strings[i]);
            }
        }
        List<String> lst = new LinkedList<>();
        while (!stack.empty()) {
            lst.add(0, stack.pop());
        }
        String rez = "";
        for (int i = 0; i < lst.size(); i++) {
            rez += "/" + lst.get(i);
        }
        return rez;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String path = input.nextLine();
//        String path = "/abc///cde/xyz/.1/asdf//a1.b2/newdir/.2/a/";

        input.close();

        System.out.println(simplifyPath(path));
    }
}
