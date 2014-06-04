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

	protected class Node {
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
		this.rows = rows;
		this.columns = columns;
		rand = new Random();
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
	public static TestStruct01 generateAldousBroder(int rows, int columns) {
		TestStruct01 drunkenWalkMaze = new TestStruct01(rows, columns);
		int totalNodes = rows * columns, visitedNodes = 0, drunkX = rand.nextInt(columns), drunkY = rand.nextInt(rows);

		while (visitedNodes < totalNodes) {

		}
		return drunkenWalkMaze;
	}

}
