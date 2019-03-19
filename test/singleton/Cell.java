package test.singleton;

public interface Cell {
    int status = 0;
    int[] coord = new int[2];
    int gridL = 10;
    int gridH = 10;

    public Cell up();

    public Cell down();

    public Cell right();

    public Cell left();

    public int getStatus();

    public void setStatus(int status);

}
