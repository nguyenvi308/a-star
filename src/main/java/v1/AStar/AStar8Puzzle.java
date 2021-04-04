package v1.AStar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import v1.heuristic.AStar8PuzzleHeuristic;
import v1.heuristic.HeuristicFunction;
import v1.node.State;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AStar8Puzzle implements AStar {
    private static final int dimension = 3;

    private State initState;
    private State goalState;
    private int x;
    private int y;

    public int calculateCost(State state1, State state2) {
        int count = 0;
        int n = state1.getMatrix().length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (state1.getMatrix()[i][j] != 0 && state1.getMatrix()[i][j] != state2.getMatrix()[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isSafe(int x, int y) {
        return (x >= 0 && x < dimension && y >= 0 && y < dimension);
    }


    public boolean isSolvable() {
        int[][] matrix = this.initState.getMatrix();
        int count = 0;
        List<Integer> array = new ArrayList<Integer>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                array.add(matrix[i][j]);
            }
        }

        Integer[] anotherArray = new Integer[array.size()];
        array.toArray(anotherArray);

        for (int i = 0; i < anotherArray.length - 1; i++) {
            for (int j = i + 1; j < anotherArray.length; j++) {
                if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {
                    count++;
                }
            }
        }

        return count % 2 == 0;
    }

    public void printPath(State root) {
        if (root == null) {
            return;
        }
        printPath(root.parent);
        printMatrix(root.matrix);
        System.out.println();
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public void solve() {
        HeuristicFunction heuristicFunction = new AStar8PuzzleHeuristic();
        int[] row = {1, 0, -1, 0};
        int[] col = {0, -1, 0, 1};
        PriorityQueue<State> pq = new PriorityQueue<State>((a, b) -> (a.cost + a.level) - (b.cost + b.level));
        State root = new State(this.initState.getMatrix(), x, y, x, y, 0, null);
        root.cost = (int) heuristicFunction.getG(root, goalState);
        pq.add(root);

        while (!pq.isEmpty()) {
            State min = pq.poll();
            if (min.cost == 0) {
                printPath(min);
                return;
            }

            for (int i = 0; i < 4; i++) {
                if (isSafe(min.x + row[i], min.y + col[i])) {
                    State child = new State(min.getMatrix(), min.x, min.y, min.x + row[i], min.y + col[i], min.level + 1, min);
                    child.cost = (int) heuristicFunction.getG(child, this.getGoalState());
                    pq.add(child);
                }
            }
        }
    }
}
