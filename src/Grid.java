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

    public int locator(int x, int y);

    public void showGrid();

    public void gridAlter(int xCord, int yCord, int modValue);


}
