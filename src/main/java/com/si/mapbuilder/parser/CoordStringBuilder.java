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
  
  private int lastTupleDpCount = 0;
  
  private boolean pastLastTupleDp = false;

  public void append(String str) {
    try {
      StringReader reader = new StringReader(str.trim());
      int read = reader.read();
      while (read != -1) {
        String c = new String(new char[]{(char) read});
        if (coordCharPattern.matcher(c).matches()) {
          coordString.append(c);
          if (pos == TuplePos.THIRD && pastLastTupleDp) {
            lastTupleDpCount++;
          }
          if (lastTupleDpCount == 6)
          {
            pos = pos.next();
            pastLastTupleDp = false;
            lastTupleDpCount = 0;
            coordString.append(' ');
          }
          if (pos == TuplePos.THIRD && c.equals(".")) {
            pastLastTupleDp = true;
          }
        }
        if (c.equals(",")) {
          coordString.append(c);
          pos = pos.next();
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
