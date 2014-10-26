package UI.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import UI.CanvaObject;
import UI.Constants;
import UI.towerdesign.TowerManagerPanel;
import core.applicationService.mapServices.MapManager;
import core.applicationService.mapServices.connectivity.imp.StartEndChecker;
import core.applicationService.warriorServices.TowerFactory;
import core.contract.MapConstants;
import core.domain.maps.Grid;
import core.domain.maps.GridCellContentType;
import core.domain.maps.VisualGrid;
import core.domain.warriors.defenders.towers.Tower;
import core.domain.warriors.defenders.towers.towerType.TowerLevel;

public class MapEditorPanel extends JPanel implements ActionListener,
		MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int height;

	private boolean addTower;

	private Tower tower;
	JButton modernTower;
	// JButton path;
	// JButton ep;
	// JButton exp;

	Color colorToDrawGreed;
	GridCellContentType cellContent;

	Grid grid;

	MapManager mapManager;
	CanvaObject canvas;
	JPanel mapContainer;

	JPanel toolBoxContainer = new JPanel();

	@SuppressWarnings("unused")
	private MapEditorPanel() {
	}

	public MapEditorPanel(int width, int height) {

		initialize(width, height);
		setLayout(new BorderLayout());

		modernTower.setBackground(MapConstants.MODERN_TOWER_COLOR);
		toolBoxContainer.setSize(10, 500);
		toolBoxContainer.setLayout(new FlowLayout());
		toolBoxContainer.add(modernTower);
		// toolBoxContainer.add(path);
		// toolBoxContainer.add(exp);
		// toolBoxContainer.add(ep);

		mapManager = new MapManager();

		modernTower.addActionListener(this);
		// path.addActionListener(this);
		// ep.addActionListener(this);
		// exp.addActionListener(this);
		canvas.addMouseListener(this);
		canvas.addMouseMotionListener(this);

		int mapPixelWidth = grid.getWidth() * grid.getUnitSize();
		int mapPixelHeight = grid.getHeight() * grid.getUnitSize();
		canvas.setSize(mapPixelWidth, mapPixelHeight);
		mapContainer.setPreferredSize(new Dimension(mapPixelWidth,
				mapPixelHeight));
		mapContainer.add(canvas);

		add(new JPanel(), BorderLayout.NORTH);
		add(toolBoxContainer, BorderLayout.EAST);
		add(mapContainer, BorderLayout.CENTER);
		add(new JPanel(), BorderLayout.WEST);
		add(new JPanel(), BorderLayout.SOUTH);
		setVisible(true);

	}

	private void initialize(int width, int height) {
		this.width = width;
		this.height = height;

		this.addTower = false;

		modernTower = new JButton(Constants.MODERN_TOWER);
		// path = new JButton(Constants.PATH);
		// ep = new JButton(Constants.ENTRANCE);
		// exp = new JButton(Constants.EXIT);

		// colorToDrawGreed = Color.green;
		// cellContent = GridCellContentType.PATH;

		grid = new VisualGrid(width, height, GridCellContentType.SCENERY);

		canvas = new CanvaObject(grid);
		mapContainer = new JPanel();

		toolBoxContainer = new JPanel();

	}

	@SuppressWarnings("serial")
	public class CanvasCoordinate extends Point {
		public CanvasCoordinate(int x, int y) {
			super(x, y);
		}
	}

	public CanvasCoordinate toCanvasCoordinates(Point point) {
		Point canvasLocation = canvas.getLocationOnScreen();
		int relativeX = point.x - canvasLocation.x;
		int relativeY = point.y - canvasLocation.y;
		int i = relativeX / grid.getUnitSize();
		int j = relativeY / grid.getUnitSize();
		return new CanvasCoordinate(i, j);
	}

	public void setGridSize(int width, int height) {
		grid.setSize(width, height);
		canvas.updateGrid(grid);
	}

	public void design(int width, int height) {
		try {
			grid.setSize(width, height);
			canvas.updateGrid(grid);

			mapContainer.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	// public void drawPath(Color backgroundColor) {
	// try {
	//
	// colorToDrawGreed = backgroundColor;
	// cellContent = GridCellContentType.PATH;
	// } catch (java.lang.Exception ex) {
	// JOptionPane.showMessageDialog(null, ex.getMessage());
	// }
	// }

	@Override
	public void mouseDragged(MouseEvent event) {
		// draw(event.getX(), event.getY());

	}

	private void draw(int x, int y) {
		int i = x / grid.getUnitSize();
		int j = y / grid.getUnitSize();
		if ((i < grid.getWidth()) && (j < grid.getHeight())
				&& (grid.getCell(i, j) != cellContent)) {
			grid.setCell(i, j, cellContent);
			canvas.repaint();

		}
	}

	@Override
	public void mouseMoved(MouseEvent event) {

	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (addTower) {
			TowerFactory factory = new TowerFactory();
			tower = factory.getTower("ModernTower", TowerLevel.two);
			draw(event.getX(), event.getY());
			addTower = false;
		}
		else {
			if (tower != null){
			TowerManagerPanel towerPanel = new TowerManagerPanel(tower);
			add(towerPanel, BorderLayout.WEST);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent event) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();

		switch (command) {
		case Constants.MODERN_TOWER:
			modernTower();
			break;
		}
	}

	private void modernTower() {
		try {
			addTower = true;
			colorToDrawGreed = modernTower.getBackground();
			cellContent = GridCellContentType.MODERN_TOWER; // black for path
		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	protected void setMapSize(int width, int height) {
		try {
			// validation part
			StartEndChecker checker = new StartEndChecker();
			if (!checker.isCorrectSize(height, width))
				throw new java.lang.Exception(
						"Error size max size: ....., min size: ....");
			// end of validation

			mapContainer.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());
			canvas.setSize(width * grid.getUnitSize(),
					height * grid.getUnitSize());

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	protected void loadMap() {
		try {
			JFileChooser openFile = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == openFile.showOpenDialog(null)) {
				grid = mapManager.LoadMapFromFile(openFile.getSelectedFile()
						.getAbsolutePath());
				canvas.updateGrid(grid);
				width = grid.getWidth();
				height = grid.getHeight();
				mapContainer.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
				canvas.setSize(width * grid.getUnitSize(),
						height * grid.getUnitSize());
			}

		} catch (java.lang.Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}

	// protected void designMap() {
	// try {
	// canvas.updateGrid(grid);
	// } catch (java.lang.Exception ex) {
	// JOptionPane.showMessageDialog(null, ex.getMessage());
	// }
	//
	// }

}