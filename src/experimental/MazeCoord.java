package experimental;

class MazeCoord {
	int c, r;

	public MazeCoord(int row, int col) {
		this.r = row;
		this.c = col;
	}

	public MazeCoord(MazeCoord node, int rowOffset, int colOffset) {
		this.c = node.getRow() + rowOffset;
		this.r = node.getCol() + colOffset;
	}

	public int getRow() {
		return r;
	}

	public int getCol() {
		return c;
	}
}