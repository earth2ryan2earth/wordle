package com.wordleClone;

import com.wordleClone.pkg.*;
import com.wordleClone.board.*;

import java.awt.event.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * TODO Filter input -- check if valid word
 * TODO Use Json word list instead of txt file
 * TODO Disallow yellow when green exists
 */

public class Wordle {
  private static Board board;
  private static Box[][] boxes;
  private static Letter[][] letters;
  private static Color clrCorrect;
  private static Color clrContains;
  private static Color clrIncorrect;
  private static Color clrBg;
  private static Text result;
  private static Rectangle resultBg;
  private static int row = 0;
  private static int col = 0;
  private static String line;
  private static long length;
  private static char[] word = new char[Board.getWidth()];
  private static boolean drawn;
  private static int rand;
  private static int lineNum;
  private static LineNumberReader reader;

  public static void main(String[] args) {
    // Assignment of global variables
    board = new Board();
    boxes = board.getBoxes();
    letters = board.getLetters();
    clrCorrect = new Color(83, 141, 78);
    clrContains = new Color(181, 159, 59);
    clrIncorrect = new Color(234, 35, 35);
    clrBg = new Color(0, 0, 0);
    result = new Text(Board.getWidth() * 110 / 2 - 40, Board.getHeight() * 110 / 2 - 30, "You won!");
    result.grow(result.getWidth() * 3, result.getHeight() * 3);
    resultBg = new Rectangle(result.getX() - 50, result.getY() - 30, result.getWidth() + 110,
        result.getHeight() + 80);
    resultBg.setColor(new Color(91, 91, 91));
    result.setColor(clrCorrect);
    drawn = false;

    startGame();

    Canvas.getInstance().frame.addKeyListener(new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        int keyCode = e.getKeyCode();

        // If game has ended, reset the board
        if (drawn == true) {
          result.undraw();
          resultBg.undraw();
          drawn = false;
          row = Board.getHeight();
          getRandWord(reader);
          reset();
        }

        // If keypress is a backspace
        if (col > 0 && keyCode == 8) {
          // Go back a column & set it blank
          col--;
          letters[row][col].setLetter(' ');
        }
        // If keypress is within the Board width & is a letter
        if (col >= 0 && col < Board.getWidth() && Character.isLetter(keyChar)) {
          // Set the value & move to the next column
          letters[row][col].setLetter(keyChar);
          col++;
        }

        // When letters exceed width and enter is pressed
        if (col >= Board.getWidth() && keyCode == 10) {
          // Go back to column 0
          col = 0;

          for (int i = 0, count = 0; i < Board.getWidth(); i++) {
            // Color yellow if letter is contained within the word
            for (int n = 0; n < Board.getWidth(); n++) {
              if (letters[row][i].getLetter() == word[n]) {
                boxes[row][i].setColor(clrContains);
              }
            }
            // Color green if letter matches position in word
            if (letters[row][i].getLetter() == word[i]) {
              boxes[row][i].setColor(clrCorrect);
              count++;
            }
            // if words match, display winning message
            if (count == 5) {
              resultBg.fill();
              result.setText("You won!");
              result.setColor(clrCorrect);
              result.draw();
              drawn = true;
              row = 0;
            }
          }

          // If board does not exceed height
          if (row < Board.getHeight() - 1) {
            // Go to next row
            row++;
          }

          // If game over, display losing message
          else {
            resultBg.fill();
            result.setColor(clrIncorrect);
            result.setText(new String(word));
            result.draw();
            drawn = true;
          }
        }
      }

      public void keyReleased(KeyEvent e) {
      }

      public void keyTyped(KeyEvent e) {
      }
    });
  }

  public static void startGame() {
    // Maven runs from "004 Make a Game/wordleClone" so path must be extend from there
    try {
      File file = new File("src/main/java/com/wordleClone/fiveLetterWords.txt");
      reader = new LineNumberReader(new FileReader(file.getAbsolutePath()));
      reader.mark(50000);
      reader.skip(Long.MAX_VALUE);
      length = reader.getLineNumber();
      reader.reset();
      System.out.println("Reader Initialized");
      getRandWord(reader);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void reset() {
    // Reset the board
    col = 0;
    row = 0;
    for (int i = 0; i < Board.getHeight(); i++) {
      for (int n = 0; n < Board.getWidth(); n++) {
        boxes[i][n].setColor(clrBg);
        letters[i][n].setLetter(' ');
      }
    }
  }
  
  public static void getRandWord(LineNumberReader reader) {    
    try {
      rand = (int) Math.floor((Math.random() * length));
      for (int i = 0; i < length; i++) {
        lineNum = reader.getLineNumber();
        line = reader.readLine();
        if (line != null && (rand == lineNum)) {
          word = line.toUpperCase().toCharArray();
          break;
        }
      }
      reader.reset();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
