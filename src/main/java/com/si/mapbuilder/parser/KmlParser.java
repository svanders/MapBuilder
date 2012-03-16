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
 *
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:04 AM
 */
public class KmlParser extends DefaultHandler {

  private static final String COORDS_TAG = "coordinates";

  private static final String LINE_TAG = "linestring";

  private CoordStringBuilder coords;


  private boolean inCoords = false;

  private boolean inLine = false;

  private final InputStream kml;


  public KmlParser(InputStream kml) {
    this.kml = kml;
  }

  public void parse() {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();
      saxParser.parse(kml, this);
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public void startDocument() throws SAXException {
    coords = new CoordStringBuilder();
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
      String data = new String(Arrays.copyOfRange(ch, start, start + length));
      coords.append(data);
    }
  }

  public Line getLine() {
    Line line = new Line();
    String[] splitCoords = coords.toString().split(" ");
    for (String coord : splitCoords)
    {
      coord = coord.trim();
      if (!coord.trim().isEmpty())
      {
        String[] xyz = coord.split(",");
        double x = Double.parseDouble(xyz[0]);
        double y =  Double.parseDouble(xyz[1]);
        line.addPoint(new Point(x, y));
      }
    }
    return line;
  }
//
//  private String filterData(String data) {
//    StringBuilder filtered = new StringBuilder();
//    for (int i = 0; i < coords.length(); i++)
//    {
//
//    }
//  }
    
}
