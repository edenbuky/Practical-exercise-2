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
    public int marked = 0;


    public FibonacciHeap(){
    }


    public FibonacciHeap(HeapNode root){
        min = root;
        head = root;
        head.right = head;
        head.left = head;
        maxDeg = root;
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
	   
   }

   /**
    * public void deleteMin()
    *
    * Deletes the node containing the minimum key.
    *
    */
    public void deleteMin() {
        n --;
    	HeapNode current, temp;
        if (n == 0){
            head = null;
            min = null;
            maxDeg = null;
            size = 0;
            return;
        }
        if (head == min){
            head = min.right;
        }
        //add minimum child-trees to the heap and delete minimum
        current = min.leftChild;
        if (current != null)
        {   temp = current.left;
            temp.mark = 0;
            head = current;
            if (size == 1){
                temp.parent = null;
                min.leftChild = null;
            }
            else {
                HeapNode l = min.left, r = min.right;
                temp.parent = null;
                current.left = l;
                l.right = current;
                min.left = null;
                temp.right = r;
                r.left = temp;
                min.right = null;
                min.leftChild = null;
            }
            while (current != temp) {
                current.parent = null;
                if (current.mark == 1) {
                    current.mark = 0;
                    marked--;
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
        if (head.left == head && head.right == head){
            update(head);
        }
        else {consolidating();}

        // Update min-pointer
        current = head.right;
        min = head.right;
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
        root2.left = null;
        root2.right = null;

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
    
    public void update(HeapNode temp) {
        HeapNode current;
        current = temp.right;
        int newSize = 1;
        while (current != temp){
            newSize++;
            if (current.rank < head.rank ){
                head = current;
            }
            current = current.right;
        }
        size = newSize;

    }

    /**
     * Successive linking trees with the same rank, to reduce the amount of threes in the heap
     */
   
    public void consolidating()
    {
    	double goldenRatio = (1 + Math.sqrt(5)) / 2;
        int maxDegree = (int) (Math.log(this.n) / Math.log(goldenRatio));
        HeapNode[] rankCells = new HeapNode[maxDegree + 1];
        HeapNode temp = head, last = getOldest(), current = head, nxt= head.right;
        int deg;
        boolean lastIter = false,indicator = true;
        //emptying the array
        for (int i = 0; i <= maxDegree; i++){rankCells[i] = null;}

        //traverse the root list. Whenever we discover two trees that have the same rank we link these trees.
        while (indicator) {
            if (lastIter){
                indicator = false;
            }
            deg = current.rank;
            temp = current;
            while (rankCells[deg] != null) {
                temp = linking(temp, rankCells[deg]);
                rankCells[deg] = null;
                deg = temp.rank;
            }
            rankCells[deg] = temp;
            if (!indicator){
                head = temp;
                break;
            }
            current = nxt;
            nxt = nxt.right;
            if (current == last){
                lastIter = true;
            }
        }

        //Update the head to the root with the smallest rank
        update(temp);

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
        if(parent == null){
            if(x.getKey() < this.min.getKey()){
                min = x;
                
            }
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
        node.right = null;
        node.left = null;
        node.parent = null;
        
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
        
        head.leftConnect(node);
        head = node;
        size ++;
    }

    public void cascading_cut(HeapNode node, HeapNode parent){
        cut(node);
        if(parent.parent != null){
            if(parent.mark == 0){
                parent.mark = 1;
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
    	   
       }

    }
}