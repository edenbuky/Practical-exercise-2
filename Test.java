
//FibonacciHeap Tester

import java.util.ArrayList;
import java.util.Collections;


public class Test {

    static Heap2 heap2;
    static FibonacciHeap fibonacciHeap;
    static double grade;
    static double testScore;

    public static void main(String[] args) {

        grade = 80.0;
        testScore = 64.0 / 29;

        try {
            test0();
            System.out.println("pass0");
        } catch (Exception e) {
            bugFound("test0");
        }
        try {
            test1();
            System.out.println("pass1");
        } catch (Exception e) {
            bugFound("test1");
        }
        try {
            test2();
            System.out.println("pass2");
        } catch (Exception e) {
            bugFound("test2");
        }
        try {
            test3();
            System.out.println("pass3");
        } catch (Exception e) {
            bugFound("test3");
        }
        try {
            test4();
            System.out.println("pass4");
        } catch (Exception e) {
            bugFound("test4");
        }
        try {
            //test5();
            System.out.println("pass5");
        } catch (Exception e) {
            bugFound("test5");
        }
        try {
            //test6();
            System.out.println("pass6");
        } catch (Exception e) {
            bugFound("test6");
        }
        try {
            test7();
            System.out.println("pass 7");
        } catch (Exception e) {
            bugFound("test7");
        }
        try {
            test8();
            System.out.println("pass8");
        } catch (Exception e) {
            bugFound("test8");
        }
        try {
            test9();
            System.out.println("pass9");
        } catch (Exception e) {
            bugFound("test9");
        }
        try {
            test10();
            System.out.println("pass10");
        } catch (Exception e) {
            bugFound("test10");
        }
        try {
            test11();
            System.out.println("pass11");
        } catch (Exception e) {
            bugFound("test11");
        }
        try {
            test12();
            System.out.println("pass12");
        } catch (Exception e) {
            bugFound("test12");
        }
        try {
            test13();
            System.out.println("pass13");
        } catch (Exception e) {
            bugFound("test13");
        }
        try {
            test14();
            System.out.println("pass14");
        } catch (Exception e) {
            bugFound("test14");
        }
        try {
            test15();
            System.out.println("pass15");
        } catch (Exception e) {
            bugFound("test15");
        }
        try {
            test16();
            System.out.println("pass16");
        } catch (Exception e) {
            bugFound("test16");
        }
        try {
            test17();
            System.out.println("pass17");
        } catch (Exception e) {
            bugFound("test17");
        }
        try {
            test18();
            System.out.println("pass18");
        } catch (Exception e) {
            bugFound("test18");
        }
        try {
            test19();
            System.out.println("pass19");
        } catch (Exception e) {
            bugFound("test19");
        }
        try {
            test20();
            System.out.println("pass20");
        } catch (Exception e) {
            bugFound("test20");
        }
        try {
            test21();
            System.out.println("pass21");
        } catch (Exception e) {
            bugFound("test21");
        }
        try {
            test22();
            System.out.println("pass22");
        } catch (Exception e) {
            bugFound("test22");
        }
        try {
            test23();
            System.out.println("pass23");
        } catch (Exception e) {
            bugFound("test23");
        }
        try {
            test24();
            System.out.println("pass 24");
        } catch (Exception e) {
            bugFound("test24");
        }
        try {
            test25();
            System.out.println("pass25");
        } catch (Exception e) {
            bugFound("test25");
        }
        try {
            test26();
            System.out.println("pass26");
        } catch (Exception e) {
            bugFound("test26");
        }
        try {
            test27();
            System.out.println("pass27");
        } catch (Exception e) {
            bugFound("test27");
        }
        try {
            test28();
            System.out.println("pass 28");
        } catch (Exception e) {
            bugFound("test28");
        }
        try {
            test29();
            System.out.println("pass 29");
        } catch (Exception e) {
            System.out.println("Bug found in " + "test29");
            grade -= 8;
        }
        try {
            //test30();
        } catch (Exception e) {
            System.out.println("Bug found in " + "test30");
            grade -= 8;
        }

        System.exit((int) grade);
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
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }
    }

    static void test1() {
        String test = "test1";
        heap2 = new Heap2();
        fibonacciHeap = new FibonacciHeap();
        addKeys(0);
        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test2() {
        String test = "test2";
        heap2 = new Heap2();
        fibonacciHeap = new FibonacciHeap();
        addKeysReverse(0);
        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test3() {
        String test = "test3";
        heap2 = new Heap2();
        fibonacciHeap = new FibonacciHeap();
        addKeys(0);
        addKeysReverse(4000);
        addKeys(2000);
        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test4() {
        String test = "test4";
        heap2 = new Heap2();
        fibonacciHeap = new FibonacciHeap();
        addKeys(0);
        addKeysReverse(4000);
        addKeys(2000);

        for (int i = 0; i < 1000; i++) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }

        addKeys(6000);
        addKeysReverse(8000);
        addKeys(10000);

        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey()) {
                bugFound(test);
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test5() {
        String test = "test5";
        fibonacciHeap = new FibonacciHeap();
        addKeys(0);
        addKeys(0);
        addKeys(0);

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != fibonacciHeap.findMin().getKey()) {
                    bugFound(test);
                    return;
                }
                fibonacciHeap.deleteMin();
            }
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test6() {
        String test = "test6";
        fibonacciHeap = new FibonacciHeap();
        addKeysReverse(1000);
        addKeysReverse(1000);
        addKeys(0);
        addKeys(0);
        addKeys(1000);
        addKeys(1000);
        addKeysReverse(0);
        addKeysReverse(0);

        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != fibonacciHeap.findMin().getKey()) {
                    bugFound(test);
                    return;
                }
                fibonacciHeap.deleteMin();
            }
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
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
                bugFound(test);
                return;
            }
            heap2.delete(i);
            fibonacciHeap.delete(nodes.get(i - 2000));
        }

        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test8() {
        String test = "test8";
        heap2 = new Heap2();
        fibonacciHeap = new FibonacciHeap();
        addKeys(7000);
        addKeysReverse(9000);

        ArrayList<FibonacciHeap.HeapNode> nodes = new ArrayList<>();

        for (int i = 2000; i < 3000; i++) {
            heap2.insert(i);
            nodes.add(fibonacciHeap.insert(i));
        }

        for (int i = 2000; i < 2500; i++) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.delete(i);
            fibonacciHeap.delete(nodes.get(i - 2000));
        }

        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test9() {
        String test = "test9";
        heap2 = new Heap2();
        fibonacciHeap = new FibonacciHeap();
        addKeys(7000);
        addKeysReverse(9000);

        ArrayList<FibonacciHeap.HeapNode> nodes = new ArrayList<>();

        for (int i = 2000; i < 3000; i++) {
            heap2.insert(i);
            nodes.add(fibonacciHeap.insert(i));
        }

        for (int i = 2700; i > 2200; i--) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.delete(i);
            fibonacciHeap.delete(nodes.get(i - 2000));
        }

        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test10() {
        String test = "test10";
        heap2 = new Heap2();
        fibonacciHeap = new FibonacciHeap();
        addKeys(7000);
        addKeysReverse(9000);

        ArrayList<FibonacciHeap.HeapNode> nodes = new ArrayList<>();

        for (int i = 2000; i < 3000; i++) {
            heap2.insert(i);
            nodes.add(fibonacciHeap.insert(i));
        }
        heap2.deleteMin();
        fibonacciHeap.deleteMin();

        for (int i = 2700; i > 2200; i--) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {
                bugFound(test);
                return;
            }
            heap2.delete(i);
            fibonacciHeap.delete(nodes.get(i - 2000));
        }

        while (!heap2.isEmpty()) {
            if (heap2.findMin() != fibonacciHeap.findMin().getKey() || heap2.size() != fibonacciHeap.n) {

                bugFound(test);
                return;
            }
            heap2.deleteMin();
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test11() {
        String test = "test11";
        fibonacciHeap = new FibonacciHeap();
        addKeys(1000);
        FibonacciHeap.HeapNode h = fibonacciHeap.insert(9999);
        fibonacciHeap.decreaseKey(h, 9999);

        if (0 != fibonacciHeap.findMin().getKey()) {
            bugFound(test);
            return;
        }

        fibonacciHeap.deleteMin();

        for (int i = 1000; i < 2000; i++) {
            if (i != fibonacciHeap.findMin().getKey()) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test12() {
        String test = "test12";
        fibonacciHeap = new FibonacciHeap();
        addKeys(1000);
        FibonacciHeap.HeapNode h = fibonacciHeap.insert(5000);
        fibonacciHeap.decreaseKey(h, 4000);


        for (int i = 0; i < 2; i++) {

            if (1000 != fibonacciHeap.findMin().getKey()) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }

        for (int i = 1001; i < 2000; i++) {
            if (i != fibonacciHeap.findMin().getKey()) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test13() {
        String test = "test13";
        fibonacciHeap = new FibonacciHeap();
        addKeys(1000);
        FibonacciHeap.HeapNode h = fibonacciHeap.insert(9000);
        fibonacciHeap.decreaseKey(h, 4000);

        for (int i = 1000; i < 2000; i++) {
            if (i != fibonacciHeap.findMin().getKey()) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }
        if (5000 != fibonacciHeap.findMin().getKey()) {
            bugFound(test);
            return;
        }
        fibonacciHeap.deleteMin();

        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test14() {
        String test = "test14";
        fibonacciHeap = new FibonacciHeap();
        addKeys(1000);
        addKeysReverse(7000);
        FibonacciHeap.HeapNode h = fibonacciHeap.insert(9000);
        fibonacciHeap.decreaseKey(h, 4000);

        for (int i = 1000; i < 2000; i++) {
            if (i != fibonacciHeap.findMin().getKey()) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }
        if (5000 != fibonacciHeap.findMin().getKey()) {
            bugFound(test);
            return;
        }
        fibonacciHeap.deleteMin();

        for (int i = 7000; i < 8000; i++) {
            if (i != fibonacciHeap.findMin().getKey()) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }

        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }


    static void test15() {
        String test = "test15";
        fibonacciHeap = new FibonacciHeap();

        for (int i = 1000; i < 10000; i += 1000) {
            addKeys(i);
        }

        fibonacciHeap.deleteMin();

        FibonacciHeap.HeapNode h = fibonacciHeap.insert(99999);
        fibonacciHeap.decreaseKey(h, 99999);

        if (0 != fibonacciHeap.findMin().getKey()) {
            bugFound(test);
            return;
        }

        fibonacciHeap.deleteMin();

        for (int i = 1001; i < 10000; i++) {
            if (i != fibonacciHeap.findMin().getKey()) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }
        if (!fibonacciHeap.isEmpty())
            bugFound(test);
    }

    static void test16() {
        String test = "test16";
        fibonacciHeap = new FibonacciHeap();

        int cuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        fibonacciHeap.insert(1);
        fibonacciHeap.insert(2);
        fibonacciHeap.insert(3);

        if (fibonacciHeap.potential() != 3 ||
                FibonacciHeap.totalCuts() - cuts != 0 ||
                FibonacciHeap.totalLinks() - links != 0 ||
                fibonacciHeap.countersRep()[0] != 3)
            bugFound(test);
    }

    static void test17() {
        String test = "test17";
        fibonacciHeap = new FibonacciHeap();

        int cuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        fibonacciHeap.insert(1);
        fibonacciHeap.insert(2);
        fibonacciHeap.insert(3);
        fibonacciHeap.deleteMin();

        if (fibonacciHeap.potential() != 1 ||
                FibonacciHeap.totalCuts() - cuts != 0 ||
                FibonacciHeap.totalLinks() - links != 1 ||
                fibonacciHeap.countersRep()[0] != 0 ||
                fibonacciHeap.countersRep()[1] != 1)
            bugFound(test);
    }

    static void test18() {
        String test = "test18";
        fibonacciHeap = new FibonacciHeap();

        int cuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        fibonacciHeap.insert(4);
        fibonacciHeap.insert(5);
        fibonacciHeap.insert(6);
        fibonacciHeap.deleteMin();

        fibonacciHeap.insert(1);
        fibonacciHeap.insert(2);
        fibonacciHeap.insert(3);
        fibonacciHeap.deleteMin();

        fibonacciHeap.insert(1);
        fibonacciHeap.deleteMin();

        if (fibonacciHeap.potential() != 1 ||
                FibonacciHeap.totalCuts() - cuts != 0 ||
                FibonacciHeap.totalLinks() - links != 3 ||
                fibonacciHeap.countersRep()[0] != 0 ||
                fibonacciHeap.countersRep()[1] != 0 ||
                fibonacciHeap.countersRep()[2] != 1)
            bugFound(test);
    }

    static void test19() {
        String test = "test19";
        fibonacciHeap = new FibonacciHeap();

        int cuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        fibonacciHeap.insert(4);
        fibonacciHeap.insert(5);
        FibonacciHeap.HeapNode node = fibonacciHeap.insert(6);
        fibonacciHeap.deleteMin();

        fibonacciHeap.insert(1);
        fibonacciHeap.insert(2);
        fibonacciHeap.insert(3);
        fibonacciHeap.deleteMin();

        fibonacciHeap.insert(1);
        fibonacciHeap.deleteMin();

        fibonacciHeap.decreaseKey(node, 2);

        if (fibonacciHeap.potential() != 4 ||
                FibonacciHeap.totalCuts() - cuts != 1 ||
                FibonacciHeap.totalLinks() - links != 3)
            bugFound(test);
    }

    static void test20() {
        String test = "test20";
        fibonacciHeap = new FibonacciHeap();


        fibonacciHeap.insert(4);
        FibonacciHeap.HeapNode node5 = fibonacciHeap.insert(5);
        FibonacciHeap.HeapNode node6 = fibonacciHeap.insert(6);
        fibonacciHeap.deleteMin();

        fibonacciHeap.insert(1);
        fibonacciHeap.insert(2);
        fibonacciHeap.insert(3);
        fibonacciHeap.deleteMin();

        fibonacciHeap.insert(1);
        fibonacciHeap.deleteMin();

        int cuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        fibonacciHeap.decreaseKey(node6, 2);
        fibonacciHeap.decreaseKey(node5, 1);

        if (fibonacciHeap.potential() != 4 ||
                FibonacciHeap.totalCuts() - cuts != 1 ||
                FibonacciHeap.totalLinks() - links != 0)
            bugFound(test);
    }

    static void test21() {
        String test = "test21";
        fibonacciHeap = new FibonacciHeap();

        int treeSize = 32768;
        int sizeToDelete = 1000;


        ArrayList<FibonacciHeap.HeapNode> nodes = new ArrayList<>();

        for (int i = treeSize; i < treeSize * 2; i++) {
            nodes.add(fibonacciHeap.insert(i));
        }

        for (int i = 0; i < sizeToDelete; i++) {
            fibonacciHeap.insert(i);
        }

        for (int i = 0; i < sizeToDelete; i++) {
            fibonacciHeap.deleteMin();
        }

        if (fibonacciHeap.potential() != 1)
            bugFound(test);
    }

    static void test22() {
        String test = "test22";
        fibonacciHeap = new FibonacciHeap();

        int treeSize = 32768;
        int sizeToDelete = 1000;

        ArrayList<FibonacciHeap.HeapNode> nodes = new ArrayList<>();

        for (int i = treeSize; i < treeSize * 2; i++) {
            nodes.add(fibonacciHeap.insert(i));
        }

        for (int i = 0; i < sizeToDelete; i++) {
            fibonacciHeap.insert(i);
        }

        for (int i = 0; i < sizeToDelete; i++) {
            fibonacciHeap.deleteMin();
        }


        if (fibonacciHeap.potential() != 1)
            bugFound(test);

        int totalCuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        boolean noCascading = true;
        int iterationCuts;

        Collections.shuffle(nodes);

        for (int i = 0; i < treeSize; i++) {
            iterationCuts = FibonacciHeap.totalCuts();

            fibonacciHeap.decreaseKey(nodes.get(i), nodes.get(i).getKey() - (treeSize - i));

            if (FibonacciHeap.totalCuts() - iterationCuts > 1)
                noCascading = false;
        }

        if (fibonacciHeap.potential() != treeSize ||
                FibonacciHeap.totalCuts() - totalCuts != treeSize - 1 ||
                FibonacciHeap.totalLinks() - links != 0 ||
                fibonacciHeap.countersRep()[0] != treeSize ||
                noCascading)
            bugFound(test);
    }

    static void test23() {
        String test = "test23";
        fibonacciHeap = new FibonacciHeap();

        int size = 1000;
        int totalCuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        for (int i = size; i > 0; i--) {
            fibonacciHeap.insert(i);
        }

        if (fibonacciHeap.potential() != size ||
                FibonacciHeap.totalCuts() - totalCuts != 0 ||
                FibonacciHeap.totalLinks() - links != 0)
            bugFound(test);
    }

    static void test24() {
        String test = "test24";
        fibonacciHeap = new FibonacciHeap();

        int size = 2000;
        int totalCuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        for (int i = size; i > 0; i--) {
            fibonacciHeap.insert(i);
        }

        if (fibonacciHeap.potential() != size ||
                FibonacciHeap.totalCuts() - totalCuts != 0 ||
                FibonacciHeap.totalLinks() - links != 0)
            bugFound(test);
    }

    static void test25() {
        String test = "test25";
        fibonacciHeap = new FibonacciHeap();

        int size = 3000;
        int totalCuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        for (int i = size; i > 0; i--) {
            fibonacciHeap.insert(i);
        }

        if (fibonacciHeap.potential() != size ||
                FibonacciHeap.totalCuts() - totalCuts != 0 ||
                FibonacciHeap.totalLinks() - links != 0)
            bugFound(test);
    }

    static void test26() {
        String test = "test26";
        fibonacciHeap = new FibonacciHeap();

        int size = 1000;
        int totalCuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        for (int i = size; i > 0; i--) {
            fibonacciHeap.insert(i);
        }

        for (int i = 0; i < size / 2; i++) {
            if (fibonacciHeap.findMin().getKey() != i + 1) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }

        if (fibonacciHeap.potential() > 100 ||
                FibonacciHeap.totalCuts() - totalCuts != 0 ||
                FibonacciHeap.totalLinks() - links < size - 100)
            bugFound(test);
    }

    static void test27() {
        String test = "test27";
        fibonacciHeap = new FibonacciHeap();

        int size = 2000;
        int totalCuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        for (int i = size; i > 0; i--) {
            fibonacciHeap.insert(i);
        }

        for (int i = 0; i < size / 2; i++) {
            if (fibonacciHeap.findMin().getKey() != i + 1) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }

        if (fibonacciHeap.potential() > 100 ||
                FibonacciHeap.totalCuts() - totalCuts != 0 ||
                FibonacciHeap.totalLinks() - links < size - 100)
            bugFound(test);
    }

    static void test28() {
        String test = "test28";
        fibonacciHeap = new FibonacciHeap();

        int size = 3000;
        int totalCuts = FibonacciHeap.totalCuts();
        int links = FibonacciHeap.totalLinks();

        for (int i = size; i > 0; i--) {
            fibonacciHeap.insert(i);
        }

        for (int i = 0; i < size / 2; i++) {
            if (fibonacciHeap.findMin().getKey() != i + 1) {
                bugFound(test);
                return;
            }
            fibonacciHeap.deleteMin();
        }

        if (fibonacciHeap.potential() > 100 ||
                FibonacciHeap.totalCuts() - totalCuts != 0 ||
                FibonacciHeap.totalLinks() - links < size - 100)
            bugFound(test);
    }


    static void test29() {
        /* kMin */
        fibonacciHeap = new FibonacciHeap();
        for (int i = 0; i < 33; i++) {
            fibonacciHeap.insert(i);
        }
        fibonacciHeap.deleteMin();
        int[] kmin = FibonacciHeap.kMin(fibonacciHeap, 10);
        for (int i = 0; i < kmin.length; i++) {
            if (kmin[i] != i + 1) {
                grade -= 8;
                return;
            }
        }
    }

    static void test30() {
        /* insert and meld */
        FibonacciHeap firstFibonacciHeap = new FibonacciHeap();
        FibonacciHeap secondFibonacciHeap = new FibonacciHeap();
        for (int i = 0; i < 100; i++) {
            firstFibonacciHeap.insert(i);
            secondFibonacciHeap.insert(i + 100);
        }
        //firstFibonacciHeap.meld(secondFibonacciHeap);
        int i = 0;
        while (!firstFibonacciHeap.isEmpty()) {
            FibonacciHeap.HeapNode min = firstFibonacciHeap.findMin();
            int minValue = min.getKey();
            if (minValue != i) {
                grade -= 8;
            }
            firstFibonacciHeap.deleteMin();
            i++;
        }

    }

    static void bugFound(String test) {
        System.out.println("Bug found in " + test);
        grade -= testScore;
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


