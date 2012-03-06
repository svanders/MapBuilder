package com.si.mapbuilder.parser;

import com.si.mapbuilder.gmap.Line;
import com.si.mapbuilder.gmap.Point;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Copyright meditrack 2011
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class KmlParser extends DefaultHandler {

  private static final String COORDS_TAG = "coordinates";

  private static final String LINE_TAG = "linestring";

  private Line line;


  private boolean inCoords = false;

  private boolean inLine = false;


  SAXParser saxParser;

  public void parse(InputStream kml) {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      saxParser = factory.newSAXParser();
      saxParser.parse(kml, this);
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public void startDocument() throws SAXException {
    line = new Line();
    inLine = false;
    inCoords = false;
  }

  @Override
  public void endDocument() throws SAXException {
    inLine = false;
    inCoords = false;
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    if (LINE_TAG.equalsIgnoreCase(qName)) {
      inLine = true;
    }
    if (COORDS_TAG.equalsIgnoreCase(qName)) {
      inCoords = true;
    }
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {
    if (LINE_TAG.equalsIgnoreCase(qName)) {
      inLine = false;
    }
    if (COORDS_TAG.equalsIgnoreCase(qName)) {
      inCoords = false;
    }
  }

  public void characters(char ch[], int start, int length) throws SAXException {
    if (inLine && inCoords) {
      String coords = new String(Arrays.copyOfRange(ch, start, start + length));
      addPoints(coords);
    }
  }

  private void addPoints(String coords) {
    String[] splitCoords = coords.split(" ");
    for (String coord : splitCoords)
    {
      coord = coord.trim();
      if (!coord.isEmpty())
      {
        String[] xyz = coord.split(",");
        double x = Double.parseDouble(xyz[0]);
        double y =  Double.parseDouble(xyz[1]);
        line.addPoint(new Point(x, y));
      }
    }
  }

  public Line getLine() {
    return line;
  }
}
