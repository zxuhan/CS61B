import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayDequeGold {
    
    @Test
    public void test1() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        
        for (int i = 0; i < 10; i++) {
            double randomNum1 = StdRandom.uniform();
            String msg = "";

            if (randomNum1 < 0.25) {
                sad.addFirst(i);
                ads.addFirst(i);
                msg = msg + "addFirst(" + i + ")\n";
            } else if (randomNum1 >= 0.25 && randomNum1 < 0.5) {
                sad.addLast(i);
                ads.addLast(i);
                msg = msg + "addLast(" + i + ")\n";
            } else if (randomNum1 >= 0.5 && randomNum1 < 0.75 && !sad.isEmpty()) {
                Integer sadInt = sad.removeFirst();
                Integer adsInt = ads.removeFirst();
                msg = msg + "removeFirst()";
                assertEquals(msg, sadInt, adsInt);
            } else if (randomNum1 >= 0.75 && randomNum1 < 1 && !sad.isEmpty()) {
                Integer sadInt = sad.removeLast();
                Integer adsInt = ads.removeLast();
                msg = msg + "removeLast()";
                assertEquals(msg, sadInt, adsInt);
            }

        }
    }

}
