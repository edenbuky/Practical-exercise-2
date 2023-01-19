import java.util.ArrayList;
import java.util.Collections;

public class myTest {
	static HeapPrinter printer = new HeapPrinter(System.out);
	static Heap2 heap2 = new Heap2();
	static FibonacciHeap.HeapNode node1 = new FibonacciHeap.HeapNode(4);

    static FibonacciHeap fibonacciHeap;
    static ArrayList<FibonacciHeap.HeapNode> nodeArr = new ArrayList<>();
	
	public static void main(String[] args) {


		testY();
		test0();
		printer.print(fibonacciHeap, false);
		System.out.println("done");
		
		
	}
	static void test0() {
        String test = "test0";
        fibonacciHeap = new FibonacciHeap();

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        for (int i = 0; i < 5; i++) {
            fibonacciHeap.insert(numbers.get(i));
        }

        for (int i = 0; i < 5; i++) {
            if (fibonacciHeap.findMin().getKey() != i) {
            	System.out.println("error" +fibonacciHeap.findMin().getKey());
                return;
            }
            fibonacciHeap.deleteMin();
        }
    }
	
	static void testY() {
		fibonacciHeap = new FibonacciHeap();
		for(int i = 0; i < 10; i++) {
			fibonacciHeap.insert(i);
		}
		printer.print(fibonacciHeap, false);
		System.out.println(fibonacciHeap.findMin().getKey());
		
		fibonacciHeap.deleteMin();
		printer.print(fibonacciHeap, false);
		printer.print(fibonacciHeap, true);
		System.out.println(fibonacciHeap.findMin().getKey());
		
		fibonacciHeap.deleteMin();
		
		
		
		
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
