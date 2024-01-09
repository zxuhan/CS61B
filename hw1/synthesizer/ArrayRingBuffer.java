// Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

// Make sure to make this class and all of its methods public
// Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first; // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Create new array with capacity elements.
        //      first, last, and fillCount should all be set to 0.
        //      this.capacity should be set appropriately. Note that the local variable
        //      here shadows the field we inherit from AbstractBoundedQueue, so
        //      you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        if (last == capacity - 1) {
            last = 0;
        } else {
            last += 1;
        }
    }

    /**
     * Dequeue the oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // Dequeue the first item. Don't forget to decrease fillCount and update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T firstItem = rb[first];
        fillCount -= 1;
        if (first == capacity - 1) {
            first = 0;
        } else {
            first += 1;
        }
        return firstItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T firstItem = rb[first];
        return firstItem;
    }
    // When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return  new RingBufferIterator();
    }

    private class RingBufferIterator implements Iterator<T> {
        private int iPos;

        public  RingBufferIterator() {
            iPos = first;
        }

        @Override
        public boolean hasNext() {
            return  iPos != last;
        }

        @Override
        public T next() {
            T item = (T) rb[iPos];
            iPos += 1;
            return  item;
        }
    }
}
