import java.util.TreeSet;

class Heap2 {
    private TreeSet<Integer> set;

    Heap2() {
        this.set = new TreeSet<Integer>();
    }

    public int size() {
        return this.set.size();
    }

    public boolean isEmpty() {
        return this.set.isEmpty();
    }

    public void insert(int v) {
        this.set.add((Integer)v);
    }



    public int deleteMin() {
        int min = this.set.first();
        this.set.remove(this.set.first());
        return min;
    }

    public int findMin() {
        if (this.isEmpty())
            return -1;
        return (int)(this.set.first());
    }

    public void delete(int i) {
        this.set.remove((Integer)i);

    }
}

