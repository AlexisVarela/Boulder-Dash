package model;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import contract.IPlayer;

/**
 * Class Player
 */
public class Player implements IPlayer {
	private int posX;
	private int posY;
	private Image sprite;
	private int score;

	public Player() {
		this.posX = 16;
		this.posY = 16;
		this.score = 0;
		try {
	    	this.setSprite(ImageIO.read(Model.class.getResource("/Normal.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		try {
			if (posX > 0) {
		    	this.setSprite(ImageIO.read(Model.class.getResource("/Droite.png")));
			} else {
				this.setSprite(ImageIO.read(Model.class.getResource("/Gauche.png")));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.posX += posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		try {
			if (posY > 0) {
		    	this.setSprite(ImageIO.read(Model.class.getResource("/Bas.png")));
			} else {
				this.setSprite(ImageIO.read(Model.class.getResource("/Haut.png")));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.posY += posY;
	}
	
	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}
	
	/**
	 * Prints a message on death and close the window.
	 */
	public void die() {
		try {
			this.setSprite(ImageIO.read(Model.class.getResource("/Death.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
