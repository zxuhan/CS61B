package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        arb.enqueue(10);
        arb.enqueue(20);
        arb.enqueue(30);
        int actural1 = arb.peek();
        int expected1 = 10;
        assertEquals(expected1, actural1);

        int actural2 = arb.dequeue();
        int expected2 = 10;
        assertEquals(expected2, actural2);

        int actural3 = arb.peek();
        int expected3 = 20;
        assertEquals(expected3, actural3);

        arb.enqueue(40);
        arb.enqueue(40);
        arb.enqueue(40);
        arb.enqueue(40);
        arb.enqueue(40);
        arb.enqueue(40);
        arb.enqueue(40);
        arb.enqueue(40);

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
