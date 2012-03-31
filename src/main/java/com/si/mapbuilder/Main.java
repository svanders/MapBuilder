package com.si.mapbuilder;

/**
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:02 AM
 */
public class Main {


  public static void main(String[] args) {
    try {
      Options options = new Options(args);
      MapProcessor processor = new MapProcessor(options);
      processor.init();
      processor.run();
      System.out.println(processor.getFrames().size() + " frames generated from map");
      System.exit(0);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
