import java.util.Arrays;

public class ConnectPattern implements Patten{
    public int[] entry = new int [2];
    public ConnectPattern() {

    }

    // Normally column stays the same, row = row -1
    // top row uses last row as up
    private int[] upCell(int[] entry) {
        int [] upCell = new int[2];
        upCell[0] = (entry [0] == 0) ? SqTen.grid.length-1 : entry[0]-1;
        upCell[1] = entry[1];
        return upCell;
    }
    // Normally row stays the same, column = column +1
    // last column uses first column as right
    private int[] rightCell(int[] entry) {
        int [] rightCell = new int[2];
        rightCell[1] = (entry [1] == SqTen.grid.length-1) ? 0 : entry[1]+1;
        rightCell[0] = entry[0];
        return rightCell;
    }
    // Normally column stays the same, row = row +1
    // bottom row uses first row as down
    private int[] downCell(int[] entry) {
        int [] downCell = new int[2];
        downCell[0] = (entry [0] == SqTen.grid[0].length-1) ? 0 : entry[0]+1;
        downCell[1] = entry[1];
        return downCell;
    }
    // Normally row stays the same, column = column -1
    // first column uses last column as left
    private int[] leftCell(int[] entry) {
        int [] leftCell = new int[2];
        leftCell[1] = (entry [1] == 0) ? SqTen.grid[0].length-1 : entry[1]-1;
        leftCell[0] = entry[0];
        return leftCell;
    }

    @Override
    public int [] getAdjacentStatus(){
        int[] adjacent = new int[4];

        // Get the value at adjacent cells
        SqTen st = SqTen.getInstance();
        // up
        adjacent[0] = st.getCell(upCell(entry));
        //right
        adjacent[1] = st.getCell(rightCell(entry));
        //down
        adjacent[2] = st.getCell(downCell(entry));
        //left
        adjacent[3] = st.getCell(leftCell(entry));

        return adjacent;
    }
    @Override
    /*
    o for 0, x for 1, middle value is decided by adjacent 4 cells
    . is the leaving cell

    |   o . |
    | o 0 o |
    |   o   |

    | . x   |   o   |   o   |   o . |
    | o 1 o | x 1 o | o 1 x | o 1 o |
    |   o   | . o   |   o . |   x   |

    | . x   |   o   |   o   |   x . | . x   |   o   |
    | x 0 o | x 0 x | x 0 o | o 0 x | o 0 o | o 0 x |
    |   o   | . o   |   x . |   o   |   x   | . x   |

    |   o   |   x . | . x   |   x   |
    | x 1 x | o 1 x | x 1 x | x 1 0 |
    |   x . |   x   |   o   | . x   |

    |   x   |
    | x 0 x |
    |   x . |
     */
    public void pattern(int [] adjacent) {
        int [] nextEntry = new int [2];
        SqTen st = SqTen.getInstance();
        int sum = Arrays.stream(adjacent).sum();
        if (sum == 0) {
            st.cellAlter(entry, 1);
            entry = rightCell(entry);
        }
        else if ((sum == 4)) {
            st.cellAlter(entry, 1);
            entry = rightCell(entry);
        }
        else if (sum == 1) {

            if (adjacent[0] == 1) {
                st.cellAlter(entry, 0);
                entry = downCell(leftCell(entry));
            }
            else if (adjacent[1] == 1) {
                st.cellAlter(entry, 1);
                entry = downCell(entry);
            }
            else if (adjacent[2] == 1) {
                st.cellAlter(entry, 0);
                entry = downCell(rightCell(entry));
            }
            else {
                st.cellAlter(entry, 1);
                entry = downCell(entry);
            }
        } else if (sum == 2) {
            if (adjacent[0] == 1 && adjacent[3] == 1) {
                st.cellAlter(entry, 1);
                entry = upCell(leftCell(entry));
            }
            else if (adjacent[1] == 1 && adjacent[3] == 0) {
                st.cellAlter(entry, 1);
                entry = downCell(entry);
            }
            else if (adjacent[2] == 1 && adjacent[3] == 1) {
                st.cellAlter(entry, 1);
                entry = downCell(rightCell(entry));
            }
            else if (adjacent[0] == 1 && adjacent[1] == 1) {
                st.cellAlter(entry, 1);
                entry = upCell(entry);
            }
            else if (adjacent[0] == 1 && adjacent[2] == 0) {
                st.cellAlter(entry, 1);
                entry = leftCell(entry);
            }
            else {
                st.cellAlter(entry, 1);
                entry = downCell(leftCell(entry));
            }
        } else {
            if (adjacent[0] == 0) {
                st.cellAlter(entry, 0);
                entry = downCell(rightCell(entry));
            }
            else if (adjacent[1] == 0) {
                st.cellAlter(entry, 1);
                entry = downCell(entry);
            }
            else if (adjacent[2] == 0) {
                st.cellAlter(entry, 1);
                entry = upCell(entry);
            }
            else {
                st.cellAlter(entry, 0);
                entry =upCell(leftCell(entry));
            }
        }

    }
}
