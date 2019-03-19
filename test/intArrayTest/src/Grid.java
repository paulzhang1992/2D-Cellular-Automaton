package test.intArrayTest.src;

public interface Grid {
    Grid instance = null;
    int defaultValue = 0;
    int width = 0;
    int length = 0;
    int [][] grid = new int[width][length];

    static Grid getInstance(){
        // Create instance here
        return instance;
    }

    public int[][] generator(int defaultValue);

    public int getCell(int row, int column);

    public void showGrid();

    public void cellAlter(int row, int column, int modValue);



}
