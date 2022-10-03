package com.wordleClone.board;

import com.wordleClone.pkg.*;

public class Box implements BoardPieces {
  private Rectangle box;
  private Color color = new Color(0, 0, 0);
  private int x;
  private int y;
  private int index;

  public Box(int x, int y, int index) {
    this.x = x;
    this.y = y;
    this.index = index;
    box = new Rectangle(x, y, 100, 100);
    box.setColor(color);
    box.fill();
  }

  public void undraw() {
    box.undraw();
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

  public void setColor(Color color) {
    this.color = color;
    box.setColor(color);
  }

  public Color getColor() {
    return color;
  }
}
