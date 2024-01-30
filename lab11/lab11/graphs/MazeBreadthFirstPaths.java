package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int v;
    private Queue<Integer> fringe;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        s = maze.xyTo1D(sourceX, sourceY);
        v = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        fringe = new Queue<>();
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        fringe.enqueue(s);
        marked[s] = true;
        
        while (!fringe.isEmpty()) {
            int delVertex = fringe.dequeue();

            if (delVertex == v) {
                return;
            }

            for (int i : maze.adj(delVertex)) {
                if (marked[i] == false) {
                    fringe.enqueue(i);
                    marked[i] = true;
                    edgeTo[i] = delVertex;
                    distTo[i] = distTo[delVertex] + 1;
                    announce();
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

