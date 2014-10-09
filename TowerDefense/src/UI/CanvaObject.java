package UI;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import core.domain.maps.Grid;

public class CanvaObject extends Canvas {

	Grid grid = null;
	Dimension d = new Dimension();
	Graphics imageGraphic = null;

	public CanvaObject(Grid newGrid) {
		grid = newGrid;

	}

	void updateGrid(Grid newGrid) {
		grid = newGrid;

	};

	/*
	 * public void setSize(int i, int j) { // TODO Auto-generated method stub
	 * super.setSize(i, j); }
	 */

	@Override
	public void paint(Graphics graphics) {
		// update( graphics );

		grid.draw(graphics);

	}

	void paintMapOnCanvas(Grid grid) {

	}
	
	
	
	
}