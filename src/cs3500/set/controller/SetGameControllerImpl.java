package cs3500.set.controller;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import cs3500.set.model.hw02.Card;
import cs3500.set.model.hw02.Coord;
import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.view.SetGameView;

/**
 * Creates an implementation for the controller for the game of Set.
 */
public class SetGameControllerImpl implements SetGameController {

  private Readable readable;
  private SetGameModel model;
  private SetGameView setGameView;

  /**
   * Plays a new game of set.
   * @param model the SetGameModel object the class is working with.
   * @param setGameView a view for the game of Set.
   * @param in the Readable object to help display to console.
   * @throws IllegalArgumentException if inputs are null.
   */
  public SetGameControllerImpl(SetGameModel<?> model,
                               SetGameView setGameView,
                               Readable in) throws IllegalArgumentException {
    if (model == null || setGameView == null || in == null) {
      throw new IllegalArgumentException("SetGameModel/SetGameView/Reader must be non-null.");
    }

    this.model = model;
    this.setGameView = setGameView;
    this.readable = in;
  }

  /**
   * Gets the class's set game model.
   * @return the SetGameModel of the class.
   */
  public SetGameModel<?> getModel() {
    return this.model;
  }

  /**
   * Gets the class's set game view.
   * @return the SetGameView of the class.
   */
  public SetGameView getSetGameView() {
    return this.setGameView;
  }

  /**
   * Gets the class's set readable.
   * @return the Readable Object of the class.
   */
  public Readable getReadable() {
    return this.readable;
  }

  /**
   * Allows a user to play a game of set in the console.
   * @throws IllegalStateException if there is no input left.
   */
  public void playGame() throws IllegalStateException {
    Scanner scan = new Scanner(this.readable);
    int height = 0;
    int width = 0;
    boolean allValid = false;
    boolean hasEnded;

    while (!allValid) {
      //height --------------
      if (!scan.hasNext()) {
        throw new IllegalStateException("no input left");
      }
      try {
        scan.hasNext();
      }
      catch (IllegalStateException e) {
        writeMessage("Game quit!" + System.lineSeparator());
        writeMessage("Score: 0");
        return;
      }

      String h = scan.next();
      String newInput;
      newInput = ensureValidInput(h);
      if (newInput.equalsIgnoreCase("Q")) {
        writeMessage("Game quit!" + System.lineSeparator());
        writeMessage("Score: 0");
        return;
      }
      height = Integer.parseInt(newInput);

      while (height < 1) {
        writeMessage("Invalid height/width. Try again." + System.lineSeparator());
        if (!scan.hasNext()) {
          throw new IllegalStateException("no input left");
        }
        try {
          scan.hasNext();
        }
        catch (IllegalStateException e) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("Score: 0");
          return;
        }
        h = scan.next();
        newInput = ensureValidInput(h);
        if (newInput.equalsIgnoreCase("Q")) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("Score: 0");
          return;
        }
        height = Integer.parseInt(newInput);
      }

      //width -----------------
      if (!scan.hasNext()) {
        throw new IllegalStateException("no input left");
      }
      try {
        scan.hasNext();
      }
      catch (IllegalStateException e) {
        writeMessage("Game quit!" + System.lineSeparator());
        writeMessage("Score: 0");
        return;
      }
      String w = scan.next();
      newInput = ensureValidInput(w);
      if (newInput.equalsIgnoreCase("Q")) {
        writeMessage("Game quit!" + System.lineSeparator());
        writeMessage("Score: 0");
        return;
      }
      width = Integer.parseInt(newInput);

      while (width < 1) {
        writeMessage("Invalid height/width. Try again." + System.lineSeparator());
        if (!scan.hasNext()) {
          throw new IllegalStateException("no input left");
        }
        try {
          scan.hasNext();
        }
        catch (IllegalStateException e) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("Score: 0");
          return;
        }
        w = scan.next();
        newInput = ensureValidInput(w);
        if (newInput.equalsIgnoreCase("Q")) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("Score: 0");
          return;
        }
        width = Integer.parseInt(newInput);
      }

      List<Card> deck = model.getCompleteDeck();
      try {
        model.startGameWithDeck(deck, height, width);
      }
      catch (IllegalArgumentException e) {
        writeMessage("Invalid height/width. Try again." + System.lineSeparator());
        continue;
      }

      //both ----------------
      if ((width * height < 3 || width * height > 27)) {
        writeMessage("Invalid height/width. Try again." + System.lineSeparator());
      }
      else {
        allValid = true;
      }
    }

    List<Card> deck = model.getCompleteDeck();

    model.startGameWithDeck(deck, height, width);

    String r1;
    String c1;
    String r2;
    String c2;
    String r3;
    String c3;

    int row1;
    int col1;
    int row2;
    int col2;
    int row3;
    int col3;


    while (!model.isGameOver()) {
      row1 = 1;
      col1 = 1;
      row2 = 1;
      col2 = 1;
      row3 = 1;
      col3 = 1;
      String newInput;
      this.getBoard();
      writeMessage("Score: " + model.getScore() + System.lineSeparator());
      allValid = false;
      while (!allValid) {
        //r1 --------------
        if (!scan.hasNext()) {
          throw new IllegalStateException("no input left");
        }
        try {
          scan.hasNext();
        }
        catch (IllegalStateException e) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        r1 = scan.next();
        newInput = ensureValidInput(r1);
        //r1 = getValidInput(r1);
        if (newInput.equalsIgnoreCase("Q")) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        row1 = Integer.parseInt(newInput);

        while (row1 < 1 || row1 > height) {
          writeMessage("Invalid height/width. Try again." + System.lineSeparator());
          if (!scan.hasNext()) {
            throw new IllegalStateException("no input left");
          }
          try {
            scan.hasNext();
          }
          catch (IllegalStateException e) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          r1 = scan.next();
          newInput = ensureValidInput(r1);
          if (newInput.equalsIgnoreCase("Q")) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          row1 = Integer.parseInt(newInput);
        }

        //c1 --------------
        if (!scan.hasNext()) {
          throw new IllegalStateException("no input left");
        }
        try {
          scan.hasNext();
        }
        catch (IllegalStateException e) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        c1 = scan.next();
        newInput = ensureValidInput(c1);
        if (newInput.equalsIgnoreCase("Q")) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        col1 = Integer.parseInt(newInput);

        while (col1 < 1 || col1 > width) {
          writeMessage("Invalid height/width. Try again." + System.lineSeparator());
          if (!scan.hasNext()) {
            throw new IllegalStateException("no input left");
          }
          try {
            scan.hasNext();
          }
          catch (IllegalStateException e) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          c1 = scan.next();
          newInput = ensureValidInput(c1);
          if (newInput.equalsIgnoreCase("Q")) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          col1 = Integer.parseInt(newInput);
        }





        //r2 --------------
        if (!scan.hasNext()) {
          throw new IllegalStateException("no input left");
        }
        try {
          scan.hasNext();
        }
        catch (IllegalStateException e) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        r2 = scan.next();
        newInput = ensureValidInput(r2);
        if (newInput.equalsIgnoreCase("Q")) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        row2 = Integer.parseInt(newInput);

        while (row2 < 1 || row2 > height) {
          writeMessage("Invalid height/width. Try again." + System.lineSeparator());
          if (!scan.hasNext()) {
            throw new IllegalStateException("no input left");
          }
          try {
            scan.hasNext();
          }
          catch (IllegalStateException e) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          r2 = scan.next();
          newInput = ensureValidInput(r2);
          if (newInput.equalsIgnoreCase("Q")) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          row2 = Integer.parseInt(newInput);
        }

        //c2 --------------
        if (!scan.hasNext()) {
          throw new IllegalStateException("no input left");
        }
        try {
          scan.hasNext();
        }
        catch (IllegalStateException e) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        c2 = scan.next();
        newInput = ensureValidInput(c2);
        if (newInput.equalsIgnoreCase("Q")) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        col2 = Integer.parseInt(newInput);

        while (col2 < 1 || col2 > width) {
          writeMessage("Invalid height/width. Try again." + System.lineSeparator());
          if (!scan.hasNext()) {
            throw new IllegalStateException("no input left");
          }
          try {
            scan.hasNext();
          }
          catch (IllegalStateException e) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          c2 = scan.next();
          newInput = ensureValidInput(c2);
          if (newInput.equalsIgnoreCase("Q")) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          col2 = Integer.parseInt(newInput);
        }






        //r3 --------------
        if (!scan.hasNext()) {
          throw new IllegalStateException("no input left");
        }
        try {
          scan.hasNext();
        }
        catch (IllegalStateException e) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        r3 = scan.next();
        newInput = ensureValidInput(r3);
        if (newInput.equalsIgnoreCase("Q")) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        row3 = Integer.parseInt(newInput);

        while (row3 < 1 || row3 > height) {
          writeMessage("Invalid height/width. Try again." + System.lineSeparator());
          if (!scan.hasNext()) {
            throw new IllegalStateException("no input left");
          }
          try {
            scan.hasNext();
          }
          catch (IllegalStateException e) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          r3 = scan.next();
          newInput = ensureValidInput(r3);
          if (newInput.equalsIgnoreCase("Q")) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          row3 = Integer.parseInt(newInput);
        }

        //c3 --------------
        if (!scan.hasNext()) {
          throw new IllegalStateException("no input left");
        }
        try {
          scan.hasNext();
        }
        catch (IllegalStateException e) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        c3 = scan.next();
        newInput = ensureValidInput(c3);
        if (newInput.equalsIgnoreCase("Q")) {
          writeMessage("Game quit!" + System.lineSeparator());
          writeMessage("State of game when quit:" + System.lineSeparator());
          this.getBoard();
          writeMessage("Score: " + model.getScore() + System.lineSeparator());
          return;
        }
        col3 = Integer.parseInt(newInput);

        while (col3 < 1 || col3 > width) {
          writeMessage("Invalid height/width. Try again." + System.lineSeparator());
          if (!scan.hasNext()) {
            throw new IllegalStateException("no input left");
          }
          try {
            scan.hasNext();
          }
          catch (IllegalStateException e) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          c3 = scan.next();
          newInput = ensureValidInput(c3);
          if (newInput.equalsIgnoreCase("Q")) {
            writeMessage("Game quit!" + System.lineSeparator());
            writeMessage("State of game when quit:" + System.lineSeparator());
            this.getBoard();
            writeMessage("Score: " + model.getScore() + System.lineSeparator());
            return;
          }
          col3 = Integer.parseInt(newInput);
        }

        allValid = true;
      }
      Coord coord1 = new Coord(row1 - 1, col1 - 1);
      Coord coord2 = new Coord(row2 - 1, col2 - 1);
      Coord coord3 = new Coord(row3 - 1, col3 - 1);

      writeMessage("(" + row1 + "," + col1 + ") = "
                        + model.getCardAtCoord(coord1) + System.lineSeparator()
                 + "(" + row2 + "," + col2 + ") = " +
                          model.getCardAtCoord(coord2) + System.lineSeparator()
                 + "(" + row3 + "," + col3 + ") = " +
                          model.getCardAtCoord(coord3) + System.lineSeparator()
                 + System.lineSeparator());

      if (model.isValidSet(coord1, coord2, coord3)) {
        model.claimSet(coord1, coord2, coord3);
      }
      else {
        writeMessage("That's not a valid set. Try again." + System.lineSeparator());
      }
    }

    gameOverMessage();

  }

  private String ensureValidInput(String input) {
    Scanner scan = new Scanner(this.readable);
    while (!isValidInput(input)) {
      writeMessage("Invalid input. Try again." + System.lineSeparator());
      input = scan.next();
    }
    return input;
  }


  private void getBoard() throws IllegalStateException {
    try {
      setGameView.renderGrid();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private boolean isValidInput(String input) {
    boolean validInput;
    if (input.equalsIgnoreCase("q")) {
      validInput = true;
    } else if (input.matches("[0-9]+")) {
      validInput = true;
    }
    else if (input == null) {
      validInput = false;
    }
    else {
      validInput = false;
    }
    return validInput;
  }

  /**
   * Writes a message to the console for the user.
   * @param message the message to be displayed.
   * @throws IllegalStateException if message cannot be displayed correctly.
   */

  public void writeMessage(String message) throws IllegalStateException {
    try {
      setGameView.renderMessage(message);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  void welcomeMessage() throws IllegalStateException {
    writeMessage("\t \t \t \t \t ~ Let's Play Set! ~"  + System.lineSeparator());
  }

  private void gameNotStartedMessage(boolean hasStarted) {
    if (!hasStarted) {
      writeMessage("Game has not yet started." + System.lineSeparator());
    }
  }

  /**
   * Writes the Game quit message.
   */

  public void gameQuitMessage() {
    writeMessage("Game quit!" + System.lineSeparator());
    writeMessage("State of game when quit:" + System.lineSeparator());
    this.getBoard();
    writeMessage("Score: " + model.getScore() + System.lineSeparator());
  }

  private void gameOverMessage() {
    writeMessage("Game over!" + System.lineSeparator());
    writeMessage("Score: " + model.getScore() + System.lineSeparator());
  }


}
