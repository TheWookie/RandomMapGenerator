package iteration1;

/** @author paul.n.baker@utah.edu */

class MazeCoord implements Comparable<MazeCoord> {
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

  // @Override
  public int compareTo(MazeCoord o) {
    int rowComp = this.r - o.r;
    if (rowComp != 0)
      return rowComp;
    return this.c - o.c;
  }
}
