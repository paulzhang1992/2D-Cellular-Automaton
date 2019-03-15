public class RuleAdjacent implements Rule {
    public RuleAdjacent() {

    }

    public int[] rules5(int ruleSerial) {
        int [] rules = null;
        switch (ruleSerial) {
            case 2:
                rules = new int[]{0,0,0,0};
                break;

            default:
            case 1:
                rules = new int[]{0,0,0,0};
                break;
        }
        return rules;
    }


    @Override
    public void applyRule(CellSquare cell) {
        System.out.println(cell.up().left().gridCoord[0]+" "+cell.up().left().gridCoord[1]);
    }
}
