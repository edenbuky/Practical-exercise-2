/**
 * FibonacciHeap
 *
 * An implementation of a Fibonacci Heap over integers.
 */
public class FibonacciHeap
{
    public int size = 0; //num of trees
    public HeapNode maxDeg; //pointer to the largest tree in the heap
    public HeapNode head; //pointer to newest node (lefmost)
    public HeapNode min; //pointer to min node
    public int n = 0;
    public static int totalLinks = 0;
    public static int totalCuts = 0;
    public int nonMarked = 0;
    public int marked = 0;


    public FibonacciHeap(){
    }


    public FibonacciHeap(HeapNode root){
        min = root;
        head = root;
        maxDeg = root;
        size = 1;
    }
    
    public void infoPrint() {
    	System.out.println("head: " + head.key);
    	System.out.println("min: " + min.key);
    	System.out.println("size: " +size);
    	System.out.println("n: " + n);
    	
    }
    public HeapNode getFirst(){return head;}
    
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
   public HeapNode insert(int key){
       HeapNode newNode = new HeapNode(key);
       insertNode(newNode);
       return newNode;
   }
   
   public void insertNode(HeapNode newNode) {
	   if(size ==0) {
    	   head = newNode;
    	   head.left = head;
    	   head.right = head;
    	   min = newNode;
    	   maxDeg = head;
       } else{
    	   head.leftConnect(newNode);
    	   head = newNode;
    	   if(newNode.key < this.min.getKey()){
    		   this.min = newNode;
    	   }
       }
       size ++;
       n ++;
       nonMarked ++;
	   
   }

   /**
    * public void deleteMin()
    *
    * Deletes the node containing the minimum key.
    *
    */
    public void deleteMin()
    {
        n --;
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
        
        HeapNode r = root2.right;
        HeapNode l = root2.left;
        
        r.left = l;
        l.right = r;
        
        if (root1.leftChild == null){
            root2.right = root2;
            root2.left = root2;
        }
        
        else {
        	HeapNode currChild = root1.leftChild;
            root2.right = currChild;
            root2.left = currChild.left;
            currChild.left = root2;
        }
        root1.leftChild = root2;
        root2.parent = root1;
        root1.rank ++;
        totalLinks ++;
        return root1;
    }
    
    public void arrayLink(HeapNode[] array) {
    	FibonacciHeap newHeap = new FibonacciHeap();
    	for(HeapNode node : array) {
    		if(node != null) {
    			FibonacciHeap temp = new FibonacciHeap(node);
    			newHeap.meld(temp);
    		}
    	}
    	head = newHeap.head;
    	min = newHeap.min;
    	size = newHeap.size; 
        maxDeg = newHeap.maxDeg; 

    }

    /**
     * Successive linking trees with the same rank, to reduce the amount of threes in the heap
     */
   
    public void consolidating()
    {
    	double goldenRatio = (1 + Math.sqrt(5)) / 2;
        int maxDegree = (int) (Math.log(this.n) / Math.log(goldenRatio));
        HeapNode[] rankCells = new HeapNode[maxDegree + 1];
        HeapNode temp, current = head.right;

        //emptying the array
        for (int i = 0; i <= maxDegree; i++){rankCells[i] = null;}
        rankCells[head.rank] = head;

        HeapNode curr = head;
        if (curr != null) {
        	HeapNode check = head;
          do {
        	  HeapNode x = curr;
            int deg = x.rank;
            while (rankCells[deg] != null) {
            	HeapNode y = rankCells[deg];
              if (x.getKey() > y.getKey()) {
            	  temp = x;
            	  x = y;
            	  y = temp;
            	  curr = x;
              }
              linking(y, x);
              check = x;
              rankCells[deg] = null;
              deg ++;
            }
            rankCells[deg] = x;
            curr = curr.right;
          } while (curr != null && curr != check);
        }
          

        //Update the head to the root with the smallest rank
        arrayLink(rankCells);

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
	   
	   if(isEmpty()) {
		   head = heap2.head;
		   min = heap2.min;
		   size = heap2.size; 
	       maxDeg = heap2.maxDeg; 
	       marked = heap2.marked;
	       nonMarked = heap2.nonMarked;
	       n = heap2.n;
	       return;
	   }
	   if(heap2.isEmpty()) {
		   return;
	   }
	   staticMeld(this, heap2);
   }
   
   
   //melds heap2 from the rigth of heap 1
   public static void staticMeld(FibonacciHeap heap1, FibonacciHeap heap2) {

	   HeapNode otherHead = heap2.head;
       HeapNode otherTail = heap2.getOldest();
       heap1.getOldest().right = otherHead;
       otherHead.left = heap1.getOldest();
       otherTail.right = heap1.head;
       heap1.head.left = otherTail;
       
       int newSize = heap1.size + heap2.size;
       int newN = heap1.n + heap2.n;
       HeapNode newMin = heap1.min;
       HeapNode newMaxDeg = heap1.maxDeg;

       if(heap2.min.getKey() < heap1.min.getKey()){
    	   newMin = heap2.min;
       }
       if(heap2.maxDeg.rank > heap1.maxDeg.rank){
    	   newMaxDeg = heap2.maxDeg;
       }
       
       int newM = heap1.marked + heap2.marked;
       int newNonM = heap1.nonMarked + heap2.nonMarked;
       
       heap1.size = newSize;
       heap2.size = newSize;
       heap1.min = newMin;
       heap2.min = newMin;
       heap1.maxDeg = newMaxDeg;
       heap2.maxDeg = newMaxDeg;
       heap1.n = newN;
       heap2.n = newN;
       heap1.marked = newM;
       heap2.marked = newM;
       heap1.nonMarked = newNonM;
       heap2.nonMarked = newNonM;
       
   }
   
   //get rightmost node
   public HeapNode getOldest() {
	   if(size == 0) {
		   return head;
	   }
	   return head.left;
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
    	int[] arr = new int[maxDeg.rank + 1];
        HeapNode curr = this.head;
        do {
        	int deg = curr.rank;
            arr[deg] ++;
            curr = curr.right;
        }
        while(curr != head);
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
        staticMeld(newHeap, this);
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
     public static int[] kMin(FibonacciHeap H, int k) {
         int[] arr = new int[k];
         arr[0] = H.head.getKey();
         FibonacciHeap helper = new FibonacciHeap();
         HeapNode first = H.head.leftChild, curr = first.right;
         int count = 1;
         while (count < k){
             //enter unsorted children to helper
             while (curr != first) {
                 helper.insert(curr.getKey());
             }

             //enter children to arr in order
             while (count < k && !(helper.isEmpty())) {
                 arr[count] = helper.findMin().getKey();
                 count++;
                 helper.deleteMin();
             }
             // move to next level of children
             first = first.leftChild;
         }
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
        public HeapNode right = this;
        public HeapNode left = this;
        public HeapNode parent = null;
        public HeapNode leftChild = null;

    	public HeapNode(int key) {
    		this.key = key;
    	
		}
	public int getRank(){
           return this.rank;
       }
       public boolean getMarked(){
           return (this.mark==1);
       }
       public HeapNode getParent(){
           return this.parent;
       }
       public HeapNode getNext(){
           return this.right;
       }
       public HeapNode getPrev(){
           return this.left;
       }
       public HeapNode getChild(){
           return this.leftChild;
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
       
       public void leftConnect(HeapNode other) {
    	   HeapNode temp = this.left;
    	   this.left = other;
    	   other.right = this;
    	   other.left = temp;
    	   temp.right = other;
    	   
       }

    }
}