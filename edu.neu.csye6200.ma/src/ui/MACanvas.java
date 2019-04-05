package ui;

import general.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;



public class MACanvas extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private Color col = null;
    private int maxRows = 50;
    private int maxCols = 50;
	public Frame cellFrame;
	private FrameCollection fc;
	private int [][] grid;
	private int ruleNum = 1;

	/**
	 * MACanvas constructor
	 * Allowing square setting
	 */
	public MACanvas(int maxRows) {
		cellFrame = new Frame(maxRows, maxRows);
		fc = new FrameCollection(cellFrame,0);
		grid  = cellFrame.getGridInt();
		this.maxCols = maxRows;
		this.maxRows = maxRows;
		col = Color.WHITE;
	}

	/**
	 * The UI thread calls this method when the screen changes, or in response.
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {
		drawMA( (Graphics2D) g); // Our Added-on drawing
    }
	
	/**
	 * Draw the MA graphics panel
	 * @param
	 */
	private void drawMA(Graphics2D g2d) {
		Dimension size = getSize();
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, size.width, size.height);

		int stepSizeY = size.height / grid[0].length;
		int stepSizeX = size.width / grid[0].length;
		// Paint all the rec
		for (int j = 0; j < grid.length; j++) {
		   for (int i = 0; i < grid[0].length; i++) {

		   	// This case is only for rule 1
			   if (ruleNum == 0 && i > (2*maxRows/5) && i < (3*maxRows/5) && j > (2*maxCols/5) && j < (3*maxCols/5)) {
				   if (grid[i][j] == 0) col = new Color(200,200,200);
				   else col = new Color(200,20,40);
			   } else {
				   // Other rules use b/w
				   if (grid[i][j] == 0) col = new Color(200,200,200);
				   else col = new Color(50,50,50);
			   }
			   // start point for each square
			   int startX = i* stepSizeX;
			   int startY = j* stepSizeY;

			   // Draw the grid
			   paintRect(g2d, startX, startY, stepSizeX , stepSizeY , col);
		   }
		}
	}

	/*
	 * A convenience routine to set the color and draw a filled rectangle
	 * @param g2d
	 * @param x the upper top left box start position on the x-Axis
	 * @param y the upper top left box start position on the y-Axis
	 * @param width the width in pixels
	 * @param height the height in pixels
	 * @param color
	 */
	private void paintRect(Graphics2D g2d, int x, int y, int width, int height, Color color) {
		g2d.setColor(color);
		g2d.fillRect(x, y, width, height);
	}

	@Override
	public void update(Observable o, Object arg) {
		// Update the newest frame to show
		cellFrame = ((ArrayList<Frame>) arg).get(((ArrayList<Frame>) arg).size()-1);
		// update the int array
		newGrid();
		this.repaint(); // Request the UI to redraw the JPanel
	}

	// Update the int array
	public void newGrid() {
		grid = cellFrame.getGridInt();
	}

	public int getMaxRows() {
		return maxRows;
	}

	public void setMaxRows(int maxRows) {
		this.maxRows = maxRows;
	}

	public int getMaxCols() {
		return maxCols;
	}

	public void setMaxCols(int maxCols) {
		this.maxCols = maxCols;
	}

	public void setRuleNum(int ruleNum) {
		this.ruleNum = ruleNum;
	}
}
