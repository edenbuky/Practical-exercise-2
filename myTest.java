import java.util.ArrayList;

public class myTest {
	static HeapPrinter printer = new HeapPrinter(System.out);
	static Heap2 heap2;
	static FibonacciHeap.HeapNode node1 = new FibonacciHeap.HeapNode(4);

    static FibonacciHeap fibonacciHeap;
    static ArrayList<FibonacciHeap.HeapNode> nodeArr = new ArrayList<>();
	
	public static void main(String[] args) {


		test9();
		
		
	}

    static void testY() {
    	fibonacciHeap = new FibonacciHeap();
    	for(int i = 0; i < 10; i++) {
    		FibonacciHeap.HeapNode node = fibonacciHeap.insert(i);
    		nodeArr.add(node);
    	}
    	printer.print(fibonacciHeap, false);
    	fibonacciHeap.delete(nodeArr.get(1));
    	printer.print(fibonacciHeap, false);
    }
	

    static void addKeys(int start) {
        for (int i = 0; i < 10; i++) {//@@@@@@@ i<1000 @@@@@
            heap2.insert(start + i);
            fibonacciHeap.insert(start + i);
        }
    }
    static void addKeysReverse(int start) {
        for (int i = 9; i >= 0; i--) {
            heap2.insert(start + i);
            fibonacciHeap.insert(start + i);
        }
    }
    
    static void test9() {
        String test = "test9";
        heap2 = new Heap2();
        fibonacciHeap = new FibonacciHeap();
        addKeys(70);
        addKeysReverse(90);

        ArrayList<FibonacciHeap.HeapNode> nodes = new ArrayList<>();

        for (int i = 20; i < 30; i++) {
            heap2.insert(i);
            nodes.add(fibonacciHeap.insert(i));
        }

        for (int i = 27; i > 22; i--) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
            	System.out.println("error1" + "ourmin: " + fibonacciHeap.findMin().getKey());
            	System.out.println(heap2.findMin());
            	printer.print(fibonacciHeap, false);
            	return;
            }
            System.out.println(i-20);
            System.out.println("deleting: " + nodes.get(i-20).getKey());
            printer.print(fibonacciHeap, false);
            heap2.delete(i);
            fibonacciHeap.delete(nodes.get(i - 20));
        }
        System.out.println("passed1");

        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                System.out.println("error2");
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
        	System.out.println("error3");
        System.out.println("done");
    }
	
	

}
