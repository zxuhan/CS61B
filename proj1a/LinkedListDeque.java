public class LinkedListDeque<Type> {
    public class ListNode {
        private Type item;
        private ListNode prev;
        private ListNode next;

        public ListNode(ListNode prev, Type item, ListNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
    
    private ListNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new ListNode(sentinel, null, sentinel);
        size = 0;
    }

    private void addFirstItem(Type t) {
        sentinel = new ListNode(sentinel, null, sentinel);
        sentinel.next = new ListNode(sentinel, t, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(Type t) {
        if (size == 0) {
            addFirstItem(t);
            return;
        }
        sentinel.next = new ListNode(sentinel, t, sentinel.next);
        size += 1;
    }

    public void addLast(Type t) {
        sentinel.prev.next = new ListNode(sentinel.prev, t, sentinel);
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
    
    public Type removeFirst() {
        if (size == 0) {
            return null;
        }
        ListNode temp = sentinel.next.next;
        Type item = sentinel.next.item;
        temp.prev = sentinel;
        sentinel.next = temp;
        size -= 1;
        return item;
    }
    
    public Type removeLast() {
        if (size == 0){
            return null;
        }
        ListNode temp = sentinel.prev.prev;
        Type item = sentinel.prev.item;
        temp.next = sentinel;
        sentinel.prev = temp;
        size -= 1;
        return item;
    }
    
    public Type get(int index) {
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

    public Type getRecursive(int index) {
        return getRecursiveNode(index).item;
    }


    

        
}