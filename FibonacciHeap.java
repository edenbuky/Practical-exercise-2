/**
 * FibonacciHeap
 *
 * An implementation of a Fibonacci Heap over integers.
 */
public class FibonacciHeap
{
    public int size = 0; //num of trees
    public int maxDeg = 0; //degree of the largest tree in the heap
    public HeapNode head; //pointer to right-most node
    public HeapNode last; //pointer to left-most node
    public HeapNode min; //pointer to min node
    public static int totalLinks = 0;
    public static int totalCuts = 0;
    public int nonMarked = 0;
    public int marked = 0;


    public FibonacciHeap(){
    }


    public FibonacciHeap(HeapNode root){
        head = root;
        min = root;
        last = root;
        maxDeg = root.rank;
        size = 1;
    }

   /**
    * public boolean isEmpty()
    *
    * Returns true if and only if the heap is empty.
    *   
    */
    public boolean isEmpty()
    {
        return this.size == 0;
    }
		
   /**
    * public HeapNode insert(int key)
    *
    * Creates a node (of type HeapNode) which contains the given key, and inserts it into the heap.
    * The added key is assumed not to already belong to the heap.  
    * 
    * Returns the newly created node.
    */
   public HeapNode insert(int key)
   {
       HeapNode newNode = new HeapNode(key);
       nonMarked ++;
       size ++;
       last.left = newNode;
       newNode.right = last;
       last = newNode;
       if(key < this.min.getKey()){
           this.min = newNode;
       }
       return newNode;
   }

   /**
    * public void deleteMin()
    *
    * Deletes the node containing the minimum key.
    *
    */
    public void deleteMin()
    {
        HeapNode current, temp;
        //add minimum child-trees to the heap and delete minimum
        current = min.leftChild;
        if (current != null)
        {
            temp = current.left;
            temp.mark = 0;
            current.left = min.left;
            min.left.right = current;
            min.left = null;
            temp.right = min.right;
            min.right.left = temp;
            min.right = null;
            min.leftChild = null;
            while (current != temp){
                if (current.mark == 1){
                    current.mark = 0;
                    nonMarked --;
                }
                current = current.right;
            }

        }
        else
        {
           current = min.left;
           temp = min.right;
           current.right = temp;
           temp.left = current;
           min.left = null;
           min.right = null;
        }

        //Successive Linking
        consolidating();

        // Update min-pointer
        current = head.right;
        min = head;
        while (current != head)
        {
            if (min.getKey() > current.getKey())
            {
                min = current;
            }
            current = current.right;
        }
    }
    /**
     * Linking 2 trees with the same rank
     * //@pre: root1.rank == root2.rank
     * //@ret: the root node to which we linked the smaller tree
     */
    public static HeapNode linking(HeapNode root1,HeapNode root2)
    {
        if (root1.getKey() > root2.getKey()){
            linking(root2,root1);
        }
        if (root1.leftChild == null){
            root2.right = root2;
            root2.left = root2;
        }
        else {
            root2.right = root1.leftChild;
            root1.leftChild.left = root2;
        }
        root1.leftChild = root2;
        root2.parent = root1;
        root1.rank += 1;
        totalLinks += 1;
        return root1;
    }

    /**
     * Successive linking trees with the same rank, to reduce the amount of threes in the heap
     */
    public void consolidating()
    {
        HeapNode[] rankCells = new HeapNode[maxDeg + 1];
        HeapNode temp, current = head.right;

        //emptying the array
        for (int i = 0; i <= maxDeg; i++){rankCells[i] = null;}
        rankCells[head.rank] = head;

        //traverse the root list. Whenever we discover two trees that have the same rank we link these trees.
        while (current != head)
        {
            while (rankCells[current.rank] != null){
                current = linking(current,rankCells[current.rank]);
            }
            rankCells[current.rank] = current;
            current = current.right;

        }

        //Update the head to the root with the smallest rank
        current = head.right;
        temp = head;
        while (current != head)
        {
            if (temp.rank > current.rank){
                temp = current;
            }
            current = current.right;
        }
        head = temp;

    }
   /**
    * public HeapNode findMin()
    *
    * Returns the node of the heap whose key is minimal, or null if the heap is empty.
    *
    */
    public HeapNode findMin()
    {
    	return this.min;
    } 
    
   /**
    * public void meld (FibonacciHeap heap2)
    *
    * Melds heap2 with the current heap. (heap2 is melded from the right)
    *
    */
   public void meld (FibonacciHeap heap2) {

       HeapNode other = heap2.last;
       head.right = other;
       other.left = head;
       head = heap2.head;
       size += heap2.size;

       if(heap2.min.getKey() < this.min.getKey()){
           this.min = heap2.min;
       }
       maxDeg = Math.max(maxDeg, heap2.maxDeg);
   }

   /**
    * public int size()
    *
    * Returns the number of elements in the heap.
    *   
    */
    public int size()
    {
    	return size;
    }
    	
    /**
    * public int[] countersRep()
    *
    * Return an array of counters. The i-th entry contains the number of trees of order i in the heap.
    * (Note: The size of the array depends on the maximum order of a tree.)
    * 
    */
    public int[] countersRep()
    {
    	int[] arr = new int[maxDeg + 1];
        HeapNode curr = this.head;
        while(curr != null){
            int deg = curr.rank;
            arr[deg] ++;
            curr = curr.left;
        }
        return arr;
    }
	
   /**
    * public void delete(HeapNode x)
    *
    * Deletes the node x from the heap.
	* It is assumed that x indeed belongs to the heap.
    *
    */
    public void delete(HeapNode x)
    {
    	decreaseKey(x,min.getKey() - 1);
        deleteMin();
    }

   /**
    * public void decreaseKey(HeapNode x, int delta)
    *
    * Decreases the key of the node x by a non-negative value delta. The structure of the heap should be updated
    * to reflect this change (for example, the cascading cuts procedure should be applied if needed).
    */
    public void decreaseKey(HeapNode x, int delta) {
    	x.key -= delta;
        HeapNode parent = x.parent;
        if(parent == null){
            if(x.getKey() < this.min.getKey()){
                min = x;
                return;
            }
        }
        if(x.getKey() < parent.getKey()){
            cascading_cut(x, parent);
            return;
        }
    }

   /**
    * public int nonMarked() 
    *
    * This function returns the current number of non-marked items in the heap
    */
    public int nonMarked() 
    {    
        return nonMarked; // should be replaced by student code
    }

   /**
    * public int potential() 
    *
    * This function returns the current potential of the heap, which is:
    * Potential = #trees + 2*#marked
    * 
    * In words: The potential equals to the number of trees in the heap
    * plus twice the number of marked nodes in the heap. 
    */
    public int potential() 
    {    
        return size + (2 * marked); // should be replaced by student code
    }


    public void cut(HeapNode node) {
        totalCuts ++;
        HeapNode parent = node.parent;
        HeapNode right = node.right;
        HeapNode left = node.left;
        node.right = null;
        node.left = null;
        node.parent = null;
        FibonacciHeap newHeap = new FibonacciHeap(node);
        if(parent != null) {
            parent.rank --;
            if(parent.leftChild == node){
                parent.leftChild = right;
            }
        }
        if(right != null){
            right.left = left;
        }
        if(left != null){
            left.right = right;
        }
        this.meld(newHeap);
    }

    public void cascading_cut(HeapNode node, HeapNode parent){
        cut(node);
        if(parent.parent != null){
            if(parent.mark == 0){
                parent.mark = 1;
                nonMarked --;
                marked ++;
            } else{
                cascading_cut(parent, parent.parent);
            }
        }

    }

   /**
    * public static int totalLinks() 
    *
    * This static function returns the total number of link operations made during the
    * run-time of the program. A link operation is the operation which gets as input two
    * trees of the same rank, and generates a tree of rank bigger by one, by hanging the
    * tree which has larger value in its root under the other tree.
    */
    public static int totalLinks()
    {    
    	return totalLinks;
    }

   /**
    * public static int totalCuts() 
    *
    * This static function returns the total number of cut operations made during the
    * run-time of the program. A cut operation is the operation which disconnects a subtree
    * from its parent (during decreaseKey/delete methods). 
    */
    public static int totalCuts()
    {    
    	return totalCuts;
    }

     /**
    * public static int[] kMin(FibonacciHeap H, int k) 
    *
    * This static function returns the k smallest elements in a Fibonacci heap that contains a single tree.
    * The function should run in O(k*deg(H)). (deg(H) is the degree of the only tree in H.)
    *  
    * ###CRITICAL### : you are NOT allowed to change H. 
    */
    public static int[] kMin(FibonacciHeap H, int k)
    {    
        int[] arr = new int[100];
        return arr; // should be replaced by student code
    }
    
   /**
    * public class HeapNode
    * 
    * If you wish to implement classes other than FibonacciHeap
    * (for example HeapNode), do it in this file, not in another file. 
    *  
    */
    public static class HeapNode{

    	public int key;
        public int rank = 0; //number of children
        public int mark = 0;
        public HeapNode right = null;
        public HeapNode left = null;
        public HeapNode parent = null;
        public HeapNode leftChild = null;

    	public HeapNode(int key) {
    		this.key = key;
    	}

    	public int getKey() {
    		return this.key;
    	}

        //sets node rank by counting children
        public void setRank(){
            int children = 0;
            HeapNode curr = leftChild;
            while(curr != null){
                children ++;
                curr = curr.right;
            }
            this.rank = children;
        }

       //sets node rank to k
       public void setRank(int k){
           this.rank = k;
       }

    }
}
