public class ArrayDeque<T> implements Deque<T>{
    private T[] arrayList;
    private int nextFirst;
    private int nextLast;
    private int size;
    private final int FACTOR = 2;
    private final int LEASTLENGTH = 16;

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

    @Override
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

    @Override
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

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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
        if (nextFirst < nextLast) {
            System.arraycopy(arrayList, nextFirst, a, index1, size);
            nextFirst = index1 - 1;
            nextLast = index1 + size;
        } else {
            System.arraycopy(arrayList, nextFirst, a, index1, arrayList.length - nextFirst);
            int index2 = index1 + arrayList.length - nextFirst;
            System.arraycopy(arrayList, 0, a, index2, nextLast);

            nextFirst = index1 - 1;
            nextLast = index1 + size;
        }

        arrayList = a;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null; 
        }

        if (nextFirst == arrayList.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        T item = arrayList[nextFirst];
        arrayList[nextFirst] = null;
        size -= 1;

        if ((arrayList.length >= LEASTLENGTH) && ((4 * size) < arrayList.length)) {
            reducingArray();
        }

        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (nextLast == 0) {
            nextLast = arrayList.length - 1;
        } else {
            nextLast -= 1;
        }
        T item = arrayList[nextLast];
        arrayList[nextLast] = null;
        size -= 1;

        if ((arrayList.length >= LEASTLENGTH) && ((4 * size) < arrayList.length)) {
            reducingArray();
        }

        return item;
    }

    @Override
    public T get(int index) {
        if (size == 0 || index >= arrayList.length) {
            return null;
        }

        int realIndex = nextFirst;
        while (index >= 0) {
            if (realIndex == arrayList.length - 1) {
                realIndex = 0;
            } else {
                realIndex += 1;
            }
            index -= 1;
        }
        return arrayList[realIndex];
    }
}
