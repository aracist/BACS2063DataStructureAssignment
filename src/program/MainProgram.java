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
        arr.add(2, "soup");
        arr.add(1, "rice");
        
        System.out.println(arr.toString());
        arr.addAll(3,arr, 4, 2);
        
        for(String s : arr)
            System.out.println(s);

        ArrayList<String> arr2 = new ArrayList<>();
        arr2.addAll(arr);
//        System.out.println(arr.size());
//        System.out.println(arr.get(2));
//        System.out.println(arr.remove(2));
//        System.out.println(arr.get(2));
//        System.out.println(arr.remove(3));
        System.out.println(arr.toString());

//        LinkedList<String> ll = new LinkedList<>();
//        ll.add("Nou");
//        ll.add("Ya");
//        ll.add("Fkin");
//        ll.add("Dum");
//        ll.add("Dummy");
//        ll.add("Dumbo");
//        ll.add(6, "seh");
//        ll.replace(3, "gai");
//        System.out.println(ll.toString());
//        System.out.println(ll.remove(3));
//
//        //rr2.addAll(ll);
//        ll.addAll(arr2);
//        arr.clear();
//        arr.addAll(ll);
//        System.out.println(ll.toString());
//        for(String s : ll)
//            System.out.println(s);
//        System.out.println(arr.equals(ll));
    }
    
}
