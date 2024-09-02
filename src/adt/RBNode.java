package adt;

public class RBNode<T> {
    int hash;
    T data;
    RBNode parent;
    RBNode left;
    RBNode right;
    boolean color;

    RBNode(){}

    RBNode(T data){
        hash = data.hashCode();
        this.data = data;
    }
}
