package cs3500.set.model.hw02;

import java.util.List;

import cs3500.set.model.hw03.AbstractSetGameModel;

/**
 * This class models a version of Set called SetThree, with 3 rows and 3 columns.
 */

public class SetThreeGameModel extends AbstractSetGameModel {

  public Card[][] grid;
  public boolean hasStarted;
  public boolean hasEnded;
  public int score;
  public int deckIndex;
  private int height;
  private int width;

  /**
   * the constructor for the SetThreeGameModel, assigning
   * values of grid, deck, hasStarted, hasEnded, score, and deckIndex.
   */

  public SetThreeGameModel() {
    super();
  }

  @Override
  public void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalStateException, IllegalArgumentException {
    List<Card> d = deck;
    if (d == null) {
      throw new IllegalArgumentException("deck must be defined.");
    }
    if (height != width) {
      throw new IllegalArgumentException("height and width must be the same.");
    }
    super.startGameWithDeck(d, height, width);
  }


}
