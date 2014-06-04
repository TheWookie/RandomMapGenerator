package experimental;

import java.util.Random;

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
		// N:1,E:2,S:4,W:8
		private static String[] toStringArray = { " ", "╵", "╶", "└", "╷", "│", "┌", "├", "╴", "┘", "─", "┴", "┐", "┤", "┬", "┼" };
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
			return toStringArray[toStringIndex];
		}
	}

	private static Random rand;
	protected TestStruct01.Node[][] nodes;
	protected int rows;
	protected int columns;

	private TestStruct01(int rows, int columns) {
		if (rows <= 0 || columns <= 0) {
			throw new IllegalArgumentException("Sizes must be a positive integer.");
		}
		nodes = new Node[rows][columns];
		// for (int i = 0; i < nodes.length; i++) {
		// nodes[i] = new Node[columns];
		// }
		this.rows = rows;
		this.columns = columns;
		rand = new Random();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes[i].length; j++) {
				sb.append(nodes[i][j] == null ? " " : nodes[i][j].toString());
			}
			sb.append("\n");
		}
		return sb.toString();
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
		Node[][] drunkNodes = drunkenWalkMaze.nodes;
		Node currentNode = drunkNodes[drunkRow][drunkCol] = new Node(new NodeCoord(drunkRow, drunkCol));
		Node previousNode = currentNode;
		while (visitedNodes < totalNodes) {
			previousNode = currentNode;
			int direction = rand.nextInt(5);
			switch (direction)
				{
				case 0: // North
					if (drunkRow == 0)
						// drunk runs face first into a wall. He can't continue ergo nothing
						// happens this iteration.
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
			if (drunkNodes[drunkRow][drunkCol] == null) {
				currentNode = drunkNodes[drunkRow][drunkCol] = new Node(new NodeCoord(drunkRow, drunkCol));
				joinAdjacentEdges(previousNode, currentNode);
				visitedNodes++;
				// System.out.println(drunkenWalkMaze);
			} else {
				currentNode = drunkNodes[drunkRow][drunkCol];
			}
		}
		return drunkenWalkMaze;
	}

	public static void main(String[] args) {
		TestStruct01 drunkMaze = TestStruct01.generateAldousBroder(10, 15);
		System.out.println(drunkMaze);
	}
}
