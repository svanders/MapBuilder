package com.si.mapbuilder.gmap;

/**
 *
 * User: simonvandersluis
 * Date: 6/03/12
 * Time: 6:54 PM
 */
public class Point {

  /** Latitude */
  private final double x;

  /** Longitude */
  private final double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Point))
    {
      return false;
    }
    Point p = (Point) other;
    return x == p.getX() && y == p.getY();
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}
