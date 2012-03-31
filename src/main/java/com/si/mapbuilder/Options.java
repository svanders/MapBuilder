package com.si.mapbuilder;

/**
 * Parses and stores the runtime args.  Ensures sensible defaults are
 * set for optional
 * User: simonvandersluis
 * Date: 31/03/12
 * Time: 12:14 PM
 */
public class Options {

  public static final String DRY_RUN = "-dryRun";

  public static final String SRC = "-src";

  public static final String FETCH_DELAY = "-fetchDelay";

  private boolean dryRun = false;

  private int fetchDelayMillis = 90000;

  private String src = "";

  public Options(String[] args) {
    for (String arg : args) {
      if (DRY_RUN.equalsIgnoreCase(arg)) {
        dryRun = true;
      }
      if (arg.toLowerCase().startsWith(SRC.toLowerCase())) {
        src = getArgValue(arg, SRC);
      }
      if (arg.toLowerCase().startsWith(FETCH_DELAY.toLowerCase())) {
        fetchDelayMillis = getIntArgValue(arg, SRC);
      }
    }
  }


  public boolean isDryRun() {
    return dryRun;
  }

  /**
   * Provides the location of the kml file (or directory of
   * files) to read.
   */
  public String getSource() {
    return src;
  }

  public int getFetchDelayMillis() {
    return fetchDelayMillis;
  }

  private String getArgValue(String arg, String argName) {
    try {
      String[] split = arg.split("=");
      String value = split[1].trim();
      return value;
    }
    catch (Exception e) {
      throw new RuntimeException("Unbale to determine value for '" + argName
          + "' from '" + arg + "'");
    }
  }

  private int getIntArgValue(String arg, String argName) {
    String value = getArgValue(arg, argName);
    try {
      return Integer.parseInt(value);
    }
    catch (Exception e) {
      throw new RuntimeException("Value for argument '" + arg + "' must be a number (integer)");
    }
  }
}
