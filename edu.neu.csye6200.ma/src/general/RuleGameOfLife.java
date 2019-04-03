package general;

public class RuleGameOfLife implements Rule {
    private Frame preGen; // For future uses

    public RuleGameOfLife(Frame preGen) {
        this.preGen = preGen;
    }

    @Override
    public Frame applyRule(int ruleNum) {
        Frame newGen = new Frame(preGen);
        switch (ruleNum) {
            case 0:
                // Loop every cell in previous generation
                for (Cell[] row : preGen.frame) {
                    for (Cell cell : row) {
                        newGen.frame[cell.rowCord][cell.colCord] = rule(cell);
                    }
                }
                break;
            case 1:
                // Loop every cell in previous generation
                for (Cell[] row : preGen.frame) {
                    for (Cell cell : row) {
                        newGen.frame[cell.rowCord][cell.colCord] = rule1(cell);
                    }
                }
                break;
            case 2:
                // Loop every cell in previous generation
                for (Cell[] row : preGen.frame) {
                    for (Cell cell : row) {
                        newGen.frame[cell.rowCord][cell.colCord] = rule2(cell);
                    }
                }
                break;

        }
        preGen = newGen;
        return newGen;
    }

    /**
     * At least one rule
     * @param previousCell cell from previous generation
     * @return new generation of this cell
     */
    @Override
    public Cell rule(Cell previousCell) {
        Cell newCell = new Cell(0,previousCell.rowCord,previousCell.colCord);
        int sum = cellSum(previousCell);
        // easier give lives rule for center part to keep the frame running
        if (previousCell.colCord > (2*preGen.getLength()/5) && previousCell.colCord < (3*preGen.getLength()/5)
                && previousCell.rowCord > (2*preGen.getLength()/5) && previousCell.rowCord < (3*preGen.getLength()/5)){
            if (sum == 3 || sum==2) newCell.setStatus(1);
        } else {
            // Other parts follow game of life rules
            if (sum == 3) newCell.setStatus(1);
            else if (sum == 2){
                if (previousCell.getStatus()==1) newCell.setStatus(1);
            }
        }
        return newCell;
    }

    public Cell rule1(Cell previousCell) {
        Cell newCell = new Cell(0,previousCell.rowCord,previousCell.colCord);
        int sum = cellSum(previousCell);
        // easier give lives rule for center part to keep the frame running
        if (sum == 3) newCell.setStatus(1);
        else if (sum == 2){
            if (previousCell.getStatus()==1 && (previousCell.colCord%3 != 0 || previousCell.rowCord%3 !=0)) newCell.setStatus(1);
        }
        return newCell;
    }

    public Cell rule2(Cell previousCell) {
        Cell newCell = new Cell(0,previousCell.rowCord,previousCell.colCord);
        int sum = cellSum(previousCell);

        if (sum == 2) newCell.setStatus(1);
        else if (sum==3 && totalActive(preGen) <=  (preGen.getLength()*preGen.getLength()/20) ) newCell.setStatus(1);
        return newCell;
    }

    /**
     *
     * @param previousCell cell from previous generation
     * @return Total active cell among the surrounding 8 cells
     */
    private int cellSum(Cell previousCell) {
        // Calculate number active cells around current cell
        int sum = preGen.up(preGen.left(previousCell)).getStatus() + preGen.up(previousCell).getStatus()+preGen.up(preGen.right(previousCell)).getStatus()
                + preGen.left(previousCell).getStatus()+                                                +preGen.right(previousCell).getStatus()
                + preGen.down(preGen.left(previousCell)).getStatus()+preGen.down(previousCell).getStatus()+preGen.down(preGen.right(previousCell)).getStatus();
        return sum;
    }

    /**
     *
     * @param preGen previous frame
     * @return  total active cells from previous fram
     */
    private int totalActive(Frame preGen) {
        int sum = 0;
        int [][] grid = preGen.getGridInt();
        for (int[] row : grid) {
            for (int i : row) {
                sum += i;
            }
        }
        return sum;
    }
}
