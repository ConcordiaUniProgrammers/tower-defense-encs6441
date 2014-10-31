package core.domain.maps;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * @author 	Team5
 *
 */
@SuppressWarnings("serial")
public class Grid implements Serializable{

	private int width;
	private int height;
	private int sizeOfUnit = 30;
	private GridCellContentType[][] content;

	/**
	 * This is a dummy constructor 
	 */
	public Grid() {}

	/**
	 * <b>Creates a Grid using width and height and initializes the content as Scenery</b>
	 * @param width width of grid 
	 * @param height height of grid
	 */
	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
	}

	/**
	 * <b>Creates a Grid with width, height, and content type</b>
	 * @param width width of grid
	 * @param height height of grid
	 * @param cellType type of grid
	 */
	public Grid(int width, int height, GridCellContentType cellType) {
		this.width = width;
		this.height = height;
		this.content = new GridCellContentType[width][height];
		initializeCellContents(cellType);
	}

	/**
	 * <b>Constructs a Grid by another grid.</b>
	 * @param grid grid object
	 */
	public Grid(Grid grid) {
		this.width = grid.getWidth();
		this.height = grid.getHeight();
		this.content = grid.getContent();
	}

	/**
	 * <b>initializes grid content to cellType</b>
	 * @param cellType type of cell 
	 */
	private void initializeCellContents(GridCellContentType cellType) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				setCell(x, y, cellType);
			}
		}
	}

	/**
	 * <b>Draws the grid</b>
	 * @param g draw graphics after iteration
	 */
	public void draw(Graphics g) {

		for (int i = 0; i < width * sizeOfUnit; i += sizeOfUnit) {
			for (int j = 0; j < height * sizeOfUnit; j += sizeOfUnit) {
				g.drawLine(i, 0, i, height * sizeOfUnit);
				g.drawLine(0, j, width * sizeOfUnit, j);
			}
		}

		g.drawLine(width * sizeOfUnit - 1, height * sizeOfUnit - 1, width
				* sizeOfUnit - 1, 0); // x1y1 x2y2

		g.drawLine(0, height * sizeOfUnit - 1, width * sizeOfUnit - 1, height
				* sizeOfUnit - 1);

	}

	/**
	 * @return height of map
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return width of map
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return type return content of the grid cell
	 */
	public GridCellContentType[][] getContent() {
		return content;
	}

	/**
	 * @return size of the unit 
	 */
	public int getUnitSize() {
		return sizeOfUnit;
	}

	/**
	 * <b>Sets the size of the grid and resets its content to scenery.</b>
	 * @param width width of the content to set
	 * @param height height of the content to set
	 */
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		content = new GridCellContentType[width][height];
		initializeCellContents(GridCellContentType.SCENERY);
		
	};


	/**
	 * <b>Sets the content of a cell to type</b>
	 * @param x location of cell
	 * @param y location of cell
	 * @param type  type of cell
	 */
	public void setCell(int x, int y, GridCellContentType type) {
		if (x >= 0 && x < width && 
				y >= 0 && y < height){
			content[x][y] = type;
		}
	}

	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return content type
	 */
	public GridCellContentType getCell(int x, int y) {
		if (x >= 0 && x < width && y >= 0
				&& y < height) {
			return content[x][y];
		}
		return null;
	}



}
