package general;

public class Test {

    public static void main(String[] args) {
        Frame startFrame = new Frame(20, 20);

        startFrame.alterCell(0,1,1);
        startFrame.alterCell(0,19,1);
        startFrame.alterCell(2,5,1);
        startFrame.alterCell(2,3,1);
        startFrame.alterCell(7,12,1);
        startFrame.alterCell(9,12,1);
        startFrame.alterCell(7,10,1);

        FrameCollection fc = new FrameCollection(startFrame);
        fc.run();
        Board board = new Board(startFrame);
        for (int i = 0; i < 10000; i++) {
            board.boardUpdate(fc.frameArrayList.get(fc.frameArrayList.size()-1));
            fc.run();
        }

    }
}
