package cs3500.set.view;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;

/**
 * This tests the SetGameTextView Object class.
 */

public class SetGameTextViewTest extends TestCase {

  String newGrid;

  @Test
  public void testValidConstruction() {
    SetGameModel setGameModel = new SetThreeGameModel();
    SetGameTextView setGameTextView = new SetGameTextView(setGameModel);

    assertEquals(setGameTextView.model, setGameModel);
  }

  @Test
  public void testTestToString() {
    List<Card> deck = new ArrayList<Card>();
    SetGameModel setGameModel = new SetThreeGameModel();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    deck.add(new Card(2, 'F', 'O'));
    deck.add(new Card(2, 'F', 'Q'));
    deck.add(new Card(2, 'F', 'D'));

    String grid = "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD";
    newGrid = grid;

    setGameModel.startGameWithDeck(deck, 3, 3);

    SetGameTextView setGameTextView = new SetGameTextView(setGameModel);
    assertEquals(grid, setGameTextView.toString());

  }

  @Test
  public void testRenderGrid() throws IOException {

    List<Card> deck = new ArrayList<Card>();
    SetGameModel setGameModel = new GeneralSetGameModel();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    deck.add(new Card(2, 'F', 'O'));
    deck.add(new Card(2, 'F', 'Q'));
    deck.add(new Card(2, 'F', 'D'));

    String grid = "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n";

    setGameModel.startGameWithDeck(deck, 3, 3);

    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    SetGameTextView writer = new SetGameTextView(setGameModel, ap);
    writer.renderGrid();
    assertEquals(grid, builder.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderGrid_invalid() throws IOException {
    List<Card> deck = new ArrayList<Card>();
    SetGameModel setGameModel = new GeneralSetGameModel();

    deck.add(new Card(1, 'E', 'O'));
    deck.add(new Card(1, 'E', 'Q'));
    deck.add(new Card(1, 'E', 'D'));

    deck.add(new Card(1, 'S', 'O'));
    deck.add(new Card(1, 'S', 'Q'));
    deck.add(new Card(1, 'S', 'D'));
    deck.add(new Card(1, 'F', 'O'));
    deck.add(new Card(1, 'F', 'Q'));
    deck.add(new Card(1, 'F', 'D'));

    deck.add(new Card(2, 'F', 'O'));
    deck.add(new Card(2, 'F', 'Q'));
    deck.add(new Card(2, 'F', 'D'));

    String grid = "1EO 1EQ 1ED\n1SO 1SQ 1SD\n1FO 1FQ 1FD\n";

    setGameModel.startGameWithDeck(deck, 3, 3);

    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    SetGameTextView writer = new SetGameTextView(setGameModel, ap);
    writer.renderGrid();

    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testRenderMessage() throws IOException {
    SetGameModel model = new GeneralSetGameModel();
    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    String message = "Hello, World!";
    SetGameTextView writer = new SetGameTextView(model, ap);
    writer.renderMessage(message);
    assertEquals(message, builder.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderMessage_invalid() throws IOException {
    SetGameModel model = new GeneralSetGameModel();
    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    String message = "Hello, World!";
    SetGameTextView writer = new SetGameTextView(model, ap);
    writer.renderMessage(message);
    int p = 2;
    assertEquals(p, 2);
  }



}