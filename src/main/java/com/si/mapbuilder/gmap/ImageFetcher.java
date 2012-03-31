package com.si.mapbuilder.gmap;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:03 AM
 */
public class ImageFetcher {

  // http://maps.google.com/maps/api/staticmap?center=51.477222,0&zoom=14&size=400x400&sensor=false


  private final int width;

  private final int height;

  private final int zoom;

  public ImageFetcher(int width, int height, int zoom) {
    this.width = width;
    this.height = height;
    this.zoom = zoom;
  }


  public String fetchImage(File f, Line line) {
    URL url = buildUrl(line);
    InputStream is = null;
    FileOutputStream fos = null;
    try {
      URLConnection urlCon = url.openConnection();
      is = url.openStream();
      String conentType = urlCon.getContentType();
      fos = new FileOutputStream(f);
      int redByte, count = 0;
      while ((redByte = is.read()) != -1) {
        fos.write(redByte);
        count++;
      }
      return conentType;
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    finally {
      closeLogexceptionAndCarryOn(is);
      closeLogexceptionAndCarryOn(fos);
    }

  }


  public URL buildUrl(double x, double y) {
    try {
      StringBuilder url = new StringBuilder("http://maps.google.com/maps/api/staticmap");
      url.append("?center=").append(x).append(",").append(y);
      url.append("&zoom=").append(zoom);
      url.append("&size=").append(width).append("x").append(height);
      url.append("&sensor=false");
      return new URL(url.toString());
    }
    catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public URL buildUrl(Line line) {
    try {
      StringBuilder url = new StringBuilder("http://maps.google.com/maps/api/staticmap");
      url.append("?size=").append(width).append("x").append(height);
      url.append("&scale=").append(zoom);
      url.append("&format=jpg");
//      url.append("&visibile=").append(bounds1.getX()).append(',').append(bounds1.getY());
//      url.append('|').append(bounds2.getX()).append(',').append(bounds2.getY());
      url.append("&sensor=false");
      url.append("&path=weight:5%7Ccolor:0xff00ff%7Cenc:");
      url.append(new PolylineEncoder().encodeLine(line));

      return new URL(url.toString());
    }
    catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  private void closeLogexceptionAndCarryOn(Closeable c)
  {
    if (c != null)
    {
      try {
        c.close();
      }
      catch (IOException e) {
        System.err.println("Error closing resource, ignoring.");
      }
    }
  }

}
