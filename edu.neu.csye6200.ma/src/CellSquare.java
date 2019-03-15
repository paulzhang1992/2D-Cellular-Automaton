public class CellSquare implements Cell {
    private int status;
    int[] gridCoord = new int[2];
    int gridL;
    int gridH;


    public CellSquare(int status ,int rowCoord, int columnCoord,int gridH,int gridL) {
        this.status = status;
        this.gridCoord[0] = rowCoord;
        this.gridCoord[1] = columnCoord;
        this.gridH = gridH;
        this.gridL = gridL;
    }

    @Override
    public CellSquare up() {
        GridSquareCell gc = GridSquareCell.getInstance();
        int [] up= new int[2];
        up[0] = (gridCoord [0] == 0) ? gridH-1 : gridCoord[0]-1;
        up[1] = gridCoord[1];
        return gc.getCell(up);
    }

    @Override
    public CellSquare down() {
        GridSquareCell gc = GridSquareCell.getInstance();
        int [] down = new int[2];
        down[0] = (gridCoord [0] == gridH-1) ? 0 : gridCoord[0]+1;
        down[1] = gridCoord[1];
        return gc.getCell(down);
    }

    @Override
    public CellSquare right() {
        GridSquareCell gc = GridSquareCell.getInstance();
        int [] right = new int[2];
        right[1] = (gridCoord [1] == gridL-1) ? 0 : gridCoord[1]+1;
        right[0] = gridCoord[0];
        return gc.getCell(right);
    }

    @Override
    public CellSquare left() {
        GridSquareCell gc = GridSquareCell.getInstance();
        int [] left = new int[2];
        left[1] = (gridCoord [1] == 0) ? gridL-1 : gridCoord[1]-1;
        left[0] = gridCoord[0];
        return gc.getCell(left);
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

}
