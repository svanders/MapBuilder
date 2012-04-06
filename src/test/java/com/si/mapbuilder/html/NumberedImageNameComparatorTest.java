package com.si.mapbuilder.html;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: simonvandersluis
 * Date: 6/04/12
 * Time: 4:24 PM
 */
public class NumberedImageNameComparatorTest {

  private NumberedImageNameComparator comp;

  @Before
  public void createComp() {
    comp = new NumberedImageNameComparator();
  }

  @Test
  public void less() {
    String a = "1.jpg";
    String b = "10.jpg";
    assertThat(comp.compare(a, b)).isLessThan(0);
  }

  @Test
  public void more() {
    String a = "100.jpg";
    String b = "1.jpg";
    assertThat(comp.compare(a, b)).isGreaterThan(0);
  }

  @Test
  public void equal() {
    String a = "1.jpg";
    String b = "1.jpg";
    assertThat(comp.compare(a, b)).isEqualTo(0);
  }

}
