package v1.node;

public interface Node extends Comparable<Node> {

    double getHValue();

    String getDataString();
    int[][] getDataArray2D();

}