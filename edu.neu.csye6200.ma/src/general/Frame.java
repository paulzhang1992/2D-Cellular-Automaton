package general;

public class Frame {

    private int length; //columns
    private int height;  //rows
    private final int dValue = 0; // default value for cells
    public Cell[][] frame;


    public Frame(int height, int length) {
        // Grid initialization
        this.length = length;
        this.height = height;
        frame = new Cell[height][length];
        int rowCord = 0;
        // Adding cells to each spot on the frame
        // Looping the rows and then looping cells on each row.
        for (Cell[] cellRow : frame) {
            int columnCord = 0;
            for (Cell cell : cellRow) {
                //Adding info including default status, Coordinates and frame size
                frame[rowCord][columnCord] = new Cell(dValue, rowCord, columnCord);
                columnCord++;
            }
            rowCord++;
        }
    }

    public Frame(Frame previousFrame) {
        frame = new Cell[previousFrame.height][previousFrame.length];
    }

    // Getting adjacent cells with Cordinates
    public Cell up(int rowCord, int columnCord) {
        //rowCord = (rowCord == 0) ? height-1 : rowCord-1;
        return getCell((rowCord == 0) ? height-1 : rowCord-1, columnCord);
    }

    public Cell down(int rowCord, int columnCord) {
        //rowCord = (rowCord == height-1) ? 0 : rowCord+1;
        return getCell((rowCord == height-1) ? 0 : rowCord+1, columnCord);
    }

    public Cell left(int rowCord, int columnCord) {
        //columnCord = (columnCord == 0) ? length-1 : columnCord-1;
        return getCell(rowCord, (columnCord == 0) ? length-1 : columnCord-1);
    }

    public Cell right(int rowCord, int columnCord) {
        //columnCord = (columnCord == length-1) ? 0 : columnCord+1;
        return getCell(rowCord, (columnCord == length-1) ? 0 : columnCord+1);
    }

    // Getting adjacent cells with cell
    public Cell up(Cell cell) {
        //rowCord = (rowCord == 0) ? height-1 : rowCord-1;
        return getCell((cell.rowCord == 0) ? height-1 : cell.rowCord-1, cell.colCord);
    }

    public Cell down(Cell cell) {
        //rowCord = (rowCord == height-1) ? 0 : rowCord+1;
        return getCell((cell.rowCord == height-1) ? 0 : cell.rowCord+1, cell.colCord);
    }

    public Cell left(Cell cell) {
        //columnCord = (columnCord == 0) ? length-1 : columnCord-1;
        return getCell(cell.rowCord, (cell.colCord == 0) ? length-1 : cell.colCord-1);
    }

    public Cell right(Cell cell) {
        //columnCord = (columnCord == length-1) ? 0 : columnCord+1;
        return getCell(cell.rowCord, (cell.colCord == length-1) ? 0 : cell.colCord+1);
    }

    // Getting a cell on frame
    public Cell getCell(int rowCord, int columnCord) {
        return frame[rowCord][columnCord];
    }

    public void alterCell(int rowCord, int colCord, int status) {
        frame[rowCord][colCord].setStatus(status);
    }


    // Converting cell with cell's status(integer). For testing purpose.
    // More converting will be added later
    public int[][] getGridInt (){
        int[][] intGrid = new int[this.height][this.length];
        int height = 0;
        for (Cell[] row : frame) {
            int length = 0;
            for (Cell cell : row) {
                intGrid[height][length] = cell.getStatus();
                length++;
            }
            height++;
        }
        return intGrid;
    }

    // Visualization method for testing the rules
    public void showGridXO() {
        int[][] intGrid = getGridInt();
        String cellString;
        for (int[] row : intGrid) {
            for (int cell : row) {
                cellString = (cell == 0) ? "o": "x";
                System.out.print(cellString+" ");
            }
            System.out.print("\n");
        }
    }
}
