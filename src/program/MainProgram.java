package program;
import adt.ArrayList;
import adt.LinkedList;
import adt.PriorityQueue;
import adt.RBTree;

public class MainProgram {
    public static void main(String[] args) {
//        ArrayList<String> arr = new ArrayList<>(3);
//        
//        arr.add("zero");
//        arr.add("one");
//        arr.add("two");
//        arr.add("three");
//        arr.add(2, "soup");
//        arr.add(1, "rice ouz".replaceAll("\\s+","").toLowerCase());
//        System.out.println(arr.get(1).hashCode());
//        
//        System.out.println(arr.toString());
//        arr.addAll(3,arr,4,3);
//        
////        for(String s : arr)
////            System.out.println(s);
//
//        ArrayList<String> arr2 = new ArrayList<>();
//        arr2.addAll(arr);
////        System.out.println(arr.size());
////        System.out.println(arr.get(2));
////        System.out.println(arr.remove(2));
////        System.out.println(arr.get(2));
////        System.out.println(arr.remove(3));
//        System.out.println(arr.toString());
//
//        LinkedList<String> ll = new LinkedList<>();
////        ll.add("Nou");
////        ll.add("Ya");
////        ll.add("Fkin");
////        ll.add("Dum");
////        ll.add("Dummy");
////        ll.add("Dumbo");
////        ll.add(7 ,"seh");
////        ll.replace(3, "gai");
////        System.out.println(ll.toString());
////        System.out.println(ll.remove(3));
////        System.out.println(ll.toString());
////        
////        ll.addAll(ll);
////        System.out.println(ll.toString());
////
////        //rr2.addAll(ll);
//        ll.addAll(arr2);
////        arr.clear();
////        arr.addAll(ll);
//        for(String s : ll)
//            System.out.println(s);
//        System.out.println(ll.equals(ll));
//        RBTree<Integer> rbt = new RBTree<>();
//        rbt.insert(4);
//        rbt.insert(7);
//        rbt.insert(12);
//        rbt.insert(15);
//        rbt.insert(3);
//        rbt.insert(5);
//        rbt.insert(14);
//        rbt.insert(18);
//        rbt.insert(16);
//        rbt.insert(17);
//        rbt.insertAll(new Integer[]{6,40,10,4,19,20,11,8});
//
////        rbt.delete(3);
////        rbt.delete(12);
////        rbt.delete(17);
////        rbt.delete(18);
////        rbt.delete(15);
////        rbt.delete(16);
//        //System.out.println(rbt.toString());
//        //System.out.println(rbt.get(12));
//        rbt.inOrder();
//        
//        
//        
//        Object[] itarr = rbt.toArray();
//        //System.out.println(itarr.getClass());
//
//        for (Object itarr1 : itarr) {
//            System.out.println((int)itarr1);
//        }
//        System.out.println(rbt.size());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.enqueue(10);
        pq.enqueue(20);
        pq.enqueue(30);
        for(Integer elem : pq)
            System.out.println(elem);
    }
    
}
