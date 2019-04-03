package ui;

import general.*;
import general.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class MyApp extends AppFrame {

    private static Logger log = Logger.getLogger(MyApp.class.getName());
    protected MACanvas canvas = null;       // Canvas
    protected JPanel northPanel = null;

    // Buttons and text fields
    protected JButton startBtn = null;
    protected JButton pauseBtn = null;
    protected JButton stopBtn = null;
    protected JComboBox<String> rule;

    protected JLabel dim;
    protected JTextField gridLength;
    protected JLabel itr;
    protected JTextField endItr;

    protected FrameCollection fc;
    private int ruleNum = 0;



    public MyApp() {

        // Default size
        frame.setSize(800, 890);
        frame.setTitle("2D Cellular Automaton");
        frame.add(getNorthPanel(), BorderLayout.NORTH);
        menuMgr.createDefaultActions(); // Set up default menu items

        canvas = (MACanvas)getMainPanel();
        frame.add(canvas, BorderLayout.CENTER);

        showUI(); // Cause the Swing Dispatch thread to display the JFrame

        // Default collection and rule selection
        fc = new FrameCollection(canvas.cellFrame,0);
    }


    public JPanel getNorthPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        // Size selection
        dim = new JLabel("Size");
        northPanel.add(dim);

        // Default size 50*50
        gridLength = new JTextField("50",3);
        gridLength.setToolTipText("Enter the dimension of frame.\n Range: 10~999, Default value 50");
        northPanel.add(gridLength);


        // Iteration selection
        itr = new JLabel("Iteration");
        northPanel.add(itr);

        // Default iterations 2000
        endItr = new JTextField("1000",5);
        endItr.setToolTipText("Enter the iteration of simulation.\n Range: 200~99999, Default value 1000");
        northPanel.add(endItr);

        startBtn = new JButton("Start");
        startBtn.addActionListener(this); // Allow the app to hear about button pushes
        northPanel.add(startBtn);

        pauseBtn = new JButton("Pause/Resume"); // Allow the app to hear about button pushes
        pauseBtn.addActionListener(this);
        northPanel.add(pauseBtn);

        stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
        stopBtn.addActionListener(this);
        northPanel.add(stopBtn);

        // Set start state for buttons
        startBtn.setEnabled(true);
        pauseBtn.setEnabled(false);
        stopBtn.setEnabled(false);

        rule = new JComboBox<String>();
        northPanel.add(rule);
        rule.addItem("Rule 1"); //Default
        rule.addItem("Rule 2");
        rule.addItem("Rule 3");

        // Rule action
        rule.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                if (e.getStateChange() == ItemEvent.SELECTED) {

                    // Change the currentRule according to rule selections
                    switch ((String) (e.getItem())) {
                        case "Rule 1": {
                            ruleNum = 1;
                            System.out.println("Choosing rule 1");
                            break;
                        }

                        case "Rule 2": {
                            ruleNum = 2;
                            System.out.println("Choosing rule 2");
                            break;
                        }

                        case "Rule 3": {
                            ruleNum = 0;
                            System.out.println("Choosing rule 3");
                            break;
                        }
                    }
                    // Pass the rule selection to canvas
                    canvas.setRuleNum(ruleNum);
                }
            }
        });

        return northPanel;
    }


    @Override
    public JPanel getMainPanel() {
        if (canvas == null) return new MACanvas(50);
        else return canvas;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        log.info("We received an ActionEvent " + ae);
        // Start action
        if (ae.getSource() == startBtn) {
            createFrameCollection();
            // Set button to state to prevent unwanted action
            startBtn.setEnabled(false);
            pauseBtn.setEnabled(true);
            stopBtn.setEnabled(true);

            // Start simulation
            startSim(ruleNum);

        }
        // Stop action
        else if (ae.getSource() == stopBtn) {
            startBtn.setEnabled(true);
            pauseBtn.setEnabled(false);
            stopBtn.setEnabled(false);
            fc.setRunStat(false);
            System.out.println("Stop pressed");
        }
        // Pause and resume action
        else if (ae.getSource() == pauseBtn) {

            startBtn.setEnabled(false);
            pauseBtn.setEnabled(true);
            stopBtn.setEnabled(true);
            // Check the current state
            if (fc.isRunStat()) {
                // If running pause
                System.out.println("Paused");
                fc.setRunStat(false);
            } else {
                //if paused, continue simulation
                System.out.println("Resumed");
                fc.setRunStat(true);
                startSim(ruleNum);
            }

        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        log.info("Window opened");
    }



    @Override
    public void windowClosing(WindowEvent e) {
    }



    @Override
    public void windowClosed(WindowEvent e) {
    }



    @Override
    public void windowIconified(WindowEvent e) {
        log.info("Window iconified");
    }



    @Override
    public void windowDeiconified(WindowEvent e) {
        log.info("Window deiconified");
    }



    @Override
    public void windowActivated(WindowEvent e) {
        log.info("Window activated");
    }



    @Override
    public void windowDeactivated(WindowEvent e) {
        log.info("Window deactivated");
        // Pause the current simulation if the window is deactivated
        if (startBtn.isEnabled() && !pauseBtn.isEnabled() && !stopBtn.isEnabled()) fc.setRunStat(false);
        else {
            fc.setRunStat(false);
            startBtn.setEnabled(false);
            pauseBtn.setEnabled(true);
            stopBtn.setEnabled(false);
        }

    }


    /**
     * Simulation start here
     * @param ruleNum
     */
    private void startSim(int ruleNum) {
        // Validating the iteration count
        int maxCount;
        if (endItr.getText().isEmpty()) maxCount = 1000;
//        else if (Integer.valueOf(endItr.getText()) == -1) maxCount = 20;    // For testing
        else if (Integer.valueOf(endItr.getText()) < 200) maxCount = 1000;
        else maxCount = Integer.valueOf(endItr.getText());
        fc.setMaxCount(maxCount);

        // Start simulation
        fc.setRunStat(true);
        fc.addObserver(canvas);
        Thread tr1 = new Thread(fc);
        tr1.start();

    }

    /**
     * Creating new FrameCollection for new rules
     */
    public void createFrameCollection() {
        // Validate the frame dimension input
        int rows;
        if (gridLength.getText().isEmpty()) rows = 50;
        else if (Integer.valueOf(gridLength.getText()) < 10) rows = 50;
        else rows = Integer.valueOf(gridLength.getText());
        // Reset canvas size
        canvas.setMaxRows(rows);
        canvas.setMaxCols(rows);
        // Creating the frame collection with the starting frame and rules
        switch (ruleNum) {
            case 0:
                fc = new FrameCollection(new Frame(rows, rows, 'd'), ruleNum);
                break;
            case 1:
                fc = new FrameCollection(new Frame(rows, rows, 'l'), ruleNum);
                break;
            case 2:
                fc = new FrameCollection(new Frame(rows, rows, 't'), ruleNum);
        }

    }

    /*
    Some messages
     */
    @Override
    public void showHelp() {
        JOptionPane.showMessageDialog(null,
                "This is a cellular automaton simulator made by: Paul Zhang.\n" +
                        "Currently 3 rules are available. All the rules are modified based on \"game of life\".\n"
                ,
                "Intro",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)));
        JOptionPane.showMessageDialog(null,
                        "Rule 1 has a different starting status where has a easier way to generate new active cells\n" +
                        "for certain rows and columns and lower cell counts.\n" +
                        "Rule 2 will generate new active cells if surrounding cells has 2 or 3 alive when total alive cells\n" +
                        "is less than 2% of all cells.\n" +
                         "Rule 3 has a center part with red color that has a easier way to generate new active cells.\n" +
                         "It acting like a life source. The black and white part follows exact rule from game of life.\n"
                ,
                "Rules",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)));
        JOptionPane.showMessageDialog(null,
                        "Try to hover mouse on text entering fields for more information"
                ,
                "Extra",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB)));
    }

    /**
     * Sample Wolf application starting point
     * @param args
     */
    public static void main(String[] args) {
        MyApp app = new MyApp();
        log.info("MyApp started");
    }
}
