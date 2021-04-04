package v1.node;

public class AStarNodeWrapper  implements Comparable<AStarNodeWrapper> {
    private final Node node;
    private AStarNodeWrapper predecessor;
    private double totalCostFromStart;
    private final double minimumRemainingCostToTarget;
    private double costSum;

    public AStarNodeWrapper(Node node, AStarNodeWrapper predecessor,
                            double totalCostFromStart, double minimumRemainingCostToTarget) {
        this.node = node;
        this.predecessor = predecessor;
        this.totalCostFromStart = totalCostFromStart;
        this.minimumRemainingCostToTarget = minimumRemainingCostToTarget;
        calculateCostSum();
    }
    private void calculateCostSum()
    {
        this.costSum = this.totalCostFromStart + this.minimumRemainingCostToTarget;
    }


    public Node getNode() {
        return node;
    }

    public AStarNodeWrapper getPredecessor() {
        return predecessor;
    }


    public double getTotalCostFromStart() {
        return totalCostFromStart;
    }

    public double getMinimumRemainingCostToTarget() {
        return minimumRemainingCostToTarget;
    }

    public double getCostSum() {
        return costSum;
    }

    public void setPredecessor(AStarNodeWrapper predecessor) {
        this.predecessor = predecessor;
    }
    public void setTotalCostFromStart(double totalCostFromStart)
    {
        this.totalCostFromStart = totalCostFromStart;
        calculateCostSum();
    }

    @Override
    public int compareTo(AStarNodeWrapper o) {
        int compare = Double.compare(this.costSum, o.costSum);
        if (compare == 0) {
            compare = node.compareTo(o.node);
        }
        return compare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AStarNodeWrapper that = (AStarNodeWrapper) o;

        return node.equals(that.node);
    }

    @Override
    public int hashCode() {
        return node.hashCode();
    }
}
