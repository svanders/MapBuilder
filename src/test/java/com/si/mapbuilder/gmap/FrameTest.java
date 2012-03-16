package com.si.mapbuilder.gmap;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests the Frame class.
 * Date: 13/03/12
 * Time: 8:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class FrameTest {

  private static final double WIDTH = 2.0;

  private static final double HEIGHT = 3.0;

  private Frame frame;


  @Before
  public void initFrame() {
    frame = new Frame(WIDTH, HEIGHT);
  }

  @Test
  public void fistPointIsInFrame() {
    // since we don't  add any of the points they should all be in frame
    assertThat(frame.isInFrame(new Point(0.0, 0.0))).isTrue();
    assertThat(frame.isInFrame(new Point(100.0, -50.0))).isTrue();
    assertThat(frame.isInFrame(new Point(1e4, 1e-10))).isTrue();
  }

  @Test
  public void addFirstPoint() {
    frame.addPoint(new Point(50.0, 100.0));
    assertThat(frame.getPoints()).hasSize(1);
  }

  @Test
  public void secondPointInFrame() {
    frame.addPoint(new Point(0.0, 0.0));
    assertThat(frame.isInFrame(new Point(1.0, 2.0))).isTrue();
  }

  @Test
  public void secondPointInFrameBoundart() {
    frame.addPoint(new Point(0.0, 0.0));
    assertThat(frame.isInFrame(new Point(WIDTH, HEIGHT))).isTrue();
  }

  @Test
  public void secondPointNotInFrame() {
    frame.addPoint(new Point(0.0, 0.0));
    assertThat(frame.isInFrame(new Point(1.0, 3.1))).isFalse();
    assertThat(frame.isInFrame(new Point(2.0000001, 3.0))).isFalse();
  }

  @Test
  public void inFrame() {
    frame.addPoint(new Point(-1.0, 1.5));
    frame.addPoint(new Point(1.0, -1.5));
    
    assertThat(frame.isInFrame(new Point(0.0, 0.0))).isTrue();
    assertThat(frame.isInFrame(new Point(-1.0, 1.5))).isTrue();
    assertThat(frame.isInFrame(new Point(1.0, -1.5))).isTrue();
    assertThat(frame.isInFrame(new Point(1.0, -1.0))).isTrue();
  }
  
  @Test
  public void notInFrame() {
    frame.addPoint(new Point(-1.0, 1.5));
    frame.addPoint(new Point(1.0, -1.5));

    assertThat(frame.isInFrame(new Point(1.1, 0.0))).isFalse();
    assertThat(frame.isInFrame(new Point(-1.1, 0.0))).isFalse();
    assertThat(frame.isInFrame(new Point(0.0, 1.6))).isFalse();
    assertThat(frame.isInFrame(new Point(0.0, -1.6))).isFalse();
    assertThat(frame.isInFrame(new Point(5.0, -100.0))).isFalse();
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void addSecondPointOutOfFrame() {
    frame.addPoint(new Point(0.0, 0.0));
    frame.addPoint(new Point(WIDTH + 1.0, HEIGHT + 0.1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void addPointOutOfFrame() {
    frame.addPoint(new Point(-1.0, 1.5));
    frame.addPoint(new Point(1.0, -1.5));
    frame.addPoint(new Point(5.0, 4.0));
  }
}
