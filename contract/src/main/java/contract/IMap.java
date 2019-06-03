package contract;

import java.util.ArrayList;

public interface IMap {

	public String getDataMap();

	public void setDataMap(String dataMap);

	public int getWidth();

	public void setWidth(int width);

	public int getHeight();

	public void setHeight(int height);
	
	public ArrayList<IBlock> getGeneratedMap();

	public void setGeneratedMap(ArrayList<IBlock> generatedMap);

	public int getDiamonds();

	public void setDiamonds(int diamonds);

	public IBlock getEnd();

	public void setEnd(IBlock end);

	public void generateMap();
	
}
