package v1;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import v1.AStar.AStar8Puzzle;
import v1.AStar.AStarPathFinding;
import v1.node.CountryNode;
import v1.node.Node;
import v1.node.State;

public class Main {
    public static final int dimension = 3;

    public static void main(String[] args) throws Exception {
        // A* Graph For Path Finding
            System.out.println("==========================> A* Path Finding <============================");
            MutableValueGraph<Node, Double> graph =
                    ValueGraphBuilder.undirected().build();
            Node a = new CountryNode("A", 3.9);
            Node b = new CountryNode("B", 4.3);
            Node c = new CountryNode("C", 3.2);
            Node d = new CountryNode("D", 2.5);
            Node e = new CountryNode("E", 2.5);
            Node f = new CountryNode("F", 1.5);
            Node g = new CountryNode("G", 2.8);
            Node h = new CountryNode("H", 0.0);
            Node i = new CountryNode("I", 1.6);

            graph.putEdgeValue(a, c, 2.0);
            graph.putEdgeValue(a, e, 3.0);
            graph.putEdgeValue(b, e, 5.0);
            graph.putEdgeValue(b, i, 15.0);
            graph.putEdgeValue(c, d, 3.0);
            graph.putEdgeValue(c, g, 2.0);
            graph.putEdgeValue(d, e, 1.0);
            graph.putEdgeValue(d, f, 4.0);
            graph.putEdgeValue(e, f, 6.0);
            graph.putEdgeValue(f, h, 7.0);
            graph.putEdgeValue(g, h, 4.0);
            graph.putEdgeValue(h, i, 3.0);

            AStarPathFinding aStarPathFinding = new AStarPathFinding(graph, d, h);
            System.out.println(aStarPathFinding.doPathFinding());

        // A* for 8 puzzle
            System.out.println("==========================> A* 8 Puzzle <============================");
            int[][] data1 = {{1, 8, 2}, {0, 4, 3}, {7, 6, 5}};
            int[][] goalData = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            int x = 1, y = 0;
            AStar8Puzzle aStar8Puzzle = new AStar8Puzzle(new State(data1), new State(goalData), x, y);
            if (aStar8Puzzle.isSolvable()) {
                aStar8Puzzle.solve();
            } else {
                System.out.println("The given initial is impossible to solve");
            }


    }



}

