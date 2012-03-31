package com.si.mapbuilder.gmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * User: simonvandersluis
 * Date: 13/03/12
 * Time: 8:34 PM
 */
public class Frame {

  private final double width;

  private final double height;

  private double minX = 0.0;

  private double maxX = 0.0;

  private double minY = 0.0;

  private double maxY = 0.0;

  private List<Point> points = new ArrayList<Point>();


  public Frame(double width, double height) {
    this.width = width;
    this.height = height;
  }

  public void addPoint(Point point) {
    if (!isInFrame(point)) {
      throw new IllegalArgumentException("Point " + point + " does not fit in the frame");
    }

    if (points.isEmpty())
    {
      // first point
      minX = point.getX();
      maxX = point.getX();
      minY = point.getY();
      maxY = point.getY();
    } else {
      setCheckBoundaries(point);
    }

    this.points.add(point);

  }

  private void setCheckBoundaries(Point p) {
    double x = p.getX();
    double y = p.getY();

    if (x < minX) {
      minX = x;
    }
    if (x > maxX) {
      maxX = x;
    }
    if (y < minY) {
      minY = y;
    }
    if (y > maxY) {
      maxY = y;
    }
  }


  public boolean isInFrame(Point point) {
    if (points.size() < 3) {
      return true;
    }
    return fitsHeight(point.getY()) && fitsWidth(point.getX());
  }


  private boolean fitsWidth(double x) {

    if (isBetween(x, minX, maxX)) {
      return true;
    }

    if (x < minX && maxX - x <= width) {
      return true;
    }

    if (x > maxX && x - minX <= width) {
      return true;
    }
    return false;
  }

  private boolean fitsHeight(double y) {

    if (isBetween(y, minY, maxY)) {
      return true;
    }

    if (y < minY && maxY - y <= height) {
      return true;
    }

    if (y > maxY && y - minY <= height) {
      return true;
    }
    return false;
  }

  private boolean isBetween(double x, double min, double max) {
    return (x >= min && x <= max);
  }


  public List<Point> getPoints() {
    return Collections.unmodifiableList(points);
  }
  
  public Line getLine() {
    return new Line(points);
  }

  public Point[] getBounds() {
    return new Point[] {new Point(minX, minY), new Point(maxX, maxY)};
  }
  
}
