import junit.framework.TestCase;

import org.junit.Test;

import cs3500.set.model.hw02.Card;

/**
 * the testing class for the Card Object.
 */

public class CardTest extends TestCase {

  Card card1 = new Card(1, 'E', 'O');
  Card card2 = new Card(2, 'S', 'D');
  Card card3 = new Card(1, 'E', 'O');

  @Test
  public void testValidConstruction() {
    assertEquals(1, card1.getCount());
    assertEquals('E', card1.getFilling());
    assertEquals('O', card1.getShape());

    assertEquals(2, card2.getCount());
    assertEquals('S', card2.getFilling());
    assertEquals('D', card2.getShape());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstruction() {

    try {
      Card aCard = new Card(0, 'E', 'O');
    }
    catch (IllegalArgumentException e) {
      assertEquals("count value must be either 1, 2, or 3", e.getMessage());
    }

    try {
      Card aCard = new Card(1, 'g', 'O');
    }
    catch (IllegalArgumentException e) {
      assertEquals("filling value must be either 'E', 'S', or 'F'", e.getMessage());
    }

    try {
      Card aCard = new Card(1, 'E', 'p');
    }
    catch (IllegalArgumentException e) {
      assertEquals("shape value must be either 'O', 'Q', or 'D'", e.getMessage());
    }

    try {
      Card aCard = new Card(0, 'g', 'g');
    }
    catch (IllegalArgumentException e) {
      assertEquals("count value must be either 1, 2, or 3", e.getMessage());
    }
  }

  @Test
  public void testTestToString() {

    assertEquals("1EO", card1.toString());
    assertEquals("2SD", card2.toString());
  }

  @Test
  public void testTestEquals() {

    assertEquals(card1, card1);
    assertEquals(card1, card3);
    assertFalse(card1.equals(card2));
    assertFalse(card1 == null);
  }
}