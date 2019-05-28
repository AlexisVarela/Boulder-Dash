package model.type;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import model.Block;
import model.Model;

public class End extends Block {

	public End(int posX, int posY) {
		super(posX, posY);
		try {
			this.setSprite(ImageIO.read(Model.class.getResource("/player.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
  
}
