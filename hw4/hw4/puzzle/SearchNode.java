package hw4.puzzle;

import java.util.Comparator;

public class SearchNode {
    protected WorldState ws;
    protected int moves;
    protected SearchNode prev;
    protected int eDTG;

    public SearchNode(WorldState ws, int moves, SearchNode prev) {
        this.ws = ws;
        this.moves = moves;
        this.prev = prev;
        this.eDTG = ws.estimatedDistanceToGoal();
    }

    private static class NodeComparator implements Comparator<SearchNode> {
        public int compare(SearchNode node1, SearchNode node2) {
            int totalMoves1 = node1.eDTG + node1.moves;
            int totalMoves2 = node2.eDTG + node2.moves;
            return totalMoves1 - totalMoves2;
        }   
    }
    
    public static Comparator<SearchNode> getComparator() {
        return new NodeComparator();
    }

    
}
