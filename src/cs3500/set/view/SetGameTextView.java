package cs3500.set.view;

import java.io.IOException;

import cs3500.set.model.hw02.SetGameModel;

/**
 * This class models a display of a Set game.
 */

public class SetGameTextView implements SetGameView {

  SetGameModel model;
  Appendable appendable;


  /**
   * The constructor for this class for the user to view and play a game of Set.
   * @param model the SetGameModel to which information will be stored.
   */
  public SetGameTextView(SetGameModel model) {
    this.model = model;
  }

  /**
   * Another constructor for this class.
   * @param model a SetGameModel object.
   * @param dest an Appendable object this view uses as its destination.
   * @throws IllegalArgumentException if any input is null.
   */

  public SetGameTextView(SetGameModel<?> model, Appendable dest) throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("model must be non-null.");
    }
    if (dest == null) {
      throw new IllegalArgumentException("Appendable must be non-null.");
    }

    this.model = model;
    this.appendable = dest;

  }

  /**
   * Produces a textual view of the grid of cards of the current game.
   * Each card is displayed as initials of all of its attributes.
   * For instance, if a card has a single red oval, the card is displayed as 1RO.
   * If a card has three squiggly purple shapes, the card is displayed as 3PS.
   * @return representation of the current state of the game
   */

  @Override
  public String toString() {
    String display = "";
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth() - 1; j++) {
        display = display + model.getCardAtCoord(i, j) + " ";
      }
      display = display + model.getCardAtCoord(i, model.getWidth() - 1);
      if (i < model.getHeight() - 1) {
        display = display + "\n";
      }
    }

    return display;
  }

  /**
   * Renders the grid to the data output in the implementation.
   * The format of the grid is exactly that of the toString method.
   *
   * @throws IOException if the transmission of the grid to the data output fails
   */
  @Override
  public void renderGrid() throws IOException {
    try {
      appendable.append(this.toString() + System.lineSeparator());
    }
    catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Renders a given message to the data output in the implementation.
   *
   * @param message the message to be printed
   * @throws IOException if the transmission of the message to the data output fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message);
    }
    catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

}
