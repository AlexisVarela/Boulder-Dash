package model.type;

import java.io.IOException;

import javax.imageio.ImageIO;

import contract.IBlock;
import model.Block;
import model.Model;

/**
 * Class Ground
 */
public class Ground extends Block implements IBlock {

  protected boolean walkedState;

	public Ground(int posX, int posY, boolean walkedState) {
    super(posX, posY);
    try {
    	if (walkedState) {
    		this.setSprite(ImageIO.read(Model.class.getResource("/voidGround.png")));
    	} else {
    		this.setSprite(ImageIO.read(Model.class.getResource("/ground.png")));
    	}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    this.setWalked(walkedState);
  }
	
	public void walkOn() {
		if(!this.isWalked()) {
			try {
				this.setSprite(ImageIO.read(Model.class.getResource("/voidGround.png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setWalked(true);
		}
	}

	public boolean isWalked() {
		return (boolean) this.walkedState;
	}

	public void setWalked(boolean walkedState) {
		this.walkedState = walkedState;
	}
	
	
  
}
