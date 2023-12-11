package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;

/**
 * This class models a version of Set called SetThree, with n rows and n columns.
 */

public class GeneralSetGameModel extends AbstractSetGameModel {

  public boolean hasStarted;
  public boolean hasEnded;
  public int score;
  private List<Card> deck;
  public int deckIndex;

  /**
   * the constructor for the GeneralSetGameModel, assigning
   * values of grid, deck, hasStarted, hasEnded, score, and deckIndex.
   */

  public GeneralSetGameModel(int row, int col) {
    this.grid = new Card[row][col];
    deck = new ArrayList<Card>();
    this.row = row;
    this.col = col;
  }

  /**
   * the constructor for the GeneralSetGameModel, assigning
   * values of grid, deck, hasStarted, hasEnded, score, and deckIndex.
   */

  public GeneralSetGameModel() {
    super();
  }

  /**
   * If the cards at the specified coordinates form a valid set, claim it,
   * and replace those cards with new cards from the deck, if possible.
   *
   * @param coord1 the coordinates of the first card
   * @param coord2 the coordinates of the second card
   * @param coord3 the coordinates of the third card
   * @throws IllegalArgumentException if any of the coordinates
   *                                  are invalid for the particular
   *                                  implementation of Set OR the cards at
   *                                  those coordinates do not form a set
   * @throws IllegalStateException    if the game has not started or the game has already ended
   */

  @Override
  public void claimSet(Coord coord1, Coord coord2, Coord coord3) {

    super.claimSet(coord1, coord2, coord3);

    while (!super.anySetsPresent()) {
      if (27 - deckIndex > this.getWidth()) {
        Card[][] newGrid = new Card[row + 1][col];
        for (int i = 0; i < row; i++) {
          for (int j = 0; j < col; j++) {
            newGrid[i][j] = grid[i][j];
          }
        }

        for (int i = 0; i < row + 1; i++) {
          newGrid[row][i] = deck.get(deckIndex);
          deckIndex++;
        }
        this.row++;
        this.grid = newGrid;
      } else {
        hasEnded = true;
        throw new IllegalStateException("No cards left to deal.");
      }
    }

  }

  /**
   * determines if there are any sets present in the current grid.
   *
   * @return if there are any sets available.
   * @throws IllegalStateException if the game has not yet started
   *                               or if the game has ended.
   */

  @Override
  public boolean anySetsPresent() throws IllegalStateException {
    if (!hasStarted) {
      throw new IllegalStateException("game has not yet started.");
    } else {
      int size = this.col * this.row;
      for (int i = 0; i < size; i++) {
        Card card1 = deck.get(i);
        for (int j = i + 1; j < size; j++) {
          Card card2 = deck.get(j);
          Card card3 = this.completeSet(card1, card2);
          for (int k = j + 1; k < size; k++) {
            if (k == i || k == j) {
              continue;
            }
            if (deck.get(k).equals(card3)) {
              return true;
            }
          }
        }
      }
    }
    if (hasEnded) {
      throw new IllegalStateException("game has ended.");
    }
    return false;
  }

  /**
   * Return true when the game is over. That happens when
   * <ul>
   *   <li>there no sets on the current board OR</li>
   *   <li>there are not enough cards remaining in the deck to deal after claiming a set</li>
   * </ul>
   *
   * @return whether the game is over
   */

  @Override
  public boolean isGameOver() {
    return (!this.anySetsPresent() && this.deckIndex < this.getWidth());
  }

}

