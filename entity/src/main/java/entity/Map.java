package entity;

import java.util.ArrayList;

import org.json.JSONObject;

import model.Block;
import model.type.Diamond;
import model.type.End;
import model.type.Ground;
import model.type.Stone;
import model.type.Wall;

/**
 * The Class HelloWorld.
 *
 * @author Jean-Aymeric Diet
 */
public class Map extends Entity {

	private String dataMap;
	private ArrayList<Block> generatedMap = new ArrayList<Block>();
	private int Width;
	private int Height;

	public Map(String dataMap, int width, int height) {
		this.setDataMap(dataMap);
		this.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * Instantiates a new hello world.
	 */
	public Map() {
		this("",0,0);
	}

	public String getDataMap() {
		return dataMap;
	}

	public void setDataMap(String dataMap) {
		this.dataMap = dataMap;
		if (!dataMap.equals("")) {
			generateMap();
		}
	}
	
	

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public ArrayList<Block> getGeneratedMap() {
		return generatedMap;
	}

	public void setGeneratedMap(ArrayList<Block> generatedMap) {
		this.generatedMap = generatedMap;
	}

	public void generateMap() {
		JSONObject obj = new JSONObject(this.dataMap);
		for (int i=0; i<22*16; i+=16) {
	    	  for(int x=0; x<40*16; x+=16) {
	    		  //System.out.println(obj.getJSONObject(String.valueOf(i/32)).getJSONObject(String.valueOf(x/32)).getString("type"));
	    		  //System.out.println("------------------");
	    		  String block = obj.getJSONObject(String.valueOf(i/16)).getJSONObject(String.valueOf(x/16)).getString("type");
	    		  switch(block) {
	    		  	case "unbreakableWall":
	    		  		Wall uwall = new Wall(x, i, false);
		    			this.generatedMap.add(uwall);
		    			break;
	    		  	case "breakableWall":
	    		  		Wall wall = new Wall(x, i, true);
		    			this.generatedMap.add(wall);
		    			break;
	    		  	case "stone":
	    		  		Stone stone = new Stone(x, i);
		    			this.generatedMap.add(stone);
		    			break;
	    		  	case "diamond":
	    		  		Diamond diamond = new Diamond(x, i);
		    			this.generatedMap.add(diamond);
		    			break;
	    		  	case "dirt":
	    		  		Ground ground = new Ground(x, i, false);
		    			this.generatedMap.add(ground);
		    			break;
	    		  	case "void":
	    		  		Ground vground = new Ground(x,i,true);
	    		  		this.generatedMap.add(vground);
	    		  		break;
	    		  	case "end":
	    		  		End end = new End(x, i);
	    		  		this.generatedMap.add(end);
	    		  		break;	    		
	    		  }
	    	  }
	      }
	}
	

}
