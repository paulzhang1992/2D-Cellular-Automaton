import java.util.Arrays;

public class SqTen implements Grid {
    private static SqTen instance = null;
    protected static int [][] grid = new int[10][10];
    private  static int defaultValue = 0;

    private SqTen() {
        grid = generator(defaultValue);
    }

    public static SqTen getInstance() {
        if (instance==null) instance = new SqTen();
        else System.out.println("Grid is already created. The length of the square grid is "+grid.length);
        return instance;
    }

    @Override
    public int[][] generator(int defaultValue) {
        for (int [] rows : grid) Arrays.fill(rows, defaultValue);
        return grid;
    }

    @Override
    public int locator(int x, int y) {
        return grid[x][y];
    }

    @Override
    public void showGrid() {
        char c;
        for (int[] row : grid) {
            for (int cell : row) {
                c = (cell == 0) ? "o".toCharArray()[0] : "x".toCharArray()[0];
                System.out.print(c+"");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void gridAlter(int xCord, int yCord, int modValue) {
        grid[xCord][yCord] = modValue;
    }


}
