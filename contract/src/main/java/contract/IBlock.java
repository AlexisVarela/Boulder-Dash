package contract;

import java.awt.Image;

public interface IBlock {

	public int getPosX();
	
	public void setPosX(int posX);
	
	public int getPosY();
	
	public void setPosY(int posY);
	
	public Image getSprite();
	
	public void setSprite(Image img);

	public void walkOn();
	
	public boolean isWalked();
	
	public boolean isFalling();

	public void release();
	
}
