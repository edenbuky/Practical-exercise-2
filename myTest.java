

public class myTest {
	static HeapPrinter printer = new HeapPrinter(System.out);
	static FibonacciHeap heap = new FibonacciHeap();
	
	
	
	public static void main(String[] args) {
		FibonacciHeap.HeapNode a = new FibonacciHeap.HeapNode(5);
		FibonacciHeap.HeapNode b = new FibonacciHeap.HeapNode(4);
		FibonacciHeap.HeapNode c = new FibonacciHeap.HeapNode(3);
		heap.insert(5);
		heap.insert(4);
		heap.insert(3);
		printer.print(heap, true);
		System.out.println(heap.size);
		
	}

	

}
