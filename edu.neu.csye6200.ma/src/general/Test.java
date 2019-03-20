package general;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {
        // initiate the first generation
        Frame startFrame = new Frame(50, 50);

        // Starting points modifications
        startFrame.alterCell(0,1,1);
        startFrame.alterCell(19,1,1);
        startFrame.alterCell(0,2,1);
        startFrame.alterCell(1,19,1);
        startFrame.alterCell(22,24,1);
        startFrame.alterCell(22,26,1);
        startFrame.alterCell(24,25,1);
        startFrame.alterCell(24,27,1);
        startFrame.alterCell(7,12,1);
        startFrame.alterCell(9,12,1);
        startFrame.alterCell(7,10,1);

        // Set first iteration visible
        FrameCollection fc = new FrameCollection(startFrame);
        fc.run();
        Board b = new Board(startFrame,50);
        JFrame f = new JFrame("Cellular-Automaton");
        f.add(b.getGui());
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setVisible(true);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // keep running
        for (int i = 0; i < 10000; i++) {
            Frame frame = fc.frameArrayList.get(fc.frameArrayList.size()-1);
            b.boardUpdate(frame);
            f.setVisible(true);
            fc.run();
        }

    }
}
