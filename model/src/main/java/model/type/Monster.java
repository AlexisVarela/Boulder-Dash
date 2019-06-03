package model.type;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import contract.IBlock;
import model.Block;
import model.Model;

/**
 * Class Monster
 */
public class Monster extends Block implements IBlock {

	private Image sprite;

	public Monster(int posX, int posY) {
		super(posX, posY);
		try {
	    	this.setSprite(ImageIO.read(Model.class.getResource("/monster.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
}
