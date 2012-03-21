package com.si.mapbuilder;

import com.si.mapbuilder.gmap.Frame;
import com.si.mapbuilder.gmap.Line;
import com.si.mapbuilder.gmap.Point;
import com.si.mapbuilder.parser.KmlParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * User: simonvandersluis
 * Date: 16/03/12
 * Time: 8:51 PM
 */
public class MapProcessor {

  private final KmlParser kml;

  List<Frame> frames = new ArrayList<Frame>();

  public MapProcessor(InputStream kml) {
    this.kml = new KmlParser(kml);
  }

  public void process() {
    kml.parse();
    Line line = kml.getLine();
    Frame frame = newFrame();
    for (Point p : line.getPoints()) {
      if (frame.isInFrame(p)) {
        frame.addPoint(p);
      }
      else {
        frame = newFrame();
        frame.addPoint(p);
      }
    }
  }

  public List<Frame> getFrames() {
    return Collections.unmodifiableList(frames);
  }

  private Frame newFrame() {
    Frame frame = new Frame(0.009460, 0.029097);
    frames.add(frame);
    return frame;
  }

}
