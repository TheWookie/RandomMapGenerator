package experimental.iteration2;

import java.awt.Point;

/**
 * An immutable point. This can be passed around without worry of change
 * 
 * @author paulbaker
 */
public class ImmutablePoint {

  private int x, y;

  /**
   * Set the x and y value of the point
   * 
   * @param x
   * @param y
   */
  public ImmutablePoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get the x coordinate of the point
   * 
   * @return
   */
  public int getX() {
    return x;
  }

  /**
   * Get the y coordinate of the point
   * 
   * @return
   */
  public int getY() {
    return y;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (!(obj instanceof ImmutablePoint))
      return false;
    ImmutablePoint other = (ImmutablePoint) obj;
    if (x != other.x)
      return false;
    if (y != other.y)
      return false;
    return true;
  }

  /**
   * Takes a point and returns an immutable version of the same point
   * 
   * @param point
   * @return
   */
  public static ImmutablePoint fromPoint(Point point) {
    return new ImmutablePoint(point.x, point.y);
  }
}
