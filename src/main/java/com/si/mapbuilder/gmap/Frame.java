package com.si.mapbuilder.gmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright meditrack 2011
 * User: simonvandersluis
 * Date: 13/03/12
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
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

    this.points.add(point);
    if (points.size() ==1)
    {
      // first point
      minX = point.getX();
      maxX = point.getX();
      minY = point.getY();
      maxY = point.getY();
    }
  }


  public boolean isInFrame(Point point) {
    if (points.isEmpty()) {
      return true;
    }
    return fitsHeight(point.getY()) && fitsWidth(point.getX());
  }


  private boolean fitsWidth(double x) {

    if (isBetween(x, minX, maxX)) {
      return true;
    }

    if (x < minX && maxX - x <= width) {
      minX = x;
      return true;
    }

    if (x > maxX && x - minX <= width) {
      maxX = x;
      return true;
    }
    return false;
  }

  private boolean fitsHeight(double y) {

    if (isBetween(y, minY, maxY)) {
      return true;
    }

    if (y < minY && maxY - y <= height) {
      minY = y;
      return true;
    }

    if (y > maxY && y - minY <= height) {
      maxY = y;
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


}
