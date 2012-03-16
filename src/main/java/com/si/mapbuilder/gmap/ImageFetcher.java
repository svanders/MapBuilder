package com.si.mapbuilder.gmap;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:03 AM
 */
public class ImageFetcher {

  // http://maps.google.com/maps/api/staticmap?center=51.477222,0&zoom=14&size=400x400&sensor=false


  private final int width;

  private final int hieght;

  private final int zoom;

  public ImageFetcher(int width, int hieght, int zoom) {
    this.width = width;
    this.hieght = hieght;
    this.zoom = zoom;
  }


  public String fetchImage(File f, double x, double y) {
    URL url = buildUrl(x, y);
    InputStream is = null;
    FileOutputStream fos = null;
    try {
      URLConnection urlC = url.openConnection();
      is = url.openStream();
      String conentType = urlC.getContentType();
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


  private URL buildUrl(double x, double y) {
    try {
      StringBuilder url = new StringBuilder("http://maps.google.com/maps/api/staticmap");
      url.append("?center=").append(x).append(",").append(y);
      url.append("&zoom=").append(zoom);
      url.append("&size=").append(width).append("x").append(hieght);
      url.append("&sensor=false");
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
