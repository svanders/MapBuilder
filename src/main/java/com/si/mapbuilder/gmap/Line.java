package com.si.mapbuilder.gmap;

import java.util.*;

/**
 * Copyright meditrack 2011
 * User: simonvandersluis
 * Date: 6/03/12
 * Time: 7:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Line {
  
  private List<Point> points = new LinkedList<Point>();
  
  
  public Iterator<Point> iterate() {
    return points.iterator();
  }
  
  public List<Point> getPoints() {
    return Collections.unmodifiableList(points);
  }
  
  public void addPoint(Point point) {
    points.add(point);
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Line))
    {
      return false;
    }
    Line l = (Line) other; 
    return points.equals(l.getPoints());
  }
  
  @Override
  public String toString() {
    return points.toString();
  }
}
