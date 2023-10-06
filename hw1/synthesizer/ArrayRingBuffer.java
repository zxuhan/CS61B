// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        fillCount = 0;
        first = 0;
        last = 0;
    }


    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount == capacity || capacity == 0) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        if (last == capacity - 1) {
            last = 0;
        } else {
            last += 1;
        }
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (fillCount == 0 || capacity == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T returnItem = rb[first];
        if (first == capacity - 1) {
            first = 0;
        } else {
            first += 1;
        }
        fillCount -= 1;
        return returnItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (fillCount == 0 || capacity == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }   

    // TODO: When you get to part 5, implement the needed code to support iteration.


    private class RingBufferIterator implements Iterator<T> {
        private int pos;

        public RingBufferIterator() {
            pos = first;
        }

        public boolean hasNext() {
            if (pos == last) {
                return false;
            }
            return true;
        }

        public T next() {
            T returnItem = rb[pos];
            if (pos == capacity - 1) {
                pos = 0;
            } else {
                pos += 1;
            }
            return returnItem;
        }
    }
    
    @Override
    public Iterator<T> iterator() {
        return new RingBufferIterator();
    }
}
