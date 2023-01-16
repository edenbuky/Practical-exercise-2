import java.util.ArrayList;
import java.util.Collections;

public class myTest {
	static HeapPrinter printer = new HeapPrinter(System.out);
	static FibonacciHeap heap = new FibonacciHeap();
	static FibonacciHeap.HeapNode node1 = new FibonacciHeap.HeapNode(4);
	
	
	
	public static void main(String[] args) {


		test0();
		
		
	}
	
	static void test0() {
        String test = "test0";
        FibonacciHeap fibonacciHeap = new FibonacciHeap();

        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        for (int i = 0; i < 5; i++) {
            fibonacciHeap.insert(numbers.get(i));
            
        }
        printer.print(fibonacciHeap, false);
        System.out.println(fibonacciHeap.findMin().getKey());

        for (int i = 0; i < 5; i++) {
            if (fibonacciHeap.findMin().getKey() != i) {
            	System.out.println("i is: " + i);
                System.out.println(fibonacciHeap.findMin().getKey());

                return;
            }
            fibonacciHeap.deleteMin();
            printer.print(fibonacciHeap, false);
            
        }
        

    }
	
	
	

}
