package cs3500.set;

import java.io.InputStreamReader;

import cs3500.set.controller.SetGameController;
import cs3500.set.controller.SetGameControllerImpl;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

/**
 * Runs a game of Set.
 */
public final class SetGame {

  /**
   * The method used to run a game of Set.
   * @param args command line arguments. 
   */
  public static void main(String[] args) {
    SetGameModel model = new GeneralSetGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();
  }
}