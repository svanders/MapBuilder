package com.si.mapbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 *
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:02 AM
 */
public class Main {


  public static void main(String[] args) {
    try {
      File file = new File("/Users/simonvandersluis/Desktop/21kph/Routes.kml");
      InputStream is = new FileInputStream(file);
      MapProcessor processor = new MapProcessor(is);
      processor.process();
      System.exit(0);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }


  }
}
