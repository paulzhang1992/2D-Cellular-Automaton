public class GridSquareCell {
    private static GridSquareCell instance = null;

    private int length; //columns
    private int height;  //rows
    private final int dValue = 0; // default value for cells
    public CellSquare[][] grid;

    private GridSquareCell(int height, int length) {
        this.length = length;
        this.height = height;
        grid = new CellSquare[height][length];
        int rowCoord = 0;
        for (CellSquare[] cellRow : grid) {
            int columnCoord = 0;
            for (CellSquare cell : cellRow) {
                grid[rowCoord][columnCoord] = new CellSquare(dValue, rowCoord, columnCoord,height,length);

                columnCoord++;
            }
            rowCoord++;
        }

    }

    public static GridSquareCell getInstance() {
        if (instance==null) instance = new GridSquareCell(10,10);
        return instance;
    }

    public CellSquare getCell(int [] coord) {
        return grid[coord[0]][coord[1]];
    }
    public CellSquare getCell(int rowCoord, int columnCoord) {
        return grid[rowCoord][columnCoord];
    }

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
