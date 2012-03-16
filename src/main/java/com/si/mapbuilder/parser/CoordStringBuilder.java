package com.si.mapbuilder.parser;

import java.io.IOException;
import java.io.StringReader;

/**
 * User: simonvandersluis
 * Date: 17/03/12
 * Time: 11:49 AM
 */
public class CoordStringBuilder {

  private StringBuilder coordString = new StringBuilder();

  public void append(String str) {
    try {
      StringReader reader = new StringReader(str.trim());
      int read = reader.read();
      while (read != -1) {
        char c = (char) read;
        coordString.append(c);
        read = reader.read();
      }
      reader.close();

    }
    catch (IOException e) {
      throw new RuntimeException("Unable to read String '" + str + "'", e);
    }
  }

  public String toString() {
    return coordString.toString();
  }

}
