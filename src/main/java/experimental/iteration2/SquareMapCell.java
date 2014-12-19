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

  public enum SquareMapCellAdjacent {
    NORTH(0b1), EAST(0b10), SOUTH(0b100), WEST(0b100);
    private final int binaryValue;

    SquareMapCellAdjacent(int binVal) {
      binaryValue = binVal;
    }
  }

  // In the following order: north, east, south, west
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
