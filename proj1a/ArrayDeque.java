public class ArrayDeque<T> {
    private T[] arrayList;
    private int nextFirst;
    private int nextLast;
    private int size;
    private final int FACTOR = 2;

    public ArrayDeque() {
        arrayList = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = nextFirst + 1;
        size = 0;
    }
    
    private void enlargingArray() {
        T[] a = (T[]) new Object[arrayList.length * FACTOR];
        if (nextFirst == arrayList.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        int index1 = a.length / 4;
        System.arraycopy(arrayList, nextFirst, a, index1, arrayList.length - nextFirst);
        int index2 = index1 + arrayList.length - nextFirst;
        System.arraycopy(arrayList, 0, a, index2, nextFirst);
        
        nextFirst = index1 - 1;
        nextLast = index1 + arrayList.length;
        arrayList = a;
    }
    
    public void addFirst(T item) {
        if (size == arrayList.length) {
            enlargingArray();
        }
        arrayList[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = arrayList.length - 1;
        } else {
            nextFirst -= 1; 
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == arrayList.length) {
            enlargingArray();
        }
        arrayList[nextLast] = item;
        if (nextLast == arrayList.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (nextFirst > nextLast) {
            for (int i = nextFirst + 1; i < arrayList.length; i++) {
                System.out.print(arrayList[i] + " ");
            }
            for (int i = 0; i < nextLast; i++) {
                System.out.print(arrayList[i] + " ");
            }
        } else {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                System.out.print(arrayList[i] + " ");
            }
        }
    }

    private void reducingArray() {
        T[] a = (T[]) new Object[arrayList.length / FACTOR];
        if (nextFirst == arrayList.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        int index1 = a.length / 4;
        System.arraycopy(arrayList, nextFirst, a, index1, arrayList.length - nextFirst);
        int index2 = index1 + arrayList.length - nextFirst;
        System.arraycopy(arrayList, 0, a, index2, nextFirst);

        nextFirst = index1 - 1;
        nextLast = index1 + arrayList.length;
        arrayList = a;
    }

    public T removeFirst() {
        if (arrayList.length >= 16 && (4 * size) < (arrayList.length)) {
            reducingArray();
        }
        T item;
        if (nextFirst == arrayList.length - 1) {
            item = arrayList[0];
            arrayList[0] = null;
            nextFirst = 0;
        } else {
            nextFirst += 1;
            item = arrayList[nextFirst];
            arrayList[nextFirst] = null;
        }
        size -= 1;
        return item;
    }

    public T removeLast() {
        if (arrayList.length >= 16 && (4 * size) < (arrayList.length)) {
            reducingArray();
        }
        T item;
        if (nextLast == 0) {
            item = arrayList[arrayList.length - 1];
            arrayList[arrayList.length - 1] = null;
            nextLast = arrayList.length - 1;
        } else {
            nextLast -= 1;
            item = arrayList[nextLast];
            arrayList[nextLast] = null;
        }
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (size == 0 || index >= arrayList.length) {
            return null;
        }
        int realIndex = nextFirst;
        while (index > 0) {
            if (realIndex == arrayList.length - 1) {
                realIndex = 0;
            } else {
                realIndex += 1;
            }
            index -= 1;
        }
        realIndex += 1;
        return arrayList[realIndex];
    }

}