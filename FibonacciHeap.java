import java.util.Arrays;

/**
 * FibonacciHeap
 *
 * An implementation of a Fibonacci Heap over integers.
 */
public class FibonacciHeap
{
	static HeapPrinter printer = new HeapPrinter(System.out);
	public int size = 0; //num of trees
    public HeapNode head; //pointer to newest node (lefmost)
    public HeapNode min; //pointer to min node
    public int n = 0;
    public static int totalLinks = 0;
    public static int totalCuts = 0;
    public int marked = 0;


    public FibonacciHeap(){
    }


    public FibonacciHeap(HeapNode root){
        min = root;
        head = root;
        head.right = head;
        head.left = head;
        size = 1;
    }
    
    
    //empty the heap
    public void emptyHeap() {
    	head = null;
    	min = null;
    	size = 0;
    	marked = 0;

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
   public HeapNode insert(int key){
       HeapNode newNode = new HeapNode(key);
       insert(newNode);
       return newNode;
   }
   
   public void insert(HeapNode newNode) {
	   if(size ==0) {
    	   head = newNode;
    	   head.left = head;
    	   head.right = head;
    	   min = newNode;
       } else{
    	   head.leftConnect(newNode);
    	   head = newNode;
    	   if(newNode.key < this.min.getKey()){
    		   this.min = newNode;
    	   }
       }
       size ++;
       n ++;
	   
   }

   



    /**
     * Linking 2 trees with the same rank
     * //@pre: root1.rank == root2.rank
     * //@ret: the root node to which we linked the smaller tree
     */
    public static HeapNode linking(HeapNode root1,HeapNode root2)
    {
        if (root1.getKey() > root2.getKey()){
            return linking(root2,root1);
        }

        HeapNode r = root2.right,l = root2.left;
        if (r == root1) {
            root1.left = root2.left;
            l.right = r;
        } else if (l == root1) {
            root1.right = root2.right;
            r.left = l;
        } else {
            r.left = l;
            l.right = r;
        }

        if (root1.leftChild == null){
            root2.right = root2;
            root2.left = root2;
        }

        else {
        	HeapNode currChild = root1.leftChild;
            root2.left = currChild.left;
            (currChild.left).right = root2;
            currChild.left = root2;
            root2.right = currChild;
            if (currChild.right == currChild){currChild.right = root2;}
        }
        root1.leftChild = root2;
        root2.parent = root1;
        root1.rank ++;
        totalLinks ++;
        return root1;
    }


    /**
     * public void deleteMin()
     *
     * Deletes the node containing the minimum key.
     *
     */
    public void deleteMin() {
        //Only min in the Heap;
        if (min.leftChild == null && min.left == min){
            n = 0;
            size = 0;
            head = null;
            min = null;
            return;
        }
        if (head == min && min.left != min){head = min.left;}
        else if (head == min) {head = min.leftChild;}
        //add min's Children to the root-list and delete minimum
        int deg = min.rank;
        if (min.leftChild != null){
            HeapNode curr = min.leftChild, nxt = curr.right;
            for (int i = 0; i < deg; i++) {
                curr.parent = null;
                if (curr.mark == 1){
                    curr.mark = 0;
                    marked--;
                }
                min.leftConnect(curr);
                curr = nxt;
                nxt = nxt.right;
            }
        }
        min.selfRemove();
        size += deg - 1;
        n--;

        //successive-linking
        consolidating();
    }
 
    
    /**
     * Successive linking trees with the same rank, to reduce the amount of threes in the heap
     */
    
    public void consolidate() {
    	System.out.println(" before cons");
    	printer.print(this, false);
    	int maxD = (int) ((Math.log(n + 1)) / (Math.log(2) + 3));
    	HeapNode[] heapArr = new HeapNode[maxD + 2];
    	System.out.println(maxD);
    	HeapNode node = head;
    	HeapNode tmp = head.left;
    	
    	while (tmp != head) {
    		tmp = node.right;
    		node.right = node;
        	node.left = node;
        	int index = node.rank;
        	if(heapArr[index] == null) {
        		heapArr[index] = node;
        	} else {
        		while(heapArr[index] != null) {
        			node = linking(node, heapArr[index]);
        			heapArr[index] = null;
        			index ++;
        		}
        	}
        	node = tmp;
    	}
    	
    	emptyHeap();
    	for(HeapNode root : heapArr) {
    		if(root != null) {
    			FibonacciHeap tmpHeap = new FibonacciHeap(root);
    			staticMeld(this, tmpHeap);
    		}
    	}
    	
    }
    public void consolidating(){
//    	System.out.println(" before cons");
//    	printer.print(this, false);
        updateMin();
        double goldenRatio = (1 + Math.sqrt(5)) / 2;
        int maxDegree = (int) (Math.log(this.n) / Math.log(goldenRatio));
        HeapNode[] B = new HeapNode[n + 1];
        // Iterate over all roots
        for (HeapNode node : getRoots()) {
            // Check if no other no with the same rank
            if (B[node.rank] == null) {
                B[node.rank] = node;
            } else {
                // Carry to higher ranks while linking
                while (B[node.rank] != null) {
                    // Connect the 2 nodes with the same rank
                    node = linking(node, B[node.rank]);
                    // Clear current node from the array
                    B[node.rank - 1] = null;
                }
                // Put node in the array
                B[node.rank] = node;
            }
        }

        linkArray(B);
    }

    public void linkArray(HeapNode[] arr) {
//    	System.out.println(" before array link");
//    	printer.print(this, false);
        HeapNode last = null;
        HeapNode first = null;
        int count = 0;
        for (HeapNode node : arr) {
            if (node == null)
                continue;
            else count++;
            
            if (first == null)
                first = node;
            if (last == null) {
                last = node;
                continue;
            }

            node.left = last;
            last.right = node;
            

            last = node;
        }
        last.right = first;
        first.left = last;
        
        head = first;
        size = count;
    }
    public void updateMin() {
    	HeapNode m = head;
    	HeapNode[] roots = getRoots();
    	for(HeapNode root : roots) {
    		if(root.getKey() < m.getKey()) {
    			m = root;
    		}
    	}
		min = m;
        return;
        
    }
    
    
    
    private HeapNode[] getRoots() {
        HeapNode[] arr = new HeapNode[size];
        HeapNode curr = head;
        for(int i = 0; i < arr.length; i++) {
            arr[i] = curr;
            curr = curr.right;
        }
        return arr;
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
	   
	   
	   staticMeld(this, heap2);
   }
   
   
   //melds heap2 from the rigth of heap 1
   public static void staticMeld(FibonacciHeap heap1, FibonacciHeap heap2) {

	   if(heap1.isEmpty() && heap2.isEmpty()) {return;}
	   if(heap1.isEmpty()) {
		   heap1.head = heap2.head;
		   heap1.min = heap2.min;
		   heap1.size = heap2.size; 
		   heap1.marked = heap2.marked;
		   heap1.n = heap2.n;
	       return;
	   }
	   if(heap2.isEmpty()) {
		   heap2.head = heap2.head;
		   heap2.min = heap2.min;
		   heap2.size = heap2.size; 
		   heap2.marked = heap2.marked;
		   heap2.n = heap2.n;
	       return;
	   }
	   HeapNode otherHead = heap2.head;
       HeapNode otherTail = heap2.getOldest();
       heap1.getOldest().right = otherHead;
       otherHead.left = heap1.getOldest();
       otherTail.right = heap1.head;
       heap1.head.left = otherTail;
       
       int newSize = heap1.size + heap2.size;
       int newN = heap1.n + heap2.n;
       HeapNode newMin = heap1.min;
 

       if(heap2.min.getKey() < heap1.min.getKey()){
    	   newMin = heap2.min;
       }
       
       int newM = heap1.marked + heap2.marked;

       
       heap1.size = newSize;
       heap2.size = newSize;
       heap1.min = newMin;
       heap2.min = newMin;
       heap1.n = newN;
       heap2.n = newN;
       heap1.marked = newM;
       heap2.marked = newM;

       
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
    	double goldenRatio = (1 + Math.sqrt(5)) / 2;
        int maxDegree = (int) (Math.log(this.n) / Math.log(goldenRatio));
    	int[] arr = new int[maxDegree + 1];
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
    	int delta = x.getKey() - min.getKey();
    	decreaseKey(x,delta + 1);
        
    	deleteMin();

    }

   /**
    * public void decreaseKey(HeapNode x, int delta)
    *
    * Decreases the key of the node x by a non-negative value delta. The structure of the heap should be updated
    * to reflect this change (for example, the cascading cuts procedure should be applied if needed).
    */
    public void decreaseKey(HeapNode x, int delta) {
    	x.key -= Math.abs(delta);
        HeapNode parent = x.parent;
        if(x.getKey() < this.min.getKey()){
            min = x;}
        if(parent == null){
            return;
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
        return (n - marked); // should be replaced by student code
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
        node.parent = null;
        
        if(parent != null) {
            parent.rank --;
            
            if(parent.leftChild == node){
                if(right == node || left == node) {
                	parent.leftChild = null;
                } else {
                	parent.leftChild = right;
                }
            }
        }
        if(right != node){
            right.left = left;
        }
        if(left != node){
            left.right = right;
        }
        HeapNode old = head.left;
        head.left = node;
        node.right = head;
        node.left = old;
        node.mark = 0;
        head = node;
        size ++;
    }

    public void cascading_cut(HeapNode node, HeapNode parent){
    	cut(node);
        if(parent.parent != null){
            if(parent.mark == 0){
            	//cut(node);
                parent.mark = 1;
                marked ++;
            } else{
                cascading_cut(parent, parent.parent);
            }}

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
    public HeapNode getFirst(){return head;}

    /**
     *  UnNeeded func
     */
    public void infoPrint() {
        System.out.println("head: " + head.key);
        System.out.println("min: " + min.key);
        System.out.println("size: " +size);
        System.out.println("n: " + n);

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
           if (this.parent != null) {
               this.parent.rank++;
               this.parent.leftChild = other;
           }


    	  }
       public void selfRemove() {
           // Have no siblings
           if (left == this) {
               if (this.parent != null) {
                   this.parent.leftChild = null;
                   this.parent.rank--;
               }
           } else { //Have siblings
               if (this.parent != null) {
                   if (this.parent.leftChild == this) {
                       this.parent.leftChild = this.left;
                   }
                   this.parent.rank--;
               }
               this.right.left = this.left;
               this.left.right = this.right;
           }
       }

    }
}