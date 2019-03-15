public class RuleAdjacent implements Rule {
    public RuleAdjacent() {
    }

    @Override
    public void applyRule(Cell cell) {
        System.out.println(cell.gridH);
        System.out.println(cell.gridL);
        System.out.println(cell.up().left().coord);
    }
}
