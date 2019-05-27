package model.type;

import model.Block;

public class Wall extends Block {

  protected boolean breakable;

	public Wall(int posX, int posY, boolean breakable) {
    super(posX, posY);
    this.breakable = breakable;
  }
  
}
