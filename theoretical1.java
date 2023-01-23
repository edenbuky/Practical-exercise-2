import java.util.ArrayList;



public class theoretical1 {
	
	public static HeapPrinter printer = new HeapPrinter(System.out);
	public static FibonacciHeap f;
	public static ArrayList<FibonacciHeap.HeapNode> arr;
	
	public static void main(String[] args) {
		for(int i = 6; i <= 14; i +=2) {
			int m = (int)(Math.pow(3, i) -1 );
			q2(m);
		}
		
//		for(int i = 4; i <= 16; i++) {
//			q2(i);
//		}
	}
		
		
	
	static void q2(int m) {
		f = new FibonacciHeap();
		arr = new ArrayList<>();
		long startTime = System.nanoTime();
		for(int k = 0; k <= m; k++) {
			FibonacciHeap.HeapNode node = new FibonacciHeap.HeapNode(k);
			f.insert(node);
		}
		System.out.println("done insert for m: " + m);
		int x = (int) (0.75 * m);
		for(int i = 1; i <= x; i++) {
			f.deleteMin();
		}
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		
		System.out.println("---------------------");
		System.out.println(" m: " + m);
		System.out.println("time: " + totalTime);
		System.out.println("time ms: " + (totalTime/1000000));
		System.out.println("links:" + f.totallinks);
		System.out.println("cuts:" + f.totalcuts);
		System.out.println("potential:" + f.potential());
		System.out.println("#trees:" + f.numOfTrees);
	}
	
	static void q1Helper(int m) {
		f = new FibonacciHeap();
		arr = new ArrayList<>();
		for(int k = m -1; k >= -1; k--) {
			FibonacciHeap.HeapNode node = new FibonacciHeap.HeapNode(k);
			f.insert(node);
			arr.add(node);
		}
		arr.remove(f.findMin());
		f.deleteMin();
		
		printer.print(f, false);
		System.out.println(arr);

		for(int i = log2(m); i >= 1;i--) {
			int idx = (int) (m - Math.pow(2, i) + 1);
			FibonacciHeap.HeapNode get = arr.get(idx-1);
			System.out.println("decreasing " + get.getKey() + " at "+ idx);
			f.decreaseKey(get, m+1);
			printer.print(f, false);
		}
		int idx = (int) m-2;
		FibonacciHeap.HeapNode get = arr.get(idx-1);
		System.out.println("fff decreasing " + get.getKey() + " at "+ (idx-1));
		f.decreaseKey(get, m+1);
		printer.print(f, false);
		
		
		
	}
	static int log2(int m) {
		return (int) (Math.log(m) / Math.log(2));
	}
	
	static void q1(int m) {
		 f = new FibonacciHeap();
		arr = new ArrayList<>();
		long startTime = System.nanoTime();
		for(int k = m -1; k >= -1; k--) {
			FibonacciHeap.HeapNode node = new FibonacciHeap.HeapNode(k);
			f.insert(node);
			arr.add(node);
		}
		arr.remove(f.findMin());
		f.deleteMin();
		

		for(int i = log2(m); i >= 1;i--) {
			int idx = (int) (m - Math.pow(2, i) + 1);
			FibonacciHeap.HeapNode get = arr.get(idx-1);
			f.decreaseKey(get, m+1);
		}
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
//		int idx = m-2;
//		
//		FibonacciHeap.HeapNode get = arr.get(idx-1);
//		System.out.println("fff decreasing " + get.getKey());
//
//		f.decreaseKey(get, m+1);

		System.out.println("---------------------");
		System.out.println("power of m: " + log2(m));
		System.out.println("time: " + totalTime);
		System.out.println("links:" + f.totallinks);
		System.out.println("cuts:" + f.totalcuts);
		System.out.println("potential:" + f.potential());
		System.out.println("#trees:" + f.numOfTrees);
	}
	
	

}
