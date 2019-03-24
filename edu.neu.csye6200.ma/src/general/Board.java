package general;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        JButton start = new JButton("Start");
        gui.add(start);
        JButton pause = new JButton("Pause");
        gui.add(pause);
        JButton stop = new JButton("Stop");
        gui.add(stop);
        board = new JPanel(new GridLayout(0, height));
        gui.add(board);
        boardUpdate(frame);
    }
    public void boardUpdate(Frame frame) {
        gui.remove(board);
        board = new JPanel(new GridLayout(0, height));
        //board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board);
        Insets buttonMargin = new Insets(0,0,0,0);
        int [][] grid = frame.getGridInt();
        int i =0;
        for (JButton[] row:cellSquares) {
            int j = 0;
            for (JButton button: row) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(6, 6, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                // Center part of the grip use w/r color option
                if (i > 20 && i < 30 && j > 20 && j < 30) {
                    if (grid[i][j] == 0) b.setBackground(Color.WHITE);
                    else b.setBackground(Color.RED);
                } else {
                    // Other part is b/w
                    if (grid[i][j] == 0) b.setBackground(Color.WHITE);
                    else b.setBackground(Color.BLACK);
                }

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
