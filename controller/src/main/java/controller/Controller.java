package controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

import contract.ControllerOrder;
import contract.IController;
import contract.IModel;
import contract.IView;
import model.Block;
import model.type.Ground;
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
		}
		int idBlock = ((model.getPlayer().getPosY()+y*16)/16 * model.getMap().getWidth()) + ((model.getPlayer().getPosX()+x*16)/16);
		Block block = model.getMap().getGeneratedMap().get(idBlock);
		actionBlock(block, idBlock);
		if (!Arrays.asList(notMove).contains(block.getClass().getName())) {
			block.walkOn();
			return true;
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
				break;
			case "model.type.Stone":
				if (playerX == blockX - 1) {
					Block nextBlock = model.getMap().getGeneratedMap().get(idBlock+1);
					if (nextBlock.isWalked()) {
						model.getMap().getGeneratedMap().set(idBlock+1, new Stone(nextBlock.getPosX(), nextBlock.getPosY()));
						model.getMap().getGeneratedMap().set(idBlock, new Ground(block.getPosX(), block.getPosY(), true));
						model.getPlayer().setPosX(16);
					}
				} else if (playerX == blockX + 1) {
					Block nextBlock = model.getMap().getGeneratedMap().get(idBlock-1);
					if (nextBlock.isWalked()) {
						model.getMap().getGeneratedMap().set(idBlock-1, new Stone(nextBlock.getPosX(), nextBlock.getPosY()));
						model.getMap().getGeneratedMap().set(idBlock, new Ground(block.getPosX(), block.getPosY(), true));
						model.getPlayer().setPosX(-16);
					}
				}

			default:
				break;
		}
	}

}
