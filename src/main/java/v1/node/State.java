package v1.node;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class State implements Node {
    public State parent;
    public int[][] matrix;

    public State(int[][] matrix) {
        this.matrix = matrix;

    }

    // Blank tile cordinates
    public int x, y;

    // Number of misplaced tiles
    public int cost;

    // The number of moves so far
    public int level;

    public State(int[][] matrix, int x, int y, int newX, int newY, int level, State parent) {
        this.parent = parent;
        this.matrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            this.matrix[i] = matrix[i].clone();
        }

        // Swap value
        this.matrix[x][y]       = this.matrix[x][y] + this.matrix[newX][newY];
        this.matrix[newX][newY] = this.matrix[x][y] - this.matrix[newX][newY];
        this.matrix[x][y]       = this.matrix[x][y] - this.matrix[newX][newY];

        this.cost = Integer.MAX_VALUE;
        this.level = level;
        this.x = newX;
        this.y = newY;
    }

    @Override
    public double getHValue() {
        return 0.0;
    }

    @Override
    public String getDataString() {
        return null;
    }

    @Override
    public int[][] getDataArray2D() {
        return this.getMatrix();
    }


    @Override
    public int compareTo(Node o) {
        return 0;
    }
}
