package program;
import adt.ArrayList;

public class MainProgram {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>(3);
        arr.add("zero");
        arr.add("one");
        arr.add("two");
        arr.add("three");
        System.out.println(arr.size());
        System.out.println(arr.get(2));
        System.out.println(arr.remove(2));
        System.out.println(arr.get(2));
        System.out.println(arr.remove(2));
    }
    
}
