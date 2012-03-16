package com.si.mapbuilder.parser;

import static org.fest.assertions.Assertions.assertThat;

import com.si.mapbuilder.gmap.Line;
import com.si.mapbuilder.gmap.Point;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * Copyright meditrack 2011
 * User: simonvandersluis
 * Date: 6/03/12
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class KmlParserTest {

  private KmlParser parser;

  @Before
  public void createParser() {
    InputStream kml = this.getClass().getResourceAsStream("/map.kml");
    parser = new KmlParser(kml);
  }

  @Test
  public void parse() {
    Line expected = new Line();
    expected.addPoint(new Point(174.765520,-41.290770));
    expected.addPoint(new Point(174.765560,-41.290940));
    expected.addPoint(new Point(174.765450,-41.291320));
    expected.addPoint(new Point(174.765450,-41.291320));
    expected.addPoint(new Point(174.765570,-41.291430));
    expected.addPoint(new Point(174.765600,-41.291790));
    expected.addPoint(new Point(174.765710,-41.291870));


    parser.parse();
    assertThat(parser.getLine()).isEqualTo(expected);
  }

}
