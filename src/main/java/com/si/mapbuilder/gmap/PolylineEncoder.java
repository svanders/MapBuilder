package com.si.mapbuilder.gmap;

import java.util.Iterator;

/**
 *
 * User: simonvandersluis
 * Date: 5/03/12
 * Time: 9:56 PM
 * Encode polylines and points as described in the google static maps API
 * http://code.google.com/apis/maps/documentation/utilities/polylinealgorithm.html
 */
public class PolylineEncoder {


  public String encodePoint(Point pt, Point previousPt) {
    
    if (previousPt == null) {
      previousPt = new Point(0.0, 0.0);
    }

    int x = floor1e5(pt.getX()) - floor1e5(previousPt.getX());
    int y = floor1e5(pt.getY()) - floor1e5(previousPt.getY());

    String xEnc = encodeSignedNumber(x);
    String yEnc = encodeSignedNumber(y);
    return xEnc+yEnc;
  }

  public String encodeLine(Line line) {
    StringBuilder encLine = new StringBuilder();
    Point prev = null;
    Point at = null;
    Iterator<Point> iter = line.iterate();
    while (iter.hasNext()) {
      at = iter.next();
      encLine.append(encodePoint(at, prev));
      prev = at;
    }

    return encLine.toString();
  }
  
  
  
  private int floor1e5(double coordinate) {
    return (int) Math.floor(coordinate * 1e5);
  }
  
  private String encodeSignedNumber(int num) {
    int sgn_num = num << 1;
    if (num < 0) {
      sgn_num = ~(sgn_num);
    }
    return (encodeNumber(sgn_num));
  }

  private String encodeNumber(int num) {

    StringBuffer encodeString = new StringBuffer();

    while (num >= 0x20) {
      int nextValue = (0x20 | (num & 0x1f)) + 63;
      encodeString.append((char) (nextValue));
      num >>= 5;
    }

    num += 63;
    encodeString.append((char) (num));

    return encodeString.toString();
  }
}



/*
Take the initial signed value:
-179.9832104
Take the decimal value and multiply it by 1e5, rounding the result:
-17998321
Convert the decimal value to binary. Note that a negative value must be calculated using its two's complement by inverting the binary value and adding one to the result:
00000001 00010010 10100001 11110001
11111110 11101101 01011110 00001110
11111110 11101101 01011110 00001111
Left-shift the binary value one bit:
11111101 11011010 10111100 00011110
If the original decimal value is negative, invert this encoding:
00000010 00100101 01000011 11100001
Break the binary value out into 5-bit chunks (starting from the right hand side):
00001 00010 01010 10000 11111 00001
Place the 5-bit chunks into reverse order:
00001 11111 10000 01010 00010 00001
OR each value with 0x20 if another bit chunk follows:
100001 111111 110000 101010 100010 000001
Convert each value to decimal:
33 63 48 42 34 1
Add 63 to each value:
96 126 111 105 97 64
Convert each value to its ASCII equivalent:
`~oia@
*/
