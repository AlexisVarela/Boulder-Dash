package contract;

import java.awt.Image;

public interface IPlayer {

	public int getPosX();

	public void setPosX(int posX);

	public int getPosY();

	public void setPosY(int posY);
	
	public Image getSprite();

	public void setSprite(Image sprite);

	public int getScore();

	public void setScore(int score);
	
	public void die();
	
}
