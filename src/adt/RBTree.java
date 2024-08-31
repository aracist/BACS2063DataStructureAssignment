package adt;

import java.util.Iterator;

public class RBTree<T> implements CollectionInterface<T>{
    RBNode root;
    int elementCount;

    public RBTree(){
        root = null;
        elementCount = 0;
    }
    
    private class RBNode{
        char color; //R for red, B for black
        int hash;
        T data;
        RBNode parent;
        RBNode left;
        RBNode right;
        
        RBNode(){}
        
        RBNode(T data){
            this.hash = data.hashCode();
            this.data = data;
            color = 'R';
        }
        
        boolean isRoot(){
            return parent==null;
        }
        boolean isLeft(){
            return parent.left==this;
        }
        boolean isRight(){
            return parent.right==this;
        }
    }
    
    //to reverse color of a node
    private void revColor(RBNode target){
        target.color = (target.color == 'R') ? 'B' : 'R';
    }
    
    
    public boolean add(T data){
        RBNode newNode = new RBNode(data);
        RBNode currentNode = root;
        
        //if tree is empty
        if(currentNode == null){
            root = newNode;
            root.color = 'B';
            elementCount++;
            return true;
        }

        do{ 
            if(newNode.hash == currentNode.hash){
                return false;
            }
            
            //less than current node
            if(newNode.hash < currentNode.hash){
                if(currentNode.left == null){
                    currentNode.left = newNode;
                    newNode.parent = currentNode;
                    balance(newNode);
                    elementCount++;
                    return true;
                }else
                    currentNode = currentNode.left;
            }
            
            //more than current node
            if(newNode.hash > currentNode.hash){
                if(currentNode.right == null){
                    currentNode.right = newNode;
                    newNode.parent = currentNode;
                    balance(newNode);
                    elementCount++;
                    return true;
                }else
                    currentNode = currentNode.right;
            }
        }while(true);
    }
    
    //to resolve double red node
    private void balance(RBNode currentNode){
        System.out.println("Processing"+ currentNode.data);
        RBNode pNode = currentNode.parent; //parent node
        
        if (!(currentNode.color == 'R' && pNode.color == 'R')) //only continue checking if parent is red
            return;
        
        RBNode gpNode = pNode.parent; //grand parent node
        RBNode uNode = null; //parent's sibling(uncle)
        Boolean sameDirection = null; //false if psNode is left while currentNode is right of their respective parent (or vice versa) 
        
        //check if parent can have sibling
        if(gpNode != null){ 
            if(pNode.isRight()){
                uNode = gpNode.left;
                sameDirection = currentNode.isLeft();
            }else{
                uNode = gpNode.right;
                sameDirection = currentNode.isRight();
            }
            //uNode = (gpNode.right == pNode)?uNode = gpNode.left:uNode = gpNode.right;
        }
        
        //if uNode is RED, reverse color or gp, p & u Node
        if(uNode != null && uNode.color == 'R'){
            revColor(pNode);
            revColor(uNode);
            revColor(gpNode);
            root.color = 'B';//ensure root is black
            
            if(gpNode.parent != null && gpNode.parent.color == 'R')
                balance(gpNode);
        }else{ //if uNode is black
            if(pNode.isRoot()){
                return;
            }
            
            char side = (pNode.isLeft()) ? 'L' : 'R';
            if(sameDirection){
                rotateSameDirection(side, pNode);
            }else
                rotateDiffDirection(side, pNode);
        }
    }
    
    private void rotateSameDirection(char side, RBNode target){
        RBNode childNode;
        RBNode pNode = target.parent;
        
        if(side == 'L'){
            childNode = target.right;
            
            if(!pNode.isRoot()){
                if(pNode.isLeft())
                    pNode.parent.left = childNode;
                else
                    pNode.parent.right = childNode;
            }else
                root = childNode;
            
            childNode.parent = pNode.parent;

            pNode.parent = childNode;
            target.parent = childNode;
            
            if(childNode.right != null)
                childNode.left.parent = target;
            target.right = childNode.left;
            childNode.left = target;
            
            if(childNode.right != null)
                childNode.right.parent = pNode;
            pNode.left = childNode.right;
            childNode.right = pNode;
            
        }else{
            childNode = target.left;
            
            if(!pNode.isRoot()){
                if(pNode.isLeft())
                    pNode.parent.left = childNode;
                else
                    pNode.parent.right = childNode;
            }else
                root = childNode;
                
            childNode.parent = pNode.parent;

            pNode.parent = childNode;
            target.parent = childNode;

            if(childNode.right != null)
                childNode.right.parent = target;
            target.left = childNode.right;
            childNode.right = target;
            
            if(childNode.left != null)
                childNode.left.parent = pNode;
            pNode.right = childNode.left;
            childNode.left = pNode;
        }
        revColor(pNode);
        revColor(childNode);
        //in case child node became root
        if(childNode.parent == null){
            childNode.color = 'B';
            root = childNode;
        }    
    }
    
    private void rotateDiffDirection(char side, RBNode target){
        RBNode childNode;
        RBNode pNode = target.parent;
        
        if(side == 'L'){
            childNode = target.right;
            
            if(!pNode.isRoot()){
                if(pNode.isLeft())
                    pNode.parent.left = target;
                else
                    pNode.parent.right = target;
            }else
                root = target;
            
            target.parent = pNode.parent;

            pNode.parent = target;
            target.right = pNode;
            
            if(childNode != null)
                childNode.parent = pNode;
            pNode.left = childNode;
            
        }else{
            childNode = target.left;
            
            if(!pNode.isRoot()){
                if(pNode.isLeft())
                    pNode.parent.left = target;
                else
                    pNode.parent.right = target;
            }else
                root = target;
            
            target.parent = pNode.parent;

            pNode.parent = target;
            target.left = pNode;

            if(childNode != null)
                childNode.parent = pNode;
            pNode.right = childNode;
        }
        
        revColor(pNode);
        revColor(target);
        //in case target node became root
        if(target.parent == null){
            target.color = 'B';
            root = target;
        } 
    }
    
    public T remove(){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public T get(int position){
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private class Itr implements Iterator<T>{
//        T[] arr;
//        int count;
//        
//        Itr(){
//            arr = (T[])RBTree.this.toArray();
//            count = 0;
//        }
//        
//        @Override
//        public boolean hasNext() {
//            return count != elementCount;
//        }
//
//        @Override
//        public T next() {
//            return arr[count++];
//        }
        RBNode prevNode;
        RBNode currentNode;
        int count;
        
        Itr(){
            count = 0;
        }
        
        private void toLeftest(){
            while(currentNode.left != null)
                currentNode = currentNode.left;
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
                if(prevNode.hash > currentNode.hash){
                    currentNode = currentNode.parent;
                }
            }
            count++;
            prevNode = currentNode;
            return currentNode.data;
        }
        
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }
    
    private void arrayNavi(ArrayList<T> arr,RBNode node) {
        if (node == null) 
          return;
        
        arrayNavi(arr, node.left);
        arr.add(node.data);
        arrayNavi(arr, node.right);
    }
    
    @Override
    public Object[] toArray() {
        ArrayList<T> arr = new ArrayList<>(elementCount);
        arrayNavi(arr, root);
        return arr.toArray();
    }

    @Override
    public int size() {
        return elementCount;
    }
    
    @Override
    public void clear(){
        root = null;
        elementCount ++;
    }
    
    @Override
    public boolean isEmpty(){
        return root == null;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        int i = 0;
        
        for(T data : this){
            str.append(String.format("\n[%2s] %s", ++i, data));
        }
        return str.toString();
    }
    
    
    
    
    
    private void inOrder(RBNode node) {
        if (node == null) 
          return;
        
        inOrder(node.left);
        System.out.printf("%s(%s) ", node.data, node.color);
        inOrder(node.right);
    }
    public void inOrder(){
        System.out.printf("root is : %s(%s)\n ", root.data, root.color);
        inOrder(root);
    }
}
