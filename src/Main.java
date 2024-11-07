import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Box<Integer> box = new Box<Integer>(3);


        int[][] arr = {null,{1, 2, 3}, {4, 5, 6, 5, 6, 7, 8}};
        System.out.println(arr.length);

        for (int i = 0; i < 2; i++) {
            int products = input.nextInt();
            arr[i] = new int[products];
            for (int j = 0; j < 5; j++) {
                arr[i][j] = i+j;
            }
        }
    }
}

class Box <T>{
    T[] arr;
    Box(int size){
        arr = (T[]) new Object[size];
    }
    void setElement(T element, int index){
         arr[index] = element;
    }
    T getElement(int index) {
        return arr[index];
    }
}
class Meal {
    Meal() { System.out.println("Meal()"); }}
class Bread {
    Bread() { System.out.println("Bread()"); }
}
class Cheese {
    Cheese() { System.out.println("Cheese()"); }
}
class Lettuce {
    Lettuce() { System.out.println("Lettuce()"); }
}
class Lunch extends Meal {
    Lunch() { System.out.println("Lunch()");}
}
class PortableLunch extends Lunch {
    PortableLunch() {
        System.out.println("PortableLunch()");
    }
}
class Sandwich extends PortableLunch {
    Bread b = new Bread();
    Cheese c = new Cheese();
    Lettuce l = new Lettuce();
    Sandwich() {
        System.out.println("Sandwich()");
    }
    public static void main(String[] args) {
        new Sandwich();
    }
}