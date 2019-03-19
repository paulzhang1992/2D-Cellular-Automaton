package general;

import java.util.ArrayList;

public class FrameCollection implements Runnable{
    public ArrayList<Frame> frameArrayList = new ArrayList<>(10);

    public FrameCollection(Frame startFrame) {
        frameArrayList.add(startFrame);
    }

    @Override
    public void run() {
        frameArrayList.add(new RuleGameOfLife(frameArrayList.get(frameArrayList.size()-1)).applyRule());
    }
}
