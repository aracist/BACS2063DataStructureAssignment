package adt;

import java.util.Iterator;

public class RBTree<T> implements BinaryTreeInterface<T>, CollectionInterface<T> {
    RBNode<T> root;
    int elementCount;
    
    static final boolean RED = false;
    static final boolean BLACK = true;

    private static class NilNode extends RBNode {
      private NilNode() {
        super();
        this.color = BLACK;
      }
    }
    
    @Override
    public T search(int hash) {
        RBNode node = root;
        while (node != null) {
            if (hash == node.hash) {
              return (T)node.data;
            } else if (hash < node.hash) {
              node = node.left;
            } else {
              node = node.right;
            }
        }

        return null;
    }
    
    // ===== Insertion =====
    public boolean insertAll(T[] dataArr){
        return insertAll(dataArr, 1, dataArr.length);
    }
    
    public boolean insertAll(T[] dataArr, int startingPosition){
        return insertAll(dataArr, startingPosition, dataArr.length);
    }
    
    public boolean insertAll(T[] dataArr, int startingPosition, int endingPosition){
        for (int i = startingPosition - 1; i < endingPosition; i++) {
            // Failed to insert
            if(!insert(dataArr[i]))
                return false;
        }
        return true;
    }
    
    @Override
    public boolean insert(T data) {
        RBNode node = root;
        RBNode parent = null;
        int hash = data.hashCode();

        while (node != null) {
            parent = node;
            
            if(hash < node.hash) 
                node = node.left;
            else if(hash > node.hash) 
                node = node.right;
            else 
                return false;
        }

        //Determine which side to append the node to 
        RBNode newNode = new RBNode(data);
        
        if(parent == null) 
            root = newNode;
        else if(hash < parent.hash) 
            parent.left = newNode;
        else 
            parent.right = newNode;
        
        newNode.parent = parent;

        fixAfterInsert(newNode);
        elementCount++;
        return true;
    }

    private void fixAfterInsert(RBNode node) {
        RBNode parent = node.parent;

        //Stop if node is root
        if (parent == null) {
            //Ensure root is black;
            node.color = BLACK;
            return;
        }

        //Only need fixing if parent is red (Double Red Situation)
        if (parent.color != RED)
            return;
      
        RBNode gParent = parent.parent;
      
        //Parent is root
        if (gParent == null) {
            parent.color = BLACK;
            return;
        }

        RBNode uncle = getUncle(parent);

        //FOR WHEN UNCLE IS RED
        //If uncle = null then uncle is BLACK
        if (uncle != null && uncle.color == RED){
            //Reverse the color
            parent.color = BLACK;
            gParent.color = RED;
            uncle.color = BLACK;
            
            //Since gParent became red, check if it causes any double red
            fixAfterInsert(gParent);
        }
        
        //UNDER IS FOR WHEN UNCLE IS BLACK
        //Parent is a left child
        else if (parent == gParent.left) {
            //Node is inner child of gParent
            if (node == parent.right) {
                rotateLeft(parent);

                //For recoloring
                parent = node;
            }

            //Node is outer child of gParent
            rotateRight(gParent);
            
            //Recolor original parent and gParent
            parent.color = BLACK;
            gParent.color = RED;
        }
        //Parent is a right child
        else {
            //Node is inner child
            if (node == parent.left) {
                rotateRight(parent);

                //For recoloring
                parent = node;
            }

            //Node is outer child
            rotateLeft(gParent);

            //Recolor original parent and gParent
            parent.color = BLACK;
            gParent.color = RED;
        }
    }

    private RBNode getUncle(RBNode parent) {
        RBNode gParent = parent.parent;
        return (gParent.left == parent) ? gParent.right : gParent.left;
    }

    // ===== Deletion =====

    @Override
    public T delete(int hash) {
        RBNode node = root;
        
        while (node != null && node.hash != hash) {
            if (hash < node.hash) 
                node = node.left;
            else 
                node = node.right;
        }

        if (node == null)
            return null;
        

        //Which node to start resolving potential Double Black
        RBNode movedUpNode;
        boolean deletedNodeColor;

        //Node has 1 or no child
        if (node.left == null || node.right == null) {
            movedUpNode = dereferenceNode(node);
            deletedNodeColor = node.color;
        }

        //Node has two children
        else {
            //Find minimum node of right subtree / next in-order node
            RBNode successor = findMinimum(node.right);

            //Copy successor's information to current node
            node.hash = successor.hash;
            node.data = successor.data;

            movedUpNode = dereferenceNode(successor);
            deletedNodeColor = successor.color;
        }

        // No changes needed if deletedNode is RED
        if (deletedNodeColor == BLACK) {
            fixAfterDelete(movedUpNode);

            //Remove the temporary NIL node
            if(movedUpNode.getClass() == NilNode.class)
                transplant(movedUpNode.parent, movedUpNode, null);
        }
        elementCount--;
        return (T)node.data;
    }

    private RBNode dereferenceNode(RBNode node) {
        if (node.left != null) {
            transplant(node.parent, node, node.left);
            //Return child node that switched parent
            return node.left;
        }
        else if (node.right != null) {
            transplant(node.parent, node, node.right);
            return node.right;
        }

        // If Node is Leaf && Node is red, simply remove
        // If Node is Leaf && Node is black, pass back BLACK to check for Double Black Situation
        else {
            RBNode newChild = (node.color == BLACK ? new NilNode() : null);
            transplant(node.parent, node, newChild);
            return newChild;
        }
    }

    private RBNode findMinimum(RBNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private void fixAfterDelete(RBNode node) {
        if (node == root) {
            node.color = BLACK;
            return;
        }

        RBNode sibling = getSibling(node);

        // Red sibling
        if (sibling.color == RED) {
            sibling.color = BLACK;
            node.parent.color = RED;

            if (node == node.parent.left)
              rotateLeft(node.parent);
            else
              rotateRight(node.parent);
            
            sibling = getSibling(node); // To allow it to keep testing
        }

        // Black sibling with 2 black child
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;

            if (node.parent.color == RED) 
                node.parent.color = BLACK;
            else 
                fixAfterDelete(node.parent);
        }
        // Black sibling with at least 1 red child
        else {
            boolean nodeIsLeftChild = node == node.parent.left;

            // Black outer nephew (rotate nephew to replace sibling)
            if (nodeIsLeftChild && isBlack(sibling.right)) {
                sibling.left.color = BLACK;
                sibling.color = RED;
                rotateRight(sibling);
                sibling = node.parent.right;
            } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
                sibling.right.color = BLACK;
                sibling.color = RED;
                rotateLeft(sibling);
                sibling = node.parent.left;
            }

            // Red outer nephew (rotate node's parent)
            sibling.color = node.parent.color;
            node.parent.color = BLACK;
            if (nodeIsLeftChild) {
                sibling.right.color = BLACK;
                rotateLeft(node.parent);
            } else {
                sibling.left.color = BLACK;
                rotateRight(node.parent);
            }
        }
    }

    private RBNode getSibling(RBNode node) {
        RBNode parent = node.parent;
        if (node == parent.left) 
            return parent.right;
        else  
            return parent.left;
        
    }

    private boolean isBlack(RBNode node) {
      return node == null || node.color == BLACK;
    }


    // -- Helpers for insertion and deletion ---------------------------------------------------------

    private void rotateRight(RBNode node) {
        RBNode parent = node.parent;
        RBNode leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) 
            leftChild.right.parent = node;

        leftChild.right = node;
        node.parent = leftChild;

        transplant(parent, node, leftChild);
    }

    private void rotateLeft(RBNode node) {
        RBNode parent = node.parent;
        RBNode rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        transplant(parent, node, rightChild);
    }

    // Shift oldChild into parent's position
    private void transplant(RBNode parent, RBNode oldChild, RBNode newChild) {
        if (parent == null) 
            root = newChild;
        else if (parent.left == oldChild) 
            parent.left = newChild;
        else 
            parent.right = newChild;
        

        if (newChild != null) 
            newChild.parent = parent;
        
    }
    
    private void arrayNavi(ArrayList<T> arr, RBNode node) {
        if (node != null){
            arrayNavi(arr, node.left);
            arr.add((T)node.data);
            arrayNavi(arr, node.right);
        }
    }
    @Override
    public Object[] toArray() {
        ArrayList<T> arr = new ArrayList<>(elementCount);
        arrayNavi(arr, root);
        return arr.toArray();
    }

    private class Itr implements Iterator<T>{
        RBNode prevNode;
        RBNode currentNode;
        int count;
        
        Itr(){
            count = 0;
        }
        
        private void toLeftest(){
            if(currentNode.left != null){
                currentNode = currentNode.left;
                toLeftest();
            }
        }
        
        @Override
        public boolean hasNext() {
            return count != elementCount;
        }

        @Override
        public T next() {
            if(currentNode == null){
                currentNode = root;
                toLeftest();
            }else if (currentNode.right != null){
                currentNode = currentNode.right;
                toLeftest();
            }else{
                currentNode = currentNode.parent;
                while(prevNode.hash > currentNode.hash){
                    currentNode = currentNode.parent;
                }
            }
            count++;
            prevNode = currentNode;
            return (T)currentNode.data;
        }
    }
        
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }
    
    @Override
    public int size() {
        return elementCount;
    }

    @Override
    public void clear() {
        // Root = null works in Java because garbage collector will clear the tree
        // The same cannot be said in other languages
        root = null;
        elementCount = 0;
    }
    
    @Override 
    public boolean isEmpty(){
        return elementCount == 0;
    };
    
    private void inOrder(RBNode node) {
        if (node == null) 
          return;
        
        inOrder(node.left);
        if(node.parent!=null)
            System.out.printf("%s(%s)<p:%s> ", node.data, node.color?'B':'R', node.parent.data);
        else
            System.out.printf("%s(%s)<root> ", node.data, node.color?'B':'R');
        inOrder(node.right);
    }
    public void inOrder(){
        System.out.printf("root is : %s(%s)\n ", root.data, root.color);
        inOrder(root);
    }
}