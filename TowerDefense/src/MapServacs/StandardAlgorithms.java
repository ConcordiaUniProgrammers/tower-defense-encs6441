package MapServacs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import maps.EmptyGrid;
import maps.Grid;

public class StandardAlgorithms {

	// mapInMatric[i][j] => 0 -> nothing , 1-> part of a path, 2-> part of
	// sineri, 3-> entry point, 4-> exit point

	int sizeX = 0; // width and height in number of cells
	int sizeY = 0;
	String source = "";
	String destination = "";
	public Stack<GraphNode> path = new Stack<GraphNode>();

	HashMap<String, GraphNode> graphMap = new HashMap<String, GraphNode>(); // contains
																			// the
																			// map
																			// structure

	int[][] map = new int[sizeX][sizeY];//

	public StandardAlgorithms() {
		map = new int[sizeX][sizeY];
	}; // default constructor

	public StandardAlgorithms(int width, int height, int[][] map) {
		sizeX = height;
		sizeY = width;
		this.map = new int[sizeX][sizeY];
		this.map = map;
	};

	public StandardAlgorithms(Grid grid) {
		sizeX = grid.getHeight();
		sizeY = grid.getWidth();
		this.map = new int[sizeX][sizeY];
		this.map = grid.getContent();
	};

	
	
	
	
	public String fillGraph() { 

		String message = "";
		int numberOfEntryPoints = 0;
		int numberOfExitPoints = 0;
		for (int i = 0; i <= sizeX - 1; i++) {
			for (int j = 0; j <= sizeY - 1; j++) {

				if (map[i][j] == 3) {
					if(numberOfEntryPoints != 0) {
						message = "Invalid map: Mor than one entry point";
						break;
						
					}
					if (j != 0) {
						message = "Invalid map: Invalid Entry point";
						break;
					} else {
						numberOfEntryPoints++;
						source = Integer.toString(i) + " "
								+ Integer.toString(j);
					}
				} else if (map[i][j] == 4) {
					if(numberOfExitPoints != 0) {
						message = "Invalid map: Mor than one exit point";
						break;
						
					}
					if (j != sizeY - 1) {

						message = "Invalid map: Invalid Exit point";
						break;
					} else {
						numberOfExitPoints++;
						destination = Integer.toString(i) + " "
								+ Integer.toString(j);

					}
				}

				String curentNode = Integer.toString(i) + " "
						+ Integer.toString(j);
				if (graphMap.get(curentNode) == null)
					graphMap.put(curentNode, new GraphNode(curentNode, i, j));

				GraphNode universalGrafNode;
				if (i - 1 > 0) {
					if ((map[i][j] == 1 || map[i][j] == 3 || map[i][j] == 4)
							&& (map[i - 1][j] == 1 || map[i - 1][j] == 3 || map[i - 1][j] == 4)) {
						if ((universalGrafNode = graphMap.get(Integer
								.toString(i - 1) + " " + Integer.toString(j))) != null) {
							graphMap.get(curentNode).neighbors
									.add(universalGrafNode);

						} else {

							universalGrafNode = new GraphNode(
									Integer.toString(i - 1) + " "
											+ Integer.toString(j), i - 1, j);
							graphMap.put(universalGrafNode.name,
									universalGrafNode);
							graphMap.get(curentNode).neighbors
									.add(universalGrafNode);
						}
					}
				}

				if (j - 1 > 0) {
					if ((map[i][j] == 1 || map[i][j] == 3 || map[i][j] == 4)
							&& (map[i][j - 1] == 1 || map[i][j - 1] == 3 || map[i][j - 1] == 4)) {
						if ((universalGrafNode = graphMap.get(Integer
								.toString(i) + " " + Integer.toString(j - 1))) != null) {
							graphMap.get(curentNode).neighbors
									.add(universalGrafNode);
						} else {
							universalGrafNode = new GraphNode(
									Integer.toString(i) + " "
											+ Integer.toString(j - 1), i, j - 1);
							graphMap.put(universalGrafNode.name,
									universalGrafNode);
							graphMap.get(curentNode).neighbors
									.add(universalGrafNode);
						}

					}
				}

				if (i + 1 < sizeX) {
					if ((map[i][j] == 1 || map[i][j] == 3 || map[i][j] == 4)
							&& (map[i + 1][j] == 1 || map[i + 1][j] == 3 || map[i + 1][j] == 4)) {
						if ((universalGrafNode = graphMap.get(Integer
								.toString(i + 1) + " " + Integer.toString(j))) != null) {
							graphMap.get(curentNode).neighbors
									.add(universalGrafNode);

						} else {

							universalGrafNode = new GraphNode(
									Integer.toString(i + 1) + " "
											+ Integer.toString(j), i + 1, j);
							graphMap.put(universalGrafNode.name,
									universalGrafNode);
							graphMap.get(curentNode).neighbors
									.add(universalGrafNode);
						}
					}

				}

				if (j + 1 < sizeY) {
					if ((map[i][j] == 1 || map[i][j] == 3 || map[i][j] == 4)
							&& (map[i][j + 1] == 1 || map[i][j + 1] == 3 || map[i][j + 1] == 4)) {

						if ((universalGrafNode = graphMap.get(Integer
								.toString(i) + " " + Integer.toString(j + 1))) != null) {
							graphMap.get(curentNode).neighbors
									.add(universalGrafNode);

						} else {

							universalGrafNode = new GraphNode(
									Integer.toString(i) + " "
											+ Integer.toString(j + 1), i, j + 1);
							graphMap.put(universalGrafNode.name,
									universalGrafNode);
							graphMap.get(curentNode).neighbors
									.add(universalGrafNode);
						}

					}
				}

			}
			

			
		}

		return message;

	}

	String validateEntryExit() {
		String message = "";
		int numberOfEntry = 0;
		int numberOfExit = 0;
		int numberofEmptyCells = 0;
		int numberOfTowers = 0;

		for (int i = 0; i < sizeX; i++){
			if(!message.equals(""))
				break;
			for (int j = 0; j < sizeY; j++) {
				if (map[i][j] == 0)
					numberofEmptyCells++;
				else if (map[i][j] == 3) {// validates entry points
					numberOfEntry++;
					if(numberOfEntry >1){
						message = "Invalis Map: Wrong number of entry points";
						break;
					}
					if (j != 0 && numberOfEntry == 1) {
						message = "Invalid Map: inapropriate erntry point";
						break;
					}

				} else if (map[i][j] == 4) { // validates exit points
					numberOfExit++;
					if(numberOfExit > 1){
						message = "Invalis Map: Wrong number of exit points";
						break;
					}
					if ((j != sizeY - 1) && (numberOfExit == 1)) {
						message = "Invalid Map: inapropriate exit point";
						break;
					}
				} else if (map[i][j] == 5)
					numberOfTowers++;

			}
	}
		
		if(message.equals("")){
		if (numberofEmptyCells > 0)
			message = "Invalid Map: not all the cells are covered";
		else if (numberOfEntry == 0)
			message = "Invalis Map: No entry points";
		else if ( numberOfExit == 0)
			message = "Invalis Map: No exit points";
	}
		
		return message;
	}

	public Stack<GraphNode> shortestPathfinding() {

		Stack<GraphNode> nodeStack = new Stack<GraphNode>();
		Stack<GraphNode> nodePath = new Stack<GraphNode>();
		boolean pathIsFound = false;
		String green = "green"; // is not processed yet
		String yellow = "yellow"; // is in a stack
		String blo = "blo"; // neighbors in a stack
		String red = "red";// processing is done

		// nodePath.push(source);
		nodeStack.push(graphMap.get(source));
		graphMap.get(source).stateColor = yellow;

		while (!nodeStack.empty()) { // pass over the graph nodes until the
										// destination is find or there are no
										// more nodes left in the stack
			if (nodeStack.peek().name.equals(destination)) { // if we have the
																// same source
																// and
																// destination
																// th...
				pathIsFound = true;
				nodePath.add(nodeStack.peek());
				break;

			} else {
				GraphNode processingNode = nodeStack.peek();
				if (processingNode.stateColor.equals(yellow)) { // if the
																// neighbors are
																// not in the
																// stack yet

					nodePath.push(processingNode); // add current processing
													// node into the stack as
													// part of a path
					processingNode.stateColor = blo;
					Iterator<GraphNode> it = processingNode.neighbors
							.iterator();
					int insertedNeighbers = 0;
					while (it.hasNext()) {
						GraphNode currentNaighbor = it.next();
						if (currentNaighbor.stateColor.equals(green)) {
							insertedNeighbers++;
							currentNaighbor.stateColor = yellow;
							nodeStack.push(currentNaighbor);
						}
					}

					if (insertedNeighbers == 0) {

						processingNode.stateColor = red;
						if (!nodePath.empty())
							nodePath.pop();
						if (!nodeStack.empty())
							nodeStack.pop();

					}
				} else if (processingNode.stateColor.equals(blo)
						|| processingNode.stateColor.equals(red)) {
					if (!nodePath.empty() && !nodeStack.empty()) {
						nodePath.pop();
						nodeStack.pop();
					} else {

						System.out.println("Stack is empty");
					}

					processingNode.stateColor = red;// change the color so that
													// it is clear the
													// processing is already
													// done with this node

				}

			}
		} // close while
		
		Stack<GraphNode> returnalue;
		if(!pathIsFound) returnalue = null;
		else returnalue = nodePath;
		
		return returnalue;

	}

	public String mapManager() {
		String ErrorMessage = "";
		if ((ErrorMessage = validateEntryExit()).isEmpty()) {
			if ((ErrorMessage = fillGraph()).isEmpty())
				if ((path = shortestPathfinding()) == null)
					ErrorMessage = "Invalid Map: Path does not found between Entry and Exit points";
	
	}

		return ErrorMessage;

	}

}