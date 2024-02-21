package creatures;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import huglife.Action;
import huglife.Direction;
import huglife.Empty;
import huglife.Impassible;
import huglife.Occupant;

public class TestClorus {

    @Test
    public void testChoose() {
        Clorus c = new Clorus(1.5);
        HashMap<Direction, Occupant> surrounded1 = new HashMap<>();
        surrounded1.put(Direction.TOP, new Impassible());
        surrounded1.put(Direction.BOTTOM, new Impassible());
        surrounded1.put(Direction.LEFT, new Impassible());
        surrounded1.put(Direction.RIGHT, new Impassible());
        
        Action actual1 = c.chooseAction(surrounded1);
        Action expected1 = new Action(Action.ActionType.STAY);
        assertEquals(expected1, actual1);

        HashMap<Direction, Occupant> surrounded2 = new HashMap<>();
        surrounded2.put(Direction.TOP, new Empty());
        surrounded2.put(Direction.BOTTOM, new Empty());
        surrounded2.put(Direction.LEFT, new Plip());
        surrounded2.put(Direction.RIGHT, new Plip());

        Action actual2 = c.chooseAction(surrounded2);
        assertEquals(Action.ActionType.ATTACK, actual2.type);

        HashMap<Direction, Occupant> surrounded3 = new HashMap<>();
        surrounded3.put(Direction.TOP, new Empty());
        surrounded3.put(Direction.BOTTOM, new Empty());
        surrounded3.put(Direction.LEFT, new Empty());
        surrounded3.put(Direction.RIGHT, new Empty());

        Action actual3 = c.chooseAction(surrounded3);
        assertEquals(Action.ActionType.REPLICATE, actual3.type);
    }
}
