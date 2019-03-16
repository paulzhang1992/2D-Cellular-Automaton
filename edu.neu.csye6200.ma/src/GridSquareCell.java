public class GridSquareCell {
    private static GridSquareCell instance = null;

    private int length; //columns
    private int height;  //rows
    private final int dValue = 0; // default value for cells
    public CellSquare[][] grid;
    public static CellSquare activeCell;


    private GridSquareCell(int height, int length) {
        // Grid initialization
        this.length = length;
        this.height = height;
        grid = new CellSquare[height][length];
        int rowCoord = 0;
        // Adding cells to each spot on the grid
        // Looping the rows and then looping cells on each row.
        for (CellSquare[] cellRow : grid) {
            int columnCoord = 0;
            for (CellSquare cell : cellRow) {
                //Adding info including default status, coordinates and grid size
                grid[rowCoord][columnCoord] = new CellSquare(dValue, rowCoord, columnCoord,height,length);
                columnCoord++;
            }
            rowCoord++;
        }
        //Default Starting point
        setActiveCell(0,0);
    }

    // Singleton design. Only one square grid is allowed.
    public static GridSquareCell getInstance() {
        //Only allowing default value. Custom set will be added in the future.
        if (instance==null) instance = new GridSquareCell(20,20);
        return instance;
    }

    //Getting cell with x,y coordinate as two integers or array {x,y}
    public CellSquare getCell(int [] coord) {
        return grid[coord[0]][coord[1]];
    }

    public CellSquare getCell(int rowCoord, int columnCoord) {
        return grid[rowCoord][columnCoord];
    }

    public static CellSquare getActiveCell() {
        return activeCell;
    }

    public  void setActiveCell(int rowCoord, int columnCoord) {
        this.activeCell = getCell(rowCoord,columnCoord);
    }

    // Get surround cells based on active rules
    public CellSquare[] getNeighbor(CellSquare cell, int count) {
            CellSquare[] neighbor;
            switch (count) {

                /*
                                0 1 2
                    Case 9      3 4 5
                                6 7 8
                 */
                case 9:{
                    neighbor = new CellSquare[count];
                    neighbor[0] = cell.up().left();
                    neighbor[1] = cell.up();
                    neighbor[2] = cell.up().right();
                    neighbor[3] = cell.left();
                    neighbor[4] = cell;
                    neighbor[5] = cell.right();
                    neighbor[6] = cell.down().left();
                    neighbor[7] = cell.down();
                    neighbor[8] = cell.down().right();
                    break;
                }
                /*
                                          0
                                       1  2  3
                     case 13        4  5  6  7  8
                                       9 10 11
                                         12
                 */
                case 13:{
                    // To-Do
                }
                /*
                                    0  1  2  3  4
                                    5  6  7  8  9
                     case 25       10 11 12 13 14
                                   15 16 17 18 19
                                   20 21 22 23 24
                 */
                case 25:{
                    // To-Do
                }
                default:
                /*
                            `       0
                    case 5        1 2 3
                                    4
                 */
                case 5:{
                    neighbor = new CellSquare[count];
                    neighbor[0] = cell.up();
                    neighbor[1] = cell.down();
                    neighbor[2] = cell;
                    neighbor[3] = cell.right();
                    neighbor[4] = cell.left();
                    break;
                }
            }
            return neighbor;
    }

    // Converting cell with cell's status(integer). For testing purpose.
    // More converting will be added later
    public int[][] getGridInt (){
        int[][] intGrid = new int[this.height][this.length];
        int height = 0;
        for (CellSquare[] row : grid) {
            int[] intRow = new int[this.length];
            int length = 0;
            for (CellSquare cell : row) {
                intRow [length] = cell.getStatus();
                length++;
            }
            intGrid[height]=intRow;
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
