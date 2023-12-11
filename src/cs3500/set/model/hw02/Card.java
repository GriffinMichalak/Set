package cs3500.set.model.hw02;

import java.util.Objects;

/**
 * This class models a Card. A Card has a count, a filling, and a shape.
 */

public class Card {

  private enum Filling {
    E, S, F;
  }

  private enum Shape {
    O, Q, D;
  }

  private final int count;
  private final Filling filling;
  private final Shape shape;

  /**
   * the Card constructor for this class.
   * @param count the number of items on a card, either 1, 2, or 3.
   * @param filling the filling type of the item on a card, either 'E', 'S', or 'F'.
   * @param shape the shape type of the item on a card, either 'O', 'Q', or 'D'.
   * @throws IllegalArgumentException if any input
   *         other than the above choices for each argument is inputted.
   */

  public Card(int count, char filling, char shape) throws IllegalArgumentException {
    if (!(count == 1 || count == 2 || count == 3)) {
      throw new IllegalArgumentException("count value must be either 1, 2, or 3");
    }
    try {
      this.filling = Filling.valueOf(String.valueOf(filling));
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("filling value must be either 'E', 'S', or 'F'");
    }
    try {
      this.shape = Shape.valueOf(String.valueOf(shape));
    }
    catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("shape value must be either 'O', 'Q', or 'D'");
    }

    this.count = count;
  }

  /**
   * outputs the card with the number, filling, and shape as a string.
   * @return cards of the form number filling string (e.g. "1ED").
   */

  @Override
  public String toString() {
    return ("" + count + filling + shape);
  }

  /**
   * outputs the count of a card.
   * @return the number of figures on a card.
   */

  public int getCount() {
    return count;
  }

  /**
   * outputs the filling type of the card.
   * @return the filling of the figures on a card.
   */

  public char getFilling() {
    return filling.name().charAt(0);
  }

  /**
   * outputs the shape type of the card.
   * @return the shape of the figures on a card.
   */

  public char getShape() {
    return shape.name().charAt(0);
  }

  /**
   * compares a card to another Object by casting it as a Card object.
   * @param o the Object to compare this Card to.
   * @return whether the Cards are equal.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Card aCard = (Card) o;
    return (this.count == aCard.count
            && this.filling == aCard.filling
            && this.shape == aCard.shape);
  }

  /**
   * returns the hashcode value of this Card.
   * @return the hashcode value of this Card, as an int.
   */

  @Override
  public int hashCode() {
    return Objects.hash(count, filling, shape);
  }

}
