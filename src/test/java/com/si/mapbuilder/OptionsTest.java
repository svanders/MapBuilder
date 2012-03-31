package com.si.mapbuilder;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: simonvandersluis
 * Date: 31/03/12
 * Time: 12:16 PM
 */
public class OptionsTest {


  @Test
  public void testDryRun() {
    String[] args = new String[]{"-dryRun"};
    Options options = new Options(args);
    assertThat(options.isDryRun()).isTrue();
  }

  @Test
  public void testDryRunMixedCase() {
    String[] args = new String[]{"-DrYRuN"};
    Options options = new Options(args);
    assertThat(options.isDryRun()).isTrue();
  }

  @Test
  public void testDryRunMixed() {
    String[] args = new String[]{"blah", "-dryRun", "blah"};
    Options options = new Options(args);
    assertThat(options.isDryRun()).isTrue();
  }

  @Test
  public void testDryRunFalse() {
    String[] args = new String[]{};
    Options options = new Options(args);
    assertThat(options.isDryRun()).isFalse();
  }

  @Test
  public void testSrc() {
    String[] args = new String[]{"-src=/home"};
    Options options = new Options(args);
    assertThat(options.getSource()).isEqualTo("/home");
  }

  @Test
  public void testSrcMixedCase() {
    String[] args = new String[]{"-sRc=/Home"};
    Options options = new Options(args);
    assertThat(options.getSource()).isEqualTo("/Home");
  }


  @Test
  public void testSrcMixed() {
    String[] args = new String[]{"blah", "-src=/home", "blah"};
    Options options = new Options(args);
    assertThat(options.getSource()).isEqualTo("/home");
  }

  @Test
  public void testFetchDelay() {
    String[] args = new String[]{"-fetchDelay=100"};
    Options options = new Options(args);
    assertThat(options.getFetchDelayMillis()).isEqualTo(100);
  }

  @Test
  public void testFetchDelayNone() {
    String[] args = new String[]{""};
    Options options = new Options(args);
    assertThat(options.getFetchDelayMillis()).isEqualTo(90000);
  }

  @Test
  public void testFetchDelayMixedCase() {
    String[] args = new String[]{"-fEtChdElAy=100"};
    Options options = new Options(args);
    assertThat(options.getFetchDelayMillis()).isEqualTo(100);
  }

  @Test
  public void testFetchDelayMixed() {
    String[] args = new String[]{"blah", "-fetchDelay=100", "blah"};
    Options options = new Options(args);
    assertThat(options.getFetchDelayMillis()).isEqualTo(100);
  }

  @Test(expected = Exception.class)
  public void testFetchDelayInvalid() {
    String[] args = new String[]{"-fetchDelay=100.1"};
    Options options = new Options(args);
    assertThat(options.getFetchDelayMillis()).isEqualTo(100);
  }
}
