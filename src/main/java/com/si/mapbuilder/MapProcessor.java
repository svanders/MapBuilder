package com.si.mapbuilder;

import com.si.mapbuilder.gmap.Frame;
import com.si.mapbuilder.gmap.ImageFetcher;
import com.si.mapbuilder.gmap.Line;
import com.si.mapbuilder.gmap.Point;
import com.si.mapbuilder.parser.KmlParser;
import com.si.mapbuilder.util.UninterruptableThreadSleeper;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: simonvandersluis
 * Date: 16/03/12
 * Time: 8:51 PM
 */
public class MapProcessor {

  private final Options options;

  private File targetDir;

  private KmlParser source;

  List<Frame> frames = new ArrayList<Frame>();

  private boolean processed = false;

  public MapProcessor(Options options) {
    this.options = options;
  }

  public File init() throws Exception {
    File kmlFile = new File(options.getSource());
    File dir = kmlFile.getParentFile();
    targetDir = new File(dir, kmlFile.getName().replace(".kml", ""));
    if (!targetDir.exists()) {
      targetDir.mkdir();
    }
    this.source = new KmlParser(new FileInputStream(kmlFile));
    return targetDir;
  }

  public void run() {
    ImageFetcher fetcher = new ImageFetcher(600, 600, 1);
    List<Frame> frames = this.getFrames();
    int i = 0;
    for (Frame frame : frames) {
      if (options.isDryRun()) {
        System.out.println(fetcher.buildUrl(frame.getLine()));
        continue;
      }
      File f = new File(targetDir, (i++) + ".jpg");
      if (!f.exists()) {
        fetcher.fetchImage(f, frame.getLine());
        UninterruptableThreadSleeper.sleep(options.getFetchDelayMillis());
      }
    }
  }


  public List<Frame> getFrames() {
    if (!processed) {
      process();
    }
    return Collections.unmodifiableList(frames);
  }

  private void process() {
    source.parse();
    Line line = source.getLine();
    Frame frame = newFrame(null);
    Point previousPoint = null;
    for (Point p : line.getPoints()) {
      if (frame.isInFrame(p)) {
        frame.addPoint(p);
        previousPoint = p;
      } else {
        frame = newFrame(previousPoint);
        frame.addPoint(p);
      }
    }
    processed = true;
  }

  private Frame newFrame(Point firstPoint) {
    Frame frame = new Frame(0.02, 0.02);
    frames.add(frame);
    if (firstPoint != null) {
      frame.addPoint(firstPoint);
    }
    return frame;
  }

}
