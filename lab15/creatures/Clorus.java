package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.HugLifeUtils;
import huglife.Occupant;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {

    /** losing energy when moving. */
    private final double ME = 0.03;
    /** losing energy when staying. */
    private final double SE = 0.01;
    /** fraction of energy to remain and bestow upon offspring. */
    private final double RE = 0.5;

    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public Clorus() {
        this(1);
    }

    @Override
    public Color color() {
        return color(34, 0, 231);
    }

    public void move() {
        energy -= ME;
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public Creature replicate() {
        double babyEnergy = energy * RE;
        energy *= RE;
        return new Clorus(babyEnergy);
    }

    public void stay() {
        energy -= SE;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plips.size() > 0) {
            Direction attDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, attDir);
        } else if (energy >= 1.0) {
            Direction repDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, repDir);
        } else {
            Direction movDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.MOVE, movDir);
        }
    }
}
