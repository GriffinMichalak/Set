package cs3500.set.model.hw02;

import java.io.IOException;
import java.io.InputStreamReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

/**
 * A classs to run a game of Set with the user based on user input.
 */

public class RunSetGame {

  /**
   * The main method for this program to run a game of Set.
   * @param args an array.
   * @throws IOException if invalid or null input.
   */
  public static void main(String[] args) throws IOException {
    SetGameModel model = new SetThreeGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();
  }
}
