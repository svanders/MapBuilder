package com.si.mapbuilder.util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: simonvandersluis
 * Date: 31/03/12
 * Time: 5:14 PM
 */
public class UninterruptableThreadSleeperTest {

  public long startTime;

  @BeforeClass
  public static void avoidClassLoadingDelays() {
    UninterruptableThreadSleeper.sleep(1);
  }


  @Before
  public void recordStartTime() {
    startTime = System.currentTimeMillis();
  }

  @Test
  public void negativeSleepLessThan2Millis() {
    UninterruptableThreadSleeper.sleep(-10);
    assertThat(System.currentTimeMillis() - startTime).isLessThanOrEqualTo(2);
  }

  @Test
  public void zeroSleepLessThan2Millis() {
    UninterruptableThreadSleeper.sleep(0);
    assertThat(System.currentTimeMillis() - startTime).isLessThanOrEqualTo(2);
  }

  @Test
  public void sleepForDesiredTime() {
    UninterruptableThreadSleeper.sleep(10);
    long diff = System.currentTimeMillis() - startTime;
    assertThat(diff).isGreaterThanOrEqualTo(10);
    assertThat(diff).isLessThanOrEqualTo(12);
  }


}
