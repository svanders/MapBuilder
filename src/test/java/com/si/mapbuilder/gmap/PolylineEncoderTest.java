package com.si.mapbuilder.gmap;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 *
 * User: simonvandersluis
 * Date: 5/03/12
 * Time: 9:59 PM
 */
public class PolylineEncoderTest {

  private PolylineEncoder encoder = new PolylineEncoder();

  @Test
  public void encodePoint() {
    Point a = new Point(38.5, -120.2);
    Point b = new Point(40.7, -120.95);
    Point c = new Point(43.252, -126.453);

    assertThat(encoder.encodePoint(a, null)).isEqualTo("_p~iF~ps|U");
    assertThat(encoder.encodePoint(b, a)).isEqualTo("_ulLnnqC");
    assertThat(encoder.encodePoint(c, b)).isEqualTo("_mqNvxq`@");
  }

  @Test
  public void encodeLine() {
    Line l = new Line();
    l.addPoint(new Point(38.5, -120.2));
    l.addPoint(new Point(40.7, -120.95));
    l.addPoint(new Point(43.252, -126.453));

    assertThat(encoder.encodeLine(l)).isEqualTo("_p~iF~ps|U_ulLnnqC_mqNvxq`@");
  }

}
