package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.json.JSONObject;

import contract.ControllerOrder;
import contract.IBlock;
import contract.IController;
import contract.IModel;
import contract.IPlayer;
import contract.IView;
import model.Block;
import model.Player;
import model.type.Diamond;
import model.type.Ground;
import model.type.Monster;
import model.type.Stone;

/**
 * The Class Controller.
 */
public final class Controller implements IController {

	/** The view. */
	private IView		view;

	/** The model. */
	private IModel	model;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view
	 *          the view
	 * @param model
	 *          the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);
	}

	/**
     * Control.
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#control()
	 */
	public void control() {
		this.view.printMessage("Appuyez sur Z,Q,S,D pour vous déplacer.");
	}

	/**
     * Sets the view.
     *
     * @param pview
     *            the new view
     */
	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 * Sets the model.
	 *
	 * @param model
	 *          the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
     * Order perform.
     *
     * @param controllerOrder
     *            the controller order
     */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) {
		if (canMove(controllerOrder)) {
			switch (controllerOrder) {
				case Top:
					this.model.getPlayer().setPosY(-16);
					break;
				case Right:
					this.model.getPlayer().setPosX(16);
					break;
				case Bottom:
					this.model.getPlayer().setPosY(16);
					break;
				case Left:
					this.model.getPlayer().setPosX(-16);
					break;
			}
		}
	}
	
	public boolean canMove(ControllerOrder controllerOrder) {
		int x = 0;
		int y = 0;
		String[] notMove = {"model.type.Wall", "model.type.Stone"};
		if (controllerOrder != null) {
			switch(controllerOrder) {
				case Top:
					y = -1;
					break;
				case Right:
					x = 1;
					break;
				case Bottom:
					y = 1;
					break;
				case Left:
					x = -1;
					break;
				default:
					break;
			}
			
			int idBlock = ((model.getPlayer().getPosY()+y*16)/16 * model.getMap().getWidth()) + ((model.getPlayer().getPosX()+x*16)/16);
			IBlock block = model.getMap().getGeneratedMap().get(idBlock);
			actionBlock((Block) block, idBlock);
			if (!Arrays.asList(notMove).contains(block.getClass().getName())) {
				block.walkOn();
				return true;
			}
		}
		
		return false;
	}
	
	public void actionBlock(Block block, int idBlock) {
		int playerX = model.getPlayer().getPosX()/16;
		int blockX = block.getPosX()/16;
		switch (block.getClass().getName()) {
			case "model.type.Diamond":
				model.getMap().getGeneratedMap().set(idBlock, new Ground(block.getPosX(), block.getPosY(), true));
				model.getPlayer().setScore(1);
				if (model.getPlayer().getScore() == model.getMap().getDiamonds()) {
					this.model.getMap().getEnd().release();
				}
				break;
			case "model.type.Stone":
				if (playerX == blockX - 1) {
					IBlock nextBlock = model.getMap().getGeneratedMap().get(idBlock+1);
					if (nextBlock.isWalked()) {
						model.getMap().getGeneratedMap().set(idBlock+1, new Stone(nextBlock.getPosX(), nextBlock.getPosY()));
						model.getMap().getGeneratedMap().set(idBlock, new Ground(block.getPosX(), block.getPosY(), true));
						model.getPlayer().setPosX(16);
					}
				} else if (playerX == blockX + 1) {
					IBlock nextBlock = model.getMap().getGeneratedMap().get(idBlock-1);
					if (nextBlock.isWalked()) {
						model.getMap().getGeneratedMap().set(idBlock-1, new Stone(nextBlock.getPosX(), nextBlock.getPosY()));
						model.getMap().getGeneratedMap().set(idBlock, new Ground(block.getPosX(), block.getPosY(), true));
						model.getPlayer().setPosX(-16);
					}
				}
				break;
			
			case "model.type.End":
				if (model.getPlayer().getScore() == model.getMap().getDiamonds()) {
					this.view.printMessage("YOU WON");
					System.exit(0);
				}
				break;
			case "model.type.Monster":
				this.die();
				break;
				
			default:
				break;
		}
	}
	
	public void run() {
		int i=0;
		while(true) {
			i++;
			this.checkForFall();
			if (i%30 == 0) {
				this.moveMonster();
			}
			this.view.actualiser();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void checkForFall() {
		String[] fallable = {"model.type.Stone", "model.type.Diamond"};
		ArrayList<IBlock> mapArray = model.getMap().getGeneratedMap();
		
			for (int i=mapArray.size()-1; i>0; i--) {
				IBlock block = mapArray.get(i);
				if (Arrays.asList(fallable).contains(block.getClass().getName())) {
					IBlock nextBlock = mapArray.get(i + model.getMap().getWidth());
					if (nextBlock.isWalked() && (nextBlock.getPosY()/16 != model.getPlayer().getPosY()/16 || nextBlock.getPosX()/16 != model.getPlayer().getPosX()/16)) {
						if (block.getClass().getName().equals("model.type.Stone")) {
							Stone newBlock = new Stone(block.getPosX(), block.getPosY() + 16);
							newBlock.setFalling(true);
							mapArray.set(i + model.getMap().getWidth(), newBlock);
						} else {
							Diamond newBlock = new Diamond(block.getPosX(), block.getPosY() + 16);
							newBlock.setFalling(true);
							mapArray.set(i + model.getMap().getWidth(), newBlock);
						}
						mapArray.set(i, new Ground(block.getPosX(), block.getPosY(), true));
						try {
							this.view.actualiser();
							Thread.sleep(150);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (nextBlock.getClass().getName().equals("model.type.Monster")) {
						this.explode(i);
					} else if (model.getPlayer().getPosX()/16 == nextBlock.getPosX()/16 && model.getPlayer().getPosY()/16 == nextBlock.getPosY()/16 && block.isFalling()) {
						this.die();
					}
				}
			}
	}
	
	public void moveMonster() {
		ArrayList<IBlock> mapArray = model.getMap().getGeneratedMap();
		int x=0;
		int y=0;
		for (int i=0; i<mapArray.size(); i++) {
			IBlock block = mapArray.get(i);
			if (block.getClass().getName().equals("model.type.Monster")) {
				Random rand = new Random();
				int move = rand.nextInt(4);
				switch (move) {
					case 0:
						y = -1;
						break;
					case 1:
						x = 1;
						break;
					case 2:
						y = 1;
						break;
					case 3:
						x = -1;
						break;
				}
				
				int idNextBlock = ((block.getPosY()+y*16)/16 * model.getMap().getWidth()) + ((block.getPosX()+x*16)/16);
				IBlock nextBlock = model.getMap().getGeneratedMap().get(idNextBlock);
				if (nextBlock.getClass().getName().equals("model.type.Ground") && nextBlock.isWalked()) {
					mapArray.set(idNextBlock, new Monster(nextBlock.getPosX(), nextBlock.getPosY()));
					mapArray.set(i, new Ground(block.getPosX(), block.getPosY(), true));
					block.setPosX(x*16);
					block.setPosY(y*16);
					if (block.getPosX() == model.getPlayer().getPosX() && block.getPosY() == model.getPlayer().getPosY()) {
						this.die();
					}
				}
			}
		}
	}

	public void die() {
		this.model.getPlayer().die();
		this.explode(0);
		this.view.actualiser();
		this.view.printMessage("YOU DIED");
		System.exit(0);
	}
	
	public void explode(int i) {
		ArrayList<IBlock> mapArray = model.getMap().getGeneratedMap();
		if (i == 0) {
			IPlayer player = model.getPlayer();
			while (mapArray.get(i).getPosX() != player.getPosX() || mapArray.get(i).getPosY() != player.getPosY()) {
				i++;
			}
		}
		
		int width = model.getMap().getWidth();
		int[] explosed = {
				i - width - 1,
				i - width,
				i - width + 1,
				i - 1,
				i,
				i + 1,
				i + width - 1,
				i + width,
				i + width + 1
		};
		for (int x=0; x<explosed.length; x++) {
			mapArray.set(explosed[x], new Diamond(mapArray.get(explosed[x]).getPosX(), mapArray.get(explosed[x]).getPosY()));
		}
	}
}
