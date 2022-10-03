package com.wordleClone.board;

import com.wordleClone.pkg.*;

public class Letter implements BoardPieces {
  private Text text;
  private char letter;
  private Color color = new Color(255, 255, 255);
  private int x;
  private int y;
  private int index; 

  public Letter(int x, int y, char letter, int index) {
    this.x = x;
    this.y = y;
    this.letter = Character.toUpperCase(this.letter);
    this.index = index;
    text = new Text(x, y, Character.toString(letter));
    text.grow(text.getWidth() * 6, text.getHeight() * 3);
    text.translate(text.getWidth(), text.getHeight() / 3);
    text.setColor(color);
    text.draw();
  }
  
  public void undraw() {
    text.undraw();
  }

  public void setLetter(char letter) {
    this.letter = Character.toUpperCase(letter);
    text.setText(Character.toString(this.letter));
  }

  public char getLetter() {
    return letter;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getIndex() {
    return index;
  }
}
