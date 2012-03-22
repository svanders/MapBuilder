package com.si.mapbuilder.parser;

import static org.fest.assertions.Assertions.assertThat;

import com.si.mapbuilder.gmap.Line;
import com.si.mapbuilder.gmap.Point;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 *
 * User: simonvandersluis
 * Date: 6/03/12
 * Time: 8:16 PM
 */
public class KmlParserTest {

  private Line expected;

  @Before
  public void createExpectedResult() {
    expected = new Line();
    expected.addPoint(new Point(-41.290770, 174.765520));
    expected.addPoint(new Point(-41.290940, 174.765560));
    expected.addPoint(new Point(-41.291320, 174.765450));
    expected.addPoint(new Point(-41.291320, 174.765450));
    expected.addPoint(new Point(-41.291430, 174.765570));
    expected.addPoint(new Point(-41.291790, 174.765600));
    expected.addPoint(new Point(-41.291870, 174.765710));
  }

  @Test
  public void testSimple() {
    InputStream kml = this.getClass().getResourceAsStream("/simple.kml");
    KmlParser parser = new KmlParser(kml);

    parser.parse();
    assertThat(parser.getLine()).isEqualTo(expected);
  }

  @Test
  public void testLineBreaks() {
    InputStream kml = this.getClass().getResourceAsStream("/lineBreaks.kml");
    KmlParser parser = new KmlParser(kml);

    parser.parse();
    assertThat(parser.getLine()).isEqualTo(expected);
  }


  @Test
  public void parseFileWithManyOtherElements() {
    InputStream kml = this.getClass().getResourceAsStream("/withManyOtherElements.kml");
    KmlParser parser = new KmlParser(kml);

    parser.parse();
    assertThat(parser.getLine()).isEqualTo(expected);
  }

}
