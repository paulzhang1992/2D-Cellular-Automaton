package general;

public class Cell {
    private int status;
    public int rowCord;
    public int colCord;

    // Build new cell with its coordinate on frame and status
    public Cell(int status ,int rowCord, int columnCord) {
        this.status = status; // Now is 0 or 1
        this.rowCord = rowCord;
        this.colCord = columnCord;
    }

    public int getStatus() {
        return status;
    }

    // Set cell status
    public void setStatus(int status) {
        this.status = status;
    }
}
