package test.singleton;

import javax.swing.*;

public class AutomatonTest {
    public AutomatonTest() {
    }

    public static void main(String[] args) throws InterruptedException {
        GridSquareCell gsc = GridSquareCell.getInstance();
        RuleAdjacent ra = new RuleAdjacent();

        gsc.getCell(0,1).setStatus(1);
        gsc.getCell(4,7).setStatus(1);
        gsc.getCell(2,5).setStatus(1);
        gsc.getCell(8,9).setStatus(1);

        Board b = new Board();
        JFrame f = new JFrame("ChessChamp");
        f.add(b.getGui());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //f.setLocationByPlatform(true);


        f.pack();
        f.setMinimumSize(f.getSize());

        for (int i = 0; i < 20000; i++) {
            ra.applyRule(5,999);
            b.boardUpdate();
            f.setVisible(true);
            Thread.sleep(5);
        }


    }
}
