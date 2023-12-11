
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;

/**
 * this is the tester for the SetThreeGameModel Object.
 */

public class SetThreeGameModelTest {

  SetThreeGameModel stgm = new SetThreeGameModel();
  Card card1 = new Card(3,'S','Q');
  Card card2 = new Card(3,'S','D');
  Card card3 = new Card(3,'F','Q');
  Card card4 = new Card(1,'S','O');
  Card card5 = new Card(1,'S','D');
  Card card6 = new Card(1,'S','Q');
  Card card7 = new Card(3,'S','Q');

  @Test
  public void testValidConstruction() {

    Assert.assertEquals(stgm.grid.length, 3);
    Assert.assertEquals(stgm.grid[0].length, 3);
    Assert.assertEquals(stgm.grid[1].length, 3);
    Assert.assertEquals(stgm.grid[2].length, 3);
    Assert.assertFalse(stgm.hasStarted);
    Assert.assertFalse(stgm.hasEnded);
    Assert.assertEquals(stgm.score, 0);
    Assert.assertEquals(stgm.deckIndex, 0);

  }

  @Test
  public void testClaimSet() {

    List<Card> deck = new ArrayList<>();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    deck.add(new Card(2, 'E', 'O'));
    deck.add(new Card(2, 'E', 'Q'));
    deck.add(new Card(2, 'E', 'D'));

    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 1);
    Coord coord3 = new Coord(0, 2);

    stgm.startGameWithDeck(deck, 3, 3);
    stgm.claimSet(coord1, coord2, coord3);

    Assert.assertEquals(1, stgm.score);

    Assert.assertEquals(deck.get(9), stgm.getGrid()[0][0]);
    Assert.assertEquals(deck.get(10), stgm.getGrid()[0][1]);
    Assert.assertEquals(deck.get(11), stgm.getGrid()[0][2]);

    Assert.assertEquals(deck.get(3), stgm.getGrid()[1][0]);
    Assert.assertEquals(deck.get(4), stgm.getGrid()[1][1]);
    Assert.assertEquals(deck.get(5), stgm.getGrid()[1][2]);
    Assert.assertEquals(deck.get(6), stgm.getGrid()[2][0]);
    Assert.assertEquals(deck.get(7), stgm.getGrid()[2][1]);
    Assert.assertEquals(deck.get(8), stgm.getGrid()[2][2]);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testClaimSet_invalidCoords() {

    List<Card> deck = new ArrayList<>();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    stgm.startGameWithDeck(deck, 3, 3);

    try {
      stgm.claimSet(new Coord(-2,0), new Coord(0, 0), new Coord(0, 0));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be non-negative.", e.getMessage());
      throw e;
    }

    try {
      stgm.claimSet(new Coord(2,34), new Coord(0, 0), new Coord(0, 0));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("row and column range must be from 0 to 2", e.getMessage());
      throw e;
    }

    try {
      stgm.claimSet(null, new Coord(0, 0), new Coord(0, 0));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must be non-null.", e.getMessage());
      throw e;
    }

    try {
      stgm.claimSet(null, null, null);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must be non-null.", e.getMessage());
      throw e;
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testClaimSet_invalidSet() {
    List<Card> deck = new ArrayList<>();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    stgm.startGameWithDeck(deck, 3, 3);

    try {
      stgm.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must form a set.", e.getMessage());
      throw e;
    }

  }

  @Test(expected = IllegalStateException.class)
  public void testClaimSet_gameState() {
    List<Card> deck = new ArrayList<>();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    try {
      stgm.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }

    stgm.startGameWithDeck(deck, 3, 3);
    stgm.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    Assert.assertTrue(stgm.isGameOver());

    try {
      stgm.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("game has ended.", e.getMessage());
      throw e;
    }
  }

  @Test
  public void testStartGameWithDeck() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card1);
    deck.add(card1);

    stgm.startGameWithDeck(deck, 3,3);
    Assert.assertEquals(deck, stgm.getDeck());

  }

  @Test(expected = IllegalStateException.class)
  public void testStartGameWithDeck_notEnoughCards() {

    List<Card> deck;
    List<Card> deck2 = null;

    Assert.assertEquals(deck2, null);

    deck = new ArrayList<>();
    try {
      stgm.startGameWithDeck(deck, 3,3);
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("deck does not have enough cards to deal.", e.getMessage());
      throw e;
    }
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    try {
      stgm.startGameWithDeck(deck, 3,3);
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("deck does not have enough cards to deal.", e.getMessage());
      throw e;
    }

    deck.add(card1);
    deck.add(card1);
    deck.add(card1);

    stgm.startGameWithDeck(deck, 3,3);

    Assert.assertEquals(deck, stgm.getDeck());
    Assert.assertEquals(stgm.getHeight(), 3);
    Assert.assertEquals(stgm.getWidth(), 3);
    Assert.assertTrue(stgm.hasStarted);
    Assert.assertFalse(stgm.hasEnded);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameWithDeck_invalidInput() {
    try {
      stgm.startGameWithDeck(null, 3, 3);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("deck must be defined.", e.getMessage());
      throw e;
    }
  }


  @Test
  public void testGetWidth() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card1);
    deck.add(card1);
    deck.add(card1);

    stgm.startGameWithDeck(deck, 3, 3);
    Assert.assertEquals(stgm.grid.length, 3);

  }

  @Test(expected = IllegalStateException.class)
  public void testGetWidth_gameNotStarted() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card1);
    deck.add(card1);
    deck.add(card1);
    try {
      stgm.getWidth();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }
  }

  @Test
  public void testGetHeight() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card1);
    deck.add(card1);
    deck.add(card1);

    stgm.startGameWithDeck(deck, 3, 3);
    Assert.assertEquals(stgm.grid[0].length, 3);
    Assert.assertEquals(stgm.grid[1].length, 3);
    Assert.assertEquals(stgm.grid[2].length, 3);


  }

  @Test(expected = IllegalStateException.class)
  public void testGetHeight_gameNotStarted() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card1);
    deck.add(card1);
    deck.add(card1);
    try {
      stgm.getHeight();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }
  }


  @Test
  public void testGetScore() {
    List<Card> deck = new ArrayList<>();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    deck.add(new Card(2, 'E', 'O'));
    deck.add(new Card(2, 'E', 'Q'));
    deck.add(new Card(2, 'E', 'D'));

    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 1);
    Coord coord3 = new Coord(0, 2);

    stgm.startGameWithDeck(deck, 3, 3);
    stgm.claimSet(coord1, coord2, coord3);

    Assert.assertEquals(1, stgm.score);

    Assert.assertEquals(deck.get(9), stgm.getGrid()[0][0]);
    Assert.assertEquals(deck.get(10), stgm.getGrid()[0][1]);
    Assert.assertEquals(deck.get(11), stgm.getGrid()[0][2]);

    Assert.assertEquals(deck.get(3), stgm.getGrid()[1][0]);
    Assert.assertEquals(deck.get(4), stgm.getGrid()[1][1]);
    Assert.assertEquals(deck.get(5), stgm.getGrid()[1][2]);
    Assert.assertEquals(deck.get(6), stgm.getGrid()[2][0]);
    Assert.assertEquals(deck.get(7), stgm.getGrid()[2][1]);
    Assert.assertEquals(deck.get(8), stgm.getGrid()[2][2]);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetScore_gameNotStarted() {
    List<Card> deck = new ArrayList<>();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    deck.add(new Card(2, 'E', 'O'));
    deck.add(new Card(2, 'E', 'Q'));
    deck.add(new Card(2, 'E', 'D'));

    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 1);
    Coord coord3 = new Coord(0, 2);

    try {
      stgm.claimSet(coord1, coord2, coord3);
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }

  }

  @Test
  public void testAnySetsPresent() {

    List<Card> deck = new ArrayList<Card>();
    SetThreeGameModel stgm = new SetThreeGameModel();
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'D'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'D'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'D'));

    stgm.startGameWithDeck(deck, 3, 3);
    Assert.assertTrue(stgm.anySetsPresent());

    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(2, 'S', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));


    Assert.assertTrue(stgm.anySetsPresent());

  }

  @Test(expected = IllegalStateException.class)
  public void testAnySetsPresent_invalidGameState() {

    List<Card> deck = new ArrayList<>();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    try {
      stgm.anySetsPresent();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }

    stgm.startGameWithDeck(deck, 3, 3);
    stgm.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));

    try {
      stgm.anySetsPresent();
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("game has ended.", e.getMessage());
      throw e;
    }
  }

  @Test
  public void testIsValidSet() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(2, 'S', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));

    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));


    Coord coord1 = new Coord(0,0);
    Coord coord2 = new Coord(0,1);
    Coord coord3 = new Coord(0,2);


    stgm.startGameWithDeck(deck, 3, 3);

    Assert.assertTrue(stgm.isValidSet(coord1, coord2, coord3));
    Assert.assertTrue(stgm.isValidSet(coord3, coord1, coord2));

    Assert.assertFalse(stgm.isValidSet(coord1, coord2, coord2));
    Assert.assertFalse(stgm.isValidSet(coord1, coord1, coord1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsValidSet_invalidCoord() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(2, 'S', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));

    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));

    Coord coord1 = new Coord(1,0);
    Coord coord2 = new Coord(0,1);
    Coord coord3 = new Coord(0,2);

    stgm.startGameWithDeck(deck, 3, 3);

    try {
      stgm.isValidSet(new Coord(-1, 2), new Coord(1, 2), new Coord(1, 1));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be non-negative.", e.getMessage());
      throw e;
    }

    try {
      stgm.isValidSet(new Coord(1, 42), new Coord(23, 2), new Coord(1, 3));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("row and column range must be from 0 to 2", e.getMessage());
      throw e;
    }

  }

  @Test
  public void testGetCardAtCoord() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card1);
    deck.add(card1);
    deck.add(card6);

    stgm.startGameWithDeck(deck, 3, 3);
    Assert.assertEquals(card1, stgm.getCardAtCoord(0, 0));
    Assert.assertEquals(card4, stgm.getCardAtCoord(1, 0));
    Assert.assertEquals(card6, stgm.getCardAtCoord(2, 2));

  }

  @Test
  public void testTestGetCardAtCoord() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card1);
    deck.add(card1);
    deck.add(card6);

    Coord coord1 = new Coord(0,0);
    Coord coord2 = new Coord(1,0);
    Coord coord3 = new Coord(2,2);


    stgm.startGameWithDeck(deck, 3, 3);
    Assert.assertEquals(card1, stgm.getCardAtCoord(coord1));
    Assert.assertEquals(card4, stgm.getCardAtCoord(coord2));
    Assert.assertEquals(card6, stgm.getCardAtCoord(coord3));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardAtCoord_gameNotStarted() {
    try {
      stgm.getCardAtCoord(new Coord(0, 1));
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetCardAtCoord_invalidCoord() {
    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card1);
    deck.add(card1);
    deck.add(card6);

    stgm.startGameWithDeck(deck, 3, 3);

    try {
      stgm.getCardAtCoord(null);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must be non-null.", e.getMessage());
      throw e;
    }

    try {
      stgm.getCardAtCoord(new Coord(1, 24));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be between 0 and 2.", e.getMessage());
      throw e;
    }

    try {
      stgm.getCardAtCoord(new Coord(-1, 2));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be non-negative.", e.getMessage());
      throw e;
    }

  }

  @Test
  public void testIsGameOver() {
    List<Card> deck = new ArrayList<>();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    deck.add(new Card(2, 'E', 'O'));
    deck.add(new Card(2, 'E', 'Q'));
    deck.add(new Card(2, 'E', 'D'));

    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 1);
    Coord coord3 = new Coord(0, 2);

    stgm.startGameWithDeck(deck, 3, 3);
    stgm.claimSet(coord1, coord2, coord3);

    Assert.assertFalse(stgm.isGameOver());
    Assert.assertFalse(stgm.isGameOver());

    stgm.claimSet(coord1, coord2, coord3);
    Assert.assertTrue(stgm.isGameOver());
    Assert.assertTrue(stgm.isGameOver());

  }

  @Test(expected = IllegalStateException.class)
  public void testIsGameOver_gameNotStarted() {
    List<Card> deck = new ArrayList<>();
    SetGameModel stgm = new SetThreeGameModel();

    try {
      stgm.isGameOver();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }
  }

  @Test
  public void testGetCompleteDeck() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));
    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    deck.add(new Card(2, 'E', 'O'));
    deck.add(new Card(2, 'E', 'Q'));
    deck.add(new Card(2, 'E', 'D'));
    deck.add(new Card(2, 'S', 'O'));
    deck.add(new Card(2, 'S', 'Q'));
    deck.add(new Card(2, 'S', 'D'));
    deck.add(new Card(2, 'F', 'O'));
    deck.add(new Card(2, 'F', 'Q'));
    deck.add(new Card(2, 'F', 'D'));

    deck.add(new Card(3, 'E', 'O'));
    deck.add(new Card(3, 'E', 'Q'));
    deck.add(new Card(3, 'E', 'D'));
    deck.add(new Card(3, 'S', 'O'));
    deck.add(new Card(3, 'S', 'Q'));
    deck.add(new Card(3, 'S', 'D'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'D'));

    Assert.assertEquals(deck, stgm.getCompleteDeck());
    Assert.assertEquals(27, stgm.getCompleteDeck().size());


  }

  @Test
  public void testGetDeck() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card1);
    deck.add(card1);
    deck.add(card6);

    stgm.startGameWithDeck(deck, 3, 3);

    Assert.assertEquals(deck.get(0), card1);
    Assert.assertEquals(deck.get(1), card2);
    Assert.assertEquals(deck.get(2), card3);
    Assert.assertEquals(deck.get(3), card4);
    Assert.assertEquals(deck.get(4), card5);
    Assert.assertEquals(deck.get(5), card6);
    Assert.assertEquals(deck.get(6), card1);
    Assert.assertEquals(deck.get(7), card1);
    Assert.assertEquals(deck.get(8), card6);

  }

  @Test(expected = IllegalStateException.class)
  public void testGetDeck_gameNotStarted() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card1);
    deck.add(card1);
    deck.add(card6);

    try {
      stgm.getDeck();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }
  }

  @Test
  public void testCompleteSet() {

    Card card1 = new Card(1, 'E', 'O');
    Card card2 = new Card(2, 'F', 'Q');
    Card card3 = new Card(1, 'S', 'Q');
    Card card4 = new Card(3, 'F', 'D');

    Card aCard12 = new Card(3, 'S', 'D');
    Card aCard13 = new Card(1, 'F', 'D');
    Card aCard14 = new Card(2, 'S', 'Q');
    Card aCard34 = new Card(2, 'E', 'O');
    Card aCard23 = new Card(3, 'E', 'Q');


    Assert.assertEquals(aCard12, stgm.completeSet(card1, card2));
    Assert.assertEquals(aCard13, stgm.completeSet(card1, card3));
    Assert.assertEquals(aCard14, stgm.completeSet(card1, card4));
    Assert.assertEquals(aCard34, stgm.completeSet(card3, card4));
    Assert.assertEquals(aCard23, stgm.completeSet(card2, card3));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testComleteSet_invalidCards() {

    Card card1 = new Card(1, 'E', 'O');
    Card card2 = new Card(1, 'E', 'O');

    try {
      stgm.completeSet(card1, card1);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("cards must not be the same.", e.getMessage());
      throw e;
    }

    try {
      stgm.completeSet(card1, card2);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("cards must not be the same.", e.getMessage());
      throw e;
    }

    try {
      stgm.completeSet(null, null);
      stgm.completeSet(card1, null);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("cards must be non-null", e.getMessage());
      throw e;
    }
  }

}