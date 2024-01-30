package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] edgeToCopy;
    private boolean isCycle;
    
    public MazeCycles(Maze m) {
        super(m);
        edgeToCopy = new int[maze.V()];
        for (int i = 0; i < maze.V(); i += 1) {
            edgeToCopy[i] = Integer.MAX_VALUE;
        }
        edgeToCopy[0] = 0;
        isCycle = false;
    }

    @Override
    public void solve() {
        // Your code here!
        
        dfs(0);
    }

    // Helper methods go here
    private void dfs(int v) {
        marked[v] = true;

        if (isCycle) {
            return;
        }

        for (int i : maze.adj(v)) {
            if (marked[i] == true && edgeToCopy[v] != i) {
                isCycle = true;
                int prev = edgeToCopy[v];
                edgeTo[v] = prev;
                while (prev != i) {
                    edgeTo[prev] = edgeToCopy[prev];
                    prev = edgeToCopy[prev];
                }
                edgeTo[i] = v;
                announce();
                return;
            }
            if (marked[i] == false) {

                marked[i] = true;
                edgeToCopy[i] = v;
                announce();

                dfs(i);

                if (isCycle) {
                    return;
                }
            }
        }
    }
}

