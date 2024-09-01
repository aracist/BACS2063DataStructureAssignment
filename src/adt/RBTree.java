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
            return (parent!=null && parent.left==this);
        }
        boolean isRight(){
            return (parent!=null && parent.right==this);
        }
        boolean hasNoChild(){
            return (left == null && right == null);
        }
    }
    
    //to reverse color of a node
    private void revColor(RBNode target){
        target.color = (target.color == 'R') ? 'B' : 'R';
    }
    
    public void addAll(T[] dataArr){
        addAll(dataArr, 1, dataArr.length);
    }
    
    public void addAll(T[] dataArr, int startingPosition){
        addAll(dataArr, startingPosition, dataArr.length);
    }
    
    public void addAll(T[] dataArr, int startingPosition, int endingPosition){
        for (int i = startingPosition - 1; i < endingPosition; i++) {
            add(dataArr[i]);
        }
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
            
            if(gpNode != null && gpNode.parent != null && gpNode.parent.color == 'R')
                balance(gpNode);
        }else{ //if uNode is black
            if(pNode.isRoot()){
                return;
            }
            
            //check if parent is a left or right child
            char side = (pNode.isLeft()) ? 'L' : 'R';
            if(sameDirection){
                rotate(side, pNode);
            }else
                specialRotate(side, pNode);
        }
    }
    
    private void rotate(char side, RBNode target){
        RBNode childNode;
        RBNode pNode = target.parent;
        
        if(side == 'L'){
            childNode = target.right;
            
            if(pNode != null){
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
            
            if(pNode != null){
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
    
    private void specialRotate(char side, RBNode target){
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
        if(target.isRoot()){
            target.color = 'B';
            root = target;
        } 
    }

    private RBNode getNode(RBNode currentNode, int hash){        
        if(currentNode == null)
            return null;

        //less than current node
        else if(hash < currentNode.hash)
            currentNode = getNode(currentNode.left, hash);

        //more than current node
        else if(hash > currentNode.hash)
            currentNode = getNode(currentNode.right, hash);
        
        
        return currentNode;
    }
    
    public T get(int hash){
        RBNode result = getNode(root, hash);
        return (result == null)?null:result.data;
    }
    
    private RBNode getLargestNode(RBNode currentNode){        
        if(currentNode.right != null)
            currentNode = getLargestNode(currentNode.right);
        
        return currentNode;
    }
    
    public T remove(int hash){
        RBNode target = getNode(root, hash);
        
        if(target == null)
            return null;
        
        T result = target.data;
        
        if(target.hasNoChild()){
            if(target.color == 'B')
                removeBalance(target);
            removeNode(target);
        }else{
            RBNode successor = (target.left != null) ? getLargestNode(target.left):target.right;

            if(successor != null){
                target.hash = successor.hash;
                target.data = successor.data;
                if (successor.color == 'B')
                    removeBalance(successor);

                //remove node & color its child black
                removeNode(successor);
            }
        }
        elementCount --;
        return result;
    }
    
    private void removeNode(RBNode target){
        if(target.isRight())
            target.parent.right = target.right;
        else
            target.parent.left = target.left;
        
        if (target.right != null)
                target.right.color = 'B';
        
        if (target.left != null)
            target.left.color = 'B';
    }
    
    private void removeBalance(RBNode target){
        RBNode sibling;
        RBNode nephew;
        
        if(target.isLeft()){
            sibling = target.parent.right;
            nephew = sibling.left;
        }else{
            sibling = target.parent.left;
            nephew = sibling.right;
        }
        
        if(sibling.color == 'B' && (nephew != null && nephew.color == 'R')){
            rotate(sibling.isLeft()?'L':'R',sibling);
            revColor(nephew);
            balance(nephew);
            
        }else if(sibling.color == 'B' && target.color == 'B'){
            sibling.color = 'R';
            if(target.parent.color == 'R')
                target.parent.color = 'B';
            else
                removeBalance(target.parent);
            
        }else if(sibling.color == 'R'){
            RBNode pNode = sibling.parent;
            
            revColor(sibling);
            revColor(pNode);
            
            if(pNode.parent == null){
                root = sibling;
                sibling.color = 'B';
            }else{
                if(pNode.isLeft())
                    pNode.parent.left = sibling;
                else
                    pNode.parent.right = sibling;
            }
            sibling.parent = pNode.parent;
            pNode.parent = sibling;
            pNode.left = sibling.right;
            sibling.right.parent = pNode;
            sibling.right = pNode;
            removeBalance(target);
        }
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
            return currentNode.data;
        }
        
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }
    
    private void arrayNavi(ArrayList<T> arr,RBNode node) {
        if (node != null){
            arrayNavi(arr, node.left);
            arr.add(node.data);
            arrayNavi(arr, node.right);
        }
    }
    
    @Override
    public Object[] toArray() {
        ArrayList<T> arr = new ArrayList<>(elementCount);
        arrayNavi(arr, root);
        return (T[])arr.toArray();
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
        if(node.parent!=null)
            System.out.printf("%s(%s)<p:%s> ", node.data, node.color, node.parent.data);
        else
            System.out.printf("%s(%s)<root> ", node.data, node.color);
        inOrder(node.right);
    }
    public void inOrder(){
        System.out.printf("root is : %s(%s)\n ", root.data, root.color);
        inOrder(root);
    }
}
