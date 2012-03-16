package com.si.mapbuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Copyright meditrack 2011
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class Main {


  public static int main(String[] args) {
    try {
      File file = new File("/Users/simonvandersluis/Desktop/21kph/Routes.kml");
      InputStream is = new FileInputStream(file);
      MapProcessor processor = new MapProcessor(is);
      processor.process();
      return 1;
    }
    catch (Exception e) {
      e.printStackTrace();
      return 100;
    }


  }
}
