import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    
    @Test
    public void test1() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        String msg = "";
        
        for (int i = 0; i < 100; i++) {
            double randomNum1 = StdRandom.uniform();
 
            if (randomNum1 < 0.25 && randomNum1 >= 0) {
                sad.addFirst(i);
                ads.addFirst(i);
                msg = msg + "addFirst(" + i + ")\n";
            } else if (randomNum1 >= 0.25 && randomNum1 < 0.5) {
                sad.addLast(i);
                ads.addLast(i);
                msg = msg + "addLast(" + i + ")\n";
            } else if (randomNum1 >= 0.5 && randomNum1 < 0.75) {
                Integer actual = sad.removeFirst();
                Integer expected = ads.removeFirst();
                msg = msg + "removeFirst()\n";
                assertEquals(msg + "removeFirst()", expected, actual);
            } else if (randomNum1 >= 0.75 && randomNum1 < 1) {
                Integer actual = sad.removeLast();
                Integer expected = ads.removeLast();
                msg = msg + "removeLast()\n";
                assertEquals(msg + "removeLast()", expected, actual);
            }

        }
    }

}
