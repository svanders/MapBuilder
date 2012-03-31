package com.si.mapbuilder.util;

/**
 * User: simonvandersluis
 * Date: 31/03/12
 * Time: 5:05 PM
 */
public class UninterruptableThreadSleeper {

  /**
   * Sleeps the current thread for the specified number of milliseconds,
   * any InterruptedExceptions are swallowed and the thread put back to sleep.
   * @param millis
   */
  public static void sleep(long millis) {
    if (millis < 1) {
      return;
    }

    long start = System.currentTimeMillis();
    long end = start + millis;

    while (System.currentTimeMillis() < end) {
      long sleepFor = end - System.currentTimeMillis();
      try {
        Thread.sleep(sleepFor);
      }
      catch (InterruptedException e) {
        // swallow.
      }
    }

  }
}
