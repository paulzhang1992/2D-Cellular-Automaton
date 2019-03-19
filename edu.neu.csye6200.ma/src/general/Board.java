package general;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Board {
    private JButton[][] cellSquares = new JButton[20][20];
    private JPanel board;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));

    Board(Frame frame) {
        initializeGui(frame);

    }
    public void initializeGui(Frame frame) {
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        boardUpdate(frame);
    }
    public void boardUpdate(Frame frame) {
        board = new JPanel(new GridLayout(0, 20));
        board.setBorder(new LineBorder(Color.BLACK));
        gui.add(board);
        Insets buttonMargin = new Insets(0,0,0,0);
        int [][] grid = frame.getGridInt();
        int i =0;
        for (JButton[] row:cellSquares) {
            int j = 0;
            for (JButton button: row) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                ImageIcon icon = new ImageIcon(new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if (grid[i][j] == 0) b.setBackground(Color.WHITE);
                else b.setBackground(Color.BLACK);
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
