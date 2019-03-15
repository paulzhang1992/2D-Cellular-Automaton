public class Test {


    public static void main(String[] args) {
        GridCell gc = GridCell.getInstance();
        //RuleAdjacent ra = new RuleAdjacent();
        int [] entryPoint = {0,0};
        //ra.applyRule(gc.getCell(entryPoint));
        SquareCell sc = gc.getCell(entryPoint);
        System.out.println(sc.left().up().gridCoord);
    }
}
