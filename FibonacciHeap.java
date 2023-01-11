/**
 * FibonacciHeap
 *
 * An implementation of a Fibonacci Heap over integers.
 */
public class FibonacciHeap
{
    public int size = 0;
    public int cuts;
    public int maxDeg = 0; //degree of the largest tree in the heap
    public HeapNode head; //pointer to left-most node
    public HeapNode last; //pointer to right-most node
    public HeapNode min; //pointer to min node
    public static int totaLinks = 0;
    public static int totalCats = 0;
    public int nonMarked = 0;

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
       nonMarked +=1;
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
                    nonMarked -= 1;
                }

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
     * root1.rank == root2.rank
     */
    public HeapNode linking(HeapNode root1,HeapNode root2)
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
        totaLinks += 1;
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
    * Melds heap2 with the current heap.
    *
    */
   public void meld (FibonacciHeap heap2) {

       HeapNode otherRoot = heap2.head;
       last.right = otherRoot;
       otherRoot.right = last;
       last = otherRoot;

       if(heap2.min.getKey() < this.min.getKey()){
           this.min = heap2.min;
       }
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
    * (Note: The size of of the array depends on the maximum order of a tree.)  
    * 
    */
    public int[] countersRep()
    {
    	int[] arr = new int[100];
        return arr; //	 to be replaced by student code
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
    public void decreaseKey(HeapNode x, int delta)
    {    
    	return; // should be replaced by student code
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
        return -234; // should be replaced by student code
    }

    public static void link(HeapNode root1, HeapNode root2){ //links smaller tree from the right
        HeapNode bigger = root2;
        if(root1.getKey() > root2.getKey()){
            bigger = root1;
        }
    }

    public static void cut(HeapNode root1, HeapNode root2) { //cuts subtree
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
    	return totaLinks; // should be replaced by student code
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
    	return totalCats; // should be replaced by student code
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
        public int rank = 0;
        public int mark = 0;
        public HeapNode right;
        public HeapNode left;
        public HeapNode parent;
        public HeapNode leftChild;

    	public HeapNode(int key) {
    		this.key = key;
    	}

    	public int getKey() {
    		return this.key;
    	}

    }
}
