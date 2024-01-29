package hw4.puzzle;

import java.util.ArrayList;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {
    private ArrayList<WorldState> bestWay;
    private MinPQ<SearchNode> minPq;
    
    public Solver(WorldState initial) {
        ArrayList<SearchNode> solutionList = new ArrayList<>();
        minPq = new MinPQ<>(SearchNode.getComparator());

        SearchNode sNode = new SearchNode(initial, 0, null);
        minPq.insert(sNode);

        while (!minPq.isEmpty()) {
            SearchNode delNode = minPq.delMin();
            solutionList.add(delNode);
            if (delNode.ws.isGoal()) {
                break;
            }
            for (WorldState i : delNode.ws.neighbors()) {
                if (delNode.prev != null && i.equals(delNode.prev.ws)) {
                    continue;
                }
                SearchNode enNode = new SearchNode(i, delNode.moves + 1, delNode);
                minPq.insert(enNode);
            }
        }

        bestWay = new ArrayList<>();
        SearchNode goalNode = solutionList.get(solutionList.size() - 1);
        bestWay.add(goalNode.ws);
        while (goalNode.prev != null) {
            SearchNode prevNode = goalNode.prev;
            bestWay.add(0, prevNode.ws);
            goalNode = prevNode;
        }
        
    }
    
    public int moves() {
        return bestWay.size() - 1;
    }
    
    public Iterable<WorldState> solution() {
        return bestWay;
    }

}
