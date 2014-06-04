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

		public Node(Node north, Node east, Node south, Node west) {
			edges = new Node[] { north, east, south, west };
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

		public void setNorth(Node replacement) {
			edges[0] = replacement;
		}

		public void setEast(Node replacement) {
			edges[1] = replacement;
		}

		public void setSouth(Node replacement) {
			edges[2] = replacement;
		}

		public void setWest(Node replacement) {
			edges[3] = replacement;
		}
	}

	private static Random rand;
	protected TestStruct01.Node[][] nodes;
	protected int rows;
	protected int columns;

	private TestStruct01(int rows, int columns) {
		nodes = new Node[rows][columns];
		this.rows = rows;
		this.columns = columns;
		rand = new Random();
	}

	public TestStruct01 generateAldousBroder(int rows, int columns) {
		TestStruct01 drunkenWalkMaze = new TestStruct01(rows, columns);
		int totalNodes = rows * columns, visitedNodes = 0, drunkX = rand.nextInt(columns), drunkY = rand.nextInt(rows);
		
		while (visitedNodes < totalNodes) {
			
		}
		return drunkenWalkMaze;
	}

}
