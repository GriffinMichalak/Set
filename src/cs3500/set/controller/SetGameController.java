package cs3500.set.controller;

import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.view.SetGameView;

/**
 * The interface for a controller for the game of Set.
 */
public interface SetGameController {

  /**
   * Allows a user to play a game of set in the console.
   * @throws IllegalStateException if there is no input left.
   */
  void playGame() throws IllegalStateException;

  /**
   * Gets the class's set game model.
   * @return the SetGameModel of the class.
   */
  SetGameModel<?> getModel();

  /**
   * Gets the class's set game view.
   * @return the SetGameView of the class.
   */
  SetGameView getSetGameView();

  /**
   * Gets the class's set readable.
   * @return the Readable Object of the class.
   */
  public Readable getReadable();

}
