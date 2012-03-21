package com.si.mapbuilder.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Pattern;

/**
 * User: simonvandersluis
 * Date: 17/03/12
 * Time: 11:49 AM
 */
public class CoordStringBuilder {

  private StringBuilder coordString = new StringBuilder();

  private Pattern coordCharPattern = Pattern.compile("[0-9]|-|\\.");

  private TuplePos pos = TuplePos.FIRST;

  private boolean numberStarted = false;

  public void append(String str) {
    try {
      StringReader reader = new StringReader(str);
      int read = reader.read();
      while (read != -1) {
        String c = new String(new char[]{(char) read});
        if (coordCharPattern.matcher(c).matches()) {
          coordString.append(c);
          numberStarted = true;
        }
        else if (pos == TuplePos.THIRD && numberStarted) {
          pos = TuplePos.FIRST;
          coordString.append(' ');
        }
        else if (c.equals(",")) {
          coordString.append(c);
          pos = pos.next();
          numberStarted = false;
        }
        read = reader.read();
      }
      reader.close();
    }
    catch (IOException e) {
      throw new RuntimeException("Unable to read String '" + str + "'", e);
    }
  }

  public String toString() {
    return coordString.toString().trim();
  }
  


  private enum TuplePos {
    FIRST() {
      @Override
      public TuplePos next() {
        return SECOND;
      }
    },
    SECOND() {
      @Override
      public TuplePos next() {
        return THIRD;
      }
    },
    THIRD() {
      @Override
      public TuplePos next() {
        return FIRST;
      }
    };

    public abstract TuplePos next();
  }
}
