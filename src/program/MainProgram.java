package program;
import java.util.ArrayList;
import adt.LinkedList;

public class MainProgram {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>(3);
        arr.addAll(0, arr);
        /*
        arr.add("zero");
        arr.add("one");
        arr.add("two");
        arr.add("three");
        
        arr.append(arr);
//        System.out.println(arr.size());
//        System.out.println(arr.get(2));
//        System.out.println(arr.remove(2));
//        System.out.println(arr.get(2));
//        System.out.println(arr.remove(3));
        System.out.println(arr.toString());
*/
        LinkedList<String> ll = new LinkedList<>();
        ll.add("Nou");
        ll.add("Ya");
        ll.add("Fkin");
        ll.add("Dum");
        ll.add("Dum");
        ll.add("Dummy");
        
        System.out.println(ll.toString());
    }
    
}
