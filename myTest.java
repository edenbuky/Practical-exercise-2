import java.util.ArrayList;

public class myTest {
	static HeapPrinter printer = new HeapPrinter(System.out);
	static Heap2 heap2;
	static FibonacciHeap.HeapNode node1 = new FibonacciHeap.HeapNode(4);

    static FibonacciHeap fibonacciHeap;
	
	public static void main(String[] args) {


		test7();
		
		
	}

    static void test7() {
        String test = "test7";
        heap2 = new Heap2();
        fibonacciHeap = new FibonacciHeap();
        addKeys(1000);
        addKeysReverse(3000);

        ArrayList<FibonacciHeap.HeapNode> nodes = new ArrayList<>();

        for (int i = 2000; i < 3000; i++) {
            heap2.insert(i);
            nodes.add(fibonacciHeap.insert(i));
        }

        for (int i = 2000; i < 2500; i++) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                System.out.println("error 1");
                return;
            }
            heap2.delete(i);
            fibonacciHeap.delete(nodes.get(i - 2000));
        }

        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                System.out.println("error 2");
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            System.out.println("error 3");;
    }
    static void addKeys(int start) {
        for (int i = 0; i < 10; i++) {//@@@@@@@ i<1000 @@@@@
            heap2.insert(start + i);
            fibonacciHeap.insert(start + i);
        }
    }
    static void addKeysReverse(int start) {
        for (int i = 999; i >= 0; i--) {
            heap2.insert(start + i);
            fibonacciHeap.insert(start + i);
        }
    }
	
	

}
