package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private int targetX;
    private int targetY;
    private MinPQ<SearchNode> AStarPQ;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        this.targetX = targetX;
        this.targetY = targetY;
        AStarPQ = new MinPQ<>();
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int vPosX = maze.toX(v);
        int vPosY = maze.toY(v);
        return Math.abs(vPosX - targetX) + Math.abs(vPosY - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        SearchNode sVertex = new SearchNode(s, distTo[s] + h(s));
        AStarPQ.insert(sVertex);
        
        while (!AStarPQ.isEmpty()) {
            SearchNode delVertex = AStarPQ.delMin();
            marked[delVertex.vertex] = true;
            announce();

            if (delVertex.vertex == t) {
                targetFound = true;
                announce();
                return;
            }

            for (int i : maze.adj(delVertex.vertex)) {
                if (marked[i] == true) {
                    continue;
                }
                distTo[i] = distTo[delVertex.vertex] + 1;
                edgeTo[i] = delVertex.vertex;
                announce();
                SearchNode p = new SearchNode(i, distTo[i] + h(i));
                AStarPQ.insert(p);
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

    private class SearchNode implements Comparable<SearchNode> {
        private int vertex;
        private int distance;

        private SearchNode(int v, int d) {
            vertex = v;
            distance = d;
        }

        @Override
        public int compareTo(SearchNode p) {
            return this.distance - p.distance;
        }
    }
}

