public class LinkedListDeque<T> {
    private class ListNode {
        private T item;
        private ListNode prev;
        private ListNode next;

        public ListNode(ListNode prev, T item, ListNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
    
    private ListNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        size = 0;
    }

    private void addFirstItem(T item) {
        sentinel = new ListNode(null, null, null);
        sentinel.next = new ListNode(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        if (size == 0) {
            addFirstItem(item);
            return;
        }
        sentinel.next = new ListNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1; 
    }

    public void addLast(T item) {
        if (size == 0) {
            addFirstItem(item);
            return;
        }
        sentinel.prev.next = new ListNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
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
        ListNode p = sentinel.next;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println(p.item);
    }
    
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        ListNode temp = sentinel.next.next;
        T item = sentinel.next.item;
        temp.prev = sentinel;
        sentinel.next = temp;
        size -= 1;
        return item;
    }
    
    public T removeLast() {
        if (size == 0){
            return null;
        }
        ListNode temp = sentinel.prev.prev;
        T item = sentinel.prev.item;
        temp.next = sentinel;
        sentinel.prev = temp;
        size -= 1;
        return item;
    }
    
    public T get(int index) {
        if (size == 0 || index > size) {
            return null;
        }
        ListNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    private ListNode getRecursiveNode(int index) {
        if (index == 0) {
            return sentinel.next;
        }
        return getRecursiveNode(index - 1).next;
    }

    public T getRecursive(int index) {
        if (size == 0 || index > size) {
            return null;
        }
        return getRecursiveNode(index).item;
    }
    
}