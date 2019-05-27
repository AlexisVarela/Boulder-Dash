package model.type;

import model.Block;

public class Ground extends Block {

  protected boolean walkedState;

	public Ground(int posX, int posY, boolean walkedState) {
    super(posX, posY);
    this.walkedState = walkedState;
  }
  
}
