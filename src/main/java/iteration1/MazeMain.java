package iteration1;

import java.util.Random;
import java.util.TreeMap;

/** @author paul.n.baker@utah.edu */

public class MazeMain {

  private static Random rand = new Random();

  // TreeMaps are not the best structure for an arbitrary maze, however, they
  // are if you need them to preserve their natural order (the order of rows)
  // and you need them to arbitrarily change sizes in the future.
  protected TreeMap<Integer, TreeMap<Integer, MazeNode>> nodes;

  protected int rows;

  protected int columns;

  private MazeMain(int rows, int columns) {
    if (rows <= 0 || columns <= 0) {
      throw new IllegalArgumentException("Sizes must be a positive integer.");
    }
    nodes = new TreeMap<Integer, TreeMap<Integer, MazeNode>>();
    this.rows = rows;
    this.columns = columns;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    final String nl = System.getProperty("line.separator");
    for (final int row : nodes.keySet()) {
      final TreeMap<Integer, MazeNode> currentRow = nodes.get(row);
      for (final int col : currentRow.keySet()) {
        final MazeNode currentNode = currentRow.get(col);
        sb.append(currentNode == null
            ? "X"
            : currentNode.toString());
      }
      sb.append(nl);
    }
    return sb.toString();
  }

  private MazeNode setGetNode(int row, int col, MazeNode node) {
    setNode(row, col, node);
    return node;
  }

  private void setNode(int row, int col, MazeNode node) {
    if (!nodes.containsKey(row)) {
      nodes.put(row, new TreeMap<Integer, MazeNode>());
    }
    nodes.get(row).put(col, node);
  }

  private MazeNode getNode(int row, int col) {
    final TreeMap<Integer, MazeNode> currentRow = nodes.get(row);
    if (currentRow == null)
      return null;
    return currentRow.get(col);
  }

  // /** Joins two nodes if and only if they are adjacent. If they are not
  // adjacent,
  // * then we will throw a runtime exception.
  // *
  // * @param o1
  // * @param o2 */
  // private static void biDirectionalLink(MazeNode o1, MazeNode o2) {
  // if (o1.getRow() - o2.getRow() > 1 || o1.getCol() - o2.getCol() > 1) {
  // throw new IllegalArgumentException("Nodes must be adjacent");
  // }
  // if (o1.getRow() == o2.getRow()) {
  // if (o1.getCol() < o2.getCol()) {
  // o1.setEast(o2);
  // o2.setWest(o1);
  // } else {
  // o1.setWest(o2);
  // o2.setEast(o1);
  // }
  // } else {
  // if (o1.getRow() < o2.getRow()) {
  // o1.setSouth(o2);
  // o2.setNorth(o1);
  // } else {
  // o1.setNorth(o2);
  // o2.setSouth(o1);
  // }
  // }
  // }

  /**
   * Known as the drunkard's-walk algorithm. Generate a maze by randomly visiting adjacent nodes until all the nodes in
   * the specified space have been visited. It is an unbiased algorithm, however, it is not guaranteed to finish within
   * a reasonable amount of time. This is implemented iteratively, because we do not know when it could potentially
   * finish it is better not to assume that the client machine has enough memory to accommodate for excessive usage.
   * 
   * @param rows
   * @param columns
   * @return returns the newly generated maze.
   */
  public static MazeMain generateAldousBroder(final int rows, final int columns) {
    MazeMain drunkenWalkMaze = new MazeMain(rows, columns);
    int visitedNodes = 1, totalNodes = rows * columns, drunkCol = rand.nextInt(columns), drunkRow = rand.nextInt(rows);
    MazeNode currentNode = drunkenWalkMaze.setGetNode(drunkRow, drunkCol, new MazeNode(drunkRow, drunkCol));
    MazeNode previousNode = currentNode;
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
        currentNode = drunkenWalkMaze.setGetNode(drunkRow, drunkCol, new MazeNode(drunkRow, drunkCol));
        // biDirectionalLink(previousNode, currentNode);
        previousNode.biDirectionalLink(currentNode);
        visitedNodes++;
      }
    }
    return drunkenWalkMaze;
  }

  // /**
  // * @param rows
  // * @param columns
  // * @return
  // */
  // public static MazeMain generateWilson(final int rows, final int columns) {
  //
  // MazeMain wilsonWalkMaze = new MazeMain(rows, columns);
  // int visitedNodes = 1, totalNodes = rows * columns;
  // // Setup our end node
  // int destCol = rand.nextInt(columns), destRow = rand.nextInt(rows);
  // wilsonWalkMaze.setNode(destRow, destCol, new MazeNode(destRow, destCol));
  // while (visitedNodes < totalNodes) {
  // // MazeMain temp = new MazeMain(rows, columns);
  // int startCol, startRow;
  // MazeNode previous, current;
  // do {
  // startRow = rand.nextInt(rows);
  // startCol = rand.nextInt(columns);
  // }
  // while ((current = wilsonWalkMaze.getNode(startRow, startCol)) == null || current instanceof WilsonMazeNode);
  //
  // // MazeNode current = previous = wilsonWalkMaze.setGetNode(startRow,
  // // startCol, new WilsonMazeNode(startRow, startCol));
  //
  // }
  //
  // MazeNode currentNode = wilsonWalkMaze.setGetNode(drunkRow, drunkCol, new MazeNode(drunkRow, drunkCol));
  // MazeNode previousNode = currentNode;
  //
  // return null;
  // }

  // I can't get this to work for the life of me.
  // http://stackoverflow.com/a/17015039/1478636
  public final static void clearConsole() {
    try {
      final String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        Runtime.getRuntime().exec("cls");
      }
      else {
        Runtime.getRuntime().exec("clear");
      }
    }
    catch (final Exception e) {
      System.err.println("Can't do it");
      System.out.println("Can't do it");
    }
  }

  /**
   * Will initialize and print to screen an array with a random number of rows and a random number of columns. This can
   * be overriden with user via cli.
   * 
   * @param args Users can provide up to two parameters. If they provide no parameters, both rows and columns will be
   *          randomly assigned. If the user provides a single number, then both the rows and columns will be assigned
   *          that number. If the user provides two numbers then the first will determine rows and the second will
   *          determine columns.
   */
  public static void main(String[] args) {
    int rows = rand.nextInt(20) + 5, cols = rand.nextInt(20) + 5;
    if (args.length > 0)
      rows = cols = Integer.parseInt(args[0]);
    if (args.length > 1)
      cols = Integer.parseInt(args[1]);
    System.out.println(MazeMain.generateAldousBroder(rows, cols));
  }
}
