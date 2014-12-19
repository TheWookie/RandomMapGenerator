package experimental.iteration2;

import java.awt.Point;
import java.util.List;

/**
 * Implementing an abstract class to represent a single cell in our map. In the immediate future this will be
 * inherited/implemented as a four sided cell, in the distant future we will be using hexagonal shapes as well
 * 
 * @author paulbaker
 */
public abstract class MapCell {

  public abstract ImmutablePoint getCoordinates();

  public abstract List<MapCell> getAdjacentCells();

  public abstract void clearAdjacentCells();

}
