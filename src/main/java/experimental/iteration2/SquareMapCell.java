package experimental.iteration2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Square cells. Every cell has the potential to have four neighbors
 * 
 * @author paulbaker
 */
public class SquareMapCell extends MapCell {

  public enum SquareMapCellDirection {
    NORTH(0b1, 0), EAST(0b10, 1), SOUTH(0b100, 2), WEST(0b100, 3);
    private final int binaryValue, positionValue;

    SquareMapCellDirection(int binVal, int position) {
      binaryValue = binVal;
      positionValue = position;
    }

    /**
     * This returns the 0 based position of the enum.
     * 
     * @return
     */
    public int getPositionValue() {
      return positionValue;
    }

    /**
     * Returns the opposite direction. Eg, South will return North. East will return West.
     * 
     * @return
     */
    public SquareMapCellDirection oppositeDirection() {
      return directionFromInt((getPositionValue() + 2) % 4);
    }

    /**
     * Pass a value from [0, 3] to get its corresponding direction
     * 
     * <pre>
     * 0 = North
     * 1 = East
     * 2 = South
     * 3 = West
     * </pre>
     * 
     * @param value
     * @return
     */
    public static SquareMapCellDirection directionFromInt(int value) {
      switch (value) {
        case 0:
          return NORTH;
        case 1:
          return EAST;
        case 2:
          return SOUTH;
        case 3:
          return WEST;
        default:
          throw new IllegalArgumentException();
      }
    }
  }

  /**
   * In the following order: north, east, south, west. For convention, we will always go clockwise starting from the top
   * (or 12 o'clock position)
   */
  private MapCell[] adjacentCells;

  private int adjacentValue;

  private ImmutablePoint coordinate;

  public SquareMapCell(int row, int column) {
    this(new ImmutablePoint(row, column));
  }

  public SquareMapCell(ImmutablePoint coordinate) {
    this.coordinate = coordinate;
    clearAdjacentCells();
  }

  @Override
  public List<MapCell> getAdjacentCells() {
    return Arrays.asList(adjacentCells);
  }

  @Override
  public ImmutablePoint getCoordinates() {
    return coordinate;
  }

  @Override
  public void clearAdjacentCells() {
    adjacentCells = new MapCell[4];
    adjacentValue = 0b0;
  }
}
