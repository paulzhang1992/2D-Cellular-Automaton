package general;

public class Cell {
    private int status;
    public int rowCord;
    public int colCord;

    public Cell(int status ,int rowCord, int columnCord) {
        this.status = status;
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
