package com.wordleClone.board;

public class Board {
  private int index = 0;
  private static int height = 6;
  private static int width = 5;
  private Box[][] boxes = new Box[height][width];
  private Letter[][] letters = new Letter[height][width];

  public Board() {
    // char alphabet = 'a';
    for (int i = 0; i < height; i++) {
      for (int n = 0; n < width; n++) {
        boxes[i][n] = new Box(n * 110, i * 110, index);
        letters[i][n] = new Letter(n * 110, i * 110, ' ', index++);
        // letters[i][n].setLetter(alphabet++);
      }
    }
  }

  public void undraw() {
    for (int i = 0; i < height; i++) {
      for (int n = 0; n < width; n++) {
        boxes[i][n].undraw();
        letters[i][n].undraw();
      }
    }
  }
  
  public Box[][] getBoxes() {
    return boxes;
  }

  public Letter[][] getLetters() {
    return letters;
  }

  public static int getWidth() {
    return width;
  }

  public static int getHeight() {
    return height;
  }

  public Box getBoxByIndex(int index) {
    for (int i = 0; i < height; i++) {
      for (int n = 0; n < width; n++) {
        if (boxes[i][n].getIndex() == index) {
          return boxes[i][n];
        }
      }
    }
    throw new IllegalArgumentException("Invalid index");
  }

  public Letter getLetterByIndex(int index) {
    for (int i = 0; i < height; i++) {
      for (int n = 0; n < width; n++) {
        if (letters[i][n].getIndex() == index) {
          return letters[i][n];
        }
      }
    }
    throw new IllegalArgumentException("Invalid index");
  }
}
