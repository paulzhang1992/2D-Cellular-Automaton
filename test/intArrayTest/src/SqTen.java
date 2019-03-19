package test.intArrayTest.src;

import java.util.Arrays;

public class SqTen implements Grid {
    private static SqTen instance = null;
    protected static int [][] grid = new int[20][20];
    private  static int defaultValue = 0;

    private SqTen() {
        grid = generator(defaultValue);
    }

    public static SqTen getInstance() {
        if (instance==null) instance = new SqTen();
        return instance;
    }

    @Override
    public int[][] generator(int defaultValue) {
        for (int [] rows : grid) Arrays.fill(rows, defaultValue);
        return grid;
    }

    @Override
    public int getCell(int row, int column) {
        return grid[row][column];
    }
    public int getCell(int [] cell) {
        return grid[cell[0]][cell[1]];
    }

    @Override
    public void showGrid() {
        char c;
        for (int[] row : grid) {
            for (int cell : row) {
                c = (cell == 0) ? "o".toCharArray()[0] : "x".toCharArray()[0];
                System.out.print(c+" ");
            }
            System.out.print("\n");
        }
    }

    @Override
    public void cellAlter(int row, int column, int modValue) {
        grid[row][column] = modValue;
    }

    public void cellAlter(int [] cell , int modValue) {
        grid[cell[0]][cell[1]] = modValue;
    }


}
