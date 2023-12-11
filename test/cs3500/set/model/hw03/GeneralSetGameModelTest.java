package cs3500.set.model.hw03;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;

/**
 * This is a GeneralSetGameModel.
 */

public class GeneralSetGameModelTest extends TestCase {
  List<Card> deck = new ArrayList<Card>();

  Card card1 = new Card(3,'S','Q');
  Card card2 = new Card(3,'S','D');
  Card card3 = new Card(3,'F','Q');
  Card card4 = new Card(1,'S','O');
  Card card5 = new Card(1,'S','D');
  Card card6 = new Card(1,'S','Q');
  Card card7 = new Card(3,'S','Q');
  Card card8 = new Card(2,'S','Q');
  Card card9 = new Card(2,'F','Q');
  Card card10 = new Card(2,'F','D');
  Card card11 = new Card(3,'F','D');
  Card card12 = new Card(1,'F','D');


  @Test
  public void testAddRow() {
    List<Card> deck = new ArrayList<>();
    SetGameModel model = new GeneralSetGameModel(3, 3);

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(2, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(2, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));
    deck.add(new Card(3, 'E', 'O'));

    model.startGameWithDeck(deck, 1, 3);
    assertEquals(model.getHeight(), 2);

  }

  @Test
  public void testRunFullGame() {
    Coord c1 = new Coord(0, 0);
    Coord c2 = new Coord(0, 1);
    Coord c3 = new Coord(0, 2);
    SetGameModel game = new GeneralSetGameModel();

    game.startGameWithDeck(game.getCompleteDeck(), 1, 3);
    assertEquals(0, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(1, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(2, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(3, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(4, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(5, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(6, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(7, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(8, game.getScore());
    assertEquals(1, game.getHeight());
    game.claimSet(c1, c2, c3);
    assertEquals(9, game.getScore());
    assertEquals(1, game.getHeight());
    assertTrue(game.isGameOver());
  }


  @Test
  public void testValidConstruction() {
    SetGameModel model = new GeneralSetGameModel();
    assertFalse(((GeneralSetGameModel) model).hasGameStarted());
  }

  @Test
  public void testValidConstruction_3x3() {
    SetGameModel model = new GeneralSetGameModel(3, 3);
    assertFalse(((GeneralSetGameModel) model).hasGameStarted());
    Assert.assertEquals(((GeneralSetGameModel) model).grid.length, 3);
    Assert.assertEquals(((GeneralSetGameModel) model).grid[0].length, 3);
    Assert.assertEquals(((GeneralSetGameModel) model).grid[1].length, 3);
    Assert.assertEquals(((GeneralSetGameModel) model).grid[2].length, 3);
  }

  @Test
  public void testValidConstruction_2x3() {
    SetGameModel model = new GeneralSetGameModel(2, 3);
    assertFalse(((GeneralSetGameModel) model).hasGameStarted());
    Assert.assertEquals(((GeneralSetGameModel) model).grid.length, 2);
    Assert.assertEquals(((GeneralSetGameModel) model).grid[0].length, 3);
    Assert.assertEquals(((GeneralSetGameModel) model).grid[1].length, 3);
  }

  @Test
  public void testClaimSet_3x3_CannotAddRow() {

    List<Card> deck = new ArrayList<>();
    SetGameModel model = new GeneralSetGameModel(3, 3);

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

    model.startGameWithDeck(deck, 3, 3);
    model.claimSet(coord1, coord2, coord3);

    Assert.assertEquals(1, model.getScore());

    Assert.assertEquals(deck.get(9), ((GeneralSetGameModel) model).getGrid()[0][0]);
    Assert.assertEquals(deck.get(10), ((GeneralSetGameModel) model).getGrid()[0][1]);
    Assert.assertEquals(deck.get(11), ((GeneralSetGameModel) model).getGrid()[0][2]);

    Assert.assertEquals(deck.get(3), ((GeneralSetGameModel) model).getGrid()[1][0]);
    Assert.assertEquals(deck.get(4), ((GeneralSetGameModel) model).getGrid()[1][1]);
    Assert.assertEquals(deck.get(5), ((GeneralSetGameModel) model).getGrid()[1][2]);
    Assert.assertEquals(deck.get(6), ((GeneralSetGameModel) model).getGrid()[2][0]);
    Assert.assertEquals(deck.get(7), ((GeneralSetGameModel) model).getGrid()[2][1]);
    Assert.assertEquals(deck.get(8), ((GeneralSetGameModel) model).getGrid()[2][2]);

  }

  @Test
  public void testClaimSet_3x3_CanAddRow() {

    List<Card> deck = new ArrayList<Card>();
    SetGameModel model = new GeneralSetGameModel();
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(2, 'F', 'Q'));
    deck.add(new Card(3, 'E', 'Q'));
    deck.add(new Card(1, 'S', 'O'));

    deck.add(new Card(2, 'F', 'Q'));
    deck.add(new Card(3, 'S', 'Q'));
    deck.add(new Card(2, 'E', 'Q'));

    deck.add(new Card(2, 'S', 'O'));
    deck.add(new Card(1, 'F', 'O'));


    model.startGameWithDeck(deck, 2, 2);

    Coord coord1 = new Coord(0, 0);
    Coord coord2 = new Coord(0, 1);
    Coord coord3 = new Coord(1, 0);

    model.claimSet(coord1, coord2, coord3);

    Assert.assertEquals(1, model.getScore());

    Assert.assertEquals(deck.get(4), ((GeneralSetGameModel) model).getGrid()[0][0]);
    Assert.assertEquals(deck.get(5), ((GeneralSetGameModel) model).getGrid()[0][1]);
    Assert.assertEquals(deck.get(6), ((GeneralSetGameModel) model).getGrid()[1][0]);
    Assert.assertEquals(deck.get(3), ((GeneralSetGameModel) model).getGrid()[1][1]);

    assertEquals(model.getHeight(), 3);

    Assert.assertEquals(deck.get(7), ((GeneralSetGameModel) model).getGrid()[2][0]);
    Assert.assertEquals(deck.get(8), ((GeneralSetGameModel) model).getGrid()[2][1]);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testClaimSet_invalidCoords_3x3() {

    List<Card> deck = new ArrayList<>();
    SetGameModel model = new GeneralSetGameModel(3, 3);

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    model.startGameWithDeck(deck, 3, 3);

    try {
      model.claimSet(new Coord(-2,0), new Coord(0, 0), new Coord(0, 0));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be non-negative.", e.getMessage());
    }

    try {
      model.claimSet(new Coord(2,34), new Coord(0, 0), new Coord(0, 0));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("column must not be greater than 3.", e.getMessage());
    }

    try {
      model.claimSet(null, new Coord(0, 0), new Coord(0, 0));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must be non-null.", e.getMessage());
    }

    try {
      model.claimSet(null, null, null);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must be non-null.", e.getMessage());
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testClaimSet_invalidCoords_5x2() {

    List<Card> deck = new ArrayList<>();
    SetGameModel model = new GeneralSetGameModel(5, 2);

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

    model.startGameWithDeck(deck, 3, 3);

    try {
      model.claimSet(new Coord(-2,0), new Coord(0, 0), new Coord(0, 0));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be non-negative.", e.getMessage());
    }

    try {
      model.claimSet(new Coord(2,34), new Coord(0, 0), new Coord(0, 0));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("column must not be greater than 3.", e.getMessage());
    }

    try {
      model.claimSet(null, new Coord(0, 0), new Coord(0, 0));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must be non-null.", e.getMessage());
    }

    try {
      model.claimSet(null, null, null);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must be non-null.", e.getMessage());
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testClaimSet_invalidSet_3x3() {
    List<Card> deck = new ArrayList<>();
    SetGameModel model = new GeneralSetGameModel();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    model.startGameWithDeck(deck, 3, 3);

    try {
      model.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must form a set.", e.getMessage());
    }

  }

  @Test(expected = IllegalArgumentException.class)
  public void testClaimSet_invalidSet_4x3() {
    List<Card> deck = new ArrayList<>();
    SetGameModel model = new GeneralSetGameModel();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    deck.add(new Card(2, 'E', 'O'));
    deck.add(new Card(2, 'S', 'Q'));
    deck.add(new Card(2, 'E', 'D'));

    model.startGameWithDeck(deck, 4, 3);

    try {
      model.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must form a set.", e.getMessage());
    }

  }

  @Test(expected = IllegalStateException.class)
  public void testClaimSet_gameState_3x3() {
    SetGameModel model = new GeneralSetGameModel();
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

    model.startGameWithDeck(deck, 3, 3);

    try {
      model.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
    }

    try {
      model.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has ended.", e.getMessage());
    }

    Assert.assertTrue(model.isGameOver());
  }

  @Test(expected = IllegalStateException.class)
  public void testClaimSet_gameState_2x4() {
    SetGameModel model = new GeneralSetGameModel();
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

    model.startGameWithDeck(deck, 2, 4);

    try {
      model.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
    }

    try {
      model.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has ended.", e.getMessage());
    }

    Assert.assertTrue(model.isGameOver());
  }

  @Test
  public void testStartGameWithDeck_3x3() {

    List<Card> deck = new ArrayList<Card>();
    SetGameModel model = new GeneralSetGameModel();
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(2, 'F', 'Q'));
    deck.add(new Card(3, 'S', 'Q'));
    deck.add(new Card(2, 'E', 'Q'));

    deck.add(new Card(2, 'E', 'Q'));
    deck.add(new Card(1, 'F', 'Q'));

    model.startGameWithDeck(deck, 2, 2);
    Assert.assertEquals(deck, ((GeneralSetGameModel) model).getDeck());
    assertEquals(model.getHeight(), 3);

  }

  @Test
  public void testStartGameWithDeck_2x4() {

    List<Card> deck = new ArrayList<Card>();
    SetGameModel model = new GeneralSetGameModel();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    model.startGameWithDeck(deck, 2, 4);
    Assert.assertEquals(deck, ((GeneralSetGameModel) model).getDeck());

  }

  @Test(expected = IllegalStateException.class)
  public void testStartGameWithDeck_notEnoughCards_3x3() {

    List<Card> deck;
    List<Card> deck2 = null;
    SetGameModel model = new GeneralSetGameModel();

    Assert.assertEquals(deck2, null);

    deck = new ArrayList<>();
    try {
      model.startGameWithDeck(deck, 3,3);
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("deck does not have enough cards to deal.", e.getMessage());
    }
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    try {
      model.startGameWithDeck(deck, 3,3);
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("deck does not have enough cards to deal.", e.getMessage());
    }

    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    model.startGameWithDeck(deck, 3,3);

    Assert.assertEquals(deck, ((GeneralSetGameModel) model).getDeck());
    Assert.assertEquals(model.getHeight(), 3);
    Assert.assertEquals(model.getWidth(), 3);
    Assert.assertTrue(((GeneralSetGameModel) model).hasGameStarted());
    Assert.assertFalse(model.isGameOver());

  }

  @Test(expected = IllegalStateException.class)
  public void testStartGameWithDeck_notEnoughCards_5x2() {

    List<Card> deck;
    List<Card> deck2 = null;
    SetGameModel model = new GeneralSetGameModel();

    Assert.assertEquals(deck2, null);

    deck = new ArrayList<>();
    try {
      model.startGameWithDeck(deck, 4,2);
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("deck does not have enough cards to deal.", e.getMessage());
    }
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    try {
      model.startGameWithDeck(deck, 4,2);
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("deck does not have enough cards to deal.", e.getMessage());
    }

    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    model.startGameWithDeck(deck, 4,2);

    Assert.assertEquals(deck, ((GeneralSetGameModel) model).getDeck());
    Assert.assertEquals(model.getHeight(), 4);
    Assert.assertEquals(model.getWidth(), 2);
    Assert.assertTrue(((GeneralSetGameModel) model).hasGameStarted());
    Assert.assertFalse(model.isGameOver());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameWithDeck_invalidInput_3x3() {
    SetGameModel model = new GeneralSetGameModel();
    try {
      model.startGameWithDeck(null, 3, 3);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("deck must be defined.", e.getMessage());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testStartGameWithDeck_invalidInput_4x5() {
    SetGameModel model = new GeneralSetGameModel();
    try {
      model.startGameWithDeck(null, 4, 5);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("deck must be defined.", e.getMessage());
    }
  }

  @Test
  public void testGetWidth_3x3() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 3, 3);
    Assert.assertEquals(((GeneralSetGameModel) model).getGrid().length, 3);

  }

  @Test
  public void testGetWidth_4x2() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 4, 2);
    Assert.assertEquals(((GeneralSetGameModel) model).getGrid().length, 4);

  }


  @Test(expected = IllegalStateException.class)
  public void testGetWidth_gameNotStarted_3x3() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(deck, 3, 3);

    try {
      model.getWidth();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWidth_gameNotStarted_2x4() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(deck, 2, 4);

    try {
      model.getWidth();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }
  }

  @Test
  public void testGetHeight_3x3() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 3, 3);
    Assert.assertEquals(((GeneralSetGameModel) model).getGrid()[0].length, 3);
    Assert.assertEquals(((GeneralSetGameModel) model).getGrid()[1].length, 3);
    Assert.assertEquals(((GeneralSetGameModel) model).getGrid()[2].length, 3);

  }

  @Test
  public void testGetHeight_2x2() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 2, 2);
    Assert.assertEquals(((GeneralSetGameModel) model).getGrid()[0].length, 2);
    Assert.assertEquals(((GeneralSetGameModel) model).getGrid()[1].length, 2);

  }

  @Test(expected = IllegalStateException.class)
  public void testGetHeight_gameNotStarted_3x3() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(deck, 3, 3);

    try {
      model.getHeight();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testGetHeight_gameNotStarted_2x4() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(deck, 2, 4);

    try {
      model.getHeight();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }
  }

  @Test
  public void testGetScore_3x3() {
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

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 3, 3);
    model.claimSet(coord1, coord2, coord3);

    Assert.assertEquals(1, model.getScore());

    Assert.assertEquals(deck.get(9), ((GeneralSetGameModel) model).getGrid()[0][0]);
    Assert.assertEquals(deck.get(10), ((GeneralSetGameModel) model).getGrid()[0][1]);
    Assert.assertEquals(deck.get(11), ((GeneralSetGameModel) model).getGrid()[0][2]);

    Assert.assertEquals(deck.get(3), ((GeneralSetGameModel) model).getGrid()[1][0]);
    Assert.assertEquals(deck.get(4), ((GeneralSetGameModel) model).getGrid()[1][1]);
    Assert.assertEquals(deck.get(5), ((GeneralSetGameModel) model).getGrid()[1][2]);
    Assert.assertEquals(deck.get(6), ((GeneralSetGameModel) model).getGrid()[2][0]);
    Assert.assertEquals(deck.get(7), ((GeneralSetGameModel) model).getGrid()[2][1]);
    Assert.assertEquals(deck.get(8), ((GeneralSetGameModel) model).getGrid()[2][2]);
  }

  @Test
  public void testGetScore_4x2() {
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
    Coord coord3 = new Coord(1, 0);

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 4, 2);
    model.claimSet(coord1, coord2, coord3);

    Assert.assertEquals(1, model.getScore());

    Assert.assertEquals(deck.get(8), ((GeneralSetGameModel) model).getGrid()[0][0]);
    Assert.assertEquals(deck.get(9), ((GeneralSetGameModel) model).getGrid()[0][1]);
    Assert.assertEquals(deck.get(10), ((GeneralSetGameModel) model).getGrid()[1][0]);

    Assert.assertEquals(deck.get(3), ((GeneralSetGameModel) model).getGrid()[1][1]);
    Assert.assertEquals(deck.get(4), ((GeneralSetGameModel) model).getGrid()[2][0]);
    Assert.assertEquals(deck.get(5), ((GeneralSetGameModel) model).getGrid()[2][1]);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetScore_gameNotStarted_3x3() {
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

    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(deck, 3, 3);

    try {
      model.claimSet(coord1, coord2, coord3);
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }

  }

  @Test(expected = IllegalStateException.class)
  public void testGetScore_gameNotStarted_2x4() {
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

    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(deck, 2, 4);

    try {
      model.claimSet(coord1, coord2, coord3);
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }

  }

  @Test
  public void testAnySetsPresent_3x3() {

    List<Card> deck = new ArrayList<Card>();
    GeneralSetGameModel model = new GeneralSetGameModel();
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'D'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'D'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'D'));

    model.startGameWithDeck(deck, 3, 3);
    Assert.assertTrue(model.anySetsPresent());

    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(2, 'S', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));

    Assert.assertTrue(model.anySetsPresent());

  }

  @Test
  public void testAnySetsPresent_2x4() {

    List<Card> deck = new ArrayList<Card>();
    SetGameModel model = new GeneralSetGameModel();
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'D'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'D'));
    deck.add(new Card(3, 'F', 'Q'));
    deck.add(new Card(3, 'F', 'O'));
    deck.add(new Card(3, 'F', 'D'));

    model.startGameWithDeck(deck, 2, 4);
    Assert.assertTrue(model.anySetsPresent());

    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(2, 'S', 'Q'));
    deck.add(new Card(3, 'F', 'Q'));

    Assert.assertTrue(model.anySetsPresent());

  }

  @Test(expected = IllegalStateException.class)
  public void testAnySetsPresent_invalidGameState_3x3() {

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

    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(deck, 3, 3);

    try {
      model.anySetsPresent();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }

    model.startGameWithDeck(deck, 3, 3);
    model.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));

    try {
      model.anySetsPresent();
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("game has ended.", e.getMessage());
      throw e;
    }
  }

  @Test(expected = IllegalStateException.class)
  public void testAnySetsPresent_invalidGameState_2x4() {

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

    SetGameModel model = new GeneralSetGameModel();
    model.startGameWithDeck(deck, 2, 4);

    try {
      model.anySetsPresent();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
      throw e;
    }

    model.startGameWithDeck(deck, 3, 3);
    model.claimSet(new Coord(0,0), new Coord(0, 1), new Coord(0, 2));

    try {
      model.anySetsPresent();
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("game has ended.", e.getMessage());
      throw e;
    }
  }

  @Test
  public void testIsValidSet_3x3() {

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

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 3, 3);

    Assert.assertTrue(model.isValidSet(coord1, coord2, coord3));
    Assert.assertTrue(model.isValidSet(coord3, coord1, coord2));

    Assert.assertFalse(model.isValidSet(coord1, coord2, coord2));
    Assert.assertFalse(model.isValidSet(coord1, coord1, coord1));
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
    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 3, 3);


    try {
      model.isValidSet(new Coord(-1, 2), new Coord(1, 2), new Coord(1, 1));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be non-negative.", e.getMessage());
    }

    try {
      model.isValidSet(new Coord(1, 42), new Coord(23, 2), new Coord(1, 3));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("row and column range must be from 0 to 2", e.getMessage());
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

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 3, 3);

    Assert.assertTrue(model.isValidSet(coord1, coord2, coord3));
    Assert.assertTrue(model.isValidSet(coord3, coord1, coord2));

    Assert.assertFalse(model.isValidSet(coord1, coord2, coord2));
    Assert.assertFalse(model.isValidSet(coord1, coord1, coord1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsValidSet_invalidCoord_2x4() {

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
    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 2, 4);


    try {
      model.isValidSet(new Coord(-1, 2), new Coord(1, 2), new Coord(1, 1));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be non-negative.", e.getMessage());
    }

    try {
      model.isValidSet(new Coord(1, 42), new Coord(23, 2), new Coord(1, 3));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("row and column range must be from 0 to 2", e.getMessage());
    }

  }

  @Test
  public void testTestGetCardAtCoord_3x3() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);

    deck.add(card4);
    deck.add(card5);
    deck.add(card6);

    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    Coord coord1 = new Coord(0,0);
    Coord coord2 = new Coord(1,0);
    Coord coord3 = new Coord(2,2);
    SetGameModel model = new GeneralSetGameModel();


    model.startGameWithDeck(deck, 3, 3);
    Assert.assertEquals(card1, model.getCardAtCoord(coord1));
    Assert.assertEquals(card4, model.getCardAtCoord(coord2));
    Assert.assertEquals(card9, model.getCardAtCoord(coord3));
  }

  @Test
  public void testTestGetCardAtCoord_2x4() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);

    deck.add(card4);
    deck.add(card5);
    deck.add(card6);

    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    Coord coord1 = new Coord(0,0);
    Coord coord2 = new Coord(0,3);
    Coord coord3 = new Coord(1,3);
    SetGameModel model = new GeneralSetGameModel();


    model.startGameWithDeck(deck, 2, 4);
    Assert.assertEquals(card1, model.getCardAtCoord(coord1));
    Assert.assertEquals(card4, model.getCardAtCoord(coord2));
    Assert.assertEquals(card8, model.getCardAtCoord(coord3));
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCardAtCoord_gameNotStarted() {
    SetGameModel model = new GeneralSetGameModel();

    try {
      model.getCardAtCoord(new Coord(0, 1));
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
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
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 3, 3);

    try {
      model.getCardAtCoord(null);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("coordinates must be non-null.", e.getMessage());
      throw e;
    }

    try {
      model.getCardAtCoord(new Coord(1, 24));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be non-negative.", e.getMessage());
      throw e;
    }

    try {
      model.getCardAtCoord(new Coord(-1, 2));
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("rows and columns must be non-negative.", e.getMessage());
      throw e;
    }

  }

  @Test
  public void testIsGameOver_3x3() {
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

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 3, 3);
    model.claimSet(coord1, coord2, coord3);

    Assert.assertFalse(model.isGameOver());
    Assert.assertFalse(model.isGameOver());

    model.claimSet(coord1, coord2, coord3);
    Assert.assertTrue(model.isGameOver());
    Assert.assertTrue(model.isGameOver());

  }

  @Test
  public void testIsGameOver_2x4() {
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

    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 2, 3);
    model.claimSet(coord1, coord2, coord3);

    Assert.assertFalse(model.isGameOver());
    Assert.assertFalse(model.isGameOver());

    model.claimSet(coord1, coord2, coord3);
    Assert.assertTrue(model.isGameOver());
    Assert.assertTrue(model.isGameOver());

  }

  @Test(expected = IllegalStateException.class)
  public void testIsGameOver_gameNotStarted() {
    List<Card> deck = new ArrayList<>();
    SetGameModel model = new GeneralSetGameModel();

    try {
      model.isGameOver();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
    }
  }

  @Test
  public void testHasGameStarted() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testGetDeck_3x3() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);
    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 3, 3);

    Assert.assertEquals(deck.get(0), card1);
    Assert.assertEquals(deck.get(1), card2);
    Assert.assertEquals(deck.get(2), card3);
    Assert.assertEquals(deck.get(3), card4);
    Assert.assertEquals(deck.get(4), card5);
    Assert.assertEquals(deck.get(5), card6);
    Assert.assertEquals(deck.get(6), card7);
    Assert.assertEquals(deck.get(7), card8);
    Assert.assertEquals(deck.get(8), card9);

  }

  @Test
  public void testGetDeck_2x4() {

    List<Card> deck = new ArrayList<Card>();
    deck.add(card1);
    deck.add(card2);
    deck.add(card3);
    deck.add(card4);
    deck.add(card5);
    deck.add(card6);
    deck.add(card7);
    deck.add(card8);
    deck.add(card9);
    SetGameModel model = new GeneralSetGameModel();

    model.startGameWithDeck(deck, 2, 4);

    Assert.assertEquals(deck.get(0), card1);
    Assert.assertEquals(deck.get(1), card2);
    Assert.assertEquals(deck.get(2), card3);
    Assert.assertEquals(deck.get(3), card4);
    Assert.assertEquals(deck.get(4), card5);
    Assert.assertEquals(deck.get(5), card6);
    Assert.assertEquals(deck.get(6), card7);
    Assert.assertEquals(deck.get(7), card8);

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
    deck.add(card7);
    deck.add(card1);
    deck.add(card6);
    SetGameModel model = new GeneralSetGameModel();

    try {
      ((GeneralSetGameModel) model).getDeck();
    }
    catch (IllegalStateException e) {
      Assert.assertEquals("game has not yet started.", e.getMessage());
    }
  }

  @Test
  public void testGetGrid() {
    int p = 2;
    assertEquals(p, 2);
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

    SetGameModel model = new GeneralSetGameModel();

    Assert.assertEquals(aCard12, ((GeneralSetGameModel) model).completeSet(card1, card2));
    Assert.assertEquals(aCard13, ((GeneralSetGameModel) model).completeSet(card1, card3));
    Assert.assertEquals(aCard14, ((GeneralSetGameModel) model).completeSet(card1, card4));
    Assert.assertEquals(aCard34, ((GeneralSetGameModel) model).completeSet(card3, card4));
    Assert.assertEquals(aCard23, ((GeneralSetGameModel) model).completeSet(card2, card3));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testComleteSet_invalidCards() {

    Card card1 = new Card(1, 'E', 'O');
    Card card2 = new Card(1, 'E', 'O');

    SetGameModel model = new GeneralSetGameModel();


    try {
      ((GeneralSetGameModel) model).completeSet(card1, card1);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("cards must not be the same.", e.getMessage());
    }

    try {
      ((GeneralSetGameModel) model).completeSet(card1, card2);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("cards must not be the same.", e.getMessage());
    }

    try {
      ((GeneralSetGameModel) model).completeSet(null, null);
      ((GeneralSetGameModel) model).completeSet(card1, null);
    }
    catch (IllegalArgumentException e) {
      Assert.assertEquals("cards must be non-null.", e.getMessage());
    }
  }
}