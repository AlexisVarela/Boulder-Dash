package model.type;

import java.io.IOException;

import javax.imageio.ImageIO;

import contract.IBlock;
import model.Block;
import model.Model;

/**
 * Class Wall
 */
public class Wall extends Block implements IBlock {

  protected boolean breakable;

	public Wall(int posX, int posY, boolean breakable) {
    super(posX, posY);
    try {
    	if (breakable) {
    		this.setSprite(ImageIO.read(Model.class.getResource("/breakableWall.png")));
    	} else {
    		this.setSprite(ImageIO.read(Model.class.getResource("/unbreakableWall.png")));
    	}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    this.breakable = breakable;
  }
  
}
