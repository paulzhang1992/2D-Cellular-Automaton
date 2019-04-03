package general;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

public class FrameCollection extends Observable implements Runnable {

    public ArrayList<Frame> frameArrayList = new ArrayList<>();
    private boolean runStat = false;
    private int ruleNum;
    private int counter = 0;
    private int maxCount = 2000;
    private String runningTime;
    private long startTime = System.nanoTime();



    public FrameCollection(Frame startFrame, int ruleNum) {
        frameArrayList.add(startFrame);
        if (ruleNum>=0 && ruleNum<=2)  this.ruleNum = ruleNum;
        else this.ruleNum = 0;
    }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
    }

    @Override
    // Do iteration following the rule
    public void run() {
        // While true
        while (runStat) {
            // Keep maximum of 10 instances of frames
            if (frameArrayList.size() == 10) {
                Frame temp = frameArrayList.get(9);
                frameArrayList = new ArrayList<>();
                frameArrayList.add(temp);
            }

            // Applying the rule to the very last frame in array list
            Frame frame = new RuleGameOfLife(frameArrayList.get(frameArrayList.size()-1)).applyRule(ruleNum);
            frameArrayList.add(frame);

            // Send message to subs
            setChanged();
            // Update the array list
            notifyObservers(frameArrayList);

            // Sleep the thread
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Check for iterations
            counter++;
            if (counter>maxCount){
                runStat = false;
                // Pop up message window
                JOptionPane.showMessageDialog(null,"Simulation completed!\nTotal iterations: "+ --counter,
                        "Complete",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)));
            }
            long endTime   = System.currentTimeMillis();
            NumberFormat formatter = new DecimalFormat("#0.00");
            runningTime = formatter.format((endTime - startTime)/1000d);
        }

        runStat = false;
    }

    public void setRunStat(boolean runStat) {
        this.runStat = runStat;
    }

    public boolean isRunStat() {
        return runStat;
    }

    public int getCounter() {
        return counter;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
