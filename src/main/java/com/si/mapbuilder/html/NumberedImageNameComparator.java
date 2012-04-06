package com.si.mapbuilder.html;

import java.util.Comparator;

/**
 * User: simonvandersluis
 * Date: 6/04/12
 * Time: 4:20 PM
 */
public class NumberedImageNameComparator implements Comparator<String> {

  @Override
  public int compare(String s, String s1) {
    Integer a = numberedImageFileNameToInt(s);
    Integer b = numberedImageFileNameToInt(s1);
    return a.compareTo(b);
  }

  private Integer numberedImageFileNameToInt(String s) {
    String number = s.substring(0, s.indexOf("."));
        return Integer.parseInt(number);
  }
}
