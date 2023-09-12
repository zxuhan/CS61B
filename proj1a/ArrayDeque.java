public class ArrayDeque<T> {
    private T[] arrayList;
    private int usedItem;
    private int FACTOR = 2;

    public ArrayDeque() {
        arrayList = (T[]) new Object[8];
        usedItem = 0;
    }

    private T[] enlargingArray() {
        T[] a;
        if (usedItem == arrayList.length) {
            a = (T[]) new Object[arrayList.length * FACTOR];
        } else {
            a = (T[]) new Object[arrayList.length];
        }
        return a;
    }
    
    public void addFirst(T item) {
        T[] a = enlargingArray();
        System.arraycopy(arrayList, 0, a, 1, arrayList.length);
        a[0] = item;
        arrayList = a;
        usedItem += 1;
    }

    public void addLast(T item) {
        T[] a = enlargingArray();
        System.arraycopy(arrayList, 0, a, 0, arrayList.length);
        a[a.length - 1] = item;
        arrayList = a;
        usedItem += 1;
    }

    public boolean isEmpty() {
        if (arrayList.length == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return arrayList.length;
    }

    public void printDeque() {
        int i = 0;
        for (; i < arrayList.length - 1; i++) {
            System.out.print(arrayList[i] + " ");
        }
        System.out.println(arrayList[i]);
    }


    private T[] reducingArray() {
        T[] a;
        if (usedItem / arrayList.length <= 0.25) {
            a = (T[]) new Object[Math.round(arrayList.length / 2)];
        } else {
            a = (T[]) new Object[arrayList.length];
        }
        return a;
    }

    public T removeFirst() {
        T res = arrayList[0];
        usedItem -= 1;
        T[] a = reducingArray();
        System.arraycopy(arrayList, 1, a, 0, arrayList.length - 1);
        return res;
    }

    public T removeLast() {
        T res = arrayList[arrayList.length - 1];
        usedItem -= 1;
        T[] a = reducingArray();
        System.arraycopy(arrayList, 0, res, 0, arrayList.length - 1);
        return res;
    }

    public T get(int index) {
        return arrayList[index];
    }
}