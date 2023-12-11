import junit.framework.TestCase;

import org.junit.Test;

import cs3500.set.model.hw02.Coord;

/**
 * the testing class for the Coordinate Object.
 */

public class CoordTest extends TestCase {

  Coord coord1 = new Coord(0,0);
  Coord coord2 = new Coord(1,3);
  Coord coord3 = new Coord(0,0);

  @Test
  public void testValidConstruction() {
    assertEquals(coord1.row, 0);
    assertEquals(coord1.col, 0);

    assertEquals(coord2.row, 1);
    assertEquals(coord2.col, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstruction() {
    try {
      Coord invalid = new Coord(-1, 1);
    }
    catch (IllegalArgumentException e) {
      assertEquals("rows and columns must be non-negative.", e.getMessage());
    }

    try {
      Coord invalid = new Coord(1, -1);
    }
    catch (IllegalArgumentException e) {
      assertEquals("rows and columns must be non-negative.", e.getMessage());
    }

    try {
      Coord invalid = new Coord(-1, -1);
    }
    catch (IllegalArgumentException e) {
      assertEquals("rows and columns must be non-negative.", e.getMessage());
    }
  }

  @Test
  public void testTestToString() {
    assertEquals("(r0,c0)", coord1.toString());
    assertEquals("(r1,c3)", coord2.toString());
  }

  @Test
  public void testTestEquals() {
    assertEquals(coord1, coord1);
    assertEquals(coord1, coord3);
    assertFalse(coord1.equals(coord2));
    assertFalse(coord1 == null);
  }

  @Test
  public void testTestHashCode() {
    Coord coordA = new Coord(1, 2);
    Coord coordB = new Coord(1, 2);
    Coord coordC = new Coord(3, 3);

    assertEquals(coordA.hashCode(), coordB.hashCode());
    assertFalse(coordA.hashCode() == coordC.hashCode());
  }

}