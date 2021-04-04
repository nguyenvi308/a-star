package v1.heuristic;

import v1.node.Node;
import v1.node.State;

public class AStarPathFindingHeuristic implements HeuristicFunction {


    @Override
    public double getG(Node node1, Node node2) {
        return node1.getHValue();
    }
}
