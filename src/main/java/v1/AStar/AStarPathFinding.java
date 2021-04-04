package v1.AStar;

import com.google.common.graph.MutableValueGraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import v1.heuristic.AStarPathFindingHeuristic;
import v1.heuristic.HeuristicFunction;
import v1.node.AStarNodeWrapper;
import v1.node.Node;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AStarPathFinding implements AStar {

    private MutableValueGraph<Node,Double> graph;
    private Node startNode;
    private Node endNode;


    public  List<Node> doPathFinding() throws Exception {

        // Store node to process.Priority Queue - base on sum cost to poll the value
        TreeSet<AStarNodeWrapper> queue = new TreeSet<>();
        // Like A table To Store Node and Its information
        Map<Node, AStarNodeWrapper> nodeWrappers = new HashMap<>();
        // To check whether we have already processed a node.
        Set<Node> visited = new HashSet<>();

        HeuristicFunction defaultHeuristicFunction = new AStarPathFindingHeuristic();
        // Start with the first node
        AStarNodeWrapper nodeWrapper = new AStarNodeWrapper(startNode, null, 0.0, startNode.getHValue());
        nodeWrappers.put(startNode, nodeWrapper);
        // Add to queue
        queue.add(nodeWrapper);

        while (!queue.isEmpty()) {
            AStarNodeWrapper currentAStarNodeWrapper = queue.pollFirst();
            Node currentNode = currentAStarNodeWrapper.getNode();
            visited.add(currentNode);
            if (currentNode.getDataString().equalsIgnoreCase(endNode.getDataString())) {
                return buildPath(currentAStarNodeWrapper);
            }

            Set<Node> neighbors = graph.adjacentNodes(currentNode);

            for (Node neighbor : neighbors) {
                if (visited.contains(neighbor)) {
                    continue;
                }
                // Calculate total cost from start to neighbor via current Node
                double cost = graph.edgeValue(currentNode,neighbor).orElseThrow(IllegalStateException::new);

                double totalCostFromStart = currentAStarNodeWrapper.getTotalCostFromStart() + cost;

                AStarNodeWrapper neighborWrapper = nodeWrappers.get(neighbor);

                if (neighborWrapper == null) {
                    neighborWrapper = new AStarNodeWrapper(neighbor, currentAStarNodeWrapper, totalCostFromStart, defaultHeuristicFunction.getG(neighbor,null));
                    nodeWrappers.put(neighbor, neighborWrapper);
                    queue.add(neighborWrapper);

                } else if (totalCostFromStart < neighborWrapper.getTotalCostFromStart()) {
                    queue.remove(neighborWrapper);
                    neighborWrapper.setTotalCostFromStart(totalCostFromStart);
                    neighborWrapper.setPredecessor(currentAStarNodeWrapper);
                    queue.add(neighborWrapper);
                }

            }

        }
        return null;

    }

    private  List<Node> buildPath(AStarNodeWrapper nodeWrapper) {
        List<Node> path = new ArrayList<>();
        while (nodeWrapper != null) {
            path.add(nodeWrapper.getNode());
            nodeWrapper = nodeWrapper.getPredecessor();
        }
        Collections.reverse(path);
        return path;
    }
}
