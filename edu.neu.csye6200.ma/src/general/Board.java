package general;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Board {
    private JButton[][] cellSquares;
    private int height;
    private JPanel board;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    Board(Frame frame, int height) {
        this.height = height;
        cellSquares = new JButton[height][height];
        initializeGui(frame);

    }
    public void initializeGui(Frame frame) {

        gui.setBorder(new EmptyBorder(8,8,8,8));
//        JButton start = new JButton("Start");
//        gui.add(start);
//        JButton pause = new JButton("Pause");
//        gui.add(pause);
//        JButton stop = new JButton("Stop");
//        gui.add(stop);
        board = new JPanel(new GridLayout(0, height));
        gui.add(board,BorderLayout.CENTER);
        boardUpdate(frame);
    }
    public void boardUpdate(Frame frame) {
        gui.remove(board);
        board = new JPanel(new GridLayout(0, height));
        //board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board,BorderLayout.CENTER);
        Insets buttonMargin = new Insets(0,0,0,0);
        int [][] grid = frame.getGridInt();
        int i =0;
        for (JButton[] row:cellSquares) {
            int j = 0;
            for (JButton button: row) {
                JButton b = new JButton();
                Border border = new LineBorder(Color.lightGray, 1);
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(12, 12, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                // Center part of the grip use w/r color option
                if (i > (2*height/5) && i < (3*height/5) && j > (2*height/5) && j < (3*height/5)) {
                    if (grid[i][j] == 0) b.setBackground(Color.WHITE);
                    else b.setBackground(Color.RED);
                } else {
                    // Other part is b/w
                    if (grid[i][j] == 0) b.setBackground(Color.WHITE);
                    else b.setBackground(Color.BLACK);
                }
                // The following tow lines are not necessary for windows and ubuntu when testing
                b.setOpaque(true);
                b.setBorder(border);

                board.add(b);
                j++;
            }
            i++;
        }

    }

    public final JComponent getGui() {
        return gui;
    }

    public JComponent getBoard() {
        return board;
    }
}
