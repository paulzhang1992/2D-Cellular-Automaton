public class SquareCell implements Cell {
    int status = 0;
    int[] gridCoord = new int[2];
    int gridL = 10;
    int gridH = 10;
    GridCell gc = GridCell.getInstance();


    @Override
    public SquareCell up() {
        int [] up= new int[2];
        up[0] = (gridCoord [0] == 0) ? gridH-1 : gridCoord[0]-1;
        up[1] = gridCoord[1];
        return gc.getCell(up);
    }

    @Override
    public SquareCell down() {
        int [] down = new int[2];
        down[0] = (gridCoord [0] == gridH-1) ? 0 : gridCoord[0]+1;
        down[1] = gridCoord[1];
        return gc.getCell(down);
    }

    @Override
    public SquareCell right() {
        int [] right = new int[2];
        right[1] = (gridCoord [1] == gridL-1) ? 0 : gridCoord[1]+1;
        right[0] = gridCoord[0];
        return gc.getCell(right);
    }

    @Override
    public SquareCell left() {
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
