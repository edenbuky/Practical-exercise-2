import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class myTest {
	static HeapPrinter printer = new HeapPrinter(System.out);
	static Heap2 heap2 = new Heap2();
	static FibonacciHeap.HeapNode node1 = new FibonacciHeap.HeapNode(4);

    static FibonacciHeap fibonacciHeap;
    static ArrayList<FibonacciHeap.HeapNode> nodeArr = new ArrayList<>();
	
	public static void main(String[] args) {


		FibonacciHeap f = new FibonacciHeap();
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 0; i < 20; i ++) {
			a.add(i);
		}
		Collections.shuffle(a);
		for(int n : a) {
			f.insert(n);
		}
		f.deleteMin();
		printer.print(f, false);
		f.deleteMin();
		printer.print(f, false);
		f.deleteMin();
		printer.print(f, false);
		f.deleteMin();
		printer.print(f, false);
		
		int[] kmin = FibonacciHeap.kMin(f, 10);
		
		//test29();
		System.out.println("done");
		
		
	}
	
	static void test29() {
        /* kMin */
        fibonacciHeap = new FibonacciHeap();
        for (int i = 0; i < 33; i++) {
            fibonacciHeap.insert(i);
        }
        printer.print(fibonacciHeap, false);
        fibonacciHeap.deleteMin();
        int[] kmin = FibonacciHeap.kMin(fibonacciHeap, 10);
        for (int i = 0; i < kmin.length; i++) {
            if (kmin[i] != i + 1) {
            	System.out.println(Arrays.toString(kmin));
                return;
            }
        }
    }

	
	
	

    static void addKeys(int start) {
        for (int i = 0; i < 1000; i++) {//@@@@@@@ i<1000 @@@@@
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
