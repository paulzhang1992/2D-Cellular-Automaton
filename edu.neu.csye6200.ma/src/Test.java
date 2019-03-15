public class Test {
    public Test() {
    }

    public static void main(String[] args) {
        GridSquareCell gc = GridSquareCell.getInstance();
        System.out.println(gc);
        RuleAdjacent ra = new RuleAdjacent();
        int [] entryPoint = {0,0};
        ra.applyRule(gc.getCell(entryPoint));



        CellSquare cs =gc.getCell(1,1);
        System.out.println(cs.gridCoord[0]+" "+cs.gridCoord[1]);

    }
}
