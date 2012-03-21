package com.si.mapbuilder.parser;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: simonvandersluis
 * Date: 17/03/12
 * Time: 11:54 AM
 */
public class CoordStringBuilderTest {

  private static final String EXPECTED = "174.765520,-41.290770,0.000000 174.765560,-41.290940,0.000000 174.765450,-41.291320,0.000000";

  private CoordStringBuilder builder;

  @Before
  public void initBuilder() {
    builder = new CoordStringBuilder();
  }


  @Test
  public void preFiltered() {
    builder.append(EXPECTED);
    assertThat(builder.toString()).isEqualTo(EXPECTED);
  }

  @Test
  public void individual() {
    builder.append("174.765520");
    builder.append(",-41.290770,");
    builder.append("0.000000\n");
    builder.append("174.765560,");
    builder.append("-41.290940,");
    builder.append("0.000000 ");
    builder.append("174.765450,");
    builder.append("-41.291320,");
    builder.append("0.000000");

    assertThat(builder.toString()).isEqualTo(EXPECTED);
  }

  @Test
  public void mixed() {
    builder.append("174.765520,-41.290770,\n");
    builder.append("0.000000\n");
    builder.append("174.765560,-41.290940,0.000000\n");
    builder.append("174.765450,");
    builder.append("-41.291320,");
    builder.append("0.000000");

    assertThat(builder.toString()).isEqualTo(EXPECTED);
  }

}
