package com.si.mapbuilder.gmap;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

/**
 *
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:30 AM
 */
public class ImageFetcherTest {

  private ImageFetcher fetcher;

  @Before
  public void initFetcher() {
    fetcher = new ImageFetcher(400, 600, 14);
  }

  @Test
  public void checkContentType() throws Exception {
    Line line = new Line();
    line.addPoint(new Point(174.765520, -41.290770));
    line.addPoint(new Point(174.765560,-41.290940));
    File file = File.createTempFile("test", ".jpg");
    String contentType = fetcher.fetchImage(file, line);
    assertThat(contentType).isEqualTo("image/jpeg");
  }


}
