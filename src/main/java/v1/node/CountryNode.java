package v1.node;

import v1.node.Node;

public class CountryNode implements Node {
    private String countryName;
    private Double hValue;

    public CountryNode(String countryName, Double hValue) {
        this.countryName = countryName;
        this.hValue = hValue;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public void sethValue(Double hValue) {
        this.hValue = hValue;
    }

    @Override
    public double getHValue() {
        return hValue;
    }

    @Override
    public String getDataString() {
        return countryName;
    }

    @Override
    public int[][] getDataArray2D() {
        return new int[0][];
    }


    @Override
    public String toString() {
        return  countryName ;
    }

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}
