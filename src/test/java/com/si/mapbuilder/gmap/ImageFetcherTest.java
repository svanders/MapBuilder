package com.si.mapbuilder.gmap;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Copyright meditrack 2011
 * User: simonvandersluis
 * Date: 3/03/12
 * Time: 1:30 AM
 * To change this template use File | Settings | File Templates.
 */
public class ImageFetcherTest {

  private ImageFetcher fetcher;

  @Before
  public void initFetcher() {
    fetcher = new ImageFetcher(400, 600, 14);
  }

  @Test
  public void checkContentType() throws Exception {
    File file = File.createTempFile("test", ".png");
    String contentType = fetcher.fetchImage(file, 51.477222, 0);
    assertThat(contentType).isEqualTo("image/png");
  }


}
