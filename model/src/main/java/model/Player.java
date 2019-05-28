package model;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	private int posX;
	private int posY;
	private Image sprite;

	public Player() {
		this.posX = 16;
		this.posY = 16;
		try {
	    	this.setSprite(ImageIO.read(Model.class.getResource("/player.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX += posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY += posY;
	}
	
	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
}
