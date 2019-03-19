package test.singleton;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
public class Board {
    private JButton[][] cellSquares = new JButton[20][20];
    private JPanel board;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    Board() {
        initializeGui();

    }
    public final void initializeGui() {
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        boardUpdate();
    }
    public void boardUpdate() {
        board = new JPanel(new GridLayout(0, 20));
        board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board);
        Insets buttonMargin = new Insets(0,0,0,0);
        GridSquareCell gsc = GridSquareCell.getInstance();
        int [][] grid = gsc.getGridInt();
        for (int i = 0; i < cellSquares.length; i++) {
            for (int j = 0; j < cellSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if (grid[i][j] == 0) b.setBackground(Color.WHITE);
                else b.setBackground(Color.BLACK);
                board.add(b);
            }
        }

    }

    public final JComponent getGui() {
        return gui;
    }

    public JComponent getBoard() {
        return board;
    }
}
