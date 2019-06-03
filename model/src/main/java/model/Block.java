package model;

import java.awt.Image;

import contract.IBlock;

public abstract class Block implements IBlock {
	// Attributes
	protected int posX;
	protected int posY;
	protected Image sprite;

	// Constructor
	public Block(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	// Methods
	public int getPosX() {
		return this.posX;
	}
	
	public void setPosX(int posX) {
		this.posX += posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public void setPosY(int posY) {
		this.posY += posY;
	}
	
	public Image getSprite() {
		return this.sprite;
	}
	
	public void setSprite(Image img) {
		this.sprite = img;
	}

	public void walkOn() {}
	
	public boolean isWalked() {
		return false;
	}
	
	public boolean isFalling() {
		return false;
	}

	public void release() {}
}
