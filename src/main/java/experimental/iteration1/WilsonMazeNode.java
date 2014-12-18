package experimental.iteration1;

public class WilsonMazeNode extends MazeNode {

	public WilsonMazeNode(int row, int col) {
		super(row, col);
	}

	public WilsonMazeNode(MazeCoord coordinate) {
		super(coordinate);
	}

	public void resetAllDirections() {
		this.setNorth(null);
		this.setEast(null);
		this.setSouth(null);
		this.setWest(null);
	}

}
