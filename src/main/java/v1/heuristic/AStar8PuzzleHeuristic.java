package v1.heuristic;

import v1.node.Node;
import v1.node.State;

public class AStar8PuzzleHeuristic implements  HeuristicFunction {

    @Override
    public double getG(Node node1, Node node2) {
        int count = 0;
        int n = node1.getDataArray2D().length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (node1.getDataArray2D()[i][j] != 0 && node1.getDataArray2D()[i][j] != node2.getDataArray2D()[i][j]) {
                    count++;
                }
            }
        }
        return Double.valueOf(count);
    }
}
