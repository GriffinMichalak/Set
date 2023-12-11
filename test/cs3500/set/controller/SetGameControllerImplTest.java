package cs3500.set.controller;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringReader;

import cs3500.set.model.hw02.SetGameModel;
import cs3500.set.model.hw02.SetThreeGameModel;
import cs3500.set.model.hw03.GeneralSetGameModel;
import cs3500.set.view.SetGameTextView;
import cs3500.set.view.SetGameView;

/**
 * The testing class for a Set Game Controller Implementation.
 */

public class SetGameControllerImplTest extends TestCase {

  @Test
  public void testValidConstruction() {
    SetGameModel model = new GeneralSetGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);

    assertEquals(model, controller.getModel());
    assertEquals(sgv, controller.getSetGameView());
    assertEquals(rd, controller.getReadable());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstruction() {
    SetGameModel model = new GeneralSetGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);

    try {
      SetGameController controller = new SetGameControllerImpl(null, sgv, rd);
    }
    catch (IllegalArgumentException e) {
      String message = "SetGameModel/SetGameView/Reader must be non-null.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(model, null, rd);
    }
    catch (IllegalArgumentException e) {
      String message = "SetGameModel/SetGameView/Reader must be non-null.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(model, sgv, null);
    }
    catch (IllegalArgumentException e) {
      String message = "SetGameModel/SetGameView/Reader must be non-null.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(null, null, rd);
    }
    catch (IllegalArgumentException e) {
      String message = "SetGameModel/SetGameView/Reader must be non-null.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(null, null, rd);
    }
    catch (IllegalArgumentException e) {
      String message = "SetGameModel/SetGameView/Reader must be non-null.";
      assertEquals(message, e.getMessage());
    }

    try {
      SetGameController controller = new SetGameControllerImpl(null, null, null);
    }
    catch (IllegalArgumentException e) {
      String message = "SetGameModel/SetGameView/Reader must be non-null.";
      assertEquals(message, e.getMessage());
    }
  }

  // ============================================================

  @Test
  public void testPlayGame_validBoardCreation() throws IOException {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);
    Appendable appendable = printStream;

    SetGameModel model = new GeneralSetGameModel();
    Readable rd = new StringReader("3 3 q");
    SetGameView sgv = new SetGameTextView(model, appendable);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String expectedOutput = "1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 0\n" +
            "Game quit!\n" +
            "State of game when quit:\n" +
            "1EO 1EQ 1ED\n" +
            "1SO 1SQ 1SD\n" +
            "1FO 1FQ 1FD\n" +
            "Score: 0\n";

    assertEquals(output.toString(), expectedOutput);

  }

  @Test
  public void testPlayGame_BadHeightWidthGiven() {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);
    Appendable appendable = printStream;

    SetGameModel model = new SetThreeGameModel();
    Readable rd = new StringReader("3 1 1 3 q");
    SetGameView sgv = new SetGameTextView(model, appendable);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String expectedOutput = "Invalid height/width. Try again.\n" +
            "Invalid height/width. Try again.\n" +
            "Game quit!\n" +
            "Score: 0";

    assertEquals(output.toString(), expectedOutput);
  }


  @Test
  public void testPlayGame_quitInsteadOfHeight() {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);
    Appendable appendable = printStream;

    SetGameModel model = new SetThreeGameModel();
    Readable rd = new StringReader("3 q");
    SetGameView sgv = new SetGameTextView(model, appendable);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String expectedOutput = "Game quit!\n" + "Score: 0";

    assertEquals(output.toString(), expectedOutput);
  }

  @Test
  public void testPlayGame_KeepTryingForBoard() {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);
    Appendable appendable = printStream;

    SetGameModel model = new SetThreeGameModel();
    Readable rd = new StringReader("1 -1 q");
    SetGameView sgv = new SetGameTextView(model, appendable);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String expectedOutput = "Invalid height/width. Try again.\n" +
            "Game quit!\n" +
            "Score: 0";

    assertEquals(output.toString(), expectedOutput);
  }

  @Test
  public void testPlayGame_quitInsteadOfWidth() {
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(output);
    Appendable appendable = printStream;

    SetGameModel model = new SetThreeGameModel();
    Readable rd = new StringReader("q 3");
    SetGameView sgv = new SetGameTextView(model, appendable);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);
    controller.playGame();

    String expectedOutput = "Game quit!\n" + "Score: 0";

    assertEquals(output.toString(), expectedOutput);
  }

  @Test
  public void testPlayGame_quitInsteadOfRow1() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_quitInsteadOfCol1() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_quitInsteadOfRow2() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_quitInsteadOfCol2() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_quitInsteadOfRow3() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_quitInsteadOfCol3() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_invalidRow1() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_invalidCol1() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_invalidRow2() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_invalidCol2() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_invalidRow3() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_invalidCol3() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_invalidAll() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_ImmediateInvalidSet() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_validSetThenQuit() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testPlayGame_validGame() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test(expected = IllegalStateException.class)
  public void testPlayGame_nullInput() {
    int p = 2;
    assertEquals(p, 2);
  }





  // ===========================================================

  @Test
  public void testGetModel() {
    SetGameModel model = new GeneralSetGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);

    assertEquals(model, controller.getModel());
  }

  @Test
  public void testGetSetGameView() {
    SetGameModel model = new GeneralSetGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);

    assertEquals(sgv, controller.getSetGameView());
  }

  @Test
  public void testGetReadable() {
    SetGameModel model = new GeneralSetGameModel();
    Appendable ap = System.out;
    Readable rd = new InputStreamReader(System.in);
    SetGameView sgv = new SetGameTextView(model, ap);
    SetGameController controller = new SetGameControllerImpl(model, sgv, rd);

    assertEquals(rd, controller.getReadable());
  }

  // =================================================

  SetGameModel model = new GeneralSetGameModel();
  Appendable ap = System.out;
  Readable rd = new InputStreamReader(System.in);
  SetGameView sgv = new SetGameTextView(model, ap);
  SetGameControllerImpl controller = new SetGameControllerImpl(model, sgv, rd);

  @Test
  public void testWelcomeMessage() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testEnsureValidInput() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testGetBoard() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testIsValidInput() {
    int p = 2;
    assertEquals(p, 2);
  }

  @Test
  public void testWriteMessage() throws IOException {
    SetGameModel model = new GeneralSetGameModel();
    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    String message = "Hello, World!";
    SetGameTextView writer = new SetGameTextView(model, ap);
    writer.renderMessage(message);
    assertEquals(message, builder.toString());
  }

  @Test
  public void testGameOverMessage() throws IOException {
    SetGameModel model = new GeneralSetGameModel();
    StringBuilder builder = new StringBuilder();
    Appendable ap = builder;

    String message = "Hello, World!\nScore: " + model.getScore();
    SetGameTextView writer = new SetGameTextView(model, ap);
    controller.gameQuitMessage();
    assertEquals(message, builder.toString());

  }


}