package general;

public class RuleGameOfLife implements Rule {
    Frame preGen;


    public RuleGameOfLife(Frame preGen) {
        this.preGen = preGen;
    }


    @Override
    public Frame applyRule() {
        Frame newGen = new Frame(preGen);
        // Loop every cell in previous generation
        for (Cell[] row : preGen.frame) {
            for (Cell cell : row) {
                newGen.frame[cell.rowCord][cell.colCord] = rule(cell);
            }
        }
        preGen = newGen;
        return newGen;
    }

    @Override
    public Cell rule(Cell previousCell) {
        Cell newCell = new Cell(0,previousCell.rowCord,previousCell.colCord);
        // Calculate number active cells around current cell
        int sum = preGen.up(preGen.left(previousCell)).getStatus() + preGen.up(previousCell).getStatus()+preGen.up(preGen.right(previousCell)).getStatus()
                + preGen.left(previousCell).getStatus()+                                                +preGen.right(previousCell).getStatus()
                + preGen.down(preGen.left(previousCell)).getStatus()+preGen.down(previousCell).getStatus()+preGen.down(preGen.right(previousCell)).getStatus();
        if (sum == 3 || sum == 2) newCell.setStatus(1);
        return newCell;
    }

}
