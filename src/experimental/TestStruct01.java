package experimental;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class TestStruct01 {
	protected static class NodeCoord {
		int x, y;

		public NodeCoord(int row, int col) {
			this.y = row;
			this.x = col;
		}

		public NodeCoord(NodeCoord node, int rowOffset, int colOffset) {
			this.x = node.getRow() + rowOffset;
			this.y = node.getCol() + colOffset;
		}

		public int getRow() {
			return y;
		}

		public int getCol() {
			return x;
		}
	}

	protected static class Node {
		// Simple math will determine the character for the maze joint
		// Ascii character reference:
		// http://www.alanwood.net/unicode/box_drawing.html
		// Binary values for directions: N:1,E:2,S:4,W:8
		private static final String[] pathStringArr = { " ", "╵", "╶", "└", "╷", "│", "┌", "├", "╴", "┘", "─", "┴", "┐", "┤", "┬", "┼" };
		TestStruct01.NodeCoord coord;
		TestStruct01.Node[] edges;

		public Node(int row, int col) {
			this.coord = new NodeCoord(row, col);
			this.edges = new Node[4];
		}

		public Node(NodeCoord coordinate) {
			this(coordinate.getRow(), coordinate.getCol());
		}

		public NodeCoord getCoord() {
			return coord;
		}

		public int getRow() {
			return coord.getRow();
		}

		public int getCol() {
			return coord.getCol();
		}

		public Node getNorth() {
			return edges[0];
		}

		public Node getEast() {
			return edges[1];
		}

		public Node getSouth() {
			return edges[2];
		}

		public Node getWest() {
			return edges[3];
		}

		public void setNorth(Node north) {
			edges[0] = north;
		}

		public void setEast(Node east) {
			edges[1] = east;
		}

		public void setSouth(Node south) {
			edges[2] = south;
		}

		public void setWest(Node west) {
			edges[3] = west;
		}

		@Override
		public String toString() {
			int toStringIndex = 0;
			if (edges[0] != null)
				toStringIndex += 1;
			if (edges[1] != null)
				toStringIndex += 2;
			if (edges[2] != null)
				toStringIndex += 4;
			if (edges[3] != null)
				toStringIndex += 8;
			return pathStringArr[toStringIndex];
		}
	}

	private static Random rand = new Random();
	protected TreeMap<Integer, TreeMap<Integer, Node>> nodes;
	protected int rows;
	protected int columns;

	private TestStruct01(int rows, int columns) {
		if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException("Sizes must be a positive integer.");
		}
		nodes = new TreeMap<Integer, TreeMap<Integer, Node>>();
		this.rows = rows;
		this.columns = columns;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		final String nl = System.getProperty("line.separator");
		for (final int row : nodes.keySet()) {
			final TreeMap<Integer, Node> currentRow = nodes.get(row);
			for (final int col : currentRow.keySet()) {
				final Node currentNode = currentRow.get(col);
				sb.append(currentNode == null ? "X" : currentNode.toString());
			}
			sb.append(nl);
		}
		return sb.toString();
		// StringBuilder sb = new StringBuilder();
		// for (int i = 0; i < nodes.length; i++) {
		// for (int j = 0; j < nodes[i].length; j++) {
		// sb.append(nodes[i][j] == null ? "█" : nodes[i][j].toString());
		// }
		// sb.append(System.getProperty("line.separator"));
		// }
		// return sb.toString();
	}

	private Node setGetNode(int row, int col, Node node) {
		setNode(row, col, node);
		return node;
	}

	private void setNode(int row, int col, Node node) {
		if (!nodes.containsKey(row)) {
			nodes.put(row, new TreeMap<Integer, Node>());
		}
		nodes.get(row).put(col, node);
	}

	private Node getNode(int row, int col) {
		final TreeMap<Integer, Node> currentRow = nodes.get(row);
		if (currentRow == null)
			return null;
		return currentRow.get(col);
	}

	private static void joinAdjacentEdges(Node o1, Node o2) {
		if (o1.getRow() - o2.getRow() > 1 || o1.getCol() - o2.getCol() > 1) {
			throw new IllegalArgumentException("Nodes must be adjacent");
		}
		if (o1.getRow() == o2.getRow()) {
			if (o1.getCol() < o2.getCol()) {
				o1.setEast(o2);
				o2.setWest(o1);
			} else {
				o1.setWest(o2);
				o2.setEast(o1);
			}
		} else {
			if (o1.getRow() < o2.getRow()) {
				o1.setSouth(o2);
				o2.setNorth(o1);
			} else {
				o1.setNorth(o2);
				o2.setSouth(o1);
			}
		}
	}

	/** Known as the drunkard's-walk algorithm. Generate a maze by randomly
	 * visiting adjacent nodes until all the nodes in the specified space have
	 * been visited. It is an unbiased algorithm, however, it is not guaranteed to
	 * finish within a reasonable amount of time.
	 * 
	 * @param rows
	 * @param columns
	 * @return returns the newly generated maze. */
	public static TestStruct01 generateAldousBroder(final int rows, final int columns) {
		TestStruct01 drunkenWalkMaze = new TestStruct01(rows, columns);
		int totalNodes = rows * columns, visitedNodes = 1, drunkCol = rand.nextInt(columns), drunkRow = rand.nextInt(rows);
		Node currentNode = drunkenWalkMaze.setGetNode(drunkRow, drunkCol, new Node(new NodeCoord(drunkRow, drunkCol)));
		Node previousNode = currentNode;
		while (visitedNodes < totalNodes) {
			previousNode = currentNode;
			int direction = rand.nextInt(5);
			switch (direction) {
			case 0: // North
				if (drunkRow == 0)
					// drunk runs face first into a wall. He can't continue ergo
					// nothing happens this iteration.
					continue;
				drunkRow--;
				break;
			case 1: // East
				if (drunkCol + 1 == columns)
					continue;
				drunkCol++;
				break;
			case 2: // South
				if (drunkRow + 1 == rows)
					continue;
				drunkRow++;
				break;
			case 3: // West
				if (drunkCol == 0)
					continue;
				drunkCol--;
				break;
			}
			previousNode = currentNode;
			if ((currentNode = drunkenWalkMaze.getNode(drunkRow, drunkCol)) == null) {
				currentNode = drunkenWalkMaze.setGetNode(drunkRow, drunkCol, new Node(new NodeCoord(drunkRow, drunkCol)));
				joinAdjacentEdges(previousNode, currentNode);
				visitedNodes++;
				// clearConsole();
				System.out.println();
				System.out.println(drunkenWalkMaze);
			}
		}
		return drunkenWalkMaze;
	}

	// http://stackoverflow.com/a/17015039/1478636
	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			System.err.println("Can't do it");
			System.out.println("Can't do it");
		}
	}

	public static void main(String[] args) {
		int rows = rand.nextInt(20) + 5, cols = rand.nextInt(20) + 5;
		if (args.length > 0)
			rows = cols = Integer.parseInt(args[0]);
		if (args.length > 1)
			cols = Integer.parseInt(args[1]);
		System.out.println(TestStruct01.generateAldousBroder(rows, cols));
	}
}
