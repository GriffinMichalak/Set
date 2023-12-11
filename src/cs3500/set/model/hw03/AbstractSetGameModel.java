package cs3500.set.model.hw03;

import java.util.ArrayList;
import java.util.List;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;

/**
 * This is a class representing AbstractGameModel.
 */

public abstract class AbstractSetGameModel implements SetGameModel<Card> {
  protected Card[][] grid;
  boolean hasStarted;
  boolean hasEnded;
  private int score;
  private List<Card> deck;
  private int deckIndex;
  protected int row;
  protected int col;

  /**
   * The constructor for this class.
   */
  public AbstractSetGameModel() {
    deck = new ArrayList<Card>();
    hasStarted = false;
  }

  /**
   * Begins the game using the cards given by the deck creates a grid specified
   * by the height and width parameters.
   * <p>
   * Specifically, the model deals the cards onto the board from left to right and top to bottom,
   * filling the grid.
   * </p>
   *
   * @param deck   the list of cards in the order they will be played
   * @param height the height of the board for this game
   * @param width  the width of the board for this game
   * @throws IllegalArgumentException if the deck does not have enough cards to deal to the grid
   *                                  OR the deck is null
   *                                  OR the width and height are invalid
   *                                  for a particular implementation of Set
   */

  @Override
  public void startGameWithDeck(List<Card> deck, int height, int width)
          throws IllegalStateException, IllegalArgumentException {
    this.deck = deck;
    if (deck.size() < this.row * this.col || deckIndex >= deck.size()) {
      throw new IllegalStateException("deck does not have enough cards to deal.");
    }
    if (height < 0) {
      throw new IllegalArgumentException("Invalid height/width.");
    }
    if (width < 0) {
      throw new IllegalArgumentException("Invalid height/width.");
    }
    if (width * height < 3) {
      throw new IllegalArgumentException("At least three cards needed.");
    }

    this.grid = new Card[height][width];

    deckIndex = 0;
    score = 0;
    hasEnded = false;

    this.row = height;
    this.col = width;

    grid = new Card[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        grid[i][j] = deck.get(deckIndex);
        deckIndex++;
      }
    }
    hasStarted = true;
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
    if (!hasStarted) {
      throw new IllegalStateException("game has not yet started.");
    }
    if (hasEnded) {
      throw new IllegalStateException("game has ended.");
    }
    if (coord1 == null || coord2 == null || coord3 == null) {
      throw new IllegalArgumentException("coordinates must be non-null.");
    }
    if (coord1.row < 0 || coord1.row > this.row
            || coord2.row < 0 || coord2.row > this.row
            || coord2.row < 0 || coord2.row > this.row) {
      throw new IllegalArgumentException("row must not be greater than " + this.row + ".");
    }
    if (coord1.col < 0 || coord1.col > this.row
            || coord2.col < 0 || coord2.col > this.row
            || coord2.col < 0 || coord2.col > this.row) {
      throw new IllegalArgumentException("row must not be greater than 3.");
    }
    else if (!isValidSet(coord1, coord2, coord3)) {
      throw new IllegalArgumentException("coordinates must form a set.");
    }

    else {
      score++;
      if (deckIndex > deck.size() - 3) {
        hasEnded = true;
        return;
      }
      grid[coord1.row][coord1.col] = deck.get(deckIndex);
      deckIndex++;
      grid[coord2.row][coord2.col] = deck.get(deckIndex);
      deckIndex++;
      grid[coord3.row][coord3.col] = deck.get(deckIndex);
      deckIndex++;

    }

  }

  /**
   * Return the width of the grid for this game of Set.
   *
   * @return the width of the grid
   * @throws IllegalStateException if the game has not yet started
   */

  public int getWidth() throws IllegalStateException {
    if (!hasStarted) {
      throw new IllegalStateException("game has not yet started.");
    }
    return this.col;
  }

  /**
   * Return the height of the grid for this game of Set.
   *
   * @return the height of the grid
   * @throws IllegalStateException if the game has not yet started
   */
  public int getHeight() throws IllegalStateException {
    if (!hasStarted) {
      throw new IllegalStateException("game has not yet started.");
    }
    return this.row;
  }

  /**
   * Returns how many sets the player has collected so far.
   *
   * @return the player's current score
   * @throws IllegalStateException if the game has not yet started
   */

  public int getScore() throws IllegalStateException {
    if (!hasStarted) {
      throw new IllegalStateException("game has not yet started.");
    }
    return score;
  }

  /**
   * determines if there are any sets present in the current grid.
   * @return if there are any sets available.
   * @throws IllegalStateException if the game has not yet started
   *                               or if the game has ended.
   */

  @Override
  public boolean anySetsPresent() throws IllegalStateException {

    if (!hasStarted) {
      throw new IllegalStateException("The game is not active.");
    }
    List<Coord> possibleCoords = new ArrayList<>();
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        possibleCoords.add(new Coord(i, j));
      }

    }

    for (int i = 0; i < possibleCoords.size(); i++) {
      for (int j = i + 1; j < possibleCoords.size(); j++) {
        for (int k = j + 1; k < possibleCoords.size(); k++) {
          if (isValidSet(possibleCoords.get(i), possibleCoords.get(j), possibleCoords.get(k))) {
            return true;
          }
        }
      }
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
    return (hasEnded || !this.anySetsPresent());
  }


  /**
   * Returns true if cards at the specified coordinates form a valid set.
   *
   * @param coord1 the coordinates of the first card
   * @param coord2 the coordinates of the second card
   * @param coord3 the coordinates of the third card
   * @return whether the cards form a valid set
   * @throws IllegalArgumentException if any of the coordinates are invalid for the given
   *                                  implementation of Set
   */
  @Override
  public boolean isValidSet(Coord coord1, Coord coord2, Coord coord3)
          throws IllegalArgumentException {

    if (coord1 == null || coord2 == null || coord3 == null) {
      throw new IllegalArgumentException("coordinates must be non-null.");
    }

    if (coord1.row >= this.getHeight()
            || coord2.row >= this.getHeight() || coord3.row >= this.getHeight()) {
      throw new IllegalArgumentException("row and column range must be from 0 to 2");
    }
    if (coord1.col >= this.getWidth()
            || coord2.col >= this.getWidth() || coord3.col >= this.getWidth()) {
      throw new IllegalArgumentException("row and column range must be from 0 to 2");
    }

    Card card1 = getCardAtCoord(coord1);
    Card card2 = getCardAtCoord(coord2);
    Card card3 = getCardAtCoord(coord3);

    if (card1.equals(card2) || card1.equals(card3) || card2.equals(card3)) {
      return false;
    }

    //test for count
    if (card1.getCount() == card2.getCount() && card3.getCount() != card1.getCount()) {
      return false;
    }
    if (card1.getCount() == card3.getCount() && card2.getCount() != card1.getCount()) {
      return false;
    }
    if (card2.getCount() == card3.getCount() && card1.getCount() != card2.getCount()) {
      return false;
    }

    //test for filling
    if (card1.getFilling() == card2.getFilling() && card3.getFilling() != card1.getFilling()) {
      return false;
    }
    if (card1.getFilling() == card3.getFilling() && card2.getFilling() != card1.getFilling()) {
      return false;
    }
    if (card2.getFilling() == card3.getFilling() && card1.getFilling() != card2.getFilling()) {
      return false;
    }

    //test for shape
    if (card1.getShape() == card2.getShape() && card3.getShape() != card1.getShape()) {
      return false;
    }
    if (card1.getShape() == card3.getShape() && card2.getShape() != card1.getShape()) {
      return false;
    }
    if (card2.getShape() == card3.getShape() && card1.getShape() != card2.getShape()) {
      return false;
    }

    return !(card2.getShape() == card3.getShape() && card1.getShape() != card2.getShape());

  }


  /**
   * Returns the card at the specified row and column coordinates.
   *
   * @param row the row of the desired card
   * @param col the column of the desired card
   * @return the card at the specified coordinates
   * @throws IllegalStateException if the game has not yet started
   */

  @Override
  public Card getCardAtCoord(int row, int col) {
    if (!hasStarted) {
      throw new IllegalStateException("game has not yet started.");
    }
    return grid[row][col];
  }

  /**
   * Returns the card at the specified coordinates.
   *
   * @param coord the coordinates of the desired card
   * @return the card at the specified coordinates
   * @throws IllegalStateException if the game has not yet started
   */

  @Override
  public Card getCardAtCoord(Coord coord) {
    if (coord == null) {
      throw new IllegalArgumentException("coordinates must be non-null.");
    }
    if (coord.row > this.row - 1) {
      throw new IllegalArgumentException("rows and columns must be non-negative.");
    }
    if (!hasStarted) {
      throw new IllegalStateException("game has not yet started.");
    }
    return grid[coord.row][coord.col];
  }

  /**
   * Return true when the game has started, and false otherwise.
   *
   * @return whether the game has started.
   */
  public boolean hasGameStarted() {
    return hasStarted;
  }

  /**
   * Craft and return a new complete deck for the implemented game.
   * This deck must contain every possible card possible in the implemented game
   * exactly once.
   * There is no required/sorted order for the cards in this deck.
   *
   * @return the complete deck of cards
   *
   */

  @Override
  public List getCompleteDeck() {
    deck = new ArrayList<>();
    int[] listCount = {1, 2, 3};
    char[] listFilling = {'E', 'S', 'F'};
    char[] listShape = {'O', 'Q', 'D'};

    for (int i = 0; i < listCount.length; i++) {
      for (int j = 0; j < listFilling.length; j++) {
        for (int k = 0; k < listShape.length; k++) {
          deck.add(new Card(listCount[i], listFilling[j], listShape[k]));
        }
      }
    }

    //return List.of(deck.toArray());
    return deck;
  }

  /**
   * outputs the current deck in the Set game.
   * @return the current deck at play in the Set game.
   * @throws IllegalStateException if game has not started
   */

  public List getDeck() throws IllegalStateException {
    if (!hasStarted) {
      throw new IllegalStateException("game has not yet started.");
    }
    return deck;
  }

  /**
   * returns the current grid in the game.
   * @return the current grid in the game of Set.
   */

  public Card[][] getGrid() {
    return grid;
  }

  /**
   * returns the corresponding Card that forms a set with the two given cards.
   * @param card1 the first Card to compare.
   * @param card2 the second Card to compare.
   * @return the Card that when combined with the others forms a complete set.
   * @throws IllegalArgumentException if two of the same cards are given.
   */

  public Card completeSet(Card card1, Card card2) throws IllegalArgumentException {
    if (card1 == null || card2 == null) {
      throw new IllegalArgumentException("cards must be non-null.");
    }
    if (card1.equals(card2)) {
      throw new IllegalArgumentException("cards must not be the same.");
    }

    int rightCount = 1;
    char rightFilling = 'E';
    char rightShape = 'D';

    ArrayList<Integer> countOptions = new ArrayList<Integer>();
    countOptions.add(1);
    countOptions.add(2);
    countOptions.add(3);

    ArrayList<Character> fillingOptions = new ArrayList<Character>();
    fillingOptions.add('E');
    fillingOptions.add('S');
    fillingOptions.add('F');

    ArrayList<Character> shapeOptions = new ArrayList<Character>();
    shapeOptions.add('O');
    shapeOptions.add('Q');
    shapeOptions.add('D');

    if (card1.getCount() == card2.getCount()) {
      rightCount = card1.getCount();
    }
    if (card1.getCount() != card2.getCount()) {
      countOptions.remove(Integer.valueOf(card1.getCount()));
      countOptions.remove(Integer.valueOf(card2.getCount()));
      rightCount = countOptions.get(0);
    }

    if (card1.getFilling() == card2.getFilling()) {
      rightFilling = card1.getFilling();
    }
    if (card1.getFilling() != card2.getFilling()) {
      fillingOptions.remove(Character.valueOf(card1.getFilling()));
      fillingOptions.remove(Character.valueOf(card2.getFilling()));
      rightFilling = fillingOptions.get(0);
    }

    if (card1.getShape() == card2.getShape()) {
      rightShape = card1.getShape();
    }
    if (card1.getShape() != card2.getShape()) {
      shapeOptions.remove(Character.valueOf(card1.getShape()));
      shapeOptions.remove(Character.valueOf(card2.getShape()));
      rightShape = shapeOptions.get(0);
    }

    Card card3 = new Card(rightCount, rightFilling, rightShape);
    return card3;
  }


}
