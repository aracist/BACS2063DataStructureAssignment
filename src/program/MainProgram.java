package program;
import adt.ArrayList;
import adt.LinkedList;

public class MainProgram {
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>(3);
        
        arr.add("zero");
        arr.add("one");
        arr.add("two");
        arr.add("three");
        
        arr.addAll(arr);

        ArrayList<String> arr2 = new ArrayList<>();
        arr2.addAll(arr);
//        System.out.println(arr.size());
//        System.out.println(arr.get(2));
//        System.out.println(arr.remove(2));
//        System.out.println(arr.get(2));
//        System.out.println(arr.remove(3));
        System.out.println(arr2.toString());

        LinkedList<String> ll = new LinkedList<>();
        ll.add("Nou");
        ll.add("Ya");
        ll.add("Fkin");
        ll.add("Dum");
        ll.add("Dummy");
        ll.add("Dumbo");
        ll.add(6, "seh");
        ll.replace(3, "gae");
        System.out.println(ll.toString());
        System.out.println(ll.remove(3));

        //rr2.addAll(ll);
        ll.addAll(arr2);
        System.out.println(ll.toString());
    }
    
}
