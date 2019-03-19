package general;

import java.util.ArrayList;

public class FrameCollection implements Runnable{
    public ArrayList<Frame> frameArrayList = new ArrayList<>();

    public FrameCollection(Frame startFrame) {
        frameArrayList.add(startFrame);
    }

    @Override
    // Do one iteration following the rule
    public void run() {
        // Keep maximum of 10 instances of frames
        if (frameArrayList.size() == 10) {
            Frame temp = frameArrayList.get(9);
            frameArrayList = new ArrayList<>();
            frameArrayList.add(temp);
        }
        // Applying the rule to the very last frame in array list
        Frame frame = new RuleGameOfLife(frameArrayList.get(frameArrayList.size()-1)).applyRule();
        frameArrayList.add(frame);
    }
}
