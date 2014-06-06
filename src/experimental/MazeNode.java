package experimental;

class MazeNode {
	private static final String[] pathStringArr = { " ", "╵", "╶", "└", "╷", "│", "┌", "├", "╴", "┘", "─", "┴", "┐", "┤", "┬", "┼" };
	MazeCoord coord;
	MazeNode[] edges;

	public MazeNode(int row, int col) {
		this.coord = new MazeCoord(row, col);
		this.edges = new MazeNode[4];
	}

	public MazeNode(MazeCoord coordinate) {
		this(coordinate.getRow(), coordinate.getCol());
	}

	public MazeCoord getCoord() {
		return coord;
	}

	public int getRow() {
		return coord.getRow();
	}

	public int getCol() {
		return coord.getCol();
	}

	public MazeNode getNorth() {
		return edges[0];
	}

	public MazeNode getEast() {
		return edges[1];
	}

	public MazeNode getSouth() {
		return edges[2];
	}

	public MazeNode getWest() {
		return edges[3];
	}

	public void setNorth(MazeNode north) {
		edges[0] = north;
	}

	public void setEast(MazeNode east) {
		edges[1] = east;
	}

	public void setSouth(MazeNode south) {
		edges[2] = south;
	}

	public void setWest(MazeNode west) {
		edges[3] = west;
	}

	@Override
	public String toString() {
		return pathStringArr[corridorValue()];
	}

	/** Determines the numerical value of the corridor. 0 means that the corridor
	 * segment has no connections in any direction and 15 means it connects in all
	 * four directions. The values for each direction are as follows. 1 = North, 2
	 * = East, 4 = South, and 8 = West
	 * 
	 * @return */
	public int corridorValue() {
		int toStringIndex = 0;
		if (edges[0] != null)
			toStringIndex += 1;
		if (edges[1] != null)
			toStringIndex += 2;
		if (edges[2] != null)
			toStringIndex += 4;
		if (edges[3] != null)
			toStringIndex += 8;
		return toStringIndex;
	}
}